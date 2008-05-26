package hr.fer.zemris.java.tecaj_8.vjezba.view;

import hr.fer.zemris.java.tecaj_8.vjezba.Line;
import hr.fer.zemris.java.tecaj_8.vjezba.controller.DrawingController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Pogled koji prikazuje izgled slike koristeci komponentu DrawingCanvas i 
 * omogucuje crtanje linija misem
 * @author Hrvoje
 */
public class DrawingView2 extends JFrame implements DrawingView{

	private static final long serialVersionUID = 1L;
	
	private DrawingCanvas canvas;
	private DrawingController controller;
	private JButton colorButton = new JButton("Izaberi boju");
	private JPanel colorsample = new JPanel();
	private Color boja = Color.BLACK;
	private Line l;

	/**
	 * Instancira novi primjerak klase
	 */
	public DrawingView2()
	{
		initGUI();
	}
	
	/**
	 * Metoda koja se zove pri instanciranju klase DrawingView2. Stvara se
	 * DrawingCanvas i izbornik za boje. Dodaju se i Mouse listeneri koji 
	 * sluze za crtanje linija po ekranu
	 */
	private void initGUI()
	{
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		canvas = new DrawingCanvas();
		canvas.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				l = new Line(e.getX(), e.getY(), e.getX(), e.getY(), boja);
				controller.addLine(l);
				canvas.repaint(e.getX(), e.getY(), 1, 1);
			}
		});
		canvas.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				l.setX2(e.getX());
				l.setY2(e.getY());
				controller.refresh();
				crtaj();
			}
			/**
			 * Pomocna metoda pomocu koje se ne iscrtava cijela slika ponovno, nego samo 
			 * dio koji se mijenja
			 */
			private void crtaj()
			{
				int x = Math.min(l.getX1(), l.getX2());
				int y = Math.min(l.getY1(), l.getY2());
				int width = Math.abs(l.getX1() - l.getX2());
				int height = Math.abs(l.getY1() - l.getY2());
				canvas.repaint(x, y, width, height);
			}
		});
		
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		JLabel label = new JLabel(" Boja: ");
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label);
		panel.add(colorsample);
		panel.add(colorButton);
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		colorButton.addActionListener(new ColorListener());
		colorsample.setBackground(boja);
		
		this.getContentPane().add(canvas, BorderLayout.CENTER);
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
	 * Metoda koja se zove kad dodje do promjene u modelu. Pri pozivu
	 * metode ponovno se iscrtavaju sve linije 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt)
	{
		try{
			canvas.setLinije((Set<Line>)evt.getNewValue());
		}catch (ClassCastException e){
			return;
		}
		canvas.repaint();
	}
	
	/**
	 * Action Listener za gumb kojim se bira boja linije
	 */
	public class ColorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Color temp = JColorChooser.showDialog(DrawingView2.this, "Boja linije", boja);
			if (temp == null)
				return;
			boja = temp;
			colorsample.setBackground(boja); 
		}
	}
}
