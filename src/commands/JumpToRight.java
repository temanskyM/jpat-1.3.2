package commands;

import models.Frog;

public class JumpToRight extends FrogCommand{
    int steps;

    public JumpToRight(Frog frog, int steps) {
        super(frog);
        this.steps = steps;
    }

    @Override
    public boolean execute() {
        if (frog.getCurrentPosition() + steps >= frog.MAX_POSITION)
            return false;

        backup();
        frog.setCurrentPosition(frog.getCurrentPosition() + steps);
        return true;
    }
}
