package hr.fer.grafovi.view;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class InfoView extends JPanel implements PropertyChangeListener
{
	private static final long	serialVersionUID	= 1L;
	
	private JTextArea info;
	
	public InfoView()
	{
		initGUI();
		redirectSystemOut(info);
	}

	private void initGUI()
	{
		this.setLayout(new GridLayout(1,1));
		info = new JTextArea();
		info.setRows(4);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(new JScrollPane(info));
		this.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
	}

	private void redirectSystemOut(JTextArea output)
	{
		OutputStream out = new OutputStream()
		{
		
			@Override
			public void write(int b) throws IOException
			{
				updateTextArea(String.valueOf((char)b));
			}
			@Override
			public void write(byte[] b, int off, int len) throws IOException
			{
				updateTextArea(new String(b, off, len));
			}
			@Override
			public void write(byte[] b) throws IOException
			{
				write(b, 0, b.length);
			}
		};
		System.setOut(new PrintStream(out, true));
	}

	private void updateTextArea(final String text)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
		
			@Override
			public void run()
			{
				info.append(text);
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		info.setText("");
	}
}