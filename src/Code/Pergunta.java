package Code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Pergunta {

	private String enunciado;
	private String correta;
	private String[] alternativas;
	
	private JLabel lblTitulo;
	private JRadioButton[] buttons = new JRadioButton[4];
	
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
		
		//Arraylist que vai ser utilizado para que verificar quais indexes já foram utilizados
		ArrayList<Integer> indexUsados = new ArrayList<>();
		
		Random random = new Random();
		
		for (int i = 0; i < alternativas.length; i++) {
			
			int index = 0;
		
			//Randomizando index até que encontre algum que não foi utilizado
			do {
				
				index = random.nextInt(alternativas.length);
			} while (indexUsados.indexOf(index) != -1);
			
			indexUsados.add(index);
			
			alternativas[i] = this.alternativas[index];
		}
		
		return alternativas;
	}

	public JPanel getInterface() {
		
		//Iniciando Painél onde ficarão os componentes
		JPanel pane = new JPanel();
		pane.setSize(400, 300);
		pane.setLayout(null);
		
		//Iniciando e configurando label da pergunta
		lblTitulo = new JLabel(getEnunciado());
		lblTitulo.setLocation(15,  15);
		
		int lblWidth = (int) lblTitulo.getPreferredSize().getWidth();
		int lblHeight = (int) lblTitulo.getPreferredSize().getHeight();
		
		lblTitulo.setSize(lblWidth, lblHeight);
		
		pane.add(lblTitulo);
		
		//Grupo para que os radio buttons funcionem direito
		ButtonGroup group = new ButtonGroup();
		
		String[] alternativas = getAlternativas();
		
		//Iniciando os botões 
		for (int i = 0; i < 4; i++) {
			
			buttons[i] = new JRadioButton(alternativas[i]);
			buttons[i].setLocation(15, 30 + 30 * (i + 1));
			
			int btnWidth = (int) buttons[i].getPreferredSize().getWidth();
			int btnHeight = (int) buttons[i].getPreferredSize().getHeight();
			
			buttons[i].setSize(btnWidth, btnHeight);
			
			group.add(buttons[i]);
			pane.add(buttons[i]);
		}
		
		//Botão para exemplo
		JButton btn = new JButton("VALIDAR");
		btn.setBounds(150, 200, 100, 25);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(validarResposta());
			}
		});
	
		pane.add(btn);
		
		return pane;
	}

	public boolean validarResposta() {
		
		//Passa por todos os Radio Buttons
		for (JRadioButton btn : buttons) {
			
			//Verifica se o botão está selecionado
			if (btn.isSelected()) {
				
				//Verifica se o texto do botão é igual à resposta correta
				if (btn.getText().equals(correta)) {
					
					return true;
				}
			}
		}
		
		return false;
	}
}