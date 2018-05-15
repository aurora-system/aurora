package com.spring.aurora.controller;

import com.spring.aurora.entity.DailySalesEntity;
import com.spring.aurora.entity.DailyTotalsEntity;
import com.spring.aurora.entity.OrderCustomerEntity;
import com.spring.aurora.entity.OrderProductEntity;
import com.spring.aurora.model.*;
import com.spring.aurora.service.*;
import com.spring.aurora.util.OrderFormValidator;
import com.spring.aurora.util.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    private static DecimalFormat df2 = new DecimalFormat("#.00");
    
    private static final String monthsWith31[] = {"01", "03", "05", "07", "08", "10", "12"};
    private static final String monthsStr[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

//	@Autowired
//	OrderFormValidator orderFormValidator;
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(orderFormValidator);
//	}

	@Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private DebtService debtService;
    
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderProductService orderProductService;
    
    @Autowired
    private CustomerPriceService customerPriceService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/neworder", method = RequestMethod.GET)
	public String newOrder(@RequestParam String customerId, Model model) {
		logger.debug("New Order form for customer: " + customerId);
		OrderProductEntity ope = new OrderProductEntity();
		Order order = new Order();
		order.setCustomerId(customerId);
		ope.setOrder(order);
		
		List<Product> productList = productService.findAll();
		List<CustomerPrice> cpList = customerPriceService.findAllByCustomerId(customerId);
		
		List<Product> overridenProductList = new ArrayList<>();
		boolean overriden = false;
		
		for (Product p : productList) {
			
			overriden = false;
			
			for (CustomerPrice cp : cpList) {
				if (p.getProductId().equalsIgnoreCase(cp.getProductId())) {
					Product overridenProduct = p;
					p.setSellingPrice(cp.getSellingPrice());
					overridenProductList.add(overridenProduct);
					overriden = true;
					break;
				}
			}
			
			if (!overriden) {
				overridenProductList.add(p);
			}
		}
		
		if (overridenProductList.size() > 0) {
			model.addAttribute("productList", overridenProductList);
		} else {
			model.addAttribute("productList", productList);
		}
		
		List<OrderProduct> opList = new ArrayList<>();
		for (Product p : productList) {
			OrderProduct op = new OrderProduct();
			op.setOrderId(order.getOrderId());
			op.setProductId(p.getProductId());
			opList.add(op);
		}
		
		// Use this for update
		// List<OrderProduct> opList = orderProductService.findAllByOrderId(order.getOrderId());
		ope.setOpList(opList);
		ope.setSaveReturned("No");
		
		model.addAttribute("opeForm", ope);
		model.addAttribute("customerId", customerId);
		//model.addAttribute("newDrNumber", orderService.getNewDrNumber());
		
		
		//List<CustomerPrice> customerPriceList = customerPriceService.findAllByCustomerId(customerId);
		
		Customer customer = customerService.view(customerId);
		model.addAttribute("customerName", customer.getName());
		model.addAttribute("customerAddress", customer.getAddress());
		model.addAttribute("saveReturnedAnswers",new String[]{"Yes","No"});
		return "new-order";
	}
    
//	@RequestMapping(value = "/neworder", method = RequestMethod.GET)
//	public String newOrder(@RequestParam String customerId, Model model) {
//		logger.debug("New Order form for customer: " + customerId);
//		Order order = new Order();
//		order.setCustomerId(customerId);
//		model.addAttribute("orderForm", order);
//		model.addAttribute("customerId", customerId);
//		model.addAttribute("newDrNumber", orderService.getNewDrNumber());
//		
//		List<Product> productList = productService.findAll();
//		model.addAttribute("products", productList);
//		
//		//List<CustomerPrice> customerPriceList = customerPriceService.findAllByCustomerId(customerId);
//		
//		Customer customer = customerService.view(customerId);
//		model.addAttribute("customerName", customer.getName());
//		return "new-order";
//	}
	
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editOrder(Model model, String orderId) {
        logger.debug("Edit Order form.");
        
        OrderProductEntity ope = new OrderProductEntity();
        Order order = orderService.findOrderByOrderId(orderId);
        
        if (order.getRoundReturned().equalsIgnoreCase("0")) {
        	order.setRoundReturned(null);
        }
        if (order.getSlimReturned().equalsIgnoreCase("0")) {
        	order.setSlimReturned(null);
        }
        
        ope.setOrder(order);
        
        Customer customer = customerService.view(order.getCustomerId());
		model.addAttribute("customerName", customer.getName());
		model.addAttribute("customerAddress", customer.getAddress());
		
		List<Product> productList = productService.findAll();
		List<CustomerPrice> cpList = customerPriceService.findAllByCustomerId(order.getCustomerId());
		
		List<Product> overriddenProductList = new ArrayList<>();
		boolean overridden = false;
		
		for (Product p : productList) {
			
			overridden = false;
			
			for (CustomerPrice cp : cpList) {
				if (p.getProductId().equalsIgnoreCase(cp.getProductId())) {
					Product overridenProduct = p;
					p.setSellingPrice(cp.getSellingPrice());
					overriddenProductList.add(overridenProduct);
					overridden = true;
					break;
				}
			}
			
			if (!overridden) {
				overriddenProductList.add(p);
			}
		}
		
		if (overriddenProductList.size() > 0) {
			model.addAttribute("productList", overriddenProductList);
		} else {
			model.addAttribute("productList", productList);
		}
		
		List<OrderProduct> opList = orderProductService.findAllByOrderId(orderId);
		
		List<OrderProduct> opListForDisplay = new ArrayList<>();
		
		for (Product p : productList) {
			
			boolean hasMatch = false;
			
			for (OrderProduct op : opList) {
				if (p.getProductId().equalsIgnoreCase(op.getProductId())) {
					opListForDisplay.add(op);
					hasMatch = true;
					break;
				}
			}
			
			if (!hasMatch) {
				OrderProduct op = new OrderProduct();
				op.setOrderId(order.getOrderId());
				op.setProductId(p.getProductId());
				opListForDisplay.add(op);
			}
		}
		
		ope.setOpList(opListForDisplay);
		model.addAttribute("opeForm", ope);
		model.addAttribute("drNumber", order.getDeliveryReceiptNum());
        //model.addAttribute("orderForm", order);
        return "edit-order";
    }
    
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String editOrder(Model model, String orderId) {
//        logger.debug("Edit Order form.");
//        
//        //Customer customer = customerService.view(orderId);
//        Order order = orderService.findOrderByOrderId(orderId);
//        Customer customer = customerService.view(order.getCustomerId());
//		model.addAttribute("customerName", customer.getName());
//		model.addAttribute("drNumber", order.getDeliveryReceiptNum());
//        model.addAttribute("orderForm", order);
//        return "edit-order";
//    }

	@RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String dailySales(Model model, @RequestParam(value="d", defaultValue="today", required=false) String d ) {
        logger.info("Daily sales report.");
        
        int totalSlimDelivered = 0;
        int totalRoundDelivered = 0;
        int totalSlimReturned = 0;
        int totalRoundReturned = 0;
        Double totalExpenses = 0.00;
        Double totalCashPayments = 0.00;
        Double totalCheckPayments = 0.00;
        Double totalDebt = 0.00;
        
        List<DailySalesEntity> dseList = new ArrayList<>();
        
        Date date = ("today".equalsIgnoreCase(d)) ? Date.valueOf(LocalDate.now()) : Date.valueOf(LocalDate.parse(d));
        String datePicked = new SimpleDateFormat("MMM dd YYYY").format(date);
        model.addAttribute("datePicked", datePicked);
        
        List<Order> orderList = orderService.findAllOrdersToday(date);
        
        for (Order o : orderList) {
        	if (o.getStatus().equalsIgnoreCase("Delivered")) {
        		DailySalesEntity dse = new DailySalesEntity();
            	dse.setOrder(o);
            	
            	Customer c = customerService.view(o.getCustomerId());
            	dse.setCustomerName(c.getName());
            	totalCashPayments += o.getAmountPaid();
            	dse.setPaidCash(o.getAmountPaid());
            	
            	totalSlimDelivered += o.getSlimCount();
            	totalRoundDelivered += o.getRoundCount();
            	//totalSlimReturned += Integer.parseInt(o.getSlimReturned());
            	//totalRoundReturned += Integer.parseInt(o.getRoundReturned());
            	
            	
            	Double ar = o.getTotalAmount() - o.getAmountPaid();
            	totalDebt += ar;
            	dse.setBalanceAmount(Double.parseDouble(df2.format(ar)));
            	
            	Timestamp dateTime = o.getCreatedAt();
            	String formattedDate = new SimpleDateFormat("h:mm a").format(dateTime);
            	
            	dse.setReturnedRound(o.getRoundReturned());
            	dse.setReturnedSlim(o.getSlimReturned());
            	dse.setRemarks(o.getRemarks());
            	dse.setDateAndTime(formattedDate);
            	dseList.add(dse);
        	}
        }
        
        // For Expenses
        List<Expense> expenseList = new ArrayList<>();
        expenseList = expenseService.findAllByDate(date);
        
        for (Expense e : expenseList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setRemarks(e.getDescription());
        	totalExpenses += e.getAmount();
        	dse.setExpenseAmount(Double.parseDouble(df2.format(e.getAmount())));
        	dseList.add(dse);
        }
        
        // For Payments
        List<Payment> paymentList = new ArrayList<>();
        paymentList = paymentService.findAllByDate(date);
        
        for (Payment p : paymentList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setCustomerName(customerService.view(p.getCustomerId()).getName());
        	dse.setRemarks(p.getRemarks());
        	
        	if (p.getPaymentType().equalsIgnoreCase("CASH")) {
        		dse.setPaidCash(Double.parseDouble(df2.format(p.getAmount())));
        		totalCashPayments += p.getAmount();
        	} else {
        		dse.setPaidCheck(Double.parseDouble(df2.format(p.getAmount())));
        		totalCheckPayments += p.getAmount();
        	}
        	dseList.add(dse);
        }
        
        // For Containers
        List<Container> containerList = new ArrayList<>();
        containerList = containerService.findContainerActivityByDate(date);
        
        for (Container c : containerList) {
        	DailySalesEntity dse = new DailySalesEntity();
        	dse.setCustomerName(customerService.view(c.getCustomerId()).getName());
        	dse.setRemarks("Returned containers.");
        	
        	if (c.getStatus().equalsIgnoreCase("R")) {
        		totalRoundReturned += c.getRoundCount();
        		totalSlimReturned += c.getSlimCount();
        		dse.setReturnedRound(Integer.valueOf(c.getRoundCount()).toString());
        		dse.setReturnedSlim(Integer.valueOf(c.getSlimCount()).toString());
        		dseList.add(dse);
        		// TODO: INTRODUCE RETURNED WITH ORDER - RO, R IS ONLY FOR SEPARATE RETURNS
        	} else if (c.getStatus().equalsIgnoreCase("RO")) {
        		totalRoundReturned += c.getRoundCount();
        		totalSlimReturned += c.getSlimCount();
        	}
        }
        
        model.addAttribute("dailySales", dseList);
        
        model.addAttribute("totalSlimDelivered", totalSlimDelivered);
        model.addAttribute("totalRoundDelivered", totalRoundDelivered);
        model.addAttribute("totalSlimReturned", totalSlimReturned);
        model.addAttribute("totalRoundReturned", totalRoundReturned);
        
        model.addAttribute("totalExpenses", ReportUtil.applyCurrencyFormat("" + totalExpenses));
        model.addAttribute("totalCashPayments", ReportUtil.applyCurrencyFormat("" + totalCashPayments));
        model.addAttribute("totalCheckPayments", ReportUtil.applyCurrencyFormat("" + totalCheckPayments));
        model.addAttribute("totalDebt", ReportUtil.applyCurrencyFormat("" + totalDebt));
        
        double netCash = totalCashPayments - totalExpenses;
        model.addAttribute("netCash", ReportUtil.applyCurrencyFormat("" + netCash));
        return "daily-sales";
    }
    
	@RequestMapping(value = "/monthly", method = RequestMethod.GET)
	public String monthlyTotals(Model model, @RequestParam(value = "d", required = false) String datePicked) {
		
		List<DailyTotalsEntity> dteList = new ArrayList<>();
		int days = 31;
		String m = "";
		String y = "";
		
		Double grandTotalCash = 0.00;
		Double grandTotalAr = 0.00;
		Double grandTotalPayment = 0.00;
		Double grandTotalExpense = 0.00;
		int grandTotalRound = 0;
		int grandTotalSlim = 0;
		int grandTotalContainers = 0;
		
		System.out.println("Date Picked: " + datePicked);
		
		if (datePicked == null || datePicked.equalsIgnoreCase("today") || datePicked.equalsIgnoreCase("")) {
			
			LocalDateTime now = LocalDateTime.now();
			
			int year = now.getYear();
			int month = now.getMonthValue();
			
			System.out.println(month);
			System.out.println(year);
			
			if (month<10) {
				m = "0" + month;
			} else {
				m = "" + month;
			}
			
			y = "" + year;
		} else {
			String[] splitDate = datePicked.split("-");
			m = splitDate[0];
			System.out.println("M: " + m);
			
			if (splitDate.length > 1) {
				y = splitDate[1];
			}
		}
		
		List<String> monthsWith31List = Arrays.asList(monthsWith31);
		
		if (!monthsWith31List.contains(m)) {
			if (Integer.valueOf(m) == 2) {
				if (ReportUtil.isLeapYear(Integer.parseInt(y))) {
					days = 29;
				} else {
					days = 28;
				}
			} else {
				days = 30;
			}
		}
		
		for (int i = 1; i <= days; i++) {
			
			DailyTotalsEntity dte = new DailyTotalsEntity();
			Double totalCash = 0.00;
			Double totalAr = 0.00;
			Double totalPayment = 0.00;
			Double totalExpense = 0.00;
			int totalRoundDelivered = 0;
			int totalSlimDelivered = 0;
			
			String day = "";
			String month = "";
			
			if (i<10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			
			month = "" + m;
			
			String dateStr = y + "-" + month + "-" + day;
			System.out.println(dateStr);
			Date date = (Date.valueOf(LocalDate.parse(dateStr)));
			List<Order> orderList = orderService.findAllOrdersToday(date);
			
			for (Order o : orderList) {
				totalCash += o.getAmountPaid();
				totalAr += (o.getTotalAmount() - o.getAmountPaid());
			}
			
			totalPayment = ReportUtil.getPaymentTotal(paymentService.findAllByDate(date));
			totalExpense = ReportUtil.getExpenseTotal(expenseService.findAllByDate(date));
			totalRoundDelivered = ReportUtil.getRoundDeliveredTotal(orderList);
			totalSlimDelivered = ReportUtil.getSlimDeliveredTotal(orderList);

			dte.setDate(date);
			dte.setTotalCash(totalCash);
			dte.setTotalAr(totalAr);
			dte.setTotalPayments(totalPayment);
			dte.setTotalExpenses(totalExpense);
			dte.setTotalDeliveredRound(totalRoundDelivered);
			dte.setTotalDeliveredSlim(totalSlimDelivered);
			dte.setTotalDeliveredContainers(totalRoundDelivered + totalSlimDelivered);
			
			grandTotalCash += totalCash;
			grandTotalAr += totalAr;
			grandTotalPayment += totalPayment;
			grandTotalExpense += totalExpense;
			grandTotalRound += totalRoundDelivered;
			grandTotalSlim += totalSlimDelivered;
			dteList.add(dte);
		}
		
		grandTotalContainers = grandTotalRound + grandTotalSlim;
		
		model.addAttribute("dailyTotals", dteList);
		model.addAttribute("grandTotalCash", grandTotalCash);
		model.addAttribute("grandTotalAr", grandTotalAr);
		model.addAttribute("grandTotalPayment", grandTotalPayment);
		model.addAttribute("grandTotalExpense", grandTotalExpense);
		model.addAttribute("grandTotalRound", grandTotalRound);
		model.addAttribute("grandTotalSlim", grandTotalSlim);
		model.addAttribute("grandTotalContainers", grandTotalContainers);
		
		model.addAttribute("monthYear", monthsStr[Integer.valueOf(m)-1] + " " + y);
    	return "monthly-totals";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOrders(Model model) {
    	logger.info("List all orders.");
    	
    	int pendingCount = 0;
        
    	List<Order> orderList = new ArrayList<>();
    	List<OrderCustomerEntity> orderCustomerEntityList = new ArrayList<>();
        
    	orderList = orderService.findAllOrdersToday(Date.valueOf(LocalDate.now()));
        
        for (Order order : orderList) {
        	
        	if (order.getStatus().equalsIgnoreCase("pending")) {
        		pendingCount++;
        	}
        	
        	OrderCustomerEntity oce = new OrderCustomerEntity();
        	oce.setOrder(order);
        	Customer customer = customerService.view(order.getCustomerId());
            oce.setCustomerName(customer.getName());
            String formattedDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a").format(order.getCreatedAt());
            oce.setFormattedDate(formattedDate);
            orderCustomerEntityList.add(oce);
        }
        model.addAttribute("orders", orderCustomerEntityList);
        model.addAttribute("pendingCount", pendingCount);
        
        return "list-orders";
    }
    
    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public String listPendingOrders(Model model) {
    	logger.info("List all pending orders.");
    	
    	int pendingCount = 0;
        
    	List<Order> orderList = new ArrayList<>();
    	List<OrderCustomerEntity> orderCustomerEntityList = new ArrayList<>();
        
    	orderList = orderService.findAll();
        
        for (Order order : orderList) {
        	
        	if (order.getStatus().equalsIgnoreCase("pending")) {
        		pendingCount++;
        		
        		OrderCustomerEntity oce = new OrderCustomerEntity();
            	oce.setOrder(order);
            	Customer customer = customerService.view(order.getCustomerId());
                oce.setCustomerName(customer.getName());
                String formattedDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a").format(order.getCreatedAt());
                oce.setFormattedDate(formattedDate);
                orderCustomerEntityList.add(oce);
        	}
        }
        model.addAttribute("orders", orderCustomerEntityList);
        model.addAttribute("pendingCount", pendingCount);
        
        return "list-pending-orders";
    }

    @RequestMapping("/{customerId}")
    public List<Order> findAllOrdersByCustomerId(@PathVariable String customerId) {
        List<Order> orders = new ArrayList<>();

        return orders;
    }

    @RequestMapping("/{customerId}/new")
    public Order saveOrderByCustomer(Order order) {
        return order;
    }
    
    @RequestMapping("/cancel")
	public String cancelOrder(Order order, BindingResult result, @RequestParam String orderId,
			final RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
            return "list-orders";
        } else {
        	orderService.cancelOrder(orderId);
        	redirectAttributes.addFlashAttribute("msg", "Order has been cancelled.");
        }
    	
    	return "redirect:/orders/list";
    }
    
    @RequestMapping("/deliver")
	public String deliverOrder(Order order, BindingResult result, @RequestParam String orderId,
			final RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
            return "list-orders";
        } else {
        	
        	order = orderService.findOrderByOrderId(orderId);
        	saveDebt(order.getAmountPaid(), order.getTotalAmount(), order.getCustomerId(), order.getOrderId());
        	
        	// Check first if already saved prior to delivery!
        	boolean saveReturnedContainers = true;
        	
        	for (Container con : containerService.findAllByCustomerId(order.getCustomerId())) {
        		if (con.getOrderId() != null && con.getOrderId().equalsIgnoreCase(order.getOrderId())) {
        			saveReturnedContainers = false;
        			break;
        		}
        	}
        	
        	// TODO: Add fields for slim container buy refill, round container buy refill, slim container buy empty, round container buy empty
    		saveContainerActivity(order.getSlimCount(), order.getRoundCount(),
					Integer.parseInt(order.getSlimReturned()), Integer.parseInt(order.getRoundReturned()),
					order.getCustomerId(), order.getOrderId(), saveReturnedContainers);
        	
        	orderService.setToDelivered(orderId);
        	redirectAttributes.addFlashAttribute("msg", "Order has been set to delivered.");
        }
    	
    	return "redirect:/orders/list";
    }
    
    @RequestMapping("/deliverPending")
	public String deliverPendingOrder(Order order, BindingResult result, @RequestParam String orderId,
			final RedirectAttributes redirectAttributes) {

    	if (result.hasErrors()) {
            return "list-orders";
        } else {
        	
        	order = orderService.findOrderByOrderId(orderId);
        	saveDebt(order.getAmountPaid(), order.getTotalAmount(), order.getCustomerId(), order.getOrderId());
        	
        	// Check first if already saved prior to delivery!
        	boolean saveReturnedContainers = true;
        	
        	for (Container con : containerService.findAllByCustomerId(order.getCustomerId())) {
        		if (con.getOrderId() != null && con.getOrderId().equalsIgnoreCase(order.getOrderId())) {
        			saveReturnedContainers = false;
        			break;
        		}
        	}
        	
        	// TODO: Add fields for slim container buy refill, round container buy refill, slim container buy empty, round container buy empty
    		saveContainerActivity(order.getSlimCount(), order.getRoundCount(),
					Integer.parseInt(order.getSlimReturned()), Integer.parseInt(order.getRoundReturned()),
					order.getCustomerId(), order.getOrderId(), saveReturnedContainers);
        	
        	orderService.setToDelivered(orderId);
        	redirectAttributes.addFlashAttribute("msg", "Order has been set to delivered.");
        }
    	
    	return "redirect:/orders/pending";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveOrderProductEntity(@ModelAttribute("opeForm") @Validated OrderProductEntity orderProductEntity,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Save order.");
        
        Order order = orderProductEntity.getOrder();
        if (order.getRoundReturned().equalsIgnoreCase("")) {
        	order.setRoundReturned("0");
        }
        
        if (order.getSlimReturned().equalsIgnoreCase("")) {
        	order.setSlimReturned("0");
        }
        
        if (result.hasErrors()) {
            //model.addAttribute("customerId", order.getCustomerId());
            //model.addAttribute("newDrNumber", orderService.getNewDrNumber());

            //Customer customer = customerService.view(order.getCustomerId());
            //model.addAttribute("customerName", customer.getName());
            return "ErrorPage";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            //order.setDeliveryReceiptNum(orderService.getNewDrNumber());
            order.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            order.setStatus("Pending");
            
            Order insertedOrder = orderService.insert(order);
            //System.out.println("Order Interval: " + orderProductEntity.getOrderInterval());
            
            
			if (orderProductEntity.getSaveReturned().equalsIgnoreCase("Yes")) {
				saveReturnedContainers(Integer.parseInt(order.getSlimReturned()), Integer.parseInt(order.getRoundReturned()),
						order.getCustomerId(), order.getOrderId());
			}
            
            List<OrderProduct> orderProductEntityList = orderProductEntity.getOpList();
            
            if (orderProductEntityList != null && orderProductEntityList.size() != 0) {
            	for (OrderProduct op : orderProductEntityList) {
                	if (op.getQuantity().equalsIgnoreCase("") || op.getQuantity().equalsIgnoreCase("0")) {
                		// Do not save
                	} else {
                		op.setOrderId(insertedOrder.getOrderId());
                		orderProductService.insert(op);
                	}
                }
            }
            
            redirectAttributes.addFlashAttribute("msg", "Order created successfully!");
            
            // POST/REDIRECT/GET
            return "redirect:/orders/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOrderProductEntity(@ModelAttribute("opeForm") @Validated OrderProductEntity orderProductEntity,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Update order.");
        Order order = orderProductEntity.getOrder();
        
        if (order.getRoundReturned().equalsIgnoreCase("")) {
        	order.setRoundReturned("0");
        }
        
        if (order.getSlimReturned().equalsIgnoreCase("")) {
        	order.setSlimReturned("0");
        }
        
        if (result.hasErrors()) {
            //model.addAttribute("customerId", order.getCustomerId());
            //model.addAttribute("drNumber", orderService.getNewDrNumber());
            //Customer customer = customerService.view(order.getCustomerId());
            //model.addAttribute("customerName", customer.getName());
            return "ErrorPage";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            order.setDeliveryReceiptNum(order.getDeliveryReceiptNum());
            order.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            order.setStatus("Pending");
            
            Order updatedOrder = orderService.update(order);
            
            for (OrderProduct op : orderProductEntity.getOpList()) {
            	if (op.getQuantity().equalsIgnoreCase("")) {
            		// Do not save
            	} else {
            		op.setOrderId(updatedOrder.getOrderId());
            		System.out.println("Product ID: " + op.getProductId());
            		System.out.println("OP ID: " + op.getOrderProductId());
            		System.out.println("Quantity: " + op.getQuantity());
            		if (op.getOrderProductId() == null || op.getOrderProductId().equalsIgnoreCase("")) {
            			System.out.println("Saving new OP");
            			//op.setProductId(productId);
            			if (!op.getQuantity().equalsIgnoreCase("0")) {
            				orderProductService.insert(op);
            			}
            			
            		} else {
            			System.out.println("Updating/Delete existing OP");
            			
            			if (op.getQuantity().equalsIgnoreCase("0")) {
            				orderProductService.remove(op);
            			} else {
            				orderProductService.update(op);
            			}
            		}
            	}
            }
            
            updateContainerActivity(order.getSlimCount(), order.getRoundCount(),
					Integer.parseInt(order.getSlimReturned()), Integer.parseInt(order.getRoundReturned()),
					order.getCustomerId(), order.getOrderId());
            
            redirectAttributes.addFlashAttribute("msg", "Order edited successfully!");
            
            // POST/REDIRECT/GET
            return "redirect:/orders/list";
            //return "redirect:/customers/" + customer.getCustomerId();
        }
    }
    
    /**
     * Saves the debt of the customer if amount paid is less than total amount for a given order.
     * 
     * @param amountPaid
     * @param totalAmount
     * @param customerId
     */
    public void saveDebt (double amountPaid, double totalAmount, String customerId, String orderId) {
    	
    	double deficit = totalAmount - amountPaid;
    	
    	if (deficit != 0.0) {
    		Debt debtEntry = new Debt();
    		debtEntry.setCustomerId(customerId);
    		debtEntry.setAmount(deficit);
    		
    		if (amountPaid == 0) {
    			debtEntry.setRemarks("Total amount is: Php" + totalAmount + " but the customer hasn't paid yet");
    		} else {
    			debtEntry.setRemarks("Total amount is: Php" + totalAmount + " but the amount paid is only Php" + amountPaid);
    		}
    		
    		
    		debtEntry.setCreatedAt(Date.valueOf(LocalDate.now()));
    		debtEntry.setOrderId(orderId);
    		debtService.insert(debtEntry);
    	}
    }
    
    /**
     * Saves the returned and borrowed containers into the Container table.
     * 
     * @param slimCount
     * @param roundCount
     * @param slimReturned
     * @param roundReturned
     * @param customerId
     */
	public void saveContainerActivity(int slimCount, int roundCount, int slimReturned, int roundReturned,
			String customerId, String orderId, boolean saveReturnedContainers) {
    	
    	Container containerActivity = new Container();
        containerActivity.setCustomerId(customerId);
        containerActivity.setOrderId(orderId);
        containerActivity.setRoundCount(roundCount);
        containerActivity.setSlimCount(slimCount);
        containerActivity.setStatus("B");
        containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        containerService.insert(containerActivity);
        
        if (saveReturnedContainers && (slimReturned != 0 || roundReturned != 0)) {
        	containerActivity = new Container();
            containerActivity.setCustomerId(customerId);
            containerActivity.setOrderId(orderId);
            containerActivity.setRoundCount(roundReturned);
            containerActivity.setSlimCount(slimReturned);
            containerActivity.setStatus("RO"); // Return with Order
            containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            containerService.insert(containerActivity);
        }
    }
	
	public void updateContainerActivity(int slimCount, int roundCount, int slimReturned, int roundReturned,
			String customerId, String orderId) {
    	
    	Container containerActivity = new Container();
        containerActivity.setCustomerId(customerId);
        containerActivity.setOrderId(orderId);
        containerActivity.setRoundCount(roundCount);
        containerActivity.setSlimCount(slimCount);
        containerActivity.setStatus("B");
        containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        containerService.update(containerActivity);
        
        if ((slimReturned != 0 || roundReturned != 0)) {
        	containerActivity = new Container();
            containerActivity.setCustomerId(customerId);
            containerActivity.setOrderId(orderId);
            containerActivity.setRoundCount(roundReturned);
            containerActivity.setSlimCount(slimReturned);
            containerActivity.setStatus("RO"); // Return with Order
            containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            containerService.update(containerActivity);
        }
    }
    
	/**
	 * 
	 * @param slimReturned
	 * @param roundReturned
	 * @param customerId
	 * @param orderId
	 */
    public void saveReturnedContainers (int slimReturned, int roundReturned, String customerId, String orderId) {
    	
        if (slimReturned != 0 || roundReturned != 0) {
        	Container containerActivity = new Container();
            containerActivity.setCustomerId(customerId);
            containerActivity.setOrderId(orderId);
            containerActivity.setRoundCount(roundReturned);
            containerActivity.setSlimCount(slimReturned);
            containerActivity.setStatus("RO"); // Return with Order
            containerActivity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            containerService.insert(containerActivity);
        }
    }
}
