package spark.repositorios;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.dominio.Jogo;

public class JogoRepositorio {
	private static final AtomicLong CONTADOR = new AtomicLong();
	private HashMap<Long, Jogo> listaDejogos;
	
	private void mockJogos() {
		Jogo jogo1 = new Jogo(CONTADOR.getAndIncrement(), "The Last of Us", "Suspense", "PS4");
		Jogo jogo2 = new Jogo(CONTADOR.getAndIncrement(), "Horizon Zero Down", "Aventura", "PS4");
		Jogo jogo3 = new Jogo(CONTADOR.getAndIncrement(), "Devil May Cry", "Ação", "XBOX One");
		Jogo jogo4 = new Jogo(CONTADOR.getAndIncrement(), "God of War", "Aventura", "PS4");
		listaDejogos.put(jogo1.getId(), jogo1);
		listaDejogos.put(jogo2.getId(), jogo2);
		listaDejogos.put(jogo3.getId(), jogo3);
		listaDejogos.put(jogo4.getId(), jogo4);
	}

	public JogoRepositorio() {
		listaDejogos = new HashMap<>();
		
		mockJogos();
	};

	public String obterTodos(Request requisicao, Response resposta) {
		resposta.type("application/json");
		resposta.status(200);
		
		return new Gson().toJson(listaDejogos.values());		
	}
	
	public String obterPorId(Request requisicao, Response resposta) {
		resposta.type("application/json");
		
		Long id = Long.parseLong(requisicao.params("id"));
		final Jogo jogo = listaDejogos.get(id);
		
		if (jogo == null) {
			resposta.status(404);		
			return new Gson().toJson("{jogo not found}");
		}
		
		resposta.status(200);		
		return new Gson().toJson(jogo);		
	}
	
	public String adicionar(Request requisicao, Response resposta) {
		resposta.type("application/json");
		
		
		String nome = requisicao.attribute("nome");
		String genero = requisicao.queryParams("genero");
		String plataforma = requisicao.queryParams("plataforma");
		
		System.out.println(nome + " - " + genero + " - " + plataforma);
		
		Jogo novoJogo = new Jogo(CONTADOR.getAndIncrement(), nome, genero, plataforma);
		
		listaDejogos.put(novoJogo.getId(), novoJogo);
		
		resposta.status(201);		
		return new Gson().toJson(novoJogo);		
	}
}
