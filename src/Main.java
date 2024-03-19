/*
<<<<<<< HEAD

                                                Ý Æ Ã-Ø Œ ¿

Code description:
    Software that implements circled linked list. For a duck duck goose game

Authors:
    * Mykyta Kuznietsov

Class:
    * AUCSC 112 LAB 1H04

ID numbers:
    * 1796900


Date:
    * March 19, 2024

*/

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Import and create a scanner */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many players?");
        int numPlayers = keyboard.nextInt();

        DCircLinkList<String> list = new DCircLinkList<>();
        Random rand = new Random();

        /* fill in the linked list */
        inputPlayerNames(keyboard, numPlayers, list);

        /* Determine it player. Use array of size one so that string element can be changed anywhere as array is pointer type*/
        String[] itName = new String[1];
        itName[0] = chooseItPlayer(keyboard, list, numPlayers);

        /* fill in the linked list */
        startGame(keyboard, rand, list, itName, numPlayers);
    }

    /* function to fill in the linked list with players */
    private static void inputPlayerNames(Scanner keyboard, int numPlayers, DCircLinkList<String> list) {
        for (int i = 0; i < numPlayers - 1; i++) {
            System.out.println("Please enter Player's " + (i + 1) + "'s name");
            String name = keyboard.next();
            list.addLast(name);
        }
    }

    /* function for the it player */
    private static String chooseItPlayer(Scanner keyboard, DCircLinkList<String> list, int numPlayers) {
        System.out.println("Please enter Player's " + numPlayers + "'s name");
        return keyboard.next();
    }

    /* function to start the game and loop through the rounds */
    private static void startGame(Scanner keyboard, Random rand, DCircLinkList<String> list, String itName[], int numPlayers) {
        System.out.println("How many rounds?");
        int rounds = keyboard.nextInt();

        for (int i = 0; i < rounds; i++) {
            /* game logic */
            playRound(keyboard, rand, list, itName, numPlayers, i);
        }
    }

    /* function for the execution of one round */
    private static void playRound(Scanner keyboard, Random rand, DCircLinkList<String> list, String itName[], int numPlayers, int round) {
        /* set the scores. If one of the scores reaches the length of the list, that person wins */
        int itScore = 0;
        int gooseScore = 0;
        boolean flag = true; /* winner is it person */
        System.out.println("Round " + (round + 1));
        System.out.println("Game circle:\t" + list);
        System.out.println("It-Person:\t" + itName[0]);
        /* choosing goose */
        int randNum = rand.nextInt(20) + 1;
        System.out.println("Random number generated is:\t" + randNum);
        for (int j = 0; j < randNum; j++) {
            /* print out everybody that it person goes through */
            System.out.print(list.getFirstElement() + " duck; ");
            list.rotateCCW();
        }
        /* choose the goose person */
        System.out.println(list.getFirstElement() + " GOOSE");
        System.out.println("Up jumps: " + list.getFirstElement());
        DNode<String> goose = list.removeFirstNode();
        System.out.println("Game circle:\t" + list);


        /* loop while goose or it runs through the circle */
        while (true) {
            String itPast = "";
            String goosePast = "";
            int reverse = 0;
            /* get the speeds */
            int speed1 = rand.nextInt(numPlayers);
            int speed2 = rand.nextInt(numPlayers);
            System.out.println("**Speeds: It-Person " + speed1 + ", Goose " + speed2);
            /* go to the point where it person should be */
            for (int k = 0; k < itScore + gooseScore; k++) {
                list.rotateCCW();
            }
            /* it runs through the circle */
            for (int k = 0; k < speed1; k++) {
                itPast += list.getFirstElement() + " ";
                list.rotateCCW();
                itScore++;
                reverse++;
                if (itScore == list.getSize()) {
                    break;
                }
            }
            System.out.println("It-Person running past:\t" + itPast);

            /* go to the position of goose */
            for (int k = 0; k < itScore + gooseScore; k++) {
                list.rotateCW();
            }

            /* goose runs through the circle */
            for (int k = 0; k < speed2; k++) {
                list.rotateCW();
                goosePast += list.getFirstElement() + " ";

                gooseScore++;
                if (gooseScore == list.getSize()) {
                    break;
                }
            }
            System.out.println("Goose running past:\t" + goosePast);

            /* checks if goose or it run through the circle */
            if (itScore == list.getSize()) {
                flag = true;
                break;
            }
            if (gooseScore == list.getSize()) {
                flag = false;
                break;
            }
        }

        /* gecides the winner */
        if (flag) {
            System.out.println("It-person " + itName[0] + " wins");
            list.addNodeAsFirst(new DNode<String>(itName[0])); // Add the current 'It' player back to the list
            itName[0] = goose.getElement(); // Set the new 'It' player
        } else {
            System.out.println("Goose " + goose.getElement() + " wins");
            list.addNodeAsFirst(goose);
        }
        System.out.println("==================================");
    }
}
