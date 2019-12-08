import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Intcode
{
    public static void main(String [] args)
    {
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        outer:
        for(int noun = 0; noun < 100; noun ++)
        {
            for(int verb = 0; verb < 100; verb++)
            {
                ArrayList<Integer> program = makeProgram(input);

                program.set(1, noun);
                program.set(2, verb);

                if(runProgram(program) == 19690720)
                {
                    System.out.println(100 * noun + verb);
                    break outer;
                }
            }
        }
    }

    public static ArrayList<Integer> makeProgram(String input)
    {
        return Stream.of(input.split(","))
               .map(Integer::parseInt)
               .collect(Collectors.toCollection(ArrayList::new));
    }

    public static int runProgram(ArrayList<Integer> program)
    {
        int ip = 0;

        int op = getOp(ip, program);

        while(op != 99)
        {
            if(op == 1)
            {
                add(ip, program);
            }
            else if(op == 2)
            {
                multiply(ip, program);
            }
            else
            {
                System.out.println("Something went wrong");
                return 0;
            }

            ip += 4;
            op = getOp(ip, program);
        }

        return program.get(0);
    }

    public static int getOp(int ip, ArrayList<Integer> program)
    {
        return program.get(ip);
    }

    public static void add(int ip, ArrayList<Integer> program)
    {
        program.set(program.get(ip+3), program.get(program.get(ip+1)) + program.get(program.get(ip+2)));
    }

    public static void multiply(int ip, ArrayList<Integer> program)
    {
        program.set(program.get(ip+3), program.get(program.get(ip+1)) * program.get(program.get(ip+2)));
    }
}
