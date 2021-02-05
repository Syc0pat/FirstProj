public class Interface_AbstrctClss_Class {

    public static void main(String[] args) {

        //INTERFACE REFERENCE INSTANCE
        intface bruh=new child();
        bruh.method1(); bruh.method2(); bruh.method3("h"); bruh.method4();
        System.out.println(bruh.pi);

        //INTERFACE
        intface.m5();
        System.out.println(intface.pi);



        //ABSTRACT CLASS REFERENCE INSTANCE
        abstrct reff; reff= new child();
        reff.a=6;
        reff.ref=32877398732L;
        System.out.println(reff.a+"  "+reff.b+"   "+reff.ref);
        System.out.println(reff.pi);


        //ABSTRACT CLASS
        System.out.println(abstrct.pi);
        abstrct.bain();
        System.out.println(abstrct.baller);



        //IMPL CLASS
        child n=new child();
        System.out.println(n.pi);
        n.biznatches();


    }
}



interface intface{

                                                                                        //FIELD
double pi=3.14;                                                                                 //FINAL STATIC - inherited by all children

                                                                                        //METHODS

    void method1(); void method2(); int method3 (String b);                                     //REGULAR  =>  inherited

    default void method4() {System.out.println("4 - interface default");}                       //DEFAULT  =>  inherited

    static void m5(){System.out.println("m5 in static interface method");}                      //STATIC  =>   not-inherited (belongs only to interface declaring it)

}

abstract class abstrct implements intface{

                                                                                         //FIELD
    static double baller =2.1726;                                                                //FINAL STATIC - not-inherited (belongs only to abstract class declaring it)
    static void bain(){System.out.println("inside static method - abstrct class ");}

    int a=5;                                                                                    //REGULAR FIELDS - inherited by all children
    final String b="yoodaleeo";
    long ref=22272862678L;

    public void method1(){System.out.println("1- abstract inherited");}                         //REGULAR IMPL METHODS - inherited by all children
    public void m7(){System.out.println("bruh wtf");}
    final void biznatches(){System.out.println("final methhod");}


    public abstract void m8();                                                                 //ABSTRACT METHOD - inherited for implementation

}




class child extends abstrct {


    @Override
    public void method2(){
        System.out.println("2- overriden at impl");
    }

    @Override
    public int method3(String d) {
        System.out.println("3 - overriden at impl");
        return 0;
    }

    public void m8(){
        System.out.println("abstract m8 implemented in impl");
    }


}
