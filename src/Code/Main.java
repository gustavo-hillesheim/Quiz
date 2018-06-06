package Code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		/*Jogador j1 = new Jogador("Diogo");
		j1.finalizarJogo(5, 200);
		
		Jogador j2 = new Jogador("Nathan");
		j2.finalizarJogo(5, 300);
		
		JScrollPane pane = new CustomTableModel(Jogador.listarJogadoresComTempo(), new int[] {1, 2, 3}).getTable();
		
		pane.setSize(200, 200);
		
		frame.add(pane);*/
		
		Matematica m = new Matematica();
		
		JPanel pane = m.getPergunta().getInterface();
		
		JButton btn = new JButton("Atualizar");
		btn.setBounds(200, 200, 100, 50);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				m.getPergunta().atualizarPanel();
			}
		});
		
		frame.add(pane);
		frame.add(btn);
		
		frame.setVisible(true);
		
		
	}
}