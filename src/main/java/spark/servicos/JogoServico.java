package spark.servicos;

import spark.Spark;
import spark.repositorios.JogoRepositorio;

public class JogoServico {
	public final String path;
	private JogoRepositorio repositorio = new JogoRepositorio();
	
	public JogoServico(String mountPoint) {
		this.path = mountPoint + "/jogos";
		
		Spark.get("/jogos", (req, resp) -> "Exemplo de API's com Spark");
		Spark.get(this.path, repositorio::obterTodos);
		Spark.get(this.path + "/:id", repositorio::obterPorId);
		Spark.post(this.path, repositorio::adicionar);
	    Spark.patch(this.path + "/:id", repositorio::atualizar);
	    Spark.delete(this.path + "/:id", repositorio::deletar);
	}
}
