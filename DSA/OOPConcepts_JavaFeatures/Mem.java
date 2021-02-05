import java.util.Objects;

public class Mem {


    protected int id;
    protected String name;
    protected int credits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Mem(){
        /*System.out.println("Parent constructor");*/
    }

    public Mem(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    @Override
    //WHY THIS OVERRIDE? => CUZ THIS CLASS IMPLICITLY INHERITS THE OBJECT SUPERCLASS and thereby its toString() method WHICH NEEDS TO BE OVERRIDDEN WITH CUSTOM IMPLEMENTATION
    public String toString() {
        return "Mem{id=" + id + ", name=" + name + ", credits=" + credits + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
}






//  toString() explained  -



//CONSIDER Running of the given Snippet :

    //        public static void main(String[] args) {
    //                    Mem memb=new Mem(1,"Palak",220);
    //                      System.out.println(memb);
    //                      }

        //Printing an object/instance (thru SOP) invokes the toString() method in object Class    (HOW exactly System.out.println(Object obj) leads to execution of Object.toString()  provided in EXTRA NOTES below.)


            //toString() implementation within Object superclass -

                    //public String toString() {
                    //    return getClass().getName() + "@" + Integer.toHexString(hashCode());                      //Here getClass() returns a Class.class instance of the current object  & then calls getName() on that instance   WHEREAS toHexString() can be directly called through its class=Integer BECAUSE it is a static method.
                    //}

                            //Since the Mem object inherits the toString() from the Object class implicitly  (& is not overridden by custom implementation)
                            // Thus passing an instance into SOP returns => returns a String appending the object_Class name along with the instance "HashCode"

                                            //          Mem@404b9385



            // However by providing custom implementation for toString() method in the child class (Mem), it can be overridden.

                    //@Override
                    //public String toString() {
                    //     return "Mem{id=" + id + ", name=" + name + ", credits=" + credits + "}";
                    //     }

                                        //Thus passing an instance into SOP returns the overridden implementation->

                                            //          Mem{id=1, name='Palak', credits=220}




//EXTRA NOTES =

// HOW EXACTLY DOES  System.out.println(Object obj) lead to execution of Object.toString()  ???

                    //Following code TAKEN from java.io.PrintStream class (whose instance returned by System.out)

                    //    public void println(Object x) {
                    //        String s = String.valueOf(x);
                    //        synchronized (this) {
                    //            print(s);
                    //            newLine();
                    //        }
                    //    }

                    //Following code TAKEN from String class (whose instance is created & whose method is called THROUGH THE println() method)

                    //    public static String valueOf(Object obj) {
                    //        return (obj == null) ? "null" : obj.toString();
                    //    }






// hashCode() and equals() methods -


/*
*
*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mem mem = (Mem) o;
        return id == mem.id &&
                credits == mem.credits &&
                Objects.equals(name, mem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
*
*
*
*  NOTE :  What is hash code??  => Hash code is a 32-bit signed integer (holds negative,zero and positive values) that allows an object to be managed by a hash-based data structure.
*                                  => It is a unique id number allocated to an object by the JVM at runtime.
*
*
* */


























// ::: NOTE :::

/*  EVERY MODEL CLASS usually has

            //ATTRIBUTES (variables)
            - Attributes

            //CONSTRUCTORS (methods)
            - Parametrized Constructor
            - No Args Constructor

            //ACCESSORS (methods)
            - Getter/Setter for each Attribute


            //INHERI
            - toString()
            - equals(Object o)
            - hashCode()
            - getClass()
*
*
*
*
* */
