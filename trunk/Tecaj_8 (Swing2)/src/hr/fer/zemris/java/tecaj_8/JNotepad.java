package hr.fer.zemris.java.tecaj_8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class JNotepad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea editor;
	private Map<String, Action> akcije = new HashMap<String, Action>();
	
	public JNotepad(){
		setTitle("JNotepad");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		createGUI();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void createGUI() {
		editor = new JTextArea();
		JScrollPane jsp = new JScrollPane(editor);
		jsp.setPreferredSize(new Dimension(600,300));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jsp, BorderLayout.CENTER);
		
		createActions();
		createMenus();
		createToolbar();
	}

	private void createActions() {
		Action open = new AbstractAction("Open") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Otvaram datoteku");
				JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File("c:\\"));
				jfc.setMultiSelectionEnabled(false);
				jfc.showOpenDialog(editor);
				System.out.println(jfc.getSelectedFile().getName());
			}
		};
		akcije.put("!open", open);
		open.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
		open.putValue(
				Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)
				);
		open.putValue(Action.SHORT_DESCRIPTION, "Ovime se otvara nova datoteka");
		
		Action save = new AbstractAction("Save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Snimam datoteku");
			}
		};
		akcije.put("!save", save);
		save.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		save.putValue(
				Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)
				);
		save.putValue(Action.SHORT_DESCRIPTION, "Ovime se snima nova datoteka");
		
		Action cut = new AbstractAction("Cut") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Akcija cut");
				editor.cut();
			}
		};
		akcije.put("!cut", cut);
		cut.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
		cut.putValue(
				Action.ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK)
				);
		cut.putValue(Action.SHORT_DESCRIPTION, "Akcija cut");
	}

	private void createToolbar() {
		JToolBar toolbar = new JToolBar("Alatna traka");
		toolbar.add(new JButton("Open"));
		toolbar.add(new JButton("Save"));
		toolbar.setRollover(true);
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	private void createMenus() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.add(new JMenuItem(akcije.get("!open")));
		file.add(new JMenuItem(akcije.get("!save")));
		file.addSeparator();
		file.add(new JMenuItem("Close"));
		
		menubar.add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.add(new JMenuItem(akcije.get("!cut")));
		edit.add(new JMenuItem("Copy"));
		edit.add(new JMenuItem("Paste"));
		
		menubar.add(edit);
		this.setJMenuBar(menubar);
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new WindowsLookAndFeel());
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new JNotepad();
			}
		});
	}

}
