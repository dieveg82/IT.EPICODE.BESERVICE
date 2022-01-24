package it.epicode.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportCsvDb {

	final String PATH = "C:\\Users\\Diego\\git\\IT.EPICODE.BESERVICE\\filecsv\\comuni-italiani.csv";
	final String PATH2 = "C:\\Users\\Diego\\git\\IT.EPICODE.BESERVICE\\filecsv\\province-italiane.csv";
	
	public ImportCsvDb () {}
	
	public List<String> stringFileComune () throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(PATH));
		
		List<String> listaLinea = new ArrayList<>();
		String line = reader.readLine();
		
		while(line!=null) {
			line = reader.readLine();
		     listaLinea.add(line);
		}
		return listaLinea;
	}
	
	public List<String> stringFileProvincia () throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(PATH2));
		
		List<String> listaLinea = new ArrayList<>();
		String line = reader.readLine();
		
		while(line!=null) {
			line = reader.readLine();
		     listaLinea.add(line);
		}
		return listaLinea;
	}
}
