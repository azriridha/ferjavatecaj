package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrMove implements Instruction {

	private int indexRx;
	private int indexRy;
	
	public InstrMove(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 2)
			throw new IllegalArgumentException("MOVE: Expected 3 arguments");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("MOVE: Type mismatch for argument 0");
		if (!arguments.get(1).isRegister())
			throw new IllegalArgumentException("MOVE: Type mismatch for argument 1");
		
		this.indexRx = ((Integer)arguments.get(0).getValue()).intValue();
		this.indexRy = ((Integer)arguments.get(1).getValue()).intValue();
	}
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getRegisters().getRegisterValue(indexRy);
		computer.getRegisters().setRegisterValue(indexRx, value);
		return false;
	}

}
