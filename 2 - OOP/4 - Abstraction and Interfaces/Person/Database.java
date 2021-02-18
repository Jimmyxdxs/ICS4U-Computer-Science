import java.util.Arrays;

class Database {
  private Person[] dataArray = new Person[10];
  private int indexCounter = 0;
  
  Database() {}
  
  public int getIndex() {
    return indexCounter;
  }
  
  public void addPerson(String firstName, String lastName, String address, String postalCode, String phoneNumber, String email) {
    System.out.println();
    dataArray[indexCounter] = new Person(firstName, lastName, address, postalCode, phoneNumber, email);
    indexCounter++;
    System.out.println("Person sucessfully added!");
  }
  
  public void removePerson(String removeName) {
    String firstName, lastName;
    boolean shift = false;
    
    if (removeName.contains(" ")) {
      firstName = removeName.substring(0, removeName.indexOf(" "));
      lastName = removeName.substring(removeName.indexOf(" ") + 1);
    }else{
      firstName = removeName;
      lastName = " ";
    }
    
    for (int i = 0; i < indexCounter; i++) {
      if (firstName.equalsIgnoreCase(dataArray[i].getFirstName()) && (lastName.equalsIgnoreCase(dataArray[i].getLastName()))) {
        shift = true;
      }
      if (shift && indexCounter < 10) {
        dataArray[i] = dataArray[i + 1];
      }
    }
    
    if (shift) {
      System.out.println("\nPerson successfully removed!");
      dataArray[indexCounter - 1] = null;   
      indexCounter--;
      shift = false;
    }else{
      System.out.println("\nPerson cannot be found.");
    }
  }
  
  public void listPeople() {
    System.out.println();
    Arrays.sort(dataArray, 0, indexCounter);
    for (int i = 0; i < indexCounter; i++) {
      System.out.println(dataArray[i].getFirstName() + " " + dataArray[i].getLastName());
    }
  }
}