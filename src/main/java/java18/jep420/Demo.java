package java18.jep420;

import static java.util.Calendar.*;

public class Demo {

	public static void main(String[] args) {

	}

	static String formatterOld(Object o) {
		String formatted = "unknown";
		if (o instanceof Integer) {
			Integer i = (Integer) o;
			formatted = String.format("int %d", i);
		} else if (o instanceof Long) {
			Long l = (Long) o;
			formatted = String.format("long %d", l);
		} else if (o instanceof Double) {
			Double d = (Double) o;
			formatted = String.format("double %f", d);
		} else if (o instanceof String) {
			String s = (String) o;
			formatted = String.format("String %s", s);
		}
		return formatted;
	}

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

	static void switchExample(int day) {

		switch (day) {
			case MONDAY:
			case FRIDAY:
			case SUNDAY:
				System.out.println(6);
				break;
			case TUESDAY:
				System.out.println(7);
				break;
			case THURSDAY:
			case SATURDAY:
				System.out.println(8);
				break;
			case WEDNESDAY:
				System.out.println(9);
				break;
		}

		int numLetters;
		switch (day) {
			case MONDAY:
			case FRIDAY:
			case SUNDAY:
				numLetters = 6;
				break;
			case TUESDAY:
				numLetters = 7;
				break;
			case THURSDAY:
			case SATURDAY:
				numLetters = 8;
				break;
			case WEDNESDAY:
				numLetters = 9;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + day);
		}
	}

	// java 14 JEP 361: Switch Expressions
	static void switchExpressions(int day) {

		switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
			case TUESDAY -> System.out.println(7);
			case THURSDAY, SATURDAY -> System.out.println(8);
			case WEDNESDAY -> System.out.println(9);
		}

		int numLetters = switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> 6;
			case TUESDAY -> 7;
			case THURSDAY, SATURDAY -> 8;
			case WEDNESDAY -> 9;
			default -> throw new IllegalStateException("Unexpected value: " + day);
		};
	}

	static String formatterPatternSwitch(Object o) {
		return switch (o) {
			case Integer i -> String.format("int %d", i);
			case Long l -> String.format("long %d", l);
			case Double d -> String.format("double %f", d);
			case String s -> String.format("String %s", s);
			default -> o.toString();
		};
	}

	static void testFooBar(String s) {
		if (s == null) {
			System.out.println("oops!");
			return;
		}
		switch (s) {
			case "Foo", "Bar" -> System.out.println("Great");
			default -> System.out.println("Ok");
		}
	}

	static void testFooBar2(String s) {
		switch (s) {
			case null -> System.out.println("Oops");
			case "Foo", "Bar" -> System.out.println("Great");
			default -> System.out.println("Ok");
		}
	}

	static void testStringOrNull(Object o) {
		switch (o) {
			case null, String s -> System.out.println("String: " + s);
			default -> System.out.println("Something else");
		}
	}

	// Case refinement

	class Shape {

	}
	class Rectangle extends Shape {

	}
	class Triangle extends Shape {

		int calculateArea() {
			return 0;
		}
	}

	static void testTriangle(Shape s) {
		switch (s) {
			case null:
				break;
			case Triangle t:
				if (t.calculateArea() > 100) {
					System.out.println("Large triangle");
					break;
				}
			default:
				System.out.println("A shape, possibly a small triangle");
		}
	}

	static void testTriangle2(Shape s) {
		switch (s) {
			case null -> {
				break;
			}
			case Triangle t when t.calculateArea() > 100 -> System.out.println("Large triangle");
			default -> System.out.println("A shape, possibly a small triangle");
		}
	}

	static void testTriangle3(Shape s) {
		switch (s) {
			case null -> {
				break;
			}
			case Triangle t when t.calculateArea() > 100 -> System.out.println("Large triangle");
			case Triangle t -> System.out.println("Small triangle");
			default -> System.out.println("Non-triangle");
		}
	}

}
