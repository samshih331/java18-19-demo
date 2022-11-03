package java18.jep413;

public class Demo {

	/*
	Summary
	Introduce an @snippet tag for JavaDoc's Standard Doclet, to simplify the inclusion of example source code in API documentation.

	generate javadoc
	 C:\Users\SamShihT14\.jdks\openjdk-19\bin\javadoc.exe .\Demo.java
	 */


	/**
	 * <pre>{@code
	 * int sum = widgets.stream()
	 * .filter(w -> w.getColor() == RED)
	 * .mapToInt(w -> w.getWeight())
	 * .sum();
	 * }</pre>
	 */
	public void test1() {

	}

	/**
	 * {@snippet :
	 * int sum = widgets.stream()
	 * .filter(w -> w.getColor() == RED)
	 * .mapToInt(w -> w.getWeight())
	 * .sum();
	 *}
	 */
	public void test2() {

	}

	/**
	 * The following code shows how to use {@code Optional.isPresent}:
	 * {@snippet :
	 * if (v.isPresent()) {
	 *     System.out.println("v: " + v.get());
	 * }
	 *}
	 */
	public void test3() {

	}

	/**
	 * A simple program.
	 * {@snippet :
	 * class HelloWorld {
	 *     public static void main(String... args) {
	 *         System.out.println("Hello World!");      // @highlight substring="println"
	 *     }
	 * }
	 *}
	 */
	public void test5() {

	}

	/**
	 * {@snippet :
	 *   public static void main(String... args) {
	 *       for (var arg : args) {                 // @highlight region regex = "\barg\b"
	 *           if (!arg.isBlank()) {
	 *               System.out.println(arg);
	 *           }
	 *       }                                      // @end
	 *   }
	 *}
	 */
	public void test6() {

	}

	/**
	 * A simple program.
	 * {@snippet :
	 * class HelloWorld {
	 *     public static void main(String... args) {
	 *         System.out.println("Hello World!");  // @replace regex='".*"' replacement="..."
	 *     }
	 * }
	 *}
	 */
	public void test7() {

	}

	/**
	 * A simple program.
	 * {@snippet :
	 * class HelloWorld {
	 *     public static void main(String... args) {
	 *         System.out.println("Hello World!");  // @link substring="System.out" target="System#out"
	 *     }
	 * }
	 *}
	 */
	public void test8() {

	}

	public static void main(String[] args) {

	}

}


