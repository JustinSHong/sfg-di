package guru;

import guru.springframework.controllers.*;
import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeDataSource2;
import guru.springframework.examplebeans.FakeJmsBroker;
import guru.springframework.examplebeans.FakeKafkaBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"guru.springframework.services", "guru.springframework"}) // tell Spring where to scan - override default
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		// PROPERTY SOURCE
		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class); // give ctx the type of bean
		System.out.println(fakeDataSource.getUser());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getUrl());

		FakeJmsBroker fakeJmsBroker = ctx.getBean(FakeJmsBroker.class);
		System.out.println(fakeJmsBroker.getUsername());
		System.out.println(fakeJmsBroker.getPassword());
		System.out.println(fakeJmsBroker.getUrl());

		FakeDataSource2 fakeDataSource2 = ctx.getBean(FakeDataSource2.class);
		System.out.println(fakeDataSource2.getUser());
		System.out.println(fakeDataSource2.getPassword());
		System.out.println(fakeDataSource2.getUrl());

		FakeKafkaBroker fakeKafkaBroker = ctx.getBean(FakeKafkaBroker.class);
		System.out.println(fakeKafkaBroker.getUser());
		System.out.println(fakeKafkaBroker.getPassword());
		System.out.println(fakeDataSource2.getUrl());


	}

}
