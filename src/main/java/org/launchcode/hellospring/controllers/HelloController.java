package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    public static String createMessage(String name, String lang){
        String greeting;
        switch (lang){
            case "fr":
                greeting = "Bonjour, " + name;
                break;
            case "sp":
                greeting = "Hola, " + name;
                break;
            case "en":
                greeting = "Hello, " + name;
                break;
            case "zh":
                greeting = "你好 " + name;
                break;
            case "de":
                greeting = "Hallo, " + name;
                break;
            default:
                greeting = "Oops! Something's gone wrong.";
        }
        return greeting;
    }

    //Handles requests at /hello
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello, Spring!";
//    }
//    @GetMapping("goodbye")
//    @ResponseBody
//    public String goodbye() {
//        return "Goodbye, Spring!";
//    }

    //Create a handler that handles requests of the form /hello?name=LaunchCode
    @RequestMapping(value="hello", method= {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name, @RequestParam String lang, Model model){
        String theGreeting = createMessage(name, lang);
        model.addAttribute("greeting", theGreeting);
        return "hello";
    }

    //handles requests of the form /hello/LaunchCode
    @GetMapping("hello/{name}/{lang}")
    public String helloWithPathParam(@PathVariable String name, @PathVariable String lang, Model model){
        String greeting = createMessage(name,lang);
        model.addAttribute("greeting",greeting);
        return "hello";
    }

    @GetMapping
    public String helloForm() {
        return "form";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("Launchcode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names",names);
        return "hello-list";
    }
}
