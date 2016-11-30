/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class NameSurfer extends Program implements NameSurferConstants {

    private NameSurferDataBase data;
    private NameSurferGraph graph;
    private TextField textField;
    private Button graphButton;
    private Button clearButton;

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
        readDatabase();
		initInteractors();
        addActionListeners();
        graph = new NameSurferGraph();
        add(graph);


//        testNameSurferEntry();
//        testDatabase();


	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
        Object obj = e.getSource();
        if(obj == graphButton || obj == textField) {
            graphName(textField.getText());
        } else if (obj == clearButton) {
            clearGraph();
        }
        textField.setText("");
	}

	private void graphName(String name) {

        System.out.println(data.findEntry(name));
        if(data.findEntry(name) == null) return;
        graph.addEntry(data.findEntry(name));
    }

    private void clearGraph() {
        System.out.println("Clear");
        graph.clear();
    }
	private void initInteractors() {
        add(new Label("Name"), SOUTH);

        textField = new TextField(15);
        textField.addActionListener(this);
        add(textField, SOUTH);

        graphButton = new Button("Graph");
        add(graphButton, SOUTH);

        clearButton = new Button("Clear");
        add(clearButton, SOUTH);
    }
    private void readDatabase() {
        data = new NameSurferDataBase(NAMES_DATA_FILE);
    }
    private void testNameSurferEntry() {
        NameSurferEntry entry = new NameSurferEntry("Mike 58 69 99 131 168 236 278 380 467 408 466");
        System.out.println(entry);
        System.out.println(entry.getName());
        System.out.println(entry.getRank(3));
    }
    private void testDatabase() {
        System.out.println(data.findEntry("Michael"));
    }
}
