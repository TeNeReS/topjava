package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkAccess;
import static ru.javawebinar.topjava.util.ValidationUtil.checkAccessAndExistence;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) throws NotFoundException {
        return checkAccess(repository.save(userId, meal));
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException{
        checkAccessAndExistence(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException{
        return checkAccessAndExistence(repository.get(userId, id), id);
    }

    @Override
    public List<MealWithExceed> getAll(int userId) throws NotFoundException{
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}