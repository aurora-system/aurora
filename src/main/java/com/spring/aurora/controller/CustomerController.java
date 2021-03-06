package com.spring.aurora.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.aurora.entity.CustomerDueDateEntity;
import com.spring.aurora.entity.CustomerPriceEntity;
import com.spring.aurora.entity.ProductPriceEntity;
import com.spring.aurora.model.Container;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.CustomerPrice;
import com.spring.aurora.model.Order;
import com.spring.aurora.model.Payment;
import com.spring.aurora.service.ContainerService;
import com.spring.aurora.service.CustomerPriceService;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.DebtService;
import com.spring.aurora.service.ExpenseService;
import com.spring.aurora.service.OrderService;
import com.spring.aurora.service.PaymentService;
import com.spring.aurora.service.ProductService;
import com.spring.aurora.util.CustomerFormValidator;
import com.spring.aurora.util.OrderFormValidator;

@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private static final String monthsStr[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private DebtService debtService;

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CustomerPriceService customerPriceService;

    @Autowired
    private ProductService productService;

    @Autowired
    CustomerFormValidator customerFormValidator;
    @Autowired
    OrderFormValidator orderFormValidator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(customerFormValidator);
        //change back to setValidator above when the View and New Order button is changed to <a> from <form>
        //binder.addValidators(customerFormValidator, orderFormValidator);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCustomer(Model model) {
        logger.info("Display customer search result.");
        //model.addAttribute("customers", customerService.findAll());
        return "customer-search-result";
    }
    
    /**
     * Fetches customers along with some of their details that have at least 
     * one successful transaction for the selected month.
     * @param model - the model
     * @param mode - normal view or print preview
     * @param datePicked - the selected month
     */
    @RequestMapping(value="/listactive", method = RequestMethod.GET)
	public String listActiveCustomersForTheMonth(Model model,
			@RequestParam(value = "mode", defaultValue = "normal", required = false) String mode,
			@RequestParam(value = "d", required = false) String datePicked) {
		logger.info("List active customers per month.");
    	
		List<CustomerPriceEntity> cpeList = new ArrayList<>();
		
		String m = "";
		String y = "";
		
		List<Customer> customerList = new ArrayList<>();
		List<String> customerIds = new ArrayList<>();
		
		if (datePicked == null || datePicked.equalsIgnoreCase("today") || datePicked.equalsIgnoreCase("")) {
			LocalDateTime now = LocalDateTime.now();
			
			Integer year = now.getYear();
			Integer month = now.getMonthValue();
			
			m = month.toString();
			y = year.toString();
		} else {
			String[] splitDate = datePicked.split("-");
			m = splitDate[0];
			if (splitDate.length > 1) {
				y = splitDate[1];
			}
		}
		
		List<Order> orderList = orderService.findAllOrdersPerMonth(m, y);
		
		for (Order o : orderList) {
			
			if (!customerIds.contains(o.getCustomerId())) {
				customerIds.add(o.getCustomerId());
			}
		}
		
		customerList = customerService.find(customerIds);
		
		for (Customer c : customerList) {
			
			Double priceRound = 0.0;
        	Double priceSlim = 0.0;
        	Double refillPrice = 40.0;
        	List<CustomerPrice> customerPriceList = customerPriceService.findAllByCustomerId(c.getCustomerId());
        	
        	Timestamp mostRecentOd = orderService.getMostRecentOrderDate(c.getCustomerId());
        	
        	
        	for (CustomerPrice cp : customerPriceList) {
        		if (cp.getProductId().equalsIgnoreCase("1")) {
        			priceRound = cp.getSellingPrice();
        		}
        		
        		if (cp.getProductId().equalsIgnoreCase("2")) {
        			priceSlim = cp.getSellingPrice();
        		}
        	}
        	
        	if (priceRound <= 0) {
        		if (priceSlim > 0) {
        			refillPrice = priceSlim;
        		}
        	} else {
        		refillPrice = priceRound;
        	}
        	
        	CustomerPriceEntity cpe = new CustomerPriceEntity(c, refillPrice);
        	cpe.setMostRecentOrderDate(mostRecentOd);
        	cpeList.add(cpe);
		}
		
		System.out.println("CPELIST size: " + cpeList.size());
		model.addAttribute("monthYear", monthsStr[Integer.valueOf(m)-1] + " " + y);
		model.addAttribute("dateParam", datePicked);
		model.addAttribute("cpeList", cpeList);
		
    	if (mode.equalsIgnoreCase("preview")) {
    		System.out.println("preview");
    		return "list-customers-monthly-active-preview";
    	} else {
    		return "list-customers-monthly-active";
    	}
    }
    
    /**
     * Fetches all the customers and their corresponding details.
     * @param model - the model
     * @param mode - normal view or print preview
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCustomers(Model model, @RequestParam(value="mode", defaultValue="normal", required=false) String mode) {
        logger.info("List all customers.");
        
        List<Customer> customerList = new ArrayList<>();
        customerList = customerService.findAll();
        
        List<CustomerPriceEntity> cpeList = new ArrayList<>();
        
        for (Customer c : customerList) {
        	
        	Double priceRound = 0.0;
        	Double priceSlim = 0.0;
        	Double refillPrice = 40.0;
        	List<CustomerPrice> customerPriceList = customerPriceService.findAllByCustomerId(c.getCustomerId());
        	
        	for (CustomerPrice cp : customerPriceList) {
        		if (cp.getProductId().equalsIgnoreCase("1")) {
        			priceRound = cp.getSellingPrice();
        		}
        		
        		if (cp.getProductId().equalsIgnoreCase("2")) {
        			priceSlim = cp.getSellingPrice();
        		}
        	}
        	
        	if (priceRound <= 0) {
        		if (priceSlim > 0) {
        			refillPrice = priceSlim;
        		}
        	} else {
        		refillPrice = priceRound;
        	}
        	
        	
        	CustomerPriceEntity cpe = new CustomerPriceEntity(c, refillPrice);
        	cpeList.add(cpe);
        }
        
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("customerPrices", cpeList);
        //model.addAttribute("orderForm", new Order());
        if (mode.equalsIgnoreCase("preview")) {
        	return "list-customers-print-preview";
        } else {
        	return "list-customers";
        }
        
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCustomer(Model model, @RequestParam String customerId) {
        logger.info("Display customer information.");
        
        List<Order> orderList = orderService.findAllByCustomerId(customerId);
        
        Customer customer = customerService.view(customerId);
        
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orderList);
        
        Timestamp mostRecentOd = orderService.getMostRecentOrderDate(customerId);
        
        if (mostRecentOd != null) {
        	LocalDate dueDate = mostRecentOd.toLocalDateTime().toLocalDate();
            dueDate = dueDate.plusDays(customer.getOrderInterval());
            Date dueDateConverted = Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String formattedDueDate = new SimpleDateFormat("MMM dd YYYY").format(dueDateConverted);
            long daysBeforeDueDate = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
            String dueDateText = "";
            
            if (daysBeforeDueDate > 0) {
            	dueDateText = "Due in " + daysBeforeDueDate + " days";
            } else if (daysBeforeDueDate < 0) {
            	dueDateText = -(daysBeforeDueDate) + " days overdue";
            } else {
            	dueDateText = "Due today";
            }
            
            model.addAttribute("dueDate", formattedDueDate);
            model.addAttribute("dueDateText", dueDateText);
        } else {
        	model.addAttribute("dueDate", "N/A");
        	model.addAttribute("dueDateText", "No orders yet");
        }
        
        if (mostRecentOd != null) {
        	Date date = new Date();
            date.setTime(mostRecentOd.getTime());
            String formattedDate = new SimpleDateFormat("MMM dd YYYY @ hh:mm a").format(date);
            model.addAttribute("mostRecentOrderDate", formattedDate);
        } else {
        	model.addAttribute("mostRecentOrderDate", "No orders yet.");
        }
        
        List<Container> containerList = containerService.findAllByCustomerId(customerId);
        List<Container> returnedContainers = new ArrayList<>();
        List<Order> orders = orderService.findAllByCustomerId(customerId);
        
        for (Order o : orders) {
    		int roundReturned = Integer.parseInt(o.getRoundReturned());
    		int slimReturned = Integer.parseInt(o.getSlimReturned());
    		
    		if (roundReturned == 0 && slimReturned == 0) {
    			// Do not save
    		} else {
    			Container c = new Container();
        		c.setRoundCount(roundReturned);
        		c.setSlimCount(slimReturned);
        		c.setStatus("RO");
        		c.setOrderId("Yes");
        		c.setCreatedAt(o.getCreatedAt());
        		returnedContainers.add(c);
    		}
        }
        
        for (Container c : containerList) {
        	if (c.getStatus().equalsIgnoreCase("R") && c.getOrderId() != null) {
        		c.setOrderId("No");
        		returnedContainers.add(c);
        	}
        }
        
//        for (Container c : containerList) {
//        	
//        	if (c.getStatus().equalsIgnoreCase("B")) {
//        		totalRoundBorrowed = totalRoundBorrowed +  c.getRoundCount();
//        		totalSlimBorrowed = totalSlimBorrowed + c.getSlimCount();
//        	} else {
//        		totalRoundBorrowed = totalRoundBorrowed - c.getRoundCount();
//        		totalSlimBorrowed = totalSlimBorrowed - c.getSlimCount();
//        		returnedContainers.add(c);
//        	}
//        }
        
        model.addAttribute("totalBorrowedRound", customer.getTotalRound());
    	model.addAttribute("totalBorrowedSlim", customer.getTotalSlim());
    	model.addAttribute("containerHistory", returnedContainers);
        
    	double debtsSubTotal = debtService.findDebtsTotalByCustomerId(customerId);
    	logger.debug("debsTotal: "+ debtsSubTotal);
        double paymentsSubTotal = paymentService.getPaymentsTotalByCustomerId(customerId);
        logger.debug("paymentsTotal: " + paymentsSubTotal);
        double totalDebt = debtsSubTotal - paymentsSubTotal;
        logger.debug("total ARs: " + totalDebt);
        model.addAttribute("totalDebt", totalDebt);

        List<CustomerPrice> prices = customerPriceService.findAllByCustomerId(customerId);
        List<ProductPriceEntity> productPrices = prices.stream().map((CustomerPrice p) -> {
            return new ProductPriceEntity(
                    p.getPriceId(),
                    customerService.view(p.getCustomerId()),
                    productService.findByProductId(p.getProductId()),
                    p.getSellingPrice()
            );
        }).collect(Collectors.toList());
        model.addAttribute("prices", productPrices);
        
        List<Payment> payments = paymentService.findAllByCustomerId(customerId);
        model.addAttribute("payments", payments);
    	
        return "view-customer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        logger.debug("New Customer form.");
        Customer customer = new Customer();
        customer.setType("Business");
        model.addAttribute("customerForm", customer);
        model.addAttribute("types",new String[]{"Business","Residential"});
        return "new-customer";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCustomer(Model model, String customerId) {
        logger.debug("Edit Customer form.");
        
        Customer customer = customerService.view(customerId);
        model.addAttribute("customerForm", customer);
        model.addAttribute("types",new String[]{"Business","Residential"});
        return "edit-customer";
    }
    
    @RequestMapping(value = "/containerBalances", method = RequestMethod.GET)
    public String updateContainerTotals(Model model, String customerId) {
        logger.debug("Update Customer container totals.");
        
        Customer customer = customerService.view(customerId);
        model.addAttribute("customerForm", customer);
        return "set-container-totals";
    }
    
    // TODO: Transfer to OrderController
    /*@RequestMapping(value = "/neworder", method = RequestMethod.GET)
    public String newOrder(@ModelAttribute(value="customerId") String customerId, Model model) {
        logger.debug("New Order form for customer: " + customerId);
        Order order = new Order();
        order.setCustomerId(customerId);
        model.addAttribute("orderForm", order);
        model.addAttribute("customerId", customerId);
        model.addAttribute("newDrNumber", orderService.getNewDrNumber());
        
        Customer customer = customerService.view(customerId);
        model.addAttribute("customerName", customer.getName());
        return "new-order";
    }
*/
    
    @RequestMapping(value = "/duedates", method = RequestMethod.GET)
    public String viewDueDates(Model model) {
        
    	logger.info("List all due dates.");
    	List<CustomerDueDateEntity> cddList = new ArrayList<>();
    	List<Customer> customerList = customerService.findAll();
    	
    	for (Customer c : customerList) {
    		
    		Timestamp mostRecentOrderDate = orderService.getMostRecentOrderDate(c.getCustomerId());
    		LocalDate lastOrderDate = LocalDate.now();
    		
    		if (mostRecentOrderDate != null) {
    			lastOrderDate = mostRecentOrderDate.toLocalDateTime().toLocalDate();
    			LocalDate dueDate = lastOrderDate.plusDays(c.getOrderInterval());
        		long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
        		
        		CustomerDueDateEntity cdd = new CustomerDueDateEntity(c, lastOrderDate, dueDate, daysRemaining);
        		cddList.add(cdd);
    		} else {
    			CustomerDueDateEntity cdd = new CustomerDueDateEntity(c, null, LocalDate.now(), 0);
        		cddList.add(cdd);
    		}
    		
    	}
    	
        model.addAttribute("dueDates", cddList);
        
        return "list-duedates";
    }
    
    @RequestMapping(value = "/dailyduedates", method = RequestMethod.GET)
	public String viewDailyDueDates(Model model,
			@RequestParam(value = "d", defaultValue = "today", required = false) String d) {

		logger.info("Show daily due dates.");
		
		List<CustomerDueDateEntity> cddList = new ArrayList<>();
    	List<Customer> customerList = customerService.findAll();
		
		java.sql.Date date = ("today".equalsIgnoreCase(d)) ? java.sql.Date.valueOf(LocalDate.now())
				: java.sql.Date.valueOf(LocalDate.parse(d));
		
		String datePicked = new SimpleDateFormat("MMM dd YYYY").format(date);
		model.addAttribute("datePicked", datePicked);
		
		for (Customer c : customerList) {
			Timestamp mostRecentOrderDate = orderService.getMostRecentOrderDate(c.getCustomerId());
    		LocalDate lastOrderDate = LocalDate.now();
    		
    		if (mostRecentOrderDate != null) {
    			lastOrderDate = mostRecentOrderDate.toLocalDateTime().toLocalDate();
    			LocalDate dueDate = lastOrderDate.plusDays(c.getOrderInterval());
    			if (date.equals(java.sql.Date.valueOf( dueDate ))) {
    				CustomerDueDateEntity cdd = new CustomerDueDateEntity(c, lastOrderDate);
            		cddList.add(cdd);
    			}
    		}
		}

		model.addAttribute("dueDates", cddList);
		
		return "list-daily-duedates";
	}
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerForm") @Validated Customer customer,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Save customer.");
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            model.addAttribute("types", new String[] {"Business","Residential"});
            return "new-customer";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            
            customerService.insert(customer);
            redirectAttributes.addFlashAttribute("msg", "Customer created successfully!");

            // POST/REDIRECT/GET
            return "redirect:/customers/view?customerId="  + customer.getCustomerId();
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCustomer(@ModelAttribute("customerForm") @Validated Customer customer,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        logger.debug("Update customer.");
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            model.addAttribute("types", new String[] {"Business","Residential"});
            return "edit-customer";
        } else {
            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");

            customerService.update(customer);
			redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");

            // POST/REDIRECT/GET
            return "redirect:/customers/view?customerId=" + customer.getCustomerId();
        }
    }
    
    @RequestMapping(value = "/updateContainerBalances", method = RequestMethod.POST)
    public String updateContainerBalances(@ModelAttribute("customerForm") @Validated Customer customer,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
    	
    	 logger.debug("Update customer container totals.");
         if (result.hasErrors()) {
             model.addAttribute("types", new String[] {"Business","Residential"});
             return "set-container-totals";
         } else {
             // Add message to flash scope
             redirectAttributes.addFlashAttribute("css", "success");

             customerService.update(customer);
 			redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");

             // POST/REDIRECT/GET
             return "redirect:/customers/view?customerId=" + customer.getCustomerId();
         }
    }
}
