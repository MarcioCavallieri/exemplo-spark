package spark.repositorios;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.dominio.Jogo;

public class JogoRepositorio {
	private static final AtomicLong CONTADOR = new AtomicLong();
	private HashMap<Long, Jogo> listaDeJogos;
	
	private void mockJogos() {
		Jogo jogo1 = new Jogo(CONTADOR.getAndIncrement(), "The Last of Us", "Suspense", "PS4");
		Jogo jogo2 = new Jogo(CONTADOR.getAndIncrement(), "Horizon Zero Down", "Aventura", "PS4");
		Jogo jogo3 = new Jogo(CONTADOR.getAndIncrement(), "Devil May Cry", "Ação", "XBOX One");
		Jogo jogo4 = new Jogo(CONTADOR.getAndIncrement(), "God of War", "Aventura", "PS4");
		listaDeJogos.put(jogo1.getId(), jogo1);
		listaDeJogos.put(jogo2.getId(), jogo2);
		listaDeJogos.put(jogo3.getId(), jogo3);
		listaDeJogos.put(jogo4.getId(), jogo4);
	}

	public JogoRepositorio() {
		listaDeJogos = new HashMap<>();
		
		mockJogos();
	};

	public String obterTodos(Request requisicao, Response resposta) {
		resposta.type("application/json");
		resposta.status(200);
		
		return new Gson().toJson(listaDeJogos.values());		
	}
	
	public String obterPorId(Request requisicao, Response resposta) {
		resposta.type("application/json");
		
		Long id = Long.parseLong(requisicao.params("id"));
		final Jogo jogo = listaDeJogos.get(id);
		
		if (jogo == null) {
			resposta.status(404);		
			return new Gson().toJson("{jogo not found}");
		}
		
		resposta.status(200);		
		return new Gson().toJson(jogo);		
	}
	
	public String adicionar(Request requisicao, Response resposta) {
		resposta.type("application/json");
				
		Jogo novoJogo = new Gson().fromJson(requisicao.body(), Jogo.class);
		novoJogo.setId(CONTADOR.getAndIncrement());
		
		listaDeJogos.put(novoJogo.getId(), novoJogo);
		
		resposta.status(201);		
		return new Gson().toJson(novoJogo);		
	}
	
	public String atualizar(Request requisicao, Response resposta) {
		resposta.type("application/json");
				
		Jogo jogo = new Gson().fromJson(requisicao.body(), Jogo.class);
		Jogo jogoAtual = listaDeJogos.get(jogo.getId());
		
		if (jogoAtual != null) {
			jogoAtual.setNome(jogo.getNome());
			jogoAtual.setGenero(jogo.getGenero());
			jogoAtual.setPlataforma(jogo.getPlataforma());
			
			resposta.status(204);
			return new Gson().toJson(jogoAtual);
		}
		
		resposta.status(404);
		return new Gson().toJson("{jogo not found}");				
	}
	
	public String deletar(Request requisicao, Response resposta) {
		resposta.type("application/json");
		Long key = Long.parseLong(requisicao.params(":id"));
		
		System.out.println(key);
		
		if (listaDeJogos.containsKey(key)) {
			Jogo jogo = listaDeJogos.get(key);
			listaDeJogos.remove(key);
			
			resposta.status(200);
			return new Gson().toJson(jogo);
		}
				
		resposta.status(404);
		return new Gson().toJson("{jogo not found}");				
	}
}

