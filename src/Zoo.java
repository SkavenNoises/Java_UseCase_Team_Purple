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

                    case 1: // TODO list all animals - sinduri

                        break;

                    case 2: // TODO Add an animal - sinduri

                        break;

                    case 3: // TODO Zoo employee maintaining the habit - sinduri

                        break;

                    case 4: // TODO Which species is eating the most food - sinduri

                        break;

                    case 5: // TODO which species is using the most medication -sinduri

                        break;

                    case 6: // TODO send animal to vet -kieran

                        break;

                    case 7: // TODO list all sick animals - zumrut

                        break;

                    case 8: // TODO List all vet clinic history -zumrut

                        break;

                    case 9: // TODO How many animals are in special care -zumrut

                        break;

                    case 10: // TODO How many times the vet has been called per species -zumrut

                        break;

                    case 11: // TODO list resources -kieran

                        break;

                    case 12: // TODO PrintReport -kieran

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

        System.out.println(" 1) List all animals");
        System.out.println(" 2) Add an animal");
        System.out.println(" 3) Enclosure maintenance");
        System.out.println(" 4) List species consuming the most food");
        System.out.println(" 5) List species consuming the most meds");
        System.out.println(" 6) Send animal to vet clinic");
        System.out.println(" 7) List all sick animals");
        System.out.println(" 8) List vet clinic history");
        System.out.println(" 9) List animals in special care");
        System.out.println("10) List vet call outs");
        System.out.println("11) List resources");
        System.out.println("12) Print report");
        System.out.println(" 0) Quit");

        System.out.println("\nMake your selection:");
    }
}
