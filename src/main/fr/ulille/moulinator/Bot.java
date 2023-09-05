package fr.ulille.moulinator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Bot extends Joueur {

    public Bot(){
        super("Bot");
    }

    public int chooseSlotOwned() throws NoHavingSlotException {
        List<Integer> temp = Game.BOARD.allPositionPlayer(this);
        if(temp.isEmpty()) { throw new NoHavingSlotException("No slot owned"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Integer> temp = Game.BOARD.allFreePosition(slot);
        if(temp.isEmpty()) { throw new SlotHavingOwnerException("No free slot"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public void choose() {
        if(this.allPlaced) {
            int first = 0, to = 0;
            try {
                first = this.chooseSlotOwned();
                to = this.chooseSlotToMove(first);
            } catch (SlotHavingOwnerException e) {
                choose();
                return;
            } catch(NoHavingSlotException e) {
                Game.logger("No slot owned !");
            }
            System.out.println("Bot move " + first + " to " + to);
            Game.BOARD.moveSlot(first, to);
        } else {
            List<Integer> temps = Game.BOARD.allFreePosition();
            Collections.shuffle(temps);
            int first = temps.get(0);
            Game.BOARD.setJoueurOnSlot(first, this);
            System.out.println("Bot place on " + first);
        }
    }

    public static void main(String[] args) {
        Bot b = new Bot();
        b.choose();
    }
    
}
