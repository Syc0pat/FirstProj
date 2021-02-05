// %%%%%%%%%%%%%%%%%%%%%%  Graphs Implementation (*+ BFS and DFS Algos) using CustomLinkedList(Generic) as AdjacencyList %%%%%%%%%%%%%%%%%%%%%%


import java.awt.*;
import java.util.*;
import java.util.List;
//import java.util.LinkedList;


//GRAPH - An ordered pair of VERTICES & EDGES
public class Graph {


            //GRAPH-NODE
            static class GraphNode{

                int vindex;                                             //Vertex Index
                //LinkedList<GraphNode> adjl;                           //Corresponding Edge-List                       (LinkedList containing indices of all adjacent vertices to given vertex)  [ called as Adjacency List ]          //NOTE: Adjacency matrix (2x2 array => adjm[i][j]) used in cases when edge connections are DENSE in nature
                CustomLinkedList<Integer> adjl;                                                                         //For simplifying object creation before addition to LinkedList , temporarily using Integer (//also a complex type object representing primitive type)

                public GraphNode(){ adjl=new CustomLinkedList<>(); }                                                    //converting each  GraphNode block's (gn[i]'s) reference adjl to an EMPTY LINKED LIST            //SIDE-NOTE: This is feasible in terms of space complexity since Number of edge connections per vertex are SPARSE

            }


    //ATTRIBUTE
    GraphNode[] gn;                                                                                                     //Equivalent to mathematical definition of a GRAPH [Basically an array containing each -> Vertex (int) & its corresponding Edges (LinkedList<GraphNode>)]


    //CONSTRUCTOR
    public Graph(int size){

        //Constructor Body code-block allows automating the initialization of each array component (i.e each g[i] vindex to 0 & adjl to empty LinkedList {with subsequent setting of vindex to array index values})  during creation of Graph object in main method
                //This allows user to directly start accessing the vertex indices (set as array index) AND SET their corresponding EDGES in the main method without having to create their objects manually OR set the vertices.                //(Manual setting of VERTEX also required in main method itself in-case the VERTEX attribute in GraphNode is a complex type)

        gn=new GraphNode[size];          //simply creates a block of 8*(12 bytes) to allocate memory for 8 GraphNode blocks (with int[4 bytes] & ref-var[8 bytes]) & initializing each block to null.    // this means that g[i].index or g[i].adjl is not accessible until 8 seperate GraphNode objs are created (+initialized) & then assigned to each block
        for(int i=0;i<size;i++){
            gn[i]=new GraphNode();       //initializes each block's int vindex to 0 & LinkedList reference to NULL.          //Thus thru constructor of GraphNode() we initialize each block's reference adjl to corresponding empty LinkedList
            gn[i].vindex=i;              //Setting each vertex index to corresponding array index
        }
    }


    //METHODS

    public boolean hasPathBFS (GraphNode source, GraphNode destination){                                                //CHECKS for existence of path between SRC & DST Nodes (via BFS method)

        boolean flag = false;
        //create Queue for storage of children of focusNode
        //create ArrayList<Integer> to store already focused-Nodes (to prevent duplicate processing of a Node)

        Queue<GraphNode> q = new LinkedList<>();
        ArrayList<Integer> isVisited = new ArrayList<>();

        q.add(source);

        while(!q.isEmpty()){

            //OBTAINING FIRST ELEMENT FROM QUEUE

            GraphNode focused=q.remove();
            int vin=focused.vindex;

            //PROCESSING QUEUE ELEMENT

                    //check for duplicate   ( if true => skip rest of the iteration // else => add vertex index to isVisited list
                    if(isVisited.contains(vin)){ continue; }
                    else isVisited.add(vin);

                    //CHECK FOR DESTINATION REACHED
                    if(focused==destination){flag=true; break;}

                    //Adding all vertices adjacent to focused Node into the Queue
                    for(int i=0;i<gn[vin].adjl.size();i++){
                            int adjv = gn[vin].adjl.get(i);          //get all the adj_vertex_indices from corresponding Edge-List (of given vindex)
                            q.add(gn[adjv]);                          //add GraphNode for each adjacent_vertex_index in gn[]
                        }

            }

        return flag;
    }




    public void BFSGraphTraversal (GraphNode source){                                                                   // Traverses thru each level of the graph (starting from the source Node) till queue is empty i.e all possible interconnected nodes in the graph have been processed
                                                                                                                        //  same as hasPath() , but just LACKS the destination check and ADDS the printing of the "isVisited" List

        //create Queue for storage of children of focus Node
        //create ArrayList<Integer> to store already focused Nodes (to prevent duplicate processing of a Node)
        Queue<GraphNode> q = new LinkedList();
        ArrayList<Integer> isVisited = new ArrayList<>();

        q.add(source);

        while(!q.isEmpty()){

            //OBTAINING FIRST ELEMENT FROM QUEUE

            GraphNode focused=q.remove();
            int vin=focused.vindex;

            //PROCESSING QUEUE ELEMENT

            //check for duplicate   ( if true => skip rest of the iteration // else => add vertex index to isVisited list
            if(isVisited.contains(vin)){ continue; }
            else isVisited.add(vin);

            //Adding all vertices adjacent to focused Node into the Queue
            for(int i=0;i<gn[vin].adjl.size();i++){
                int adjv = gn[vin].adjl.get(i);          //get all the adj vertex indices from corresponding Edge-List (of given vindex)
                q.add(gn[adjv]);                          //store GraphNode corresponding to each adj vertex in local var gn

            }

        }

        //PRINTING list of each unique vertex visited in the graph object (i.e all Nodes) level by level starting from input source Node.
        System.out.println(isVisited.toString());

    }


    public void BFSGraphLevels(GraphNode src) {

                                                                                                                        //PSEUDO CODE  (Regular BFS Traversal)=>

                                                                                                                                    //Queue creation , source addition

                                                                                                                                            //while (q not empty){

                                                                                                                                            //remove front Node from queue
                                                                                                                                                        //GraphNode focused = q remove
                                                                                                                                                        //int vin=focused.vindex;

                                                                                                                                            //check for duplicate
                                                                                                                                            //add to isVisited

                                                                                                                                             //add its adjacent vertex indices to queue
                                                                                                                                                        //int vin_edges=gn[vin].adjl.size();
                                                                                                                                                        //for (i=0; i< vin_edges;i++){  q.add(gn[vin].adjl.get(i)); }
                                                                                                                                                        //}

                                                                                                                                      //Print isVisited



    //ACTUAL CODE (Level By Level Printing while BFS Traversal)

        //isVisited Creation, Queue creation , source addition

                                                                        List<List<Integer>> graphList = new ArrayList<>();
        ArrayList<Integer> isVisited = new ArrayList<>();
        Queue<GraphNode> q = new LinkedList<>();
        q.add(src);

        while (!q.isEmpty()) {

                                                                             List<Integer> levelList = new ArrayList<>();
                                                                                               int iterations = q.size();

            for (int j = 0; j < iterations; j++) {
                //remove front Node from queue
                    GraphNode focused = q.remove();
                    int vindex = focused.vindex;

                //check for duplicate
                    if (isVisited.contains(vindex)) {  continue;  }
                //add to isVisited
                     else { isVisited.add(vindex); levelList.add(vindex); /*extra addition from regular code*/ }



                //add its adjacent vertex indices to queue
//                    int vindex_edges = gn[vindex].adjl.size();
//                     for (int i = 0; i < vindex_edges; i++) { /*get vindex of particular adjacent GraphNode edge*/int vindex_edge = gn[vindex].adjl.get(i);q.add(gn[vindex_edge]); }
//            }


                                //OR


                // NOW THAT ITERABLE has been implemented by DataStructure (Custom LinkedList) and ITERATOR for data TYPE in its Node also has been added, above block can be replaced by enhanced for loop =>
                                                //Enabling much more cleaner code / logic

                     CustomLinkedList<Integer> edges=gn[vindex].adjl;
                        for (Integer edge: edges){ q.add(gn[edge]); }

            }


                                                                   if(!levelList.isEmpty()){ graphList.add(levelList); };
            }

        //System.out.println(graphList.toString());                                           //instead of printing isVisited , we print the graphList instead
        for (List<Integer> integers : graphList) { System.out.println("\n" + integers); }
    }


    public boolean hasPathDFS (GraphNode source, GraphNode destination) {
        ArrayList<Integer> isVisited=new ArrayList<>();
        return hasPathDFSRecursive(source, destination, isVisited);
    }

    private boolean hasPathDFSRecursive (GraphNode src, GraphNode dstn, ArrayList<Integer> isVisited) {

        if(isVisited.contains(src.vindex)){ return false; }
        isVisited.add(src.vindex);

        if(src==dstn){ return true; }

        for(int i=0; i<gn[src.vindex].adjl.size(); i++){
            if(hasPathDFSRecursive(gn[gn[src.vindex].adjl.get(i)],dstn,isVisited))
            {return true;}


        }
        return false;
    }





                                                                                                                        //EXPERIMENTAL METHODS

                                                                                                                        public boolean DFSTraversal (GraphNode source, GraphNode destination) {
                                                                                                                            ArrayList<Integer> isVisited=new ArrayList<>();
                                                                                                                            return DFSTraversalRecursive(source, destination, isVisited);
                                                                                                                        }

                                                                                                                        private boolean DFSTraversalRecursive (GraphNode src, GraphNode dstn, ArrayList<Integer> isVisited) {

                                                                                                                            if(isVisited.contains(src)){ return false; }
                                                                                                                            isVisited.add(src.vindex);
                                                                                                                            //System.out.println();

                                                                                                                            if(src==dstn){ return true; }

                                                                                                                            for(Integer edge : gn[src.vindex].adjl){
                                                                                                                                if(hasPathDFSRecursive(gn[edge],dstn,isVisited))
                                                                                                                                {return true;}

                                                                                                                            }
                                                                                                                            return false;
                                                                                                                        }






    //Simplifying Edge addition for each vertex
    public GraphNode addVertexEdges (int vindex, int[] edges){

        for (int i = 0; i < edges.length ; i++) {
            int edge=edges[i];
            gn[vindex].adjl.insert(edge);
        }
        return  gn[vindex];
    }

}




class yello {
    public static void main(String[] args) {

        Graph graph=new Graph(9);

//        graph.gn[0].adjl.add(1);                                           //setting edges for first Vertex of Graph
//        graph.gn[0].adjl.add(2);                                           //setting edges for first Vertex of Graph
//        graph.gn[0].adjl.add(3);                                           //setting edges for first Vertex of Graph
//
//        graph.gn[1].adjl.add(4);
//        graph.gn[1].adjl.add(0);
//
//        graph.gn[2].adjl.add(5);
//        graph.gn[2].adjl.add(0);
//
//        graph.gn[3].adjl.add(0);
//        graph.gn[3].adjl.add(7);
//
//        graph.gn[4].adjl.add(1);
//        graph.gn[4].adjl.add(5);
//
//        graph.gn[5].adjl.add(2);
//        graph.gn[5].adjl.add(4);
//        graph.gn[5].adjl.add(6);
//
//        graph.gn[6].adjl.add(5);
//
//        graph.gn[7].adjl.add(3);

//        Graph.GraphNode src=graph.gn[0];
//        Graph.GraphNode dstn=graph.gn[6];
//
//        System.out.println("Does 0 have a path to 6 ? = "+graph.hasPath(src,dstn));         //Try breaking the link btw 5 & 6 and observe the result
//        graph.BFSTraversal(src);


                //OR


    //use code optimizing methods -

        //FOR A GRAPH G represented by (V,E) => ((0,1,2,3,4,5,6,7),({1,2,3},{4,0},{5,0},{0,7},{1,5},{2,4,6},{5},{3}))

            // PREPARING edge-list arrays for each vertex

                int[] edges0=new int[]{1,2,3};
                int[] edges1=new int[]{4,0};
                int[] edges2=new int[]{5,0};
                int[] edges3=new int[]{0,7};
                int[] edges4=new int[]{1,5};
                int[] edges5=new int[]{2,4,6};
                int[] edges6=new int[]{5,8};
                int[] edges7=new int[]{3};
                int[] edges8=new int[]{6};


            // SETTING the prepared edge-list arrays into actual EdgeLists (for each Vertex) in the graph object  USING the method => addVertexEdges()

                Graph.GraphNode gn0 = graph.addVertexEdges(0,edges0);
                graph.addVertexEdges(1,edges1);
                graph.addVertexEdges(2,edges2);
                graph.addVertexEdges(3,edges3);
                graph.addVertexEdges(4,edges4);
                graph.addVertexEdges(5,edges5);
                Graph.GraphNode gn6 = graph.addVertexEdges(6,edges6);
                graph.addVertexEdges(7,edges7);
                Graph.GraphNode gn8 = graph.addVertexEdges(8,edges8);

                //FUN-FACT: Try reversing the path & the traversal source and observe the result

                Graph.GraphNode gn_false_test= new Graph.GraphNode();

                System.out.println("BFS => Does Vertex "+gn0.vindex+" have a path to Vertex "+gn8.vindex+" ? = "+graph.hasPathBFS(gn0,gn8));              //Returning boolean result of whether Vertex Node (@index0) has a path to VertexNode (@index6)
                System.out.println("\n Regular BFS Graph Traversal from source ="+gn8.vindex+" is =>");

                //Traversing the GRAPH using BFS Algo with vertex index 0 as source Node =>

                graph.BFSGraphTraversal(gn0);

                System.out.println("BFS => Does Vertex "+gn0.vindex+" have a path to Vertex-outside-graph ? = "+graph.hasPathDFS(gn0,gn_false_test));       //PART 1 - Testing the working of the replacement of LinkedList with CustomLinkedList (when used inside the Graph Implementation)

                graph.BFSGraphLevels(gn0);


        //PART 2 - Testing the working of the replacement of LinkedList with CustomLinkedList       (when used with custom-complex types)

                CustomLinkedList<Mem> memList=new CustomLinkedList<>();
                Mem mem1=new Mem(1,"GAVIN",23);

                Mem mem4=new Mem(4,"KEN",22);
                Mem mem3=new Mem(3,"LAM",74);
                Mem mem2=new Mem(2,"SUPPA",43);
                Mem mem5=new Mem(5,"JOKER",1);


                memList.insert(mem1);
                memList.insert(mem5);
                memList.insert(mem2);
                memList.insert(mem4);
                memList.insert(mem3);


                memList.remove();

                CustomLinkedList<Mem> sortedList=memList.sortList(memList);


        System.out.println("\n\n-----------------------TESTING THE CUSTOMLINKEDLIST with MEM------------------------");

//        while()

        for (int i = 0; i < memList.size() ; i++) { System.out.println(memList.get(i)); }

        for (Mem m:sortedList) { System.out.println("\n"+m); };                //for loop Always prints in an ordered format

        System.out.println("\nsize="+sortedList.size());




//        Algos.mergeSort()

//                graph.DFSTraversal(gn0,gn8);

    }
}





that isnt to say that we knkow




