package java18.jep400;

import java.io.FileReader;

public class Demo {

	/*
	Specify UTF-8 as the default charset of the standard Java APIs. With this change,
	APIs that depend upon the default charset will behave consistently across all implementations,
	operating systems, locales, and configurations.

	In JDK 17 and earlier, the default charset is determined when the Java runtime starts. On macOS,
	it is UTF-8 except in the POSIX C locale. On other operating systems,
	it depends upon the user's locale and the default encoding, e.g.,
	on Windows, it is a codepage-based charset such as windows-1252 or windows-31j.

	Make Java programs more predictable and portable when their code relies on the default charset.
	Several standard Java APIs use the default charset, including:

	In the java.io package, InputStreamReader, FileReader, OutputStreamWriter, FileWriter,
	and PrintStream define constructors to create readers, writers,
	and print streams that encode or decode using the default charset.

	In the java.util package, Formatter and Scanner define constructors whose results use the default charset.

	In the java.net package, URLEncoder and URLDecoder define deprecated methods that use the default charset.
	 */

	public static void main(String[] args) {

		/*
		 jdk8
		 cd C:\Users\SamShihT14\IdeaProject\java18-19-demo\src\main\java\java18\jep400
		 javac .\Demo.java
		 cd C:\Users\SamShihT14\IdeaProject\java18-19-demo\src\main\java
		 java java18.jep400.Demo
		 jdk19
		 run this program
		 */

		try (FileReader fileReader = new FileReader(
			"C:\\Users\\SamShihT14\\IdeaProject\\java18-19-demo\\src\\main\\resources\\encoding.txt")) {
			System.out.println(fileReader.getEncoding());

			int z;
			while ((z = fileReader.read()) != -1) {
				System.out.print((char) z);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
