import java.io.*;
import java.util.*;

public class Fuel
{
    public static void main(String [] args)
    {
        Scanner in = new Scanner(System.in);

        long ans = 0;

        while(in.hasNext())
        {
            int fuel = getNeededFuel(in.nextInt());

            while(fuel > 0)
            {
                ans += fuel;
                fuel = getNeededFuel(fuel);
            }
        }

        System.out.println(ans);
    }

    public static int getNeededFuel(int mass)
    {
        return (mass / 3) - 2;
    }
}
