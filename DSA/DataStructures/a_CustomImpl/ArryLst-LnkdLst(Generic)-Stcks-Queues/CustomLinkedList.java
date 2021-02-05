// %%%%%%%%%%%  ARRAYLIST / LINKEDLIST (with Generics, hashCode understanding) / STACKS / QUEUES  %%%%%%%%%%%%% //      /*KEY OBSERVATIONS : While implementing methods for DATA STRUCTURES => After initially visualising the data structure representation & its basic working => START OFF BY CODING FOR MOST PROBABLE CASES (such as when there are a number of Nodes already existing in the List) , ONLY ONCE THAT IS HANDLED move to Handling Exceptions & Edge Cases */


import org.w3c.dom.Node;
import java.util.Iterator;


//CUSTOM IMPLEMENTATION (LinkedList)           //Note : Same class name (LinkedList) may be used since in a different package
public class CustomLinkedList<T> implements Iterable<T> {

    //NODE CREATION (for CustomLL {via Inner Class})                                                                                                       //Declaring 'static' on inner class exhibits its ability to exist without the existence of the outer class    //Hence allows objects of Node to exist without requirement of an existing LinkedList object
    public class NodeLL {                                                                                               //Recursive object (created within a LinkedList object) to hold each element (of any given type) in a collection
            T data;                                                                                                      //Primitive/Complex Type Element to hold User-Input Data
            NodeLL next;                                                                                                //Reference variable pointing to the next Node object in HEAP
            }

//*****SUPER IMPORTANT NOTE: Reference variables in STACK act like temporary pointers (existing for lifetime of their frame) to the actual objects stored in the HEAP (which are destroyed by the garbage collector in JVM once the objects are no longer referenced by any STACK ref variables)****//

    //ATTRIBUTES
             NodeLL head;                                                                                                        //NOTE: KEEP IN MIND WHILE IMPLEMENTING THE METHODS THAT = When any object (containing complex/primitive types) created with "new" keyword in main method, if no constructor used to set values in its attributes=> then the reference is set to NULL
                                                                                                                        // Thus any constituent fields within the object cannot be accessed (Such as head.data OR head.next) since the pointer when dereferenced points to Null && thus a NullPointer Exception is thrown in either case

    //CONSTRUCTORS
            //RANDOM EXPERIMENTATION WITH CONSTRUCTORS

            //                                                                                                                            public LinkedList(){                                 //Non Parametrized Contructors can be used to store custom default values in the Object attributes during its creation
            //                                                                                                                                Node n=new Node();
            //                                                                                                                                n.data=22; //some default value
            //                                                                                                                                n.next=null;
            //                                                                                                                                this.head=n;
            //                                                                                                                            }

            // public LinkedList(Node defaultHeadNode){                                                      //Parametrized Constructors takes values sent in from other methods as Args to calculate & then set custom attributes during obj creation
            //          this.head=defaultHeadNode;
            //    }


    //METHODS
        public void insert(T x) {                           //EQUIVALENT TO add() in Collection Interface

            //Creation of a *new* Node Object to hold the user-input element
            NodeLL newNode = new NodeLL();
            newNode.data = x;

            //Linking newly created Node object to existing LL

                //If List empty (set as headNode)
                if (head == null) {
                    head = newNode;  //Linking by Setting reference variable "head" (in HEAP since instance variable) to point to the first Node object in List (also stored in HEAP referenced by ref-var "newNode" in STACK)
                }

                //If List not empty (link to lastNode)
                else {
                    NodeLL current = head;                                                                                                              //Setting up ref-var "current" in STACK to ALSO point to the First List Node obj (used in HEAP code)         //NOTE: Reference variables in STACK act like temporary pointers (existing for lifetime of their frame) to the actual objects stored in the HEAP (which are destroyed by the garbage collector in JVM once the objects are no longer referenced by any STACK ref variables)  HOWEVER Reference variables stored in the HEAP stay as long as the object holding them is alive
                    //Since last Node's attribute next == null, iterating through the list via the head-pointer + while loop to reach the last Node     //***NOTE : Using the dot operator on a reference variable allows us to dereference the object pointed to by that ref-var & hence access the its attribute values***
                    while (current.next != null) {                                                                                                      //Checking if "next" attribute of dereferenced object (i.e. same object pointed to by ref-var "newNode") points to another Node object in the HEAP  or not
                        current = current.next;                                                                                                         //Updating the ref-var "current" (in STACK) to point to the next Node object(child) thru the dereferencing of current object(using dot) followed by accessing of the ref-var "next" within (both in HEAP)
                    }
                    //On reaching last existing node (even if its first node itself), updating its "next" value from NULL => newly created Node,
                    current.next = newNode;
                }
        }

                                                                                                                        /* IMPORTANT =
                                                                                                                            //All local variables exist in the STACK
                                                                                                                            //All instance variables exist in the HEAP

                                                                                                                                // References "linker","newNode","current" exist in STACK whereas
                                                                                                                                // References "head","next" + Objects pointed to by all these references exist in HEAP

                                                                                                                                //SIDE NOTE: The whole reason all additional Nodes (linked to its previous Node in the List) survives even outside this method is because each child can be considered to be living INSIDE its parent finally resulting in the HEAD NODE which lives in the HEAP (visualize as a chain of hierarchical objects each stored within its parent recursively starting from tail to head)
                                                                                                                                     //and hence the hierarchically highest parent for all Nodes i.e. HEAD NODE which effectively contains all subsequent Nodes, lives as an instance variable in the HEAP providing persistence to the List for the lifetime of the program. (unless the ref-var linker itself is destroyed from the stack in which case the GC will destroy the corresponding pointed object)
                                                                                                                        */

        public boolean isEmpty() {
            return head == null;
        }



        public int size() {

            int c = 1;
            NodeLL current = head;
            while (current.next != null) {
                current = current.next;
                c++;
            }
            return c;
        }



        public int count() {

                int c = 0;
                NodeLL current = head;
                while (current.next != null) {
                    current = current.next;
                    c++;
                }
                return c + 1;
            }


        public void insertAt(int index, T x) {

            //Handling exceptions
            if ((isEmpty() && index != 0) || (index >= count())) {
                System.out.println("Please provide valid position");
            }
            //If index within proper bounds
            else {
                NodeLL newNode = new NodeLL();
                newNode.data = x;

                NodeLL current = head;

                //Handling HeadNode modification + ArrayOutOfBoundsException   //If index=0 => modify head (insertFront)---- if index=1 => don't move current (since current Node already set to Node before Node@inputindex) ---- if index>1 => move current based on index value

             //INSERTING AS HEAD NODE (index=0)

                if (index == 0) {
                    head = newNode;                      //Changing head position to newNode
                    head.next = current;                 //Setting prev existing headNode as "next" to newNode(now head)
                }

            //INSERTING AS ANY OTHER SUBSEQUENT NODE (index!=0)

                //Setting reference "current" to the Node just before required index.
                else {
                    if (index > 1) {
                        for (int i = 0; i < index - 1; i++) {                                                                          //(less than allows one more decrement such that current.next points to Node before index Node)
                            current = current.next;
                        }
                    }

                    //Saving existing Node next to "current" (beyond required index) in temp reference
                    NodeLL temp = current.next;
                    //Updating the current Node "next" reference to newly created Node
                    current.next = newNode;
                    //Updating the newly created Node's "next" reference back to temp
                    newNode.next = temp;
                }
            }
        }

        public void insertFront(T x) {

            //Modification of head Node only         //No transversal required

            NodeLL newNode = new NodeLL();
            newNode.data = x;

            NodeLL temp = head;     //Storing previous head
            head = newNode;       //Changing head to newly inserted Node
            head.next = temp;     //Setting next reference of newNode to prev head

        }


        public T get(int index) {

            NodeLL current = head;
            //        if(head==null) return null;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;

        }


        public void remove() {           //removes first element from top of the List            //EQUIVALENT TO remove()

            //NodeLL current = head;
            if (head == null) {
                System.out.println("Could not be removed, List is empty");
            } else if (head.next == null) {
                head = null;
                System.out.println("Last element from List removed, List is now empty");
            } else {
                head = head.next;
                //current.next = null;
            }
        }


        public void delete(T x) {

            NodeLL current = head;

            //Head node deletion
            if (current.data == x) {

                head = head.next;                         //Set "head" to second Node
                //current.next = null;                    //Set removed Node reference to Null                          //(This step Not needed in Java since GC takes care of removal of unreferenced objects)

            //Subsequent Node deletion
            } else {
                while (current.next.data != x) {
                    current = current.next;
                }
                //NodeLL temp = current.next;             //Store Node TBD (TO BE DELETED) in reference "temp"
                current.next = current.next.next;         //Setting up link btw TBD Node parent & child (i.e. btw Nodes Before & After TBD Node skipping it in the middle)  [Thereby Deleting Link btw TBD Node & its parent]
                //temp.next = null;                       //Deleting Link between TBD & its child Node                                //(This step Not really necessary in Java since GC takes care?)

                //**IMPORTANT** FOR DOUBLY LINKED LISTS => i.e where NodeLL inner class would be defined with an additional attribute (NodeLL prev), we will have to also set current.next's (which was current.next.next before last step) prev to current ,
                             // THUS corresponding code => current.next.prev = current;   to ensure isolation from both ends (after which the GC would handle the removal of isolated obj)//
            }

        }



        public void deleteAt(int index) {

            NodeLL current = head;

            //Handling Head Node modification + ArrayOutOfBoundsException   //If index=0 => modify head ---- if index=1 => don't move current (since current Node already set to Node before Node@inputindex) ---- if index>1 => move current based on index value
        //DELETING HEAD NODE
            if (index == 0) {
                head = head.next;             //Set head to second Node
                current.next = null;          //Release prev head
                current = head;               //Set current back to new head
            }
        //DELETING ANY OTHER NODE (Here => SUBSEQUENT NODE FROM CURRENT NODE)
            else {
                //MOVING to Node before input index
                if (index > 1) {
                    for (int i = 0; i < index - 1; i++) {                                                                          //(less than allows one more decrement such that current.next points to Node before index Node)
                        current = current.next;
                    }
                }
                NodeLL temp = current.next;         //Store TBD in ref-var "temp"
                current.next = current.next.next;   //Link TBD parent & child
                temp.next = null;                   //Release TBD
            }
        }



        public T peek() { return head.data; }



        public void show() {

            if (isEmpty()) {
                System.out.println("***LinkedList is empty***");
            } else {
                NodeLL current = head;
                System.out.print("LinkedList : ");
    //            while (current.next != null) {
    //                System.out.print("[ " + current.data + " ]  ");
    //                current = current.next;
    //            }
                for (int i = 0; i < count(); i++) {
                    System.out.print("[ " + current.data + " ]  ");
                    current = current.next;
                }
                System.out.println("");
            }
        }



        public CustomLinkedList<Mem>.NodeLL searchList(int id,CustomLinkedList<Mem> linkedList){                        //SEARCHING INPUT LIST based on input attribute in a CustomLinkedList<T> where T= Mem and attribute= id

            if (isEmpty()) { System.out.println("***LinkedList is empty***"); return null; }
            else{
                    //Set current to List head
                    CustomLinkedList<Mem>.NodeLL current=linkedList.head;
                    //Transverse + Compare
                    while (current.next != null) {
                             if(current.data.id==id){ return current;}
                                current = current.next;
                    }
                    if(current.data.id==id){ return current;}       //involving last element
            }
            System.out.println("Element not found");
            return null;
        }




        public CustomLinkedList<Mem> sortList(CustomLinkedList<Mem> linkedList) {                                       //SORTING INPUT LIST based on "id" attribute of the ComplexType Object(with T=Mem) defined within Node Object in the CustomLinkedList

            //Creating new SORTED Linkedlist to store Nodes ordered by ids of their data (i.e. Mem objects)
            CustomLinkedList<Mem> sortedList=new CustomLinkedList<>();

            //Traversing thru list WHILST extracting T.id/ T.index/ T.srl_no or even T.name from it
            CustomLinkedList<Mem>.NodeLL current = linkedList.head;
            if (current == null) { System.out.println("EMPTY LIST"); }
            else if(linkedList.size()==1) { return linkedList; }
            else
                {

                    //Create new array to store ids of all existing elements
                    int[] a = new int[linkedList.size()];                                                                                                                                                 //for sorting  based on String , create String[] here

                    //continuously storing in new array while traversing
                    int i=0;
                    while (current.next != null) {
                        a[i]=current.data.id;                                                                                                                                                            //Store each data's (T's) string value from each Node in the inputList => INTO the String[]
                        current = current.next;
                        i++;
                    }
                    a[i]=current.data.id;                                                                               //(accounting for last element with next=null)

                    //run quicksort/mergesort on array  => a[]                                                          //***NOTE : JAVA by default uses double pivot quicksort for primitive arrays & Timsort (modified mergesort) for custom object arrays***
                    int[] sorted=Algos.mergeSort(a);                                                                                                                                                        //Apply sorting algorithm to the Strings in the array



                                                                                                                                                                                                            //  public CustomLinkedList<Mem> OrderByString (CustomLinkedList<Mem> linkedList) {
                                                                                                                                                                                                            // method to - take in  & return back custom LIST with Nodes of type <T> (where T has a String attribute)  with returned version containing Nodes (in original List) ORDERED BY the String attribute inside data part of each Node in the list
                                                                                                                                                                                                                                //  Logic used could be  => to create an char array of char arrays each containing a String attribute from the Node data
                                                                                                                                                                                                                                        //  PSEUDO CODE====>   char[] c_all=new char[String[].length] //for n strings in the String[] => /*parse each String in String[] to char[] c and store in c_all*/  for(int i=0; i<String[].length; i++) { char[] c = String[i].toCharArray(); c_all[i] = c; }
                                                                                                                                                                                                                                                                        //=> converting each character value in each character array in c_all to corresponding ASCII value => sorting in steps [ first sorting each c[j] in c_all  based on value of c[0]  -  if( c[0] value same )then sub loop- sorting each c[k] in c_all  based on c[1] ...... ]  => //finally once sorted, convert back each char[] in c_all into corresponding String & then store into String[] in same order =>  for(int j=0;j<c_all[].length;j++) {String s = c_all[j].toString(); String[j] sortedStrings = s;  }
                    //SEARCH for sorted array elements within the List & then INSERT them node by node into newly created List via the insert(T x) method        //ENCAPSULATE INSIDE FOR LOOP (i=0,i<array length,i++) ( -> search Node within existing linkedlist with id= a[i] , -> insert that Node into a new LinkedList )

                        for(int j=0;j<sorted.length;j++){

                            CustomLinkedList<Mem>.NodeLL node= linkedList.searchList(a[j],linkedList);      if(node==null){return null;}        // created a loop => for(int j=0;j<sortedStrings.length;j++){   CustomLinkedList<Mem>.NodeLL node= linkedList.{{{{{Traverse thru lsit while USING STRING MATCH ALGO to check for a match between (sortedStrings[j], current.data.StringAttribute )   and return the corresponding Node if true returned}}}}}
                            sortedList.insert(node.data);                                                                                       //finally insert node to newly created list // at the end return this list with elements ordered based on String attribute value

                        }
                }
            //return the newLinkedList back (with Nodes (& hence elements of Type T) ordered by selected attribute(id) of their data component)
            return sortedList;

        }



        public Iterator<T> iterator() {                                                                                 //TO ALLOW USAGE OF ENHANCED FOR-EACH  in a custom list implementation ( must implement Iterable<T> & also create an iterator() method which returns => a new ListIterator implementing Iterator interface taking headNode as input & overriding next & hasNext methods)
            return new ListIterator(head);
        }


//    The CustomList class implements Iterable, which allows us to use "for" with the list.
//    In the next step, we will create our own implementation of ListIterator:


        public class ListIterator implements Iterator<T> {

            private NodeLL current;


            public ListIterator(NodeLL head) { current = head; }

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public T next() { T temp = current.data; current = current.next; return temp; }
        }

//    It is necessary to pass the first node of the list in the constructor to access the contents of the list using the pointers (next, prev) depending on your needs. We must also overload the next() and hasNext() methods.

}








//~~~~~~~~~~~~~~~~~~TESTING CLASS~~~~~~~~~~~~~~~~~~~~~~//


//    @Override
//    public int hashCode() {
//        return Objects.hash(head);
//    }
//}


class biruh {

    public static void main(String[] args) {

//        //Hash code Experimentation
//         Mem m=new Mem();
//        System.out.println("Linked List object reference variable is stored as :"+m);
//        System.out.println("Hash Code for Mem object reference variable = "+m.hashCode());

//        LinkedList.Node defltHEAD=new LinkedList.Node();      //Default value setting of Object thru  Constructor Experimentation  (to prevent Null Pointer Exceptions)
//        defltHEAD.data=43;
//        defltHEAD.next=null;
//        LinkedList linkedlist=new LinkedList(defltHEAD);

        CustomLinkedList<Integer> linkedlist=new CustomLinkedList<>();
        System.out.println("Linked List object reference variable is stored as :"+linkedlist);
        System.out.println("Hash Code for linkeslist reference variable = "+linkedlist.hashCode());
        System.out.println("List is empty= "+linkedlist.isEmpty());                                            //shud be true
        linkedlist.show();
        linkedlist.insert(2);      //index 0
        System.out.println("First Node inserted");
        System.out.println("List is empty= "+linkedlist.isEmpty());
        System.out.println("Reference variable 'head' value= "+linkedlist.head);                               //shud be some hashcode (or reference value)
        System.out.println("Dereferenced object's (first node's) data value = "+linkedlist.head.data);         //shud be 2
        System.out.println("Dereferenced object's (first node's) next value = "+linkedlist.head.next);         //shud be null
        linkedlist.show();

        linkedlist.insert(3);     //index 1
        System.out.println("Second Node inserted");
        System.out.println("Element count within LinkedList: "+linkedlist.count());
        System.out.println("Dereferenced object's (first node's) data value = "+linkedlist.head.data);             //shud still be 2
        System.out.println("Dereferenced object's (second node's) data value = "+linkedlist.head.next.data);       //shud be 3
        System.out.println("Dereferenced object's (first node's) next value = "+linkedlist.head.next);             //shud be another random reference value
        System.out.println("Dereferenced object's (second node's) next value = "+linkedlist.head.next.next);       //shud be null

        linkedlist.insert(4);      //index 2
        linkedlist.insert(5);      //index 3
        linkedlist.insert(6);      //index 4

        linkedlist.show();
        int index=0;
        linkedlist.deleteAt(index);    System.out.println("Value @ index "+index+" removed");
        int x=5;
        linkedlist.delete(x);      System.out.println("Value  "+x+" removed");     linkedlist.show();
        linkedlist.insertAt(0,64);     System.out.println("Input value added at reqd position");   linkedlist.show();
        int cc=linkedlist.count();     System.out.println("Element count within LinkedList: "+cc);
        linkedlist.insertAt(3,100);    System.out.println("Input value added at index 3"); linkedlist.show();
        linkedlist.insertFront(44);    System.out.println("Value inserted in Front");  linkedlist.show();
        linkedlist.insert(887);    System.out.println("Value inserted at End of list");    linkedlist.show();
        System.out.println("Peeking at value of head Node= "+linkedlist.peek());


        for (Integer nodeData:linkedlist) {                                                                             //        In the code shown, we noticed that we can iterate over the lists with the for structure.
            System.out.println(nodeData);                                                                               //                Thus, we've implemented our custom List with Java by implementing Iterator.
        }

    }
}






//Based on my humble understanding -

            // Stack Impl using LinkedList would involve 3 methods -
                //ATTRIBUTES
                    //LinkedList stackList;
                    // Node top;
                //METHODS
                    // Push(x)
                                                // stackList.insertFront(x);
                                                // top=stackList.head;
                    // Pop()
                                                //  stackList.deleteAt(0);
                                                //  top=stackList.head;
                   // Peek()
                                                //  stackList.peek();



// Similarly Queue Impl using LinkedList would involve 3 methods -
                //ATTRIBUTES
                        //LinkedList queueList;
                        // Node front;
                        // Node rear;
                //METHODS
                            // int Enqueue(int x){
                                            //  queueList.insert(x);
                                            //  Node current=head;
                                            //  while(current.next!=null){ current=current.next; }
                                            //  rear=current; }


                            // Dequeue()
                                            // queueList.deleteAt(0);
                                            // front=queueList.head;


                            // Peek()
                                            //  return queueList.peek();   OR // return queueList.front.data;


                            // PeekLast() ??
                                            // return queueList.rear.data;                                               ( i.e.  Value of Queue Rear which is last value added to queue )