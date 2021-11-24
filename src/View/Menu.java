package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;

import SwingComponent.Frame;
import SwingComponent.Panel;
import SwingComponent.TitreButton;
import Utils.ValidationException;

@SuppressWarnings("serial")
public class Menu extends Panel {

	public static TitreButton verification = new TitreButton("Verification Mot");
	public static TitreButton Automates = new TitreButton("Graph Automates");
	public static TitreButton TableDeTransistion = new TitreButton(
			"Table de Transition");
	public static TitreButton Legende = new TitreButton("Legende");
	public static TitreButton propos = new TitreButton("A propos");

	Menu(String m) {
		unsetfocus();
		verification.focus();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(50, 500));
		this.add(Box.createVerticalGlue());
		this.add(verification);
		this.add(Box.createVerticalGlue());
		this.add(Automates);
		this.add(Box.createVerticalGlue());
		this.add(TableDeTransistion);
		this.add(Box.createVerticalGlue());
		this.add(Legende);
		this.add(Box.createVerticalGlue());
		this.add(propos);
		this.add(Box.createVerticalGlue());

		Automates.setEnabled(false);
		TableDeTransistion.setEnabled(false);
		verification.addActionListener(action);
		Automates.addActionListener(action);
		TableDeTransistion.addActionListener(action);
		Legende.addActionListener(action);
		propos.addActionListener(action);
	}

	public void bon() {
		Automates.setEnabled(true);
		TableDeTransistion.setEnabled(true);
	}

	public static void unsetfocus() {
		verification.setBackground(Color.white);
		Automates.setBackground(Color.white);
		TableDeTransistion.setBackground(Color.white);
		Legende.setBackground(Color.white);
		propos.setBackground(Color.white);
		
		verification.setForeground(Color.black);
		Automates.setForeground(Color.black);
		TableDeTransistion.setForeground(Color.black);
		Legende.setForeground(Color.black);
		propos.setForeground(Color.black);

	}

	public static ActionListener action = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			unsetfocus();
			if (e.getSource() == verification) {
				Frame.splitPane.setRightComponent(new Verification());
				verification.focus();
			} else

			if (e.getSource() == Automates) {

				try {
					Frame.splitPane.setRightComponent(new AutomateView(
							MainView.AutomateCourant));
					Automates.focus();
				} catch (ValidationException e1) {
					e1.printStackTrace();
				}
			} else

			if (e.getSource() == TableDeTransistion) {
				try {
					Frame.splitPane.setRightComponent(new TableView(
							MainView.AutomateCourant));
					TableDeTransistion.focus();
				} catch (ValidationException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			} else

			if (e.getSource() == Legende) {
				Legende.focus();
				Frame.splitPane.setRightComponent(new Legende());
			}
			if (e.getSource() == propos) {
				propos.setBackground(Color.BLACK);
				propos.setForeground(Color.white);
				Frame.splitPane.setRightComponent(new Propos());
			}
		}

	};

}
