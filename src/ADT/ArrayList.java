package ADT;
import java.io.Serializable;
import java.util.Comparator;
/**
 *
 * @author Chow Xing Shao
 */
public class ArrayList<T> implements ListInterface<T>, Serializable, Comparator<T>{
    private T[] array;
    private int numOfEntries;
    private static final int DEFAULT_CAPACITY = 5;
    
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayList(int initialSize) {
        numOfEntries = 0;
        array = (T[]) new Object[initialSize];      //cast to T
    }
    
    public void doubleArray(){
        T[] arrayTemp = array;
        array = (T[]) new Object[arrayTemp.length * 2];
        
        for(int i=0;i<arrayTemp.length;i++){
            array[i]=arrayTemp[i];
        }
    }
    
    @Override
    public boolean add(T newEntry) {
        if(isFull()){
            doubleArray();
        }
        array[numOfEntries] = newEntry;
        numOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numOfEntries + 1)) {
            if (isFull()) {
                doubleArray();
            }
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }
    
    @Override
    public T remove(int position) {
        T result = null;

        if ((position >= 1) && (position <= numOfEntries)) {
            result = array[position - 1];

            if (position < numOfEntries) {
                removeGap(position);
            }

            numOfEntries--;
        }

        return result;
    }
    
    @Override
    public T remove(T entry) {
        T result = null;
        
        for(int i=0;i<numOfEntries;i++){
            if(entry.equals(array[i])){
                result = array[i];
                if(i < numOfEntries) {
                    removeGap(i);
                }
                numOfEntries--;
            }
        }
        return result;
    }
    
    @Override
    public void clear() {
       for(int i=0;i<numOfEntries;i++){
           array[i] = null;
           numOfEntries--;
       }
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int position) {
        T result = null;

        if ((position >= 1) && (position <= numOfEntries)) {
            result = array[position - 1];
        }

        return result;
    }

    @Override
    public T contains(T entry) {
        T result = null;
        
        for(int i=0;i<numOfEntries;i++){
            if(array[i].equals(entry)){
                result = array[i];
            }
        }
        return result;
    }
    
    @Override
    public int size() {
        return numOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }
    
    @Override
    public boolean isFull() {
        return numOfEntries == array.length;
    }
    
    @Override
    public void list(){
        
        for(int i=0;i<numOfEntries;i++){
            if(array[i] != null){
                System.out.printf("%2s.%s\n", (i+1), array[i]);
            }
        }
    }
    
    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next lower position. 
     * Precondition: array is not empty; 
     * 1 <= givenPosition < numOfEntries; numOfEntries is array's numOfEntries before removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
          array[index] = array[index + 1];
        }
    }
    
    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= numberOfEntries + 1; numberOfEntries is array's
     * numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    @Override
    public int compare(T o1, T o2) {
        // Assuming T is comparable, you can directly use compareTo method
        // If T is not guaranteed to be comparable, you should handle that case accordingly
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            Comparable<T> comparable1 = (Comparable<T>) o1;
            Comparable<T> comparable2 = (Comparable<T>) o2;
            return comparable1.compareTo((T) comparable2);
        } else {
            // Handle the case where o1 or o2 is not comparable
            throw new IllegalArgumentException("Objects are not comparable");
        }
    }
    
    private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public void sort() {
        boolean sorted = false;
        for (int pass = 1; pass < numOfEntries && !sorted; pass++) {
            sorted = true;
            for (int index = 0; index < numOfEntries - pass; index++) {
                // swap adjacent elements if first is greater than second
                if (compare(array[index], array[index + 1]) > 0) {
                    swap(array, index, index + 1); // swap adjacent elements 
                    sorted = false;  // array not sorted because a swap was performed
                }
            }
        }
    }
}

    
    
    

    
    

