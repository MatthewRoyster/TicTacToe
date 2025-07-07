
package tictactoe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TicTacComp{

    private String move;

    private ArrayList<JButton> boardButtons;

    private Random rand = new Random();

    private AtomicInteger myMove;

    public TicTacComp(String playerMove, ArrayList<JButton> board, AtomicInteger AIMove){
        this.move = playerMove;
        this.boardButtons = board;
        this.myMove = AIMove;
    }

    public void makeNextMove(){

        boolean freeMove = checkFreeMoves();
        int nextMove = rand.nextInt(9);
        while(!boardButtons.get(nextMove).getText().equals(" ") && freeMove){
            //System.out.println("Unable to make a move in " + nextMove + " contains " + boardButtons.get(nextMove).getText());
            nextMove = rand.nextInt(9);
        }
        //System.out.println("The move to be made is " + nextMove + " it current has " + boardButtons.get(nextMove).getText());
        this.myMove.set(nextMove);
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

    public ArrayList<JButton> getGameBoard() {
        return this.boardButtons;
    }
}
