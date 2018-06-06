package Interface;

import java.util.ArrayList;

import javax.swing.JComponent;

public class Divisao {

	public static final int HORIZONTAL = 0, VERTICAL = 1;
	
	private Frame frm;
	
	private int iniX, fimX, iniY, fimY;
	private int direcao;
	private int margem = 0;
	
	private ArrayList<CompLocation> componentes = new ArrayList<>();
	
	public Divisao(int direcao, Frame frm) {
		
		this.frm = frm;
		
		this.iniX = 0;
		this.fimX = 0;
		this.iniY = 0;
		this.fimY = 0;
		
		this.direcao = direcao;
	}
	
	public void setMargem(int margem) {
		
		this.margem = margem;
	}
	
	public void add(CompLocation compLocal) {
		
		ajustarComp(compLocal);
		
		ajustarSize(compLocal);
		
		atualizar();
		
		componentes.add(compLocal);
	}
	
	private void ajustarComp(CompLocation compLocal) {
		
		JComponent comp = compLocal.getComp();
		int location = compLocal.getLocation();
		
		int x = (int) comp.getLocation().getX(), y = (int) comp.getLocation().getY();
		
		if (direcao == HORIZONTAL) {
			
			comp.setLocation(x, iniY);
		} else {
			
			comp.setLocation(iniX, y);
		}
		
		
	}
	
	private void ajustarSize(CompLocation compLocal) {
		
		JComponent comp = compLocal.getComp();
		
		int width = (int) comp.getSize().getWidth(), height = (int) comp.getSize().getHeight();
		
		if (direcao == HORIZONTAL) {
					
			int alturaAtual = fimY;
			int alturaNova = iniY + height + margem;
			
			fimY = alturaNova > alturaAtual ? alturaNova : alturaAtual;
		} else {
				
			int larguraAtual = fimX;
			int larguraNova = iniX + width + margem;
			
			fimX = larguraNova > larguraAtual ? larguraNova : larguraAtual;
		}
	}
	
	private void reposicionar(CompLocation compLocal) {
		
		JComponent comp = compLocal.getComp();
		int location = compLocal.getLocation();
		
		int padding = frm.getPadding();
		
		int x = (int) comp.getLocation().getX(), y = (int) comp.getLocation().getY();
		int width = (int) comp.getSize().getWidth(), height = (int) comp.getSize().getHeight();				
		
		if (direcao == HORIZONTAL) {
			
			switch (location) {
			
				case CompLocation.TOP: comp.setLocation(x, y + padding); break;
				case CompLocation.CENTER: comp.setLocation(x, iniY + ((fimY - margem - iniY) / 2) - (height / 2) + padding); break;
				case CompLocation.BOTTOM: comp.setLocation(x, (fimY - iniY) - height + padding); break;
			}
		} else {
			
			switch (location) {
			
				case CompLocation.LEFT: comp.setLocation(x + padding, y); break;
				case CompLocation.CENTER: comp.setLocation(iniX + ((fimX - margem - iniX) / 2) - (width / 2) + padding, y); break; 
				case CompLocation.RIGHT: comp.setLocation((fimX - iniX) - width + padding, y); break;
			}
		}
	}
		
	public void atualizar() {
		
		for (CompLocation comp : componentes) {
			
			ajustarComp(comp);
			
			ajustarSize(comp);
			
			reposicionar(comp);
		}
	}
	
	public void setIniX(int iniX) {
		
		this.iniX = iniX;
	}
	
	public void setIniY(int iniY) {
		
		this.iniY = iniY;
	}
	
	public int getIniX() {
		
		return this.iniX;
	}
	
	public int getFimX() {
		
		return this.fimX;
	}
	
	public int getIniY() {
		
		return this.iniY;
	}
	
	public int getFimY() {
		
		return this.fimY;
	}
}
