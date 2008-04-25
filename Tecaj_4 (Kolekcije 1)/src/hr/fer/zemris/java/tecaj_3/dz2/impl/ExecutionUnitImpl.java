package hr.fer.zemris.java.tecaj_3.dz2.impl;

import hr.fer.zemris.java.tecaj_3.dz2.*;

public class ExecutionUnitImpl implements ExecutionUnit {

	@Override
	public boolean go(Computer computer) {
		Instruction instrukcija;
		try{
			computer.getRegisters().setProgramCounter(0);
			boolean kraj = false;
			while (!kraj)
			{
				//dohvati instrukciju iz memorije s lokacije program countera
				try {
				instrukcija = (Instruction) computer.getMemory().getLocation(
						computer.getRegisters().getProgramCounter());
				} catch (ClassCastException e){
					throw new ClassCastException("Na memorijskoj lokaciji " +
							computer.getRegisters().getProgramCounter() +
							" se ne nalazi instrukcija");
				}
				//uvecaj program counter za 1
				computer.getRegisters().incrementProgramCounter();
				//izvrsi instrukciju; ako je instrukcija vratila true, prekini petlju
				kraj = instrukcija.execute(computer);
			}
			return true	;
		} catch (ClassCastException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

}
