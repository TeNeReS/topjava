package ru.javawebinar.topjava.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    @Transactional(timeout = 10)
    default Meal getWithUser(int id, int userId){
        Meal meal = get(id, userId);
        meal.getUser().getName();
        return meal;
    }

    // ORDERED dateTime
    List<Meal> getAll(int userId);

    // ORDERED dateTime
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
