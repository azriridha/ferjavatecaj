package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrEcho implements Instruction {
	
	private int indexRegistra;
	
	public InstrEcho(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 1)
			throw new IllegalArgumentException("ECHO: Expected 1 arguments");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("ECHO: Type mismatch for argument 0!");
		
		this.indexRegistra = ((Integer)arguments.get(0).getValue()).intValue();
	}	
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getRegisters().getRegisterValue(indexRegistra);
		System.out.print(value);
		return false;
	}

}
