/*
 * File: TestConfig
 * Created on 09.09.2020
 *
 * Copyright (c) 2020 by Daimler AG
 */
package com.dailyrental;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Grote, GROTEMI (415)
 */
@Configuration
@ComponentScan(basePackageClasses = TestConfig.class)
public class TestConfig {
}
