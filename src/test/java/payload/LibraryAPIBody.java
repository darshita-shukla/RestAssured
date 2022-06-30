package payload;

public class LibraryAPIBody {
	public static String libraryAPIAddBody(String aisle, String isbn) {
		return "{\r\n" + "\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n" + "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n" + "\"author\":\"John foe\"\r\n" + "}";
	}

	public static String libraryAPIDeleteBody(String aisle, String isbn) {
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+""+aisle+"\"\r\n"
				+ " \r\n"
				+ "} ";
	}
}
