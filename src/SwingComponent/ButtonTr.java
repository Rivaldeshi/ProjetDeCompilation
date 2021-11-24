package SwingComponent;

import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class ButtonTr extends TitreButton{
	
	ButtonTr(String s){
		super(s);
		this.setFont(new Font("serif", Font.ROMAN_BASELINE, 18));
	}
	public Dimension getMaximumSize(){
		Dimension d = super.getMaximumSize();
		 d.height= d.height-30;
		  d.width =Integer.MAX_VALUE;
		return d;
		}
}
