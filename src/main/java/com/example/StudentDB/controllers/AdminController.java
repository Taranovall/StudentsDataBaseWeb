package com.example.StudentDB.controllers;

import com.example.StudentDB.entity.Role;
import com.example.StudentDB.entity.User;
import com.example.StudentDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;



    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable(value = "id") Long id,Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("allUsers", userService.allUsers());
        Set<Role> set = user.getRoles();
        for (Role i : set) {
            if (i.getName().equals("ROLE_ADMIN")) {
                model.addAttribute("removeAdmin",true);
                return "admin";
            }
        }
        userService.deleteUser(id);
        return "redirect:/admin";
    }



}
