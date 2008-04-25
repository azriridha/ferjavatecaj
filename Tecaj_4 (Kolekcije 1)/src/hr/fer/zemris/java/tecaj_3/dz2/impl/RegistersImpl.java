package hr.fer.zemris.java.tecaj_3.dz2.impl;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.tecaj_3.dz2.Registers;

public class RegistersImpl implements Registers {

	private List<Object> registers;
	private int pc;
	private boolean flag;
	
	public RegistersImpl(int numberOfRegisters)
	{
		registers = new ArrayList<Object>(numberOfRegisters);
		for (int i = 0; i < numberOfRegisters; i++)
			registers.add(i, null);
		flag = false;
	}
	
	@Override
	public boolean getFlag() {
		return flag;
	}

	@Override
	public int getProgramCounter() {
		return pc;
	}

	@Override
	public Object getRegisterValue(int index) {
		if (index < 0 || index >= registers.size())
			throw new IllegalArgumentException("Illegal register index");
		return registers.get(index);
	}

	@Override
	public void incrementProgramCounter() {
		pc++;
	}

	@Override
	public void setFlag(boolean value) {
		flag = value;
	}

	@Override
	public void setProgramCounter(int value) {
		pc = value;
	}

	@Override
	public void setRegisterValue(int index, Object value) {
		if (index < 0 || index >= registers.size())
			throw new IllegalArgumentException("Illegal register index");
		registers.set(index, value);
	}

}
