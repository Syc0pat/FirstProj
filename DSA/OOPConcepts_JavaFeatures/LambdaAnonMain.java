import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@FunctionalInterface
interface LambdaExample {
    void display();
}

public class LambdaAnonMain {

    public static void main(String[] args) {

        Mem memb=new Mem(5,"HHELLO",235);

//LAMBDA EXPRESSION TO REPLACE ANONYMOUS INNER CLASS

         //GENERIC ANONYMOUS INNER CLASS ( for implementing abstract methods of both Interfaces & Abstract classes )

                           // LambdaExample anon=new LambdaExample(){
                           //     @Override
                           //     public void display() {
                           //         System.out.println("row rwo row yo bota");
                           //     }};
                           //
                           // anon.display();


                                    //OR

        //LAMBDA EXPRESSION EQUIVALENT TO ANONYMOUS INNER CLASS  ( ONLY works for implementing abstract methods of interfaces )

                            LambdaExample anon=()-> System.out.println("row rwo row yo bota");
                            anon.display();



//LAMBDA EXPRESSION TO REPLACE FOR LOOP

        List<Mem> memblist= new ArrayList<>(Arrays.asList(new Mem(1,"Palak",220),new Mem(2,"Sodhi",350),new Mem(3,"Bugga",4000)));


        // GENERIC FOR LOOP

                    for (int i=0;i<memblist.size();i++){
                        System.out.println(memblist.get(i));

                    }

                                // This prints in the console -

                                            //        row rwo row yo bota
                                            //        Mem{id=1, name=Palak, credits=220}
                                            //        Mem{id=2, name=Sodhi, credits=350}
                                            //        Mem{id=3, name=Bugga, credits=4000}


                                    //OR


        //USING ENHANCED FOR LOOP

            //            for (Mem m:memblist){
            //                System.out.println(m);
            //            }

                                    //OR

        //USING LAMBDA EQUIVALENT EXPRESSION









//        LambdaExample anon = System.out::println;     //METHOD REFERENCES

    }
}



interface foreach{

    void displayAll();

}



//        System.out.println(p);











//EXTRA NOTES : Working of the ___arraylist.get(int index)___ method shown below

// NOTE : THE code below (from java.util.ArrayList class) shows how the list_obj.get(i) method to get an instance from the list obj

    // public E get(int index) {
    //     rangeCheck(index);
    //     return elementData(index);
    //     }

//(Wrapping it in a for loop iterates the instance index and its fetching process)




