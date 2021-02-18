import java.util.Scanner;

public class BoardingHouse {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    Animal[] animalArray = new Animal[10];
    Animal animalType = new Animal();
    String animalInput;
    String checkInput;
    String nameInput;
    int indexCounter = 0;
    boolean petFound = false;
    
    do {
      do {
        System.out.print("Would you like to board a pet (Yes / No)?");
        checkInput = in.nextLine();
      } while ((!checkInput.equalsIgnoreCase("yes")) && (!checkInput.equalsIgnoreCase("no")));
      
      if (checkInput.equalsIgnoreCase("yes")) {
        do {
          System.out.print("What pet would you like to board (Cat, Dog, Bird)?");
          animalInput = in.nextLine();
        } while ((!animalInput.equalsIgnoreCase("cat")) && (!animalInput.equalsIgnoreCase("dog")) && (!animalInput.equalsIgnoreCase("bird")));
        
        System.out.print("What is your pet's name?");
        nameInput = in.nextLine();
        
        if (animalInput.equalsIgnoreCase("cat")) {
          animalArray[indexCounter] = new Cat(nameInput);
          indexCounter++;
        }else if (animalInput.equalsIgnoreCase("dog")) {
          animalArray[indexCounter] = new Dog(nameInput);
          indexCounter++;
        }else if (animalInput.equalsIgnoreCase("bird")) {
          animalArray[indexCounter] = new Bird(nameInput);
          indexCounter++;
        }
      }
      
      do {
        System.out.print("Would you like to pick up a pet (Yes / No)?");
        checkInput = in.nextLine(); 
      } while ((!checkInput.equalsIgnoreCase("yes")) && (!checkInput.equalsIgnoreCase("no")));
      
      if (checkInput.equalsIgnoreCase("yes")) {
        System.out.print("What is your pet's name?");
        nameInput = in.nextLine();
        for (int i = 0; i < indexCounter; i++) {
          if (nameInput.equalsIgnoreCase(animalArray[i].name)) {
            petFound = true;
            animalArray[i] = animalArray[i + 1];
            animalType = animalArray[i];
          }
        }
        
        if (petFound) {
          System.out.println("Pet picked up!");
          animalType.makeNoise();
          indexCounter--;
          animalArray[indexCounter - 1] = null;
          petFound = false;
        }else{
          System.out.println("Pet cannot be found.");
        }
      }
      
    } while (true);
  }
}