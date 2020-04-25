import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;

public class TreeMapTester{
  public static void main(String[] args){
    TreeMap<String, LinkedList<Integer>> map
      = new TreeMap<String, LinkedList<Integer>>();
    store(map, "hello", 1);
    store(map, "world", 2);
    store(map, "hello", 3);
    store(map, "world", 4);
    store(map, "!", 5);

    Set<String> set = map.keySet();
    Iterator<String> keys = set.iterator();
    while(keys.hasNext()){
      String key = keys.next();
      System.out.println(key+": "+map.get(key));
    }
  }

  private static void store(TreeMap<String, LinkedList<Integer>> map,
    String key, Integer value){
    LinkedList<Integer> list = map.get(key);
    if (list == null){
      list = new LinkedList<Integer>();
    }
    list.add(value);
    map.put(key, list);
  }
}
