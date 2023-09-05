package fr.ulille.moulinator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Board implements Iterable<Slot> {

    public int height, width;
    private List<List<Slot>> slots;
    private static final int DEFAULT_HEIGHT = 3;
    private static final int DEFAULT_WIDTH = 3;

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
        for(int i = 0; i < this.height * 2 + 1; i++) {
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
        this.slots.get(this.height + 1).addAll(temps);
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

    private Slot getSlotMiddleLine(int x, boolean isLeft) {
        if(isLeft) { return this.getSlot(x, height / 2); }
        return this.getSlot(width / 2 + x, height / 2);
    }

    private Slot getSlotMiddleColumn(int y, boolean isTop) {
        if(isTop) { return this.getSlot(width / 2, y); }
        return this.getSlot(width / 2, height / 2 + y);
    }

    public List<Slot> getColumn(int x) {
        List<Slot> column = new ArrayList<>();
        for(int i = 0; i < this.height; i++) {
            if(i == height / 2) { column.add(this.getSlotMiddleLine(x, x < width / 2)); }
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

    @Override
    public String toString() {
        // Chargement du fichier dans resources/board
        String board = "";
        try(Scanner sc = new Scanner(new File("resources/board"))) {
            while(sc.hasNextLine()) {
                board += sc.nextLine() + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Remplacement des slots par les joueurs
        int x = 1;
        for(Slot s : this) {
            board = board.replaceFirst(x + "", s.toString());
            x++;
        }
        return board;
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);
    }

    @Override
    public Iterator<Slot> iterator() {
        List<Slot> slots = new ArrayList<>();
        for(List<Slot> row : this.slots) {
            slots.addAll(row);
        }
        return slots.iterator();
    }
}
