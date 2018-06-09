package Code;

import java.util.Random;

public class Anime extends Categoria {
	
	//Array de perguntas
	private static String [] enunciados = {
		"Qual o nome do protagonista de Cowboy Bebop?",
		"Qual a fonte dos poderes sobrenaturais dos personagens no universo de One Piece?",
		"Em Full Metal Alchemist, que artefato os irmãos Elric buscam para recuperarem seus corpos?",
	    "Em Dragon Ball Z, qual personagem casou com a Andróide #18?",
	    "Como é conhecido o grupo de Cavaleiros Sagrados protagonistas em Nanatsu no Taizai?",
	    "No anime Trigun,  qual o motto do personagem Vash? ",
	    "Como são chamados os robôs gigantes em Evangelion?",
	    "Em Dragon Ball, Goku é de uma raça de alienígenas superpoderosa. Qual o nome dessa raça?",
	    "O caderno em Death Note garante ao seu usuário o poder de uma entidade sobrenatural. Que entidade é esta? ",
	    "Em Mob Psycho 100, as pessoas com poderes são conhecidas como?",
	    "Em Fullmetal Alchemist, os Ouroboros são?",
	    "Qual o sonho do personagem Naruto (série homônima)?",
	    "Qual é o nome da terceira temporada da série Digimon?",
	    "Na primeira temporada de Pokemon, qual é o nome da organização criminosa que atua na região?",
	    "Em Dragon Ball é necessário reunir as Esferas do Dragão para que Shenlong apareça. Quantas são?",
	    "Em que local se passam as aventuras de One Piece?",
	    "Em Kiseijuu (Parasyte), em que parte de do corpo de Shinichi fica o alienígena Migi? ",
	    "Qual o nome do golpe assinatura do personagem All Might no anime Boku no Hero Academia?",
	    "Como são chamadas as transformações do personagem Monkey D. Luffy no anime One Piece?",
	    "Em uma luta entre Saitama de One Punch Man e Goku de Dragon Ball, quem seria o vencedor? "};
	
	//Array de respostas
	private static String[][] respostas = {
		{"Spike","Smite","Sprite","Strike"},
		{"Akuma no Mi","Death Note","Esferas do Dragão","Digivice"},
		{"Pedra Filosofal","A Varinha das Varinhas","Pomo de Ouro","Cálice de Fogo"},
		{"Kuririn", "Curupira", "Caipora", "Curumim"},
		{"Os Sete Pecados Capitais","Os Dez Mandamentos","As Dez Pragas do Egito","Os Quatro Cavaleiros do Apocalipse"},
		{"Paz e amor","Liberdade, igualdade e fraternidade","Orgulho e preconceito","Sexo, drogas e rock'n roll"},
		{"EVAs","Jaeger","Megazord","Gundam"},
		{"Saiyajins","Klingon","Grey","Kryptonianos"},
		{"Shinigami","Shenlong","Digimon","Bijuu"},
		{"Paranormais","Cavaleiros Sagrados","Dobradores","Digi-Escolhidos"},
		{"Homunculos","Hollow","Uchihas","Marinheiros"},
		{"Ser o Hokage","Virar um Mestre Pokemon","Ser o Shaman King","Ser Rei dos Piratas"},
		{"Digimon Tamers","Digimon Adventure","Digimon Data Squad","Digimon Frontier"},
		{"Team Rocket","Team Galatic","Team Skull","Team Plasma"},
		{"7","14","8","64"},
		{"Grand Line","Digimundo","Upside Down","Azeroth"},
		{"Mão Direita","Cabeça","Perna Esquerda","Tórax"},
		{"Detroit Smash","Kamehameha","Choque do Trovão","Gomu Gomu no Red Hawk"},
		{"Gear","Evolução","Digievolução","Super Sayijin"},
		{"Saitama","Goku","Empate","Nenhum dos dois, pois o universo seria destruído durante a luta"},
	};
	  
	// instância estática usada para gerar as perguntas
	private static Anime anime = new Anime();
	
	// Índice da última pergunta retirada do array
	private static int indicePergunta = -1;
	
	// Inicializa o array de perguntas
	static {
		for (int ix = 0; ix < enunciados.length; ix++) {
			anime.addPergunta(enunciados[ix], respostas[ix]);
		}
	}
	
	// Retorna uma pergunta do array de perguntas
	public Pergunta getPergunta() {
	    indicePergunta++;
	return anime.perguntas[indicePergunta];
	}
	
	// Embaralha as perguntas no array de perguntas
	// Algoritmo de Fischer-Yates
	private void embaralharPerguntas() {
		int index;
	    Pergunta temp;
	    Random random = new Random();
	    
	    for (int i = perguntas.length - 1; i > 0; i--) {
	    	index = random.nextInt(i + 1);
	    	temp = perguntas[index];
	    	perguntas[index] = perguntas[i];
	    	perguntas[i] = temp;
	    }
	    indicePergunta = -1;
	}
	
	// Construtor da classe. O que ele faz de fato é embaralhar a lista de
	// perguntas toda vez que uma nova instância é criada
	public Anime() {
		if(!(anime == null)){
			anime.embaralharPerguntas();
		}
	}
}
