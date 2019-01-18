//Graphing Calculator by Alex Camer
//Made on Ready to Program
import java.awt.*;
import hsa.Console;
import java.lang.Math;

public class GraphingCalc
{
    static Console a, b;
    public static void main (String[] args)
    {
	a = new Console (37, 93, "Polynomial Graphing Calculator"); //creating ouptut console
	b = new Console (32, 40, "Polynomial Graphing Calculator"); //creating intut console

	a.setColor (Color.black); //drawing the cartesian plane on the output console
	a.drawRect (371, 11, 0, 719);
	a.drawRect (11, 371, 719, 0);
	for (int x = 0 ; x < 41 ; x++)
	{
	    a.drawRect (11 + 18 * x, 369, 0, 4);
	    a.drawRect (369, 11 + 18 * x, 4, 0);
	}
	a.drawString ("5", 363, 286); //placing the numbers on the cartesian plane on the output console
	a.drawString ("10", 357, 196);
	a.drawString ("15", 357, 106);
	a.drawString ("20", 357, 16);
	
	a.drawString ("-5", 359, 466);
	a.drawString ("-10", 353, 556);
	a.drawString ("-15", 353, 646);
	a.drawString ("-20", 353, 736);
	
	a.drawString ("0", 363, 384);
	
	a.drawString ("5", 279, 384);
	a.drawString ("10", 186, 384);
	a.drawString ("15", 96, 384);
	a.drawString ("20", 6, 384);
	
	a.drawString ("-5", 455, 384);
	a.drawString ("-10", 543, 384);
	a.drawString ("-15", 633, 384);
	a.drawString ("-20", 723, 384);
	int c = 1;
	while (1!=2) //never ending loop
	{
	    if(c == 1) //changing the color for each different line
	    {
	    a.setColour (Color.red);
	    c++;
	    }
	    else if(c == 2)
	    {
	    a.setColour (Color.green);
	    c++;
	    }
	    else if(c == 3)
	    {
	    a.setColour (Color.orange);
	    c++;
	    }
	    else if(c == 4)
	    {
	    a.setColour (Color.blue);
	    c++;
	    }
	    else if(c == 5)
	    {
	    a.setColour (Color.cyan);
	    c = 1;
	    }
	    b.println ("What is the degree of your polynomial?"); // prompting user for input
	    int Degree = Integer.parseInt (b.readLine ()); //taking input for degree of polynomial
	    String[] Alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; //creating an alphabet array for the polynomial format coefficients.
	    b.println ("Your polynomial will be in the format:"); //giving the user the format for their polynomial
	    b.println ("");
	    b.print ("y = ");
	    for (int x = 0 ; x <= Degree ; x++)
	    {
		if (x == Degree)
		{
		    b.print (Alphabet [x]);
		}
		else
		{
		    b.print (Alphabet [x] + "x^" + Integer.toString (Degree - x));
		    if (x != Degree)
		    {
			b.print (" + ");
		    }
		}
	    }
	    double Coefficients[] = new double [Degree + 1]; //creating an array to store the coefficient variables
	    b.println ("");
	    b.println ("");
	    b.println ("Input your values:");
	    for (int x = 0 ; x <= Degree ; x++)
	    {
		b.print (Alphabet [x] + " = ");
		Coefficients [x] = Double.parseDouble (b.readLine ());
	    }
	    double m = 0;
	    int last1 = Compute (Coefficients, m), last2 = Compute (Coefficients, m);
	    for (double x = 0 ; x <= 371 ; x++) //plotting and connecting points from 0 to 20
	    {
		double z = x / 18;
		int y = Compute (Coefficients, z); //using Compute method to determine the y value for each x value of the pixels
		a.drawRect (371 + (int) x, 371 - y, 0, 0); //plotting points from 0 to 20
		if (y - last1 > 1) //y - last1 is equal to the distance between 2 consecutive points on the graph
		{
		    for (int i = y - last1 ; i >= 0 ; i--) //connectinng points where slope is positive
		    {
			a.drawRect (371 + (int) x, 371 - y + i, 0, 0);
		    }
		}
		else if (y - last1 < -1) //y - last1 is equal to the distance between 2 consecutive points on the graph
		{
		    for (int i = y - last1 ; i <= 0 ; i++) //connectinng points where slope is negative
		    {
			a.drawRect (371 + (int) x, 371 - y + i, 0, 0);
		    }
		}
		last1 = y;
	    }
	    for (double x = 0 ; x >= -371 ; x--) //plotting and connecting points from -20 to 0
	    {
		double z = x / 18;
		int y = Compute (Coefficients, z); //using Compute method to determine the y value for each x value of the pixels
		a.drawRect (371 + (int) x, 371 - y, 0, 0); //plotting points from -20 to 0
		if (y - last2 > 1) //y - last1 is equal to the distance between 2 consecutive points on the graph
		{
		    for (int i = y - last2 ; i >= 0 ; i--) //connectinng points where slope is positive
		    {
			a.drawRect (371 + (int) x, 371 - y + i, 0, 0); 
		    }
		}
		else if (y - last2 < -1) //y - last1 is equal to the distance between 2 consecutive points on the graph
		{
		    for (int i = y - last2 ; i <= 0 ; i++) //connectinng points where slope is negative
		    {
			a.drawRect (371 + (int) x, 371 - y + i, 0, 0);
		    }
		}
		last2 = y;
	    }
	    b.println ("");
	}
    }


    public static int Compute (double[] Coefficients, double x) //method for calculating the y value for every x value of the pixels
    {
	double a = 0;
	for (int i = 0 ; i < Coefficients.length ; i++) //calculating y value for every inputted x value
	{
	    a = a + (Coefficients [i]) * (Math.pow (x, Coefficients.length - i - 1));
	}
	a = a * 18;
	int b = (int) a; //making b into an integer so that it is returnable
	if (b > 375) //if the line goes below the console - > do not plot it at its actual spot
	{
	    b = 375;
	}
	else if (b < -375) //if the line goes above the console - > do not plot it at its actual spot
	{
	    b = -375;
	}
	return b;
    }
}

