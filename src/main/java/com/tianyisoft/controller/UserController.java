package com.tianyisoft.controller;

import com.tianyisoft.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author tianyi
 */
@Controller
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(User user, Model model, HttpSession session) {
        String name = user.getName();
        String pass = user.getPass();
        if ("xiaoxue".equals(name) && "123456".equals(pass)) {
            session.setAttribute("USER_SESSION", user);
            return "redirect:/";
        }
        model.addAttribute("msg", "用户名或密码错误！");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
