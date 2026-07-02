package com.example.Robomain.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads variables from .env file into Spring's Environment before context starts.
 * .env takes lower priority than real OS environment variables (addLast behavior
 * is intentional — OS env always wins, .env is the fallback for local dev).
 */
public class DotEnvEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String PROPERTY_SOURCE_NAME = "dotenvProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()   // no error if .env doesn't exist (CI/prod)
                .load();

        Map<String, Object> props = new HashMap<>();
        dotenv.entries().forEach(entry -> props.put(entry.getKey(), entry.getValue()));

        if (!props.isEmpty()) {
            // addLast = lower priority than OS env vars, so real env always overrides .env
            environment.getPropertySources().addLast(
                    new MapPropertySource(PROPERTY_SOURCE_NAME, props)
            );
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
