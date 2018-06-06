package Code;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

	public CustomTableModel (List<Jogador> jogadores, int[] indexes) {
		
		String[] colunas = {"Jogador", "Tempo", "Pontuação"};
		
		for (int i = 0; i < indexes.length; i++) {
			
			addColumn(colunas[indexes[i]]);
		}
		
		for (Jogador jogador : jogadores) {
			
			Object[] infoCrua = jogador.getInfo();
			
			Object[] info = new Object[indexes.length];
			
			for (int i = 0; i < indexes.length; i++) {
				
				info[i] = infoCrua[indexes[i]];
			}
			
			addRow(info);
		}
	}
	
	public JScrollPane getTable() {
		
		JTable tabela = new JTable();
		tabela.setModel(this);
		
		JScrollPane pane = new JScrollPane(tabela);
		
		return pane;
	}
}