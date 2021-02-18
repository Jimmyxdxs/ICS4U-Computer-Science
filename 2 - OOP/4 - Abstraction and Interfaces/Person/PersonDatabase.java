import java.util.Scanner;

class PersonDatabase {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    int menuInput;
    String fullName, firstName, lastName, address, postalCode, phoneNumber, email, removeName;
    Database databaseArray = new Database();
    
    do {
      System.out.println("Enter an option number:");
      System.out.println("1. Add a Person");
      System.out.println("2. Remove a Person");
      System.out.println("3. List all People"); // Sort by last name
      System.out.println("4. Quit");
      
      do {
        menuInput = in.nextInt();
      } while (menuInput < 1 || menuInput > 4);
      in.nextLine();
     
      if (menuInput == 1) {
        if (databaseArray.getIndex() >= 10) {
          System.out.println("\nCannot add another person!");
        }else{
          System.out.print("Enter your full name: ");
          fullName = in.nextLine();
          
          if (fullName.contains(" ")) {
            firstName = fullName.substring(0, fullName.indexOf(" "));
            lastName = fullName.substring(fullName.indexOf(" ") + 1);
          }else{
            firstName = fullName;
            lastName = " ";
          }
            
          System.out.print("Enter an address: ");
          address = in.nextLine();
          System.out.print("Enter a postal code: ");
          postalCode = in.nextLine();
          System.out.print("Enter a phone number: ");
          phoneNumber = in.nextLine();
          System.out.print("Enter an email: ");
          email = in.nextLine();
          
          databaseArray.addPerson(firstName, lastName, address, postalCode, phoneNumber, email);
        }
      }

      else if (menuInput == 2) {
        System.out.print("Enter the full name of the person to be removed: ");
        removeName = in.nextLine();
        
        databaseArray.removePerson(removeName);
      }
      
      else if (menuInput == 3) {
        databaseArray.listPeople();
      }
      System.out.println();
    } while (menuInput != 4);
  }
}