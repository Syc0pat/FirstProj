class MemDet extends Mem{

    int age;
    String city;

//    public MemDet(int age, String city) {
//        this.age = age;
//        this.city = city;
//    }

//    public MemDet(){
//        super(1,"pat",43);
//        System.out.println("Child constructor");
//    }

    public MemDet(int id, String name, int credits, int age, String city) {
        super(id, name, credits);
        this.age = age;
        this.city = city;
    }
}

class Main{
    public static void main(String[] args) {
        MemDet memDet=new MemDet(1,"dude",43,21,"newhampshire");


//        System.out.println(memDet.id+" "+memDet.name);



    }
}





//
//
//
//Member
//1
//2
//3
//4
//Memdet memv;


