package hr.fer.zemris.java.tecaj_3.dz2.impl;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.ExecutionUnit;
import hr.fer.zemris.java.tecaj_3.dz2.parser.*;

public class Glavni {

	public static void main(String[] args) {

		Computer comp = new ComputerImpl(256, 16);
		
		try {
			ProgramParser.parse("./examples/asmprogram1.txt", 
					comp,
					new InstructionCreatorImpl("hr.fer.zemris.java.tecaj_3.dz2.impl.instructions")
			);
			ExecutionUnit exec = new ExecutionUnitImpl();
			exec.go(comp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
