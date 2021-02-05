/// IGNORE                                                          // actually the println method is far from simple with a lot of shifting around of large character arrays

public class Println_Emulation {


    public static void main(String[] args) {

        Company.outputDetail.ceo("Hello there");

    }
}


//EMULATING System.out.println ()

class Company{                                          // In place of class System
    static final Ceo outputDetail=null;
}


class Ceo{                                            // In place of class PrintStream
    public static void ceo(String booyah){                      // In place of method println();
        System.out.println(booyah);
        }
}






