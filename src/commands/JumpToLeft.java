package commands;

import models.Frog;

public class JumpToLeft extends FrogCommand {
    int steps;

    public JumpToLeft(Frog frog, int steps) {
        super(frog);
        this.steps = steps;
    }

    @Override
    public boolean execute() {
        if (frog.getCurrentPosition() - steps <= frog.MIN_POSITION)
            return false;

        backup();
        frog.setCurrentPosition(frog.getCurrentPosition() - steps);
        return true;
    }
}
