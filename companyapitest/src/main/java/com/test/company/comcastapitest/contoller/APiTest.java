package com.test.company.comcastapitest.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APiTest {
    @GetMapping("/test")
    public String test(){
        return "Test";
    }
}
