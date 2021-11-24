package View;

import java.awt.Color;
import SwingComponent.Label;
import SwingComponent.Panel;
import Utils.Constans;

@SuppressWarnings("serial")
public class Propos extends Panel {
	public static int i = 0;


	Propos() {
		super("A propos");

		Label l=new Label("<html>             Bien venue Sur <br>  <u><center> " + Constans.title+" </center></u> </html> ", 27);
		l.setForeground(Color.BLUE);
		
		this.add(l);
		
		Label lab = new Label(
				"<html> <center>Cette application est surnomer AutomateBase a pour but de verifier l'appartenance dun mot a un language ( expression reguliere)"
				+ "elle peut dont prende un expression reguliere et un mot nous dire si le mot appartient a l'expression reguliere ou pas"
				+ " elle peut aussi tracer l'automate d'une expression reguliere la determiniser , la minimiser , la completer et la complementer"
				+ "aplication tres facile a utiliser <br> propoer par des etudiant d'informatique 3 UDS <br>"
				+ "<br> contacter nous par Nous@gmail.com"
				+ " </center> </html>");
		lab.setForeground(Color.WHITE);
		this.add(lab);
	}

}
