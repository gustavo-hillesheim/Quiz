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
		
		if (resposta.equals(correta)) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	private String[] embaralharAlternativas() {
		
		String[] alternativas = new String[this.alternativas.length];
		ArrayList<Integer> indexUsados = new ArrayList<>();
		
		Random random = new Random();
		
		for (int i = 0; i < alternativas.length; i++) {
			
			int index = 0;
			
			do {
				
				index = random.nextInt(alternativas.length);
			} while (indexUsados.indexOf(index) != -1);
			
			indexUsados.add(index);
			
			alternativas[i] = this.alternativas[index];
		}
		
		return alternativas;
	}

	public JComponent[] getInterface() {
		
		JComponent[] comps = new JComponent[5];
		
		JPanel pane = new JPanel();
		pane.setSize(400, 300);
		pane.setLayout(null);
		
		comps[0] = pane;
		
		lblTitulo = new JLabel(getEnunciado());
		lblTitulo.setLocation(15,  15);
		
		int lblWidth = (int) lblTitulo.getPreferredSize().getWidth();
		int lblHeight = (int) lblTitulo.getPreferredSize().getHeight();
		
		lblTitulo.setSize(lblWidth, lblHeight);
		
		pane.add(lblTitulo);
		
		ButtonGroup group = new ButtonGroup();
		
		String[] alternativas = getAlternativas();
		
		for (int i = 0; i < 4; i++) {
			
			buttons[i] = new JRadioButton(alternativas[i]);
			buttons[i].setLocation(15, 30 + 30 * (i + 1));
			
			int btnWidth = (int) buttons[i].getPreferredSize().getWidth();
			int btnHeight = (int) buttons[i].getPreferredSize().getHeight();
			
			buttons[i].setSize(btnWidth, btnHeight);
			
			comps[i+1] = buttons[i];
			
			group.add(buttons[i]);
			pane.add(buttons[i]);
		}
		
		JButton btn = new JButton("VALIDAR");
		btn.setBounds(150, 200, 100, 25);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(validarResposta());
			}
		});
	
		pane.add(btn);
		
		return comps;
	}

	public boolean validarResposta() {
		
		for (JRadioButton btn : buttons) {
			
			if (btn.isSelected()) {
				
				if (btn.getText().equals(correta)) {
					
					return true;
				}
			}
		}
		
		return false;
	}
}