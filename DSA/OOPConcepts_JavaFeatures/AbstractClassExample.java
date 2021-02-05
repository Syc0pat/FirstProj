
// PARENT ABSTRACT CLASS
public abstract class AbstractClassExample {
                                                                                                                        /* What are abstract classes? How they differ from Interfaces?
                                                                                                                        Abstract classes are similar to interfaces. You cannot instantiate them, and they may contain a mix of methods declared with or without an implementation.
                                                                                                                        However, with abstract classes, you can declare fields that are not <static final>, and even define public, protected, and private concrete methods.*/
    //FIELDS (inheritable)

        //REGULAR FIELDS                                                                                                //INHERITED in Impl/Sub/Child class. //ACCESSED THRU Impl Class Object/Instance

        int currentYear=2020;

        //STATIC FIELDS                                                                                                 //***INHERITED as static variable in Impl/Child class.  //HENCE ACCESSED DIRECTLY thru Abstract_Class & ALSO thru Impl/Child_Class  //Modification of static var value thru either route produces a change reflected in BOTH***//

        static String ceo="Pat";


    //IMPLEMENTED METHODS (inheritable)
                                                                                                                        //concrete/regular IMPLEMENTED METHODS
                                                                                                                            //****Non-abstract methods in an abstract class have to declare method body (with/without implementation code)****
                                                                                                                                //CAN BE OVERRIDDEN in the sub/Impl class using SAME SIGNATURE METHOD
       void showName(){
           System.out.println("I am implemented like a regular method inside Abstract class (NOT OVERRIDDEN)");
       }

        /*public/protected/private*/ void justforShow(){}


    //"static" IMPLEMENTED METHODS (not inheritable)
                                                                                                                        //NOT INHERITED in Impl/Child class.  //HENCE ACCESSED DIRECTLY only thru Abstract_Class
                                                                                                                            //can use same static method signature in Impl class with different implementation
        static void Suo(){
        System.out.println("I am static method within the Parent Abstract class");
    }



    //ABSTRACT METHODS (must be implemented)-
                                                                                                                        //***To allow for interface-like abstraction of methods => explicitly declare <abstract> with method signature (body missing)***//
                                                                                                                            //IMPLEMENTED in Impl/Sub Class
        abstract int showAge(int age);

}





//  IMPLEMNTING CHILD CLASS
class ClubMember extends AbstractClassExample {                 // inherits both regular fields and methods from the abstract class   ( including static fields BUT NOT static method )                   //must IMPLEMENT abstract methods          from extended abstract class
                                                                                                                                                                                                            //can OVERRIDE regular methods             from extended abstract class

    //IMPLEMENTING ABSTRACT METHODS

        int showAge(int bage){
            System.out.println("abstract method showAge IMPLEMENTED with arg="+bage+" (IMPLEMENTED)");
            return bage;
        }


    //METHOD OVERRIDING

        //void showName(){                                                                 //Same method signature (name,return type,input type) but different implementation
        //  System.out.println("I am overriding existing implementation");
        //};


    //METHOD OVERLOADING   ( Same method name but DIFFERENT arg TYPES OR NUMBER of args )

       void showName(String name){
                    System.out.println("I am overloaded method with  Name= "+name);
                }

        int showAge(){
            System.out.println("showAge overloaded with no input arg");
            return 4;
        };


    // Redefining Child_Class static method  ( ~ modified parent static method )

        static void Suo(){
        System.out.println("I am static method within the Child class");
    }


    // MAIN METHOD                                                                                                      ( TO TEST IMPLEMENTED METHODS )

        public static void main (String[] ssss){

    // Abstract class filled using instantiated Impl/Child_Class object can only fill fields/methods defined in the AbstractClass (regular fields/methods with implemented/overridden values + modified static field value )
                                                                                                                        //NOTE: Parent Static method is hidden hence it can be redefined in the Impl class with same method signature.
    // Creation of Parent_Class instance
        AbstractClassExample exx=new ClubMember();

        // Accessing Parent_Class regular field and method
            System.out.println("---------PARENT CLASS REGULAR FIELDS & METHODS----------");
            System.out.println(exx.currentYear);
            exx.showName();
            exx.showAge(48);




        // Accessing Parent_Class static field and method
            System.out.println("---------PARENT CLASS STATIC FIELDS & METHODS----------");
            System.out.println(AbstractClassExample.ceo);
            AbstractClassExample.Suo();                                                                                 //NOTE : No matter the implementation of Impl class static method with same signature , it DOES NOT OVERRIDE the Parent static method. Rather they both are maintained separately.


    // Creation of Child_Class instance
        ClubMember cm=new ClubMember();

        // Accessing inherited regular fields and methods
            System.out.println("---------CHILD CLASS REGULAR FIELDS & METHODS----------");
            System.out.println("The inherited regular field can be accessed thru child class instance =" +cm.currentYear);
            cm.showName();
            cm.showAge(24);
                                                                                                                    //CHILD_CLASS SPECIFIC OVERLOADED METHODS
                                                                                                                        //            cm.showName("Bruh");
                                                                                                                        //            cm.showAge();

        //Modifying INHERITED static field                                                                              //***NOTE: The value of Parent Class static field CAN BE OVERRIDDEN by modification of the inherited static field through the Child_Class directly.***//
            ClubMember.ceo="New CEO";
            System.out.println("--------------CHILD CLASS STATIC FIELD MODIFIED-----------");


        // Accessing modified Child_Class static field and method
            System.out.println("---------CHILD CLASS STATIC FIELDS & METHODS----------");
            System.out.println(ClubMember.ceo);
            ClubMember.Suo();


        // Accessing Parent_Class static field post-modification
            System.out.println("---------PARENT CLASS MODIFIED FIELDS----------");
            System.out.println( AbstractClassExample.ceo);


    }


}






//SUMMARY

//AN ABSTRACT CLASS
// Allows to declare both  -implementable methods (which can be overridden in Impl/sub/extending class)
//-abstract methods BY explicit <abstract> declaration (which have to be implemented in the Impl class)


        // NOTE : abstract methods CANNOT be declared inside a regular class.