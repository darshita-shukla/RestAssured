package jsonExercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import payload.JsonParseBody;

public class JsonParse {
		JsonPath js=new JsonPath(JsonParseBody.jsonParseBody());
	@Test
	public void step01_Print_Number_Of_Courses() {
		int actual=js.getInt("courses.size()");
		Assert.assertEquals(actual, 3);
		System.out.println(actual);
	}
	
	@Test
	public void step02_Print_Purchase_Amount() {
		int actual=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(actual, 910);
		System.out.println(actual);
	}
	
	@Test
	public void step03_Print_Title_Of_First_Course() {
		String actual=js.getString("courses[0].title");
		Assert.assertEquals(actual, "Selenium Python");
		System.out.println(actual);
	}
	
	@Test
	public void step04_Print_All_Courses_And_Titles() {
		int size=js.getInt("courses.size()");
		for(int i=0;i<size;i++) {
			System.out.println(js.getString("courses["+i+"].title"));
		}
	}
	
	@Test
	public void step05_Print_Number_Of_Copies_Sold_By_RPACourse() {
		int actual=js.getInt("courses[2].copies");
		Assert.assertEquals(actual, 10);
		System.out.println(actual);
	}
	
	@Test
	public void step06_Verify_Sum_Of_All_Prices_Matches_Purchase_Amount() {
		int sum=0;
		int size=js.getInt("courses.size()");
		for(int i=0;i<size;i++) {
			sum+=js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
		}
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
	}
}
