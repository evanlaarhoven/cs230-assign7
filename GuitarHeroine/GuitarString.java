/*
 * GuitarString.java
 * Emily Van Laarhoven & Hanae Yaskawa
 * CS230 Assignment 7
 * Task 2b
 */

import javafoundations.*;

public class GuitarString {
  
 // instance variables 
  private BoundedQueue<Double> queue;
  private double frequency;
  private int capacity;
  private final int samplingRate = 44100;
  private final double energyDecay = 0.994;
  
  // constructor
  public GuitarString(double freq) {
    // calculates maxCapacity by dividing samplingRate by frequency
    // and rounding up to nearest integer
    frequency = freq;
    capacity = (int)Math.ceil((int)samplingRate/frequency); //sampling rate divided by frequency rounded up to the nearest int
    queue = new BoundedQueue<Double>(capacity);
    for (int i = 0; i < capacity; i++) {
      queue.enqueue(0.0); //fills up the queue to capacity with zeroes
    }
  }
  
  // method to replaces N samples in bounded queue with N
  // random values between -0.5 and 0.5
  public void pluck() {
    for (int i = 0; i < capacity; i++) {
      double value = Math.random()-0.5; //generate random values between -0.5 and 0.5
      queue.dequeue(); //dequeue a zero from the front
      queue.enqueue(value); //enqueue a random value to the back
    }
  }
  
  // method returns value of the item at the front
  // of the bounded queue
  public double sample() {
    return queue.first();
  }
  
  // method deletes sample at front of bounded queue and 
  // adds to the end average of the deleted sample and sample
  // at front of the bounded queue, multiplied by the energy decay
  // of factor 0.994
  public void tic() {
    double front = queue.dequeue();
    double average = (queue.first() + front)*0.5*energyDecay;
    queue.enqueue(average);
  }
  
  public String toString() {
    return queue.toString();
  }
  
  // main method to test
  public static void main (String[] args) {
    
    GuitarString testGuitar = new GuitarString(440); //440 Hz = middle c
    testGuitar.pluck();
    System.out.println("Plucked once: " + testGuitar.toString());
    testGuitar.tic();
    System.out.println("Ticked once: " + testGuitar.toString());
    
  }
}