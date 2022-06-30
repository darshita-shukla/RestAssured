package libraryAPI;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import payload.LibraryAPIBody;

import static io.restassured.RestAssured.*;


public class LibraryAPITest {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://216.10.245.166";
	}

	@Test(dataProvider = "BookData")
	public void step01_AddBook(String aisle, String isbn) {
		given().log().all().header("Content-Type", "application/json")
				.body(LibraryAPIBody.libraryAPIAddBody(aisle, isbn)).when().post("Library/Addbook.php").then().log()
				.all().assertThat().statusCode(200);

	}

	@DataProvider(name = "BookData")
	public Object[][] getData() {
		return new Object[][] { { "105", "aab" }, { "106", "bbc" }, { "107", "ccd" } };
	}
	
	@Test(priority=-1,dataProvider="BookData")
	public void step02_DeleteBook(String aisle, String isbn) {
		given().log().all().header("Content-Type", "application/json")
		.body(LibraryAPIBody.libraryAPIDeleteBody(aisle, isbn)).when().post("Library/DeleteBook.php").then().log()
		.all().assertThat().statusCode(200);
	}
}
