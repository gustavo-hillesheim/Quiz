package Code;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		Matematica m = new Matematica();
		Pergunta p = m.getPergunta();
		
		String info = p.getEnunciado() + "\n\n";
		
		String[] alternativas = p.getAlternativas();
		
		for (int i = 0; i < alternativas.length; i++) {
			
			info += (i + 1) + ". " + alternativas[i] + "\n";
		}
		
		info += "\n\nInforme sua resposta";
		
		int opt = Integer.parseInt(JOptionPane.showInputDialog(info));
		
		System.out.println(p.validarResposta(alternativas[opt - 1]));
	}
}
