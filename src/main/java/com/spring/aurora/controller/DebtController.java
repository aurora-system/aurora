package com.spring.aurora.controller;

import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Debt;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.DebtService;
import com.spring.aurora.service.PaymentService;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/debts")
public class DebtController {

    private static final Logger logger = LoggerFactory.getLogger(DebtController.class);

    @Autowired private DebtService debtService;
    @Autowired private PaymentService paymentService;
    @Autowired private CustomerService customerService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDebt(@RequestParam String cid, Model model) {
        Debt debt = new Debt();
        debt.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        debt.setCustomerId(cid);
        Customer customer = customerService.view(cid);
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
        debtService.insert(debt);
        return "redirect:/debts/list?cid=" +debt.getCustomerId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listDebts(@RequestParam String cid, Model model) {
        List<Debt> debts = debtService.findAllByCustomerId(cid);
        Customer customer = customerService.view(cid);
        model.addAttribute("debts", debts);
        model.addAttribute("debtsTotal", getDebtsTotal(cid));
        model.addAttribute("customer", customer);
        return "list-debts";
    }

    private double getDebtsTotal(String customerId) {
        double debtsSubTotal = debtService.findDebtsTotalByCustomerId(customerId);
        double paymentsSubTotal = paymentService.getPaymentsTotalByCustomerId(customerId);
        return debtsSubTotal - paymentsSubTotal;
    }
}
