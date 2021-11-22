package br.senai.sp.jandira;

import br.senai.sp.jandira.model.Console;
import br.senai.sp.jandira.model.Fabricante;
import br.senai.sp.jandira.model.Jogo;
import br.senai.sp.jandira.repository.FabricanteRepository;
import br.senai.sp.jandira.ui.FrameGestaoJogos;

public class App {

	public static void main(String[] args) {
		
		FrameGestaoJogos frame = new FrameGestaoJogos();
		frame.setVisible(true);
	}

}
