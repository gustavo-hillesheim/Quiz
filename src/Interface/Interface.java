package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Interface {

	public Interface(){
		
		Question();
	}
	
	private static void Main(){
		
		Frame janela = new Frame();
		janela.setPadding(5);
		janela.setRowMargin(20);
		
		Image image;
		Image newimg;
		
		ImageIcon logo = new ImageIcon("src/Interface/logo.gif");
		
		JLabel lblLogo = new JLabel(logo);
		lblLogo.setSize(logo.getIconWidth(), logo.getIconHeight());
		
		ImageIcon user = new ImageIcon("src/Interface/user.png");
		image = user.getImage();
		newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); 
		user = new ImageIcon(newimg);
		
		JLabel lblUser = new JLabel(user);
		lblUser.setBounds(50,175, user.getIconWidth(), user.getIconHeight());
		
		JTextField txtUser = new JTextField();
		txtUser.setBounds(85, 0, 175, 25);
		
		JLabel lblCateg = new JLabel("Categorias:");
		lblCateg.setBounds(50,215,120,25);
		
		Object[] cat = {
				"Matemática",
				"Bizarro",
				"Anime"
		};
		
		JComboBox<String> cbxCateg = new JComboBox(cat);
		cbxCateg.setBounds(50,240,120,25);
		
		ImageIcon clock = new ImageIcon("src/Interface/clock.png");
		image = clock.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		clock = new ImageIcon(newimg);		
		
		JLabel lblClock = new JLabel(clock);
		lblClock.setBounds(350,235, clock.getIconWidth(), clock.getIconHeight());
		lblClock.setToolTipText("Time");
		
		ImageIcon rank = new ImageIcon("src/Interface/rank.png");
		image = rank.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		rank = new ImageIcon(newimg);		
		
		JLabel lblRank = new JLabel(rank);
		lblRank.setBounds(285,235, rank.getIconWidth(), rank.getIconHeight());
		lblRank.setToolTipText("Rank");
		
		ImageIcon start = new ImageIcon("src/Interface/play.png");
		image = start.getImage();
		newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
		start = new ImageIcon(newimg);		
		
		JLabel lblStart = new JLabel(start);
		lblStart.setBounds(405,200, start.getIconWidth(), start.getIconHeight());
		lblStart.setToolTipText("Start");
		
		janela.add(lblLogo, Frame.MIDDLE_TOP);
		janela.addAtRow(lblLogo, 1);		
		janela.addAtRow(lblUser, 2);
		janela.addAtRow(txtUser, 2);
		janela.add(lblCateg);
		janela.add(cbxCateg);
		janela.add(lblStart);
		janela.add(lblClock);
		janela.add(lblRank);
	
		janela.setVisible(true);
	}
	
	private static void Question(){
		
		Frame janela = new Frame();
		janela.setPadding(15);
		janela.setRowMargin(20);
		
		JLabel lblCamp = new JLabel();
		lblCamp.setOpaque(true);
		lblCamp.setBackground(Color.LIGHT_GRAY);
		lblCamp.setSize(700,250);
		
		Image image;
		Image newimg;
		
		//Pular pergunta
		ImageIcon jump = new ImageIcon("src/Interface/jump.png");
		image = jump.getImage();
		newimg = image.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); 
		jump = new ImageIcon(newimg);
		
		JLabel lblJump = new JLabel(jump);
		lblJump.setBounds(450,255, jump.getIconWidth(), jump.getIconHeight());
		
		/*//Ajuda
		ImageIcon clock = new ImageIcon("src/Interface/clock.png");
		image = clock.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		clock = new ImageIcon(newimg);		
		
		JLabel lblClock = new JLabel(next);
		lblClock.setBounds(350,235, clock.getIconWidth(), clock.getIconHeight());
		lblClock.setToolTipText("Time");
		*/
		//Responder
		ImageIcon next = new ImageIcon("src/Interface/next.png");
		image = next.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		next = new ImageIcon(newimg);		
		
		JLabel lblRank = new JLabel(rank);
		lblRank.setBounds(285,235, rank.getIconWidth(), rank.getIconHeight());
		lblRank.setToolTipText("Rank");
		
		ImageIcon start = new ImageIcon("src/Interface/play.png");
		image = start.getImage();
		newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
		start = new ImageIcon(newimg);		
		
		JLabel lblStart = new JLabel(start);
		lblStart.setBounds(405,200, start.getIconWidth(), start.getIconHeight());
		lblStart.setToolTipText("Start");
		
		
		
		janela.add(lblJump);
		janela.add(lblCamp);
	
		janela.setVisible(true);
		
		System.out.println(janela.getSize());
	}
}
