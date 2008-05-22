package hr.fer.zemris.java.tecaj_8;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class Tablice extends JFrame {

	private static final long serialVersionUID = 1L;

	public Tablice() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		createGUI();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createGUI() {
		final MojModelTablice model = new MojModelTablice();
		JTable table1 = new JTable(model);
		JTable table2 = new JTable(model);
		JPanel ploca = new JPanel(new GridLayout(1,2));
		ploca.add(new JScrollPane(table1));
		ploca.add(new JScrollPane(table2));
		JButton dodaj = new JButton("dodaj redak u tablicu");
		dodaj.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Random generator = new Random();
				String ime = "Ime"+generator.nextInt(1000);
				Integer godine = new Integer(15 + generator.nextInt(11));
				Boolean naFeru = Boolean.valueOf(generator.nextBoolean());
				model.dodajRedak(ime, godine, naFeru);
			}
		
		});
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(ploca, BorderLayout.CENTER);
		this.getContentPane().add(dodaj, BorderLayout.SOUTH);
	}
	
	private static class MojModelTablice implements TableModel
	{
		private List<TableModelListener> listeners = new ArrayList<TableModelListener>();

		List<Object[]> podaci = new ArrayList<Object[]>();

		String[] naziviStupaca = new String[] {
				"Osoba", "Starost", "Na FER-u"
		};
		
		
		public MojModelTablice(){
			podaci.add(new Object[]{"ivana", 22, true});
			podaci.add(new Object[]{"ivan", 23, true});
			podaci.add(new Object[]{"anita", 21, false});
		}
		
		public void dodajRedak(String ime, Integer godine, Boolean naFeru){
			podaci.add(new Object[]{ime, godine, naFeru});
			TableModelEvent dogadaj = new TableModelEvent(
					this,
					podaci.size() - 1,
					podaci.size() - 1,
					TableModelEvent.ALL_COLUMNS, 
					TableModelEvent.INSERT
					);
			fireTableEvent(dogadaj);
		}
		
		private void fireTableEvent(TableModelEvent dogadaj) {
			for (TableModelListener l: listeners)
				l.tableChanged(dogadaj);
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			System.out.println("zelim u listeners dodati objekt " + l);
			if (listeners.contains(l)) return;
			listeners.add(l);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch(columnIndex){
			case 0: return String.class;
			case 1: return Integer.class;
			case 2: return Boolean.class;
			}
			return null;
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return naziviStupaca[columnIndex];
		}

		@Override
		public int getRowCount() {
			return podaci.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return podaci.get(rowIndex)[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			listeners.remove(l);
			
		}

		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			podaci.get(rowIndex)[columnIndex] = value;
			TableModelEvent dogadaj = new TableModelEvent(
					this,
					rowIndex,
					rowIndex,
					columnIndex, 
					TableModelEvent.UPDATE
					);
			fireTableEvent(dogadaj);
		}
		
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new Tablice();
			}
		});
	}

}
