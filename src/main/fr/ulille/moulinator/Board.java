package fr.ulille.moulinator;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * <p>La classe qui initialise des joueur</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
public class Board implements Iterable<Slot>, Serializable {

    /**
     * int : les dimensions du plateau
     */
    public int height, width;

    /**
     * List&lt;List&lt;Slot&gt;&gt; : la liste des slots du plateau
     */
    private final List<List<Slot>> slots;

    /**
     * int Height: la hauteur par defaut du plateau
     */
    private static final int DEFAULT_HEIGHT = 3;

    /**
     * int Width: la largeur par defaut du plateau
     */
    private static final int DEFAULT_WIDTH = 3;

    /**
     * Map&lt;Integer, List&lt;Integer&gt;&gt; : les possibilités de mouvement
     */
    public static final Map<Integer, List<Integer>> POSSIBILITY = loadPossibility();
    private static final List<List<Integer>> COLUMN_CHECK = loadColumnList();

    /**
     * Constructeur de la classe Board avec les dimensions
     * @param height : hauteur du plateau
     * @param width : largeur du plateau
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.slots = new ArrayList<>();
        this.initBoard();
    }

    /**
     * Constructeur de la classe Board avec les dimensions par defaut
     */
    public Board() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    /**
     * Methode qui rempli le plateau de slots disponibles
     * @see Slot
     */
    private void fillBoard() {
        for(int i = 0; i < this.height * 2 + 1; i++) {
            List<Slot> row = new ArrayList<>();
            for(int j = 0; j < this.width; j++) {
                row.add(new Slot());
            }
            this.slots.add(row);
        }
    }

    /**
     * Methode qui rempli le plateau de slots disponibles (partie central)
     * @see Slot
     * @see List
     * @see ArrayList
     * @see Board
     */
    private void fillMiddle() {
        List<Slot> temps = new ArrayList<>();
        for(int i = 0; i < this.width; i++) {
            temps.add(new Slot());
        }
        this.slots.get(this.height + 1).addAll(temps);
    }

    /**
     * Methode qui initialise le plateau
     * @see Board#fillBoard
     * @see Board#fillMiddle
     */
    private void initBoard() {
        fillBoard();
        fillMiddle();
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

    public void moveSlot(int first, int to) {
        Slot s1 = this.concat2DList().get(first);
        Slot s2 = this.concat2DList().get(to);
        s1.moveSlot(s2);
    }

    public void setJoueurOnSlot(int slot, Joueur j) {
        this.concat2DList().get(slot).changeOwner(j);
        if(j != null) {
            j.onBoard = j.onBoard + 1;
            if(j.onBoard ==6) { 
                j.allPlaced = true; 
            }
        }
    }

    public boolean sontAligne(int slot) {
        List<Slot> map = this.concat2DList();
        Joueur j = map.get(slot).getOwner();
        // Ligne horizontal
        switch(slot % 3) {
            case 0 -> {
                if(j.equals(map.get(slot + 1).getOwner()) && j.equals(map.get(slot + 2).getOwner())) return true;
            }
            case 1 -> {
                if(j.equals(map.get(slot + 1).getOwner()) && j.equals(map.get(slot - 1).getOwner())) return true;
            }
            case 2 -> {
                if(j.equals(map.get(slot - 1).getOwner()) && j.equals(map.get(slot - 2).getOwner())) return true;
            }
        }

        //Ligne Vertical
        for(List<Integer> line : COLUMN_CHECK) {
            if(line.contains(slot)) {
                List<Integer> temps = new ArrayList<>(line);
                temps.remove(Integer.valueOf(slot));
                boolean good = true;
                for(int i : temps) {
                    if(!j.equals(map.get(i).getOwner())) {
                        good = false;
                        break;
                    }
                }
                return good;
            }
        }

        return false;
    }

    public static List<List<Integer>> loadColumnList() {
        List<List<Integer>> t = new ArrayList<>();
        try(Scanner sc = new Scanner(new File("resources/columnChecker.csv"))) {
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                List<Integer> columnLine = new ArrayList<>();
                for(String s : line) {
                    columnLine.add(Integer.parseInt(s));
                }
                t.add(columnLine);
            }
        } catch(Exception ignored) {}
        return t;
    }
}
