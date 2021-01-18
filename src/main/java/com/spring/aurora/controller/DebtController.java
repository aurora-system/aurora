package com.spring.aurora.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.aurora.entity.DebtCustomerEntity;
import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.DebtService;
import com.spring.aurora.service.PaymentService;

@Controller
@RequestMapping("/debts")
public class DebtController {

    private static final Logger logger = LoggerFactory.getLogger(DebtController.class);

    @Autowired private DebtService debtService;
    @Autowired private PaymentService paymentService;
    @Autowired private CustomerService customerService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDebt(@RequestParam long cid, Model model) {
        Debt debt = new Debt();
        debt.setCreatedAt(Date.valueOf(LocalDate.now()));
        debt.setCustomerId(cid);
        Customer customer = this.customerService.view(cid);
        model.addAttribute("debt", debt);
        model.addAttribute("customer", customer);
        return "new-debt";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDebt(@ModelAttribute("debt") Debt debt,
            BindingResult result, Model model,
            final RedirectAttributes redirect){
        if (result.hasErrors()) {
            return "new-debt";
        } else {
            redirect.addFlashAttribute("msg", "New debt logged successfully!");
        }
        this.debtService.insert(debt);
        return "redirect:/debts/list?cid=" +debt.getCustomerId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listDebts(@RequestParam long cid, Model model) {
        List<Debt> debts = this.debtService.findAllByCustomerId(cid);
        Customer customer = this.customerService.view(cid);
        model.addAttribute("debts", debts);
        model.addAttribute("debtsTotal", this.getDebtsTotal(cid));
        model.addAttribute("customer", customer);
        return "list-debts";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAllDebts(Model model, @RequestParam(value="mode", defaultValue="normal", required=false) String mode) {
        List<Customer> customers = this.customerService.findAll();
        Date dateToday = Date.valueOf(LocalDate.now());
        Map<Long, Object> debtsMap = customers.stream()
                .filter(customer -> this.getDebtsTotal(customer.getCustomerId()) > 0.0)
                .collect(Collectors.toMap(Customer::getCustomerId,
                        customer -> new DebtCustomerEntity(
                                customer.getName(),
                                this.getDebtsTotal(customer.getCustomerId()),
                                this.paymentService.getPaymentsTotalByCustomerId(customer.getCustomerId()))
                        )
                        );
        model.addAttribute("debtsMap", debtsMap);
        Double arTotal = this.getTotalArsAsOfToday();
        model.addAttribute("arTotal", arTotal);
        model.addAttribute("dateToday", dateToday);

        if (mode.equalsIgnoreCase("preview")) {
            return "list-debts-all-print-preview";
        } else {
            return "list-debts-all";
        }

    }

    private Double getTotalArsAsOfToday() {
        Double totalARs = this.debtService.findTotalARs();
        Double totalPmts = this.paymentService.getTotalPayments();
        return totalARs - totalPmts;
    }

    private double getDebtsTotal(long customerId) {
        double debtsSubTotal = this.debtService.findDebtsTotalByCustomerId(customerId);
        double paymentsSubTotal = this.paymentService.getPaymentsTotalByCustomerId(customerId);
        return debtsSubTotal - paymentsSubTotal;
    }
}
