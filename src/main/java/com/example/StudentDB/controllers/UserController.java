package com.example.StudentDB.controllers;

import com.example.StudentDB.entity.User;
import com.example.StudentDB.repository.UserRepository;
import com.example.StudentDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model){

        if (userForm.getUsername().length() < 6 || userForm.getUsername().length() > 32) {
            model.addAttribute("username",true);
            return "registration";
        }
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            model.addAttribute("usernameDuplicate",true);
            return "registration";
        }

        if (userForm.getPassword().length() < 8 || userForm.getPassword().length() > 32) {
            model.addAttribute("password",true);
            return "registration";
        }

        if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
            model.addAttribute("passwordConfirm",true);
            return "registration";
        }

        if (bindingResult.hasErrors()){
            return "registration";
        }
         userService.saveUser(userForm);



        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


}
