package com.webcheckers.model;

import com.webcheckers.application.DevMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * class that represents a row on the checkers board
 */
public class Row{
    private final int index;
    private final int redLimit = 7;
    private final int whiteLimit = 0;

    public ArrayList<Space> spaceList;

    // to reverse board, create copy of board in reversed state

    /**
     * Makes string representation of Row contents.
     * @return String
     */
    @Override
    public String toString() {
        return "Row{" +
                "spaceList=" + spaceList +
                '}';
    }

    /**
     * Row constructor which creates list of spaces.
     * @param index the index of the row
     */
    public Row(int index)
    {
        spaceList = new ArrayList<>();
        this.index = index;
        for(int a = 0; a<8; a++)
        {   //devmode determines the board start arrangement
            spaceList.add(new Space(a, index, DevMode.KING_CHAINING));
        }
    }

    /**
     * Row creator with pre-made list of spaces
     * @param index row index
     * @param spaces list of spaces
     */
    public Row(int index, ArrayList<Space> spaces){
        this.index = index;
        this.spaceList = spaces;
    }

    /**
     * Retrieves index of row
     * @return int row index
     */
    public int getIndex(){return this.index;}

    /**
     * Iterator for row which iterates differently depending on color.
     * @param color color for iterating (true for red, false for white)
     * @return Iterator<Space>
     */
    public Iterator<Space> iterate_by_color(boolean color) {
        if(color) {
            return new Iterator<Space>() {
                int i = -1;
                @Override
                public boolean hasNext() {
                    return i+1 <= redLimit;
                }

                @Override
                public Space next() {
                    i= i+1;
                    return spaceList.get(i);
                }
            };
        }
        else{
            return new Iterator<Space>() {
                int a = 8;
                @Override
                public boolean hasNext() {
                    return a-1 >= whiteLimit;
                }

                @Override
                public Space next() {
                    a = a-1;
                    return spaceList.get(a);
                }
            };
        }
    }

    /**
     * Retrieves list of spaces
     * @return List<Space></Space>
     */
    public List<Space> getSpaces() {
        return spaceList;
    }

    /**
     * Gets the piece at the given column.
     *
     * @param column the column or index that the piece is at.
     * @return the desired piece.
     */
    public Piece getPieceAtIndex(int column) {
        return spaceList.get(column).getPiece();
    }

    /**
     * Copys Row contents
     * @return new Row
     */
    public Row copyRow(){
        ArrayList<Space> copySpaces = new ArrayList<>();
        for (Space s: spaceList){
            copySpaces.add(s.copySpace());
        }
        return new Row(getIndex(), copySpaces);
    }
}
