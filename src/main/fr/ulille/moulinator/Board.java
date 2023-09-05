package fr.ulille.moulinator;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class Board implements Iterable<Slot>, Serializable {

    public int height, width;
    private List<List<Slot>> slots;
    private static final int DEFAULT_HEIGHT = 3;
    private static final int DEFAULT_WIDTH = 3;
    public static final Map<Integer, List<Integer>> POSSIBILITY = loadPossibility();

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
        int x = 0;
        for(Slot s : this) {
            if((char) ('A' + x) == 'X') { x++; }
            board = board.replaceFirst((char) ('A' + x) + "", s.toString());
            x++;
        }
        return board;
    }

    @Override
    public Iterator<Slot> iterator() {
        return this.concat2DList().iterator();
    }

    private List<Slot> concat2DList() {
        List<Slot> slots = new ArrayList<>();
        for(List<Slot> row : this.slots) {
            slots.addAll(row);
        }
        return slots;
    }

    public List<List<Slot>> getSlots() {
        return slots;
    }

    public List<Integer> allPositionPlayer(Joueur j) {
        List<Integer> positions = new ArrayList<>();
        int x = 0;
        for(Slot s : this.concat2DList()) {
            if(s.getOwner() != null && s.getOwner().equals(j)) { positions.add(x); }
            x++;
        }
        return positions;
    }

    public List<Integer> allFreePosition() {
        List<Integer> positions = new ArrayList<>();
        int x = 0;
        for(Slot s : this.concat2DList()) {
            if(s.getOwner() == null) { positions.add(x); }
            x++;
        }
        return positions;
    }

    public List<Integer> allFreePosition(int slotPos) {
        List<Integer> positions = new ArrayList<>();
        for(int i : POSSIBILITY.get(slotPos)) {
            if(this.concat2DList().get(i).getOwner() == null) { positions.add(i); }
        }
        return positions;
    }

    public static Map<Integer, List<Integer>> loadPossibility() {
        Map<Integer, List<Integer>> t = new HashMap<>();
        try(Scanner sc = new Scanner(new File("resources/possibility.csv"))) {
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                List<Integer> temp = new ArrayList<>();
                for(String s : line[1].split("\\.")) {
                    temp.add(Integer.parseInt(s));
                }
                t.put(Integer.parseInt(line[0]), temp);
            }
        } catch(Exception ignored) {}
        return t;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public void moveSlot(int first, int to) {
        Slot s1 = this.concat2DList().get(first);
        Slot s2 = this.concat2DList().get(to);
        s1.moveSlot(s2);
    }

    public void setJoueurOnSlot(int slot, Joueur j) {
        this.concat2DList().get(slot).changeOwner(j);
    }

}
