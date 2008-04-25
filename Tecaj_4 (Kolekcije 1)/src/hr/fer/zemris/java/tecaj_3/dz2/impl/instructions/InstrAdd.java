package hr.fer.zemris.java.tecaj_3.dz2.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Instruction;
import hr.fer.zemris.java.tecaj_3.dz2.InstructionArgument;

public class InstrAdd implements Instruction {

	private int indexRx;
	private int indexRy;
	private int indexRz;
	
	public InstrAdd(List<InstructionArgument> arguments)
	{
		if (arguments.size() != 3)
			throw new IllegalArgumentException("ADD: Expected 3 arguments");
		if (!arguments.get(0).isRegister())
			throw new IllegalArgumentException("ADD: Type mismatch for argument 0");
		if (!arguments.get(1).isRegister())
			throw new IllegalArgumentException("ADD: Type mismatch for argument 1");
		if (!arguments.get(2).isRegister())
			throw new IllegalArgumentException("ADD: Type mismatch for argument 2");
		
		this.indexRx = ((Integer)arguments.get(0).getValue()).intValue();
		this.indexRy = ((Integer)arguments.get(1).getValue()).intValue();
		this.indexRz = ((Integer)arguments.get(2).getValue()).intValue();
	}
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRy);
		Object value2 = computer.getRegisters().getRegisterValue(indexRz);
		if (!(value1 instanceof Integer))
			throw new ClassCastException("ADD: u registru " + indexRy + " se ne nalazi broj");
		if (!(value2 instanceof Integer))
			throw new ClassCastException("ADD: u registru " + indexRz + " se ne nalazi broj");
		computer.getRegisters().setRegisterValue(indexRx, 
				(Integer)value1 + (Integer)value2);
		return false;
	}

}
