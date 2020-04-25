import java.util.LinkedList;
import java.util.Iterator;
import tree.LinkedBinarySearchTree;

public class MyTreeMap{
  LinkedBinarySearchTree<Element> tree =
    new LinkedBinarySearchTree<Element>();

  // find the LinkedList associated with a word
  public LinkedList<Integer> get(String word) {
    // TO BE IMPLEMENTED
    // find the element in the tree with the "word" as the key
    // if the element is found, return the value (LinkedList) associated
    // with the key
    // otherwise, if the element is not found return null
    return null;
  }

  // insert (word, locations) pair to AVL tree
  public void put(String word, Integer location){
    // TO BE IMPLEMENTED
    // get the old list associated with "word" in the tree
    // if old list is null (doesn't exist), create a new one
    // append new "location" to the list
    // add (word, list) pair to the tree
    return;
  }

  /**
   * Create an inorder iterator of the Keys in the tree.
   * @return inorder iterator of the Keys
   */
  public Iterator<String> keys(){
    LinkedList<String> keys = new LinkedList<String>();
    Iterator<Object> it = tree.inOrderIterator();
    while(it.hasNext()){
      Element e = (Element)it.next();
      keys.add(e.getKey());
    }
    return keys.iterator();
  }

  public String toString(){
    String result = "";
    Iterator<Object> it = tree.inOrderIterator();
    while(it.hasNext()){
      Element e = (Element)it.next();
      result += e+"\n";
    }
    return result;
  }
}
