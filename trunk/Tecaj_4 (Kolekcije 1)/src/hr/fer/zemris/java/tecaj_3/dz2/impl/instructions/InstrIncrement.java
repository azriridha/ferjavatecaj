package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrIncrement implements Instruction {

private int indexRx;
	
	public InstrIncrement(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 1)
			throw new IllegalArgumentException("INCREMENT: Expected 1 argument");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("INCREMENT: Type mismatch for argument 0");
		
		this.indexRx = ((Integer)arguments.get(0).getValue()).intValue();
		
	}
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getRegisters().getRegisterValue(indexRx);
		if (!(value instanceof Integer))
			throw new ClassCastException("INCREMENT: u registru " + indexRx + " se ne nalazi broj");
		computer.getRegisters().setRegisterValue(indexRx, (Integer)value + 1);
		return false;
	}
}
