public interface InterfaceExample {

//INTERFACE FIELDS  (USAGE  DISCOURAGED)
    //***Any field declared in interface is implicitly defined with <static final> keyword***//                         // Do not use unless its a global variable like PI with unchanging values across all class implementations//
    static final int establishmentYear=1988;


//ABSTRACT METHODS

    //***All methods in an interface are implicitly set with <public abstract> keywords as shown***//                   ((IMPLEMENTED in Impl/Sub Class))
        public abstract void make(int b);
        void model();


//IMPLEMENTED METHODS                                                                                                   ( MUST READ => https://softwareengineering.stackexchange.com/questions/233053/why-were-default-and-static-methods-added-to-interfaces-in-java-8-when-we-alread had abstract classes )

    //"default" IMPLEMENTED METHODs                                                                                     ( READ => https://stackoverflow.com/questions/27833168/difference-between-static-and-default-methods-in-interface )
                                                                                                                        //***Since Java 8, To implement a method directly in the interface ( just like a regular class ) explicitly declare as <default>***   AKA ---Extension Method---
                                                                                                                        //default methods Can be OVERRIDEN in Impl class using SAME SIGNATURE METHOD
        default String hello(){
            String c="yello i am the original default method impl";
            System.out.println(c);
            return c;
        };

    //"static" IMPLEMENTED METHODS
                                                                                                                        //NOT INHERITED by Impl class - Can be INVOKED only outside Impl class using Interface directly (i.e. without creation of interface reference/instance)             // STATIC INTERFACE METHODS COMMONLY USED AS A HELPER METHOD for the other DEFAULT METHODS in the same interface.
         static void jello(){
             int x=6;   //local var
            System.out.println("hi im whats inside a static method "+x);
        };

}



//IMPL CLASS
class ImplClass implements InterfaceExample {

    //FIELDS

    int implfield=20;                                                                                                   //cannot be accessed by interface reference loaded with impl class object
    final String bruh="DUED";


    //IMPLEMENTING ABSTRACT METHODS

     public void make(int b){
         System.out.println("Implemented abstract method  Make - "+b+ " & interface field value (static final) ="+establishmentYear);
     }
     public void model(){
         System.out.println("Implemented abstract method  Model - empty");
     }

     //OVERRIDING INTERFACE "DEFAULT" IMPLEMENTATION METHODS

     public String hello(){                                                                                             // NOTE: STATIC METHODS CANNOT be OVERRIDDEN
         System.out.println("interface default method overriden ");return "byaaaatch";}

     //ABSTRACT METHOD OVERLOADING

                     public void model(String modelname){
                                                                                                    //Class specific method , cannot be accessed by interface object
                         System.out.println("The model with diff args has name = "+modelname);
                     }

                    public void model(String modelname,String modelversion){
                        //Class specific method , cannot be accessed by interface object
                        System.out.println("The model with diff args has name = "+modelname+" and model version = "+modelversion);
                    }

    //MAIN METHOD

     public static void main (String [] argsss){



         ImplClass impl=new ImplClass();
         impl.make(7);
         impl.hello();
         impl.model("X6","X-Series 2.0");




         InterfaceExample ex;       //Creating interface reference
         ex=new ImplClass();        //Filling the reference using the implementation class instance
                                              //As a result the reference is filled with => Impl class IMPLEMENTED <abstract> methods + OVERRIDDEN <default> methods
         ex.make(7);
         ex.hello();
         ex.model();



         //STATIC VARIABLES & METHODS defined in the Interface --belong to the interface-- (& are invokable only thru the Interface itself)  // Thus No reference of the interface needs to be created and filled before calling this method/variable.

         System.out.println( "The year of establishing BMW is "+InterfaceExample.establishmentYear);
         InterfaceExample.jello();


     }


}





//SUMMARY

//AN INTERFACE
//Allows declaration of  - "abstract" methods implicitly (i.e. <public abstract> methods which MUST be implemented in the Impl class)
//                       - implemented methods via "default" keyword (i.e. interface <default> methods which CAN be overridden in Impl/sub/extending class)
//                       - implemented methods via "static" keyword (i.e. interface <static> methods which CANNOT be overriden/accessed in Impl class)

//STATIC METHODS
//USE OF STATIC FIELDS & METHODS in most cases are discouraged   => // To get an idea about its practical implication wrt Collections package, see =>  <https://stackoverflow.com/questions/129267/why-no-static-methods-in-interfaces-but-static-fields-and-inner-classes-ok-pr/129934#129934>


//Static method can never be inherited, it's redefined by sub-class.
// Classes IMPLEMENTING Interfaces with Static methods do not inherit them. In fact a static method with the same name can be redefined in the child class keeping them both separate.
// Classes EXTENDING Abstract Classes with static methods do not inherit them either.
// Classes EXTENDING Regular Parent Class with static methods inherit them just like instance methods, with the difference that when they are redeclared, the parent static method implementation is HIDDEN rather than OVERRIDDEN.

//SUMMARY FOR BOTH types of methods (added in Java 8)-

        // "static" method in interface:
        //
        //        You can call it directly (InterfacetA.staticMethod())
        //
        //        Sub-class will not be able to override.
        //
        //        Sub-class may have method with same name as staticMethod
        //
        // "default" method in interface:
        //
        //        You can not call it directly.
        //
        //        Sub-class will be able to override it
        //

        // Advantage:
                //
                //static Method: You don't need to create separate class for holding utility methods (example - Collections class in the Java API).
                //
                //default Method: Provides the common functionality for all children in default method itself (making updations much easier & faster.... example - easy inclusion of Streams & lambda APIs in the Java API) .



