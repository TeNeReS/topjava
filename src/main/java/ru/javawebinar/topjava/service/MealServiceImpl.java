package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) throws NotFoundException {
        return checkNotFoundWithId(repository.save(userId, meal), userId);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException{
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException{
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public List<Meal> getAll(int userId) throws NotFoundException{
        return checkNotFoundWithId(repository.getAll(userId), userId);
    }
}