package com.company.day3.lab01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
//@WebAppConfiguration
public class EventRegistrationTest {

    @Autowired
    private SomeService eventService;

    @Test
    public void registrationAndFindAllTest(){
        eventService.run();
        String name = "Alexander";
        eventService.registration(name);
        Guest guest = eventService.findGuestByName(name);
        Assert.assertEquals(name, guest.getName());
        System.out.println("Guest created successfully");
        Assert.assertEquals(guest.getId(), eventService.getGuests().get(0).getId());
        System.out.println("Tests completed");
    }
}