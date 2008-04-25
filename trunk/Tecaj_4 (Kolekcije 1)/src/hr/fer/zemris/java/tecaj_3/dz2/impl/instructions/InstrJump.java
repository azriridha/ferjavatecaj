package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrJump implements Instruction {

	private int lokacija;
	
	public InstrJump(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 1)
			throw new IllegalArgumentException("JUMP: Expected 1 argument");
		if (!arguments.get(0).isNumber())
			throw new IllegalArgumentException("JUMP: Type mismatch for argument 0");
		
		this.lokacija = ((Integer)arguments.get(0).getValue()).intValue();
		
	}
	@Override
	public boolean execute(Computer computer) {
		computer.getRegisters().setProgramCounter(lokacija);
		return false;
	}

}
