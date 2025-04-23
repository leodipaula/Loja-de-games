package leodepaula.com.loja_de_games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class LojaDeGamesApplication {

	static {
		BlockHound.install();
	}

	public static void main(String[] args) {

		SpringApplication.run(LojaDeGamesApplication.class, args);
	}

}
