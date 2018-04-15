import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @EnableAutoConfiguration
    public class Example extends SpringBootServletInitializer {

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(Example.class);
        }

        @RequestMapping("/")
        String home() {
            return "Spring test with war";
        }

        public static void main(String[] args) throws Exception {
            SpringApplication.run(Example.class, args);
        }

    }

