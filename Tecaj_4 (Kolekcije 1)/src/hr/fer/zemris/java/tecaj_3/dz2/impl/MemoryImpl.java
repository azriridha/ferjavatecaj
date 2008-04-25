package hr.fer.zemris.java.tecaj_3.dz2.impl;

import java.util.*;

import hr.fer.zemris.java.tecaj_3.dz2.Memory;

public class MemoryImpl implements Memory {
	
	private List<Object> memory;
	
	public MemoryImpl(int memsize)
	{
		memory = new ArrayList<Object>(memsize);
		for (int i = 0; i < memsize; i++)
			memory.add(i, null);
	}

	@Override
	public Object getLocation(int location) {
		if (location < 0 || location >= memory.size())
			throw new IllegalArgumentException("Illegal memory location: " + location);
		return memory.get(location);
	}

	@Override
	public void setLocation(int location, Object value) {
		if (location < 0 || location >= memory.size())
			throw new IllegalArgumentException("Illegal memory location: " + location);
		memory.set(location, value);
	}

}
