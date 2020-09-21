package com.shr.backend;


import com.shr.backend.service.StaticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private StaticsService staticsService;

    @Test
    public void contextLoads() {
        staticsService.customerStatics(new Date(120, Calendar.MAY, 1),
                new Date(120, Calendar.JULY, 1), "username");
    }
}
