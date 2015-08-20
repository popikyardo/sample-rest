package com.sample.config;

import org.springframework.web.WebApplicationInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
public class AppInitializer extends Initializer implements WebApplicationInitializer {

    List<Class> registerClass = new ArrayList<>();
    List<String> mappedUrl = new ArrayList<>();

    {
        registerClass.add(AppConfig.class);
        mappedUrl.add("/");
    }

    @Override
    public List<Class> getRegisterClass() {
        return registerClass;
    }

    @Override
    public List<String> getMappedUrl() {
        return mappedUrl;
    }
}
