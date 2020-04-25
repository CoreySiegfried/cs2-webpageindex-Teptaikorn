import java.util.LinkedList;

public class Element implements Comparable{
  private String key;
  private LinkedList<Integer> value;

  public Element(String key, LinkedList<Integer> value){
    this.key = key;
    this.value = value;
  }

  public int compareTo(Object obj){
    Element e = (Element) obj;
    return key.compareTo(e.key);
  }

  public String getKey(){
    return key;
  }
  
  public LinkedList<Integer> getValue(){
    return value;
  }

  public String toString(){
    return ""+key+"->"+value;
  }
}