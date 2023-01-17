package com.example.vscapp01.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DebugControllerTest {
    
    private Logger logger = LogManager.getLogger(DebugControllerTest.class);
    
    @Test
    void test1() {
        System.out.println("## test1 시작 ##");
        System.out.println();

        logger.debug("debugTest calss call..");




    }





}
