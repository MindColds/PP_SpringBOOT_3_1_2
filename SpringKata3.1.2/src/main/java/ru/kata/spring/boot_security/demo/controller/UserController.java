package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.getUserByName(userDetails.getUsername()));
        return "user";
    }

//    @GetMapping("/")
//    public String showAllUsers(Model model) {
//        model.addAttribute("user", userService.getAllUsers());
//        return "userList";
//    }
//
//    @GetMapping("/create")
//    public String createEmptyUserForm(@ModelAttribute User user) {
//        return "/createUser";
//    }
//
//    @PostMapping("/createUser")
//    public String createUserWithDetails(@ModelAttribute User user) {
//        userService.createUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/update/{id}")
//    public String updateUserById(@PathVariable int id, Model model) {
//        model.addAttribute("user", userService.getUser(id));
//        return "/updateUser";
//    }
//
//    @PostMapping("/userUpdate")
//    public String updateUser(@ModelAttribute User user) {
//        userService.updateUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }

}
