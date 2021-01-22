package co.za.entelect.relationship.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController
{

    @RequestMapping("/greeting")
    public String sayHelloServer()
    {
        return "hello server..";
    }
}
