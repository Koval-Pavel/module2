package org.module.springmvc.controller;

import org.module.springmvc.dao.DAOQuestions;
import org.module.springmvc.dao.DAOUsers;
import org.module.springmvc.model.Question;
import org.module.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    public static List<Question> questionList = new ArrayList<Question>();
    public static List<Question> questionList1 = new ArrayList<Question>();
    public int counter = 0;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/questionMenu", method = RequestMethod.GET)
    public ModelAndView questionMenu() {
        questionList = DAOQuestions.getInstance().findQuestions();
        String question = questionList.get(counter).getQuestion();
        User user = UserController.user;
        ModelAndView model = new ModelAndView("question");

        model.addObject("question1", question);
        model.addObject("command", new Question());
        return model;
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public ModelAndView result(@ModelAttribute("mvc-dispatcher") Question question) {
        if ( questionList.get(counter).getAnswer().equals(question.getAnswer())) {
            int points = questionList.get(counter).getPoints();
            int resultpoints = UserController.user.getTotal_points() + points;
            UserController.user.setTotal_points(resultpoints);
        }
        counter = counter + 1;
        ModelAndView model;
        if (counter == 5) {
            model = new ModelAndView("result");
            model.addObject("user", UserController.user);
            DAOUsers.getInstance().updateUser(UserController.user);
            counter = 0;
        } else {
            model = new ModelAndView("redirect:/questionMenu");
        }
        return model;
    }

    @RequestMapping(value = "/stopTest", method = RequestMethod.GET)
    public ModelAndView stopTest() {
        counter = 0;
        ModelAndView model;
        model = new ModelAndView("result");
        model.addObject("user", UserController.user);
        DAOUsers.getInstance().updateUser(UserController.user);
        return model;
    }

    @RequestMapping(value = "/allResult", method = RequestMethod.GET)
    public ModelAndView allResult() {
        counter = 0;
        ModelAndView model;
        model = new ModelAndView("allResult");
        model.addObject("list", DAOUsers.getInstance().getAllUsers());
        return model;
    }

}
