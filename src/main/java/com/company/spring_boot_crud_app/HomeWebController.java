package com.company.spring_boot_crud_app;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeWebController {

    @GetMapping("/")
    public String home() {
        return "Bienvenido";
    }
}
