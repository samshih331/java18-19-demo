package java18.jep408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

public class Demo {

	/*
	Summary
	Provide a command-line tool to start a minimal web server that serves static files only.
	No CGI or servlet-like functionality is available. This tool will be useful for prototyping,
	ad-hoc coding, and testing purposes, particularly in educational contexts.
	 */

	public static void main(String[] args) throws IOException {

		/*
		With this class, a minimal yet customized server can be started in a few lines of code in jshell:
		 */

		HttpServer fileServer = SimpleFileServer.createFileServer(new InetSocketAddress(8080),
			Path.of("C:\\Users\\SamShihT14\\IdeaProject\\CPS\\CPS-H5\\dist"),
			SimpleFileServer.OutputLevel.VERBOSE);

		fileServer.start();

		/*
		A customized file-server handler can be added to an existing server:
		 */

		//		HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 10, "/store/", exchange -> {
		//			try {
		//				StringBuilder stringBuilder = new StringBuilder();
		//				stringBuilder.append("request method: ").append(exchange.getRequestMethod()).append("<br/>");
		//				stringBuilder.append("request param: ").append(getRequestParam(exchange)).append("<br/>");
		//				stringBuilder.append("request header: <br/> ").append(getRequestHeader(exchange)).append("<br/>");
		//				handleResponse(exchange, stringBuilder.toString());
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		});
		//
		//		HttpHandler fileHandler = SimpleFileServer.createFileHandler(
		//			Path.of("C:\\Users\\SamShihT14\\IdeaProject\\java18-19-demo\\src\\main\\resources"));
		//
		//		httpServer.createContext("/browse/", fileHandler);
		//		httpServer.start();

		/*
		A customized output filter can be added to a server during creation:
		 */

		//		Filter outputFilter = SimpleFileServer.createOutputFilter(System.out, SimpleFileServer.OutputLevel.INFO);
		//
		//		HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 10, "/store/", exchange -> {
		//			try {
		//				StringBuilder stringBuilder = new StringBuilder();
		//				stringBuilder.append("request method: ").append(exchange.getRequestMethod()).append("<br/>");
		//				stringBuilder.append("request param: ").append(getRequestParam(exchange)).append("<br/>");
		//				stringBuilder.append("request header: <br/> ").append(getRequestHeader(exchange)).append("<br/>");
		//				handleResponse(exchange, stringBuilder.toString());
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		}, outputFilter);
		//
		//		HttpHandler fileHandler = SimpleFileServer.createFileHandler(
		//			Path.of("C:\\Users\\SamShihT14\\IdeaProject\\java18-19-demo\\src\\main\\resources"));
		//
		//		httpServer.createContext("/browse/", fileHandler);
		//		httpServer.start();
	}

	private static void handleResponse(HttpExchange httpExchange, String respnseText) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<html>").append("<body>").append(respnseText).append("</body>").append("</html>");
		String s = stringBuilder.toString();
		byte[] bytes = s.getBytes(Charset.defaultCharset());

		httpExchange.getResponseHeaders().add("Content-Type:", "text/html=utf-8");

		httpExchange.sendResponseHeaders(200, bytes.length);

		OutputStream responseBody = httpExchange.getResponseBody();
		responseBody.write(bytes);
		responseBody.flush();
		responseBody.close();
	}

	private static String getRequestHeader(HttpExchange httpExchange) {
		Headers requestHeaders = httpExchange.getRequestHeaders();
		return requestHeaders.entrySet().stream()
			.map((Map.Entry<String, List<String>> entry) -> entry.getKey() + ":" + entry.getValue().toString())
			.collect(Collectors.joining("<br/>"));
	}

	private static String getRequestParam(HttpExchange httpExchange) throws Exception {
		String paramStr = "";

		if (httpExchange.getRequestMethod().equals("GET")) {
			paramStr = httpExchange.getRequestURI().getQuery();
		} else {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			paramStr = stringBuilder.toString();
		}
		return paramStr;
	}
}

