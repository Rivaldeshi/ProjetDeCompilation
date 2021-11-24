package View;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import DrawAutomate.Draw;
import SwingComponent.Label;
import SwingComponent.Panel;

@SuppressWarnings("serial")
public class Legende extends Panel {

	private static final mxStylesheet STYLE = Draw.STYLE;

	public Legende() {
		super("Legende");
		this.setBackground(Color.WHITE);
		
		this.add(drawVertex("q1", "Etat simple", "RIEN"));
		this.add(drawVertex("q1(I)", "Etat Initial", "INITIAL"));
		this.add(drawVertex("q1(F)", "Etat finale", "FINAL"));
		this.add(drawVertex("q1(I,F)", "Etat initial et finale", "IF"));
	}

	public static Panel drawVertex(String nom, String signifiaction, String type) {
		// Creates graph with model
		mxGraph graph = new mxGraph();
		graph.setStylesheet(STYLE);
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();

		try {
			graph.insertVertex(parent, null, nom, 0, 0,90, 90,type);

		} finally {
			graph.getModel().endUpdate();
		}

		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);

		layout.setOrientation(SwingConstants.WEST);
		layout.execute(parent);
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.getViewport().setOpaque(true);
		graphComponent.getViewport().setBackground(Color.WHITE);
		graphComponent.setBorder(BorderFactory.createEmptyBorder());
		graphComponent.setEnabled(false);

		Panel pan = new Panel(graphComponent, new Label("            "+signifiaction));
		
		pan.setBackground(Color.WHITE);
		

		return pan;

	}

}
