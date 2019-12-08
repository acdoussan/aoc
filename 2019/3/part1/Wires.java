import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Wires
{
    public static void main(String [] args)
    {
        Scanner in = new Scanner(System.in);

        String wire1 = in.nextLine();
        String wire2 = in.nextLine();

        ArrayList<Line> wire1Lines = getLines(wire1);
        ArrayList<Line> wire2Lines = getLines(wire2);

        Point start = new Point(0,0);
        double ans = Double.POSITIVE_INFINITY;

        for(int i = 0; i < wire1Lines.size(); i++)
        {
            for(int j = 0; j < wire2Lines.size(); j++)
            {
                if(i == 0 && j == 0) continue;

                Line seg1 = wire1Lines.get(i);
                Line seg2 = wire2Lines.get(j);

                Point intersection = seg1.findIntersection(seg2);

                if(!Double.isInfinite(intersection.x) && !Double.isInfinite(intersection.y))
                {
                    double dist = start.dist(intersection);

                    if(dist < ans)
                    {
                        ans = dist;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static ArrayList<Line> getLines(String wire)
    {
        ArrayList<String> paths = Stream.of(wire.split(",")).collect(Collectors.toCollection(ArrayList::new));
        Point at = new Point(0,0);
        ArrayList<Line> ans = new ArrayList<>();

        for(String path : paths)
        {
            char dir = path.charAt(0);
            int amount = Integer.parseInt(path.substring(1,path.length()));

            Point stop = new Point(0,0);

            switch(dir)
            {
                case 'U':
                    stop = new Point(at.x, at.y+amount);
                    break;
                case 'D':
                    stop = new Point(at.x, at.y-amount);
                    break;
                case 'L':
                    stop = new Point(at.x-amount, at.y);
                    break;
                case 'R':
                    stop = new Point(at.x+amount, at.y);
                    break;
            }

            ans.add(new Line(at, stop));
            at = stop;
        }

        return ans;
    }
}

class Point
{
    public double x, y;

    Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double dist(Point o)
    {
        return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
    }

    public String toString()
    {
        return String.format("{%f, %f}", x, y);
    }
}

class Line
{
    public Point s, e;

    Line(Point s, Point e)
    {
        this.s = s;
        this.e = e;
    }

    public Point findIntersection(Line o)
    {
        double a1 = this.e.y - this.s.y;
        double b1 = this.s.x - this.e.x;
        double c1 = a1 * this.s.x + b1 * this.s.y;

        double a2 = o.e.y - o.s.y;
        double b2 = o.s.x - o.e.x;
        double c2 = a2 * o.s.x + b2 * o.s.y;

        double delta = a1 * b2 - a2 * b1;
        Point ints = new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);

        // point is on both line segments
        if(Math.min(this.s.x, this.e.x) <= ints.x && ints.x <= Math.max(this.s.x, this.e.x) &&
           Math.min(this.s.y, this.e.y) <= ints.y && ints.y <= Math.max(this.s.y, this.e.y) &&
           Math.min(o.s.x, o.e.x) <= ints.x && ints.x <= Math.max(o.s.x, o.e.x) &&
           Math.min(o.s.y, o.e.y) <= ints.y && ints.y <= Math.max(o.s.y, o.e.y))
        {
            return ints;
        }
        else
        {
            return new Point(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        }
    }

    public String toString()
    {
        return String.format("Start: %s, End: %s", s, e);
    }
}
