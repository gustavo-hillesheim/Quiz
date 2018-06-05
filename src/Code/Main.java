package Code;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Matematica m = new Matematica();
		Pergunta p = m.getPergunta();
		
		JFrame frame = new JFrame();
		frame.setSize(416, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(p.getInterface()[0]);
		
		frame.setVisible(true);
	}
}
