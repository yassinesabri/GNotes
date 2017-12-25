package ma.ac.ensa.gnotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer webPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocation = new Resource[]{
                new ClassPathResource("database.properties")
        };
        p.setLocations(resourceLocation);
        p.setFileEncoding("UTF-8");
        p.setIgnoreUnresolvablePlaceholders(true);
        return p;
    }
}
