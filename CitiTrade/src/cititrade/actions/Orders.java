package cititrade.actions;

import cititrade.ordermanager.OrderManager;
import cititrade.ordermanager.OrderManager.OrderResult;

public class Orders {
	
	public static void main(String[] args){
		OrderResult or = OrderManager.getInstance().buyOrder("AAPL", 10.00, 50);
	}

}
