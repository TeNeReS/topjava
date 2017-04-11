package ru.javawebinar.topjava;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryImpl;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserRepositoryImpl;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");

        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");

        MealRepository mR = springContext.getBean(JdbcMealRepositoryImpl.class);
//        UserRepository uR = springContext.getBean(JdbcUserRepositoryImpl.class);

        System.out.println(mR.save(new Meal(100011, LocalDateTime.of(2015, Month.MAY, 23, 10, 0), "Завтрак", 500), 100000));

//        System.out.println(uR.get(100000));
//        System.out.println(mR.get(100002, 100000));
//        System.out.println();
//        System.out.println(mR.getAll(100000));
//        System.out.println(mR.getBetween(LocalDateTime.of(2015, Month.MAY, 1, 0, 0), LocalDateTime.of(2015, Month.MAY, 30, 0, 0), 100000));
    }
}
