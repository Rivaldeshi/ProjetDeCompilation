package DrawAutomate;

import Automate.Automate;
import Automate.Etat;
import AutomateRegex.Verifications;
import SwingComponent.Label;
import SwingComponent.Panel;
import Utils.Constans;
import Utils.ValidationException;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxPerimeter;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxConstants;

public class Draw {

	public static final mxStylesheet STYLE = new mxStylesheet();

	static {
		HashMap<String, Object> style = new HashMap<String, Object>();
		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("RIEN", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "white");
		style.put(mxConstants.STYLE_OPACITY, 80);
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "green");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("INITIAL", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "black");
		style.put(mxConstants.STYLE_OPACITY, 80);
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "white");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("FINAL", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "white");
		style.put(mxConstants.STYLE_OPACITY, 80);
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "blue");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("IF", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_LEFT);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_OPACITY, 80);
		style.put(mxConstants.STYLE_STROKECOLOR, "red");
		STYLE.putCellStyle("NOT", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_LEFT);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_STROKECOLOR, "black");
		style.put(mxConstants.STYLE_OPACITY, 80);
		STYLE.setDefaultEdgeStyle(style);

	}

	public static Panel drawAutomate(Automate automate, ArrayList<Etat> trace)
			throws ValidationException {
		return drawAutomate(automate, "", trace);
	}

	public static Panel drawAutomate(Automate automate, String nom,
			ArrayList<Etat> trace) throws ValidationException {

		// Creates graph with model
		mxGraph graph = new mxGraph();
		graph.setStylesheet(STYLE);
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {

			ArrayList<Object> vertexs = new ArrayList<Object>();

			for (Etat et : automate.getStates()) {
				Object v1;
				if (et.isFinal() && et.isInitial()) {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 60, 60,
							"IF");
				} else if (et.isFinal()) {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 60, 60,
							"FINAL");
				} else if (et.isInitial()) {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 60, 60,
							"INITIAL");

				} else {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 60, 60,
							"RIEN");

				}
				vertexs.add(v1);

			}

			for (Etat et : automate.getStates()) {
				for (String symb : Constans.APHABET) {
					for (Etat dest : automate.getTransitionTable().getTransition(et, symb)) {
						if (Verifications.succesive(trace, et, dest)) {
							graph.insertEdge(parent, null, symb.toString(),vertexs.get(automate.getStates().indexOf(et)), vertexs.get(automate.getStates().indexOf(dest)), "NOT");
						}else{
							graph.insertEdge(parent, null, symb.toString(),vertexs.get(automate.getStates().indexOf(et)), vertexs.get(automate.getStates().indexOf(dest)));
						}
					}
				}
			}

		} finally {
			graph.getModel().endUpdate();
		}

		Panel pan = nom.equals("") ? new Panel() : new Panel(nom);
		pan.add(new Label("<html>Expression    : "+Constans.expressionCourant+"</html>"));
		String s = Constans.motCourant.equals("")? Constans.EPSILON: Constans.motCourant;
		pan.add(new Label("<html>Mot           : "+s+"</html>"));
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);

		layout.setOrientation(SwingConstants.WEST);
		layout.execute(parent);

		mxGraphComponent graphComponent = new mxGraphComponent(graph);

		graphComponent.getViewport().setOpaque(true);

		graphComponent.getViewport().setBackground(Color.WHITE);
		graphComponent.setBorder(BorderFactory.createEmptyBorder());
		graphComponent.setEnabled(false);
		pan.setBackground(Color.white);
		pan.add(graphComponent);

		return pan;
	}
}