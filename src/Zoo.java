import java.util.Scanner;

public class Zoo {

    public static void main(String[] args) {


        // Init scanner obj
        Scanner scanner = new Scanner(System.in);

        // App lifecycle
        boolean endLoop = false;
        while (!endLoop) {
            try {
                // Display Menu
                displayUserMenu();

                // Holding the user selection
                int userSelection = Integer.parseInt(scanner.next());

                // TODO - catch out of bounds user selection

                // Menu options
                switch (userSelection) {
                    case 0:
                        System.out.println("Program closing...");
                        endLoop = true;
                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid selection");
            }
        }
    }

    public static void displayUserMenu() {
        String headerString = String.format("%20s", "Zoo UI");
        System.out.println("\n+" + "-".repeat(35) + "+");
        System.out.println(headerString);
        System.out.println("+" + "-".repeat(35) + "+");

        System.out.println("1) WIP");
        System.out.println("2) WIP");
        System.out.println("3) WIP");
        System.out.println("4) WIP");
        System.out.println("0) Quit");

        System.out.println("\nMake your selection:");
    }
}
