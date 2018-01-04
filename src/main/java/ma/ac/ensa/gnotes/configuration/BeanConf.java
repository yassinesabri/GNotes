package ma.ac.ensa.gnotes.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConf {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
