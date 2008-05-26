package hr.fer.zemris.java.tecaj_8.vjezba.view;

import hr.fer.zemris.java.tecaj_8.vjezba.Line;
import hr.fer.zemris.java.tecaj_8.vjezba.controller.DrawingController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Pogled koji prikazuje linije u listi i omogucava 
 * dodavanje novih linija i njihovo brisanje
 * 
 * @author Hrvoje
 */
public class DrawingView1 extends JFrame implements DrawingView {

	private static final long serialVersionUID = 1L;
	private JList listaLinija;
	private DefaultListModel listModel;
	private JTextField x1 = new JTextField("0");
	private JTextField y1 = new JTextField("0");
	private JTextField x2 = new JTextField("0");
	private JTextField y2 = new JTextField("0");
	private JButton addButton = new JButton("Dodaj liniju");
	private JButton delButton = new JButton("Izbrisi liniju");
	private JButton colorButton = new JButton("Izaberi boju");
	private JPanel colorsample = new JPanel();
	private DrawingController controller;
	private Color boja = Color.BLACK;
	
	/**
	 * Instancira novi primjerak klase
	 */
	public DrawingView1()
	{
		initGUI();
	}
	
	/**
	 * Metoda koja se zove pri instanciranju klase DrawingView1. U JFrame dodaje JList gdje
	 * ce se prikazivati linije, opcije za stvaranje nove linije i dva gumba: za dodavanje linije
	 * i brisanje postojecih linija 
	 */
	private void initGUI()
	{
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());	
		listModel = new DefaultListModel();
		listaLinija = new JList(listModel);
		colorsample.setBackground(boja);
		colorButton.addActionListener(new ColorListener());
		
		JPanel linijaPanel = new JPanel(new GridLayout(3,3,5,5));
		linijaPanel.add(new JLabel(" Pocetna tocka: "));
		linijaPanel.add(x1);
		linijaPanel.add(y1);
		linijaPanel.add(new JLabel(" Krajnja tocka: "));
		linijaPanel.add(x2);
		linijaPanel.add(y2);
		linijaPanel.add(new JLabel(" Boja: "));
		linijaPanel.add(colorsample);
		linijaPanel.add(colorButton);
		linijaPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		
		
		JPanel buttonPanel = new JPanel();
		addButton.addActionListener(new AddListener());
		buttonPanel.add(addButton);
		delButton.addActionListener(new DelListener());
		buttonPanel.add(delButton);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(linijaPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.getContentPane().add(new JScrollPane(listaLinija), BorderLayout.CENTER);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
	}

	/**
	 * Postavlja referencu na kontroler
	 * @param controller
	 */
	public void setController(DrawingController controller)
	{
		this.controller = controller;
	}
	
	/**
	 * Metoda koja se zove kad dodje do promjene u modelu. Iz liste se brisu sve linije
	 * i lista se ponovno puni linijama iz modela
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt)
	{
		Set<Line> linije;
		listModel.clear();
		try
		{
			linije = (Set<Line>)(evt.getNewValue());
		} catch(ClassCastException e)
		{
			return;
		}
		for (Line line : linije)
		{
			listModel.addElement(line);
		}
	}
	
	/**
	 * Action Listener za gumb kojim se dodaje nova linija. Nova linija se stvara na temelju upisane 
	 * pocetne i krajnje tocke, te izabrane boje. Ako neka koordinata tocke nije broj, iskace 
	 * upozorenje i ne stvara se nova linija.
	 */
	public class AddListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try{
				controller.addLine(new Line(
					Integer.parseInt(x1.getText()), 
					Integer.parseInt(y1.getText()), 
					Integer.parseInt(x2.getText()), 
					Integer.parseInt(y2.getText()), 
					boja));
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(
						DrawingView1.this, "<html>NumberFormatException:<br>" + e.getMessage() + "</html>", 
						"Error", 
						JOptionPane.ERROR_MESSAGE
						);
			}
		}
	}
  
	/**
	 * Action Listener za gumb koji se brisu odabrane linije iz liste. Ako nije odabrana nijedna 
	 * linija, nista se ne dogadja
	 */
	public class DelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Object[] linije = listaLinija.getSelectedValues();
			for (Object line : linije)
				controller.removeLine((Line)line);
		}
	}
	
	/**
	 * Action Listener za gumb kojim se bira boja linije
	 */
	public class ColorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Color temp = JColorChooser.showDialog(DrawingView1.this, "Boja linije", boja);
			if (temp == null)
				return;
			boja = temp;
			colorsample.setBackground(boja); 
		}
	}

}
