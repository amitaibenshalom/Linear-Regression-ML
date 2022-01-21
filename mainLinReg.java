import java.util.Scanner;

public class mainLinReg {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("How many points would you like to use?");
        int num = in.nextInt();
        while (num < 2)
        {
            System.out.println("Input needs to be bigger than 1");
            System.out.println("How many points would you like to use?");
            num = in.nextInt();
        }
        Point[] points = new Point[num];

        for (int i = 0; i < num; i++)
        {
            System.out.println("Point " + (i+1) + ":");
            System.out.println("Input X value:");
            double x = in.nextDouble();
            System.out.println("Input Y value:");
            double y = in.nextDouble();
            points[i] = new Point(x,y);
            System.out.println();
        }
        LinearReg line = LinearReg.getBestLine(points,99999);
        System.out.println(line);
    }
}
