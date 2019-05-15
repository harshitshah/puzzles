package puzzle.Coingame;

public class CoinGame
{

    private static class Result
    {
        private int player;
        private int numWays;

        public Result(int player, int numWays)
        {
            this.player = player;
            this.numWays = numWays;
        }

        public int getPlayer()
        {
            return player;
        }

        public int getNumWays()
        {
            return numWays;
        }

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof Result)) return false;

            Result p = (Result)o;
            if (p.getPlayer() == player && p.getNumWays() == numWays)
                return true;
            return false;
        }
    }


    private static Result checkBaseCase(Integer numCoins, int player)
    {
        //System.out.println("baseCase: " + numCoins + "," + player1 + "," + player2);
        switch(numCoins)
        {
            case 1: return new Result(player, 1);
            case 2: return new Result(player, 1);
            case 3: return new Result((player+1)%2, 2);
            case 4: return new Result(player, 3);
        }
        return null;
    }

    public static Result solve(Integer numCoins, int player)
    {
        //System.out.println("solve: " + numCoins + "," + player1 + "," + player2);
        if (numCoins <= 4) return checkBaseCase(numCoins, player);

        int count1 = 0;
        int count2 = 0;
        Result p1 = solve(numCoins-1, (player+1)%2);
        //System.out.println("solve: " + (numCoins-1) + "," + player2 + "," + player1 + " returned " + p1.getPlayer() + " wins in " + p1.getNumWays() + " ways");
        if (p1.getPlayer() == player) {
            count1 += p1.getNumWays();
        } else {
            count2 += p1.getNumWays();
        }
        p1 = solve(numCoins-2, (player+1)%2);
        //System.out.println("solve: " + (numCoins-2) + "," + player2 + "," + player1 + " returned " + p1.getPlayer() + " wins in " + p1.getNumWays() + " ways");
        if (p1.getPlayer() == player) {
            count1 += p1.getNumWays();
        } else {
            count2 += p1.getNumWays();
        }
        p1 = solve(numCoins-4, (player+1)%2);
        //System.out.println("solve: " + (numCoins-4) + "," + player2 + "," + player1 + " returned " + p1.getPlayer() + " wins in " + p1.getNumWays() + " ways");
        if (p1.getPlayer() == player) {
            count1 += p1.getNumWays();
        } else {
            count2 += p1.getNumWays();
        }

        if (count1 > 0) {
            return new Result(player, count1);
        }
        return new Result((player+1)%2, count2);
    }

    public static void main(String[] args) {

        for (int i = 5; i <= 10; i++)
        {
            System.out.printf("\nnumCoins=%d\n", i);
            Result p = solve(i, 0);
            System.out.println(p.getPlayer() + " wins in " + p.getNumWays() + " ways");

        }
    }
}
