package spark.servicos;

import spark.Spark;
import spark.repositorios.JogoRepositorio;

public class JogoServico {
	private static String mountPoint = "/api";
	public final String path;
	private JogoRepositorio repositorio = new JogoRepositorio();
	
	public JogoServico() {
		this.path = mountPoint + "/jogos";
		
		Spark.get(this.path, repositorio::obterTodos);
		Spark.get(this.path + "/:id", repositorio::obterPorId);
		Spark.post(this.path, repositorio::adicionar);
		// Spark.patch(this.path + "/:id", this::handlePatch);
		// Spark.delete(this.path + "/:id", this::handleDelete);
	}
}
