package fr.ulille.moulinator;

import java.util.Collections;
import java.util.List;

public final class Bot extends Joueur {

    /**
     * Constructeur de la classe Bot avec le nom "Bot" par défaut et la couleur par defaut
     */
    public Bot(){
        super("Bot");
    }

    /**
     * M
     * @return int : la position du slot sur le plateau
     * @throws NoHavingSlotException : Le slot choisie n'est pas possédé par le bot
     */
    public int chooseSlotOwned() throws NoHavingSlotException {
        List<Integer> temp = Game.Board.allPositionPlayer(this);
        if(temp.isEmpty()) { throw new NoHavingSlotException("No slot owned"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    /**
     * @param slot
     * @return int : la position du slot sur le plateau où le bot veux déplacer son pion
     * @throws SlotHavingOwnerException : Le slot choisie est déjà possédé par un joueur ou est déjà occupé
     */
    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Integer> temp = Game.Board.allFreePosition(slot);
        if(temp.isEmpty()) { throw new SlotHavingOwnerException("No free slot"); }
        Collections.shuffle(temp);
        return temp.get(0);
    }

    /**
     * Methode qui permet au bot de choisir un slot et de le déplacer
     * @see
     * @see
     * @return boolean : si le bot a réussi à jouer
     */
    public boolean choose() {
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
        } else {
            List<Integer> temps = Game.Board.allFreePosition();
            Collections.shuffle(temps);
            int first = temps.get(0);
            Game.Board.setJoueurOnSlot(first, this);
            System.out.println("Bot place on " + first);
            this.addPiecePlaced();
        }
        return true;
    }

    public static void main(String[] args) {
        Bot b = new Bot();
        b.choose();
    }
    
}
