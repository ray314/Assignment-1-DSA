/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fbb3628
 */
public class TimeTest {
    
    public static void main(String[] args)
    {
        Random rand = new Random();
        long startTime = System.nanoTime();
        ArraySet<Integer> set = new ArraySet<Integer>();
        int size = 100;
        
        
        System.out.println("Add:");
        // Code being measured here starts
        for (int i = 0; i < size; i++)
        {
           set.add(i); 
        }
        
        // Code being measured ends
        
        long endTime = System.nanoTime();
        
        long timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        
        startTime = System.nanoTime();
        
        // Code being measured here starts
        for (int i = 0; i < size; i++)
        {
           set.remove(i); 
        }
        
        // Code being measured ends
        System.out.println("Remove:");
        
        endTime = System.nanoTime();
        
        timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        
        System.out.println("Contains:");
        startTime = System.nanoTime();
        
        // Code being measured here starts
        for (int i = 0; i < size; i++)
        {
           set.contains(i); 
        }
        
        // Code being measured ends
        
        endTime = System.nanoTime();
        
        timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
