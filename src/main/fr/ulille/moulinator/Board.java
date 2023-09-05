package fr.ulille.moulinator;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public int height, width;
    private List<List<Slot>> slots;
    private static final int DEFAULT_HEIGHT = 0;
    private static final int DEFAULT_WIDTH = 0;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.slots = new ArrayList<>();
        this.initBoard();
    }

    public Board() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    private void fillBoard() {
        for(int i = 0; i < this.height; i++) {
            List<Slot> row = new ArrayList<>();
            for(int j = 0; j < this.width; j++) {
                row.add(new Slot());
            }
            this.slots.add(row);
        }
    }

    private void fillMiddle() {
        List<Slot> temps = new ArrayList<>();
        for(int i = 0; i < this.width; i++) {
            temps.add(new Slot());
        }
        this.slots.get(this.height / 2).addAll(temps);
    }

    private void initBoard() {
        fillBoard();
        fillMiddle();
    }

    public Slot getSlot(int x, int y) {
        return this.slots.get(y).get(x);
    }

    public List<Slot> getRow(int y) {
        return this.slots.get(y);
    }

    private Slot getSlotMiddle(int x, boolean isLeft) {
        if(isLeft) { return this.getSlot(x, height / 2); }
        return this.getSlot(width / 2 + x, height / 2);
    }

    public List<Slot> getColumn(int x) {
        List<Slot> column = new ArrayList<>();
        for(int i = 0; i < this.height; i++) {
            if(i == height / 2) { column.add(this.getSlotMiddle(x, x < width / 2)); }
            else { column.add(this.getSlot(x, i)); }
        }
        return column;
    }

    public boolean checkLine(int ligne, Joueur j) {
        List<Slot> row = this.getRow(ligne);
        for(Slot s : row) {
            if(!s.getOwner().equals(j)) { return false; }
        }
        return true;
    }

}
