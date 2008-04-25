package hr.fer.zemris.java.tecaj_3.dz2.impl;

import hr.fer.zemris.java.tecaj_3.dz2.Computer;
import hr.fer.zemris.java.tecaj_3.dz2.Memory;
import hr.fer.zemris.java.tecaj_3.dz2.Registers;

public class ComputerImpl implements Computer {

	private Memory memory;
	private Registers registers;
	
	public ComputerImpl(int memSize, int numberOfRegisters)
	{
		memory = new MemoryImpl(memSize);
		registers = new RegistersImpl(numberOfRegisters);
	}
	@Override
	public Memory getMemory() {
		return memory;
	}

	@Override
	public Registers getRegisters() {
		return registers;
	}

}
