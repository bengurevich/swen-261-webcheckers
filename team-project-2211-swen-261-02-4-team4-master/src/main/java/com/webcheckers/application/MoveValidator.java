package com.webcheckers.application;
import com.webcheckers.model.*;

import java.util.HashSet;
import java.util.Iterator;

/**
 * called by board. The reason for this class is to separate the nitty-gritty logic from the board class.
 * the board is the information expert and knows what data is required to validate the move, so it calls the
 * right method in this class. this class takes the information and applies logic to it.
 *
 * hierarchy of move check
 *
 * turn checks to see if its the right time to mvoe
 * board sends info to moveValidationLogic to see if a move is valid
 *
 * this is how it should be used
 *
 * MoveValidator.isDiagonal(Move move)
 * MoveValidator.canDoublejump(info needed)
 * validate.<specific rule to check>(<info needed to check that>) ;
 */
public class MoveValidator {
    private final Game game;
    private final Board board;
    private Color activeColor;


    public MoveValidator(Game game) {
        this.game = game;
        this.board = game.getGameBoard();
        this.activeColor = game.getActiveColor();
    }


    /**
     * Checks the moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all simple moves that can be made.
     */
    private HashSet<Move> simpleDiagonalMoves(Position start) {
        HashSet<Move> singleMoves = new HashSet<>();
        Move newMove;

        Position validMove1;
        Position validMove2;


        if(activeColor.equals(Color.RED)) {
            //System.out.println("Red");
            validMove1 = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1); //moveNegPos
            validMove2 = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1); //moveNegNeg
        }
        else { //Runs if active color is white. This accounts for inverted rows
            //System.out.println("White");
            validMove1 = Position.createTestPosition(start.getRow() + 1, start.getCell() + 1); //movePosPos
            validMove2 = Position.createTestPosition(start.getRow() + 1, start.getCell() - 1); //movePosNeg
        }
        if ((validMove1 != null) && board.getPiece(validMove1, activeColor) == null) {
            newMove = new Move(start, validMove1);
            singleMoves.add(newMove);
        }
        if (validMove2 != null && board.getPiece(validMove2, activeColor) == null) {
            newMove = new Move(start, validMove2);
            singleMoves.add(newMove);
        }
        return singleMoves;
    }

    /**
     * Checks the moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all simple moves that can be made.
     */
    private HashSet<Move> kingDiagonalMoves(Position start) {
        HashSet<Move> kingMoves = new HashSet<>();
        Position movePosPos = Position.createTestPosition(start.getRow() + 1, start.getCell() + 1);
        Position movePosNeg = Position.createTestPosition(start.getRow() + 1, start.getCell() - 1);
        Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
        Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
        if(movePosPos != null && board.getPieceAtPosition(movePosPos) == null) {
            kingMoves.add(new Move(start, movePosPos));
        }
        if(movePosNeg != null && board.getPieceAtPosition(movePosNeg) == null) {
            kingMoves.add(new Move(start, movePosNeg));
        }
        if(moveNegPos != null && board.getPieceAtPosition(moveNegPos) == null) {
            kingMoves.add(new Move(start, moveNegPos));
        }
        if(moveNegNeg != null && board.getPieceAtPosition(moveNegNeg) == null) {
            kingMoves.add(new Move(start, moveNegNeg));
        }
        return kingMoves;
    }

    /**
     * Checks the jump moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all jump moves that can be made.
     */
    private HashSet<Move> simpleJumpMoves(Position start) {
        HashSet<Move> singleMoves = new HashSet<>();
        Move newMove;

        if (activeColor.equals(Color.RED)) {
            Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
            Piece pieceNegPos = board.getPiece(moveNegPos, activeColor);
            Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
            Piece pieceNegNeg = board.getPiece(moveNegNeg, activeColor);

            if (moveNegPos != null && pieceNegPos != null && pieceNegPos.getColor() != activeColor) {
                Position jumpNegPos = Position.createTestPosition(start.getRow() - 2, start.getCell() + 2);
                if (jumpNegPos != null && board.getPiece(jumpNegPos, activeColor) == null) {
                    newMove = new Move(start, jumpNegPos);
                    singleMoves.add(newMove);
                }
            }
            if (moveNegNeg != null && pieceNegNeg != null && pieceNegNeg.getColor() != activeColor) {
                Position jumpNegNeg = Position.createTestPosition(start.getRow() - 2, start.getCell() - 2);
                if (jumpNegNeg != null && board.getPiece(jumpNegNeg, activeColor) == null) {
                    newMove = new Move(start, jumpNegNeg);
                    singleMoves.add(newMove);
                }
            }
        }

        else{ //Runs if active color is white. This accounts for inverted rows
            Position moveNegPos = Position.createTestPosition(start.getRow() + 1, start.getCell() + 1);
            Piece pieceNegPos = board.getPiece(moveNegPos, activeColor);
            Position moveNegNeg = Position.createTestPosition(start.getRow() + 1, start.getCell() - 1);
            Piece pieceNegNeg = board.getPiece(moveNegNeg, activeColor);

            if (moveNegPos != null && pieceNegPos != null && pieceNegPos.getColor() != activeColor) {
                Position jumpNegPos = Position.createTestPosition(start.getRow() + 2, start.getCell() + 2);
                if (jumpNegPos != null && board.getPiece(jumpNegPos, activeColor) == null) {
                    newMove = new Move(start, jumpNegPos);
                    singleMoves.add(newMove);
                }
            }
            if (moveNegNeg != null && pieceNegNeg != null && pieceNegNeg.getColor() != activeColor) {
                Position jumpNegNeg = Position.createTestPosition(start.getRow() + 2, start.getCell() - 2);
                if (jumpNegNeg != null && board.getPiece(jumpNegNeg, activeColor) == null) {
                    newMove = new Move(start, jumpNegNeg);
                    singleMoves.add(newMove);
                }
            }
        }
        return singleMoves;
    }

    /**
     * Checks the jump moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all jump moves that can be made.
     */
    private HashSet<Move> kingJumpMoves(Position start) {
        HashSet<Move> kingMoves = new HashSet<>();
        Position movePosPos = Position.createTestPosition(start.getRow() + 1, start.getCell() + 1);
        Piece piecePosPos = board.getPieceAtPosition(movePosPos);
        Position movePosNeg = Position.createTestPosition(start.getRow() + 1, start.getCell() - 1);
        Piece piecePosNeg = board.getPieceAtPosition(movePosNeg);
        Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
        Piece pieceNegPos = board.getPieceAtPosition(moveNegPos);
        Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
        Piece pieceNegNeg = board.getPieceAtPosition(moveNegNeg);

        if(movePosPos != null && piecePosPos != null && piecePosPos.getColor() != activeColor) {
            Position jumpPosPos = Position.createTestPosition(start.getRow() + 2, start.getCell() + 2);
            if(jumpPosPos != null && board.getPieceAtPosition(jumpPosPos) == null) {
                kingMoves.add(new Move(start, jumpPosPos));
            }
        }
        if(movePosNeg != null && piecePosNeg != null && piecePosNeg.getColor() != activeColor) {
            Position jumpPosNeg = Position.createTestPosition(start.getRow() + 2, start.getCell() - 2);
            if(jumpPosNeg != null && board.getPieceAtPosition(jumpPosNeg) == null) {
                kingMoves.add(new Move(start, jumpPosNeg));
            }
        }
        if(moveNegPos != null && pieceNegPos != null && pieceNegPos.getColor() != activeColor) {
            Position jumpNegPos = Position.createTestPosition(start.getRow() - 2, start.getCell() + 2);
            if(jumpNegPos != null && board.getPieceAtPosition(jumpNegPos) == null) {
                kingMoves.add(new Move(start, jumpNegPos));
            }
        }
        if(moveNegNeg != null && pieceNegNeg != null && pieceNegNeg.getColor() != activeColor) {
            Position jumpNegNeg = Position.createTestPosition(start.getRow() - 2, start.getCell() - 2);
            if(jumpNegNeg != null && board.getPieceAtPosition(jumpNegNeg) == null) {
                kingMoves.add(new Move(start, jumpNegNeg));
            }
        }
        return kingMoves;
    }

    /**
     * Checks if you can jump again.
     *
     * @param lastMove a move that is being tested for validation.
     * @return true or false.
     */
    public boolean canJumpContinue(Move lastMove){
        Position endOfLastMove = lastMove.getEndPos();
        if(board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.SINGLE)) {
            HashSet<Move> allJumpMoves = simpleJumpMoves(endOfLastMove);
            return !allJumpMoves.isEmpty();
        }
        if(board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.KING)) {
            HashSet<Move> kingJumpMoves = kingJumpMoves(endOfLastMove);
            return !kingJumpMoves.isEmpty();
        }
        return false;
    }

    /**
     * Tests if the jump can be continued off the first move.
     *
     * @param next the second move that will be made.
     * @param start the first move that was made.
     * @return true or false depending on if the end of the first move matches the
     * start of the second move.
     */
    public boolean isJumpContinued(Move next, Move start) {
        Position endOfLastMove = start.getEndPos();
        if (board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.SINGLE)) { // if single
            HashSet<Move> allJumpMoves = simpleJumpMoves(endOfLastMove);


            boolean flag = false; // this will go true if the input move matches the valid move

            for (Move m : allJumpMoves) {
                if (m.equals(next)) { // next move is valid!!
                    flag = true;
                }
            }
            return flag;

            //return allJumpMoves.contains(next);
        }
        if (board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.KING)) {
            HashSet<Move> allJumpMoves = kingJumpMoves(endOfLastMove);
            boolean flag = false; // this will go true if the input move matches the valid move
            for (Move m : allJumpMoves) {
                if (m.equals(next)) { // next move is valid!!
                    flag = true;
                }
            }


//            // debug stuff
//            int numberOfPossibleJumpMoves = 0;
//
//            for (Move m : allJumpMoves) {
//                numberOfPossibleJumpMoves++;
//            }
//            System.out.println("           There are " + numberOfPossibleJumpMoves + " King Jump Move Possibilities: ");
//
//            for (Move m : allJumpMoves) {
//                m.printMove();
//            }
//            System.out.println("------ end possible jump move ---------");
//            // end debug stuff
            return flag;
        }
        System.out.println("error invalid piece type");
     return false;
    }

    /**
     * Validates a move and returns true or false depending on if it is valid or not.
     *
     * @param move a move that is being tested for validation.
     * @return true or false.
     */

    /**
     *
     *
     *
     */
    public boolean isMoveValid(Move move){
        this.activeColor = game.getActiveColor();
        HashSet<Move> allLegalMoves = possibleMoves();
        //System.out.println("Add Valid Moves:");
        boolean flag = false;
        for(Move m : allLegalMoves){
            //m.printMove();
            if(m.equals(move)){
                //System.out.print( "         ^^^ this is the move input" );
                flag = true;
            }

        }
        //System.out.println("----------------");

        return flag;
    }




    /**
     * Uses all the helper methods to get a list of all possible moves that a player can make.
     *
     * @return a  list of all possible moves that a player can make.
     */


    public HashSet<Move> possibleMoves() {
        HashSet<Move> simpleMoves = new HashSet<>();
        HashSet<Move> jumpMoves = new HashSet<>();
        this.activeColor = game.getActiveColor();
        Iterator<Row> boardItr = board.iterate_by_color(!activeColor.equals(Color.WHITE ));

        while(boardItr.hasNext()) {
            Row row = boardItr.next();
            for(Space space : row.getSpaces()) {
                Piece piece = space.getPiece();
                if(piece != null && piece.getColor().equals(activeColor)) {

                    if(piece.getType() == Piece.type.SINGLE) {
                        simpleMoves.addAll(simpleDiagonalMoves(new Position(row.getIndex(), space.getCellIdx())));
                        jumpMoves.addAll(simpleJumpMoves(new Position(row.getIndex(), space.getCellIdx())));

                    }
                    else if(piece.getType() == Piece.type.KING) {
                    simpleMoves.addAll(kingDiagonalMoves(new Position(row.getIndex(), space.getCellIdx())));
                    jumpMoves.addAll(kingJumpMoves(new Position(row.getIndex(), space.getCellIdx())));

                    }

                }
            }
        }
       // System.out.println("\nMoves \n");
       // for (Move m : simpleMoves) {m.printMove();}


        if(jumpMoves.isEmpty()) {

            return simpleMoves;
        }
        else {

            return jumpMoves;
        }
    }




    /**
     * checks if the move is one square in the diagional direction. If a signgle, just checks to see if move
     * is forward. if king, checks for all 4 diagonal
     * @return
     */

    public static boolean isOneDiagonal(Move move){
        int startRow = move.getStartPos().getRow();
        int startCol = move.getStartPos().getCell();

        int endRow = move.getEndPos().getRow();
        int endCol = move.getEndPos().getCell();
        //check the following valid move positions and return true if the position is in one of these places

        // up left
        if (endCol == startCol-1 && endRow == startRow-1)
            return true;
        // up right
        if (endCol == startCol+1 && endRow == startRow-1)
            return true;
        //down left
        if (endCol == startCol-1 && endRow == startRow+1)
            return true;
        //down right
        if (endCol == startCol+1 && endRow ==+1)
            return true;
        return false;
    }

//      public static boolean isOneDiagonal(Move move){
//      int startRow = move.getStart().getRow();
//      int startCol = move.getStart().getCell();

//      int endRow = move.getEnd().getRow();
//      int endCol = move.getEnd().getCell();
//      //check the following valid move positions and return true if the position is in one of these places

//      // up left
//      if (endCol == startCol-1 && endRow == startRow-1)
//      return true;
//      // up right
//      if (endCol == startCol+1 && endRow == startRow-1)
//      return true;
//      //down left
//      if (endCol == startCol-1 && endRow == startRow+1)
//      return true;
//      //down right
//      if (endCol == startCol+1 && endRow ==+1)
//      return true;

//      return false;
//      }


}
