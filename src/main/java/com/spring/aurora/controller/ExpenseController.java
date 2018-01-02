package com.spring.aurora.controller;

import com.spring.aurora.model.Expense;
import com.spring.aurora.service.ExpenseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

    }

    @RequestMapping(value = "/expenses", method = RequestMethod.GET)
    public String expenses(Model model){
        logger.debug("/expenses");
        return "redirect:/expenses/new";
    }

    @RequestMapping(value = "/expenses/new", method = RequestMethod.GET)
    public String newExpenseForm(Model model) {
        logger.debug("show new expense form.");
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        return "new-expense";
    }

    @RequestMapping(value = "/expenses/save", method = RequestMethod.POST)
    public String saveExpense(@ModelAttribute("expense") Expense expense,
                              BindingResult result, Model model,
                              final RedirectAttributes redirect) {
        logger.debug("saving expense");
        if (result.hasErrors()) {
            return "new-expense";
        } else {
            redirect.addFlashAttribute("msg", "User added successfully!");
        }
        expenseService.save(expense);
        return "redirect:/expenses/list";
    }

    @RequestMapping(value = "/expenses/list", method = RequestMethod.GET)
    public String listExpensesToday(Model model) {
        logger.debug("list today's expenses");
        List<Expense> expenses = expenseService.findAllByDate(Date.valueOf(LocalDate.of(2018,1,31)));
        model.addAttribute("expenses", expenses);
        return "list-expenses";
    }
}
