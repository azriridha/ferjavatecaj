package hr.fer.zemris.java.tecaj_3.dz2;

import java.util.List;

/**
 * Ovo sucelje predstavlja razred koji ce koristiti
 * parser asemblerske datoteke u svrhu stvaranja
 * definiranih instrukcija. 
 * 
 * @author marcupic
 */
public interface InstructionCreator {
	/**
	 * Metoda koja na temelju predanog imena i liste
	 * argumenata stvara odgovarajucu instrukciju
	 * 
	 * @param name ime instrukcije (ocitano iz datoteke s kodom)
	 * @param arguments lista argumenata
	 * @return novu instrukciju
	 */
	public Instruction getInstruction(String name, List<InstructionArgument> arguments);
}
