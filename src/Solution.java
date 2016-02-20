import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.text.*;
import java.text.DecimalFormat;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;
import java.lang.NumberFormatException;
import java.security.Permission;


/**
 * Created by aspanu on 6/13/14.
 * Calculate Volume
 * https://www.hackerrank.com/challenges/calculating-volume
 */

public class Calculate {
    private Scanner scanner;
    public Volume output;

    public Calculate() {
        this.scanner = new Scanner(System.in);
        this.output = new Volume();
    }

    public int getINTVal() {
        int val = this.scanner.nextInt();
        if (val < 0) {
            throw NumberFormatException("All the values must be positive");
        }
        return val;
    }

    public double getDoubleVal() {
        double val =this.scanner.nextDouble();
        if (val < 0) {
            throw NumberFormatException("All the values must be positive");
        }
        return val;
    }

    public static Volume get_Vol() {
        return this.output;
    }
}


class Volume {

    public Volume() {
    }

    public void display(double value) {
        DecimalFormat df = DecimalFormat("#.000");
        System.out.println(df.format(value));
    }

    public double main(int cube_side) {
        //Cube
        double out = 0.0d;
        out = cube_side ^ 3;
        return out;
    }

    public double main(int l, int b, int h) {
        double out = 0.0d;
        out = l * b * h;
        return out;
    }

    public double main(double r) {
        //Sphere
        double out = 0.0d;
        out = (2/3) * Math.PI() * r;
        return out;
    }

    public double main(double r, double h) {
        //Cylinder
        double out = 0.0d;
        out = Math.PI() * (r^2) * h;
        return out;
    }

}

public class Solution {

    public static void main(String[] args) {
        Do_Not_Terminate.forbidExit();
        try{
            Calculate cal=new Calculate();
            int T=cal.getINTVal();
            while(T-->0){
                double volume = 0.0d;
                int ch=cal.getINTVal();
                if(ch==1){

                    int a=cal.getINTVal();
                    volume=Calculate.get_Vol().main(a);


                }
                else if(ch==2){

                    int l=cal.getINTVal();
                    int b=cal.getINTVal();
                    int h=cal.getINTVal();
                    volume=Calculate.get_Vol().main(l,b,h);

                }
                else if(ch==3){

                    double r=cal.getDoubleVal();
                    volume=Calculate.get_Vol().main(r);

                }
                else if(ch==4){

                    double r=cal.getDoubleVal();
                    double h=cal.getDoubleVal();
                    volume=Calculate.get_Vol().main(r,h);

                }
                cal.output.display(volume);
            }

        }
        catch (NumberFormatException e) {
            System.out.print(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Do_Not_Terminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }


    }
}

/**
 *This class prevents the user from using System.exit(0)
 * from terminating the program abnormally.
 */
class Do_Not_Terminate {

    public static class ExitTrappedException extends SecurityException {
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}//end of Do_Not_Terminate

}
