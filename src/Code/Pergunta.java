package Code;

import java.util.ArrayList;
import java.util.Random;

public class Pergunta {

	String enunciado;
	String correta;
	String[] alternativas;
	
	public Pergunta(String enunciado, String[] alternativas) {
		
		this.enunciado = enunciado;
		this.correta = alternativas[0];
		this.alternativas = alternativas;
	}
	
	public String getEnunciado() {
		
		return this.enunciado;
	}
	
	public String[] getAlternativas() {
		
		return embaralharAlternativas();
	}

	public boolean validarResposta(String resposta) {
		
		if (resposta.equals(correta)) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	private String[] embaralharAlternativas() {
		
		String[] alternativas = new String[this.alternativas.length];
		ArrayList<Integer> indexUsados = new ArrayList<>();
		
		Random random = new Random();
		
		for (int i = 0; i < alternativas.length; i++) {
			
			int index = 0;
			
			do {
				
				index = random.nextInt(alternativas.length);
			} while (indexUsados.indexOf(index) != -1);
			
			alternativas[i] = this.alternativas[index];
		}
		
		return alternativas;
	}
}