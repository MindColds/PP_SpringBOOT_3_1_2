package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserRoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public AdminController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping()
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.getUserByName(userDetails.getUsername()));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(value = "id", required = false) int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", userRoleService.getAllRoles());
        return "updateUser";
    }

    @PatchMapping("/{id}/update")
    public String updateUser(@PathVariable(value = "id", required = false) int id, @ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole", required = false) String nameRole) {
        if (nameRole != null) {
            user.setRoles(userRoleService.getRoleByName(nameRole));
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable(value = "id", required = false) int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleName", userRoleService.getAllRoles());
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole", required = false) String nameRole,
                             @RequestParam(value = "username", required = false) String username) {
        user.setUsername(username);
        user.setRoles(userRoleService.getRoleByName(nameRole));
        userService.createUser(user);
        return "redirect:/admin";
    }
}
