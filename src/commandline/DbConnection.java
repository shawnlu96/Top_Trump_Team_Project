/**
 * Connects to database "public" and runs a query to retrieve a result in stdout. Remember to import postgresql-42.2.5.jar before running this. 
 */
package commandline;
import java.sql.*;

import org.postgresql.util.PSQLException;

public class DbConnection 
{
	private Connection connection = null; //connection object 
	private final String db = "jdbc:postgresql://localhost:5432/postgres?currentSchema=public"; //yacata server and db shall be used, only need one db 
	private final String username = "postgres";
	private final String password = "postgres";
	private int game_id = 0; //last game_id in the database
	private int winner_player = 0;
	private int draws = 0;
	private int rounds = 0;
	private int[] player_rounds_won;
	
	public DbConnection()
	{
		try //First we ensure that the postgresql driver is found. 
		{
			Class.forName("org.postgresql.Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("PostgresSQL driver not found."); 
			return;
		}
		System.out.println("Jar found.");
		try { //Second we ensure that we can connect to the database via connection object.
			connection = DriverManager.getConnection(db, username, password); //username and password of GU student is used
		}
		catch (SQLException e)
		{
			System.out.println("Database connection not established.");
			e.printStackTrace();
			return;
		}
		System.out.println("Controlling your database."); //This should be prrinted if it works
		setFinalGame();
	}
	
	private int sqlCreate(String input, String sql)
	{
		int i = 0;
		if(connection != null) //queries run once connection is established
		{
			try {
				Statement statement = connection.createStatement();
				String query = input;
				ResultSet result = null;
				try 
				{
					result = statement.executeQuery(query);
				}
				catch(PSQLException e) //this ignores any errors not related to select statements 
				{
				}
				if(sql.equals("select"))
				{
					while(result.next()) //Result will have only one cell.
					{
						i = result.getInt(1);
					}
				}
				statement.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Failed to establish connection.");
		}
		return i;
	}
	
	private void setFinalGame()
	{
		int i = sqlCreate("select max(game_id) as max_id from games;", "select");
		game_id = i + 1;
		System.out.println("Game_id is: " + game_id); //Stdout print. 
	}
	
	public void insertGameValues(DbConnection d)
	{
		int i;
		int[] ia = {d.game_id, d.winner_player,d.draws,d.rounds}; 
		String sql_games = "insert into games values (" + ia[0] + "," + ia[1] + "," + ia[2] + "," + ia[3] + ");";
		i = sqlCreate(sql_games,"insert");
		i = sqlCreate("commit","commit");
		for(int j = 0; j<d.player_rounds_won.length; j++)
		{
			String sql_players = "insert into players values (" + (j+1) + "," + d.game_id + "," + player_rounds_won[j] + ");";
			i = sqlCreate(sql_players,"insert");
			i = sqlCreate("commit","commit");
		}
	}
	
	public String statisticsToString()
	{
		String s = 	"\nTotal games:   " + sqlCreate("select count(game_id) as games_played from games;", "select") +
					"\nComputer wins: " + sqlCreate("select count(winner_player) as ai_wins from games where winner_player <> 1;", "select") +
					"\nHuman wins:    " + sqlCreate("select count(winner_player) as human_wins from games where winner_player = 1;", "select") +
					"\nAverage draws: " + sqlCreate("select floor(avg(draws)) as avg_draws from games;", "select") +
					"\nMax rounds:    " + sqlCreate("select max(rounds) as max_rounds from games;", "select");
		return s;
	}
	
	public Connection getConnection()
	{ //get connection to see if it is null 
		return connection; //other classes may get connection
	}
	
	public void closeConnection(DbConnection d) 
	{
		try 
		{
			d.getConnection().close(); //other classes may close connection 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setWinnerPlayer(DbConnection d, int wp)
	{
		d.winner_player= wp;
	}
	
	public void setDraws(DbConnection d, int dw)
	{
		d.draws = dw;
	}
	
	public void setRounds(DbConnection d, int r)
	{
		d.rounds = r;
	}
	
	public void setPlayers(DbConnection d, int r)
	{
		d.player_rounds_won = new int[r];
	}
	
	public void setPlayerRoundsWon(DbConnection d, int[] r) 
	{
		for(int i=0; i<d.player_rounds_won.length; i++)
		{
			d.player_rounds_won[i] = r[i];
		}
	}

	public int getgameNumber(){
		return sqlCreate("select count(game_id) as games_played from games;", "select");
	}

	public int getAIwinningNumber(){
		return sqlCreate("select count(winner_player) as ai_wins from games where winner_player <> 1;", "select");
	}

	public int getHumanwinningNumber(){
		return sqlCreate("select count(winner_player) as human_wins from games where winner_player = 1;", "select");
	}

	public int getAverageDrawNumber(){
		return sqlCreate("select floor(avg(draws)) as avg_draws from games;", "select");
	}

	public int getMaxRounds(){
		return sqlCreate("select max(rounds) as max_rounds from games;", "select");
	}
}
