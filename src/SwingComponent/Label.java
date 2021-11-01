package SwingComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {

	public Label() {
		this.setForeground(Color.black);
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setFont(new Font("serif", Font.ITALIC, 20));
	}

	public Label(int size) {
		this();
		this.setFont(new Font("serif", Font.ITALIC, size));
	}

	public Label(String s) {
		this();
		this.setText(s);

	}

	public Label(String s, int size) {
		this(s);
		this.setFont(new Font("serif", Font.ITALIC, size));

	}

	public Label(String s,boolean a){
		this(s);
		this.setPreferredSize(new Dimension(300,30));
	}
	public Label(String s, int size, boolean opaque) {
		this(s);
		this.setOpaque(opaque);
		this.setFont(new Font("serif", Font.ITALIC, size));

	}

}
