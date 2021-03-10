package com.example.finalproj.controller;

import com.example.finalproj.repository.RoleRepository;
import com.example.finalproj.repository.dto.*;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.service.RoleService;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private RoleService roleService;
    private AccountDetailsService accountDetailsService;
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public HomeController(RoleService roleService, AccountDetailsService accountDetailsService, UserService userService, QuestionService questionService) {
        this.roleService = roleService;
        this.questionService = questionService;
        this.accountDetailsService = accountDetailsService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new Account());
        return "signup";
    }

    @PostMapping("/signup")
    public String reg(@ModelAttribute("user") Account account) {
        account.setRole(roleService.getRole(Long.valueOf(2)));
        userService.registerUser(account);
        return "redirect:/login?success";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("user", accountDetailsService.getAccount());
        model.addAttribute("question", questionService.getAllNonAnsweredQuestions(accountDetailsService.getAccount()));
        model.addAttribute("answers", questionService.getAllAnswers());
        model.addAttribute("question2", questionService.getAllAnsweredQuestions(accountDetailsService.getAccount()));
        model.addAttribute("answers2", questionService.getAllAnsweredAnswers(accountDetailsService.getAccount()));
        model.addAttribute("chosenAns", new Answer());
        return "home";
    }

    @PostMapping("/home")
    public String answer(@ModelAttribute("chosenAns") Answer ans){
        questionService.setVote(ans.getAnswerId(),accountDetailsService.getAccount());
        return "redirect:/home";
    }

}