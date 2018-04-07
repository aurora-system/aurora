package com.spring.aurora.controller;

import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Payment;
import com.spring.aurora.service.CustomerService;
import com.spring.aurora.service.PaymentService;
import com.spring.aurora.util.PaymentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    PaymentFormValidator paymentFormValidator;
    /*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(paymentFormValidator);
    }*/

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPayment(@RequestParam String cid, Model model) {
        Payment payment = new Payment();
        payment.setCreatedAt(Date.valueOf(LocalDate.now()));
        payment.setCustomerId(cid);
        payment.setPaymentType("CASH");
        payment.setWithholdingTax(new Double("0.00"));
        Customer customer = customerService.view(cid);
        model.addAttribute("payment", payment);
        model.addAttribute("customer", customer);
        return "new-payment";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePayment(@ModelAttribute("payment") Payment payment,
                              BindingResult result, Model model,
                              final RedirectAttributes redirect){
        paymentFormValidator.validate(payment,result);
        if (result.hasErrors()) {
            Customer customer = customerService.view(payment.getCustomerId());
            model.addAttribute("customer", customer);
            return "new-payment";
        } else {
            redirect.addFlashAttribute("msg", "Payment logged successfully!");
        }
        paymentService.insert(payment);
        return "redirect:/payments/list?cid=" + payment.getCustomerId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPayments(@RequestParam String cid, Model model){
        List<Payment> payments = paymentService.findAllByCustomerId(cid);
        Customer customer = customerService.view(cid);
        model.addAttribute("payments", payments);
        model.addAttribute("paymentsTotal", paymentService.getPaymentsTotalByCustomerId(cid));
        model.addAttribute("customer", customer);
        return "list-payments";
    }
}
