package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }


    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 9, 0), "Еще еда", 1200);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_THREE, MEAL_TWO, MEAL_ONE, newMeal), service.getAll(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100003, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_THREE, MEAL_ONE), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(100005, USER_ID);
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(100003, USER_ID);
        MealTestData.MATCHER.assertEquals(MEAL_TWO, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(100004, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = MEAL_SIX;
        updated.setDescription("Измененная еда");
        updated.setCalories(440);
        service.update(updated, 100001);
        MealTestData.MATCHER.assertEquals(updated, service.get(100007, ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal updated = MEAL_SIX;
        service.update(updated, USER_ID);
    }
}