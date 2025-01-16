package com.automationtest.utils.templates;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class EnvironmentConfig {

    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public String getVariable(String variable){
        String value = System.getenv(variable);
        if(value == null || value.isEmpty()){
            value = System.getProperty(variable);
        }

        return value == null ? "" : value;

        }
}

