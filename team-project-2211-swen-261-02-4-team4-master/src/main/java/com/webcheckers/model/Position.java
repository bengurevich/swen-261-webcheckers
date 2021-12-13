package com.webcheckers.model;

/**
 * class that represents the position in the model tier
 */
public class Position {
    private int row; //0-7
    private int cell; // the cell in the row (should be called column). 0-7

    /**
     * Constructor for Position
     * @param row the row number
     * @param cell the column number
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Retrieves row number of position
     * @return int row
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves column number of position
     * @return int cell
     */
    public int getCell() {
        return cell;
    }

    /**
     * Creates a new position only if the parameters are correct.
     *
     * @param row an int of any size.
     * @param cell an int of any size.
     * @return a new position with row and cell or null if the inputs are wrong.
     */
    public static Position createTestPosition(int row, int cell) {
        if(row < 8 && row >= 0 && cell < 8 && cell >= 0) {
            return new Position(row, cell);
        }
        return null;
    }

    /**
     * Equals method for position
     * @param o the other position being compared
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {return true;}
        if (o instanceof Position){
            Position other = (Position)o;
            return other.getRow() == getRow() && other.getCell() == getCell();
        }
        return false;
    }
}
