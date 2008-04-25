package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrLoad implements Instruction {

	private int indexRegistra;
	private int memorijskaAdresa;
	
	public InstrLoad(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 2)
			throw new IllegalArgumentException("LOAD: Expected 2 arguments");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("LOAD: Type mismatch for argument 0!");
		if (!arguments.get(1).isNumber())
			throw new IllegalArgumentException("LOAD: Type mismatch for argument 1!");
				
		this.indexRegistra = ((Integer)arguments.get(0).getValue()).intValue();
		this.memorijskaAdresa = ((Integer)arguments.get(1).getValue()).intValue();
	}	
	
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getMemory().getLocation(memorijskaAdresa);
		computer.getRegisters().setRegisterValue(indexRegistra, value);
		return false;
	}

}
