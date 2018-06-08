package Code;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class CustomTableModel extends DefaultTableModel {

	private static JScrollPane pane = new JScrollPane();
	
	public CustomTableModel(List<Jogador> jogadores, int[] indexes) {

		String[] colunas = { "Jogador", "Tempo", "Pontua��o"};

		for (int i = 0; i < indexes.length; i++) {

			addColumn(colunas[indexes[i] - 1]);
		}

		for (Jogador jogador : jogadores) {

			Object[] infoCrua = jogador.getInfo();

			Object[] info = new Object[indexes.length];

			for (int i = 0; i < indexes.length; i++) {

				info[i] = infoCrua[indexes[i] - 1];
			}

			addRow(info);
		}
	}

	public JScrollPane getTable() {

		JTable tabela = new JTable() {

			// Centraliza as informações das colunas
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			{
				render.setHorizontalAlignment(SwingConstants.CENTER);
			}

			@Override
			public TableCellRenderer getCellRenderer(int arg0, int arg1) {
				return render;
			}
		};
		tabela.setModel(this);

		pane.setViewportView(tabela);;

		return pane;
	}
}