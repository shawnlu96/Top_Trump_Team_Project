/**
 * DB section main method. Remember to import postgresql-42.2.5.jar before running this. 
 */


public class Main 
{
	public static void main(String[] args)
	{
		DbConnection d = new DbConnection();
		int[] r = {3,5,7};
		System.out.println(d.statisticsToString());
		
		d.setWinnerPlayer(d,1);
		d.setDraws(d, 11);
		d.setRounds(d,11);
		d.setPlayers(d,3);
		d.setPlayerRoundsWon(d,r);
		
		d.insertGameValues(d);
		
		d.closeConnection(d);
	}
}
