package java19.jep405;

public class Demo {

	// java 16 JEP 394: Pattern Matching for instanceof
	static String formatter(Object o) {
		String formatted = "unknown";
		if (o instanceof Integer i) {
			formatted = String.format("int %d", i);
		} else if (o instanceof Long l) {
			formatted = String.format("long %d", l);
		} else if (o instanceof Double d) {
			formatted = String.format("double %f", d);
		} else if (o instanceof String s) {
			formatted = String.format("String %s", s);
		}
		return formatted;
	}

	// java 16 JEP 395: Records
	record Point(int x, int y) {

	}

	static void printSum(Object o) {
		if (o instanceof Point p) {
			int x = p.x();
			int y = p.y();
			System.out.println(x + y);
		}
	}

	void printSum2(Object o) {
		if (o instanceof Point(int x,int y)) {
			System.out.println(x + y);
		}
	}

	enum Color {
		RED,
		GREEN,
		BLUE
	}

	record ColoredPoint(Point p, Color c) {

	}

	record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {

	}

	static void printUpperLeftColoredPoint(Rectangle r) {
		if (r instanceof Rectangle(ColoredPoint ul,ColoredPoint lr)) {
			System.out.println(ul.c());
		}
	}

	static void printColorOfUpperLeftPoint(Rectangle r) {
		if (r instanceof Rectangle(ColoredPoint(Point p,Color c),
			ColoredPoint lr)) {
			System.out.println(c);
		}
	}

	static void printXCoordOfUpperLeftPointWithPatterns(Rectangle r) {
		if (r instanceof Rectangle(ColoredPoint(Point(int x,int y),Color c),
			ColoredPoint lr)) {
			System.out.println("Upper-left corner: " + x);
		}
	}

	public static void main(String[] args) {
		Demo.printSum(new Point(2, 3));
		Rectangle rectangle = new Rectangle(new ColoredPoint(new Point(1, 2), Color.BLUE),
			new ColoredPoint(new Point(3, 4), Color.GREEN));
		Demo.printColorOfUpperLeftPoint(rectangle);

		Rectangle r = new Rectangle(new ColoredPoint(new Point(1, 2), Color.RED),
			new ColoredPoint(new Point(3, 4), Color.BLUE));
	}

}
