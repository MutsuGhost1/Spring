package com.example.springmybatis.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.example.springmybatis.mapper", "com.example.springmybatis.pojo" })
public class PersistenceAutoConfig {

}