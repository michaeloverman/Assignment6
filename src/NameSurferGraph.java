/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

    private List<NameSurferEntry> entries;

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
        entries = new ArrayList<>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
        entries.clear();
        update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
        entries.add(entry);
        update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
        drawGridLinesAndKey();
        graphEntries();
	}

	private void graphEntries() {
        double top = GRAPH_MARGIN_SIZE;
        double bottom = height - GRAPH_MARGIN_SIZE;
        double rankPos = (bottom - top) / 1000;
        for(NameSurferEntry entry : entries) {
            double lastX = 0;
            int rank = entry.getRank(0);
            if(rank == 0) rank = 1000;
            double lastY = (rank * rankPos) + GRAPH_MARGIN_SIZE;
            double newX;
            double newY;
            for (int i = 1; i < NDECADES; i++) {
                newX = vertOffset * i;
                rank = entry.getRank(i);
                if(rank == 0) rank = 1000;
                newY = (rank * rankPos) + GRAPH_MARGIN_SIZE;
                add(new GLine(lastX, lastY, newX, newY));
                lastX = newX;
                lastY = newY;
            }
        }
    }


    private double width;
    private double height;
    private double vertOffset;

	private void drawGridLinesAndKey() {
        width = getWidth();
        height = getHeight();
		vertOffset = width / NDECADES;
		for(int i = 0; i < NDECADES; i++) {
			add(new GLine(vertOffset * i, 0, vertOffset * i, height));
            String decade = Integer.toString(START_DECADE + 10 * i);
            add(new GLabel(decade, vertOffset * i + 4, height - 4));
		}
		add(new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE));
		add(new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE));
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
