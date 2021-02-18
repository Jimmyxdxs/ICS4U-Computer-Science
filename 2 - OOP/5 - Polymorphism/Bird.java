class Bird extends Animal {
  
  Bird(String name) {
    this.name = name;
  }
  
  public void makeNoise() {
    System.out.println("Chirp!");
  }
  
  public void flapWings() {
    System.out.println("Flap flap flap");
  }
}