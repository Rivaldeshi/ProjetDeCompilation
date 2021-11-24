package SwingComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Utils.Constans;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	public static JSplitPane splitPane;
	Titre titre;

	public Panel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(Constans.framewidh,
				Constans.framehight));
	}

	public Panel(String s) {
		this();
		titre = new Titre(s, 30);
		titre.setBackground(Color.cyan);
		titre.setOpaque(true);
		this.add(titre);
		this.add(Box.createRigidArea(new Dimension(40, 40)));
	}

	public Panel(String s, int size) {
		this();
		titre = new Titre(s, size);
		titre.setOpaque(true);
		titre.setBackground(Color.BLACK);
		titre.setForeground(Color.white);
		this.add(titre);
		this.setBackground(Color.white);
	}

	public Panel(String s, boolean a) {
		this();
		titre = new Titre(s, 30);
		titre.setBackground(Color.ORANGE);
		titre.setOpaque(true);
		this.add(titre);
	}

	public Panel(JLabel l, JButton b) {
		super();
		this.setBackground(Color.black);
		this.setLayout(new FlowLayout(200));
		l.setForeground(Color.white);
		this.add(l);
		this.add(b);
	}

	public Panel(String s, String s1) {
		this();
		titre = new Titre(s, 30);
		titre.setBackground(Color.cyan);
		titre.setOpaque(true);
		this.add(titre);
	}

	public Panel(JLabel l, Text t) {
		this.setBackground(Color.black);
		this.setLayout(new FlowLayout());
		l.setForeground(Color.white);
		this.add(l);
		this.add(t);
	}

	public Panel(JPanel panel1, JPanel panel2) {
		this();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(panel2);
		splitPane.setResizeWeight(1);
		splitPane.setRightComponent(panel1);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.15);
		this.add(splitPane);
	}
}
