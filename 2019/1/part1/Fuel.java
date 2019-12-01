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
            ans += (in.nextInt() / 3) - 2;
        }

        System.out.println(ans);
    }
}
