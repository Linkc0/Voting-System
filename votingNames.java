import java.util.Scanner;

// Record holding information for names
class Names {
    final int MAX_NAMES = 10;
    String[] name = new String[MAX_NAMES];
    int nameCounter = 0;

}//END class Names

class votingNames {

    // Main method where other methods will be called
    public static void main(String[] a) {
        Names newName = new Names();
        String winners;
        String[] voteResults = new String[getMaxNames(newName)];

        addingNameDetails(newName);
        displayingVoters(newName);
        voting(newName, voteResults);
        winners = calcWinner(newName, voteResults);
        displayWinners(winners);

        return;
    }// END main

    // Handles user inputs
    public static String inputs(String message) {
        String userInput;
        Scanner scanner = new Scanner(System.in);
        print(message);
        userInput = scanner.nextLine();
        return userInput;
    }// END inputs

    // Shorter outputting method
    public static void print(String message) {
        System.out.println(message);

        return;
    }// END print

    // Updates the Names record with the entered names at the current name index
    public static void setNames(Names newName, String name) {
        int counter = getNameCounter(newName);
        newName.name[counter] = name;
        return;
    }// END setNames



    public static void setNameCounter(Names newName, int counter) {
        newName.nameCounter = counter;
    } // END setNameCounter


    // Retrieves the name counter
    public static int getNameCounter(Names newName) {
        return newName.nameCounter;
    }// END getNameCounter

    // Retrieves the name at the specific name index
    public static String getNames(Names newName, int nameIndex) {
        return newName.name[nameIndex];
    }// END getNames

    // Retrieves the max number of names that can be stored
    public static int getMaxNames(Names newName) {
        return newName.MAX_NAMES;
    }// END getMaxNames

    // Checks if user input is a String
    public static boolean isStringValue(String input) {
        return (input.matches("[a-zA-Z\\s]+"));
    }// END isStringValue

    // Checks if user input is a valid vote (number between 1 and 10)
    public static boolean isValidVote(String input) {
        return (input.matches("\\d+")) || (Integer.parseInt(input)>=1 && Integer.parseInt(input)<=10);

    }// END isValidVote

    // Asks the user for the 10 names then saves it in record
    public static void askingNames(Names newName, int nameNum) {
        String name = inputs("Name " + (nameNum + 1) + "?");
        int counter = getNameCounter(newName);

        while (!isStringValue(name)) {
            name = inputs("Invalid input. Please only use characters.");
        }
        setNames(newName, name);
        setNameCounter(newName, counter+1);
        return;
    } // END askingNames

    // Allows user to enter names to be used for voting
    public static void addingNameDetails(Names newName) {
        int counter = getNameCounter(newName);
        while (counter < getMaxNames(newName)) {
            askingNames(newName, counter);
        }
        return;
    }// END addingNameDetails


    // Displays the 10 voter's names
    public static void displayingVoters(Names newName) {
        int maxNames = getMaxNames(newName);

        print("Voting: ");
        for (int num = 0; num < maxNames; num++) {
            print((num + 1) + "=" + getNames(newName, num));
        }
        return;
    }// END displayingVoters

    // Allows voters to vote
    public static void voting(Names newName, String[] voteResults) {
        int maxNames = getMaxNames(newName);
        String voteOption;

        for (int num = 0; num < maxNames; num++) {
            voteOption = inputs(getNames(newName, num) + " who do you vote for? ");

            while (!isValidVote(voteOption)) {
                voteOption = inputs("Invalid input. Please only enter a number between 1 and 10.");
            }

            voteResults[num] = voteOption;
        }
        return;
    }// END voting

    // Calculates the winners
    public static String calcWinner(Names newName, String[] voteResults) {
        int[] voteCount = new int[getMaxNames(newName)];
        int maxVotes = 0;
        int voteIndex;
        String winners = "";

        for (int vote = 0; vote < voteResults.length; vote++) {
            if (voteResults[vote] != null) {
                voteIndex = Integer.parseInt(voteResults[vote]) - 1;
                voteCount[voteIndex]++;
                if (voteCount[voteIndex] > maxVotes) {
                    maxVotes = voteCount[voteIndex];
                }
            }
        }

        for (int vote = 0; vote < voteCount.length; vote++) {
            if (voteCount[vote] == maxVotes) {
                if (!winners.isEmpty()) {
                    winners += ", ";
                }
                winners += getNames(newName, vote);
            }
        }//END calcWinner

        return winners;
    }// END calcWinner

    // Displays the winners
    public static void displayWinners(String winners) {
        print("Winner(s): " + winners);
        return;
    }// END displayWinners

}//END class votingNames
