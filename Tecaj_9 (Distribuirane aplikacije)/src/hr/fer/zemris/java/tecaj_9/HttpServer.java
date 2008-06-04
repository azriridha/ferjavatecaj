package hr.fer.zemris.java.tecaj_9;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {

	private int port;
	private File documentRoot;
	
	public HttpServer(int port, String documentRoot) throws IOException {
		this.port = port;
		this.documentRoot = new File(documentRoot);
		pokreni();
	}
	

	private void pokreni() throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("Slusam na: " + server.getLocalSocketAddress());
		while(true)
		{
			Socket klijent = server.accept();
			Obrada o = new Obrada(klijent);
			new Thread(o).start();
		}
	}
	
	private class Obrada implements Runnable{

		private Socket klijent;
		
		public Obrada(Socket klijent) {
			this.klijent = klijent;
		}

		@Override
		public void run(){
			try {
				BufferedInputStream is = new BufferedInputStream(klijent.getInputStream());
				BufferedOutputStream os = new BufferedOutputStream(klijent.getOutputStream());
				
				List<String> zaglavlje = procitajZaglavlje(is);
				String[] elementi = zaglavlje.get(0).split(" ");
				String datoteka = elementi[1];
				if (datoteka.equals("/")) 
					datoteka = "/index.html";
				vratiDatoteku(datoteka, os);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					klijent.close();
				} catch (IOException ignorable) {}
			}
			
		}

		private void vratiDatoteku(String datoteka, BufferedOutputStream os) throws IOException {
			File d = new File(documentRoot, datoteka.substring(1));
			
			String odgovor = "HTTP/1.1 200 OK\n" +
							"Content-Length: " + d.length() + "/n" +
							"Content-Type: text/plain\n" +
							"\n";
			os.write(odgovor.getBytes());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(d));
			byte[] spremnik = new byte[10*1024];
			while(true)
			{
				int r = bis.read(spremnik);
				if (r < 1)
					break;
				else {
					os.write(spremnik, 0, r);
				}
			}
			bis.close();
		}

		private List<String> procitajZaglavlje(BufferedInputStream is) throws IOException {
			List<String> zaglavlje = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while(true)
			{
				String line = br.readLine();
				if (line == null || line.length() == 0)
					break;
				zaglavlje.add(line);
			}
			return zaglavlje;
		}
		
	}

	public static void main(String[] args) throws IOException {
		new HttpServer(1234, "C:/javaTmp/");
	}

}
