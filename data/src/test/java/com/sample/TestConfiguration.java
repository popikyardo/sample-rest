package com.sample;

import com.sample.config.DbConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by popikyardo on 06.08.15.
 */
@Configuration
@ComponentScan("com.sample")
@Import({DbConfig.class})
public class TestConfiguration {
}
