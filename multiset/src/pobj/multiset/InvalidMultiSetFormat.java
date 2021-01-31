package pobj.multiset;

public class InvalidMultiSetFormat extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7150433486665878929L;

	public InvalidMultiSetFormat(String s) {
		super(s);
	}
	public InvalidMultiSetFormat(String s,Throwable cause) {
		super(s,cause);
	}
}
