/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import linkedRRSet.LinkedRRSet;

/**
 *
 * @author fbb3628
 */
public class TimeTest {
    
    public static void main(String[] args)
    {
        //Random rand = new Random();
        
        LinkedRRSet<Integer> set = new LinkedRRSet<Integer>();
        int size = 100000;
        
        for (int i = 7; i >= 1; i--)
		{
			set.add(i);
		}

        long startTime = System.nanoTime();
        System.out.println("Retain:");
        // Code being measured here starts
        for (int i = 0; i < size; i++)
        {
           set.retain(2,6); 
           set.add(1);
           set.add(6);
           set.add(7);
        }
        
        // Code being measured ends
        
        long endTime = System.nanoTime();
        
        long timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        
        startTime = System.nanoTime();
        
        System.out.println("Remove:");
        // Code being measured here starts
        for (int i = 0; i < size; i++)
        {
           set.remove(2,6); 
           set.add(2);
           set.add(3);
           set.add(4);
           set.add(5);
        }
        
        // Code being measured ends
        
        endTime = System.nanoTime();
        
        timeElapsed = endTime - startTime;
        
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        
        /*System.out.println("Contains:");
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
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);*/
    }
}
