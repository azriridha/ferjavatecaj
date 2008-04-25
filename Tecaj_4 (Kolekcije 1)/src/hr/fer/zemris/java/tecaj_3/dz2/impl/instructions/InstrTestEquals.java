package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrTestEquals implements Instruction {

	private int indexRx;
	private int indexRy;
	
	public InstrTestEquals(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 2)
			throw new IllegalArgumentException("TESTEQUALS: Expected 3 arguments");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("TESTEQUALS: Type mismatch for argument 0");
		if (!arguments.get(1).isRegister())
			throw new IllegalArgumentException("TESTEQUALS: Type mismatch for argument 1");
		
		this.indexRx = ((Integer)arguments.get(0).getValue()).intValue();
		this.indexRy = ((Integer)arguments.get(1).getValue()).intValue();
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRx);
		Object value2 = computer.getRegisters().getRegisterValue(indexRy);
		if (value1.equals(value2))
			computer.getRegisters().setFlag(true);
		else
			computer.getRegisters().setFlag(false);
		return false;
	}

}
