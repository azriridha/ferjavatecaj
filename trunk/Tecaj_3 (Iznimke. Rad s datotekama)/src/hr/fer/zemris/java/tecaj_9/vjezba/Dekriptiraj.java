package hr.fer.zemris.java.tecaj_9.vjezba;

import java.io.*;

public class Dekriptiraj {
	public static void main(String[] args) {
		
		if (args.length != 3)
		{
			System.out.println("pogresan broj argumenata");
			System.exit(1);
		}
		
		File fCri = new File(args[0]);
		File fKey = new File(args[1]);
		File fDest = new File(args[2]);
		
		byte[] key = new byte[(int)fKey.length()];
		try{
			BufferedInputStream brKey = new BufferedInputStream(new FileInputStream(fKey));
			if (brKey.read(key) != key.length)
				System.out.println("Upozorenje: nije procitana cijela datoteka");
		} catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		try{
			int buf;
			BufferedInputStream bisCri = new BufferedInputStream(new FileInputStream(fCri));
			BufferedOutputStream bosDest = new BufferedOutputStream(new FileOutputStream(fDest));
			while ((buf = bisCri.read()) != -1)
			{
				buf = (buf << 8) | bisCri.read();
				bosDest.write(key[buf]);
			}
			bisCri.close();
			bosDest.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}

}
