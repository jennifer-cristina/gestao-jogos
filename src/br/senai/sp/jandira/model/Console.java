package br.senai.sp.jandira.model;

public enum Console {
	
	PLAYSTATION("Playstation"),
	XBOX("Xbox"),
	NINTENDO("Nintendo");
	
	private String descricao;
	
	private Console(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
