package br.senai.sp.jandira;

import br.senai.sp.jandira.model.Console;
import br.senai.sp.jandira.model.Fabricante;
import br.senai.sp.jandira.model.Jogo;
import br.senai.sp.jandira.repository.FabricanteRepository;
import br.senai.sp.jandira.ui.FrameGestaoJogos;

public class App {

	public static void main(String[] args) {
		
		Fabricante jenniferTech = new Fabricante();
		jenniferTech.setNome("Jennifer Tech");
		
		Jogo mario = new Jogo();
		mario.setTitulo("Mario");
		mario.setZerado(true);
		mario.setConsole(Console.NINTENDO);
		mario.setValor(100);
		mario.setObservacoes("Muito bom!");
		mario.setFabricante(jenniferTech);
		
		FrameGestaoJogos frame = new FrameGestaoJogos();
		frame.setVisible(true);
	}

}
