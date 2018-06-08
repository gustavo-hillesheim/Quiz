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
		int operacao = (Integer) enunResul[2];
		
		String[] alternativas = gerarAlternativas(resultado, operacao);
		
		Pergunta p = new Pergunta(enunciado, alternativas);
		
		return p;
	}
	
	public Object[] gerarEnunciado() {
		
		Random random = new Random();
		
		String enun = "";
		double resul = 0;
		int op = 0;
		
		do {
			
			//Gera dois n�meros aleat�rios
			double n1 = random.nextInt(100) + 1;
			double n2 = random.nextInt(100) + 1;
			
			//Gera uma opera��o aleat�rio
			op = random.nextInt(4) + 1;
			
			//Verifica qual opera��o foi randomizada e faz o c�lculo da resposta
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
		//Verifica se a quest�o j� foi randomizada antes
		} while (enunUsados.indexOf(enun) != -1);
		
		enunUsados.add(enun);
		
		String resulFormat = String.format("%.2f", resul).replace(',', '.');
		
		Object[] enunResul = {enun, Double.parseDouble(resulFormat), op};
		
		return enunResul;
	}

	private String[] gerarAlternativas(double correta, int operacao) {
		
		String alt2 = gerarAlternativa(correta, operacao);
		String alt3 = gerarAlternativa(correta, operacao);
		String alt4 = gerarAlternativa(correta, operacao);
		
		//Verifica se a parte inteira da resposta � igual a parte real dela
		String corretaFormat = (int) correta == correta ? String.valueOf((int) correta) : String.valueOf(correta);
		
		return new String[] {corretaFormat, alt2, alt3, alt4};
	}
	
	private String gerarAlternativa(double correta, int operacao) {
		
		double alternativa = correta;
		
		Random random = new Random();
		
		while (alternativa == correta) {
			
			//Gera 2 n�meros aleat�rios
			double n1 = random.nextInt(100) + 1;
			double n2 = random.nextInt(100) + 1;
			
			//Verifica qual opera��o foi gerada e faz o c�lculo do resultado
			switch (operacao) {
			
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
			
			String altFormat = String.format("%.2f", alternativa).replace(',', '.');
			
			alternativa = Double.parseDouble(altFormat);
		}
		
		//Verifica se a parte inteira do resultado � igual a parte real
		return (int) alternativa == alternativa ? String.valueOf((int) alternativa) : String.valueOf(alternativa);	
	}
}