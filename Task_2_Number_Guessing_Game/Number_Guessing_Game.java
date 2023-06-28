import java.util.Random;
import java.util.Scanner;
public class Number_Guessing_Game {
    public static void main(String[] args) {
        Random Random_number = new Random();
        int right_guess = Random_number.nextInt(1000);
        int turns = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game");
        System.out.println("Guess a number between 1 to 1000, You will have 15 turns!");

        int guess;
        int i = 0;
        boolean win = false;
        while (win == false) {
            System.out.print("Enter Your Guess Number " + (i+1) + " :  ");
            guess = scan.nextInt();
            turns++;

            if (guess == right_guess) {
                win = true;
            } else if (i > 13) {
                System.out.println("Game Over! The Coorect Answer is : " + right_guess);
                return;
            } else if (guess < right_guess) {
                i++;
                System.out.println("Not so Low, Guess a Little Higher ! Turns left: " + (15 - i));


            } else if (guess > right_guess) {
                i++;
                System.out.println("Its too High !! Guess a Little Low ! Turns left: " + (15 - i));

            }


        }
        System.out.println("Congratulations !!");
        System.out.println("Yes , The Number was " + right_guess);
        System.out.println("You guessed the Correct Number in " + turns +" turns." );

    }
}