package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> this.save(AuthorizedUser.id(), meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (!(userId == AuthorizedUser.id()))
            return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        if (!(userId == AuthorizedUser.id()))
            return false;
        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int userId, int id) {
        if (!(userId == AuthorizedUser.id()))
            return null;
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (!(userId == AuthorizedUser.id()))
            return null;
        List<Meal> list = new ArrayList<Meal>(repository.values());
        list.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        return list;
    }
}

