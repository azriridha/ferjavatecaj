package hr.fer.zemris.java.tecaj_9;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws IOException{
		ByteArrayOutputStream izlaz = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(izlaz);
		
		dos.writeShort(1);
		
		byte[] naredba = "QUOTE".getBytes("UTF-8");
		
		dos.writeShort(naredba.length);
		dos.write(naredba);
		
		dos.close();
		
		byte[] podaci = izlaz.toByteArray();
		
		DatagramPacket paket = new DatagramPacket(podaci, podaci.length);
		paket.setAddress(InetAddress.getByName("java.zemris.fer.hr"));
		paket.setPort(22222);

		DatagramSocket socket = new DatagramSocket();
		socket.send(paket);
		
		byte[] spremnik = new byte[1024];
		paket.setData(spremnik);
		
		socket.receive(paket);
		
		ByteArrayInputStream ulaz = new ByteArrayInputStream(
											paket.getData(),
											paket.getOffset(),
											paket.getLength()
											);
		DataInputStream dis = new DataInputStream(ulaz);
		
		short identifikator = dis.readShort();
		short velicinaCitata = dis.readShort();
	
		if (identifikator != 2)
		{
			System.out.println("dobio sam nesto neocekivano");
			System.exit(0);
		}
		
		byte[] podaciZaString = new byte[velicinaCitata];
		dis.readFully(podaciZaString);
		
		String poruka = new String(podaciZaString, "UTF-8");
		
		System.out.println(poruka);
	}

}
