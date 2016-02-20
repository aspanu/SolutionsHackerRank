import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.lang.NumberFormatException;
import java.security.Permission;


/**
 * Created by aspanu on 2015-12-13
 * Calculate Volume
 * https://www.hackerrank.com/challenges/calculating-volume
 */

class Calculate {
    private Scanner scanner;
    public Volume output;

    public Calculate() {
        this.scanner = new Scanner(System.in);
        this.output = new Volume();
    }

    public int getINTVal() throws NumberFormatException {
        int val = this.scanner.nextInt();
        if (val <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return val;
    }

    public double getDoubleVal() throws NumberFormatException {
        double val =this.scanner.nextDouble();
        if (val <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return val;
    }

    public static Volume get_Vol() {
        return new Volume();
    }
}


class Volume {

    public Volume() {
    }

    public void display(double value) throws IOException {
        DecimalFormat df = new DecimalFormat("#.000");
        System.out.println(df.format(value));
        if (value < 0) {
            throw new java.io.IOException("Wtf is this shit.");
        }
    }

    public double main(int cube_side) {
        //Cube
        return Math.pow(cube_side, 3);
    }

    public double main(int l, int b, int h) {
        return l * b * h;
    }

    public double main(double r) {
        //Sphere
        return Math.PI * Math.pow(r, 3) * 2 / 3;
    }

    public double main(double r, double h) {
        //Cylinder
        return Math.PI * h * r * r;
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
        }  catch (IOException e) {
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


