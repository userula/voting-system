package com.example.finalproj.controller;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AccountDetailsService accountDetailsService;
    private final QuestionService questionService;
    private final RoleService roleService;
    private final VoteService voteService;

    @Autowired
    public UserController(UserService userService, AccountDetailsService accountDetailsService, QuestionService questionService, RoleService roleService, VoteService voteService) {
        this.userService = userService;
        this.roleService = roleService;
        this.questionService = questionService;
        this.accountDetailsService = accountDetailsService;
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public String toUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("currentUser", accountDetailsService.getAccount());
        model.addAttribute("votes", voteService.getVotesByUserId(id));
        return "profile";
    }
    @GetMapping("/{id}/edit")
    public String updateUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("user", new Account());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("currentUser", accountDetailsService.getAccount());
        return "updateUser";
    }
    @PostMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("user") Account user, @PathVariable(value = "id") Long id){
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String toAdmin(Model model){
        model.addAttribute("question", questionService.getAllQuestions());
        model.addAttribute("user", accountDetailsService.getAccount());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }
    @GetMapping("/addUser")
    public String toAddUser(Model model){
        model.addAttribute("user", new Account());
        model.addAttribute("roles", roleService.getRoles());
        return "addUser";
    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") Account account) {
        account.setRole(roleService.getRole(account.getRole().getRoleId()));
        userService.registerUser(account);
        return "redirect:/";
    }
    @GetMapping("/addQuestion")
    public String toAddQuestion(Model model){
        Question q = new Question();
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            answers.add(new Answer());
        }
        q.setAnswerOptions(answers);
        model.addAttribute("question", q);
        return "addQuestion";
    }
    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute("question") Question question) {
        questionService.registerQuestion(question);
        questionService.registerAnswers(question);
        return "redirect:/";
    }
    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id")Long id, Model model){
        questionService.deleteQuestion(id);
        return "redirect:/";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id")Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }
    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answers", questionService.getAllAnswers());
        return "question";
    }
    @GetMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(value = "id")Long id, Model model){
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            answers.add(new Answer());
        }
        questionService.getQuestion(id).setAnswerOptions(answers);
        model.addAttribute("question", questionService.getQuestion(id));
        return "updateQuestion";
    }
    @PostMapping("/updateQuestion/{id}")
    public String updateQ(@PathVariable(value = "id")Long id, @ModelAttribute("question") Question question){
        questionService.updateQuestion(id, question);
        questionService.updateAnswers(id, question);
        return "redirect:/";
    }
}
