import java.util.*;
import java.io.*;
public class WebPageIndex {
  private MyTreeMap map = new MyTreeMap();
  private MyTreeSet set = new MyTreeSet();
  private int count = 0;
  private String URL;

  public WebPageIndex(String url){
    this.URL = url;
    try {
      HTMLScanner scanner = new HTMLScanner(URL);
      while(scanner.hasNext()) {
        String token = scanner.next();
        // turn words into lowercase
        map.put(token.toLowerCase(), count);
        count++;
      }
      while (scanner.hasNextLink()) {
        set.add(scanner.nextLink());
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args){
    // TO BE IMPLEMENTED
    String fileName = "testscannerfile";
    WebPageIndex index = new WebPageIndex(fileName);
    Iterator<String> keys = index.map.keys();
    while(keys.hasNext()){
      String word = keys.next();
      LinkedList<Integer> location = index.map.get(word);
      System.out.println(word+"\t"+index.getFrequency(word)+"\t"+location);
    }
    System.out.println("====================");

    // test getWordCount()
    System.out.println("expect:\t9");
    System.out.println("got:\t"+index.getWordCount());

    // test contains()
    System.out.println("expect:\ttrue");
    System.out.println("got:\t"+index.contains("is"));

    // test getCount()
    System.out.println("expect:\t3");
    System.out.println("got:\t"+index.getCount("is"));

    // test getLocations()
    System.out.println("expect:\t[6, 9]");
    System.out.println("got:\t"+index.getLocations("it"));

    //test words()
    System.out.println("expect:\t[happening,hi,if,impportant,is,it,tagged,there,what]");
    String result = "";
    Iterator<String> it = index.words();
    while(it.hasNext()){
      result += it.next()+",";
    }
    System.out.println("got:\t[" + result + "]");

    //test containsPhrase
    /*System.out.println("expect:\ttrue");
    System.out.println("got:\t"+index.containsPhrase("it is"));
    System.out.println("expect:\tfalse");
    System.out.println("got:\t"+index.containsPhrase("is important"));*/
    }

    public int getWordCount() {
      // TO BE IMPLEMENTED
      Iterator<String> it = map.keys();
      int size = 0;
      while(it.hasNext()){
        it.next();
        size++;
      }
      return size;
    }
    
    public String getUrl() {
      // TO BE IMPLEMENTED
      return URL;
    }
    
    public boolean contains(String s) {
      // TO BE IMPLEMENTED
      Object result = map.get(s);
      return result != null;
    }
    
    public int getCount(String s) {
      // TO BE IMPLEMENTED
      LinkedList<Integer> locations = map.get(s);
      return locations.size();
    }
    
    public double getFrequency(String s) {
      // TO BE IMPLEMENTED
      return getCount(s)*1.0/count;
    }

    public List<Integer> getLocations(String s) {
      // TO BE IMPLEMENTED
      return map.get(s);
    }
    
    public Iterator<String> words() {
      // TO BE IMPLEMENTED
      return map.keys();
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

