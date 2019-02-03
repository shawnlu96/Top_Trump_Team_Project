package commandline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestLog {
    private String logStr;
    private TTModel model;

    public TestLog(TTModel model){
        logStr = "";
        this.model = model;
    }

    public void addLog(String s){
        logStr += s + "\r\n";
    }

    public void addLine(String label){
        addLog("-----------------" + label + "---------------------");
    }

    public void addPlayerCardsInfo(){
        addLine("Player decks");
        for(Player p:model.getPlayers()){
            addLog(p.getPlayerName() + ": ");
            for(Card c:p.getPlayerCards()){
                addLog(c.toString());
            }
        }
    }

    public void addCommunalPile(){
        addLine("Communal Pile");
        for(Card c:model.getCommunalPile()){
            addLog(c.toString());
        }
    }

    public void addCardsThisRound(){
        addLine("Cards in play");
        for(Player p: model.getPlayers()){
            if(p.getPlayerCards().size()>0){        //if the current player has any cards
                addLog(String.format("%-9s",p.getPlayerName()) + ": " + p.getPlayerCards().get(0).toString());
            }
        }
    }

    public void addAttributeSelected(){
        addLine("Attribute selected");
        addLog("Attribute " + (model.getIndexOfCurrentAttribute()+1) + ": " + model.getAttributeNames()[model.getIndexOfCurrentAttribute()+1]);
    }

    public void addFinalWinner(){
        addLine("Final Winner");
        addLog(model.getPlayers().get(model.getIndexOfFinalWinner()).getPlayerName());
    }

    public void writeLog(){
        String path = "GameLog.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(logStr);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
