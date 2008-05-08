package hr.fer.zemris.java.tecaj_6.vjezba;

import java.util.Stack;
/**
 * Klasa koja sluzi kao jednostavni kalkulator koji funkcionira na temelju stoga
 * 
 * @author Hrvoje Torbasinovic
 *
 */
public class Kalkulator {
	
	private Stack<Double> stog;
	
	/**
	 * Stvara novu instancu kalkulatora
	 */
	public Kalkulator()
	{
		stog = new Stack<Double>();
	}
	
	/**
	 * Dodaje operand na stog
	 * 
	 * @param d
	 */
	public void push(double d)
	{
		stog.push(d);
	}
	
	/**
	 * Mice sve operande sa stoga
	 */
	public void reset()
	{
		stog.clear();
	}
	
	/**
	 * Zbraja dva operanda s vrha stoga. 
	 * 
	 * @return Zbroj
	 * @throws EmptyStackException ako na stogu nema barem dva operanda
	 */
	public double add()
	{
		double a = stog.pop();
		double b = stog.pop();
		return stog.push(a + b);
	}
	
	/**
	 * Oduzima dva operanda s vrha stoga. 
	 * 
	 * @return Razlika
	 * @throws EmptyStackException ako na stogu nema barem dva operanda
	 */
	public double subtract()
	{
		double a = stog.pop();
		double b = stog.pop();
		return stog.push(b - a);
	}
	
	/**
	 * Mnozi dva operanda s vrha stoga. 
	 * 
	 * @return Umnozak
	 * @throws EmptyStackException ako na stogu nema barem dva operanda
	 */
	public double multiply()
	{
		double a = stog.pop();
		double b = stog.pop();
		return stog.push(a * b);
	}
	
	/**
	 * Dijeli dva operanda s vrha stoga. 
	 * 
	 * @return Kvocijent
	 * @throws EmptyStackException ako na stogu nema barem dva operanda
	 */
	public double divide()
	{
		double a = stog.pop();
		double b = stog.pop();
		return stog.push(b / a);
	}
	
	/**
	 * Racuna sinus operanda na vrhu stoga 
	 * 
	 * @return sinus
	 * @throws EmptyStackException ako na stogu nema barem jedan operand
	 */
	public double sin()
	{
		return stog.push(Math.sin(stog.pop()));
	}
	
	/**
	 * Racuna kosinus operanda na vrhu stoga 
	 * 
	 * @return kosinus
	 * @throws EmptyStackException ako na stogu nema barem jedan operand
	 */

	public double cos()
	{
		return stog.push(Math.cos(stog.pop()));
	}
	
	
	
}
