package Utils;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import Regex.TransformRegex;
import SwingComponent.Frame;
import SwingComponent.Panel;
import Automate.Automate;
import Automate.AutomateOperation;
import Automate.Determinisation;
import Automate.Etat;
import AutomateRegex.Verifications;
import Automate.Minimiser;
import DrawAutomate.Draw;

public class Brouillon {

	public static void testTransistion() {

		Etat etat1 = new Etat(1), etat2 = new Etat(2), etat3 = new Etat(4);

		Automate aut = new Automate(Constans.APHABET_PAR_DEFAUT);

		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtat(etat2);
		aut.ajouterUnEtat(etat3);

		try {
			aut.ajouterUneTransition(etat1, "a", etat2);
			aut.ajouterUneTransition(etat1, Constans.APHABET_PAR_DEFAUT[0],
					etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		System.out.println(aut.getTransitionTable());

	}

	public static void testAutomateSimple() {

		try {
			Automate automat1 = AutomateOperation.AutomateSimple("a");
			Automate automat2 = AutomateOperation.AutomateSimple("b");
			Automate aut = AutomateOperation.AutomatePlus(automat1, automat2);

			System.out.println(aut.getTransitionTable());
			System.out.println(aut.getStates().size());
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

	// test expresion regulierer (a+b)*abb;
	public static void testAutomate() {
		try {
			Automate aut = conca(
					conca(conca(etoil(ou(sim("a"), sim("b"))), sim("a")),
							sim("b")), sim("b"));
			aut = Determinisation.Determiniser(aut);
			aut = Minimiser.minimisation(aut);
			System.out.println(aut.getTransitionTable());
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testVerif() {
		try {
			Automate aut = conca(
					conca(conca(etoil(ou(sim("a"), sim("b"))), sim("a")),
							sim("b")), sim("b"));
			// Automate aut = etoil(sim(""));

			System.out.println(aut.getTransitionTable());

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDessin() {
		try {
			Automate aut = conca(
					conca(conca(etoil(ou(sim("a"), sim("b"))), sim("a")),
							sim("b")), sim("b"));
			Panel pan = Draw.drawAutomate(aut);

		//	Panel pan1 = Draw.drawAutomate(Determinisation.Determiniser(aut));

			Frame frame = new Frame();

			frame.add(pan);
			//frame.add(pan1);

			frame.setVisible(true);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test() {

		Scanner sc = new Scanner(System.in);
		try {

			System.out
					.print("Entrer un expression reguliere Bien former  :   ");
			String regex = sc.nextLine();

			Automate aut = TransformRegex.evaluateRegex(regex);
			System.out.println(aut.getTransitionTable());

			System.out.println();

			System.out.print("Entrer un mot :   ");

			String mot = sc.nextLine();

			System.out.println();

			Verifications.ApartientAutomate(mot, aut);
			sc.close();

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testPostfix() {

		try {
			String postFix = TransformRegex.infixToPostfix("(a+b)*.a.b.b");

			System.out.println(postFix);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testConcact() {
		try {
			Automate a1 = conca(ou(sim("a"), sim("b")), sim("b"));
			// Automate a2 = ou(sim("a"), sim("b"));
			System.out.println(a1.getTransitionTable());
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public static Automate ou(Automate a1, Automate a2)
			throws ValidationException {
		return AutomateOperation.AutomatePlus(a1, a2);
	}

	public static Automate conca(Automate a1, Automate a2)
			throws ValidationException {
		return AutomateOperation.AutomatePoint(a1, a2);
	}

	public static Automate sim(String s) throws ValidationException {
		return AutomateOperation.AutomateSimple(s);
	}

	public static Automate etoil(Automate a1) throws ValidationException {
		return AutomateOperation.AutomateEtoil(a1);
	}

}
