package br.senai.sp.jandira.repository;

import br.senai.sp.jandira.model.Jogo;

public class JogoRepository {

	private Jogo[] jogos;
	
	public JogoRepository(int quantidadeJogos) {
		jogos = new Jogo[quantidadeJogos];
	}
	
	public void gravar(Jogo jogo, int posicao) {
		jogos[posicao] = jogo;

	}
	
	public Jogo listarJogo(int posicao) {
		return jogos[posicao];
	}
	
	public Jogo[] listarTodos() {
		return jogos;
	}
	
	public int getTamanho() {
		return jogos.length;
	}
	
}
