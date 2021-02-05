//ANNOTATION EXAMPLE
        //[1] => Field Level Annotation
        //[2] => Method Level Annotation
        //[3] => Type (Class/Interface/Enum) Level Annotation


//ANNOTATION @INTERFACE
import java.lang.annotation.*;
//                                                                                                                        import java.lang.reflect.Method;  //[2]
//                                                                                                                        import java.lang.reflect.Field;   //[1]
@Inherited
@Documented
//[3]
@Target(ElementType.TYPE)
                                                                                                                            //[2]
//                                                                                                                        @Target(ElementType.METHOD)
                                                                                                                            //[1]
//                                                                                                                        @Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Galaxy {

        int GalaxyId() default 100;
        String GalaxyName() default "Milky Way";
        double GalaxyPiValue() default 3.1417;

}



//CONCRETE CLASS  (ANNOTATED)                                                                                                                        //[3]
@Galaxy(GalaxyId = 103,GalaxyName = "Andromeda",GalaxyPiValue = 3.22)
class PyTheorem{

    int a;
                                                                                                                        //[1]
//                                                                                                                        @Galaxy(GalaxyId = 101,GalaxyName = "LMC",GalaxyPiValue = 2.87)
    int b;


    public PyTheorem(int a, int b) {
        this.a = a;
        this.b = b;
    }
                                                                                                                        //[2]
//                                                                                                                        @Galaxy(GalaxyId = 102,GalaxyName = "Antlia2",GalaxyPiValue = 3.45)
    public double getThirdSide(){
    return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }
}




//MAIN CLASS
class Execution{
    public static void main(String[] args) throws Exception {

        PyTheorem pt=new PyTheorem(3,4);
        double ts=pt.getThirdSide();
        System.out.println("Third side dimension ="+ts);

                                                                                                                        //[3]
        Class c=pt.getClass();
//Following step replaces 2 steps=> //Annotation a=c.getAnnotation(Galaxy.class);   +   //Galaxy g=(Galaxy) a;
        Galaxy g=(Galaxy)c.getAnnotation(Galaxy.class);

                                                                                                                        //[2]
//                                                                                                                                Class c=pt.getClass();
//                                                                                                                                Method m=c.getMethod("getThirdSide");          //=> Mention Annotated MethodName
//                                                                                                                                Galaxy g=m.getAnnotation(Galaxy.class);

                                                                                                                        //[1]
//                                                                                                                                Class c=pt.getClass();
//                                                                                                                                Field f=c.getDeclaredField("b");              //=> Mention Annotated FieldName
//                                                                                                                                Galaxy g=f.getAnnotation(Galaxy.class);

        System.out.println("The annotated values accessed are "+g.GalaxyId()+" , "+g.GalaxyName()+" , "+g.GalaxyPiValue());
    }
}





//NOTES

//Thus it is shown how the annotations carrying metadata (added by user) regarding the classes/interface OR methods OR variables of a CLASS are later accessed through the instances of the CLASS to perform any function or operation required with that data

//For example while using JPA  (javax.persistence),
    // => Type Annotation ( @interface Table ) applied on the Model/Entity Class ITSELF takes input annotation_member (String name="table_name")
    // & the Field annotation @interface Column applied on the Entity Class FIELDS  takes input annotation_member (String name="column_name")
                                //=> to use it in the subsequent SQL queries when the corresponding em CRUD methods (like find,persist,merge,remove) are invoked.


// Many such frameworks and tools (including Spring, Hibernate, JPA, JDBC, Struts, JSF, Servlets, EJB )  provide their own set of Annotations
                                                                                            // which must have supporting runtime-code in their packages for each of them
                                                                                                      // to utilise all the user-input annotation data to perform the corresponding operations in the background.


//NOTE :  the reflection api is a very imp API which is not very useful in project development but essential for product development like frameworks and tools as it provides all the base functionalities.






