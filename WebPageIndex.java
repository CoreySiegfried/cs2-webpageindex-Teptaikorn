import java.util.*;

public class WebPageIndex {
  private MyTreeMap map = new MyTreeMap();
  private MyTreeSet set = new MyTreeSet();

  public static void main(String[] args){
    // TO BE IMPLEMENTED
  }

  public int getWordCount() {
    // TO BE IMPLEMENTED
    return -1;
  }
  
  public String getUrl() {
    // TO BE IMPLEMENTED
    return "TODO";
  }
  
  public boolean contains(String s) {
    // TO BE IMPLEMENTED
    return false;
  }
  
  public int getCount(String s) {
    // TO BE IMPLEMENTED
    return -1;
  }
  
  public double getFrequency(String s) {
    // TO BE IMPLEMENTED
    return -1.0;
  }

  public List<Integer> getLocations(String s) {
    // TO BE IMPLEMENTED
    return null;
  }
  
  public Iterator<String> words() {
    // TO BE IMPLEMENTED
    return null;
  }
  
  public String toString() {
    // TO BE IMPLEMENTED
    return "TODO";
  }
  
  /* 
   * additional features you might add to support multi-word phrases 
   * work on these after you have the previous methods working correctly
   */

  public boolean containsPhrase(String s) {
    // TO BE IMPLEMENTED
    return false;
  }
  
  public int getPhraseCount(String s) {
    // TO BE IMPLEMENTED
    return -1;
  }
  
  public double getPhraseFrequency(String s) {
    // TO BE IMPLEMENTED
    return -1.0;
  }

  public List<Integer> getPhraseLocations(String s) {
    // TO BE IMPLEMENTED
    return null;
  }
}

