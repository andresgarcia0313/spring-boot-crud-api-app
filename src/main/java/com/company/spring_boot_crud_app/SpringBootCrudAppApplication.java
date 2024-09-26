package com.company.spring_boot_crud_app; // Paquete que agrupa clases relacionadas con la aplicación.

import org.springframework.beans.factory.annotation.Value; // Importa la anotación Value para la inyección de propiedades.
import org.springframework.boot.SpringApplication; // Importa la clase para iniciar la aplicación de Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para habilitar la configuración automática de Spring Boot.
import org.springframework.boot.context.event.ApplicationReadyEvent; // Importa el evento que indica que la aplicación ha sido iniciada.
import org.springframework.context.ApplicationListener; // Importa la interfaz para escuchar eventos de la aplicación.
import org.springframework.context.annotation.Bean; // Importa la anotación Bean para definir métodos que devuelven objetos gestionados por Spring.
import org.springframework.core.env.ConfigurableEnvironment; // Importa la interfaz para gestionar el entorno de la aplicación.
import org.springframework.stereotype.Component; // Importa la anotación para marcar la clase como un componente de Spring.

import io.github.cdimascio.dotenv.Dotenv; // Importa la clase Dotenv para cargar variables de entorno desde un archivo .env.

import org.springframework.lang.NonNull; // Importa la anotación para indicar que un parámetro no debe ser nulo.
import org.slf4j.Logger; // Importa la interfaz Logger para el registro de mensajes.
import org.slf4j.LoggerFactory; // Importa la clase para crear instancias de Logger.

@SpringBootApplication // Anotación que marca esta clase como la entrada principal de la aplicación de Spring Boot.
public class SpringBootCrudAppApplication {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudAppApplication.class, args); // Inicia la aplicación de Spring Boot.
    }

    /**
     * Configura y carga las variables de entorno desde un archivo .env.
     *
     * @param env El entorno configurable de la aplicación.
     * @return El objeto Dotenv cargado con las variables de entorno.
     */
    @Bean // Indica que el método devuelve un objeto gestionado por el contenedor de Spring.
    public Dotenv dotenv(ConfigurableEnvironment env) {
        Dotenv dotenv = Dotenv.configure().load(); // Carga las variables de entorno desde el archivo .env.
        // Almacena las entradas de Dotenv en el entorno de la aplicación.
        dotenv.entries().forEach(entry -> env.getSystemProperties().put(entry.getKey(), entry.getValue()));
        return dotenv; // Retorna el objeto Dotenv.
    }
}

// Clase que escucha el evento de inicio de la aplicación y registra un mensaje.
@Component // Marca esta clase como un componente de Spring que será gestionado por el contenedor.
class StartupMessageListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(StartupMessageListener.class); // Logger para registrar mensajes de inicio.

    @Value("${server.port}") // Inyecta el valor de la propiedad 'server.port' desde el archivo de propiedades.
    private int port; // Almacena el puerto en el que la aplicación está escuchando.

    /**
     * Método que se llama cuando la aplicación está lista.
     *
     * @param event El evento que indica que la aplicación está lista.
     */
    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        // Registra un mensaje indicando que la aplicación ha iniciado y en qué puerto se puede acceder.
        logger.info("Aplicación iniciada. Puedes acceder a ella en: http://localhost:{}", port);
    }
}
