package com.sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by popikyardo on 06.08.15.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.sample")
@Import({DbConfig.class})
public class AppConfig {

}
