package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        getFilteredWithExceededJ8(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> mealWithExceedList = new ArrayList<UserMealWithExceed>();
        Map<LocalDate, Integer> calPerDay = new HashMap<LocalDate, Integer>();
        for(UserMeal userMeal : mealList)
        {
            LocalDate ld = userMeal.getDateTime().toLocalDate();
            calPerDay.put(ld, calPerDay.getOrDefault(ld, 0) + userMeal.getCalories());
        }

        for(UserMeal userMeal : mealList)
        {
            LocalDateTime ldt = userMeal.getDateTime();
            if (isBetween(ldt.toLocalTime(), startTime, endTime))
            {
                mealWithExceedList.add(new UserMealWithExceed(ldt, userMeal.getDescription(), userMeal.getCalories(), calPerDay.get(ldt.toLocalDate()) > caloriesPerDay));
            }
        }
        return mealWithExceedList;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceededJ8(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Stream stream = mealList.stream()
                .filter((p) -> isBetween(p.getDateTime().toLocalTime(), startTime, endTime));
        return null;
    }
}
