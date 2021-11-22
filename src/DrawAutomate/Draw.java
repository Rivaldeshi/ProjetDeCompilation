package DrawAutomate;

import javax.swing.JFrame;

import Automate.Automate;
import Automate.Etat;
import SwingComponent.Label;
import SwingComponent.Panel;
import Utils.Constans;
import Utils.ValidationException;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxPerimeter;
import com.mxgraph.view.mxStylesheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;

public class Draw {

	private static final mxStylesheet STYLE = new mxStylesheet();

	static {
		HashMap<String, Object> style = new HashMap<String, Object>();

		style = new HashMap<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_OPACITY, 50);
		style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
		style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
		style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
		style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		STYLE.putCellStyle("PINK_NONE", style);

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

				Object v1 = graph.insertVertex(parent, null, et, 240, 150, 40,
						20, "PINK_NONE");

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