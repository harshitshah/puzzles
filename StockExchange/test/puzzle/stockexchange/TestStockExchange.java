package puzzle.stockexchange;

import puzzle.stockexchange.StockExchange;

public class TestStockExchange
{
	public static void main(String[] args)
	{
		int[] prices1 = {1, 2, 3, 4, 5};
		assert StockExchange.computeMaxProfit(1, 5, prices1) == 4;
		assert StockExchange.computeMaxProfit(2, 5, prices1) == 4;
		assert StockExchange.computeMaxProfit(3, 5, prices1) == 4;
		assert StockExchange.computeMaxProfit(4, 5, prices1) == 4;

		int[] prices2 = {5, 11, 3, 50, 60, 90};
		assert StockExchange.computeMaxProfit(1, 6, prices2) == 87;
		assert StockExchange.computeMaxProfit(2, 6, prices2) == 93;
	}
}
