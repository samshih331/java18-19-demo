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
	public void beforeJava18() {
	}

	/**
	 * {@snippet :
	 * int sum = widgets.stream()
	 * .filter(w -> w.getColor() == RED)
	 * .mapToInt(w -> w.getWeight())
	 * .sum();
	 *}
	 */
	public void codeSnippets() {
	}

	/**
	 * The following code shows how to use {@code Optional.isPresent}:
	 * {@snippet :
	 * if (v.isPresent()) {
	 *     System.out.println("v: " + v.get());
	 * }
	 *}
	 */
	public void inlineSnippets() {
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
	public void highlighting() {

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
	public void highlighting2() {
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
	public void modifyingTheDisplayedText() {
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
	public void linkingText() {

	}

	public static void main(String[] args) {

	}

}


