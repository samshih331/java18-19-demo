package java18.jep400_utf8_default;

import java.io.FileReader;

public class Demo {

	/*
	Summary
	Specify UTF-8 as the default charset of the standard Java APIs. With this change,
	APIs that depend upon the default charset will behave consistently across all implementations,
	operating systems, locales, and configurations.

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
		 java -classpath C:\Users\SamShihT14\IdeaProject\java18-19-demo\target\classes java18.jep400_utf8_default.Demo
		 jdk19
		 C:\Users\SamShihT14\.jdks\openjdk-19\bin\java.exe -classpath C:\Users\SamShihT14\IdeaProject\java18-19-demo\target\classes java18.jep400_utf8_default.Demo
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
