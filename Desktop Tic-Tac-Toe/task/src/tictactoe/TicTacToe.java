package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TicTacToe extends JFrame {
    private static String currentMove = "X";

    private static final String[] boardMarks = new String[9];

    private static JLabel status;

    private static ArrayList<JButton> buttons;

    private static int counter = 0;

    private static boolean gameOver = false;

    private static TicTacComp p1;

    private static TicTacComp p2;

    private static boolean p1Comp = false;

    private static boolean p2Comp = false;

    private static JButton reset;

    private static JButton buttonPlayer1;

    private static JButton buttonPlayer2;

    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setSize(550, 550);
        setVisible(true);

        buttonPlayer1 = new JButton("Human");
        buttonPlayer1.setName("ButtonPlayer1");
        buttonPlayer1.addActionListener(e -> {
            if(p1Comp){
                buttonPlayer1.setText("Human");
                p1Comp = false;
            }else{
                buttonPlayer1.setText("Robot");
                p1Comp = true;
            }
        });

        buttonPlayer2 = new JButton("Human");
        buttonPlayer2.setName("ButtonPlayer2");
        buttonPlayer2.addActionListener(e -> {
            if(p2Comp){
                buttonPlayer2.setText("Human");
                p2Comp = false;
            }else{
                buttonPlayer2.setText("Robot");
                p2Comp = true;
            }
        });

        reset = new JButton("Start");
        reset.setName("ButtonStartReset");
        reset.addActionListener(e -> {
            if(reset.getText().equals("Start")){
                setFieldState(true);
                reset.setText("Reset");
                status.setText("Game in progress");
                buttonPlayer1.setEnabled(false);
                buttonPlayer2.setEnabled(false);
                if(p1Comp){
                    reset.setText("Reset");
                    buttons.get(new Random().nextInt(9)).doClick();
                }
            }else {
                clearField(buttons);
                reset.setText("Start");
                buttonPlayer1.setEnabled(true);
                buttonPlayer2.setEnabled(true);
                setFieldState(false);
            }
        });



        /*
        Generate all the buttons for playing the game

        Add them to the list and pass to a new method to
        generate appearances and add action listener events
         */
        JButton a1 = new JButton();
        JButton a2 = new JButton();
        JButton a3 = new JButton();
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton c1 = new JButton();
        JButton c2 = new JButton();
        JButton c3 = new JButton();

        buttons = new ArrayList<>();

        buttons.add(a1);
        buttons.add(a2);
        buttons.add(a3);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(c1);
        buttons.add(c2);
        buttons.add(c3);

        generateButtons();

        for(JButton b : buttons){
            b.setEnabled(false);
        }

        p1 = new TicTacComp("X", buttons);
        p2 = new TicTacComp("O", buttons);

        /*
        Create the status label and reset button, these get added to the
        game board JPanel with a spacer in between to properly fill columns
         */

        status = new JLabel("Game is not started");
        status.setName("LabelStatus");

        JMenuBar menuBar = getBar();

        setJMenuBar(menuBar);

        JPanel board = new JPanel();
        GridLayout gl = new GridLayout(5, 3);
        gl.setVgap(10);
        gl.setHgap(10);
        board.setLayout(gl);

        JLabel space = new JLabel("  ");

        board.add(buttonPlayer1);
        board.add(reset);
        board.add(buttonPlayer2);

        board.add(a3);
        board.add(b3);
        board.add(c3);

        board.add(a2);
        board.add(b2);
        board.add(c2);

        board.add(a1);
        board.add(b1);
        board.add(c1);

        board.add(status);
        board.add(space);
        board.add(space);

        add(board);

        Arrays.fill(boardMarks, " ");

    }

    private static JMenuBar getBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setName("MenuGame");

        JMenuItem HH = new JMenuItem("Human vs Human");
        HH.setName("MenuHumanHuman");
        HH.addActionListener(e -> setGameState(false, false));
        gameMenu.add(HH);
        JMenuItem HR = new JMenuItem("Human vs Robot");
        HR.setName("MenuHumanRobot");
        HR.addActionListener(e -> setGameState(false, true));
        gameMenu.add(HR);
        JMenuItem RH = new JMenuItem("Robot vs Human");
        RH.setName("MenuRobotHuman");
        RH.addActionListener(e -> setGameState(true, false));
        gameMenu.add(RH);
        JMenuItem RR = new JMenuItem("Robot vs Robot");
        RR.setName("MenuRobotRobot");
        RR.addActionListener(e -> setGameState(true, true));
        gameMenu.add(RR);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("MenuExit");
        exit.addActionListener(e -> System.exit(0));
        gameMenu.addSeparator();
        gameMenu.add(exit);

        menuBar.add(gameMenu);
        return menuBar;
    }

    private static void setGameState(boolean p1Robot, boolean p2Robot){
        clearField(buttons);
        if(p1Robot){
            buttonPlayer1.setText("Robot");
            p1Comp = true;
        }else{
            buttonPlayer1.setText("Human");
            p1Comp = false;
        }
        if(p2Robot){
            buttonPlayer2.setText("Robot");
            p2Comp = true;
        }else{
            buttonPlayer2.setText("Human");
            p2Comp = false;
        }
        reset.setText("Start");
        reset.doClick();
    }

    private static void setFieldState(boolean b){
        for(JButton j : buttons){
            j.setEnabled(b);
        }
    }

    private static void generateButtons(){
        char letter = 'A';
        int number = 1;
        int columns = 3;
        for (JButton temp : buttons) {
            temp.setName("Button" + letter + number);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            temp.setBackground(Color.YELLOW);
            temp.setText(" ");
            temp.addActionListener(e -> {
                //A is equal to 10, to calculate index subtract 10;
                int locationIndex = (Character.getNumericValue(temp.getName().charAt(6)) - 10);
                locationIndex += (Integer.parseInt(temp.getName().charAt(7) + "") - 1) * 3;
                if (boardMarks[locationIndex].equals(" ") && !gameOver) {
                    status.setText("Game in progress");
                    temp.setText(currentMove);
                    temp.setEnabled(false);
                    boardMarks[locationIndex] = currentMove;
                    checkWinner();
                    //System.out.println(currentMove + " just played, placed at " + locationIndex);

                    //Change the move to the opponent
                    if (currentMove.equals("X")) {
                        currentMove = "O";
                    } else {
                        currentMove = "X";
                    }
                    if (currentMove.equals("O") && p2Comp) {
                        int compMove = p2.makeNextMove();
                        buttons.get(compMove).doClick();
                    } else if (currentMove.equals("X") && p1Comp) {
                        int compMove = p1.makeNextMove();
                        buttons.get(compMove).doClick();
                    }
                }
            });
            number++;
            if (number > columns) {
                number = 1;
                letter++;
            }
        }
    }

    private static void clearField(ArrayList<JButton> buttons){
        for(int i = 0; i < 9; i++){
            boardMarks[i] = " ";
        }
        for (JButton button : buttons) {
            button.setText(" ");
        }
        status.setText("Game is not started");
        currentMove = "X";
        counter = 0;
        gameOver = false;
    }

    private static void checkWinner(){
        boolean isWinner = false;
        counter++;

        if(buttons.get(0).getText().equals(buttons.get(1).getText()) && !buttons.get(0).getText().equals(" ")){
            if(buttons.get(0).getText().equals(buttons.get(2).getText())){
                isWinner = true;
            }
        }
        if(buttons.get(3).getText().equals(buttons.get(4).getText()) && !buttons.get(3).getText().equals(" ")){
            if(buttons.get(3).getText().equals(buttons.get(5).getText())){isWinner = true;}
        }
        if(buttons.get(6).getText().equals(buttons.get(7).getText()) && !buttons.get(6).getText().equals(" ")){
            if(buttons.get(6).getText().equals(buttons.get(8).getText())){isWinner = true;}
        }
        if(buttons.get(6).getText().equals(buttons.get(3).getText()) && !buttons.get(6).getText().equals(" ")){
            if(buttons.get(6).getText().equals(buttons.get(0).getText())){isWinner = true;}
        }
        if(buttons.get(7).getText().equals(buttons.get(4).getText()) && !buttons.get(7).getText().equals(" ")){
            if(buttons.get(7).getText().equals(buttons.get(1).getText())){isWinner = true;}
        }
        if(buttons.get(8).getText().equals(buttons.get(5).getText()) && !buttons.get(8).getText().equals(" ")){
            if(buttons.get(8).getText().equals(buttons.get(2).getText())){isWinner = true;}
        }
        if(buttons.get(6).getText().equals(buttons.get(4).getText()) && !buttons.get(6).getText().equals(" ")){
            if(buttons.get(6).getText().equals(buttons.get(2).getText())){isWinner = true;}
        }
        if(buttons.get(8).getText().equals(buttons.get(4).getText()) && !buttons.get(8).getText().equals(" ")){
            if(buttons.get(8).getText().equals(buttons.get(0).getText())){isWinner = true;}
        }
        printGameBoard();

        if(isWinner){
            status.setText(currentMove + " wins");
            System.out.println(currentMove + " wins");
            gameOver = true;
            setFieldState(false);
        }else if(counter == 9){
            status.setText("Draw");
        }
    }

    private static void printGameBoard(){
        StringBuilder labels = new StringBuilder(" ");
        for (JButton button : buttons) {
            labels.append(button.getText()).append(" ");
        }

        System.out.println(labels);
    }


}