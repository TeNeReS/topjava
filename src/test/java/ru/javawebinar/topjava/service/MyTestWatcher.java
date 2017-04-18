package ru.javawebinar.topjava.service;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MyTestWatcher extends TestWatcher {
    private Date start;
    private String name;
    private Map<String, Long> methodsTime = new HashMap<String, Long>();

    @Override
    protected void starting(Description description) {
        super.starting(description);
        start = new Date();
        name = description.getMethodName();
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        Date finish = new Date();
        long time = finish.getTime() - start.getTime();
        System.out.println("Время выполнения " + name + ": " + time + " мс");
        methodsTime.put(name, time);
    }

    public Map<String, Long> getMethodsTime() {
        return methodsTime;
    }
}
