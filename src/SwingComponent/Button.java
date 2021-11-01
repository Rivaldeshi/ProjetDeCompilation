package SwingComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	public	Button() {
		this.setForeground(Color.black);
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setFont(new Font("serif", Font.ITALIC, 20));
		this.setBackground(Color.white);
		this.setFocusable(false);

	}

	public	Button(String s) {
		this();
		this.setText(s);
	}
	
	public Button(String s , int size) {
		this(s);
		this.setPreferredSize(new Dimension(400,30));
	}
	
	
}

