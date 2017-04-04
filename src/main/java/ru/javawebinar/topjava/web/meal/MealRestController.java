package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    Meal save(Meal meal) {
        LOG.info("save " + meal);
        return service.save(AuthorizedUser.id(), meal);
    }

    void delete(int id) {
        LOG.info("delete " + id);
        service.delete(AuthorizedUser.id(), id);
    }

    Meal get(int id) {
        LOG.info("get " + id);
        return service.get(AuthorizedUser.id(), id);
    }

    public List<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }
}