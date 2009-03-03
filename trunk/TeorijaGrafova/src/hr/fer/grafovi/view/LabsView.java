package hr.fer.grafovi.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import hr.fer.grafovi.controller.LabsController;
import hr.fer.grafovi.controller.MainController;
import hr.fer.grafovi.utilities.Constants;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

public class LabsView extends JPanel implements PropertyChangeListener
{
	private static final long	serialVersionUID	= 1L;
	
	private static final String POVEZANOST = "lab 3";
	private static final String DIJKSTRA = "lab 4";
	private static final String TRGOVACKI_PUTNIK = "lab 5";
	private static final String RAZAPINJUCA_STABLA = "lab 6";
	private static final String MIN_RAZAPINJUCE_STABLO = "lab 7";
	private static final String PLANARNOST = "lab 8";
	private static final String BOJANJE_VRHOVA = "lab 9";
	private static final String BOJANJE_BRIDOVA = "lab 10";
	private static final String SETNJA = "lab 11";
	private static final String SPARIVANJE = "lab 12";
	
	private MainController ctrl;
	private LabsController labsCtrl;
	
	private TextListener textListener = new TextListener();
	private SolveListener solveListener = new SolveListener();
	
	private SpinnerNumberModel sourceSpinnerModel = new SpinnerNumberModel(0,0,0,1);
	private SpinnerNumberModel sinkSpinnerModel = new SpinnerNumberModel(0,0,0,1);
	
	public LabsView()
	{
		this.ctrl = MainController.ctrl;
		this.labsCtrl = MainController.labsCtrl;
		initGUI();
	}

	private void initGUI()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		addLab("Povezanost: ", POVEZANOST);
		addLab("Dijkstra: ", DIJKSTRA);
		addLab("Trgovacki putnik: ", TRGOVACKI_PUTNIK);
		addLab("Razapinjuca stabla", RAZAPINJUCA_STABLA);
		addLab("Minimalno razapinjuce stablo: ", MIN_RAZAPINJUCE_STABLO);
		addLab("Planarnost: ", PLANARNOST);
		addLab("Bojanje vrhova: ", BOJANJE_VRHOVA);
		addLab("Bojanje bridova", BOJANJE_BRIDOVA);
		addLab("Setnja: ", SETNJA);
		addLab("Sparivanje: ", SPARIVANJE);
	}
	
	private void addLab(String labelText, String actionCommand)
	{
		JPanel panel = new JPanel();
		JButton textButton = new JButton("Zadatak");
		JButton solveButton = new JButton("Rijesi");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		textButton.addActionListener(textListener);
		solveButton.addActionListener(solveListener);
		
		textButton.setActionCommand(actionCommand);
		solveButton.setActionCommand(actionCommand);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textButton.getMaximumSize().height));
		
		panel.add(new JLabel(labelText));
		panel.add(Box.createHorizontalGlue());
		if (actionCommand.equals(DIJKSTRA))
		{
			JSpinner spinner = new JSpinner(sourceSpinnerModel);
			spinner.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			panel.add(spinner);
			spinner = new JSpinner(sinkSpinnerModel);
			spinner.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			panel.add(spinner);
		}
		panel.add(textButton);
		panel.add(Box.createRigidArea(new Dimension(5,0)));
		panel.add(solveButton);
		
		panel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
		
		this.add(panel);
	}
	
	private class TextListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ctrl.clearInfo();
			if (e.getActionCommand().equals(POVEZANOST))
				System.out.println(Constants.POVEZANOST_TEKST);
			else if (e.getActionCommand().equals(DIJKSTRA))
				System.out.println(Constants.DIJKSTRA_TEKST);
			else if (e.getActionCommand().equals(TRGOVACKI_PUTNIK))
				System.out.println(Constants.TRGOVACKI_PUTNIK_TEKST);
			else if (e.getActionCommand().equals(RAZAPINJUCA_STABLA))
				System.out.println(Constants.RAZAPINJUCA_STABLA_TEKST);
			else if (e.getActionCommand().equals(MIN_RAZAPINJUCE_STABLO))
				System.out.println(Constants.MIN_RAZAPINJUCE_STABLO_TEKST);
			else if (e.getActionCommand().equals(PLANARNOST))
				System.out.println(Constants.PLANARNOST_TEKST);
			else if (e.getActionCommand().equals(BOJANJE_VRHOVA))
				System.out.println(Constants.BOJANJE_VRHOVA_TEKST);
			else if (e.getActionCommand().equals(BOJANJE_BRIDOVA))
				System.out.println(Constants.BOJANJE_BRIDOVA_TEKST);
			else if (e.getActionCommand().equals(SETNJA))
				System.out.println(Constants.SETNJA_TEKST);
			else if (e.getActionCommand().equals(SPARIVANJE))
				System.out.println(Constants.SPARIVANJE_TEKST);
		}
		
	}
	
	private class SolveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			ctrl.clearInfo();
			if (e.getActionCommand().equals(POVEZANOST))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(DIJKSTRA))
			{
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						labsCtrl.startDijkstra(sourceSpinnerModel.getNumber().intValue(), sinkSpinnerModel.getNumber().intValue());
					}
				}).start();
			}
			else if (e.getActionCommand().equals(TRGOVACKI_PUTNIK))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(RAZAPINJUCA_STABLA))
			{
				new Thread(new Runnable()
				{
				
					@Override
					public void run()
					{
						labsCtrl.countSpanningTrees();
					}
				}).start();
			}
			else if (e.getActionCommand().equals(MIN_RAZAPINJUCE_STABLO))
			{
				new Thread(new Runnable()
				{
				
					@Override
					public void run()
					{
						labsCtrl.startKruskal();
					}
				}).start();
			}
			else if (e.getActionCommand().equals(PLANARNOST))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(BOJANJE_VRHOVA))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(BOJANJE_BRIDOVA))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(SETNJA))
			{
				// TODO
			}
			else if (e.getActionCommand().equals(SPARIVANJE))
			{
				// TODO
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		sourceSpinnerModel.setMaximum((Integer)evt.getNewValue());
		sinkSpinnerModel.setMaximum((Integer)evt.getNewValue());
	}


}
