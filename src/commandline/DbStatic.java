package commandline;
/**
 * DB section main method. Remember to import postgresql-42.2.5.jar before running this. 
 */


public class DbStatic 
{
	public static DbConnection d = new DbConnection();
	
	public static void dbSet()
	{
		int[] r = {3,5,7};
		
		d.setWinnerPlayer(d,1);
		d.setDraws(d, 11);
		d.setRounds(d,11);
		d.setPlayers(d,3);
		d.setPlayerRoundsWon(d,r);
		
		d.insertGameValues(d);
		
		d.closeConnection(d);
	}
	
	public static void showStatistics() {
		System.out.println(d.statisticsToString());
		System.out.println();

	}
}
