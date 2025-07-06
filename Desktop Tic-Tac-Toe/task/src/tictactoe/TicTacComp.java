package tictactoe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class TicTacComp {

    private String move;

    private ArrayList<JButton> boardButtons;

    private Random rand = new Random();

    public TicTacComp(String playerMove, ArrayList<JButton> board){
        this.move = playerMove;
        this.boardButtons = board;
    }

    public int makeNextMove(){

        boolean freeMove = checkFreeMoves();
        int nextMove = rand.nextInt(9);
        while(!boardButtons.get(nextMove).getText().equals(" ") && freeMove){
            //System.out.println("Unable to make a move in " + nextMove + " contains " + boardButtons.get(nextMove).getText());
            nextMove = rand.nextInt(9);
        }
        //System.out.println("The move to be made is " + nextMove + " it current has " + boardButtons.get(nextMove).getText());
        return nextMove;
    }

    private boolean checkFreeMoves(){
        for(int i = 0; i < boardButtons.size(); i++){
            if(boardButtons.get(i).getText().equals(" ")){
                return true;
            }
        }
        return false;
    }

    public void setMove(String move){
        this.move = move;
    }

    public String getMove(){
        return this.move;
    }

    public void setGameBoard(ArrayList<JButton> board){
        this.boardButtons = board;
    }

    public ArrayList<JButton> getGameBoard(){
        return this.boardButtons;
    }
}
