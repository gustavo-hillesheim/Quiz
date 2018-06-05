package Code;

public abstract class Categoria {

	protected Pergunta[] perguntas = new Pergunta[20];
	private int index = 0;
	
	public abstract Pergunta getPergunta();
	
	protected void addPergunta(String enunciado, String[] alternativas) {
		
		this.perguntas[index]= new Pergunta(enunciado, alternativas);
		
		index++;
	}
}
