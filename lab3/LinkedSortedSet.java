/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author fbb3628
 * @param <E>
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E> {

    public LinkedSortedSet()
    {
        super();
    }
    
    public LinkedSortedSet(Collection<? extends E> c)
    {
        super(c);
    }
    
    //Node<E> m = new Node target
    // if (firstnode == null || target.compareTo
    
    @Override
    public boolean add(E o)
    {
        Node<E> newNode = new Node<E>(o);
        Node<E> currentNode = firstNode;
        
        if (!contains(o))
        {
            // if new element is less than first element
            if (firstNode == null || newNode.element.compareTo(firstNode.element) < 0)
            {
                newNode.next = firstNode; // Next new node
                firstNode = newNode;
                numElements++;
                return true;
            }
            // if new element is greater than first element
            else
            {
                
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args)
    {
        LinkedSortedSet<Integer> list = new LinkedSortedSet<Integer>();
        //LinkedSet<Integer> list = new LinkedSet<Integer>();
        
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(6);
        
        //System.out.println(list.size());
        Iterator<Integer> it = list.iterator();
        
        while (it.hasNext())
        {
            System.out.print(it.next() + ", ");
        }
    }
    
}


