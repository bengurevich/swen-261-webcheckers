package com.webcheckers.model;

/**
 * class that represents a move on the checkers board
 */


public class Move {
    private Position start; // starting position
    private Position end; // ending position

    /**
     * Constructor for the Move
     * @param start the starting position for the piece
     * @param end the ending position for the piece
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * getStartPos gets the initial position
     * @return Position
     */

    public Position getStartPos() {
        return start;
    }

    /**
     * getEndPos gets the ending position
     * @return Position
     */
    public Position getEndPos() {
        return end;
    }

    /**
     * Checks if simple move is valid
     *
     * @return whether move is valid
     */
    public boolean isSimpleMove() {
        return start.getRow() - end.getRow() == 1 && Math.abs(start.getCell() - end.getCell()) == 1;
    }

    /**
     * Checks if single jump is valid
     *
     * @return whether jump is valid
     */
    public boolean isJumpMove() {
        return Math.abs(start.getRow() - end.getRow()) == 2 && Math.abs(start.getCell() - end.getCell()) == 2;
    }

    /**
     * printMove prints the starting move and the ending move into cmd line
     */

    public void printMove() {
        System.out.println("Move info:: (Row, Cell/Col) \n    " +
                "start: (" + getStartPos().getCell()+ ","+ getStartPos().getRow() + ")"+"\n    " +
                "end:   ("+getEndPos().getCell()+ ","+ getEndPos().getRow() + ")");

    }


    /**
     * Equals method to check the change in position if there is any
     * @param o the other move being checked and compared
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {return true;}
        if (o instanceof Move){
            Move other = (Move)o;
            return other.getStartPos().equals(getStartPos()) && other.getEndPos().equals(getEndPos());
        }
        return false;
    }
}
