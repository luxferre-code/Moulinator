package fr.ulille.moulinator;

import java.util.Objects;

public class Slot {

    private Joueur owner, exOwner;

    public Slot() { }

    public Slot(Joueur owner) {
        this.owner = owner;
    }

    public Joueur getOwner() {
        return owner;
    }

    public Joueur getExOwner() {
        return exOwner;
    }

    public boolean changeOwner(Joueur newOwner) {
        if(!this.owner.equals(newOwner)) {
            this.exOwner = this.owner;
            this.owner = newOwner;
            return true;
        }
        return false;
    }

    public boolean moveSlot(Slot s) {
        if(this.equals(s) || this.owner.equals(s.getOwner())) { return false; }
        this.exOwner = this.owner;
        s.exOwner = s.owner;
        this.owner = s.exOwner;
        s.owner = this.exOwner;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Slot slot)) return false;
        return Objects.equals(getOwner(), slot.getOwner()) && Objects.equals(getExOwner(), slot.getExOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getExOwner());
    }
}
