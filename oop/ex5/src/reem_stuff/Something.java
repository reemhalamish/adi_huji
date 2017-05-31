package reem_stuff;

/**
 * Created by reem on 31/05/17.
 */
public class Something {
    public void something() {
        A a = new A();
        a.f(); // will return 666

        A a2 = new A(){
            // here. im in an anonymous class that extends A



            @Override
            public int f() {
                return 1234;
            }
        };

        a2.f(); // will give me 1234;


        A a3 = new A(){
            int start = 0;

            @Override
            public int f() {
                start++;
                return start;
            }
        };

        a3.f(); // will return 1
        a3.f(); // will return 2
        a3.f(); // will return 3
        a3.f(); // will return 4
    }

}
