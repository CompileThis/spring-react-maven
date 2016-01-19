package com.compilethis.srm.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = { ApplicationConfiguration.class })
public class ApplicationConfiguration {
}
