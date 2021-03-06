/*
 * BoundedQueue.java
 * Emily Van Laarhoven & Hanae Yaskawa
 * CS230 Assignment 7
 * Task 2a
 */

package javafoundations;

public class BoundedQueue<T> extends CircularArrayQueue<T> {
  
  //instance variables
  private int maxCapacity;
  
  //constructor
  public BoundedQueue(int maxCapacity) {
    this.maxCapacity = maxCapacity; 
  }
  
  /*
   * returns true if bounded queue is full
   */
  public boolean isFull() {
    return super.size() == maxCapacity;
  }
  
  /*
   * enqueues an element of type T if BoundedQueue is less than max capacity
   */
  public void enqueue (T elt) {
    if (!isFull()) {
      super.enqueue(elt);
    } 
  }
  
  public static void main (String[] args) {
   
    BoundedQueue<String> testQ = new BoundedQueue<String>(3);
    testQ.enqueue("Hanae");
    testQ.enqueue("Emily");
    System.out.println("TestQ is full? (false): "+testQ.isFull());
    System.out.println("The size of testQ is (2): "+testQ.size());
    System.out.println(testQ);
    
    testQ.enqueue("Hello");
    testQ.enqueue("BLAH"); 
    System.out.println(testQ); //doesn't add Blah bc max capacity is reached
    System.out.println("TestQ is full? (true): "+testQ.isFull());
    System.out.println("The size of testQ is (3): "+testQ.size());
    
  }
  
}