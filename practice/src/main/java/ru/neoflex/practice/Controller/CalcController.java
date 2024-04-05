package ru.neoflex.practice.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a+b;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Min(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a-b;
    }

    @GetMapping("/Hello")
    public String GetString(){
        return "Hello World";
    }

}
