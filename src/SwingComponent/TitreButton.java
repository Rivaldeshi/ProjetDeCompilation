package SwingComponent;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class TitreButton extends Button {

	 TitreButton(){
		 super();
		 
	 }
	 public TitreButton(String s){
		 super(s);
		 
	 }
	 
	 public void focus(){
			this.setBackground(Color.black);
		this.setForeground(Color.white);
	 }
	public Dimension getMaximumSize(){
		Dimension d = super.getMaximumSize();
		 d.height= d.height+20;
		  d.width =Integer.MAX_VALUE;
		return d;
	}
}
