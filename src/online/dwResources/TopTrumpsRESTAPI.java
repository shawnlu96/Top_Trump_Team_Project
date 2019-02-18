package online.dwResources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import commandline.*;
import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controlled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();


	TTModel model;
	TTView view;
	TTController controller;
	DbConnection d;
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
        d = new DbConnection();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

    @GET
    @Path("/playARound")
    public void playARound(@QueryParam("index") int index){
		model.setRoundNumber(model.getRoundNumber()+1);
		model.setIndexOfCurrentAttribute(index);
		view.showCardsThisRound();
		controller.playRound(index);
    }

	@GET
	@Path("/autoPlay")
	public void autoPlay(){
		controller.startGame();
	}

	@GET
	@Path("/getPlayerHashcode")
	public int getPlayerHashcode(@QueryParam("playerIndex") int playerIndex){
		return model.getPlayers().get(playerIndex).hashCode();
	}

    @GET
	@Path("/getHighestAttributeIndex")
	public int getHighestAttributeIndex(@QueryParam("playerIndex") int playerIndex){
		return controller.getHighestAttributeIndex(playerIndex);
	}

	@GET
	@Path("/getCardByIndex")
	public String getCardByIndex(@QueryParam("index") int index) throws IOException{
		String cardJSON = oWriter.writeValueAsString(model.getCards().get(index));
		return cardJSON;
	}

	@GET
	@Path("/getPlayerByIndex")
	public String getPlayerByIndex(@QueryParam("index") int index) throws IOException{
		String playerJSON = oWriter.writeValueAsString(model.getPlayers().get(index));
		return playerJSON;
	}

    @GET
    @Path("/restoreInputStream")
    public void restoreInputStream(){
	    System.setIn(System.in);
    }

    @GET
    @Path("/startGame")
    public void startGame(@QueryParam("numberOfPlayers") String numberOfPlayers) {
        model = new TTModel(Integer.parseInt(numberOfPlayers));
        view = new TTView(model);
        controller = new TTController(model,view);
        controller.setOnline(true);
        model.setRoundNumber(0);
        //set numberOfPlayers
        System.out.println("game start");
    }

    @GET
    @Path("/getModel")
    public String getModel() throws IOException{
	    String JSONModelString = oWriter.writeValueAsString(model);
	    return  JSONModelString;
    }

    @GET
    @Path("/getStatistics")
    public String getStatistics() {

        String s = "Total games:   " + d.getgameNumber() + "</br>"
                + "Computer wins: " + d.getAIwinningNumber() + "</br>"
                + "Human wins:    " + d.getHumanwinningNumber() + "</br>"
                + "Average draws: " + d.getAverageDrawNumber() + "</br>"
                + "Max rounds:    " + d.getMaxRounds();
        return s;
    }


	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
//		List<String> listOfWords = new ArrayList<String>();
////		listOfWords.add("World!");
//		String data = "4";
//
//		System.setIn(new ByteArrayInputStream(data.getBytes()));
		model = new TTModel(4);

//		controller = new TTController(model,view);

		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(model);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}



	
}
