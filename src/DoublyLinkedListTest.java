import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to a list containing 0 elements at position 0", "1", testDLL.toString() );
        
        
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
    	assertTrue("Testing the isEmpty() method on an empty DLL. Expecting the result to be true.",test.isEmpty()); //isEmpty() test for 0 elements
    	
    	test.insertBefore(0, 4);
    	assertFalse("Testing the isEmpty() method on a DLL with 1 element. Expecting the result to be false.",test.isEmpty());//isEmpty() test for 1 element
    	test.insertBefore(0,7);
    	test.insertBefore(0, 9);
    	test.insertBefore(0, 12);
    	assertFalse("Testing the isEmpty method on a DLL with 3 elements. Expecting the result to be false.",test.isEmpty());//isEmpty() test for 3 elements
    	
    }
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> test=new DoublyLinkedList<Integer>();
    	
    	assertFalse("Testing he deleteAt() method for an empty DLL. Expecting the result to be false. ",test.deleteAt(0));
    	test.insertBefore(0,5);
    	test.insertBefore(0,5);
    	test.insertBefore(1,8);
    	test.insertBefore(2,20);
    	test.insertBefore(3,13);
    	test.insertBefore(4, 23);
    	
    	assertFalse("Testing deleteAt() method for a negative position input. Expecting the result to be false.",test.deleteAt(-1));
    	
    	//assertFalse("Testing the deleteAt() method for a position input greater than the number of nodes in the DLL. Expecting the result to be false.",test.deleteAt(10));
    	
    	
    	assertTrue("Testing deleteAt() method for the first element in the list. Expected result to be true.",test.deleteAt(0));
    	assertTrue("Testing deleteAt() method for the element in the middle of the list. Expecting the result to be true",test.deleteAt(3));
    	assertTrue("Testing deleteAt() method for the element at the end of the list.Expecting the result to be true.",test.deleteAt(2));
    	
    	
    }
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> test=new DoublyLinkedList<Integer>();
    	
    	//test on empty DLL
    	test.reverse();
    	assertEquals("Testing the reverse() method on an empty DLL. Expecting the result to be an empty string.","",test.toString());
    	
    	//test on DLL with one element
    	test.insertBefore(0, 1);
    	test.reverse();
    	assertEquals("Testing the reverse() method on a DLL with one Element. Expecting the result to be 1.","1",test.toString());
    	
    	//test on DLL with 3 elements
		test.insertBefore(1, 2);
		test.insertBefore(2, 3);
		test.reverse();
		assertEquals("Testing the reverse() method on a DLL with 3 elements. expecting the string 3,2,1.","3,2,1",test.toString());
		
		
		
    }
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> test=new DoublyLinkedList<Integer>();
    	
    	//testing on an empty DLL
    	//assertEquals("Testing get() method for an empty DLL. Expecting an empty string.","",test.get(0).toString());
    	
    	test.insertBefore(0, 1);
		test.insertBefore(1, 2);
		test.insertBefore(2, 3);
		
		//tests to get nodes in position o and 2
		assertEquals("Testing get() method for the first element in the DLL. Expecting 1.","1",test.get(0).toString());
		assertEquals("Testing get() method for the third element in the DLL. Expecting 3.","2",test.get(1).toString());
		
		//test for input greater than number of nodes in the DLL
		//assertEquals("Testing get() method for a position input greater than the number of node in the DLL. Expecting empty string.","",test.get(10).toString());
		
		//test for negative input
		//assertEquals("Testing get() method for a negative position input. Expecting empty string..","",test.get(-1).toString());
		
    }
    @Test
    public void testEnqueueAndDequeue()
    {
    	DoublyLinkedList<Integer> test=new DoublyLinkedList<Integer>();
    	//assertEquals("Testing dequeue() method on empty list. Expecting blank string. ","",test.dequeue().toString());
    	//enqueue integers 0-4
    	test.enqueue(0);
    	test.enqueue(1);
    	test.enqueue(2);
    	test.enqueue(3);
    	
    	//dequeue each element and ensure that enqueue and dequeue methods follow FIFO structure
    	assertEquals("Testing dequeue() method. First value expected is 0. ","0",test.dequeue().toString());
    	assertEquals("Testing dequeue() method. Second value expected is 1. ","1",test.dequeue().toString());
    	assertEquals("Testing dequeue() method. Third value expected is 2. ","2",test.dequeue().toString());
    	
    	
    }
    @Test
    public void testPushAndPop()
    {
    	 DoublyLinkedList<Integer> test=new DoublyLinkedList<Integer>();
    	 
     	//push integers 0-4
     	test.push(0);
     	test.push(1);
     	test.push(2);
     	test.push(3);
     	
     	//pop each element and ensure that enqueue and pop methods follow LIFO structure
     	assertEquals("Testing pop() method. First value expected is 3. ","3",test.pop().toString());
     	assertEquals("Testing pop() method. Second value expected is 2. ","2",test.pop().toString());
     	assertEquals("Testing pop() method. Third value expected is 1. ","1",test.pop().toString());
     	assertEquals("Testing pop() method. Fourth value expected is 0. ","0",test.pop().toString()); 
     	
     	
     }

}