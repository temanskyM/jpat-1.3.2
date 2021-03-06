package models;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    private int currentPosition;

    public Frog() {
        this.currentPosition = 5;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void printField() {
        System.out.println("Field:");

        System.out.print("  ");
        for (int i = 0; i < MAX_POSITION; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println(" ");

        System.out.print("{ ");
        for (int i = 0; i < MAX_POSITION; i++) {
            if (i != currentPosition)
                System.out.print("_");
            else
                System.out.print("*");
            System.out.print(" ");
        }
        System.out.println("}");
    }
}
