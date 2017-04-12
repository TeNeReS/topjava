package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int ID_ONE = START_SEQ + 2;
    public static final int ID_TWO = START_SEQ + 3;
    public static final int ID_THREE = START_SEQ + 4;
    public static final int ID_FOUR = START_SEQ + 5;
    public static final int ID_FIVE = START_SEQ + 6;
    public static final int ID_SIX = START_SEQ + 7;

    public static final Meal MEAL_ONE = new Meal(ID_ONE, LocalDateTime.of(2015, Month.MAY, 30, 10, 0),"Завтрак", 500);
    public static final Meal MEAL_TWO = new Meal(ID_TWO, LocalDateTime.of(2015, Month.MAY, 30, 13, 0),"Обед", 1001);
    public static final Meal MEAL_THREE = new Meal(ID_THREE, LocalDateTime.of(2015, Month.MAY, 30, 19, 0),"Ужин", 500);
    public static final Meal MEAL_FOUR = new Meal(ID_FOUR, LocalDateTime.of(2015, Month.MAY, 25, 9, 0),"Завтрак", 500);
    public static final Meal MEAL_FIVE = new Meal(ID_FIVE, LocalDateTime.of(2015, Month.MAY, 25, 12, 0),"Обед", 500);
    public static final Meal MEAL_SIX = new Meal(ID_SIX, LocalDateTime.of(2015, Month.MAY, 25, 18, 0),"Ужин", 1000);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();



}
