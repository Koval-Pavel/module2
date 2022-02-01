package org.module.springmvc.controller;

import org.module.springmvc.dao.DAOUsers;
import org.module.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    public static User user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }



    @RequestMapping(value = "/inputUser", method = RequestMethod.GET)
    public ModelAndView inputUser() {
        User user = new User();
        return new ModelAndView("user", "command", user);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public ModelAndView findUuser(@ModelAttribute("mvc-dispatcher") User user) {
        User foundUser = DAOUsers.getInstance().findUser(user.getName());
        if (foundUser.getName() == null) {
            DAOUsers.getInstance().inputUser(user);
            foundUser = user;
        }
        user.setId(DAOUsers.getInstance().findUser(user.getName()).getId());
        UserController.user = user;

        ModelAndView model = new ModelAndView("redirect:/questionMenu");
        String operationName = null;
        model.addObject("user", user);
        return model;
    }

}
