package cititrade.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import cititrade.dal.DataAccess;

public class GetQuotes {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		int movAvg = 0;
		double bidTotal = 0, askTotal = 0, bidAvg = 0, askAvg = 0, bidTemp = 0, askTemp = 0;
		int ind = 0;	

		List<String> stockSymbols = GetStockSymbolsFromCSV.getSymbols();
		//double[] bidMoving = new double[captureSize];
		//double[] askMoving = new double[captureSize];


		System.out.println("\nSymbol, Ask Price, Bid Price");

		while(true){
			for(String s: stockSymbols){
				StringBuilder url = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");

				url.append(s);
				url.append("&f=sabc1c0o0p0&e=.csv");
				
				//symbol, askprice, bidprice, change, changepercent, open, close

				String theUrl = url.toString();
				URL obj = new URL(theUrl);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;

				while((inputLine = in.readLine()) != null)	//(essentially while(true))
				{
					//System.out.println(inputLine);
					String[] fields = inputLine.split(",");

					for(int i = 0; i < fields.length; i++){
						System.out.print(fields[i].replaceAll("\"", "").replaceAll("N/A", "0") + " | ");
					} 

					DataAccess.addStockQuote(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
					if(!fields[1].contains("N/A") && !fields[2].contains("N/A")){
						bidTotal += Double.parseDouble(fields[1]);
						askTotal += Double.parseDouble(fields[2]);
					}
					System.out.println("-----------------------------------");
				}			
			}
		}
		/*System.out.printf("Bid Price Average: %.2f", bidTotal/capture);	       
		System.out.printf("\nAsk Price Average: %.2f", askTotal/capture);
		System.out.printf("\nBid Price Moving Average (Last %s): %.2f", movAvg, bidAvg);
		System.out.printf("\nAsk Price Moving Average (Last %s): %.2f", movAvg, askAvg);
		kb.close();*/
	}
}
