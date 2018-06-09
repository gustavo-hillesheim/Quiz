package Code;

import java.util.Random;

public class Bizarro extends Categoria {

  // Array de enunciados
  private static String[] enunciados = {
      "O que o imperador Claus permitiu que as pessoas fizessem num banquete, por questões de saúde pública?",
      "Por 150 reais, você pode obter uma licença para ser o que no Texas?",
      "O que é ilegal trazer num duelo no Massachusetts?",
      "Por lei, o que é proibido nos restaurantes do Japão?",
      "De acordo com um censo de 2000 no reino unido, o que agora é considerado uma religião?",
      "Que odor causa um desejo de tentar sadomasoquismo em 43% das mulheres?",
      "Em determinadas áreas da China, o que é considerado uma iguaria tradicional?",
      "O que significa o nome Eskimó?",
      "Estudantes na universidade de Brigham precisam de licensa médica para quê?",
      "Qual o plural oficial de prius, determinado pela toyota em uma votação online?",
      "Qual a quantidade de BELEZA necessária para inaugurar um navio? (oi?)",
      "Qual dessas frases foi dita por Karl Marx?",
      "Qual a soma de todos os números em uma roleta?", "Quantos mamilos tem um gambá?",
      "O que cavalos não conseguem fazer?", "O que mulas não conseguem fazer?",
      "Qual dos grupos de facebook abaixo NÃO existe?",
      "Qual seria a cor original da Coca se não fosse adicionado corante?",
      "Qual a raça de cachorro mais burra?", "Qual a única comida que não estraga?" };

  // Arrays de alternativas
  private static String[][] respostas =
          { { "Peidar", "Arrotar", "Espirrar", "Comer de pé" },
              { "Caçador de animais mortos", "Assassino de corvos", "Testador de carnes",
                  "Fiscalizador de prostituição" },
              { "Pistolas de água", "Espadas de plástico", "Um cachorro", "Sua mãe" },
              { "Dar gorjetas", "Flertar com garçons e garçonetes", "Tirar foto de comida",
                  "Falar alto" },
              { "Cavaleiros Jedi", "Mitologia nórdica", "Pizza", "Cthulhu" },
              { "Baunilha", "Vinho", "Lavanda", "Sangue" },
              { "Urina de meninos virgens em ovos cozidos", "Fezes de turistas", "Sua mãe",
                  "Arroz com polvo vivo" },
              { "Comedor de carne crua", "Construtor de iglus", "Matador de focas",
                  "Vizinho de urso polar" },
              { "Crescer barba", "Transar", "Beber", "Estudar menos de 5 horas por dia" },
              { "Prii", "Prios", "Prius", "Pringles" },
              { "1 milliHelen", "1 milliMarinaRuyBarbosa", "1 milliDH", "1 milliAfrodite" },
              { "Tudo que sei é que não sou marxista", "Cheira minha virilha",
                  "Eu estou bêbado e você é feia, mas quando eu acordar eu estarei sóbrio",
                  "Pessoas pelo mundo todo me vêem como um líder espiritual" },
              { "666", "69", "42", "100" }, { "13", "1", "0", "2" },
              { "Vomitar", "Mais cavalos", "Deitar", "Viver sem cuidados humanos" },
              { "Mais mulas", "Deitar", "Viver sem cuidados humanos", "Segurar urina" },
              { "Penis Day: Um grupo que se reúne para celebrar o falo, deixando eles à mostra",
                  "Boobquake: Um grupo que quer provar que peitos não causam terremotos",
                  "Benjyo Soujer: Um grupo que se encontra para limpar banheiros públicos",
                  "Accomplishing Something Before the Microwave Reaches :00" },
              { "Verde", "Sua mãe", "Preto mesmo", "Transparente" },
              { "Galgo Afegão", "Poddle", "Pug", "A do meu" },
              { "Mel", "Manteiga de cabra", "Tapioca", "Carne de soja" } };

  // instância estática usada para gerar as perguntas
  private static Bizarro bizarro = new Bizarro();
  // Índice da última pergunta retirada do array
  private static int indicePergunta = -1;

  // Inicializa o array de perguntas
  static {
    for (int ix = 0; ix < enunciados.length; ix++) {
      bizarro.addPergunta(enunciados[ix], respostas[ix]);
    }
  }

  // Retorna uma pergunta do array de perguntas
  public Pergunta getPergunta() {
    indicePergunta++;
    return bizarro.perguntas[indicePergunta];
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
  // perguntas toda vez que uma nova instância é criada.
  public Bizarro() {
    if (!(bizarro == null)) {
      bizarro.embaralharPerguntas();
    }
  }

}
