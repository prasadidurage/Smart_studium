package lk.ijse.b72.finalproject.back_end.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfig   {


    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper =  new ModelMapper();
    return modelMapper;

    }



}
