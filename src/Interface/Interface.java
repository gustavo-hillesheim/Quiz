package Interface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import Code.Bizarro;

public class Interface {
	
	private static JLabel lblTimer = new JLabel();
	private static JLabel lblHourGlass = new JLabel();

	public Interface(){
		
		Main();

	}
	
	private static void Main(){
		
		Frame janela = new Frame();
		janela.getContentPane().setBackground(Color.WHITE);
		janela.setPadding(5);
		
		//Logo
		ImageIcon logo = new ImageIcon("src/Interface/img/logo.gif");
		
		JLabel lblLogo = new JLabel(logo);
		lblLogo.setSize(logo.getIconWidth(), logo.getIconHeight());
		
		//UserFace
		JLabel lblUser = setLabelIcon(50, 150, null, "src/Interface/img/user.png", 30, 30);
		
		JTextField txtUser = new JTextField();
		txtUser.setBounds(90, (int) 153.5, 175, 25);
		
		//CategoryFace
		JLabel lblCateg = new JLabel("Categorias:");
		lblCateg.setBounds(50,215,120,25);
		
		Object[] cat = {"Matem�tica", "Bizarro", "Anime"};
		
		JComboBox<String> cbxCateg = new JComboBox(cat);
		cbxCateg.setBounds(50,240,120,25);	
		
		//Clock Enable/Disable
		JLabel lblClock = setLabelIcon(350, 235, "Time", "src/Interface/img/clock.png", 50, 50);
		
		JLabel lblSwitchOff = setLabelIcon(363, 215, "Time", "src/Interface/img/switchOff.png", 20, 13);
		
		JLabel lblSwitchOn = setLabelIcon(363, 215, "Time", "src/Interface/img/switchOn.png", 20, 13);
		lblSwitchOn.setVisible(changeView());
		
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
				
				verifyIfTimer(lblSwitchOn);
				Ranking();
				janela.dispose();
		}}));

		//BeginQuiz
		JLabel lblStart = setLabelIcon(405, 200, "Start", "src/Interface/img/play.png", 100, 100);
		
		lblStart.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				
				verifyIfTimer(lblSwitchOn);
				Question();
				janela.dispose();
		}}));

		janela.add(lblLogo, Frame.MIDDLE_TOP);		
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
	
	static Code.Categoria quest = new Bizarro();
	
	static JPanel pnQuestion = quest.getPergunta().getInterface();
	
	static int indPerg=1;
	
	static int indJump;
	
	private static void Question(){
		
		Frame janela = new Frame();
		janela.getContentPane().setBackground(Color.WHITE);
		janela.setPadding(10);
		
		JLabel lblCamp = new JLabel();
		lblCamp.setOpaque(true);
		lblCamp.setBackground(new Color(202,204,206));
		lblCamp.setSize(700,250);
		
		//Colocar pergunta
		
		
		
		
		//Number of pergunta
		
		//Pular pergunta
		JLabel lblJump = setLabelIcon(450, 255, "Jump", "src/Interface/img/jump.png", 75, 75);
		
		//Ajuda
		JLabel lblHelp = setLabelIcon(320, 255, "Help", "src/Interface/img/flag.png", 80, 80);
		
		//Pr�ximo/Responder
		JLabel lblNext = setLabelIcon(200, 259, "Next", "src/Interface/img/next.png", 65, 65);
		
		//Indice da Pergunta
		JLabel lblIndice = new JLabel(indPerg+" - ");
		lblIndice.setBounds(18,20,45,35);
		lblIndice.setFont(new Font("Gerogean", Font.BOLD, 20));

		lblNext.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
									
				quest.getPergunta().atualizarPanel();
				
				indPerg++;		
				
				if(indPerg != 10){
					lblIndice.setText(indPerg+" - ");
				}else {
					lblIndice.setText(indPerg+"- ");
					indPerg=1;
				}
		}}));
		
		lblJump.addMouseListener(EventLabel(janela, new Inter() {
			
			@Override
			public void run() {
				
				quest.getPergunta().atualizarPanel();
				
				
		}}));
		
		
		//ImgCronometro
		ImageIcon hourglass = new ImageIcon("src/Interface/img/timer.gif");
		
		lblHourGlass.setIcon(hourglass);
		lblHourGlass.setBounds(20,265,hourglass.getIconWidth(), hourglass.getIconHeight());
				
		//CronometroItself
		lblTimer.setText("30:00min");
		lblTimer.setFont(new Font("Georgean", Font.BOLD, 15));
		lblTimer.setBounds(15, 305, 120,25);
		
		Timer(lblTimer);
		
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

	private static void Ranking() {
		
		Frame janela = new Frame();
		janela.getContentPane().setBackground(Color.WHITE);
		janela.setPadding(10);

		//Heanding
		JLabel lblHome = setLabelIcon(0, 0, "Home", "src/Interface/img/house.png", 40, 40);
		
		lblHome.addMouseListener(EventLabel(janela, () -> Main()));
		
		JLabel lblTitle = new JLabel("Ranking");
		lblTitle.setFont(new Font("Georgean", Font.BOLD, 35));
		lblTitle.setBounds(115,10,150,45);
		
		JLabel lblSymbol = setLabelIcon(320, 0, null, "src/Interface/img/grown.png", 50, 50);
		
		//Social Midias
		JLabel lblShare = new JLabel("Compartilhe com seus amigos!");
		lblShare.setBounds(100,400,200,25);
	
		JLabel lblFace = setLabelIcon(130, 415, null, "src/Interface/img/face.png", 45, 45);
		
		JLabel lblTwitter = setLabelIcon(165, 415, null, "src/Interface/img/tt.png", 45, 45);
		
		JLabel lblWpp = setLabelIcon(200, 415, null, "src/Interface/img/wpp.png", 43, 43);
		
		
		janela.add(lblSymbol);
		janela.add(lblTitle);
		janela.add(lblHome);
		janela.add(lblWpp);
		janela.add(lblTwitter);
		janela.add(lblFace);
		janela.add(lblShare);
		janela.setVisible(true);
	}
	
	private static JLabel setLabelIcon(int x, int y, String ToolTip, String icon, int Width, int Height) {
				
		ImageIcon imageIcon = new ImageIcon(icon);
		Image image = imageIcon.getImage().getScaledInstance(Width, Height,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);		
		
		JLabel label = new JLabel(imageIcon);
		label.setBounds(x,y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		label.setToolTipText(ToolTip);
		
		return label;
	}
		
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
	
	private static int currentMIlisecond=30000;
	private static int currentSegundo = 60;
	private static int currentMinuto = 5;
	private static int speed = 1000;
	
	private static void Timer(JLabel label) {
		
		Timer timer;
		
		 ActionListener action = new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	               	      
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
	            }
	        };
	        timer = new Timer(speed, action);  
	        timer.start();
	}
	
	private static boolean changeView() {
		
		boolean valida = true;
		
		if(valida == false) {
			valida = true;
		}else {
			valida=false;
		}
		
		return valida;
	}
	
	private static MouseListener EventLabel(Frame janela, Inter metodo) {
		
		MouseListener action = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				
				metodo.run();
								
			}
		};
		
		return action;
	}
	
	public interface Inter {
	
		public abstract void run();
	}
	
	
}
