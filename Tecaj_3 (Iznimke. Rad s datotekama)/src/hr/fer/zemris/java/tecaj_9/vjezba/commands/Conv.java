package hr.fer.zemris.java.tecaj_9.vjezba.commands;
import java.io.*;

import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.CommandExecException;
import hr.fer.zemris.java.tecaj_9.zadace.ConsoleEngine.interfaces.ICommand;


public class Conv implements ICommand {

	@Override
	public void execCommand(String[] parameters, PrintStream out)
			throws CommandExecException, IllegalArgumentException {
		if (parameters.length != 4)
			throw new IllegalArgumentException("Pogresan broj argumenata");
		File src = new File(parameters[2]);
		File dest = new File(parameters[3]);
			
		BufferedReader br;
		BufferedWriter bw;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(src),parameters[0]));
		} catch (FileNotFoundException e) {
			throw new CommandExecException("Izvorisna datoteka ne postoji");
		} catch (UnsupportedEncodingException e) {
			throw new CommandExecException("Izvorisni encoding nije podrzan");
		}
		
		char[] rbuf = new char[(int)src.length()];
		//broj procitanih znakova
		int procitano;
		try {
			procitano = br.read(rbuf);
		} catch (IOException e) {
			throw new CommandExecException("Ne mogu procitati izvorisnu datoteku");
		} finally{
			try{
				br.close();
			} catch(IOException ignorable){}
		}
		
		char[] wbuf = new char[procitano];
		System.arraycopy(rbuf,0,wbuf,0,procitano);
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), parameters[1]));
		} catch (UnsupportedEncodingException e) {
			throw new CommandExecException("Odredisni encoding nije podrzan");
		} catch (FileNotFoundException e) {
			throw new CommandExecException("Odredisna datoteka ne moze biti kreirana");
		}
		
		try {
			bw.write(wbuf);
		
		} catch (IOException e) {
			throw new CommandExecException("Ne mogu zapisati u odredisnu datoteku");
		}finally{
			try{
				bw.close();
			} catch(IOException ignorable){}
		}
		System.out.println("Konverzija uspjesno obavljena");
	}

	@Override
	public String getCommandDescription() {
		return "conv <izvKodStr> <odredKodStr> <izvDat> <odredDat>\n" +
				"konvertira kodnu stranicu datoteke";
	}

	@Override
	public String getCommandName() {
		return "conv";
	}

}
