package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Utils.Constans;
import Utils.ValidationException;
import Automate.Automate;
import Automate.AutomateOperation;
import Automate.Determinisation;
import Automate.Etat;
import Automate.Minimiser;
import DrawAutomate.Draw;
import SwingComponent.Header;
import SwingComponent.Label;
import SwingComponent.Panel;
import SwingComponent.TitreButton;

@SuppressWarnings("serial")
public class AutomateView extends Panel {

	public static Panel menu = new Panel();

	public static TitreButton AFN = new TitreButton("AFN");
	public static TitreButton AFDI = new TitreButton("AFD");
	public static TitreButton MINISER = new TitreButton("MINIMALE");
	public static TitreButton AFDC = new TitreButton("AFD Complet");
	public static TitreButton AFDComple = new TitreButton("AFD Bar");

	Panel footer = new Panel();

	AutomateView(final Automate automate) throws ValidationException {

		super();
		unsetfocus();
		AFN.focus();
		footer.add(Draw.drawAutomate(automate, "Automate Non deterministe",Constans.cheminAFN));
		Label l = new Label("<html>trace : "
				+ traceToString(Constans.cheminAFN) + " </html>");
		l.setBackground(Color.black);
		l.setForeground(Color.white);

		footer.add(l);
		List<TitreButton> heads = new ArrayList<TitreButton>();
		heads.add(AFN);
		heads.add(AFDI);
		heads.add(MINISER);
		heads.add(AFDC);
		heads.add(AFDComple);
		Header header = new Header(heads);
		this.add(header);
		this.add(footer);

		ActionListener acc = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unsetfocus();

				try {
					Automate determiniserC = Determinisation
							.Determiniser(automate);

					Automate determiniserI = Determinisation.Determiniser(
							automate, false);
					Automate Minimal = Minimiser.minimisation(Determinisation
							.Determiniser(automate));
					Automate complemantaire = AutomateOperation
							.AutomateComplementaire(Determinisation
									.Determiniser(automate, false));
					footer.removeAll();
					if (e.getSource() == AFN) {

						AFN.focus();
						footer.add(Draw.drawAutomate(automate,
								"Automate Non deterministe",Constans.cheminAFN));
						Label l = new Label("<html>trace : "
								+ traceToString(Constans.cheminAFN)
								+ " </html>");
						l.setBackground(Color.black);
						l.setForeground(Color.white);

						footer.add(l);

					} else if (e.getSource() == AFDI) {
						AFDI.focus();
						footer.add(Draw
								.drawAutomate(determiniserI,
										"Automate deterministe Incomplet (sans etat puit) ",Constans.cheminAFD));
						Label l = new Label("<html>trace : "
								+ traceToString(Constans.cheminAFD)
								+ " </html>");
						l.setBackground(Color.black);
						l.setForeground(Color.white);

						footer.add(l);
					} else if (e.getSource() == AFDC) {
						AFDC.focus();
						footer.add(Draw
								.drawAutomate(determiniserC,
										"Automate deterministe Complet (avec etat puit)",Constans.cheminAFD));
						Label l = new Label("<html>trace : "
								+ traceToString(Constans.cheminAFD)
								+ " </html>");
						l.setBackground(Color.black);
						l.setForeground(Color.white);
						footer.add(l);
					} else if (e.getSource() == MINISER) {
						footer.add(Draw.drawAutomate(Minimal,
								"Automate Minimale  Complet de AFD  ",Constans.cheminM));
						Label l = new Label("<html>trace : "
								+ traceToString(Constans.cheminM)
								+ " </html>");
						l.setBackground(Color.black);
						l.setForeground(Color.white);
						footer.add(l);
						MINISER.focus();
					} else if (e.getSource() == AFDComple) {
						footer.add(Draw.drawAutomate(complemantaire,
								"Automate Complementaire  InComplet de AFD", new ArrayList<Etat>()));
						AFDComple.focus();

					}

					footer.repaint();
					footer.revalidate();
				} catch (ValidationException e1) {
					e1.printStackTrace();
				}
			}

		};

		for (TitreButton btn : heads) {
			btn.addActionListener(acc);
		}

	}

	public static String traceToString(ArrayList<Etat> trace) {
		String str = "";
		for (Etat et : trace) {
			str += "-> " + et;
		}
		return str;
	}

	public static void unsetfocus() {
		AFN.setBackground(Color.white);
		AFDI.setBackground(Color.white);
		AFDC.setBackground(Color.white);
		MINISER.setBackground(Color.white);
		AFDComple.setBackground(Color.white);

		AFN.setForeground(Color.black);
		AFDI.setForeground(Color.black);
		AFDC.setForeground(Color.black);
		MINISER.setForeground(Color.black);
		AFDComple.setForeground(Color.black);
	}

}
