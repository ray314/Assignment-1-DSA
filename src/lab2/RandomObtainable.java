/**
 * 
 */
package lab2;

import java.util.NoSuchElementException;

/**
 * @author Justin Yeung
 *
 */
public interface RandomObtainable<E> {
	public E getRandom() throws NoSuchElementException;
	public boolean removeRandom() throws UnsupportedOperationException;
}
