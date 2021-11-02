import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XmlVisitor;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SortsTester {

    @Test
    public void insertionSort() {
        Sorts<Integer> test1 = new Sorts<>();
        ArrayList<Integer> randNums = new ArrayList<>();
        ArrayList<Integer> expectedNums = new ArrayList<>();
        expectedNums.add(1);
        expectedNums.add(2);
        expectedNums.add(2);
        expectedNums.add(3);
        expectedNums.add(6);
        expectedNums.add(7);
        expectedNums.add(9);
        randNums.add(1);
        randNums.add(3);
        randNums.add(6);
        randNums.add(2);
        randNums.add(9);
        randNums.add(7);
        randNums.add(2);
        test1.InsertionSort(randNums, 0, randNums.size()-1);
        assertEquals(expectedNums, randNums);
        randNums.add(4);
        expectedNums.add(4);
        test1.InsertionSort(randNums, 0, randNums.size()-1);
        test1.InsertionSort(expectedNums, 0, randNums.size()-1);
        assertEquals(expectedNums, randNums);
        expectedNums.remove(expectedNums.size()-1);
        randNums.remove(randNums.size()-1);
        assertEquals(expectedNums, randNums);
        ArrayList<Integer> randNums2 = new ArrayList<>();
        ArrayList<Integer> expectedNums2 = new ArrayList<>();
        randNums2.add(54);
        randNums2.add(53);
        randNums2.add(52);
        randNums2.add(51);
        expectedNums2.add(51);
        expectedNums2.add(52);
        expectedNums2.add(53);
        expectedNums2.add(54);
        test1.InsertionSort(randNums2, 0, randNums2.size()-1);
        assertEquals(expectedNums2, randNums2);
        randNums2.add(5);
        expectedNums2.add(5);
        assertEquals(expectedNums2, randNums2);
        expectedNums2.remove(expectedNums2.size()-1);
        test1.InsertionSort(randNums2, 0, randNums2.size()-1);
        expectedNums2.add(0, 5);
        assertEquals(expectedNums2, randNums2);
    }

    @Test
    public void quickSort() {
        Sorts<Integer> test1 = new Sorts<>();
        ArrayList<Integer> randNums = new ArrayList<>();
        ArrayList<Integer> expectedNums = new ArrayList<>();
        randNums.add(3);
        randNums.add(2);
        randNums.add(4);
        randNums.add(1);
        randNums.add(5);
        test1.QuickSort(randNums, 0, randNums.size()-1);
        expectedNums.add(1);
        expectedNums.add(2);
        expectedNums.add(3);
        expectedNums.add(4);
        expectedNums.add(5);
        assertEquals(expectedNums, randNums);
    }

    @Test
    public void modified_QuickSort() {
    }

    @Test
    public void cocktailSort() {
    }
}