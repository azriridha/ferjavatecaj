package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrJumpIfTrue implements Instruction {

	private int lokacija;
	
	public InstrJumpIfTrue(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 1)
			throw new IllegalArgumentException("JUMPIFTRUE: Expected 1 argument");
		if (!arguments.get(0).isNumber())
			throw new IllegalArgumentException("JUMPIFTRUE: Type mismatch for argument 0");
		
		this.lokacija = ((Integer)arguments.get(0).getValue()).intValue();
		
	}
	@Override
	public boolean execute(Computer computer) {
		if (computer.getRegisters().getFlag())
			computer.getRegisters().setProgramCounter(lokacija);
		return false;
	}
}
