package Interface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Code.Anime;
import Code.Bizarro;
import Code.Categoria;
import Code.CustomTableModel;
import Code.Jogador;
import Code.Matematica;
import Code.Pergunta;

public class Interface {
	
	public Interface(){
		
		Main();

	}
	
	private static Categoria theme;
	private static Jogador player;
	private static String User;
	//private static Jogador player = new Jogador(User);
	
	//Painel principal
	private static void Main(){
		
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(531, 349);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.getContentPane().setBackground(Color.WHITE);
		
		//Logo
		ImageIcon logo = new ImageIcon("src/Interface/img/logo.gif");
		
		JLabel lblLogo = setLabelIcon((int) 85.5, 0, null, "src/Interface/img/logo.gif", logo.getIconWidth(), logo.getIconHeight());
		
		//UserFace
		JLabel lblUser = setLabelIcon(50, 150, null, "src/Interface/img/user.png", 30, 30);
		
		JTextField txtUser = new JTextField();
		txtUser.setBounds(90, (int) 153.5, 175, 25);
		
		//CategoryFace
		JLabel lblCateg = new JLabel("Categorias:");
		lblCateg.setBounds(50,215,120,25);
		
		Object[] cat = {"Anime", "Bizarro", "Matematica"};
		
		JComboBox<Object> cbxCateg = new JComboBox<Object>(cat);
		cbxCateg.setBounds(50,240,120,25);	
		
		//Clock Enable/Disable
		JLabel lblClock = setLabelIcon(350, 235, "Time", "src/Interface/img/clock.png", 50, 50);
		
		JLabel lblSwitchOff = setLabelIcon(363, 215, "Time", "src/Interface/img/switchOff.png", 20, 13);
		
		JLabel lblSwitchOn = setLabelIcon(363, 215, "Time", "src/Interface/img/switchOn.png", 20, 13);
		lblSwitchOn.setVisible(changeView());
		
		//Adcionando ação para trocar a imagem do botão com o intuito de parecer que ele está se movimentando
		lblSwitchOff.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				
				lblSwitchOff.setVisible(false);
				lblSwitchOn.setVisible(true);
		}}));
		
		lblSwitchOn.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				
				lblSwitchOn.setVisible(false);
				lblSwitchOff.setVisible(true);
		}}));
	
		//ShowRank
		JLabel lblRank = setLabelIcon(285, 235, "Rank", "src/Interface/img/rank.png", 50, 50);
		
		lblRank.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				//Verifica sem alguem já jogou
				if((Jogador.listarJogadoresSemTempo().size() != 0) || (Jogador.listarJogadoresComTempo().size() != 0)) {
					verifyIfTimer(lblSwitchOn);
					Ranking();
					janela.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Rank sem jogador algum", "ERROR", 0);
				}
		}}));

		//BeginQuiz
		JLabel lblStart = setLabelIcon(405, 200, "Start", "src/Interface/img/play.png", 100, 100);
		
		lblStart.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				
				User = txtUser.getText();
				String categoria = cbxCateg.getSelectedItem().toString();
				
				//Verificação se o jogador está escrito
				if(User.equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o nome do jogador, por favor", "ERROR", 0);
				}else {
					
					if(categoria.equals("Anime")) {
						theme = new Anime();
					}else if(categoria.equals("Bizarro")) {
						theme = new Bizarro();
					}else {
						theme = new Matematica();
					}
					
					player = new Jogador(User);
					verifyIfTimer(lblSwitchOn);
					Question();
					janela.dispose();
				}
		}}));

		janela.add(lblLogo);		
		janela.add(lblUser);
		janela.add(txtUser);
		janela.add(lblCateg);
		janela.add(cbxCateg);
		janela.add(lblStart);
		janela.add(lblClock);
		janela.add(lblSwitchOn);
		janela.add(lblSwitchOff);
		janela.add(lblRank);
	
		janela.setVisible(true);
	}
	
	//Variavéis usadas entre os metodos seguintes
	private static JPanel pnQuestion;
	private static JLabel lblTimer = new JLabel();
	private static JLabel lblHourGlass = new JLabel();
	private static int indPerg=1;
	private static int indHelp=1;
	private static int indJump=3;
	private static int indRight=0;
	private static int indWrong=0;
	private static JLabel lblJump;
	private static JLabel lblJumps;
	private static JLabel lblHelp;
	private static JLabel lblHelps;
	
	//Painel das questões
	private static void Question(){
		
		Pergunta question = theme.getPergunta();
		pnQuestion = question.getInterface();

		indRight=0;
		indWrong=0;
		indHelp=1;
		indJump=3;
		
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(736, 375);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.getContentPane().setBackground(Color.WHITE);
		
		JLabel lblCamp = new JLabel();
		lblCamp.setOpaque(true);
		lblCamp.setBackground(new Color(202,204,206));
		lblCamp.setBounds(10,10,700,240);
		
		//"Botões"
		//Próximo/Responder
		JLabel lblNext = setLabelIcon(180, 265, "Next", "src/Interface/img/next.png", 60, 60);
		
		//Ajuda
		lblHelp = setLabelIcon(295, 260, "Help", "src/Interface/img/flag.png", 75, 75);
		
		//Pular Pergunta
		lblJump = setLabelIcon(412, 260, "Jump", "src/Interface/img/jump.png", 70, 70);
		
		//Marcadores
		//Wrongs
		JLabel lblWrongs = setLabelIcon(640, 295, "Wrongs", "src/Interface/img/sad.png", 25, 25);
		
		JLabel lblShowWrongs = new JLabel(""+indWrong);
		lblShowWrongs.setBounds(680,295,25,25);
		lblShowWrongs.setFont(new Font("Georgean", Font.BOLD, 15));
		lblShowWrongs.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Rights
		JLabel lblRights = setLabelIcon(635, 260, "Rights", "src/Interface/img/hat.png", 35, 35);
		
		JLabel lblShowRights = new JLabel(""+indRight);
		lblShowRights.setBounds(680,268,25,25);
		lblShowRights.setFont(new Font("Georgean", Font.BOLD, 15));
		lblShowRights.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Help
		lblHelps = setLabelIcon(345, 268, "Helps", "src/Interface/img/circle.png", 15, 15);
		lblHelps.setText(""+indHelp);
		lblHelps.setFont(new Font("Georgean", Font.BOLD, 10));
		lblHelps.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Jumps
		lblJumps = setLabelIcon(462, 268, "Jumps", "src/Interface/img/circle.png", 15, 15);
		lblJumps.setText(""+indJump);
		lblJumps.setFont(new Font("Georgean", Font.BOLD, 10));
		lblJumps.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Number of Pergunta
		JLabel lblIndice = new JLabel(indPerg+" ) ");
		lblIndice.setBounds(28,30,45,35);
		lblIndice.setFont(new Font("Georgean", Font.BOLD, 20));

		//ImgCronometro
		ImageIcon hourglass = new ImageIcon("src/Interface/img/timer.gif");
		
		lblHourGlass.setIcon(hourglass);
		lblHourGlass.setBounds(20,267,hourglass.getIconWidth(), hourglass.getIconHeight());
				
		//CronometroItself
		lblTimer.setText("05:00min");
		lblTimer.setFont(new Font("Georgean", Font.BOLD, 15));
		lblTimer.setBounds(15, 305, 120,25);
		
		if(lblTimer.isVisible() == true){
			Timer(janela, lblTimer);
		}
		
		lblNext.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
												
				if(question.algoEscolhido()) {
				
					if(question.validarResposta()) {
						indRight++;
					}else {
						indWrong++;
					}
					
					lblShowWrongs.setText(""+indWrong);
					lblShowRights.setText(""+indRight);
										
					indPerg++;		
				
					//Se for o número for 10 remove um espaço para não sobrepor a label do enunciado
					if(indPerg != 10){
						lblIndice.setText(indPerg+" ) ");
					}else {
						lblIndice.setText(indPerg+") ");
					}
					
					if (indPerg <= 10) {
						
						//Pega outra pergunta
						question.setPergunta(theme.getPergunta().getPergunta());
						question.atualizarPanel();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Escolha pelo menos uma alternativa", "ERROR", 0);
				}
				
				if(indPerg > 10) {
					
					//Verifica se tem tempo ou não e pega as informações requisitadas
					if(lblTimer.isVisible() == true) {
						player.finalizarJogo(indRight, currentTime);
					}else {
						player.finalizarJogo(indRight);
					}
					
					//Reinicia as variáveis
					indPerg=1;
					theme.getPergunta().reiniciar();
					janela.dispose();
					End();
					
				}
		}}));
		
		lblJump.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				if(indJump > 0) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(indJump > 0){
					
					//Pega outra pergunta
					question.setPergunta(theme.getPergunta().getPergunta());
					question.atualizarPanel();
					
					indJump--;
					
					lblJumps.setText(""+indJump);
				}
				
				//Se não ter mais pulos remove a a opção de bottão do label
				if(indJump == 0) {
					
					ImageIcon imageIcon = new ImageIcon("src/Interface/img/jumpoff.png");
					Image image = imageIcon.getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);		
					
					lblJump.setIcon(imageIcon);
					
					janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
					lblJumps.setVisible(false);
					
				}
			}
		});
				
		lblHelp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				if(indHelp == 1) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
								
				if(indHelp > 0){
					
					question.ajuda();
					indHelp--;
				}

				//Se não tem mais ajuda remove a opção de botão do label
				if(indHelp == 0) {
					
					ImageIcon imageIcon = new ImageIcon("src/Interface/img/flagoff.png");
					Image image = imageIcon.getImage().getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(image);		
					
					lblHelp.setIcon(imageIcon);
					
					janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
					lblHelps.setVisible(false);
					
				}
			}
		});
						
		janela.add(lblShowRights);
		janela.add(lblShowWrongs);
		janela.add(lblHelps);
		janela.add(lblJumps);
		janela.add(lblRights);
		janela.add(lblWrongs);
		janela.add(lblIndice);
		janela.add(pnQuestion);
		janela.add(lblJump);
		janela.add(lblHelp);
		janela.add(lblNext);
		janela.add(lblHourGlass);
		janela.add(lblTimer);
		janela.add(lblCamp);
	
		janela.setVisible(true);
	}

	private static CustomTableModel model;
	private static JScrollPane scroll;
	
	//Painel do Ranking
	private static void Ranking() {
				
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(406, 519);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.getContentPane().setBackground(Color.WHITE);

		//Heanding
		JLabel lblHome = setLabelIcon(0, 0, "Home", "src/Interface/img/house.png", 40, 40);
		
		lblHome.addMouseListener(EventLabel(janela, new Inter() {

			@Override
			public void run() {
				
				janela.dispose();
				Main();
			}
		}));
		
		JLabel lblTitle = new JLabel("Ranking");
		lblTitle.setFont(new Font("Georgean", Font.BOLD, 35));
		lblTitle.setBounds(123,10,150,45);
		
		JLabel lblSymbol = setLabelIcon(335, 0, null, "src/Interface/img/grown.png", 50, 50);
		
		//Escolha
		Object[] choices = {"With Time", "Without Time"};
		
		JComboBox<Object> cbxChoice = new JComboBox<Object>(choices);
		cbxChoice.setBounds(135,55,100,20);
		
		//Tabela
		cbxChoice.setSelectedIndex(-1);
									
		cbxChoice.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbxChoice.getSelectedIndex() == 0) {
										
					model = new CustomTableModel(Jogador.listarJogadoresComTempo(), new int[] {1,2,3});
					
					scroll = model.getTable();
					
				}else {
					
					model = new CustomTableModel(Jogador.listarJogadoresSemTempo(), new int[] {1,3});
					
					scroll = model.getTable();
				}
				
				scroll.setBounds(20,85,352,225);
				janela.add(scroll);
			}
		});
						
		//Social Midias
		JLabel lblShare = new JLabel("Compartilhe com seus amigos!");
		lblShare.setBounds(100,400,200,25);
	
		JLabel lblFace = setLabelIcon(130, 415, null, "src/Interface/img/face.png", 45, 45);
		
		JLabel lblTwitter = setLabelIcon(165, 415, null, "src/Interface/img/tt.png", 45, 45);
		
		JLabel lblWpp = setLabelIcon(200, 415, null, "src/Interface/img/wpp.png", 43, 43);
				
		janela.add(lblSymbol);
		janela.add(lblTitle);
		janela.add(lblHome);
		janela.add(cbxChoice);
		janela.add(lblWpp);
		janela.add(lblTwitter);
		janela.add(lblFace);
		janela.add(lblShare);
		janela.setVisible(true);
	}
	
	//Painel final
	private static void End() {
				
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel();
		
		ImageIcon image = new ImageIcon();
		
		if(currentTime == 300) {
			image = new ImageIcon("src/Interface/img/timegone.gif");
		
			label = setFinalImage(janela, image, "<html><center>TIME<br>IS<br>GONE!</center></html>", 35, 0, 59, 111, () -> Main());
		}else {
	
			if(indRight < 2) {
				image = new ImageIcon("src/Interface/img/Right1.gif");
			
				label = setFinalImage(janela, image, "<html><center>TA<br>FODA!</center></html>", 45, 255, 113, 181, () -> Main());
			}else if((indRight == 2) || (indRight == 3)) {
			
				image = new ImageIcon("src/Interface/img/Right2.gif");
			
				label = setFinalImage(janela, image, "", 35, 0, 59, 111, () -> Main());
			}else if((indRight == 4) || (indRight == 5)) {
			
				image = new ImageIcon("src/Interface/img/Right3.gif");
			
				label = setFinalImage(janela, image, "<html><center>JÁ<br>VI<br>MELHORES!</center></html>", 35, 0, 59, 111, () -> Main());
			}else if((indRight == 6) || (indRight == 7)) {
			
				image = new ImageIcon("src/Interface/img/Right4.gif");
			
				label = setFinalImage(janela, image, "<html><center>JÁ<br>VI<br>PIOR!</center></html>", 35, 0, 59, 111, () -> Main());
			}else if((indRight == 8) || (indRight == 9)){
			
				image = new ImageIcon("src/Interface/img/Right5.gif");
			
				label = setFinalImage(janela, image, "<html><center>É<br>NÓS!</center></html>", 35, 0, 59, 111, () -> Main());
			}else {
			
				image = new ImageIcon("src/Interface/img/Right6.gif");
			
				label = setFinalImage(janela, image, "<html><center>TU<br>ÉS<br>FODA!</center></html>", 35, 0, 59, 111, () -> Main());
			}
		}
		
		
		janela.setSize(image.getIconWidth(), image.getIconHeight());
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		 			
		
		janela.add(label);
		
		janela.setVisible(true);
	}
	
	//Criador imagem de fundo com ação de fechamento automatico de tela
	private static JLabel setFinalImage(JFrame janela, ImageIcon image, String txt, int TamFont, int r, int g, int b, Inter metodo) {
		
		JLabel label = new JLabel(image);
		label.setText(txt);
		label.setFont(new Font("Georgean", Font.BOLD, TamFont));
		label.setForeground(new Color(r,g,b));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setBounds(0,0,image.getIconWidth(), image.getIconHeight());
		
		janela.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				Timer timer = new Timer(3000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		              janela.dispose();
		            }
		          });
		          timer.setRepeats(false);
		          timer.start();
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				metodo.run();
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
				
			}
		});
		
		return label;
	}
	
	//Criador de botão usando label com icone
	private static JLabel setLabelIcon(int x, int y, String ToolTip, String icon, int Width, int Height) {
				
		ImageIcon imageIcon = new ImageIcon(icon);
		Image image = imageIcon.getImage().getScaledInstance(Width, Height,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);		
		
		JLabel label = new JLabel(imageIcon);
		label.setBounds(x,y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		label.setToolTipText(ToolTip);
		
		return label;
	}
	
	//Verificação se vai ter timer ou não
	private static void verifyIfTimer(JLabel lblSwitchOn) {
		
		boolean valida;
		
		if(lblSwitchOn.isVisible() == true) {
			
			valida = true;
			
		}else {
			
			valida = false;
		}

		lblHourGlass.setVisible(valida);
		lblTimer.setVisible(valida);
	}
	
	//Variaveis usadas nos metodos abaixo
	private static Timer timer;
	private static int currentTime;
	private static int currentSegundo;
	private static int currentMinuto;
	private static int speed;
	
	//Timer
	private static void Timer(JFrame janela, JLabel label) {
		
		currentTime = 0;
		currentSegundo = 60;
		currentMinuto = 5;
		speed = 1000;
		
		timer = new Timer(speed, new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	               	      	            	            	
	            	if(((currentMinuto != 0) || (currentSegundo != 0)) && (janela.isShowing() == true)) {
	            		
	            		currentTime++;
	            		
	            		if(currentSegundo == 0) {
	            			currentSegundo = 60;
	            		}
	            	
	            		if(currentSegundo == 60){
	            			currentMinuto--;
	            		}
	            		
	            		currentSegundo--;
	                	               
	            		String min = currentMinuto <= 9? "0"+currentMinuto:currentMinuto+"";
	            		String seg = currentSegundo <= 9? "0"+currentSegundo:currentSegundo+"";
	                
	            		label.setText(min+":"+seg+"min");
	            	}else {
	            		timer.stop();
	              	}
	            	
	            	if((timer.isRunning() == false) && (janela.isShowing() == true)) {
	            		janela.dispose();
	            		End();
	            	}
	            	
	            }
	        });
	        timer.start();
		
	}
	
	//Mudar o botão do timer
	private static boolean changeView() {
		
		boolean valida = true;
		
		if(valida == false) {
			valida = true;
		}else {
			valida=false;
		}
		
		return valida;
	}
	
	//Ação para que o cursor do mouse mude e para executar uma acao ao clicar
	private static MouseListener EventLabel(JFrame janela, Inter metodo) {
		
		MouseListener action = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {

				metodo.run();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				janela.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
								
			}
		};
		
		return action;
	}
	
	//Complemento do de cima
	public interface Inter {
	
		public abstract void run();
	}
	
	
}
