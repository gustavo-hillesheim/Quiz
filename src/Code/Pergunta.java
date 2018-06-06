package Code;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Pergunta {

	private String enunciado;
	private String correta;
	private String[] alternativas;

	private static JTextArea lblTitulo;
	private static JRadioButton[] buttons = new JRadioButton[4];

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

		return resposta.equals(correta);
	}

	private String[] embaralharAlternativas() {

		String[] alternativas = new String[this.alternativas.length];

		// Arraylist que vai ser utilizado para que verificar quais indexes jï¿½ foram
		// utilizados
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
	
	public void atualizarPanel() {

		// Iniciando e configurando label da pergunta
		lblTitulo.setText(getEnunciado());
		lblTitulo.setBounds(50, 25, 610, 25);
		lblTitulo.setFont(new Font("Gerogean", Font.BOLD, 20));
		lblTitulo.setBackground(new Color(202, 204, 206));
		lblTitulo.setEditable(false);
		lblTitulo.setLineWrap(true);
		lblTitulo.setWrapStyleWord(true);

		String[] alternativas = getAlternativas();

		// Iniciando os botÃµes
		for (int i = 0; i < 4; i++) {

			buttons[i].setText(alternativas[i]);
			buttons[i].setLocation(95, 65 + 30 * (i + 1));
			buttons[i].setFont(new Font("Georgean", Font.BOLD, 13));
			buttons[i].setLocation(15, 30 + 30 * (i + 1));

			int btnWidth = (int) buttons[i].getPreferredSize().getWidth();
			int btnHeight = (int) buttons[i].getPreferredSize().getHeight();

			buttons[i].setSize(btnWidth, btnHeight);
			buttons[i].setOpaque(false);
		}
	}

	public JPanel getInterface() {

		// Iniciando PainÃ©l onde ficarÃ£o os componentes
		JPanel pane = new JPanel();
		pane.setBounds(0, 0, 700, 250);
		pane.setLayout(null);
		pane.setOpaque(false);

		if (lblTitulo == null) {

			// Iniciando e configurando label da pergunta
			lblTitulo = new JTextArea();

			// Grupo para que os radio buttons funcionem direito
			ButtonGroup group = new ButtonGroup();

			for (int i = 0; i < 4; i++) {

				buttons[i] = new JRadioButton();

				group.add(buttons[i]);
				pane.add(buttons[i]);
			}

			pane.add(lblTitulo);
		}

		atualizarPanel();

		return pane;
	}

	public boolean validarResposta() {

		// Passa por todos os Radio Buttons
		for (JRadioButton btn : buttons) {

			// Verifica se o botï¿½o estï¿½ selecionado
			if (btn.isSelected()) {

				// Verifica se o texto do botï¿½o ï¿½ igual ï¿½ resposta correta
				if (btn.getText().equals(correta)) {

					return true;
				}
			}
		}

		return false;
	}
}