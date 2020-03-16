package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

// fetches values from resources/datasource.properties
// properties must match from the properties file
// Spring expressions are evaluated and Spring Context returns values to be used externally
@Configuration
@PropertySource("classpath:datasource.properties") // use this set of properties in the datasource.properties to resolve values' expression
public class PropertyConfig {

    @Autowired
    Environment env;

    // external properties
    @Value("${guru.username}")
    String user;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

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

    // allows us connect properties to their values in the properties file
    // reads the file containing properties
    // and allows us to use @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
}
