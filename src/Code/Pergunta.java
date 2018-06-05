package Code;

public class Pergunta {

	String enunciado;
	String correta;
	String[] alternativas;
	
	public Pergunta(String enunciado, String[] alternativas) {
		
		this.enunciado = enunciado;
		this.correta = alternativas[0];
		this.alternativas = alternativas;
	}
	
}
