package DrawAutomate;

import Automate.Automate;
import Automate.Etat;
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

	private static final mxStylesheet STYLE = new mxStylesheet();

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
		style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("INITAL", style);
		
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_FONTCOLOR, "black");
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "red");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("FINAL", style);

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
		style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_LEFT);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_STROKECOLOR, "black");
		STYLE.setDefaultEdgeStyle(style);
	}

	public static Panel drawAutomate(Automate automate)
			throws ValidationException {

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
							"RIEN");
				} else if (et.isFinal()) {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 50, 50,"FINAL");
				} else if (et.isInitial()) {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 50, 50,
							"RIEN");
					
				} else {
					v1 = graph.insertVertex(parent, null, et, 0, 0, 50, 50,
							"RIEN");
					
				}
				vertexs.add(v1);

			}

			for (Etat et : automate.getStates()) {
				for (String symb : Constans.APHABET) {
					for (Etat dest : automate.getTransitionTable()
							.getTransition(et, symb)) {

						graph.insertEdge(parent, null, symb.toString(),
								vertexs.get(automate.getStates().indexOf(et)),
								vertexs.get(automate.getStates().indexOf(dest)));
					}
				}
			}

		} finally {
			graph.getModel().endUpdate();
		}

		Panel pan = new Panel();

		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);

		layout.setOrientation(SwingConstants.WEST);
		layout.execute(parent);

		mxGraphComponent graphComponent = new mxGraphComponent(graph);

		graphComponent.getViewport().setOpaque(true);
		graphComponent.getViewport().setBackground(Color.white);
		graphComponent.setBorder(BorderFactory.createEmptyBorder());
		graphComponent.setEnabled(false);
		pan.setBackground(Color.white);
		pan.add(graphComponent);

		return pan;

	}
}