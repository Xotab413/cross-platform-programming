package com.example.service;

import com.example.service.asyncCounter.AsyncCounter;
import com.example.service.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.example.service.stats")
@ComponentScan("com.example.service.process")
@ComponentScan("com.example.service.cache")
@ComponentScan("com.example.service.asyncCounter")
public class Config {

}
