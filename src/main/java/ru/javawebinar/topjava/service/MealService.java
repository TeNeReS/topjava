package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal save(int userId, Meal Meal) throws NotFoundException;

    void delete(int userId, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    List<MealWithExceed> getAll(int userId) throws NotFoundException;
}