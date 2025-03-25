//package com.chargr.config;
//
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Allow CORS for all endpoints
//                .allowedOrigins("*") // Allow all origins
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all methods
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow credentials if needed
//    }
//}