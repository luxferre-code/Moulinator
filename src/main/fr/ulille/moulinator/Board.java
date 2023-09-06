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
    private List<List<Slot>> slots;

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

    /**
     * @param x
     * @return List&lt;Slot&gt; : la colonne x
     * @see Slot
     * @see List
     */
    public List<Slot> getColumn(int x) {
        List<Slot> column = new ArrayList<>();
        for(int i = 0; i < this.height; i++) {
            if(i == height / 2) { column.add(this.getSlotMiddleLine(x, x < width / 2)); }
            else { column.add(this.getSlot(x, i)); }
        }
        return column;
    }

    /**
     * @param ligne
     * @param j
     * @return
     * @see Joueur
     * @see Slot
     * @see List
     */
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
        Board b = new Board();
        b.initBoard();
        System.out.println(b);
    }

    public void moveSlot(int first, int to) {
        Slot s1 = this.concat2DList().get(first);
        Slot s2 = this.concat2DList().get(to);
        s1.moveSlot(s2);
    }

    public void setJoueurOnSlot(int slot, Joueur j) {
        this.concat2DList().get(slot).changeOwner(j);
        j.onBoard = j.onBoard + 1;
        if(j.onBoard ==6) { 
            j.allPlaced = true; 
        }
    }

    public boolean sontAligne(Slot s){
        boolean renvoi=false;
        int x=s.getx();
        if(this.slots.get(x).size()==height){
            renvoi = true;
            for(int j=0; j<height;j++){
                if(!this.slots.get(x).get(j).getOwner().equals(s.getOwner())){
                    renvoi=false;
                }
            }
        }
        else{
            for(int i=0; i<2;i++){
                boolean tempo = true;
                for(int j=0; j<this.height;j++){
                    if(!slots.get(x).get(i*3+j).getOwner().equals(s.getOwner())){
                        tempo=false;
                    }
                }
                if(tempo){
                    renvoi=tempo;
                }
            }
        }
        if(renvoi){
            return renvoi;
        }
        for(int i=0;i<this.height;i++){
            renvoi = true;
            for(int j=0;j<this.height;j++){
                if(!slots.get(j*(3-i)).get(i).getOwner().equals(s.getOwner())){
                    renvoi=false;
                }
            }
            if(renvoi){
                return renvoi;
            }
            for(int j=0;j<this.height;j++){
                if(!slots.get(j*(3-i)).get(this.height*2+1-i).getOwner().equals(s.getOwner())){
                    renvoi=false;
                }
            }
            if(renvoi){
                return renvoi;
            }
        }
        for(int i=0; i<this.height;i++){
        boolean check1=true;
        boolean check2=true;
        if(!slots.get(i).get(2).equals(s.getOwner())){
        check1=false;
        }
        if(!slots.get(3+i).get(2).equals(s.getOwner())){
        check2=false;
        }
        if(check1||check2){
        return true;
        }
        }
            return false;
        }

    public Joueur getJoueurOnSlot(int i) {
        return this.concat2DList().get(i).getOwner();
    }
}
