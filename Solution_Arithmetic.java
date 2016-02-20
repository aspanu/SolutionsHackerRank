
/**
 * Created by aspanu on 2015-12-13
 * Java Inheritance 2
 * https://www.hackerrank.com/challenges/java-inheritence-2
 */

abstract class Arithmetic {
}

class Adder extends Arithmetic{
    public Adder() {
    }

    public int add(int a, int b) {
        return a + b;
    }
}

public class Solution{

    public static void main(String []args){
        Adder X=new Adder();
        System.out.println("My superclass is: "+X.getClass().getSuperclass().getName());
        System.out.print(X.add(10,32)+" "+X.add(10,3)+" "+X.add(10,10)+"\n");

    }
}
