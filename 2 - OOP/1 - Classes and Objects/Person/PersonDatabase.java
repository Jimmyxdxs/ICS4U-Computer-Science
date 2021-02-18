import java.util.Scanner;

class PersonDatabase {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    int menuInput;
    String firstName, lastName, address, postalCode, phoneNumber, email;
    boolean shift = false;
    int indexCounter = 0;
    
    String[] removeName;
    Person person = new Person();
    Person[] databaseArray = new Person[10];
    
    do {
      System.out.println("Enter an option number:");
      System.out.println("1. Add a Person");
      System.out.println("2. Remove a Person");
      System.out.println("3. List all People");
      System.out.println("4. Quit");
      
      do {
        menuInput = in.nextInt();
      } while (menuInput < 1 || menuInput > 4);
      in.nextLine();
     
      if (menuInput == 1) {
        if (indexCounter == 10) {
          System.out.println("\nCannot add another person!\n");
        }else{
          System.out.print("Enter your first name: ");
          firstName = in.nextLine();
          System.out.print("Enter your last name: ");
          lastName = in.nextLine();
          System.out.print("Enter an address: ");
          address = in.nextLine();
          System.out.print("Enter a postal code: ");
          postalCode = in.nextLine();
          System.out.print("Enter a phone number: ");
          phoneNumber = in.nextLine();
          System.out.print("Enter an email: ");
          email = in.nextLine();
          
          person = new Person(firstName, lastName, address, postalCode, phoneNumber, email);
          databaseArray[indexCounter] = person;
          indexCounter++;
        }
      }

      else if (menuInput == 2) {
        System.out.print("Enter the full name of the person to be removed: ");
        removeName = in.nextLine().split(" ");
        for (int i = 0; i < indexCounter; i++) {
          if (removeName[0].equalsIgnoreCase(databaseArray[i].firstName) && (removeName[1].equalsIgnoreCase(databaseArray[i].lastName))) {
            shift = true;
          }
          
          if (shift) {
            databaseArray[i] = databaseArray[i + 1];
          }
        }
        
        if (shift) {
          System.out.println("\nPerson succesfully removed!\n");
          databaseArray[indexCounter - 1] = null;   
          indexCounter--;
          shift = false;
        }else{
          System.out.println("\nPerson cannot be found.\n");
        }
      }
      
      else if (menuInput == 3) {
        System.out.println();
        for (int i = 0; i < indexCounter; i++) {
          if (databaseArray[i] != null) {
            System.out.println(databaseArray[i].firstName + " " + databaseArray[i].lastName);
          }
        }
        System.out.println();
      }
    } while (menuInput != 4);
  }
}