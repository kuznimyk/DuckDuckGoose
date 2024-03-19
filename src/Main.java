import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many players?");
        int numPlayers = keyboard.nextInt();
        DCircLinkList<String> list = new DCircLinkList<>();
        Random rand = new Random();

        // Input player names
        inputPlayerNames(keyboard, numPlayers, list);

        // Determine 'It' player
        String[] itName = new String[1];
        itName[0] = chooseItPlayer(keyboard, list, numPlayers);

        // Start the game
        startGame(keyboard, rand, list, itName, numPlayers);
    }

    // Function to input player names
    private static void inputPlayerNames(Scanner keyboard, int numPlayers, DCircLinkList<String> list) {
        for (int i = 0; i < numPlayers - 1; i++) {
            System.out.println("Please enter Player's " + (i + 1) + "'s name");
            String name = keyboard.next();
            list.addLast(name);
        }
    }

    // Function to choose 'It' player
    private static String chooseItPlayer(Scanner keyboard, DCircLinkList<String> list, int numPlayers) {
        System.out.println("Please enter Player's " + numPlayers + "'s name");
        return keyboard.next();
    }

    // Function to start the game
    private static void startGame(Scanner keyboard, Random rand, DCircLinkList<String> list, String itName[], int numPlayers) {
        System.out.println("How many rounds?");
        int rounds = keyboard.nextInt();

        for (int i = 0; i < rounds; i++) {
            // Game logic for each round
            playRound(keyboard, rand, list, itName, numPlayers, i);
        }
    }

    // Function to play a single round
    private static void playRound(Scanner keyboard, Random rand, DCircLinkList<String> list, String itName[], int numPlayers, int round) {
        int itScore = 0;
        int gooseScore = 0;
        boolean flag = true; // winner is it-person
        System.out.println("Round " + (round + 1));
        System.out.println("Game circle:\t" + list);
        System.out.println("It-Person:\t" + itName[0]);
        int randNum = rand.nextInt(20) + 1;
        System.out.println("Random number generated is:\t" + randNum);
        for (int j = 0; j < randNum; j++) {
            System.out.print(list.getFirstElement() + " duck; ");
            list.rotateCCW();
        }
        System.out.println(list.getFirstElement() + " GOOSE");
        System.out.println("Up jumps: " + list.getFirstElement());
        DNode<String> goose = list.removeFirstNode();
        System.out.println("Game circle:\t" + list);

        while (true) {
            String itPast = "";
            String goosePast = "";
            int reverse = 0;
            int speed1 = rand.nextInt(numPlayers);
            int speed2 = rand.nextInt(numPlayers);
            System.out.println("**Speeds: It-Person " + speed1 + ", Goose " + speed2);
            for (int k = 0; k < itScore + gooseScore; k++) {
                list.rotateCCW();
            }
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
            for (int k = 0; k < itScore + gooseScore; k++) {
                list.rotateCW();
            }
            for (int k = 0; k < speed2; k++) {
                list.rotateCW();
                goosePast += list.getFirstElement() + " ";

                gooseScore++;
                if (gooseScore == list.getSize()) {
                    break;
                }
            }
            System.out.println("Goose running past:\t" + goosePast);

            if (itScore == list.getSize()) {
                flag = true;
                break;
            }
            if (gooseScore == list.getSize()) {
                flag = false;
                break;
            }
        }
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
