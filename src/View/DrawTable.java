package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import SwingComponent.Panel;
import Utils.Constans;
import Utils.ValidationException;
import Automate.Automate;

public class DrawTable {

	public static Panel Draw(Automate automate, String nom)
			throws ValidationException {
		Panel pan = new Panel(nom+" de "+Constans.expressionCourant);

		Object[][] data = getTableDeTransition(automate);

		String[] columnNames = new String[Constans.APHABET.length + 1];
		columnNames[0] = "";
		for (int i = 0; i < Constans.APHABET.length; i++) {
			columnNames[i + 1] = Constans.APHABET[i];
		}

		JTable table = new JTable(data, columnNames);

		table.setRowHeight(30);
		table.setSelectionBackground(Color.WHITE);
		table.setSelectionForeground(Color.black);
		table.setEnabled(false);
		table.setFont(new Font("serif", Font.ITALIC, 20));
		
		table.getTableHeader().setFont(new Font("serif", Font.ITALIC, 30));
		table.getTableHeader().setBackground(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setOpaque(false);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(400, 35));

		JScrollPane sp = new JScrollPane(table);

		sp.setBorder(null);
		sp.getViewport().setBackground(Color.white);

		pan.add(sp);
		pan.setBackground(Color.white);
		return pan;

	}

	public static Object[][] getTableDeTransition(Automate automate)
			throws ValidationException {

		Object[][] tab = new Object[automate.getStates().size()][Constans.APHABET.length + 1];

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (j == 0) {
					tab[i][j] = automate.getStates().get(i);
				} else {
					String str = "";
					for (int k = 0; k < automate
							.getTransitionTable()
							.getTransition(automate.getStates().get(i),
									Constans.APHABET[j - 1]).size(); k++) {
						str += " "
								+ automate
										.getTransitionTable()
										.getTransition(
												automate.getStates().get(i),
												Constans.APHABET[j - 1]).get(k)
								+ "  ,  ";
					}
					tab[i][j] = str;
				}
			}
		}
		return tab;

	}

}
