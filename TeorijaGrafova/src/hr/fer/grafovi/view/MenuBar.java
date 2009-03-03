package hr.fer.grafovi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import hr.fer.grafovi.controller.MainController;
import hr.fer.grafovi.model.GraphIO;
import hr.fer.grafovi.utilities.Constants;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuBar extends JMenuBar implements ActionListener, PropertyChangeListener
{
	private static final long	serialVersionUID	= 1L;
	private static final String COMPLETE = "Potpuni Graf";
	private static final String BIPARTITE = "Potpuni Bipartitni Graf";
	private static final String WHEEL = "Kotac";
	private static final String CUBE = "Kocka";
	private static final String CYCLE = "Ciklus";
	private static final String RANDOM = "Random Graph";
	private MainController ctrl;
	private JFileChooser fc;
	private JRadioButtonMenuItem adjecencyLists;
	private JRadioButtonMenuItem adjecencyMatrix;
	
	public MenuBar()
	{
		this.ctrl = MainController.ctrl;
		initGUI();
	}

	private void initGUI()
	{
		fc = new JFileChooser(System.getProperty("user.dir"));
		
		JMenu file = new JMenu("File");
		
		JMenu submenuNew = new JMenu("New");
		JMenuItem complete = new JMenuItem(COMPLETE);
		JMenuItem bipartite = new JMenuItem(BIPARTITE);
		JMenuItem wheel = new JMenuItem(WHEEL);
		JMenuItem cube = new JMenuItem(CUBE);
		JMenuItem cycle = new JMenuItem(CYCLE);
		JMenuItem random = new JMenuItem(RANDOM);
		submenuNew.add(complete);
		submenuNew.add(bipartite);
		submenuNew.add(wheel);
		submenuNew.add(cube);
		submenuNew.add(cycle);
		submenuNew.add(random);
		
		JMenu submenuSave = new JMenu("Save As");
		JMenuItem matrica = new JMenuItem("Matrica Susjedstva");
		JMenuItem lista = new JMenuItem("Lista Susjedstva");
		submenuSave.add(matrica);
		submenuSave.add(lista);
		
		JMenuItem open = new JMenuItem("Open");
		JMenuItem exit = new JMenuItem("Exit");
		
		file.add(submenuNew);
		file.add(open);
		file.add(submenuSave);
		file.addSeparator();
		file.add(exit);
		
		JMenu convert = new JMenu("Convert");
		adjecencyLists = new JRadioButtonMenuItem("Lista Susjedstva");
		adjecencyMatrix = new JRadioButtonMenuItem("Matrica Susjedstva");
		ButtonGroup grupa = new ButtonGroup();
		grupa.add(adjecencyLists);
		grupa.add(adjecencyMatrix);
		
		convert.add(adjecencyLists);
		convert.add(adjecencyMatrix);
	
		this.add(file);
		this.add(convert);
		
		open.setActionCommand("open");
		matrica.setActionCommand("save matrica");
		lista.setActionCommand("save lista");
		exit.setActionCommand("exit");
		adjecencyLists.setActionCommand("convert to lista");
		adjecencyMatrix.setActionCommand("convert to matrica");
		
		
		complete.addActionListener(this);
		bipartite.addActionListener(this);
		wheel.addActionListener(this);
		cube.addActionListener(this);
		cycle.addActionListener(this);
		random.addActionListener(this);
		open.addActionListener(this);
		matrica.addActionListener(this);
		lista.addActionListener(this);
		exit.addActionListener(this);
		adjecencyLists.addActionListener(this);
		adjecencyMatrix.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(COMPLETE))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.COMPLETE);
			if (values != null)
				ctrl.createGraph(MainController.COMPLETE, values);
		}
		else if (e.getActionCommand().equals(BIPARTITE))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.BIPARTITE);
			if (values != null)
				ctrl.createGraph(MainController.BIPARTITE, values);
		}
		else if (e.getActionCommand().equals(WHEEL))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.WHEEL);
			if (values != null)
				ctrl.createGraph(MainController.WHEEL, values);
		}
		else if (e.getActionCommand().equals(CUBE))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.CUBE);
			if (values != null)
				ctrl.createGraph(MainController.CUBE, values);
		}
		else if (e.getActionCommand().equals(CYCLE))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.CYCLE);
			if (values != null)
				ctrl.createGraph(MainController.CYCLE, values);
		}
		else if (e.getActionCommand().equals(RANDOM))
		{
			int[] values = CustomOptionPane.showInputDialog(this.getParent(), CustomOptionPane.RANDOM);
			if (values != null)
				ctrl.createGraph(MainController.RANDOM, values);
		}
		else if (e.getActionCommand().equals("open"))
		{
			int val =fc.showOpenDialog(this.getParent());
			if (val == JFileChooser.APPROVE_OPTION)
				ctrl.open(fc.getSelectedFile());
		}
		else if (e.getActionCommand().equals("save matrica"))
		{
			int val =fc.showSaveDialog(this.getParent());
			if (val == JFileChooser.APPROVE_OPTION)
				ctrl.save(fc.getSelectedFile(), GraphIO.ADJECENY_MATRIX);
		}
		else if (e.getActionCommand().equals("save lista"))
		{
			int val =fc.showSaveDialog(this.getParent());
			if (val == JFileChooser.APPROVE_OPTION)
				ctrl.save(fc.getSelectedFile(), GraphIO.ADJECENY_LISTS);
		}
		else if (e.getActionCommand().equals("convert to lista"))
		{
			ctrl.convertTo(Constants.LISTA_SUSJEDSTVA);
		}
		else if (e.getActionCommand().equals("convert to matrica"))
		{
			ctrl.convertTo(Constants.MATRICA_SUSJEDSTVA);
		}
		else
			System.exit(0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		switch((Integer)evt.getNewValue())
		{
		case Constants.MATRICA_SUSJEDSTVA: adjecencyMatrix.setSelected(true); break;
		case Constants.LISTA_SUSJEDSTVA: adjecencyLists.setSelected(true); break;
		}
	}


}
