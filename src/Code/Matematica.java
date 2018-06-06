package Code;

import java.util.ArrayList;
import java.util.Random;

public class Matematica extends Categoria {

	private ArrayList<String> enunUsados = new ArrayList<>();
	
	@Override
	public Pergunta getPergunta() {
		
		return gerarPergunta();
	}
	
	private Pergunta gerarPergunta() {
		
		Object[] enunResul = gerarEnunciado();
		String enunciado = (String) enunResul[0];
		double resultado = (Double) enunResul[1];
		
		String[] alternativas = gerarAlternativas(resultado);
		
		Pergunta p = new Pergunta(enunciado, alternativas);
		
		return p;
	}
	
	private Object[] gerarEnunciado() {
		
		Random random = new Random();
		
		String enun = "";
		double resul = 0;
		
		do {
			
			double n1 = random.nextInt(100) + 1;
			double n2 = random.nextInt(100) + 1;
			int op = random.nextInt(4) + 1;
			
			switch (op) {
			
				case 1: {
					enun = (int)n1 + " + " + (int)n2;
					resul = n1 + n2;
					break;
				}
		
				case 2: {
					enun = (int)n1 + " - " + (int)n2;
					resul = n1 - n2;
					break;
				}
		
				case 3: {
					enun = (int)n1 + " x " + (int)n2;
					resul = n1 * n2;
					break;
				}
				case 4: {
					enun = (int)n1 + " ÷ " + (int)n2;
					resul = n1 / n2;
					break;
				}
			}
		} while (enunUsados.indexOf(enun) != -1);
		
		enunUsados.add(enun);
		
		Object[] enunResul = {enun, resul};
		
		return enunResul;
	}

	private String[] gerarAlternativas(double correta) {
		
		String alt2 = gerarAlternativa(correta);
		String alt3 = gerarAlternativa(correta);
		String alt4 = gerarAlternativa(correta);
		
		return new String[] {String.valueOf(correta), alt2, alt3, alt4};
	}
	
	private String gerarAlternativa(double correta) {
		
		double alternativa = correta;
		
		Random random = new Random();
		
		while (alternativa == correta) {
			
			double n1 = random.nextInt(100) + 1;
			double n2 = random.nextInt(100) + 1;
			int op = random.nextInt(4) + 1;
			
			switch (op) {
			
				case 1: {
					alternativa = n1 + n2;
					break;
				}
				case 2: {
					alternativa = n1 - n2;
					break;
				}
				case 3: {
					alternativa = n1 * n2;
					break;
				}
				case 4: {
					alternativa = n1 / n2;
				}
			}
		}
		
		return String.valueOf(alternativa);
		
	}
}
