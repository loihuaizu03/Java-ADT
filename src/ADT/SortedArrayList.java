package ADT;

/**
 * SortedArrayList - Implements the ADT Sorted List using an array. - Note: Some
 * methods are not implemented yet and have been left as practical exercises
 *
 * @param <T>
 */
public class SortedArrayList<T extends Comparable<T>> implements SortedListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;

    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        int i = 0;
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean isSucessful = false;
        
        for(int i=0;i<numberOfEntries;i++){
            if(anEntry.equals(array[i])){
                if(i < numberOfEntries) {
                    removeGap(i);
                }
                numberOfEntries--;
                isSucessful = true;
            }
        }
        return isSucessful;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; index < numberOfEntries && anEntry.compareTo(array[index]) >= 0; index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += (index+1) + "." + array[index] + "\n";
        }

        return outputStr;
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldList = array;
        int oldSize = oldList.length;

        array = (T[]) new Object[2 * oldSize];

        for (int index = 0; index < oldSize; index++) {
            array[index] = oldList[index];
        }
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public boolean isFull() {
        return false;
    }

}
