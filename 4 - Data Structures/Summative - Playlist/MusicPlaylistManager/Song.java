/**
 * Song.java
 * Contains the song class.
 * @author Daniel Zhang
 * @since December 3, 2020
 * @version 1.0
 */
class Song { 
  private String title;  // name of song
  private int mins, seconds;  // duration 
  
  /**
   * Song
   * A constructor to create a song object.
   * @param title The title of the song
   * @param durationMins The length of the song in minutes.
   * @param durationSecs The length of the song in seconds.
   */
  Song(String title, int durationMins, int durationSecs) {
    this.title = title;
    this.mins=durationMins;
    this.seconds=durationSecs;
  }
  
  /**
   * getTitle
   * Gets the title of the song.
   * @return the title of the song.
   */
  public String getTitle() { 
    return this.title;
  }
  
  /**
   * getDurationMins
   * Gets the minutes portion of the song length.
   * @return the minutes portion of the song length.
   */
  public int getDurationMins() { 
    return this.mins;
  }
  
  /**
   * getDurationSecs
   * Gets the seconds portion of the song length.
   * @return the seconds portion of the song length.
   */
  public int getDurationSecs() { 
    return this.seconds;
  }
}