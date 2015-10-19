package com.onmysky.fuji.server.context.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mark on 15/10/19.
 */
@Configuration
public class UtilsConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
