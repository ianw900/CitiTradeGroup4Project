package cititrade.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetStockSymbolsFromCSV {

	static List<String> stockSymbols = new ArrayList <>();

	public static List<String> getSymbols(){

		String csvFile = "C:/Users/Citi-2013-27/Downloads/companylist2.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] symbol = line.split(cvsSplitBy);
				stockSymbols.add(symbol[0]);
				//System.out.println("Symbol =" + country[0] );
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stockSymbols.remove(0);
		System.out.println(stockSymbols);
		//System.out.println("Done");
		return stockSymbols;
	}

}
