package Code;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Pergunta {

	private String enunciado;
	private String correta;
	private String[] alternativas;

	private static JTextArea lblTitulo;
	private static JRadioButton[] buttons = new JRadioButton[4];
	private static ButtonGroup radioGroup;

	public Pergunta(String enunciado, String[] alternativas) {

		this.enunciado = enunciado;
		this.correta = alternativas[0];
		this.alternativas = alternativas;
	}
	
	public void setPergunta(String[][] info) {

		String enunciado = info[0][0];
		String[] alternativas = info[1];
		
		this.enunciado = enunciado;
		this.correta = alternativas[0];
		this.alternativas = alternativas;
	}
	
	public String[][] getPergunta() {
		
		//Retorna as informações da pergunta
		return new String[][] {new String[] {this.enunciado}, this.alternativas};
	}

	private String getEnunciado() {

		return this.enunciado;
	}

	public String[] getAlternativas() {

		return embaralharAlternativas();
	}

	public boolean validarResposta(String resposta) {

		return resposta.equals(correta);
	}

	private String[] embaralharAlternativas() {

		String[] alternativas = new String[this.alternativas.length];

		// Arraylist que vai ser utilizado para que verificar quais indexes jï¿½
		// foram utilizados
		ArrayList<Integer> indexUsados = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < alternativas.length; i++) {

			int index = 0;

			// Randomizando index atï¿½ que encontre algum que nï¿½o foi utilizado
			do {

				index = random.nextInt(alternativas.length);
			} while (indexUsados.indexOf(index) != -1);

			indexUsados.add(index);

			alternativas[i] = this.alternativas[index];
		}

		return alternativas;
	}

	public void ajuda() {
		
		Random random = new Random();
		
		ArrayList<Integer> indexes = new ArrayList<>();
		
		int times = random.nextInt(3) + 1;
		
		for (int i = 0; i < times; i++) {
			
			int index = 0;
			
			do {
				
				index = random.nextInt(4);
			} while ((indexes.indexOf(index) != -1) || (buttons[index].getText().equals(correta)));
			
			buttons[index].setVisible(false);
		}
	}
	
	public void reiniciar() {
		
		lblTitulo = null;
		
		for (JRadioButton btn : buttons) {
			
			btn = null;
		}
	}
	
	public void atualizarPanel() {

		// Iniciando e configurando label da pergunta
		lblTitulo.setText(getEnunciado());
		lblTitulo.setBounds(50, 25, 610, 75);
		lblTitulo.setFont(new Font("Georgean", Font.BOLD, 20));
		lblTitulo.setBackground(new Color(202, 204, 206));
		lblTitulo.setEditable(false);
		lblTitulo.setLineWrap(true);
		lblTitulo.setWrapStyleWord(true);
		lblTitulo.setVisible(true);
		
		String[] alternativas = getAlternativas();

		radioGroup.clearSelection();
		
		// Iniciando os botÃµes
		for (int i = 0; i < 4; i++) {

			buttons[i].setText(alternativas[i]);
			buttons[i].setLocation(95, 65 + 30 * (i + 1));
			buttons[i].setFont(new Font("Georgean", Font.BOLD, 13));

			int btnWidth = (int) buttons[i].getPreferredSize().getWidth();
			int btnHeight = (int) buttons[i].getPreferredSize().getHeight();

			buttons[i].setSize(btnWidth, btnHeight);
			buttons[i].setOpaque(false);
			buttons[i].setVisible(true);
		}
	}

	public JPanel getInterface() {

		// Iniciando PainÃ©l onde ficarão os componentes
		JPanel pane = new JPanel();
		pane.setBounds(15, 10, 700, 250);
		pane.setLayout(null);
		pane.setOpaque(false);

		if (lblTitulo == null) {

			// Iniciando e configurando label da pergunta
			lblTitulo = new JTextArea();

			// Grupo para que os radio buttons funcionem direito
			radioGroup = new ButtonGroup();

			for (int i = 0; i < 4; i++) {

				buttons[i] = new JRadioButton();

				radioGroup.add(buttons[i]);
				pane.add(buttons[i]);
			}

			pane.add(lblTitulo);
		}

		atualizarPanel();

		return pane;
	}

	public boolean algoEscolhido() {
		
		for (JRadioButton btn : buttons) {
			
			if (btn.isSelected()) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean validarResposta() {
		
		// Passa por todos os Radio Buttons
		for (JRadioButton btn : buttons) {		
			
			// Verifica se o botï¿½o estï¿½ selecionado
			if (btn.isSelected()) {

				// Verifica se o texto do botão é igual à resposta correta
				if (btn.getText().equals(correta)) {

					return true;
				}
			}
		}

		return false;
	}
}