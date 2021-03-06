package commands;

import models.Frog;

public abstract class FrogCommand  implements Cloneable{
    public Frog frog;

    int savePosition;

    public FrogCommand(Frog frog) {
        this.frog = frog;
    }

    protected void backup() {
        savePosition = frog.getCurrentPosition();
    }

    public void undo() {
        frog.setCurrentPosition(savePosition);
    }

    public abstract boolean execute();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
