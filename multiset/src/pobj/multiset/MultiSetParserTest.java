package pobj.multiset;

public class MultiSetParserTest {
	public static void main(String[] args) {
		//MultiSetParser p = new MultiSetParser();
		String file = "data/testparser.txt";
		MultiSet<String> multi;
		try {
			 multi = MultiSetParser.parse(file);
			 System.out.println(multi.toString());
		} catch (InvalidMultiSetFormat e) {
			System.out.println(e.toString());
		}
		
	}
}
