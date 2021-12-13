package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.MoveValidator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * instance of game happening contains board
 */
public class Game {

    private Player redPlayer; // red player necessary for game

    private LinkedList<Move> currentMove; // current move of the board at that specific state

    private Player whitePlayer; // white player necessary for game

    private Color winner = null; // winner is inherently null since upon start no winner has been called

    private com.webcheckers.model.Color Color; // unused

    private Color activeColor; // active color is used to see which player is up

    private int gameId; // TODO: 10/6/21  implement number generator for multiple games

    // initilized to red as red goes first

    private int movesSinceCapture = 0; // integer of moves so ties can be determined

    private final MoveValidator validator; // checks for all moves

    private Board gameBoard; // creates the initial game board for these players

    private Board futureBoard; // used for replay mode

    private LinkedList<Board> boardList; // list of checkerboards

    private boolean isTied = false; // isTied will be false initially since a tie can't happen upon initialization

    private String gameOverMessage = ""; // message to show game is over.

    /**
     * Game constructor
     * @param redPlayer the player to be assigned red
     * @param whitePlayer the playert to be assigned white
     * @param gameId the game ID
     */
    public Game(Player redPlayer, Player whitePlayer, int gameId) {
        this.gameBoard = new Board();
        this.futureBoard = this.gameBoard;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentMove = new LinkedList<>();
        this.boardList = new LinkedList<Board>();
        boardList.add(gameBoard.copyBoard());
        validator = new MoveValidator(this);
        this.gameId = gameId;
    }

    /**
     * Retrieves gameBoard
     * @return Board
     */
    public Board getGameBoard(){return this.gameBoard;}

    /**
     * Retrieves red player of game
     * @return Player
     */
    public Player getRedPlayer(){return this.redPlayer;}

    /**
     * Retrieves white player of game
     * @return Player
     */
    public Player getWhitePlayer(){return this.whitePlayer;}

    /**
     * Retrieves other player of game
     * @param you player calling method
     * @return Player
     */
    public Player getOtherPlayer(Player you){
        if (you.getColor().equals(Color.RED)) { return getWhitePlayer();}
        return getRedPlayer();
    }


    public static final String VALID_MOVE = "You have made your move";
    public static final String JUMP_AGAIN = "You must jump again";
    public static final String ERROR_INVALID_MOVE = "ERROR: invalid move.";
    public static final String ERROR_NO_MOVE = "ERROR: You have not made a move to submit";
    public static final String ERROR_MORE_JUMPS = "ERROR: You can chain more jumps";
    public static final String SUBMITTED = "Move submitted!";
    public static final String ERROR_NO_MOVE_BACKUP = "ERROR: You have not made a move to backup from.";
    public static final String BACKUP_SUCCESS = "Move backed up.";
    public static final String RESIGN_ERROR = "ERROR: Cannot resign when it is not your turn.";

    /**
     * checks if the game is over. This check should occur before each move.
     *
     * if a game is over, the winner object is set.
     *
     * @return boolean
     */
    public boolean isOver(){
        // check if all pieces are dead
        if (winner != null){ // already a winner
            return true;
        }

        int redPieces = 0;
        int whitePieces = 0;

        // TODO: 11/14/21 add return true for resign.

        Iterator<Row> gameOverRowIterator = gameBoard.iterate_by_color(true);
        while (gameOverRowIterator.hasNext()) {
            Row tempRow = gameOverRowIterator.next();
            Iterator<Space> gameOverSpaceIterator = tempRow.iterate_by_color(true);
            while (gameOverSpaceIterator.hasNext()) {
                Space tempSpace = gameOverSpaceIterator.next();
                if (tempSpace.hasPiece()){
                    if(tempSpace.getPiece().getColor() == (com.webcheckers.model.Color.RED))
                        redPieces++;
                    if(tempSpace.getPiece().getColor() == (com.webcheckers.model.Color.WHITE))
                        whitePieces++;
                }
            }
        }

        if (redPieces == 0 ){ //white won
            //System.out.println("zero red Pieces");
            winner = com.webcheckers.model.Color.WHITE;
            gameOverMessage = String.format("Congratulation, %s won!", getWinPlayer().getName());
            return true;
        }

        if (whitePieces==0){ // red won
            //System.out.println("zero white Pieces");
            winner = com.webcheckers.model.Color.RED;
            gameOverMessage = String.format("Congratulation, %s won!", getWinPlayer().getName());
            return true;
        }

        /** TIE STUFF*/
        // check if no possible moves exist
         if (validator.possibleMoves().isEmpty()){ // no possible moves need to ask ryan how to do this
             //System.out.println("TIE due to no possible moves!!");
             this.gameOverMessage = "TIE due to no possible moves!!";
             this.isTied= true;
        }
        // FORTY MOVES WITHOUT CAPTURE, = tie
        for (Move m:this.currentMove) {
            if (m.isJumpMove()){
                movesSinceCapture=0;
                System.out.println("jump move, movesSinceCapture reset");
            }
        }

        if (movesSinceCapture >= 3){
            this.isTied=true;
            //System.out.println("TIE due to 40 moves without capture!!");
            this.gameOverMessage = "TIE due to 40 moves without capture!!";
            System.out.println("TIEEEE");
            return true;

        }

        System.out.println("movesSinceCapture: "+movesSinceCapture);

        return false; // game is not over
    }

    /**
     * Checks if game is tied
     * @return boolean
     */
    public boolean isTied(){
        return isTied;
    }

    /**
     * Declares winner of game
     * @param winnerColor player color who won
     */
    public void declareWinner(Color winnerColor){
        this.winner= winnerColor;
    }

        public String submitMove() {
            movesSinceCapture++;
            if(this.isOver()){
                String s = " WINNER WINNER CHICKEN DINNER!! Congratulations ";
                if (this.winner == com.webcheckers.model.Color.RED)
                    s += "Red player";
                if (this.winner == com.webcheckers.model.Color.WHITE)
                    s += "WHITE player";
                // TODO: 11/15/21    implement game over properly
            }




            if (currentMove.isEmpty()) {
                return ERROR_NO_MOVE;
            } else {
                if (currentMove.getLast().isJumpMove()) {
                    if (validator.canJumpContinue(currentMove.getLast())) {
                        return ERROR_MORE_JUMPS;
                    }
                }
                if (activeColor.equals(com.webcheckers.model.Color.RED)) {setActiveColor(com.webcheckers.model.Color.WHITE);}
                else {setActiveColor(com.webcheckers.model.Color.RED);}
                currentMove.clear();
                this.boardList.add(gameBoard.copyBoard());
                return SUBMITTED;
            }
        }

    /**
     * Lets the player take his or her turn.
     *
     * @param move a move that the player is making.
     * @return a string with the results of the attempt to make the move.
     */
    public String takeTurn(Move move) {


        if(currentMove.isEmpty()) { // if there is no move
            boolean isValid = validator.isMoveValid(move);
            if(isValid) {
                currentMove.add(move);
                gameBoard.makeMove(move);
                if(move.isJumpMove() && validator.canJumpContinue(move)) {
                    return JUMP_AGAIN;
                }
                else {
                    return VALID_MOVE;
                }
            }
            else {
                return ERROR_INVALID_MOVE;
            }
        }
        else {
            boolean partOfJump = false;
            if(currentMove.getLast().isJumpMove()) {
                //System.out.println("ENTERING CHAINING");
                partOfJump = validator.isJumpContinued(move, currentMove.getLast());
            }
            if(partOfJump) {
                boolean isValid = validator.canJumpContinue(currentMove.getLast());
                if (isValid) {
                    currentMove.add(move);
                    gameBoard.makeMove(move);
                    if (validator.canJumpContinue(move)) {
                        return JUMP_AGAIN;
                    }
                    return VALID_MOVE;
                }
                else {
                    return ERROR_INVALID_MOVE;
                }
            }
            else {
                return ERROR_INVALID_MOVE;
            }
        }
    }

    /**
     * Sets active color of game
     * @param activeColor the Color to set to
     */
    public void setActiveColor(Color activeColor){this.activeColor = activeColor;}

    /**
     * Retrieves active color of game
     * @return Color
     */
    public Color getActiveColor(){return this.activeColor;}

    public void setGameBoard(Board gameBoard){this.gameBoard = gameBoard;}

    /**
     * Retrieves winner color of game
     * @return Color
     */
    public Color getWinner(){return this.winner;}

    /**
     * Retrieves winning player
     * @return Player
     */
    public Player getWinPlayer(){
        if (winner.equals(Color.RED)) return getRedPlayer();
        else return getWhitePlayer();
    }

    /**
     * Retrieves ID of game
     * @return int
     */
    public int getGameId(){return this.gameId;}

    /**
     * Declares winner, returns false if issue
     * @param winner Player who won
     * @return boolean
     */
    public boolean declareWinner(Player winner) {
        if((winner.equals(redPlayer) || winner.equals(whitePlayer)) && this.winner == null) {
            this.winner = winner.getColor();
            gameOverMessage = "You won by resignation";
            return true;
        }
        return false;
    }

    /**
     * Backs up moves in current turn
     * @return String for success or ERROR
     */
    public String backup()
    {
        System.out.println("Size of boardList" + boardList.size());
        if(this.currentMove.isEmpty())
        {
            return ERROR_NO_MOVE_BACKUP;
        }
        else
        {
            this.gameBoard = boardList.getLast().copyBoard();
            currentMove.clear();
            return BACKUP_SUCCESS;
        }
    }

    public String resignGame(Color color){
            if(color.equals(Color.RED))
            {
                this.declareWinner(Color.WHITE);
                return this.getWhitePlayer().getName() + " wins due to opponent resignation.";
            }
            else if(color.equals(com.webcheckers.model.Color.WHITE))
            {
                this.declareWinner(com.webcheckers.model.Color.RED);
                return this.getRedPlayer().getName() + " wins due to opponent resignation.";
            }

        return RESIGN_ERROR;
    }

    /**
     * Retrieves game over message
     * @return String
     */
    public String getGameOverMessage(){return this.gameOverMessage;}
}
