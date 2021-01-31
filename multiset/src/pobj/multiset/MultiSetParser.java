package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat{
		MultiSet<String> m = new HashMultiSet<String>();
		BufferedReader bf=null;
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line ;
			while((line = bf.readLine())!=null) {
				String[] elem = line.split(":");
				
				if(elem.length!=2) {
					throw new InvalidMultiSetFormat("erreur du format");
				}
				String key = elem[0];
				int value = Integer.decode(elem[1]);
				m.add(key,value);
				

			}
			
			bf.close();
		} catch (FileNotFoundException e) {
			throw new InvalidMultiSetFormat("fichier introuvable ! ",e);
		}catch (IOException e) {
			throw new InvalidMultiSetFormat("erreur lecture fichier",e);
		}catch (NumberFormatException e) {
			throw new InvalidMultiSetFormat("value is not int  !", e);
		}
		
		return m;
	}
}
