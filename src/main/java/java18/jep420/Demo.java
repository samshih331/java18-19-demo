package java18.jep420;

public class Demo {

	public static void main(String[] args) {

	}

	// java 16  Pattern Matching for instanceof
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

	// java 14 Switch Expressions
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

	static void testFooBar19(String s) {
		switch (s) {
			case null -> System.out.println("Oops");
			case "Foo", "Bar" -> System.out.println("Great");
			default -> System.out.println("Ok");
		}
	}

}
