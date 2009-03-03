package hr.fer.grafovi.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import hr.fer.grafovi.controller.MainController;
import hr.fer.grafovi.utilities.Constants;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

public class GraphView extends JPanel implements PropertyChangeListener, Constants
{
	private static final long	serialVersionUID	= 1L;
	
	private MainController ctrl;
	private JTextArea graphTextArea;
	private JRadioButton listaSusjedstva;
	private JRadioButton matricaSusjedstva;
	private JRadioButton matricaIncidencije;
	private JRadioButton bezPrikaza;
	private SpinnerNumberModel addVertices = new SpinnerNumberModel(1,1,100,1);
	private SpinnerNumberModel deleteVertex = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel addV = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel addW = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel deleteV = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel deleteW = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel weight = new SpinnerNumberModel(1,1,99,1);
	
	
	public GraphView()
	{
		this.ctrl = MainController.ctrl;
		initGUI();
	}

	private void initGUI()
	{
		this.setLayout(new BorderLayout());
		JPanel radioPanel = new JPanel();
		radioPanel.add(new JLabel("Prikaz grafa: "));
		listaSusjedstva	= new JRadioButton("lista susjedstva");
		matricaSusjedstva = new JRadioButton("matrica susjedstva");
		matricaIncidencije = new JRadioButton("matrica incidencije");
		bezPrikaza = new JRadioButton("bez prikaza");
		
		RadioButtonListener radioButtonListener = new RadioButtonListener();
		listaSusjedstva.addActionListener(radioButtonListener);
		matricaSusjedstva.addActionListener(radioButtonListener);
		matricaIncidencije.addActionListener(radioButtonListener);
		bezPrikaza.addActionListener(radioButtonListener);
		
		ButtonGroup grupa = new ButtonGroup();
		grupa.add(listaSusjedstva);
		grupa.add(matricaSusjedstva);
		grupa.add(matricaIncidencije);
		grupa.add(bezPrikaza);
		matricaSusjedstva.setSelected(true);
		
		radioPanel.add(listaSusjedstva);
		radioPanel.add(matricaSusjedstva);
		radioPanel.add(matricaIncidencije);
		radioPanel.add(bezPrikaza);
		
		Box bigBox = Box.createHorizontalBox();
		bigBox.add(Box.createHorizontalGlue());
		
		Box verticalBox;
		ButtonListener buttonListener = new ButtonListener();

		verticalBox = Box.createVerticalBox();
		
		verticalBox.add(addVerticesModifier("Dodaj vrhove", "broj vrhova: ", buttonListener, addVertices));
		verticalBox.add(addVerticesModifier("Obrisi vrh", "indeks vrha: ", buttonListener, deleteVertex));
		
		bigBox.add(verticalBox);
		verticalBox = Box.createVerticalBox();
		
		verticalBox.add(addEdgesModifier("Dodaj brid", buttonListener, addV, addW));
		verticalBox.add(addEdgesModifier("Obrisi brid", buttonListener, deleteV, deleteW));
		
		bigBox.add(verticalBox);
		
		graphTextArea = new JTextArea();
		graphTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		graphTextArea.setEditable(false);
		graphTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		
		this.add(radioPanel, BorderLayout.NORTH);
		this.add(new JScrollPane(graphTextArea), BorderLayout.CENTER);
		this.add(bigBox, BorderLayout.SOUTH);
	}

	private Component addEdgesModifier(String buttonText, ButtonListener buttonListener, SpinnerModel vModel, SpinnerModel wModel)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				BorderFactory.createEmptyBorder(5,5,5,5)
				));
		JButton button = new JButton(buttonText);
		button.addActionListener(buttonListener);
		panel.add(new JLabel("v = "));
		JSpinner spinner = new JSpinner(vModel);
		spinner.setPreferredSize(new Dimension(50, 25));
		panel.add(spinner);
		panel.add(new JLabel("  w = "));
		spinner = new JSpinner(wModel);
		spinner.setPreferredSize(new Dimension(50, 25));
		panel.add(spinner);
		if (buttonText.equals("Dodaj brid"))
		{
			spinner = new JSpinner(weight);
			panel.add(new JLabel("  tezina = "));
			spinner.setPreferredSize(new Dimension(50, 25));
			panel.add(spinner);
		}
		panel.add(button);
		return panel;
	}

	private Component addVerticesModifier(String buttonText, String labelText, ButtonListener buttonListener, SpinnerModel model)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				BorderFactory.createEmptyBorder(5,5,5,5)
				));
		JButton button = new JButton(buttonText);
		button.addActionListener(buttonListener);
		JSpinner spinner = new JSpinner(model);
		panel.add(new JLabel(labelText));
		spinner.setPreferredSize(new Dimension(50, 25));
		panel.add(spinner);
		panel.add(button);
		return panel;
	}

	public OutputStream getOutputStream()
	{
		OutputStream out = new OutputStream()
		{
		
			@Override
			public void write(int b) throws IOException
			{
				graphTextArea.append(String.valueOf((char)b));
			}
			@Override
			public void write(byte[] b, int off, int len) throws IOException
			{
				graphTextArea.append(new String(b, off, len));
			}
			@Override
			public void write(byte[] b) throws IOException
			{
				write(b, 0, b.length);
			}
		};
		return out;
	}
	
	public void clear()
	{
		graphTextArea.setText("");
	}
	
	public int getSelected()
	{
		if (listaSusjedstva.isSelected())
			return LISTA_SUSJEDSTVA;
		if (matricaIncidencije.isSelected())
			return MATRICA_INCIDENCIJE;
		if (matricaSusjedstva.isSelected())
			return MATRICA_SUSJEDSTVA;
		else 
			return 0;
	}
	
	private class RadioButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			ctrl.updateView();
		}
		
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Dodaj brid"))
			{
				ctrl.addEdge(addV.getNumber().intValue(), addW.getNumber().intValue(), weight.getNumber().intValue());
				if (ctrl.weightedGraph())
					weight.setValue(new Random().nextInt(99) + 1);
				else 
					weight.setValue(1);
			}
			else if (e.getActionCommand().equals("Obrisi brid"))
			{
				ctrl.removeEdge(deleteV.getNumber().intValue(), deleteW.getNumber().intValue());
			}
			else if (e.getActionCommand().equals("Dodaj vrhove"))
			{
				ctrl.addVertex(addVertices.getNumber().intValue());
			}
			else if (e.getActionCommand().equals("Obrisi vrh") )
			{
				ctrl.removeVertex(deleteVertex.getNumber().intValue());
			}
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		deleteVertex.setMaximum((Integer)evt.getNewValue());
		addV.setMaximum((Integer)evt.getNewValue());
		addW.setMaximum((Integer)evt.getNewValue());
		deleteV.setMaximum((Integer)evt.getNewValue());
		deleteW.setMaximum((Integer)evt.getNewValue());
	}


}
