package puzzle.stockexchange;

import java.util.Arrays;
import java.util.Scanner;

public class StockExchange
{

    public static void main(String[] args)
    {
	    // write your code here
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of transactions: ");
        int numTransactions = sc.nextInt();
        System.out.print("Enter the number of days: ");
        int numDays = sc.nextInt();
        int[] price = new int[numDays];
        System.out.print("Enter share prices for each day: ");
        for (int i = 0; i < numDays && sc.hasNextInt(); i++)
        {
            price[i] = sc.nextInt();
        }

        System.out.println("numTransactions: " + numTransactions + ", numDays: " + numDays);
        System.out.println(Arrays.toString(price));

        System.out.println("Max profit: " + computeMaxProfit(numTransactions, numDays, price));
    }

    public static int computeMaxProfit(int numTrans, int numDays, int[] price)
    {
        int[][] scratch = new int[numTrans+1][numDays];

        for (int i = 0; i <= numTrans; i++)
        {
            scratch[i][0] = 0;
        }
        for (int i = 0; i < numDays; i++)
        {
            scratch[0][i] = 0;
        }

        for (int i = 1; i <= numTrans; i++)
        {
            int maxPrev = Integer.MIN_VALUE;
            for (int j = 1; j < numDays; j++)
            {
                if ((j > 0) && (maxPrev < -price[j-1] + scratch[i-1][j-1]))
                {
                    maxPrev = -price[j-1] + scratch[i-1][j-1];
                }

                scratch[i][j] = (scratch[i][j-1] > (maxPrev+price[j]))? scratch[i][j-1] : (maxPrev+price[j]);
            }
        }
        System.out.println(Arrays.deepToString(scratch));
        return scratch[numTrans][numDays-1];
    }

}
