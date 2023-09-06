package fr.ulille.moulinator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Bot extends Joueur {

    public Bot(){
        super("Bot");
        List<Color> colors = new ArrayList<>();
        for(Color c : Color.values()) {
            if(c != Color.ANSI_RESET && !c.isUsed()) {
                colors.add(c);
            }
        }
        Collections.shuffle(colors);
        this.color = colors.get(0);
        this.color.setUsed(true);
    }

    public int chooseSlotOwned() throws NoHavingSlotException {
        List<Integer> temp = Game.Board.allPositionPlayer(this);
        if(temp.isEmpty()) { throw new NoHavingSlotException("No slot owned"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Integer> temp = Game.Board.allFreePosition(slot);
        if(temp.isEmpty()) { throw new SlotHavingOwnerException("No free slot"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public int chooseEnnemiSlot() {
        List<Integer> temp = Game.Board.allPositionPlayer(Game.isPlayer1Turn ? Game.p2 : Game.p1);
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public boolean choose() {
        int slotPlace = -1;
        if(this.allPlaced) {
            int first = 0, to = 0;
            try {
                first = this.chooseSlotOwned();
                to = this.chooseSlotToMove(first);
            } catch (SlotHavingOwnerException e) {
                choose();
                return false;
            } catch(NoHavingSlotException e) {
                Game.logger("No slot owned !");
            }
            System.out.println("Bot move " + first + " to " + to);
            Game.Board.moveSlot(first, to);
            slotPlace = to;
        } else {
            List<Integer> temps = Game.Board.allFreePosition();
            Collections.shuffle(temps);
            int first = temps.get(0);
            Game.Board.setJoueurOnSlot(first, this);
            System.out.println("Bot place on " + first);
            this.addPiecePlaced();
            this.onBoard++;
            slotPlace = first;
        }

        if(Game.Board.sontAligne(slotPlace)) {
            int ennemiSlot = chooseEnnemiSlot();
            Game.Board.setJoueurOnSlot(ennemiSlot, null);
            System.out.println(this.NAME + " remove " + ennemiSlot);
        }

        return true;
    }

    public static void main(String[] args) {
        Bot b = new Bot();
        b.choose();
    }
    
}
