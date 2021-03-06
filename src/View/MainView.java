package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JPanel;

import Automate.Automate;
import AutomateRegex.Verifications;
import Regex.TransformRegex;
import SwingComponent.*;
import Utils.ValidationException;

public class MainView {

	public Frame frame;

	public MainView() {

		frame = new Frame();

		final Text expr = new Text(0, 0);
		final Label lexpr = new Label("Entrer l'expression");

		final Text mot = new Text(0, 0);
		Label lmot = new Label("Entrer l'expression");

		Panel pexpr = new Panel(lexpr, expr);
		Panel pmot = new Panel(lmot, mot);

		Panel p = new Panel();

		Button verif = new Button("Verifier", 0);

		final Label res = new Label("");
		final Label erreur = new Label("");

		erreur.setForeground(Color.red);
		res.setForeground(Color.white);
		p.add(pexpr);
		p.add(pmot);
		p.add(res);

		JPanel p1 = new JPanel();
		p1.setBackground(Color.black);
		p1.add(verif);
		p.add(p1);
		p.add(erreur);

		verif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				erreur.setText("");
				res.setText("");
				try {
					String regex = expr.getText();

					Automate aut = TransformRegex.evaluateRegex(regex);
					System.out.println(aut.getTransitionTable());

					String mot1 = mot.getText();

					if (Verifications.ApartientAutomate(mot1, aut)) {
						res.setForeground(Color.GREEN);
						res.setText("appatient au language");
					} else {
						res.setForeground(Color.red);
						res.setText("n'appatient pas au language");
					}
					;

				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					erreur.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		frame.add(p);
		frame.pack();
		frame.setVisible(true);

	}
}
