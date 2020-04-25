import java.util.Iterator;

public class MyTreeSetTester{
  public static void main(String[] args){
    MyTreeSet set = new MyTreeSet();
    set.add("hello");
    set.add("world");
    set.add("hello");
    set.add("world");
    set.add("!");

    Iterator<String> keys = set.keys();
    while(keys.hasNext()){
      System.out.println(keys.next());
    }
  }
}
