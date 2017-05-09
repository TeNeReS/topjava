package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class MealController extends MealRestController{
    public MealController(MealService service) {
        super(service);
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String createAndUpdate(@ModelAttribute("meal") Meal meal) {
        super.create(meal);
        return "meals";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        modelAndView.addObject("meal", meal);
        modelAndView.setViewName("meal");
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        super.delete(id);
        return "redirect:meals";
    }

}
