import java.util.Iterator;

public class MyTreeMapTester{
  public static void main(String[] args){
    MyTreeMap map = new MyTreeMap();
    map.put("hello", 1);
    map.put("world", 2);
    map.put("hello", 3);
    map.put("world", 4);
    map.put("!", 5);

    Iterator<String> keys = map.keys();
    while(keys.hasNext()){
      String key = keys.next();
      System.out.println(key+": "+map.get(key));
    }
    //System.out.println(map);
  }
}