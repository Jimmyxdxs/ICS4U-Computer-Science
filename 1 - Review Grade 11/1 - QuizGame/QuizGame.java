/** 
 * QuizGame.java
 * @version 1.0 
 * Sept 15, 2020
 * @author Daniel Zhang
 * This program generates three multiple-choice questions in a random order, and it displays the user's score after.*/
import java.util.Scanner;

public class QuizGame {
  
  /** Shuffles shuffledArray {0, 1, 2} to randomize the question order.
   * @return shuffledArray, A shuffled integer array containing elements 0, 1, and 2. */
  public static int[] generateRandomOrder() {
    int[] shuffledArray = new int[] {0, 1, 2}; 
    int temporaryInteger;
    int randomIndex; 
    
//    // Generates the random index to switch with the first element of the array.
//    randomIndex = (int) (Math.random()*3);
//    temporaryInteger = shuffledArray[0];
//    shuffledArray[0] = shuffledArray[randomIndex];
//    shuffledArray[randomIndex] = temporaryInteger;
//    
//    // Generates the random index to switch with the second element of the array.
//    randomIndex = (int) (Math.random()*2+1);
//    temporaryInteger = shuffledArray[1];
//    shuffledArray[1] = shuffledArray[randomIndex];
//    shuffledArray[randomIndex] = temporaryInteger;
    
    /* Loop from the first to the last element of the array, use 
     * randomly-generated index to switch with the current element. 
     * Therefore, the original array will be shuffled to a random order. */
    for (int i = 2; i >= 0; i--) {
      randomIndex = (int) (Math.random()*(i+1) + (2-i));
      temporaryInteger = shuffledArray[2-i];
      shuffledArray[2-i] = shuffledArray[randomIndex];
      shuffledArray[randomIndex] = temporaryInteger;
    }  
    return shuffledArray;
  }
    
  // Beginning of main method.
  public static void main(String[] args) {
    final int TOTAL_QUESTIONS = 3;
    
    // The questionArray stores the three multiple-choice questions.
    String[] questionArray = new String[] {"Which one of the following is not a programming language?", 
      "In what year did the Java programming language first release?", 
      "What is printed by the following statement? System.out.println(\"Hello,\\nworld!\")"};
    
    // These three arrays store the answers to the three multiple-choice questions.
    String[] questionOneAnswerArray = new String[] {"a) Java", "b) Python", "c) C++", "d) Eclipse"};
    String[] questionTwoAnswerArray = new String[] {"a) 1950", "b) 1996", "c) 2000", "d) 2010"};
    String[] questionThreeAnswerArray = new String[] {"a) Hello,\n   world!", "b) \"Hello,\\nworld!\"", "c) Hello,\\nworld!", "d) Hello,world!"};
  
    String[] answerArray = new String[4];
    char[] correctAnswer = new char[] {'d', 'b', 'a'}; // The correctAnswer array stores the correct answers.
    
    int scoreCounter = 0;
    int[] questionsOrderArray;
    char inputAnswer;
    char tryAgainAnswer = 'y';
    String userName;
    
    Scanner in = new Scanner (System.in);
    System.out.print("Please enter your name: ");
    userName = in.nextLine();
    do {
      questionsOrderArray = generateRandomOrder(); // Creates a random order for the questions.
      
      for (int i = 0; i < TOTAL_QUESTIONS; i++) {
        System.out.println((i + 1) + ". " + questionArray[questionsOrderArray[i]]); // Displays the questions based on the random array generated.
        // Finds the corresponding answers to the current question.
        switch (questionsOrderArray[i]) {
          case 0:
            answerArray = questionOneAnswerArray;
            break;
          case 1:
            answerArray = questionTwoAnswerArray;
            break;
          case 2:
            answerArray = questionThreeAnswerArray;
        }
        
        // Displays the corresponding answers.
        for (int j = 0; j < answerArray.length; j++) {
          System.out.println(answerArray[j]);
        }
        
        inputAnswer = in.next().toLowerCase().charAt(0); 
        
        // Checks if the inputAnswer is the correct answer.
        if (inputAnswer == correctAnswer[questionsOrderArray[i]]) {
          System.out.println("Correct!");
          scoreCounter++;
        }else{
          System.out.println("Incorrect. The correct answer is " + correctAnswer[questionsOrderArray[i]] + ".");
        }
      }
      System.out.println(userName + ", your score was " + scoreCounter + "/3.");
      System.out.println("Do you want to try again (y/n)? ");
      tryAgainAnswer = in.next().toLowerCase().charAt(0); 
      scoreCounter = 0;
    } while (tryAgainAnswer == 'y');
  } // End of main method. 
} // End of QuizGame class.
    
    