import commands.FrogCommand;
import commands.JumpToLeft;
import commands.JumpToRight;
import models.Frog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrogGame {
    private final Frog frog;
    private final List<FrogCommand> historyCommands;
    private int currentCommand = -1;

    public FrogGame() {
        frog = new Frog();
        historyCommands = new ArrayList<>();
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        Frog frog = new Frog();

        //Начало игры
        String userInput;

        frog.printField();
        while (true) {
            printCommandList();
            System.out.println("Ваша команда: ");
            userInput = scanner.nextLine();
            if (userInput.length() != 2) {
                printIncorrectInput();
                continue;
            }

            switch (userInput.charAt(0)) {
                case '0':
                    System.out.println("");
                    return;
                case '+': {
                    if (currentCommand != historyCommands.size() - 1) {
                        deleteAllCommandsAfterIndex();
                    }
                    int steps = Integer.parseInt(String.valueOf(userInput.charAt(1)));
                    FrogCommand cmd = new JumpToRight(frog, steps);

                    if (cmd.execute()) {
                        currentCommand++;
                        historyCommands.add(cmd);
                    } else
                        System.out.println("Команда не выполнена.");
                    break;
                }
                case '-': {
                    if (currentCommand != historyCommands.size() - 1) {
                        deleteAllCommandsAfterIndex();
                    }

                    int steps = Integer.parseInt(String.valueOf(userInput.charAt(1)));
                    FrogCommand cmd = new JumpToLeft(frog, steps);

                    if (cmd.execute()) {
                        currentCommand++;
                        historyCommands.add(cmd);
                    } else
                        System.out.println("Команда не выполнена.");

                    break;
                }
                case '<':
                    if (currentCommand < 0) {
                        System.out.println("История команд пуста. Невозможно выполнить команду Undo.");
                    } else {
                        historyCommands.get(currentCommand).undo();
                        currentCommand--;
                    }
                    break;
                case '>':
                    if (currentCommand == historyCommands.size() - 1)
                        System.out.println("Нет отмененных команд. Невозможно выполнить команду Redo.");
                    else {
                        currentCommand++;
                        historyCommands.get(currentCommand).execute();
                    }
                    break;
                case '!':
                    if (currentCommand < 0)
                        System.out.println("История команд пуста. Невозможно выполнить команду doAgain.");
                    else {
                        //Делаем клон, иначе не отработает команда Undo во второй раз
                        FrogCommand cmd = null;
                        try {
                            cmd = (FrogCommand) historyCommands.get(currentCommand).clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        cmd.execute();
                        currentCommand++;
                        historyCommands.add(cmd);
                    }

                    break;
                default:
                    printIncorrectInput();
                    break;
            }
            frog.printField();
        }
    }

    private void printCommandList() {
        System.out.println("Выберите необходимую команду:");
        System.out.println("    +N - прыгни на N шагов направо");
        System.out.println("    -N - прыгни на N шагов налево");
        System.out.println("    << - Undo (отмени последнюю команду)");
        System.out.println("    >> - Redo (повтори отменённую команду)");
        System.out.println("    !! - повтори последнюю команду");
        System.out.println("    00 - выход");
    }

    private void printIncorrectInput() {
        System.out.println("Введена неверная команда");
    }

    private void deleteAllCommandsAfterIndex() {
        historyCommands.subList(currentCommand, historyCommands.size()).clear();//Удаляем все команды после определенного индекса
    }
}
