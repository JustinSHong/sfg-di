package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeDataSource2;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

// use a Java config class to pull values from the property file in to the Spring Context at runtime
    // fetches values from resources/datasource.properties
    // properties must match from the properties file
    // Spring expressions are evaluated and Spring Context returns values to be used outside the file

// configure Beans to expose property values to the context

@Configuration
// @PropertySource({"classpath:datasource.properties", "classpath:jms.properties"}) // use this set of properties in the datasource.properties to resolve values' expression
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    // wire in env variables at the system level from the system Bean
        // these take precedent over values in the properties files
    @Autowired
    Environment env;

    // external properties - @Value tells Spring to store a particular property value at a location
    @Value("${guru.username}")
    String user;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

    @Value("${guru.jms.username}")
    String jmsUsername;

    @Value("${guru.jms.password}")
    String jmsPassword;

    @Value("${guru.jms.url}")
    String jmsUrl;

    // values from application.properties file
    @Value("${guru.datasource2.username}")
    String datasource2username;

    @Value("${guru.datasource2.password}")
    String datasource2password;

    @Value("${guru.datasource2.dburl}")
    String datasource2url;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUsername(jmsUsername);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);

        return fakeJmsBroker;
    }

    // expose values to be used elsewhere
    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
//        fakeDataSource.setUser(user);
        fakeDataSource.setUser(env.getProperty("USERNAME")); // ask Spring to give system property USERNAME
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);

        return fakeDataSource;
    }

    @Bean
    public FakeDataSource2 fakeDataSource2() {
        FakeDataSource2 fakeDataSource2 = new FakeDataSource2();
        fakeDataSource2.setUser(datasource2username);
        fakeDataSource2.setPassword(datasource2password);
        fakeDataSource2.setUrl(datasource2url);

        return fakeDataSource2;
    }

    // allows us to save property values to members of this Bean
    // reads the file containing properties
    // and allows us to use @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
}
