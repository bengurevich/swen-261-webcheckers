package com.webcheckers.model;

import com.webcheckers.application.DevMode;

/**
 * class that represents a space on the checkers board
 */
public class Space {

    public enum Shade {LIGHT, DARK}
    public enum Status {VACANT, OCCUPIED, INVALID}
    private Status status;
    private Shade shade;

    /**
     * Produces string rep. of space
     * @return String
     */
    @Override
    public String toString() {
        return "Space{" +
                "piece=" + piece +
                ", cellIdx=" + cellIdx +
                '}';
    }

    public Piece piece = null;
    private int cellIdx;
    private final int limit = 7;
    public Space(int cellIdx, Piece piece, Status status, Shade shade){
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.status = status;
        this.shade = shade;
    }


    /**
     * Space Constructor
     * @param cellIdx the column id for the space
     * @param row_index the row number of the space
     */
    public Space(int cellIdx, int row_index)
    {
        this.cellIdx = cellIdx;
        if(row_index%2 ==0)
        {
            if(this.cellIdx%2 ==0)
            {
                this.shade = Shade.LIGHT;
                this.status = Status.VACANT;
            }
            else this.shade = Shade.DARK;
            this.status = Status.VACANT;
        }
        else if(row_index%2 == 1)
        {
            if(this.cellIdx%2 == 0)
            {
                this.shade = Shade.DARK;
                this.status = Status.VACANT;
            }
            else this.shade = Shade.LIGHT;
            this.status = Status.VACANT;
        }
        if(this.shade.equals(Shade.DARK) && row_index >= 5 && row_index <= 7)
        {
            addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
        }
        else if(this.shade.equals(Shade.DARK) && row_index >= 0 && row_index <= 2)
        {
            addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
        }
    }

    /**
     * Space Constructor with devMode capability
     * @param cellIdx the column id of space
     * @param row_index the row number of space
     * @param devMode the mode to create the board in, used for testing and demo
     */
    public Space(int cellIdx, int row_index, DevMode devMode)
    {

        //setting pieces to be on the right color tiles
        this.cellIdx = cellIdx;
        if(row_index%2 ==0)
        {
            if(this.cellIdx%2 ==0)
            {
                this.shade = Shade.LIGHT;
                this.status = Status.VACANT;
            }
            else this.shade = Shade.DARK;
            this.status = Status.VACANT;
        }
        else if(row_index%2 == 1)
        {
            if(this.cellIdx%2 == 0)
            {
                this.shade = Shade.DARK;
                this.status = Status.VACANT;
            }

            else this.shade = Shade.LIGHT;
            this.status = Status.VACANT;
        }

        if (devMode==DevMode.KING){
            if(this.shade.equals(Shade.DARK) && row_index == 1 ) // place red pieces 1 away from being king, in row 1
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));

            else if(this.shade.equals(Shade.DARK) && row_index == 6) // place white 1 away from being king in row 6
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
        }

        else if  (devMode==DevMode.CHAINING){
            // bottom half of board, white eating 2 whites // place red pieces in row 0,
            if(this.shade.equals(Shade.DARK) &&  row_index == 0 && cellIdx ==5)
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
                //place white pieces in row 1, 3
            else if(this.shade.equals(Shade.DARK) &&  row_index == 1  && cellIdx == 4 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
            else if(this.shade.equals(Shade.DARK) &&  row_index == 3 && cellIdx == 2 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));

            //top half of baord, red eating 2 reds. // place white pieces in row 7,
            if(this.shade.equals(Shade.DARK) &&  row_index == 7  && cellIdx == 2 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
            //place red pieces in row 6, 4
            if(this.shade.equals(Shade.DARK) &&  row_index == 6  && cellIdx == 3 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
            if(this.shade.equals(Shade.DARK) &&  row_index == 4 && cellIdx == 5 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
        }

        else if (devMode==DevMode.KING_CHAINING){
            // bottom half of board, white eating 2 whites // place red pieces in row 0,
            if(this.shade.equals(Shade.DARK) &&  row_index == 4 && cellIdx ==1)
                addCurrentPiece(new Piece(Piece.type.KING, Color.WHITE));
                //place white pieces in row 1, 3
            else if(this.shade.equals(Shade.DARK) &&  row_index == 3  && cellIdx == 2 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
            else if(this.shade.equals(Shade.DARK) &&  row_index == 1 && cellIdx == 4 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));

            //top half of board, red eating 2 reds. // place white pieces in row 7,
            if(this.shade.equals(Shade.DARK) &&  row_index == 3  && cellIdx == 6 )
                addCurrentPiece(new Piece(Piece.type.KING, Color.RED));
            //place red pieces in row 6, 4
            if(this.shade.equals(Shade.DARK) &&  row_index == 4  && cellIdx == 5 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
            if(this.shade.equals(Shade.DARK) &&  row_index == 6 && cellIdx == 3 )
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
        }

        else if (devMode==DevMode.ENDGAME){
            if(this.shade.equals(Shade.DARK) &&  row_index == 4 && cellIdx ==1)
                addCurrentPiece(new Piece(Piece.type.KING, Color.WHITE));

            if(this.shade.equals(Shade.DARK) &&  row_index == 3 && cellIdx ==2)
                addCurrentPiece(new Piece(Piece.type.KING, Color.RED));
        }

        else if(devMode == DevMode.TIE){
            if(this.shade.equals(Shade.DARK) &&  row_index == 7 && cellIdx ==0)
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
            if(this.shade.equals(Shade.DARK) &&  row_index == 5 && cellIdx ==4)
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));

            if(this.shade.equals(Shade.DARK) &&  row_index == 3 && cellIdx ==4)
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
            if(this.shade.equals(Shade.DARK) &&  row_index == 6 && cellIdx ==1)
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));

        }
        else if(devMode == DevMode.NONE) { //no dev mode
            if(this.shade.equals(Shade.DARK) && row_index >= 5 && row_index <= 7)
            {
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
            }
            else if(this.shade.equals(Shade.DARK) && row_index >= 0 && row_index <= 2)
            {
                addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
            }
        }

    }


    /**
     * Returns column of space
     * @return cellIdx
     */
    public int getCellIdx() { return cellIdx; }

    public void setShade(Space.Shade shade) {this.shade = shade; }

    /**
     * Checks to see if a space is a valid areas for a move.
     * @return boolean
     */
    public boolean isValid()
    {
        return this.shade.equals(Shade.DARK) && this.status.equals(Status.VACANT);
    }

    /**
     * Gets shade of space
     * @return Shade
     */
    public Shade getShade(){
        return shade;
    }

    /**
     * Gets piece of space (if any)
     * @return Piece or null
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Checks if a space has a piece
     * @return boolean
     */
    public Boolean hasPiece(){
        if (this.piece == null)
            return false;
        return true;
    }

    public boolean moveTo (Space origin){
        if(origin == null) {
            return false;
        }
        else if(status != Status.VACANT) {
            return false;
        }
        else if(origin.getPiece() == null) {
            return false;
        }

        addCurrentPiece(origin.getPiece());
        origin.removeCurrentPiece();
        return true;
    }

    /**
     * Adds piece to space
     * @param pieceHelper a piece to be added
     * @return status of space
     */
    public Status addCurrentPiece(Piece pieceHelper) {
        if(status.equals(Status.VACANT)) {
            this.piece = pieceHelper;
            status = Status.OCCUPIED;
            return status;
        } else{
            return status;
        }
    }

    /**
     * Removes the piece on the space and changes the space's status to VACANT
     */
    public void removeCurrentPiece() {
            this.piece = null;
            status = Status.VACANT;

    }

    public boolean isNotTaken() {
        return (status != Status.INVALID && status != Status.OCCUPIED);
    }

    public boolean isTaken() {
        return (this.status == Status.OCCUPIED);
    }

    public boolean isAllowed() {
        return(this.status == Status.VACANT);
    }

    /**
     * Gets the status of a space
     * @return Status
     */
    public Status getState() {
        return this.status;
    }

    /**
     * Makes a copy of a space.
     * @return Space
     */
    public Space copySpace(){
        if(this.piece != null)
            return new Space(getCellIdx(), piece.copyPiece(), getState(), getShade());
        return new Space(getCellIdx(), null, getState(), getShade());
    }






}