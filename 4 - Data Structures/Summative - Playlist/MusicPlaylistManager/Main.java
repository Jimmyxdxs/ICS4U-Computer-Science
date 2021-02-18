import java.util.Scanner;

/**
 * Main.java
 * Contains the playlist and the user input interface.
 * @author Daniel Zhang
 * @since December 3, 2020
 * @version 1.0
 */
class Main {  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    DoublyCircularLinkedList<Song> playList = new DoublyCircularLinkedList<Song>();
    String[] songTime = new String[2];
    String[] userInput; 
    String songTitle, currentSong;
    int minutes, seconds;
    Song song;
    int currentIndex = 0; // Index of current song
    
    do { 
      userInput = input.nextLine().trim().split(" "); // Gets command line from user input
      try {
        switch (userInput[0]) { // Checks the input command
          case "add": {
            songTitle = userInput[3]; // Gets the song title
            songTime = userInput[4].split(":"); // Gets the the song time
            song = new Song(songTitle, Integer.parseInt(songTime[0]), Integer.parseInt(songTime[1])); // Create a new Song
            
            // Adds the new song to the start of the list.
            if ((userInput[1] + userInput[2]).equals("tostart")) { 
              playList.addToStart(song);
              // The index of current song will add one if the song is added to the start of non-empty list.
              if (currentIndex != 0) {
                currentIndex++;
              }
            }
            // Adds the new song to the end of the list.
            else if ((userInput[1] + userInput[2]).equals("toend")) {
              playList.addToEnd(song);
            }
            // Adds the new song after the given index in the list.
            else if (userInput[1].equals("after")) {
              playList.insert(song, Integer.parseInt(userInput[2]));
            // The index of current song will add one if the new song is added ahead of it.
              if (Integer.parseInt(userInput[2]) < currentIndex) {
                currentIndex++;
              }
            } else { 
              System.out.println("Invalid input!"); // Print an error message if the command is invalid.
            }
            break;
          }
          case "play": {
            // If command is Play Index, sets index of current song to the given number
            if (userInput.length == 2) {
              currentIndex = Integer.parseInt(userInput[1]);
            }
            // Plays the current song
            currentSong = playList.get(currentIndex).getTitle();
            System.out.println("playing " + currentSong);
            // Gets the index of the next song
            currentIndex = (currentIndex + 1) % playList.size();
            break;
          }
          case "remove": {
            // Removes the song from the list with the given index
            playList.delete(Integer.parseInt(userInput[1]));
            // The index of current song will be offset by 1 if the removed song is ahead of it.
            if (Integer.parseInt(userInput[1]) <= currentIndex) {
              currentIndex--;
            }
            break;
          }
          case "get": {
            // Calculates the total playlist length in minutes and seconds. 
            if ((userInput[1] + userInput[2]).equals("playlistlength")) {
              minutes = 0;
              seconds = 0;
              for (int i = 0; i < playList.size(); i++) {
                minutes += playList.get(i).getDurationMins();
                seconds += playList.get(i).getDurationSecs();
              }
              minutes += seconds / 60;
              seconds = seconds % 60;
              System.out.println(minutes + ":" + seconds);
            }
            break;
          }
          case "display": {
            // Displays all the songs in the linked list.
            for (int i = 0; i < playList.size(); i++) {
              System.out.println(playList.get(i).getTitle());
            }
            break;
          }
          case "quit": {
            break;
          }
          default: {
            System.out.println("Invalid entry!");
          }
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Invalid entry!");
      } catch (NumberFormatException e) {
        System.out.println("Invalid index number!");
      }
    } while (!userInput[0].equals("quit")); // Do-while loop runs while the user does not input "quit"
  }
}