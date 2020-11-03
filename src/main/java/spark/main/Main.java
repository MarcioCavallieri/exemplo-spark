package spark.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Spark;
import spark.servicos.JogoServico;

public class Main {
	private static int PORTA = Integer.parseInt(System.getenv().getOrDefault("PORT", "9000"));
	private static final String MOUNT_POINT = "/api";
	private static final Logger LOGGER = Logger.getLogger("Main");
	
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Listening on {0}", PORTA);
		Spark.port(PORTA);
		Spark.get("/", (req, resp) -> "Exemplo de API's com Spark");
		
        final var mouting = "Mouting " + MOUNT_POINT + "{0}";
        LOGGER.log(Level.INFO, mouting, "/jogos");
        new JogoServico(MOUNT_POINT);
	}
}