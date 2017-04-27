package ru.javawebinar.topjava.service;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;


@ActiveProfiles(Profiles.JPA)
public class JPAUserServiceTest extends UserServiceTest{

}