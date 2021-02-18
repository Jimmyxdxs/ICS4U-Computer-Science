class VerticalLineException extends Exception {
  private String message;
  
  VerticalLineException(String message) {
    this.message = message;
  }
  
  public String getMessage() {
    return message;
  }
}