import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: theta (1)
     *
     * Justification:
     *  only has one operation; checking if the head is null or not.
     */
    public boolean isEmpty()
    {
    	return (head==null);
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: theta (N)
     *
     * Justification:
     *  worst case would assume that the element will be inserted at the end of the list. 
     *  A constant run time time is assumed for the operations withing the loop.
     *  Therefore the worst was must be N*theta (1).
     */
    
    public void insertBefore( int pos, T data ) 
    {
    	DLLNode newNode;
    	DLLNode temp;
    	if(isEmpty())
    	{
    		newNode = new DLLNode(data, null, null);
    		head = newNode;
    		tail = newNode;
    		return;
    	}
    	if(pos<=0)
    	{
    		temp = head;
    		newNode = new DLLNode(data,null,head);
    		temp.prev= newNode;
    		head = newNode;
    		return;
    	}
    	else
    	{
    		int i = 0;
    		temp = head;
    		while(i<=pos)
    		{
    			
    			if(i==pos && temp.prev==null)
    			{
    				newNode = new DLLNode(data, null, head );
    				temp.next.prev=newNode;
    				head = newNode;
    				return;
    			}
    			else if(temp.next == null)
    			{
  
    				newNode = new DLLNode(data, tail,null);
    				temp.next = newNode;
    				tail = newNode;
    				return;
    			}
    			else if(i==pos)
    			{
    				newNode = new DLLNode(data, temp.prev, temp);
    				temp.prev.next = newNode;
    				temp.prev = newNode;
    				return;
    			}
    			temp = temp.next;
    			i++;
    		}
    	}
	}
     

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost:theta (N)
     *
     * Justification:
     *  The worst case would assume the position inputted is at the very end of the list.
     *  Therefore the while loop will iterate n times, the operations inside are assumed to be constant.
     *
     * Worst-case precise runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T get(int pos) 
    {
    	if(isEmpty()||pos<0)
    		return null;
    	
    	else
    	{
    		DLLNode temp = head;
    		for (int i=0;i<pos;i++)
    		{
    			if(temp.next!=null)
    				temp = temp.next;
    			else
    				return null;
    		}
    		return temp.data;
    			
    	}
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: theta (N)
     *
     * Justification:
     *  worst case would assume element to be deleted is the last in the list. 
     *  Therefore loop would be iterated N times.
     */
    public boolean deleteAt(int pos) 
    {
    	if (isEmpty()||pos<0)
      	  return false;
        else if(pos==0)
        {
      	  head=head.next;
      	  if(head!=null)
      		  head.prev=null;
      	  return true;
        }
        else 
        {
  	     DLLNode temp =head;
  	     for(int i=0;i<pos;i++)
  	     {
  	    	 if(temp.next==null)
  	    		 return false;
  	    	 temp = temp.next;
  	     }
  	     if (temp == head) 
  			{
  				if (temp.next != null)
  				{
  					temp.next.prev = null;
  					head = temp.next;
  				} 
  				else
  				{
  					temp=null;
  				}
  			} 
  			else if (temp == tail) 
  			{
  				temp.prev.next = null;
  				tail = temp.prev;
  			} 
  			else 
  			{
  				temp.prev.next = temp.next;
  				temp.next.prev = temp.prev;
  			}
  			return true;
  	     
        }

	}

		    
        
		     
		 
	    
	      
   
    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: theta (N)
     *
     * Justification:
     *  For the reverse, method the while loop iterates over every element in the 
     *  list on every occasion.
     */
    public void reverse()
    {
    	
    	if (isEmpty()||head==tail)
			return;
    	else 
    	{
    		boolean finished=false;
			DLLNode node=head;
			T data = node.data;
			while (!finished) 
			{
				DLLNode temp = new DLLNode(data,node.prev,node.next);
				if (node==head) 
				{
					node.prev=temp.next;
					node.next=null;
					node=temp.next;
					
				} 
				else if (node==tail)
				{
					node.next=temp.prev;
					node.prev=null;
					tail=head;
					head=node;
					finished=true;
					
				}
				else 
				{
					node.prev=temp.next;
					node.next=temp.prev;
					node=temp.next;
					
				}
				
			}
		}
		return;
	}
    
    	
      
    


    
    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: theta (1)
     *
     * Justification:
     *  this method calls the insertBefore function however the position input is 0 each time, therefore each operation 
     *  in insertBefore only has to occur once
     */
    public void push(T item) 
    {
      insertBefore(0,item);
    }
    	

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: theta(1)
     *
     * Justification:
     *  deleteAt() method is called however the positioin is always 0 so each operation in deleteAt() only occurs once.
     */
    public T pop() 
    {
    	T data = null;
    	if (isEmpty())
    		return null;
    	else
    	{
    		data = head.data;
    		deleteAt(0);
    		
    	}
    	return data;
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: theta (N)
     *
     * Justification:
     *  insertBefore is called and will always iterrate over the entire list due to the position input
     *   being equal to integer.MAX_VALUE.
     */
    public void enqueue(T item) 
    {
      insertBefore(Integer.MAX_VALUE, item);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: theta (1)
     *
     * Justification:
     *  no iterations
     */
    public T dequeue() 
    {
    	if(isEmpty())
    		return null;
    	
    	T data;
		if(head==tail)
		{
    		data=head.data;
    		head=null;
    		tail=null;
    		return data;
		}
	    if(!isEmpty())
	    {
	    	data = head.data;
		    this.head = this.head.next;
		    this.head.prev = null;
		    return data;
	    }
	    return null;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }
 
      return s.toString();
    }


}