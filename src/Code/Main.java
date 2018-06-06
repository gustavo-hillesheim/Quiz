package Code;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Jogador j1 = new Jogador("Diogo");
		j1.finalizarJogo(5, 200);
		
		Jogador j2 = new Jogador("Nathan");
		j2.finalizarJogo(5, 300);
		
		JScrollPane pane = new CustomTableModel(Jogador.listarJogadoresComTempo(), new int[] {1, 2, 3}).getTable();
		
		pane.setSize(200, 200);
		
		frame.add(pane);
		
		frame.setVisible(true);
	}
}