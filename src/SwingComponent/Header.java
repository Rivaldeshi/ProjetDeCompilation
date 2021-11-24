package SwingComponent;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

@SuppressWarnings("serial")
public class Header extends Panel {

	public Header(List<TitreButton> heads) {
		super();

		this.setLayout(new GridLayout(1, 0));
		this.setPreferredSize(new Dimension(200, 30));

		for (TitreButton btn : heads)
			this.add(btn);

	}

}
