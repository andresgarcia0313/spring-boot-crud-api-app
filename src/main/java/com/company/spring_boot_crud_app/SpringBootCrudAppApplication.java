package com.company.spring_boot_crud_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.lang.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringBootCrudAppApplication {

    public static void main(String[] args) {
        Dotenv.configure().load();
        SpringApplication.run(SpringBootCrudAppApplication.class, args);
    }
}

// Componente para imprimir el mensaje al inicio
@Component
class StartupMessageListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(StartupMessageListener.class);

    @Value("${server.port}")
    private int port;
    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        logger.info("Aplicación iniciada. Puedes acceder a ella en: http://localhost:{}", port);
    }
}
