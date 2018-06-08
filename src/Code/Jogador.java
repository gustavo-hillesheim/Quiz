package Code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Jogador {

  // Lista de jogadores com tempo cadastrado e sem
  private static List<Jogador> listaComTempo = new ArrayList<>();
  private static List<Jogador> listaSemTempo = new ArrayList<>();
  // Comparador usado para ordenar a lista de jogadores
  private static Comparator<Jogador> comparadorJogador;
  private String nome;
  private Integer numAcertos;
  private Integer tempo; // Tempo que o jogador demorou no jogo.

  // Inicializa o comparador
  static {
    // Instrui o comparador para primeiro comparar pelo número de acertos e em
    // seguida pelo tempo de jogo.
    comparadorJogador = (jogador1, jogador2) -> jogador2.numAcertos.compareTo(jogador1.numAcertos);
    comparadorJogador = comparadorJogador.thenComparing(
            (jogador1, jogador2) -> jogador1.tempo.compareTo(jogador2.tempo));
  }

  // Retorna uma lista ordenada dos jogadores com tempo. A lista é ordenada
  // primeiro pelo número de acertos e em seguida pelo tempo que o jogador
  // levou. Jogadores que não têm um tempo cadastrado ficam no final da lista.
  public static List<Jogador> listarJogadoresComTempo() {
    List<Jogador> jogadores = listaComTempo;
    // Ordena a lista.
    jogadores.sort(comparadorJogador);

    return jogadores;
  }

  public static List<Jogador> listarJogadoresSemTempo() {
    List<Jogador> jogadores = listaSemTempo;
    // Ordena a lista.
    jogadores.sort(comparadorJogador);

    return jogadores;
  }

  // Métodos para quando o jogador termina o quizz. Registra o desempenho dele
  public void finalizarJogo(Integer numAcertos, Integer tempo) {
    this.numAcertos = numAcertos;
    this.tempo = tempo;
    listaComTempo.add(this);
  }

  // Quando o jogador não jogou com o timer ligado, acionar esse
  public void finalizarJogo(Integer numAcertos) {
    this.numAcertos = numAcertos;
    this.tempo = 0;
    listaSemTempo.add(this);
  }

  // Getters
  public String getNome() {
    return nome;
  }

  public Integer getNumAcertos() {
    return numAcertos;
  }

  public Integer getTempo() {
    return tempo;
  }
  
  public String getTempoString() {
	  
	  return converterTempo();
  }
  
  private String converterTempo() {
	  
	  int minutos = ((int) this.tempo) / 60;
	  String min = String.format("%02d", minutos);
	  String seg = String.format("%02d", this.tempo - (minutos * 60));
	  
	  return min + ":" + seg;
  }

  // O construtor do jogador usa apenas o nome
  public Jogador(String nome) {
    this.nome = nome;
  }
  
  public Object[] getInfo() {
	  
	  return new Object[] {getNome(), getTempoString(), getNumAcertos()};
  }

}
