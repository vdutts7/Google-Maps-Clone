
package byow.lab12;

import java.util.ArrayList;
import java.util.List;

import byow.TileEngine.*;

public class Hexagon {
    private TETile tile;
    private int side;
    private Position upperLeft;
    private Position lowerLeft;
    private Position lowerRight;

    public Hexagon(Position upperLeft, int side, TETile tile) {
        this.upperLeft = upperLeft;
        this.side = side;
        this.tile = tile;
        this.lowerLeft = new Position(this.upperLeft, -1*(this.side - 1), -1*(this.side - 1));
        this.lowerRight = new Position(this.lowerLeft, this.getRowWidth(this.side), 0);
    }

    public List<Position> getHexPositions() {
        List<Position> positions = new ArrayList<>();
        for(int y = 0; y < getHexHeight(); y++) {
            int startRow = this.getRowStart(y);
            int width = this.getRowWidth(y);
            for(int x = 0; x < width; x++) {
                positions.add(new Position(startRow + x, this.upperLeft.getY() - y));
            }
        }
        return positions;
    }

    private int getRowWidth(int row) {
        if (row < side) {
            return this.side + row*2;
        } else {
            int startSize = this.side + (this.side - 1)*2;
            return startSize - (row%side)*2;
        }
    }

    private int getRowStart(int row) {
        if (row <this.side) {
            return this.upperLeft.getX() - row;
        } else {
            int startOff = this.upperLeft.getX() - (this.side - 1);
            return startOff + row;
        }
    }

    private int getHexHeight() {
        return side*2;
    }

    private Position getLowerLeft() {
        return this.lowerLeft;
    }

    private Position getLowerRight() {
        return this.lowerRight;
    }

    private Position getUpperLeft() {
        return this.upperLeft;
    }

    //private int getHexWidth() { }

}

