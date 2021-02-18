class Person implements Comparable<Person> {
  private String firstName, lastName, address, postalCode, phoneNumber, email;
  
  Person(String firstName, String lastName, String address, String postalCode, String phoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.postalCode = postalCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public int compareTo(Person person) {    
    return lastName.compareTo(person.getLastName());
  }
}