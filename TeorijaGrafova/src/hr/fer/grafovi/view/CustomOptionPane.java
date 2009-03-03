package hr.fer.grafovi.view;

import hr.fer.grafovi.utilities.Constants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class CustomOptionPane implements ActionListener, Constants
{
	private int[] values;
	
	private final int VALUE = 10;
	private final int MIN = 1;
	private final int MAX = 10000;
	private final int STEP = 1;
	private final String OK = "Ok";
	private final String CANCEL = "Cancel";
	
	private int type;
	private JSpinner vrhoviSpinner;
	private JSpinner bridoviSpinner;
	private JSpinner rSpinner;
	private JSpinner sSpinner;
	private Box box;
	private JDialog dialog;
	private JCheckBox digraphCheckBox;
	private JCheckBox weightedCheckBox;

	
	private CustomOptionPane(int type)
	{
		super();
		this.type = type;
	}
	
	public static int[] showInputDialog(Component parent, int type)
	{
		if (parent == null)
			throw new IllegalArgumentException("showInputDialog: parent component ne smije biti null");
		CustomOptionPane pane = new CustomOptionPane(type);
		switch (type)
		{
		case COMPLETE: pane.createNormalPanel("Potpuni graf"); break;
		case WHEEL: pane.createNormalPanel("Kotac"); break;
		case CUBE: pane.createNormalPanel("Kocka"); break;
		case CYCLE: pane.createNormalPanel("Ciklus"); break;
		case BIPARTITE: pane.createBipartitePanel(); break;
		case RANDOM: pane.createRandomPanel(); break;
		default: throw new IllegalArgumentException("showInputDialog: pogresni argumenti");
		}
		JDialog dialog = pane.createDialog(parent);
		dialog.setVisible(true);
		return pane.getValues();
	}

	private JDialog createDialog(Component parent)
	{
		Frame toUse = (Frame)SwingUtilities.getAncestorOfClass(JDesktopPane.class, parent);
		dialog = new JDialog(toUse, "Novi graf");
		dialog.setContentPane(box);
		dialog.setModal(true);
		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		return dialog;		
	}

	private void createRandomPanel()
	{
		values = new int[4];
		box = new Box(BoxLayout.Y_AXIS);
		
		int maxBrid = MAX * (MAX - 1) / 2;
		vrhoviSpinner = new JSpinner(new SpinnerNumberModel(VALUE, MIN, MAX, STEP));
		bridoviSpinner = new JSpinner(new SpinnerNumberModel(2 * VALUE, MIN, maxBrid, STEP));
		
		vrhoviSpinner.setPreferredSize(new Dimension(60,20));
		bridoviSpinner.setPreferredSize(new Dimension(60,20));
		bridoviSpinner.setMaximumSize(new Dimension(60,20));
		
		Box vrhoviBox = new Box(BoxLayout.X_AXIS);
		vrhoviBox.add(new JLabel("broj vrhova: "));
		vrhoviBox.add(Box.createHorizontalStrut(100));
		vrhoviBox.add(vrhoviSpinner);
		vrhoviBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		Box bridoviBox = new Box(BoxLayout.X_AXIS);
		bridoviBox.add(new JLabel("broj bridova: "));
		bridoviBox.add(Box.createHorizontalGlue());
		bridoviBox.add(bridoviSpinner);
		bridoviBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		typePanel.add(new JLabel("Potpuni bipartitni graf :"));
		
		box.add(typePanel); 
		box.add(vrhoviBox);
		box.add(bridoviBox);
		box.add(makeCheckBoxPanel());
		box.add(makeButtonPanel());
	}

	private void createBipartitePanel()
	{
		values = new int[4];
		box = new Box(BoxLayout.Y_AXIS);
		
		rSpinner = new JSpinner(new SpinnerNumberModel(VALUE, MIN, MAX, STEP));
		sSpinner = new JSpinner(new SpinnerNumberModel(VALUE, MIN, MAX, STEP));
		
		JPanel rPanel = new JPanel();
		JPanel sPanel = new JPanel();
		rPanel.add(new JLabel("r = "));
		rPanel.add(rSpinner);
		sPanel.add(new JLabel("s = "));
		sPanel.add(sSpinner);
		
		JPanel spinnerPanel = new JPanel(new GridLayout(1,2));
		spinnerPanel.add(rPanel);
		spinnerPanel.add(sPanel);
		
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		typePanel.add(new JLabel("Potpuni bipartitni graf :"));
		
		box.add(typePanel);
		box.add(spinnerPanel);
		box.add(makeCheckBoxPanel());
		box.add(makeButtonPanel());
	}

	private void createNormalPanel(String text)
	{
		values = new int[3];
		box = new Box(BoxLayout.Y_AXIS);
		
		JPanel vrhoviPanel = new JPanel();
		String spinnerText;
		int max = MAX;
		if (type == CUBE)
		{
			spinnerText = "broj vrhova = 2^k, k = ";
			max = 13;
		}
		else 
			spinnerText = "broj vrhova: ";
		vrhoviSpinner = new JSpinner(new SpinnerNumberModel(VALUE, MIN, max, STEP));
		
		vrhoviPanel.add(new JLabel(spinnerText));
		vrhoviPanel.add(vrhoviSpinner);
		
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		typePanel.add(new JLabel(text + " :"));
		
		box.add(typePanel);
		box.add(vrhoviPanel);
		box.add(makeCheckBoxPanel());
		box.add(makeButtonPanel());
	}
	
	private JPanel makeCheckBoxPanel()
	{
		JPanel checkBoxPanel = new JPanel();
		digraphCheckBox = new JCheckBox("Digraf");
		weightedCheckBox = new JCheckBox("Tezinski graf");
		
		checkBoxPanel.add(digraphCheckBox);
		checkBoxPanel.add(weightedCheckBox);
		
		return checkBoxPanel;
	}
	
	private JPanel makeButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		
		JButton okButton = new JButton(OK);
		JButton cancelButton = new JButton(CANCEL);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		return buttonPanel;
	}

	private int[] getValues()
	{
		return values;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(OK))
		{
			if (digraphCheckBox.isSelected())
				values[0] = 1;
			if (weightedCheckBox.isSelected())
				values[1] = 1;
			switch (type)
			{
			case COMPLETE:
			case WHEEL:
			case CUBE:
			case CYCLE: values[2] = (Integer)vrhoviSpinner.getValue(); 
						break;
			case BIPARTITE: values[2] = (Integer)rSpinner.getValue(); 
							values[3] = (Integer)sSpinner.getValue(); 
							break;
			case RANDOM: values[2] = (Integer)vrhoviSpinner.getValue(); 
						 values[3] = (Integer)bridoviSpinner.getValue(); 
						 break;
			default: values = null;
			}
		}
		else if (e.getActionCommand().equals(CANCEL))
			values = null;
		
		dialog.setVisible(false);
		
	}
}
