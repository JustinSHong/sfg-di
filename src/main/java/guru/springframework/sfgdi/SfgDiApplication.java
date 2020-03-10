package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Note: We never instantiated a new controller object
	// Spring did this for us --> create a new instance and put it in the class

// hallmark of Dependency Injection
	// let framework handle management and creation of objects

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		// return an application context
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		// DECLARE A REFERENCE
			// name is defaulted to lowerCase name of class
		// Spring scans for "beans" on start up --> looks for annotations
		MyController myController = (MyController) ctx.getBean("myController");

		// work with controller object
//		String greeting = myController.sayHello();
//		System.out.println(greeting);


		// ----- METHODS OF CONTROLLING DEPENDENCY INJECTION -----
			// useful for messaging - kafka, jms
			// can set up messaging for different deployments with dependency injection

		System.out.println("---------- Primary Injection ----------");
		System.out.println(myController.sayHello());

		// Spring Dependency Injection - Property
		System.out.println("---------- Property Injection ----------");
		// hey Spring instantiate this controller and inject the appropriate bean in to it so I can work with it
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		// Spring Dependency Injection - Setter
		System.out.println("---------- Setter Injection ----------");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		// Spring Dependency Injection - Constructor
		System.out.println("---------- Constructor Injection ----------");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		// PROFILE
		System.out.println("---------- Profiles ----------");
		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());
	}

}
