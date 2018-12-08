package takenoko.moteur;

public class Commande {
    private boolean conditions;
    private Enums.Action action;

    public Commande(Enums.Action action) {
        this.action = action;
    }

    public boolean isConditions() {
        return conditions;
    }

    public void setConditions(boolean conditions) {
        this.conditions = conditions;
    }

    public Enums.Action getAction() {
        return action;
    }

    public void setAction(Enums.Action action) {
        this.action = action;
    }

}
