package guru.springframework.controllers;

import guru.springframework.services.GreetingService;
import org.springframework.stereotype.Controller;

/**
 * Created by jt on 12/27/19.
 */
@Controller
public class I18nController {

    private final GreetingService greetingService;

    public I18nController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
