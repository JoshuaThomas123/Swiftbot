import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

import swiftbot.SwiftBotAPI;

public class Assignment3_code {
	private static SwiftBotAPI swiftbot;

	public static void main(String args[]) {
		try {
			swiftbot = new SwiftBotAPI();
		} catch (ExceptionInInitializerError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Choose your input          Example shapes:"); // Intial ui
		System.out.println("S                          Square      **");
		System.out.println("                                       **");
		System.out.println("T                          Triangle     *");
		System.out.println("                                       ***");
		System.out.println("P                          Pentagon      *");
		System.out.println("                                       *****");
		System.out.println("                                        ***");
		System.out.println("Q:to quit");
		ArrayList<String> Input_decision = new ArrayList<String>();
		ArrayList<Integer> Triangle_Length1_rec = new ArrayList<Integer>();
		ArrayList<Integer> Triangle_Length2_rec = new ArrayList<Integer>();
		ArrayList<Integer> Triangle_Length3_rec = new ArrayList<Integer>();
		ArrayList<Integer> Square_Length_rec = new ArrayList<Integer>();
		ArrayList<Integer> Pentagon_Length_rec = new ArrayList<Integer>();
		ArrayList<Integer> time = new ArrayList<Integer>();
		ArrayList<Integer> area = new ArrayList<Integer>(); // arraylist records all of the inputs/needed outputs
		while (true) { // loops anything past this
			Scanner in = new Scanner(System.in); // allows the user to make a input within the system
			System.out.println("Type one of the listed letters");

			String Input = in.nextLine(); // input

			if (Input.equals("S")) { // if loop, for which decision user makes, each if statement goes to a different
										// method
				Input_decision.add(Input);
				Square(Square_Length_rec, area, time);
			}

			else if (Input.equals("T")) {
				Input_decision.add(Input);
				Triangle(Triangle_Length1_rec, Triangle_Length2_rec, Triangle_Length3_rec, area, time);
			} else if (Input.equals("P")) {
				Input_decision.add(Input);
				Pentagon(Pentagon_Length_rec, area, time);
			} else if (Input.equals("Q")) {
				Input_decision.add(Input);
				Quit(Triangle_Length1_rec, Pentagon_Length_rec, Triangle_Length2_rec, Triangle_Length3_rec,
						Square_Length_rec, Input_decision, area, time);
			} else {
				System.out.println("incorrect input"); // informs the user,then allows the user to input another input.
			}
		}
	}

	public static void Square(ArrayList<Integer> Square_Length_rec, ArrayList<Integer> area, ArrayList<Integer> time) {
		Scanner in = new Scanner(System.in);

		System.out.println("Type in a number, within the ranges of 15-85cm:");
		int Square_Length = in.nextInt(); // square input only needs one condition because we are asking for only one
											// input

		if (15 <= Square_Length && Square_Length <= 85) {
			Square_Length_rec.add(Square_Length);
			DrawingSquare(Square_Length); // method for the swiftbot to move in a square
			time.add( (int) ((int) 4 * (363 * 0.1 * Square_Length + 1266 * 0.5))); // adds to the arraylist, ported formula from
																			// the drawingsquare method
			area.add((int) (Square_Length * Square_Length));
		} else {
			System.out.println("invalid number");
			Indicator_red(); // method to turn the robot red for 1s
			Square(Square_Length_rec, time, area); // loops back to square, if it is a incorrect input
		}

	}

	public static void Triangle(ArrayList<Integer> Triangle_Length1_rec, ArrayList<Integer> Triangle_Length2_rec,
			ArrayList<Integer> Triangle_Length3_rec, ArrayList<Integer> area, ArrayList<Integer> time) {
		Scanner in = new Scanner(System.in);

		System.out.println("Type in a number, within the ranges of 15-85cm:");
		int Triangle_Length1 = in.nextInt();
		System.out.println("Input another number");
		int Triangle_Length2 = in.nextInt();
		System.out.println("Input another number");
		int Triangle_Length3 = in.nextInt(); // triangle allows for three inputs, then if it is within the parameters of
												// 15-85cm then another method is called

		if (15 <= Triangle_Length1 && Triangle_Length1 <= 85 && 15 <= Triangle_Length2 && Triangle_Length2 <= 85
				&& 15 <= Triangle_Length3 && Triangle_Length3 <= 85) {

			Angledecis(Triangle_Length1_rec, Triangle_Length2_rec, Triangle_Length3_rec, Triangle_Length1,
					Triangle_Length2, Triangle_Length3, time, area);

		} else {
			Indicator_red();
			Triangle(Triangle_Length1_rec, Triangle_Length2_rec, Triangle_Length3_rec, time, area); // loops back, if
																									// the user makes a
																									// incorrect input
		}

	}

	public static void Angledecis(ArrayList<Integer> Triangle_Length1_rec, ArrayList<Integer> Triangle_Length2_rec,
			ArrayList<Integer> Triangle_Length3_rec, double Triangle_Length1, double Triangle_Length2,
			double Triangle_Length3, ArrayList<Integer> time, ArrayList<Integer> area) { // method to calculate the angles
																						// and decides if the user's
																						// input is accurate or not
		double Triangle_Angle2 = Math
				.toDegrees(Math.acos((-Triangle_Length1 * Triangle_Length1 + Triangle_Length2 * Triangle_Length2
						+ Triangle_Length3 * Triangle_Length3) / (2 * Triangle_Length3 * Triangle_Length2))); // formulae to calculate a angle
																		
		double Triangle_Angle3 = Math
				.toDegrees(Math.acos((-Triangle_Length2 * Triangle_Length2 + Triangle_Length1 * Triangle_Length1
						+ Triangle_Length3 * Triangle_Length3) / (2 * Triangle_Length1 * Triangle_Length3)));
		double Triangle_Angle1 = Math
				.toDegrees(Math.acos((-Triangle_Length3 * Triangle_Length3 + Triangle_Length2 * Triangle_Length2
						+ Triangle_Length1 * Triangle_Length1) / (2 * Triangle_Length2 * Triangle_Length1)));
		System.out.println(Triangle_Angle1);

		System.out.println(Triangle_Angle2);

		System.out.println(Triangle_Angle3);
		System.out.println("This in Degrees"); // shows the user, the angles

		if (Triangle_Angle1 + Triangle_Angle2 + Triangle_Angle3 == 180) {
			Triangle_Length1_rec.add((int) Triangle_Length1);
			Triangle_Length2_rec.add((int) Triangle_Length2); // if all three angles add up to 180 degrees then it is
																// added to the arraylists and then the robot proceeds
																// to move in a triangle.
			Triangle_Length3_rec.add((int) Triangle_Length3);
			area.add((int) ((int)(363 * 0.1 * Triangle_Length1 + 1266 * (1 / 180) * Triangle_Angle1) + 363 * 0.1 * Triangle_Length2
					+ (int) (1266 * (1 / 180) * Triangle_Angle2) + (int) (363 * 0.1 * Triangle_Length3)
					+ 1266 * (1 / 180) * Triangle_Angle3));
			double Angle = Math.sin(Triangle_Angle1);
			time.add((int) ((int)(Triangle_Length1 * Triangle_Length2) / 2 * Angle));

			double[] lengths = {Triangle_Length1, Triangle_Length2, Triangle_Length3};
			double[] angles = {Triangle_Angle1, Triangle_Angle2, Triangle_Angle3};
			DrawingTriangle(lengths, angles);

		} else {
			Indicator_red();
			Triangle(Triangle_Length1_rec, Triangle_Length2_rec, Triangle_Length3_rec, area, time); // loops back to triangle method
																								

		}
	}

	public static void Pentagon(ArrayList<Integer> Pentagon_Length_rec, ArrayList<Integer> area,
			ArrayList<Integer> time) {
		Scanner in = new Scanner(System.in);
		int Pentagon_Length;

		System.out.println("Type in a number, within the ranges of 15-85cm:");
		Pentagon_Length = in.nextInt(); // user's makes a input between 15-85cm, or it will loop back

		if (15 <= Pentagon_Length && Pentagon_Length <= 85) {
			Pentagon_Length_rec.add((int) Pentagon_Length); // makes the robot move following a pentagon.
			area.add((int) ((int)5 * Pentagon_Length * Pentagon_Length / 0.727)); // adds to the arraylist
			time.add((int) ((int)5 * (363 * 0.1 * Pentagon_Length + 1266 * 0.4)));

			DrawingPentagon(Pentagon_Length);

		} else {
			Indicator_red();
			Pentagon(Pentagon_Length_rec, area, time);
		}

	}

	public static void DrawingSquare(int Square_Length)

	{
		

		try {
			for (int i = 0; i < 4; i++) { // for loop, to iterates two lines of code twice

				swiftbot.move(60, 67, (int) (363 * 0.1 * Square_Length));
				// caliberation to my swiftbot is 60 left velocity, 67 right velocity. the
				// milliseconds is converted into a formula.
				Thread.sleep(500);

				swiftbot.move(0, 90, (int) ((double) 1266 * 0.5)); // turns the swiftbot 90 degrees
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Indicator_green();// makes the swiftbot's underlights go green

	}

	public static void DrawingTriangle(double[] length, double[] angle) {
		for (int i = 0; i < 3; i++) //loops the code 3 times and alternates the lengths and angles with this array 
		{
			System.out.println(angle[i]);
			try {
				int time = (int) (363 * 0.1 * length[i]);
				swiftbot.move(60, 67, time);
				Thread.sleep(time);

				// straight 10cm (60,67.45,363), makes it to one cm then times it by the length

				time = (int) (1266 * ((180-angle[i]) / 180));
				System.out.println(time);
				swiftbot.move(0, 90, time);
				Thread.sleep(time);
			} catch (InterruptedException e) {
				System.out.println("There was an error while moving the robot.");
			}
			
		}
		
		Indicator_green();
	}

	public static void DrawingPentagon(double Pentagon_Length) {
		try {
			for (int i = 0; i < 5; i++) {//loops the code 5 times

				swiftbot.move((int) 60, (int) 67, (int) (363 * 0.1 * Pentagon_Length));
				// straight 10cm
				Thread.sleep(500);
				swiftbot.move(0, 90, (int) (1266 * 0.4)); // 180 degrees, needed angle 72 = *0.4
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Indicator_green();// makes the swiftbot's underlights go green
	}

	public static void Data_log(ArrayList<Integer> Triangle_Length1_rec, ArrayList<Integer> Pentagon_Length_rec,
			ArrayList<Integer> Triangle_Length2_rec, ArrayList<Integer> Triangle_Length3_rec,
			ArrayList<Integer> Square_Length_rec, ArrayList<String> Input_decision, ArrayList<Integer> area,
			ArrayList<Integer> time) {
		System.out.println("Inputs used to decide which operation is made:");
		System.out.println(Input_decision); // calls arraylist(all of them)
		System.out.println("Lengths of Squares being given by the user:");
		System.out.println(Square_Length_rec);
		System.out.println("1st Length of a Triangle being given by the user:");
		System.out.println(Triangle_Length1_rec);
		System.out.println("2nd Length of a Triangle being given by the user:");
		System.out.println(Triangle_Length2_rec);
		System.out.println("3rd Length of a Triangle being given by the user:");
		System.out.println(Triangle_Length3_rec);
		System.out.println("Lengths of a Pentagon being given by the user:");
		System.out.println(Pentagon_Length_rec);
		System.out.println("The areas of the shapes inputted from first to last:");
		System.out.println(area);
		System.out.println("The time, it took to make the shapes from first input to last:");
		System.out.println(time);
		int total = 0; // calculate average by adding all of the values through a for loop and dividing
						// it by the amount
		int average;
		for (int i = 0; i < time.size(); i++)

			total += time.get(i);
		average = total / time.size();
		System.out.println("The average time for making a shape is " + average);
		double avgspeed = 10/(363*(Math.pow(10, -3))); // it takes 363ms for the swiftbot to move 10cm.
		System.out.println("The average speed is "+ avgspeed);

	}

	public static void Quit(ArrayList<Integer> Triangle_Length1_rec, ArrayList<Integer> Pentagon_Length_rec,
			ArrayList<Integer> Triangle_Length2_rec, ArrayList<Integer> Triangle_Length3_rec,
			ArrayList<Integer> Square_Length_rec, ArrayList<String> Input_decision, ArrayList<Integer> area,
			ArrayList<Integer> time) {

		Scanner in = new Scanner(System.in);
		String decision = "";

		Data_log(Triangle_Length1_rec, Pentagon_Length_rec, Triangle_Length2_rec, Triangle_Length3_rec,
				Square_Length_rec, Input_decision, area, time);
		System.out.println("Do you wish to exit the program");

		decision = in.nextLine();
		if (decision.equals("yes")) {

			System.exit(0); // exit the program
		} else if (decision.equals("no")) {

		} else {
			System.out.println("Incorrect input");
			Quit(Triangle_Length1_rec, Pentagon_Length_rec, Triangle_Length2_rec, Triangle_Length3_rec,
					Square_Length_rec, Input_decision, area, time);
		}
	}

	public static void Indicator_green() {
		try {
			// turns all green lights on with max brightness
			swiftbot.fillUnderlights(0, 255, 0);
			Thread.sleep(1000);


			swiftbot.disableUnderlights(); // disables the lights
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Indicator_red() {

		try {
			// turns all red lights on with max brightness
			swiftbot.fillUnderlights(255, 0, 0);
			Thread.sleep(1000); //last for 1s
			swiftbot.disableUnderlights();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
