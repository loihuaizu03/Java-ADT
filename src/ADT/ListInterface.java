
package ADT;

import Entity.Course;
import java.util.function.Predicate;

/**
 *
 * @author Chow Xing Shao1
 */
public interface ListInterface<T> {
    
    /**
     * Adds a new entry to the end of the list.
     * The list's size is increased by 1.
     * 
     * @param newEntry The object to be added 
     */
    boolean add(T newEntry);
    
    /**
     * Adds newEntry at new position within the list.
     * The list's size is increased by 1.
     * Position 1 indicates the first entry in the list. 
     * 
     * @param newPosition The position that an object should be added to
     * @param newEntry The object to be added 
     * 
     * @return true if newEntry was successfully added to the list; false otherwise.
     */
    boolean add(int newPosition, T newEntry);
    
    /**
     * Task: Removes the entry at a given position from the list. Entries
     * originally at positions higher than the given position are at the next
     * lower position within the list, and the list's size is decreased by 1.
     *
     * @param givenPosition an integer that indicates the position of the entry to be removed
     * 
     * @return a reference to the removed entry or null, if either the list was
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    T remove(int givenPosition);
    
    /**
     * Removes the entry at position within the list.
     * The list's size is decreased by 1.
     * Position 1 indicates the first entry in the list. 
     * 
     * @param entry The entry that is going to remove
     * 
     * @return a reference to the removed entry or null, if either the list was
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    T remove(T entry);
    
    /**
     * Removes all entries from the list.
     * The list is now empty.
     * 
     */
    void clear();
    
    /**
     * Replaces the entry at position with newEntry.Position 1 indicates the first entry in the list. 
     * 
     * @param givenPosition The position that an object is replace
     * @param newEntry The new entry that is going to replace
     * 
     * @return true if entry had been successfully replaced, false otherwise. 
     * 
     */
    boolean replace(int givenPosition, T newEntry);
    
    /**
     * Retrieves the entry at position givenPosition in the list.
     * 
     * @param position The position that an object is retrieved
     * 
     * @return The entry at position givenPosition.
     */
    T getEntry(int position);
    
    /**
     * Determines whether the list contains anEntry.
     * 
     * @param entry The entry that needs to check
     * 
     * @return entry that is found
     */
     T contains(T entry);
    
    /**
     * Gets the number of entries currently in the list.
     * 
     * @return The number of entries currently in the list.
     */
    int size();
    
    /**
     * Determines whether the list is empty.
     * 
     * @return true if the list is empty, or false if not.
     */
    boolean isEmpty();
    
    /**
     * Task: Sees whether the list is full.
     *
     * @return true if the list is full, or false if not
     */
    public boolean isFull();
    
    /**
     * Task: List out the entries inside the list
     */
    public void list();
    
    //sort
     public void sort();

   
    
  
}
