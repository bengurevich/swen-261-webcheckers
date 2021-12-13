package com.webcheckers.model;

import java.util.*;

/**
 * Board class for rendering checkersBoard
 */
public class Board{
    private final int redLimit = 7;
    private final int whiteLimit = 0;

    public ArrayList<Row> RowList;
    private List<Move> moves = new ArrayList<>();

    /**
     * Creates string rep. of board rows.
     * Primarily used for debugging.
     * @return String rows of board
     */
    @Override
    public String toString() {
        return "Board{" +
                "RowList=" + RowList +
                '}';
    }

    /**
     * Personal iterator method which shows board contents in different orientation depending
     * on player color.
     * @param color the color of the player. (true for red, false for white)
     * @return new Iterator<Row></Row>
     */
    public Iterator<Row> iterate_by_color(boolean color) {
        if(color) {
            return new Iterator<Row>() {
                int i = -1;
                @Override
                public boolean hasNext() {
                    return i+1 <= redLimit;
                }

                @Override
                public Row next() {
                    i= i+1;
                    return RowList.get(i);
                }
            };
        }
        else{
            return new Iterator<Row>() {
                int a = 8;
                @Override
                public boolean hasNext() {
                    return a-1 >= whiteLimit;
                }

                @Override
                public Row next() {
                    a = a-1;
                    return RowList.get(a);
                }
            };
        }
    }
//    public Iterator<Row> iterate_by_color(Color color) {
//        if(color == Color.RED ) {
//            return new Iterator<Row>() {
//                int i = -1;
//                @Override
//                public boolean hasNext() {
//                    return i+1 <= redlimit;
//                }
//
//                @Override
//                public Row next() {
//                    i= i+1;
//                    return RowList.get(i);
//                }
//            };
//        }
//        else{
//            return new Iterator<Row>() {
//                int a = 8;
//                @Override
//                public boolean hasNext() {
//                    return a-1 >= whitelimit;
//                }
//
//                @Override
//                public Row next() {
//                    a = a-1;
//                    return RowList.get(a);
//                }
//            };
//        }
//    }

    /**
     * Retrieves the piece at a position on the board
     * @param position the position of the space being accessed on the board
     * @param perspective the color of the player
     * @return Piece the piece (if any) at the space
     */
    public Piece getPiece(Position position, Color perspective) {
        if(position == null || perspective  == null) {
            return null;
        }
 //       if(perspective.equals(Color.RED)) {
            return RowList.get(position.getRow()).getPieceAtIndex(position.getCell());
//        }
//        else {
//            return RowList.get(7 - position.getRow()).getPieceAtIndex(7 - position.getCell());
//        }
    }



    /**
     * Makes a move on the board.
     *
     * @param move the move that is being made.
     */
    public void makeMove(Move move) {
        int startRow = move.getStartPos().getRow();
        int startCol = move.getStartPos().getCell();
        int endRow = move.getEndPos().getRow();
        int endCol = move.getEndPos().getCell();
        // if the move is a jump, delete the Piece that is jumped over
        if(move.isJumpMove()){
//            System.out.println(" Jump Move detected, removing this piece: (" +
//                    "Row: "+ ((endRow + startRow) / 2) + ", Col " + (endCol + startCol / 2) + ")");
            RowList.get((endRow + startRow) / 2).getSpaces().get( (endCol + startCol) / 2).removeCurrentPiece();

        }

        Piece movingPiece = RowList.get(startRow).getPieceAtIndex(startCol);
        //remove original piece from the board
        RowList.get(startRow).getSpaces().get(startCol).removeCurrentPiece();
        //Add a piece to the new vacant space. Either a king or a single depending on which row it ends on
        if (endRow == 0 || endRow == 7) {
                RowList.get(endRow).getSpaces().get(endCol).addCurrentPiece(new Piece(Piece.type.KING, movingPiece.getColor()));
        }
        else {
            RowList.get(endRow).getSpaces().get(endCol).addCurrentPiece(new Piece(movingPiece.getType(), movingPiece.getColor()));
        }
    }

    /**
     * Gets the piece on a space from the perspective of the player.
     *
     * @param position the position that is being checked.
     * @return the piece at the position.
     */
    public Piece getPieceAtPosition(Position position) {
        if(position == null) {
            return null;
        }
            return RowList.get(position.getRow()).getPieceAtIndex(position.getCell());
    }

    /**
     * Board constructor.
     * Creates 8 rows and puts them in a list.
     */
    public Board(){
        RowList = new ArrayList<>();
        for(int a = 0; a < 8; a++)
        {
            RowList.add(new Row(a));
        }

    }

    public Board(ArrayList<Row> rowList){
        this.RowList = rowList;
    }

    /**
     * Copy function for creating copy of board.
     * @return Board the copyBoard
     */
    public Board copyBoard()
    {
        ArrayList<Row> copyBoard = new ArrayList<>();
        for (Row r : RowList){
            copyBoard.add(r.copyRow());
        }
        return new Board(copyBoard);

    }

    public void addMove(Move m){
        moves.add(m);
    }

    /**
     * Gets moves made
     * @return List<Move> moves list
     */
    public List<Move> getMoves(){
        return moves;
    }

}
