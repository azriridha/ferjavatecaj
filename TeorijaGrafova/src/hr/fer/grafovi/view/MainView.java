package hr.fer.grafovi.view;

import hr.fer.grafovi.controller.MainController;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author hrvoje
 *
 */
public class MainView extends JFrame 
{
	private static final long	serialVersionUID	= 1L;
	private MainController	ctrl;
	private InfoView	infoPanel;
	private GraphView	graphPanel;
	private MenuBar		menubar;
	private LabsView	labsPanel;
	
	public MainView()
	{
		super("Teorija grafova");
		this.ctrl = MainController.ctrl;
		initGUI();
	}

	private void initGUI()
	{
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		infoPanel = new InfoView();
		graphPanel = new GraphView();
		labsPanel = new LabsView();
		menubar = new MenuBar();
		
		ctrl.addPropertyChangeListener("info", infoPanel);
		ctrl.addPropertyChangeListener("graph", menubar);
		ctrl.addPropertyChangeListener("vertices", graphPanel);
		ctrl.addPropertyChangeListener("vertices", labsPanel);
		this.setJMenuBar(menubar);
		this.add(labsPanel, BorderLayout.EAST);
		this.add(infoPanel, BorderLayout.SOUTH);
		this.add(graphPanel, BorderLayout.CENTER);
	}

	public GraphView getGraphView()
	{
		return graphPanel;
	}
}
