public class LinearReg {

    private double m;
    private double b;

    public LinearReg(double m, double b)
    {
        this.m = m;
        this.b = b;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Linear line: y = " +
                 m + "x + " +
                 b ;
    }


    public static double distancePointToLine(LinearReg line, Point p)
    {
        // returns the distance from the line to the point p
        return Math.pow(((line.m * p.getX() - p.getY() + line.getB()) / (Math.sqrt(line.m * line.m + 1))),2);
    }
    public static double costFunction(LinearReg line, Point[] points)
    {
        // returns the sum of the distances from the point to the line
        double cost = 0;
        for (int i = 0; i < points.length; i++)
            cost += distancePointToLine(line, points[i]);
        return cost;
    }

    public static int minCostIndex(double[] costs)
    {
        // returns the index of the lowest cost

        int minIndex = 0;
        for (int k = 1; k < costs.length; k++)
        {
            if (costs[k] < costs[minIndex])
                minIndex = k;
        }
        return minIndex;
    }

    public static LinearReg getBestLine(Point[] points, int num)
    {

        // returns the best linear line by the cost function
        LinearReg line = new LinearReg(((points[points.length-1].getY() - points[0].getY())/(points[points.length-1].getX() - points[0].getX())),
                points[0].getY() - points[0].getX()*((points[points.length-1].getY() - points[0].getY())/(points[points.length-1].getX() - points[0].getX())));
        LinearReg tempLine = new LinearReg(line.getM(), line.getB());
        double cost = costFunction(line, points);
        double a = 0.001;
        double[] costs = {cost, 0, 0, 0, 0};
        int minIndex;
        double cost1, cost2, cost3, cost4;
        for (int i = 0; i < num; i++)
        {
            tempLine.setM(line.getM() + a);
            cost1 = costFunction(tempLine, points);
            costs[1] = cost1;
            tempLine.setM(line.getM() - a);
            cost2 = costFunction(tempLine, points);
            costs[2] = cost2;
            tempLine.setM(line.getM());
            tempLine.setB(line.getB() + a);
            cost3 = costFunction(tempLine, points);
            costs[3] = cost3;
            tempLine.setB(line.getB() - a);
            cost4 = costFunction(tempLine, points);
            costs[4] = cost4;

            minIndex = minCostIndex(costs);
            switch (minIndex)
            {
                case 0:
                    a += 0.001;
                    break;
                case 1:
                    line.setM(line.getM() + a);
                    break;
                case 2:
                    line.setM(line.getM() - a);
                    break;
                case 3:
                    line.setB(line.getB() + a);
                    break;
                case 4:
                    line.setB(line.getB() - a);
                    break;
            }
        }
        //System.out.println(1-costFunction(line,points));
        return line;
    }
}
