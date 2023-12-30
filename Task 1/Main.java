import java.util.Scanner;

class RangeGenerator {
    public int generate(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RangeGenerator rg = new RangeGenerator();
        int totalAttempts = 0;
        int win = 0;

        while (true) {
            System.out.println("Enter the Maximum number");
            int max = sc.nextInt();
            System.out.println("Enter the Minimum number");
            int min = sc.nextInt();
            sc.nextLine(); // Fix: consume the newline character

            int cnum = rg.generate(max, min);
            int attempts = 0;

            while (true) {
                System.out.println("Guess a number between " + min + " and " + max);
                int gnum = sc.nextInt();
                attempts++;

                if (gnum > cnum) {
                    System.out.println("It's Greater");
                } else if (gnum < cnum) {
                    System.out.println("It's Lesser");
                } else {
                    System.out.println("Correct Guess");
                    win++;
                    break;
                }
            }
            totalAttempts += attempts;
            System.out.println("Attempts = " + attempts);
            System.out.println("Wins = " + win);

            double winrate = (double) win / totalAttempts * 100;
            System.out.printf("Your winrate is %.2f%%\n", winrate);

            System.out.println("Do you want to play again? (yes/no)");
            String playAgain = sc.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                sc.close();
                System.exit(0);
            }
            sc.nextLine(); // Fix: consume the newline character
        }
    }
}
