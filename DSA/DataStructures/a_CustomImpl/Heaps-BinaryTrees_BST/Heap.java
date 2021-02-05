//%%%%%%%%%%%%%   HEAPS / BINARY TREES / BINARY SEARCH TREES %%%%%%%%%%%%%%%%%%%



// BINARY TREE {ARRAY IMPLEMENTATION} with static methods (for simplified access in HEAP Code)

import java.util.*;
import java.util.LinkedList;

class BinaryTree{

    int [] bt;
    int parent;
    int leftChild;
    int rightChild;

    public static int getParentIndex(int index){
        return (int)Math.floor(((index-1)/2));
    }

    public static int getLeftChildIndex(int index){
        return index*2+1;
    }

    public static int getRightChildIndex(int index){
        return index*2+2;
    }

}






                                                                                                                        // HEAP =>

                                                                                                                        //NOTE : FOr MIN HEAP only differences would be ->  For Insertion -swapping upwards - only ITERATIVE COMPARISON LOGIC in the while loop CHANGES from  <child>parent>  TO  <child<parent>
                                                                                                                               //RemoveTop -> swapping downwards - only  ITERATIVE COMPARISON LOGIC in the while loop CHANGES from  <child<parent>  TO  <child>parent>  ALSO SWAP COMPARISON LOGIC changes from left>right TO left<right  ( also must take care of min comparison btw left & right child - i.e. when both are zero)
                                                                                                                                //HeapSort (descending)-> same backward moving logic while storing removeTop() element  OR (ascending) - same logic but store moving forwards thus resulting in min_element @ first_index & max_element @ last_index
                                                                                                                                //Heapify -> swapping downwards - only  ITERATIVE COMPARISON LOGIC in the while loop CHANGES from  <child<parent>  TO  <child>parent>  ALSO SWAP COMPARISON LOGIC changes from left>right TO left<right

// HEAP IMPLEMENTATION
public class Heap {

    //ATTRIBUTES
    private int[] heapArr;
    int size;

    //CONSTRUCTORS
    public Heap(int heapSize){ this.size=heapSize; }

    //METHODS
    public void insertMaxHeap(int x) {

        //ADDING FIRST ELEMENT TO HEAP  [HANDLING BOUNDARY CONDITION]        => (Creation of Heap Array + Insertion)
        if (heapArr == null) { heapArr = new int[size]; heapArr[0] = x; }

        //ADDING SUBSEQUENT ELEMENTS
        else {
            //FINDING FIRST EMPTY SPOT in array
            int i = 0; while (/*[WITHIN ARRAY BOUNDS]*/i < size && heapArr[i] != 0) { i++; }
                //(IF EMPTY SPOT NOT FOUND Throw Error-Message/Exception )
                if (i == size) { System.out.println("***Heap(size="+size+") is full !  ["+x+"] could not be added***"); }           //NOTE => Custom Exception can also be defined by USer in JAVA (by creating custom-exception class & extending the Exception superclass)

            //IF EMPTY SPOT FOUND            => (Insert to leafIndex & perform Swapping based on parent comparison logic)
            else {

                //ELEMENT INSERTION @ LAST LEAF NODE index
                heapArr[i] = x;

                    //ITERATION SETUP
                    int leafIndex = i;                                                                                        //leaf index initially set to i
                    int leaf = heapArr[leafIndex];                                                                                //leaf set to inserted value x (simply for improved code readability in next code block)

                //ITERATIVE COMPARISON & SWAP
                    //COMPARISON OF LEAF WITH PARENTS TILL EITHER //(ARRAY BOUNDS REACHED) OR //(LARGER THAN PARENT CONDITION NOT SATISFIED)
                    while (/*Exit condition when leaf reaches root node*/ BinaryTree.getParentIndex(leafIndex) >= 0 && /*Exit condition when leaf no longer larger than parent*/heapArr[leafIndex] > heapArr[BinaryTree.getParentIndex(leafIndex)]) {
                            int parentIndex = BinaryTree.getParentIndex(leafIndex); //for brevity
                        //SWAP LOGIC
                            int parent = heapArr[parentIndex];
                            heapArr[parentIndex] = leaf;
                            heapArr[leafIndex] = parent;
                        //UPDATION OF INDEX
                            leafIndex = parentIndex;
                    }
            }
        }
    }


    public void insertMinHeap(int x) {

        //ADDING FIRST ELEMENT TO HEAP  [HANDLING BOUNDARY CONDITION]        => (Creation of Heap Array + Insertion)
        if (heapArr == null) { heapArr = new int[size]; heapArr[0] = x; }

        //ADDING SUBSEQUENT ELEMENTS
        else {
            //FINDING FIRST EMPTY SPOT in array
            int i = 0; while (/*[WITHIN ARRAY BOUNDS]*/i < size && heapArr[i] != 0) { i++; }
            //(IF EMPTY SPOT NOT FOUND Throw Error-Message/Exception )
            if (i == size) { System.out.println("***Heap(size="+size+") is full !  ["+x+"] could not be added***"); }           //NOTE => Custom Exception can also be defined by USer in JAVA (by creating custom-exception class & extending the Exception superclass)

            //IF EMPTY SPOT FOUND            => (Insert to leafIndex & perform Swapping based on parent comparison logic)
            else {

                //ELEMENT INSERTION @ LAST LEAF NODE index
                heapArr[i] = x;

                //ITERATION SETUP
                int leafIndex = i;                                                                                        //leaf index initially set to i
                int leaf = heapArr[leafIndex];                                                                                //leaf set to inserted value x (simply for improved code readability in next code block)

                //ITERATIVE COMPARISON & SWAP
                //COMPARISON OF LEAF WITH PARENTS TILL EITHER //(ARRAY BOUNDS REACHED) OR //(LARGER THAN PARENT CONDITION NOT SATISFIED)
                while (/*Exit condition when leaf reaches root node*/ BinaryTree.getParentIndex(leafIndex) >= 0 && /*Exit condition when leaf no longer smaller than parent*/heapArr[leafIndex] < heapArr[BinaryTree.getParentIndex(leafIndex)]) {
                    int parentIndex = BinaryTree.getParentIndex(leafIndex); //for brevity
                    //SWAP LOGIC
                        int parent = heapArr[parentIndex];
                        heapArr[parentIndex] = leaf;
                        heapArr[leafIndex] = parent;
                    //UPDATION OF INDEX
                        leafIndex = parentIndex;
                }
            }
        }
    }


    public int removeTopMaxHeap(){

        int top=0;  //temp var to store & return heap's max/min value

        //IF HEAP ARRAY EMPTY
        if(heapArr==null || heapArr[0]==0){ System.out.println("***Heap is empty !***"); }
        //IF NOT EMPTY => REPLACE ROOT NODE WITH LAST LEAF NODE => FOLLOWED BY A ITERATIVE PROCESS OF COMPARING WITH CHILDREN TILL APPROPRIATE POSITION REACHED
        else{

            //ROOT REPLACEMENT BY LEAF
            top=heapArr[0];
            int i=0; while(i<size && heapArr[i]!=0){ i++; }         //finding index of first empty cell in array
            int last=i-1;                                           //storing last legible element index
            int leaf=heapArr[last];                                 //setting 'leaf' as reference to last legible element
            heapArr[0]=leaf;                                        //putting last leaf element into root node
            heapArr[last]=0;                                        //clearing value in last leaf node

            //ITERATION SETUP
            int leafIndex=0;                                        //setting leaf Index (where leaf is present initially)

            //ITERATIVE PROCESS (RECURSION EQUIVALENT) OF (Comparing the new root with its children + Swapping with largest btw em TILL EITHER  1)Child indices go out of array bounds OR 2)No larger child element found )
                while(/*handling ArrayOutOfBoundsException*/ (BinaryTree.getLeftChildIndex(leafIndex)<size)&&(BinaryTree.getRightChildIndex(leafIndex)<size)  &&
                      /*Checking if either of both children > parent*/ (leaf<heapArr[BinaryTree.getLeftChildIndex(leafIndex)] || leaf<heapArr[BinaryTree.getRightChildIndex(leafIndex)])){

                    //UPDATING LEFT & RIGHT CHILD INDICES + VALUES after each swap
                    int leftChildIndex=BinaryTree.getLeftChildIndex(leafIndex);             int leftChild=heapArr[leftChildIndex];
                    int rightChildIndex=BinaryTree.getRightChildIndex(leafIndex);           int rightChild=heapArr[rightChildIndex];

                        //SWAP LOGIC (+ NODE INDEX UPDATION)
                        if(leftChild>rightChild) {
                            heapArr[leafIndex] = heapArr[leftChildIndex];
                            heapArr[leftChildIndex] = leaf;
                            leafIndex = leftChildIndex;
                        }
                        else if(rightChild>leftChild){
                            heapArr[leafIndex]=heapArr[rightChildIndex];
                            heapArr[rightChildIndex]=leaf;
                            leafIndex = rightChildIndex;
                            }
                        else{break;}
                }
        }
        //returning max element from MaxHeap when removeTop() is called [ Concept utilised in Priority Queues ]
        return top;
    }

    public int removeTopMinHeap(){

        int top=0;  //temp var to store & return heap's max/min value

        //IF HEAP ARRAY EMPTY
        if(heapArr==null || heapArr[0]==0){ System.out.println("***Heap is empty !***"); }
        //IF NOT EMPTY => REPLACE ROOT NODE WITH LAST LEAF NODE => FOLLOWED BY A ITERATIVE PROCESS OF COMPARING WITH CHILDREN TILL APPROPRIATE POSITION REACHED
        else{

            //ROOT REPLACEMENT BY LEAF
            top=heapArr[0];
            int i=0; while(i<size && heapArr[i]!=0){ i++; }         //finding index of first empty cell in array
            int last=i-1;                                           //storing last legible element index
            int leaf=heapArr[last];                                 //setting 'leaf' as reference to last legible element
            heapArr[0]=leaf;                                        //putting last leaf element into root node
            heapArr[last]=0;                                        //clearing value in last leaf node

            //ITERATION SETUP
            int leafIndex=0;                                        //setting leaf Index (where leaf is present initially)

            //ITERATIVE PROCESS (RECURSION EQUIVALENT) OF (Comparing the new root with its children + Swapping with largest btw em TILL EITHER  1)Child indices go out of array bounds OR 2)No larger child element found )
            while(/*handling ArrayOutOfBoundsException*/ (BinaryTree.getLeftChildIndex(leafIndex)<size)&&(BinaryTree.getRightChildIndex(leafIndex)<size)  &&
                    /*Checking if either of both children > parent*/ (leaf>heapArr[BinaryTree.getLeftChildIndex(leafIndex)] || leaf>heapArr[BinaryTree.getRightChildIndex(leafIndex)])){

                //UPDATING LEFT & RIGHT CHILD INDICES + VALUES after each swap
                int leftChildIndex=BinaryTree.getLeftChildIndex(leafIndex);             int leftChild=heapArr[leftChildIndex];
                int rightChildIndex=BinaryTree.getRightChildIndex(leafIndex);           int rightChild=heapArr[rightChildIndex];

                if(leftChild==0){leftChild=Integer.MAX_VALUE;}      //while checking min value , must ignore 0 used by java for empty cell (i.e no node present) during initialization
                if(rightChild==0){rightChild=Integer.MAX_VALUE;}        // hence setting those values to MAX value to allow them to be skipped during minimum comparison
                //SWAP LOGIC (+ NODE INDEX UPDATION)
                if(leftChild<rightChild) {
                    heapArr[leafIndex] = heapArr[leftChildIndex];
                    heapArr[leftChildIndex] = leaf;
                    leafIndex = leftChildIndex;
                }
                else if(rightChild<leftChild) {
                    heapArr[leafIndex]=heapArr[rightChildIndex];
                    heapArr[rightChildIndex]=leaf;
                    leafIndex = rightChildIndex;
                }
                else{break;}            //when no child nodes present, i.e. both children are equal i.e. equal to 0
            }
        }
        //returning min element from MaxHeap when removeTop() is called [ Concept utilised in Priority Queues ]
        return top;
    }



    //CONVERTING INPUT MAX HEAP TO AN ARRAY IN ASCENDING ORDER
    public int[] HeapSortAsc(Heap maxHeap){

        //CREATING HEAP COPY (to ensure preservation of original Heap elements)
        size=maxHeap.heapArr.length;
        int[] unsortedArr=Arrays.copyOf(maxHeap.heapArr,size);
        Heap h=new Heap(size);
        h.heapArr=unsortedArr;


        //ITERATION SETUP

            //FINDING LAST LEAF NODE (i.e. index of last legible element in HeapCopy array)
            int i=0; while(i<size && unsortedArr[i]!=0){ i++; }                              //finding first empty spot in array @ index i
            int last=i-1;                                                                    //setting last legible element index in 'last' variable

            // SETTING UP backward moving index k (starting from last legible element) & new Array to store sorted elements (obtained from MaxHeap)
            int k=last;                                                                        //setting sortedArray lastIndex 'k'
            int[] sortedArr=new int[k+1];                                                      //Creating new array of size (k+1)

        //ITERATIVE PROCESS OF [-> Removal of top(max) element from Heap-copy & its corresponding storage into new Array[k] while moving k backwards]
            for(k=last;k>=0;k--){
                int max=h.removeTopMaxHeap();                //removal of top value (HEAP MAX value)
                sortedArr[k]=max;                            //storage of max value into newArray lastIndex (i.e. k)
            }
        return sortedArr;
    }

        // ====== NOTE ======  //
                // : Iteration of above steps returns and stores largest,then 2nd largest,then 3rd largest...etc MAX values (since MaxHeap)  backwards resulting in ascending order array.
                // *** To obtain Descending array from MaxHeap , simply iterate k forwards from 0 to last (thru k++) ***
                // Ascending from MinHeap = iterate removal & store moving forwards
                // Descending from MinHeap = iterate removal & store moving backwards
        // ===================  //


    //CONVERTING INPUT MIN HEAP TO AN ARRAY IN DESCENDING ORDER
    public int[] HeapSortDesc(Heap minHeap){

        //CREATING HEAP COPY (to ensure preservation of original Heap elements)
        size=minHeap.heapArr.length;
        int[] unsortedArr=Arrays.copyOf(minHeap.heapArr,size);
        Heap h=new Heap(size);
        h.heapArr=unsortedArr;


        //ITERATION SETUP

            //FINDING LAST LEAF NODE (i.e. index of last legible element in HeapCopy array)
            int i=0; while(i<size && unsortedArr[i]!=0){ i++; }                              //finding first empty spot in array @ index i
            int last=i-1;                                                                    //setting last legible element index in 'last' variable

            // SETTING UP backward moving index k (starting from last legible element) & new Array to store sorted elements (obtained from MinHeap)
            int k=last;                                                                        //setting sortedArray lastIndex 'k'
            int[] sortedArr=new int[k+1];                                                      //Creating new array of size (k+1)

        //ITERATIVE PROCESS OF [-> Removal of top(max) element from Heap-copy & its corresponding storage into new Array[k] while moving k backwards]
            for(k=last;k>=0;k--){
                int min=h.removeTopMinHeap();                //removal of top value (HEAP MIN value)
                sortedArr[k]=min;                            //storage of min value into newArray lastIndex (i.e. k)
            }
        return sortedArr;
    }



    //CONVERTING INPUT ARRAY INTO A MAX HEAP
    public static Heap HeapifytoMaxHeap(int[] a){

        //HEAP CREATION FROM INPUT ARRAY
        int size=a.length;
        Heap heap1=new Heap(size);

        //FINDING LAST LEAF NODE in array
        int i=0; while(i<size && a[i]!=0){ i++; }                              //finding first empty spot in array @ index i
        int last=i-1;                                                              //storing last legible element in last

        //ITERATION SETUP (Moving backwards through every node from last leaf node in the input array)
        for(int j=last;j>=0;j--){

                int leafIndex=j;
                int leaf=a[j];

                //whilst CHECKING (->IF Children of that node are within bounds & ->IF either of both children is larger than leaf/node) + PERFORMING (SWAPPING of node with largest btw children + UPDATION of leaf/node index) THROUGH ALL THE DESCENDANTS OF THAT NODE
                //(ITERATIVE PROCESS FOR EACH NODE)

                while(/*handling ArrayOutOfBoundsException for leaf layer nodes*/(BinaryTree.getLeftChildIndex(leafIndex)<size)&&(BinaryTree.getRightChildIndex(leafIndex)<size) && /*checking for swaps i.e. if parent<child */(leaf<a[BinaryTree.getLeftChildIndex(leafIndex)] || leaf<a[BinaryTree.getRightChildIndex(leafIndex)])) {
                    int leftChildIndex = BinaryTree.getLeftChildIndex(leafIndex);     int leftChild = a[leftChildIndex];
                    int rightChildIndex = BinaryTree.getRightChildIndex(leafIndex);   int rightChild = a[rightChildIndex];

                        //SWAP LOGIC
                        if (leftChild > rightChild) { /*then swap leaf with leftChild value , (then update leafIndex value-> automatically done using for loop) & finally increment to get leftChild,rightChild indices again for updated leaf node index */
                            a[leafIndex] = a[leftChildIndex];
                            a[leftChildIndex] = leaf;
                            // ***IMPORTANT*** => following line (updation of the leaf node index before every iteration of while loop)
                                                // allows the parent to be checked as largest AMONGST ALL ITS DESCENDANTS (& not just among its immediate children)
                            leafIndex = leftChildIndex;     //leaf node index updation if swapped

                        }
                        else if(rightChild > leftChild) {
                            a[leafIndex] = a[rightChildIndex];
                            a[rightChildIndex] = leaf;
                            leafIndex = rightChildIndex;    //leaf node index updation if swapped
                        }
                        else{break;}
                }
        }
        //ONCE ALL ELEMENTS HAVE BEEN SEGREGATED INTO LEVELS BY SIZE (i.e MaxHeap formed) then SET THE MODIFIED INPUT ARRAY AS **array attribute of new Heap Object** (created earlier)
        heap1.heapArr=a;
        //return the MaxHeap
        return heap1;
    }

    //CONVERTING INPUT ARRAY INTO A MIN HEAP
    public static Heap HeapifytoMinHeap(int [] b){

        //HEAP CREATION FROM INPUT ARRAY
        int size=b.length;
        Heap heap2=new Heap(size);

        //FINDING LAST LEAF NODE in array
        int i=0; while(i<size && b[i]!=0){ i++; }                              //finding first empty spot in array @ index i
        int last=i-1;                                                              //storing last legible element in last

        //ITERATION SETUP (Moving backwards through every node from last leaf node in the input array)
        for(int j=last;j>=0;j--){

                int leafIndex=j;
                int leaf=b[j];

                //whilst CHECKING (->IF Children of that node are within bounds & ->IF either of both children is larger than leaf/node) + PERFORMING (SWAPPING of node with largest btw children + UPDATION of leaf/node index) THROUGH ALL THE DESCENDANTS OF THAT NODE
                //(ITERATIVE PROCESS FOR EACH NODE)

                while(/*handling ArrayOutOfBoundsException for leaf layer nodes*/(BinaryTree.getLeftChildIndex(leafIndex)<size)&&(BinaryTree.getRightChildIndex(leafIndex)<size) && /*checking for swaps i.e. if parent<child */(leaf>b[BinaryTree.getLeftChildIndex(leafIndex)] || leaf>b[BinaryTree.getRightChildIndex(leafIndex)])) {
                    int leftChildIndex = BinaryTree.getLeftChildIndex(leafIndex);     int leftChild = b[leftChildIndex];
                    int rightChildIndex = BinaryTree.getRightChildIndex(leafIndex);   int rightChild = b[rightChildIndex];

                        //SWAP LOGIC
                        if (leftChild < rightChild) { /*then swap leaf with leftChild value , (then update leafIndex value-> automatically done using for loop) & finally increment to get leftChild,rightChild indices again for updated leaf node index */
                            b[leafIndex] = b[leftChildIndex];
                            b[leftChildIndex] = leaf;
                            //***IMPORTANT*** => this updation of the leaf node index before every iteration of the while loop
                                                // allows the parent to be checked as largest AMONGST ALL ITS DESCENDANTS (& not just among its immediate children)
                            leafIndex = leftChildIndex;     //leaf node index updation if swapped
                        }
                        else if(rightChild > leftChild) {
                            b[leafIndex] = b[rightChildIndex];
                            b[rightChildIndex] = leaf;
                            leafIndex = rightChildIndex;    //leaf node index updation if swapped
                        }
                        else{break;}
                }
        }
        //ONCE ALL ELEMENTS HAVE BEEN SEGREGATED INTO LEVELS BY SIZE (i.e MinHeap formed) then SET THE MODIFIED INPUT ARRAY AS **array attribute of new Heap Object** (created earlier)
        heap2.heapArr=b;
        //return the MinHeap
        return heap2;
    }


//~~~~~~~~~~   OR   ~~~~~~~~~~~~~~~~~~


    // we could simply use FOR-EACH on the input array (in both cases i.e. Heapifying into Min/Max Heaps) & just call earlier defined insertMaxHeap / insertMinHeap functions to add the array elements sequentially such that they are automatically heapified on addition

    public static Heap HeapifyMax2(int[] c){

        //HEAP CREATION FROM INPUT ARRAY
        int size=c.length;
        Heap heapx=new Heap(size);

        for(int i:c){ heapx.insertMaxHeap(i); }     //Use heapx.insertMinHeap(i) to convert input array to corresponding Min Heap
        return heapx;
    }


    //Prints the toString() of the Heap
    public void show() {
        System.out.println("{" + "heapArray=" + Arrays.toString(heapArr) + ", size=" + size + '}');
    }

}











//BINARY SEARCH TREE NODE
class BSTNode {

    String key;
    int data;
    BSTNode left;
    BSTNode right;
    BSTNode parent;

    public BSTNode(String key, int data) {
        this.key = key;
        this.data = data;
    }

}



//BINARY SEARCH TREE IMPLEMENTATION
class BST {

    //Attribute
      BSTNode root;

    //Constructor
        // --

    //Methods
        //insert
        //DFS + BFS Traversal
        //search
        //remove

    public void insert (String key,int data){

        //Create Node to be added
        BSTNode newNode=new BSTNode(key, data);

        //IF BST EMPTY => Add Node as root
        if(root==null) { root=newNode; root.parent=null; }

        //ELSE Search for a spot in the BST where input data fits
        //Thus traverse thru tree using the comparison logic
        else{

            //Create Node reference for traversal & Set to root
            BSTNode current;
            current = root;

            //Iterate the process of moving thru the Nodes while performing comparison btw their data value & the input
            while(true){
                if(data > current.data){

                    if(current.right!=null){ current =current.right; }
                    else{ current.right=newNode; current.right.parent=current; }
                }
                else if(data < current.data){
                    if(current.left!=null){ current=current.left; }
                    else{ current.left=newNode; current.left.parent=current; }
                }
                else{ break; }
            }
        }
    }


    //Depth First Search (DFS)

                                                                                                                        /*
                                                                                                                        //INORDER
                                                                                                                        //public void inorderTraversalStack() {         //  L D R       //Inorder traversal of a BST yields an ordered list
                                                                                                                        //
                                                                                                                        //   Stack<BSTNode> s = new Stack<BSTNode>();
                                                                                                                        //   BSTNode nNode = root;
                                                                                                                        //   s.push(nNode);
                                                                                                                        //   while (nNode.left != null) {
                                                                                                                        //            nNode = nNode.left;
                                                                                                                        //            s.push(nNode);
                                                                                                                        //   }
                                                                                                                        //   System.out.println("Key = " + nNode.key + " with Data = " + nNode.data);
                                                                                                                        //   while (nNode.right != null) {
                                                                                                                        //          nNode = nNode.right;
                                                                                                                        //          s.push(nNode);
                                                                                                                        //   }
                                                                                                                        //   nNode=s.pop();
                                                                                                                        //   System.out.println("Key = " + nNode.key + " with Data = " + nNode.data);
                                                                                                                        //}
                                                                                                                        */

    //INORDER
    public void inorderTraversal(BSTNode nNode) {         //  L D R                                                     //NOTE : Inorder traversal of a BST yields an ordered list

        if (nNode != null) {

            inorderTraversal(nNode.left);
            System.out.println("Key = "+nNode.key+" with Data = "+nNode.data);
            inorderTraversal(nNode.right);
        }

    }

    //PREORDER
    public void preorderTraversal(BSTNode nNode) {       //  D L R

        if (nNode != null) {

            System.out.println("Key = "+nNode.key+" with Data = "+nNode.data);
            preorderTraversal(nNode.left);
            preorderTraversal(nNode.right);
        }

    }

    //POSTORDER
    public void postorderTraversal(BSTNode nNode) {      //  L R D

        if (nNode != null) {

            postorderTraversal(nNode.left);
            postorderTraversal(nNode.right);
            System.out.println("Key = "+nNode.key+" with Data = "+nNode.data);
        }

    }


    //Breadth First Search (BFS)
                                                                                                                        // Unlike DFS which utilises a STACK to pile up children at all levels until we've reached a leaf Node and can return back,
                                                                                                                        // in BFS we utilise a QUEUE, which we initially fill up with the Tree-rootNode/Graph-sourceNode,
                                                                                                                        // followed by an iterating WHILE loop which checks for existence of node children, which then adds them to the queue (if they exist) & continues the loop
    public void BFSBinTreeTraversal() {

        Queue<BSTNode> q = new LinkedList();                                                                            // Can use the Queue Structure provided by Java Collections Framework as well
        q.add(root);

        while (!q.isEmpty()) {

            BSTNode focused = q.remove();
            if (focused.left != null) { q.add(focused.left); }
            if (focused.right != null) { q.add(focused.right); }

            System.out.print(focused.data+"  ");
        }
    }


    public void BFSBinTreeLevels() {                                                                                    //BFS Traversal with elements on each level of the Tree added to a separate List & finally adding them into a List of lists.

        //Creating TreeList to store each level list
        List<List<Integer>> treeList=new ArrayList<>();

            //Creating Queue to store all elements at given level
            Queue<BSTNode> q = new LinkedList();

            //Initial Setup
            q.add(root);
            int iterations=1;

            //WHILE LOOP to add each level (with its elements) to treeList
            while (!q.isEmpty()) {


            //Creating levelList to store elements at each level in separate list
                List<Integer> levelList=new ArrayList<>();

                 //FOR LOOP => to iterate across all children added from prev loop (or init setup) to manifest eachLevel      //Iterative process to remove existing children from queue, adding their data to level list  & then finally adding all their immediate children to queue (for next level processing))      //NOTE ://iteration count depends on number of children stored in queue by prev iteration [ to clear all current children from q & add them to the same level list ] before using the queue for other subsequents children (in a full BinTree, numb of elements @ each level = 2 * right & left children added previously)
                    for (int i = 0; i < iterations; i++) {
                        //IN A FULL BINARY TREE (for each level)
                            //First iteration - The queue stores root added manually (one element)
                            //Second iteration - The queue stores root-left and root-right child  added automatically at the end of first iteration (two element)
                            //Third iteration - The queue stores root-left's (-left & right) & root-right's (left & right) children added at the end of second iteration (four elements) ......
                        BSTNode focused = q.remove();
                        if (focused.left != null) { q.add(focused.left); }
                        if (focused.right != null) { q.add(focused.right); }

                        levelList.add(focused.data);
                    }
                //Updating for-loop iteration count after each Level (to number of children in upcoming level)
                iterations=q.size();

            //Addition of each separate levelList to treeList
            treeList.add(levelList);
            }

        //System.out.println(TreeLevels.toString());
        for (int j = 0; j < treeList.size(); j++) { System.out.println("\n"+treeList.get(j)); }

    }


    public BSTNode search(int x){                                                                                       //FIND INPUT ELEMENT IN B-TREE
            //Search for a Node in the BST where data is equal to Node.data
            //Thus traverse through tree using the comparison logic

            //Create Node reference for traversal & Set to root
            BSTNode current;
            current = root;

            //Transverse thru Nodes while performing checks to see if -> [data value matches search input]
            while(current!=null && current.data!=x) {
                if (x > current.data) { current = current.right; }
                else { current = current.left; }
            }
                if(current==null){ System.out.println("No such element found"); }

        return current;
        }


    public void remove (int x){                                                                                         //REMOVE

    BSTNode current;
    current=search(x);
    if(current==null){ System.out.println("Element not present in the given BSTree"); }
    BSTNode tbd=current;       //Storing the Node TBD (to be deleted)

    //CASES->
            //Element tbd has NO children                                                                                 => //simply cut it off
            if(current.left==null && current.right==null) {

                    //if tbd is root (without children)
                    if (tbd.parent == null) {
                        root = null;
                    }
                    //if tbd a leaf node ->
                    else if (x < tbd.parent.data) {
                        tbd.parent.left = null;
                    } else {
                        tbd.parent.right = null;
                    }
            }

            //Element tbd has ONE child                                                                                  => cut off + adopt [cut off tbd  +  adopt tbd child subtree on tbd parent]
            else if(current.right==null || current.left==null){

                    //If only right child present
                    if(current.left==null){
                            current=current.right;              //Enter only existing right subtree
                            BSTNode replacement=current;        //Store it as replacement

                            //Adopting remaining subtree (+ deletion)
                            if(tbd.parent==null) { root=replacement; replacement.parent=null; }
                            else if (x < tbd.parent.data) { tbd.parent.left = replacement; replacement.parent=tbd.parent; } //If tbd Node is a left child (to its parent), replacement Node also gotta be set as leftChild of parent only.
                            else { tbd.parent.right = replacement; replacement.parent=tbd.parent; }                         //vice versa
                    }

                    //If only left child present
                    if(current.right==null){
                           while(current.left!=null) {current=current.left;}                                              //Enter only existing left subtree
                            BSTNode replacement=current;                                                                  //Store it as replacement

                            //Adopting remaining subtree (+ deletion)
                            if(tbd.parent==null) { root=replacement; replacement.parent=null; }
                            else if (x < tbd.parent.data) { tbd.parent.left = replacement; replacement.parent=tbd.parent;} //If tbd Node is a left child (to its parent), replacement Node also gotta be set as leftChild of parent only.
                            else { tbd.parent.right = replacement; replacement.parent=tbd.parent;}                         //vice versa
                    }
            }

            //Element tbd has BOTH children
            else{

                   //Here we select the minimum value in right-subtree logic (OVER the max in left-subtree logic although either can be used) & use that as replacement instead

                    //first storing the left subtree in a separate temp Node    (& cutting off left SubTree of TBD)
                    BSTNode lSubTree=tbd.left;
                    tbd.left=null;

                    //then storing the right subtree in another temp Node
                    BSTNode rSubTree=tbd.right;

                    //Enter right subtree      (& then cutting off right SubTree of TBD as well)
                    current=current.right;

                    //move leftwards till empty node found (finding minimum in RSubTree)         //& Then cutting it off from immediate parent
                    int c=0;
                    while(current.left!=null){ current=current.left; c++; } //Move left from rSubTree root & Set current to leftmost element
                            /*if no leftward movement directly cut parent's right child (i.e. RSubTree root has no left child)*/
                            if(c==0){ current.parent.right=null; }
                            /*In case RSubTree root *has* left children [then cut out min from its immediate parent & then the tbd right explicitly]  i.e. potential replacement candidates */
                            else{ current.parent.left=null; tbd.right=null; }
                    //Setting min in rSubTree as replacement Node
                    BSTNode replacement=current;


                    //AT THIS STAGE WE HAVE THREE DISJOINTED NODES => lSubTree , rSubTree & replacement/min (which could be rSubTree or not depending on c value)

                    //Replacing tbd with replacement        (takes care of situation where rSubRoot has no children i.e. itself is min/replacement)
                    if(tbd.parent==null){ root=replacement; replacement.parent=null; }
                    else if (tbd.data > tbd.parent.data) { tbd.parent.right=replacement; replacement.parent=tbd.parent; }
                    else { tbd.parent.left=replacement; replacement.parent=tbd.parent; }

                    //Setting LSubTree as leftChild directly (since leftChild for min == null)
                    replacement.left=lSubTree; lSubTree.parent=replacement;

                    //( if replacement not rSubTree, but instead a subnode within rSubTree then appending rSubTree to rightmost element in existing replacement
                    //Moving right from min till current.right==null found then storing RSubTree there                  (since even first element on RSubTree is larger than all elements on left of RSubTree root)
                    if(c!=0) {          /*In case RSubTree root *has* left children, must add it to replacement explicitly*/
                        current = replacement;
                        while (current.right != null) { current = current.right; }
                        current.right = rSubTree;  rSubTree.parent=current;


                    }
            }
    }



}
























////////////        TESTING CLASS       ////////////////////



//MAIN METHOD

class brivuh {


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

/*

        Heap heap = new Heap(7);
        heap.insertMaxHeap(5);
        heap.insertMaxHeap(3);
        heap.insertMaxHeap(2);
        heap.insertMaxHeap(45);
        heap.insertMaxHeap(8);
        heap.insertMaxHeap(10);
        heap.insertMaxHeap(22);
        heap.show();
        heap.insertMaxHeap(24);
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());
        heap.insertMaxHeap(50);
        heap.show();
        System.out.println("Removed from Heap= "+heap.removeTopMaxHeap());

//        int [] sortedArray=heap.HeapSortAsc(heap);
//        System.out.println(Arrays.toString(sortedArray));

//        heap.insertMaxHeap(5);
//        heap.insertMaxHeap(2);
//        heap.insertMaxHeap(45);
//        heap.insertMaxHeap(8);
//        heap.insertMaxHeap(22);
//        heap.show();

        int [] sortedArray=heap.HeapSortAsc(heap);
        System.out.println("!!!Sorted Array ascending (out of input MaxHeap) = "+Arrays.toString(sortedArray)+"!!!");

        System.out.print("***Original Heap Preservation Check=>***  ");
        heap.show();

        int[] a={2,5,22,3,8,45,1};
        System.out.print("Array input "+Arrays.toString(a)+" is heapified to MaxHeap ");
        Heap.HeapifytoMaxHeap(a).show();

        heap.insertMinHeap(5);
        heap.insertMinHeap(2);
        heap.insertMinHeap(45);
        heap.insertMinHeap(8);
        heap.insertMinHeap(22);
        System.out.println("Min Heap Insertions Made to existing empty Heap");
        heap.show();

        int [] sortedArray2=heap.HeapSortDesc(heap);
        System.out.println("!!!Sorted Array descending (out of input MinHeap) = "+Arrays.toString(sortedArray2)+"!!!");

        int[] b={2,5,22,3,8,45,1};
        System.out.print("Array input "+Arrays.toString(a)+" is heapified to MinHeap ");
        Heap.HeapifytoMinHeap(b).show();
*/


//        HeapLL heapll=new HeapLL();

        BST bst=new BST();
        bst.insert("Associate key",8);
        bst.insert("Peon Key",3);
        bst.insert("Sweeper key",1);
        bst.insert("Assistant Associate key",4);
        bst.insert("CEO key",22);
        bst.insert("Salesman key",45);
        bst.insert("Manager key",10);



/*
            Depending on input sequence they stack up in the tree accordingly (no self balancing happens)
//                            8
//                       3        22
//                    1    4   10    45

        */

        System.out.println("Inorder Traversal =>");             //LDR
        bst.inorderTraversal(bst.root);         //As expected, Inorder traversal of a BST yields an ordered list (as it reads Left, then Data, then Right) - 1,3,4,8,10,22,45

        System.out.println("Preorder Traversal =>");            //DLR
        bst.preorderTraversal(bst.root);        //Like QuickSort Algo, should read from Top to bottom (as it reads Root , then Left then Right) -    8,3,1,4,22,10,45

        System.out.println("Postorder Traversal =>");           //LRD
        bst.postorderTraversal(bst.root);       //Like MergeSort Algo, should read from Bottom to Top (as it reads Left. then Right, then finally the Root) -    1,4,3,10,45,22,8

        System.out.println(bst.root.data);
        System.out.println(bst.root.left.data);
        System.out.println(bst.root.left.left.data);

        BSTNode bstNode=bst.search(27);  //Element not found exception thrown within the search method itself  (returns null when no such element present)
        if(bstNode!=null){ System.out.println("Element found with data = "+bstNode.data+" & parent = "+bstNode.parent.data);}


//EXPERIMENTATION

    /*//remove() method experimentation Block - 1

        //      Removal of random node with 2 children          [USING THIRD CODE BLOCK where replacement == rSubTree]
                bst.remove(22);

                System.out.println("-----------------22 removed------------------");

                System.out.println("Inorder Traversal =>");             //LDR
                bst.inorderTraversal(bst.root);         //As expected, Inorder traversal of a BST yields an ordered list (as it reads Left, then Data, then Right) - 1,3,4,8,10,45

                System.out.println(bst.root.left.data);
                System.out.println(bst.root.right.data);
                System.out.println(bst.root.right.left.data);
                System.out.println(bst.root.right.right);


        //                      8
        //               3            45
        //            1    4       10    null


        //      Removal of random node with 1 child   [USING SECOND CODE BLOCK ]

                bst.remove(45);

                System.out.println("------------------45 removed-----------------");

                System.out.println(bst.root.left.data);
                System.out.println(bst.root.right.data);
                System.out.println(bst.root.right.left);
                System.out.println(bst.root.right.right);


        //                      8
        //               3            10
        //            1    4     null    null



        //      Removal of leaf node   [ using FIRST CODE BLOCK }

                bst.remove(4);

                System.out.println("-----------------4 removed------------------");

                System.out.println(bst.root.data);
                System.out.println(bst.root.left.data);
                System.out.println(bst.root.right.data);

                System.out.println(bst.root.left.left.data);
                System.out.println(bst.root.left.right);

                System.out.println(bst.root.right.left);
                System.out.println(bst.root.right.right);



//                        8
//               3               10
//            1    null     null    null


*/


/*

    //remove() method experimentation Block - 2




            // Removal of Root with BOTH children        [USING THIRD CODE BLOCK where replacement != rSubTree]


        bst.remove(8);

        System.out.println("-----------------Root (8) removed------------------");

        System.out.println(bst.root.data);
        System.out.println(bst.root.left.data);
        System.out.println(bst.root.right.data);

        System.out.println(bst.root.left.left.data);
        System.out.println(bst.root.left.right.data);

        System.out.println(bst.root.right.left);
        System.out.println(bst.root.right.right.data);



//                      10
//               3             22
//            1    4     null       45


*/



    //remove() method experimentation Block - 3


            //   Removal of root with BOTH children when right-subtree's minimum/replacement has elements to its own right & hence rSubTree has to be appended to its end [ to check last sub-block code of THIRD BLOCK working ]

        bst.insert("key_random",11);

        System.out.println("-----------------11 inserted------------------");

        System.out.println(bst.root.data);
        System.out.println(bst.root.left.data);
        System.out.println(bst.root.right.data);

        System.out.println(bst.root.left.left.data);
        System.out.println(bst.root.left.right.data);

        System.out.println(bst.root.right.left.data);
        System.out.println(bst.root.right.right.data);

        System.out.println(bst.root.left.left.right);
        System.out.println(bst.root.left.right.left);
        System.out.println(bst.root.right.left.left);
        System.out.println(bst.root.right.left.right.data);
        System.out.println(bst.root.right.right.left);
        System.out.println(bst.root.right.right.right);


//NOTE: HERE 11 though greater than 10, is always lesser than 22 (since it falls on left subtree of 22) hence it is safe to append rSubTee to the last right Node of the replacement Node (min Node)

//                            8
//                       3         22
//                    1    4   10     45
//                               11



        bst.remove(8);

        System.out.println("-----------------8 removed------------------");

        System.out.println(bst.root.data);
        System.out.println(bst.root.left.data);
        System.out.println(bst.root.right.data);

        System.out.println(bst.root.left.left.data);
        System.out.println(bst.root.left.right.data);

        System.out.println(bst.root.right.left);
        System.out.println(bst.root.right.right.data);

        System.out.println(bst.root.right.right.left);
        System.out.println(bst.root.right.right.right.data);

        System.out.println("-------------BFS (BinTree) traversal-------------\n");
        bst.BFSBinTreeTraversal();     //using queue (linkedlist impl)



        System.out.println("\n\n-------------BFS (BinTree) traversal level by level------------");
        bst.BFSBinTreeLevels();


        //NOTE: We could have easily chosen max_of_lSubTree logic for selecting replacement node as well -> in which case most of the residing logic would be handling the lSubTree while the rSubTree would have been simply appended as it is to the replacement Node in the end.

//                            10
//                       3          11
//                    1    4   null    22
//                                        45





        long endTime = System.currentTimeMillis();
        System.out.println("\nTook "+(endTime-startTime) +" ms");
    }
}


































////LinkedlIst Implementationn
//public class HeapLL {
//
//
//    class HLLNode {
//        int data;
//        HLLNode left;
//        HLLNode right;
//
//        HLLNode(int data) {
//            this.data = data;
//        }
//    }
//
//    //Attributes
//
//    HLLNode root;
//
//    //Methods
//    public void insertMaxHeap(int x) {
//
//        HLLNode newNode = new HLLNode(x);         //creating new node
//
//        //ADDING FIRST ELEMENT TO HEAP  [HANDLING BOUNDARY CONDITION]        => (Creation of Heap Array + Insertion)
//        if (root == null) {
//            root = newNode;
//        }
//
//        //ADDING SUBSEQUENT ELEMENTS
//        else {
//            //FINDING FIRST EMPTY SPOT in array
////            int i = 0; while (/*[WITHIN ARRAY BOUNDS]*/i < size && heapArr[i] != 0) { i++; }
//            //(IF EMPTY SPOT NOT FOUND Throw Error-Message/Exception )
////            if (i == size) { System.out.println("***Heap(size="+size+") is full !  ["+x+"] could not be added***"); }           //NOTE => Custom Exception can also be defined by USer in JAVA (by creating custom-exception class & extending the Exception superclass)
//
//            //IF EMPTY SPOT FOUND            => (Insert to leafIndex & perform Swapping based on parent comparison logic)
//            //ELEMENT INSERTION @ LAST LEAF NODE index
//            heapArr[i] = x;
//
//            //ITERATION SETUP
//            int leafIndex = i;                                                                                        //leaf index initially set to i
//            int leaf = heapArr[leafIndex];                                                                                //leaf set to inserted value x (simply for improved code readability in next code block)
//
//            //ITERATIVE COMPARISON & SWAP
//            //COMPARISON OF LEAF WITH PARENTS TILL EITHER //(ARRAY BOUNDS REACHED) OR //(LARGER THAN PARENT CONDITION NOT SATISFIED)
//            while (/*Exit condition when leaf reaches root node*/ BinaryTree.getParentIndex(leafIndex) >= 0 && /*Exit condition when leaf no longer larger than parent*/heapArr[leafIndex] > heapArr[BinaryTree.getParentIndex(leafIndex)]) {
//                int parentIndex = BinaryTree.getParentIndex(leafIndex);
//                int parent = heapArr[parentIndex];
//                //SWAP LOGIC
//                heapArr[parentIndex] = leaf;
//                heapArr[leafIndex] = parent;
//                //UPDATION OF INDEX
//                leafIndex = parentIndex;
//
//            }
//        }
//    }
//}
//
















// SIDE NOTE : DONT USE 0 as input values in any HEAP since they help determine - empty cell blocks
        //ALSO DONT INPUT SAME VALUE MULTIPLE TIMES INTO HEAP , (since those use-cases have mostly been left out while designing code , not that it majorly impacts its functioning or anything)        // Can be easily fixed by adding a conditional block or just adjusting existing conditional statements




/*
*-------------- IMPORTANT OBSERVATIONS ---------------------------
*
* (NOTE : ITERATIVE PROCESS OF {COMPARISON OF A NODE WITH ITS CHILDREN followed by UPDATION OF the NODE INDEX once swapping is complete} ~ COMPARISON OF A NODE WITH ALL ITS DESCENDANTS)
*
*
*
*
*
* */




















//-- INCONSEQUENTIAL SIDE NOTES --

// FIRST DRAFT OF  Heap class > insert method

/*

//Inserted element added to last leaf node of complete BinTree
        if(heapArr==null){ heapArr=new int[size]; int i=0; heapArr[i]=x;}                                                                     //If no existing array => Creating new heap array of input size

                else {
                int i=0; while(heapArr[i]!=0 && i<size){ i++; }
        if(i==size){ System.out.println("Heap is full !"); }

        heapArr[i] = x;                                                                                               //else insert at empty spot
        int parentIndex = BinaryTree.getParentIndex(i-1);

        //Compare value of inserted element to parent
        if (heapArr[i] > heapArr[parentIndex]) {              //if leaf node > parent node , then swap elements (for MAX HEAP)
        int temp = heapArr[parentIndex];
        heapArr[parentIndex] = x;
        heapArr[i] = temp;
        }
        }

*/













/*


class HeapLL{

     class HeapNode{
        int data;
        HeapNode leftChild;
        HeapNode rightChild;
        HeapNode parent;

//        public HeapNode(int data){
//            this.data=data;
//        }
    }

    HeapNode rootNode;

//     public HeapLL(int rootData){
//         HeapNode hn=new HeapNode(rootData);
//         this.rootNode=hn;
//     }

    public HeapLL(){
        this.rootNode=new HeapNode();
     }


        //Methods  ( in LL => insert(x) , insertAt(i) , insertFront(x) , delete(x) , deleteAt(i), search(x), count() , isEmpty()

    public boolean isEmpty(){
         return rootNode==null;
    }

    //Tree Traversal

    public int[] InorderTraversal() {


        HeapNode current=new HeapNode();
        current=rootNode;

        while(current.leftChild!=null){
            current=current.leftChild;
            System.out.println(current.data);
        }

        while(current.rightChild==null){
            current=current.parent;
            System.out.println;
        }



    }







}









//BST CRAP





    public boolean isEmpty() {
        return root == null;
    }


    public BSTNode insert(BSTNode root, int x) {

        if (isEmpty()) {
            root = new BSTNode(x);
        } else {
//            BSTNode current;          //recursive functions must not contains any resets of variables (to be updated with each iteration)
//            current = root;

            if (x <= root.data) {
                                                                                                                        //                if (root.left == null) {
                                                                                                                        //                    root.left = new BSTNode(x);
                                                                                                                        //                } else {
                                                                                                                        //                    root = root.left;
                root.left=insert(root.left,x);
                }
            else {
                                                                                                                        //                if (root.right == null) {
                                                                                                                        //                    root.right = new BSTNode(x);
                                                                                                                        //                } else {
                                                                                                                        //                    root = root.right;
                    root.right=insert(root.right,x);
                }
            }
    return root;            //returns inserted Node ??
    }


//    public BSTNode insert(int x){
//        BSTNode last_inserted=root;
//        last_inserted=insertL(last_inserted,x);
//        return last_inserted;
//    }

//    public BSTNode printInorder(){
//        BSTNode last_in_tree;
//        last_in_tree=printInorderL(root);
//        return last_in_tree;
//    }




    public BSTNode printInorder(BSTNode root) {                //Note: Inorder -> LDR  --> Left - Data - Right

        if (root.left != null) {
//            root = root.left;
            root.left=printInorder(root.left);
        }

        System.out.println(root.data);

        if (root.right != null) {
//            root = root.right;
            root.right=printInorder(root.right);
        }
    return root;        //returns the last node ??
    }

    @Override
    public String toString() {
        return "BST{" + "root=" + root + '}';
    }
}

    //    public boolean contains(int x) {
//
//        if(isEmpty()){
//            System.out.println("Binary Search Tree is empty");
//        }
//        else {
//            BSTNode current;
//            current = root;
//
//            if( )







//            if( x <= data) {
//                if (left == null) {
//                    left = newNode;
//                } else {
//                    left.insert(x);
//                }
//            }
//            else {
//                if(right==null){
//                right=newNode;
//                }
//                else{
//                    right.insert(x);
//                }
//            }
//
//        }
//}



*/






























/*
Graphs                                                                                                                  [ Essentially a set of Vertices (Nodes) & Edges (Connecting Links) ]   ( Note : Edges have a Directional & Weighted property => UniDirectional/BiDirectional & Unweighted/Weighted)
                                                                                                                        [ Can be implemented using only Arrays however taking Space Complexity into account => An array of LinkedLists is preferable (where each array index -> represents each Vertex in Graph & each corresponding LinkedList Node (with attributes - connectionIndex, edgeWeight) -> represents the connected Vertex , weight of their connecting Edge.

        - Trees                                                                                                         [ Notable Attributes of a TREE DS - Depth/Height ]

                -Binary Trees  (Complete/Full/Balanced)                                                                 [ Important Note : How many nodes in a FULL BinTree with l levels => n=(2^l)-1 , How many edges in a FULL BinTree with l levels =>  e=(2^l)-2 OR e=n-1 ]

                        - Heap                              [Organizes data in size-based-hierarchy-levels to provide O(1) for extraction of MIN/MAX value from a list of elements]
                                                            [Min and Max Heap Types] [Used in Priority Queues since root element stored in Heap is always max/min depending on Heap Type]

                        - Binary Search Tree (BST)          [Organizes data so as to provide O(logn) for searching,inserting & removing Node]
                                                            [Implemented using LinkedList to avoid shifting of array elements for every insertion/deletion]
                                                            [uses Recursion to INSERT/DELETE Nodes]     [uses Iterative method to SEARCH for particular value OR even the MIN/MAX value in the BST (where MIN value = leftmost leaf node & MAX - rightmost leaf node)]
                                                            [Note: DFS InOrder traversal of a BST yields an ordered list !!!]
                                                            [ Additional knowledge - BST NODE DELETION logic involves - 1)Checking if node to be deleted has any children ( if 0 - simply Cut loose target Node, if 1 - Cut loose target Node + Adopt its child subtree, if 2 - Cut loose target Node + Find max in left subtree OR Min in right subtree & THEN Shift it to target Node location.

                                 -Self Balancing BSTs
                                        - AVL Trees
                                        - Red Black Trees



                - Non-Binary Trees


                        - B Trees ( Balanced m-way Trees )               [Generalized BSTs   =>   use similar concept of storing all smaller than Node in left SubTree & all larger in right SubTree]
                                                                        [ Only difference being that a Node can have (m-1) keys for a BTree of order m (i.e any Node can have maximum "m" children) ]

                            - 2-3 Trees
                            - 2-3-4 Trees



                        - B+ Trees




                        - Tries



TYPES OF GRAPHS -

    -    Directed / UnDirected

    -    Weighted / Unweighted

    -    Sparsely Populated (Adjacency List) / Densely Populated (Adjacency Matrix)

    -    Cyclic / Acyclic



NOTE : Every Tree is basically a DAG (Directed Acyclic Graph)   { & obviously unweighted }






Interesting Note on Graphs (or even Trees) -

Graph/Tree Traversal is achieved through 2 techniques -

            BFS (Breadth First Search)
            DFS (Depth First Search)  - 3 Types =>  PreOrder/DLR (QuickSort i.e. Top to Bottom)   InOrder/LDR    PostOrder/LRD (MergeSort i.e. Bottom to Top)
*/
