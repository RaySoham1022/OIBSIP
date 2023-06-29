import java.util.Random;
import java.util.Scanner;
public class Number_Guessing_Game {
    public static void main(String[] args) {
        Random Random_number = new Random();
        int correct_guess = Random_number.nextInt(1000);
        int turns = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game");
        System.out.println("Guess a number between 1 to 1000, You will have 15 turns!");

        int assumption;
        int i = 0;
        boolean win = false;
        while (win == false) {
            System.out.print("Enter Your Guess Number " + (i+1) + " :  ");
            assumption = scan.nextInt();
            turns++;

            if (assumption == correct_guess) {
                win = true;
            } else if (i > 13) {
                System.out.println("Game Over! The Coorect Answer is : " + correct_guess);
                return;
            } else if (assumption < correct_guess) {
                i++;
                System.out.println("Not so Low, Assumption a Little Higher ! Turns left: " + (15 - i));

            } else if (assumption > correct_guess) {
                i++;
                System.out.println("Its too High !! Assumption a Little Low ! Turns left: " + (15 - i));

            }

        }
        System.out.println("Congratulations !!");
        System.out.println("Yes , The Number was " + correct_guess);
        System.out.println("You guessed the Correct Number in " + turns +" turns." );

    }
}

