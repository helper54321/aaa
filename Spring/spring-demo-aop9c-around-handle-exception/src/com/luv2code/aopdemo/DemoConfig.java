package com.luv2code.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //használhasson proxy-t a háttérben
@ComponentScan("com.luv2code.aopdemo")
public class DemoConfig {

}
