package spark.dominio;

import java.io.Serializable;

public class Jogo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private String genero;
	private String plataforma;
	
	public Jogo() {
	
	}
	
	public Jogo(long id, String nome, String genero, String plataforma) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.plataforma = plataforma;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getGenero() {
		return genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
