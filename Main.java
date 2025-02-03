import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int fieldSize = 9;
        int leftSideDiagonal = 0;
        int rightSideDiagonal = 0;
        char currentchar = 0;
        boolean fullField = false;
        boolean player = true;
        boolean gameEnd = false;
        boolean gameWin = false;

        while (true) {

            String mainMenu = """
                    --Menu--
                    1. Start game.
                    2. Settings.
                    3. Exit
                    """;

            String gameMenu = """
                    --Game--
                    1. Start vs player.
                    2. Back to menu
                    """;

            String settings = """
                    --Settings--
                    1. Change size.
                    2. Back to menu.
                    """;

            String settingSize = """
                    --Settings--
                    1. 3x3.
                    2. 5x5.
                    3. 7x7.
                    4. 9x9.
                    5. Back to settings
                    """;

            System.out.println(mainMenu);

            System.out.println("Next step: ");
            if(sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 2) {
                    System.out.println(settings);
                    if (sc.hasNextInt()) {
                        int thirdChoice = sc.nextInt();
                        if (thirdChoice == 1) {
                            System.out.print(settingSize);
                            if(sc.hasNextInt()) {
                                int sizeChoice = sc.nextInt();
                                if (sizeChoice == 1) {
                                    fieldSize = 9;
                                    System.out.println("U choose 3x3.");
                                } else if (sizeChoice == 2) {
                                    fieldSize = 13;
                                    System.out.println("U choose 5x5.");
                                } else if (sizeChoice == 3) {
                                    fieldSize = 17;
                                    System.out.println("U choose 7x7.");
                                } else if (sizeChoice == 4) {
                                    fieldSize = 21;
                                    System.out.println("U choose 9x9.");
                                }
                            }
                        }
                    }
                } else if (choice == 1) {
                    System.out.println(gameMenu);
                    if(sc.hasNextInt()){
                        int secondChoice = sc.nextInt();
                        if(secondChoice == 1){

                            char[][] game = new char[fieldSize][fieldSize];

                            for (int i = 0; i < game.length; i++) {
                                for (int j = 0; j < game.length; j++) {
                                    game[i][j] = ' ';
                                }
                            }

                            while (!gameEnd) {

                                if(gameWin){
                                    gameEnd = true;
                                    System.out.println("Player " + currentchar + " win.");
                                }

                                for (int j = 2; j < game.length; j++) {
                                    if (j % 2 != 0) {
                                        game[1][j] = (char) ('0' + (j/ 2));
                                    }
                                }
                                for (int i = 2; i < game.length; i++) {
                                    if (i % 2 != 0) {
                                        game[i][1] = (char) ('0' + (i / 2));
                                    }
                                }
                                for (int i = 0; i < game.length; i++) {
                                    System.out.println();
                                    for (int j = 0; j < game.length; j++) {
                                        if (i % 2 == 0 && j % 2 != 0) {
                                            game[i][j] = '-';
                                        } else if (i % 2 == 0) {
                                            game[i][j] = '+';
                                        } else if (j % 2 == 0) {
                                            game[i][j] = '|';
                                        }
                                    }
                                }

                                for (int i = 0; i < game.length; i++) {
                                    for (int j = 0; j < game.length ; j++) {
                                        System.out.print(game[i][j]);
                                    }
                                    System.out.println();
                                }

                                if (player) {
                                    currentchar = 'X';
                                    System.out.println("It's player X turn.");
                                } else {
                                    currentchar = 'O';
                                    System.out.println("It's player O turn.");
                                }

                                int first_coord;
                                while (true) {
                                    System.out.println("Enter first coordinate: ");
                                    if (sc.hasNextInt()) {
                                        first_coord = sc.nextInt();
                                        if (first_coord > (fieldSize - 1) / 2 || first_coord < 1) {
                                            System.out.println("Too huge coordinate, try again.");
                                        } else {
                                            first_coord = (first_coord * 2) + 1;
                                            break;
                                        }
                                    } else {
                                        System.out.println("Invalid input, please enter a number.");
                                        sc.next();
                                    }
                                }

                                int second_coord;
                                while (true) {
                                    System.out.println("Enter second coordinate: ");
                                    if (sc.hasNextInt()) {
                                        second_coord = sc.nextInt();
                                        if (second_coord > (fieldSize - 1) / 2 || second_coord < 1) {
                                            System.out.println("Too huge coordinate, try again.");
                                        } else {
                                            second_coord = (second_coord * 2) + 1;
                                            break;
                                        }
                                    } else {
                                        System.out.println("Invalid input, please enter a number.");
                                        sc.next();
                                    }
                                }

                                if (game[first_coord][second_coord] == ' ') {
                                    game[first_coord][second_coord] = currentchar;
                                    player = !player;

                                    for(int i = 3; i <= fieldSize - 1 ; i += 2){
                                        if(game[i][3] == currentchar && game[i][5] == currentchar && game[i][7] == currentchar){
                                            gameWin = true;
                                        }
                                        for(int j = 3; j <= fieldSize - 1 ; j += 2) {
                                            if (game[3][j] == currentchar && game[5][j] == currentchar && game[7][j] == currentchar) {
                                                gameWin = true;
                                            }
                                        }
                                    }

                                    if(game[3][3] == currentchar && game[5][5] == currentchar && game[7][7] == currentchar){
                                        gameWin = true;
                                    }
                                    if (game[3][7] == currentchar && game[5][5] == currentchar && game[7][3] == currentchar){
                                        gameWin = true;
                                    }

                                } else {
                                    System.out.println("Cell is occupied, try again.");
                                }

                                if(gameWin){
                                    gameEnd = true;
                                    for (int i = 0; i < game.length; i++) {
                                        for (int j = 0; j < game.length; j++) {
                                            System.out.print(game[i][j]);
                                        }
                                        System.out.println();
                                    }
                                    System.out.println("Player " + currentchar + " win.");
                                }

                                for (int i = 3; i < fieldSize - 1; i += 2) {
                                    for (int j = 3; j < fieldSize - 1; j += 2) {
                                        if (game[i][j] == ' ') {
                                            fullField = false;
                                            break;
                                        } else {
                                            fullField = true;
                                        }
                                    }
                                    if (!fullField) break;
                                }

                                if(!gameWin && fullField){
                                    gameEnd = true;
                                    for (int i = 0; i < game.length; i++) {
                                        for (int j = 0; j < game.length; j++) {
                                            System.out.print(game[i][j]);
                                        }
                                        System.out.println();
                                    }
                                    System.out.println("Draw.");
                                }
                            }
                        }
                    }
                }  else if (choice == 3) {
                    System.out.println("Game closed...");
                    break;
                }
            } else{
                System.out.println("Error, try again.");
                sc.next();
            }
        }
    }
}
