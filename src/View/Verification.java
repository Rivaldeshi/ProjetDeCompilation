package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Automate.Automate;
import Automate.Determinisation;
import Automate.Etat;
import Automate.Minimiser;
import AutomateRegex.Verifications;
import Regex.RegexAnalyser;
import Regex.TransformRegex;
import SwingComponent.Button;
import SwingComponent.Label;
import SwingComponent.Panel;
import SwingComponent.Text;
import Utils.Constans;
import Utils.ValidationException;

@SuppressWarnings("serial")
public class Verification extends Panel {

	Verification() {
		super("Verifcation si un mot appartient a un languade");
		final Text expr = new Text(0, 0);
		expr.setText(Constans.expressionCourant);
		final Label lexpr = new Label("Entrer l'expression  ");

		final Text mot = new Text(0, 0);
		mot.setText(Constans.motCourant);
		Label lmot = new Label("Entrer le mot          ");

		Panel pexpr = new Panel(lexpr, expr);
		Panel pmot = new Panel(lmot, mot);

		Panel p = new Panel();
		Button verif = new Button("Verifier", 0);

		final Label res = new Label("");
		final Label erreur = new Label("");
		final Label chemin = new Label("");

		erreur.setForeground(Color.red);
		res.setForeground(Color.white);

		p.add(pexpr);
		p.add(pmot);
		p.add(res);

		chemin.setForeground(Color.BLUE);

		JPanel p1 = new JPanel();
		p1.setBackground(Color.black);
		p1.add(verif);
		p.add(p1);
		p.add(erreur);
		p.add(chemin);

		verif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				erreur.setText("");
				res.setText("");
				try {
					String regex = expr.getText();
					String mot1 = mot.getText();

					Constans.expressionCourant = regex;

					if (regex.length() == 0)
						throw new ValidationException("EXpression reguliere est vide");
					Constans.APHABET = RegexAnalyser.ChercherAphabetApartireDuRegex(expr.getText());

					Constans.motCourant = mot1;

					Automate aut = TransformRegex.evaluateRegex(regex);
					MainView.AutomateCourant = aut;
					MainView.menu.bon();

					ArrayList<Etat> trace = new ArrayList<Etat>();

					if (Verifications.ApartientAutomate(mot1, aut, trace)) {
						res.setForeground(Color.GREEN);
						res.setText("appatient au language");
					} else {
						res.setForeground(Color.red);
						res.setText("n'appatient pas au language");
					}
					Constans.cheminAFD = trace;

					trace = new ArrayList<Etat>();
					if (Verifications.ApartientAutomateAFN(aut, mot1, aut.getInitialState(), trace)) {
						Constans.cheminAFN = trace;
						trace = new ArrayList<Etat>();
						Automate min = Minimiser.minimisation(Determinisation.Determiniser(aut));
						Verifications.ApartientAutomateAFN(min, mot1, min.getInitialState(), trace);
						Constans.cheminM = trace;
					}else{
						Constans.cheminM = new ArrayList<Etat>();
						Constans.cheminAFN =new ArrayList<Etat>();
					}

				} catch (ValidationException e1) {
					erreur.setText(e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		this.add(p);

	}

}
