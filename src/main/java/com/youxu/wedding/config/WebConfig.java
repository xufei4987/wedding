package com.youxu.wedding.config;

import com.youxu.wedding.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Component
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Value("${static.resource.dir}")
    private String resourceDir;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String jarHomePath = CommonUtils.getJarHomePath();
        String resourceLocationPath = jarHomePath + File.separator + resourceDir + File.separator;

        log.info("resource location path is {}",resourceLocationPath);
        registry.addResourceHandler("/photos/**").addResourceLocations("file:" + resourceLocationPath);
    }
}
