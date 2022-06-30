package googleAPI;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.GoogleAPIBody;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleAPITest {
	String postResponse = "", placeId = "", address = "London,UK", getResponse = "",actualAddress="";

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
	}

	@Test
	public void step01_AddPlace() {
		postResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(GoogleAPIBody.addPlaceBody()).when().post("maps/api/place/add/json").then().log().all()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract()
				.response().asString();
	}

	@Test(dependsOnMethods = "step01_AddPlace")
	public void step02_UpdatePlace() {
		JsonPath js = new JsonPath(postResponse);
		placeId = js.get("place_id");

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\": \"" + placeId + "\",\r\n" + "\"address\": \""+address+"\",\r\n"
						+ "\"key\": \"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
	}

	@Test(dependsOnMethods = "step02_UpdatePlace")
	public void step03_GetPlace() {
		getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js1 = new JsonPath(getResponse);
		actualAddress = js1.get("address");
	}
	
	@Test(dependsOnMethods="step03_GetPlace")
	public void step04_VerifyingAddress() {
		Assert.assertEquals(actualAddress, address);
	}
}
