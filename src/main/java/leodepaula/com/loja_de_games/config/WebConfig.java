package leodepaula.com.loja_de_games.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Configuration
public class WebConfig {

    @Bean
    public WebProperties webProperties() {
        return new WebProperties();
    }
}
