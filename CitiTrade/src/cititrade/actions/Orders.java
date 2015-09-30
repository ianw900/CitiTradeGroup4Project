package cititrade.actions;

import cititrade.ordermanager.OrderManager;
import cititrade.ordermanager.OrderManager.OrderResult;

public class Orders {
	
	OrderManager.OrderResult order = new OrderResult();
	
	order.sellOrder("INT", 56.3, 500);

}
