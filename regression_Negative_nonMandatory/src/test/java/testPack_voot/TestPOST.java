package testPack_voot;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.apache.log4j.BasicConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;

@Test
public class TestPOST {
	static Response UATResp;
	static Response ProdResp;
	static Response PostResponse;
	static Response TestResp;
//	static SoftAssert softAssert = new SoftAssert();

	
	  
	public static void postmethodCheck() {
		BasicConfigurator.configure();
		
	/*	Message message=new message();
		message.setMessage("My messagee");
		RestAssured.given().
		       contentType("application/json; charset=UTF-16").
		       formParam("param1", message).
		when().
		      post("/message");
		
		*/
		
		
	

		// ArrayList<String> body;
		String body = "username:testvoot101@gmail.com\r\n" + "password:123456\r\n"
				+ "UDID:1B22219F-4373-394D-B6D8-FD15E98664DD\r\n" + "deviceBrand:HTC";
		
		//String body = "username","testvoot101@gmail.com\r\n" + "password:123456\r\n"
	//			+ "UDID:1B22219F-4373-394D-B6D8-FD15E98664DD\r\n" + "deviceBrand:HTC";
		
		Map<String, Object> bodyparams = new HashMap<String, Object>();
		// String test = "pet:cat::car:honda::location:Japan::food:sushi";

		// ArrayList<String> s3 = new ArrayList<String>();
		// split on ':' and on '::'
		String[] parts = body.split("\r\n");

		String abc = String.join(":", parts);

		String[] updatedbody = abc.split(":");
		// String str = String.join(",", arr);

		// for(String s : parts) {
		// String[] s2 = s.split(":");
		// s3.add(s2);
		// for(String results : s2) {
		// System.out.println(results);

		// }
		// }
	
		bodyparams.put("username", "testvoot234@mailinator.com");
		bodyparams.put("password", "123456");
		bodyparams.put("UDID", "1B22219F-4373-394D-B6D8-FD15E98664DD");
		bodyparams.put("deviceBrand", "HTC");
		
	//	RestAssured.config = RestAssured.config().encoderConfig(
	//			EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.BINARY));
		
		byte[] byteArr = abc.getBytes();
		
		
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(byteArr);
		builder.setContentType("multipart/form-data; charset=UTF-8");
		
		
		
		
		
		RequestSpecification requestSpec = builder.build();
		
		String url="https://auth.voot.com/wsv_2_0/user/authentication.json?platform=Cellular&pId=1";
		
		TestResp = RestAssured.given().spec(requestSpec).when().post(url);
		
		TestResp.prettyPrint();
		// for each sheet in the workbook

		// for (int i = 1; i <= updatedbody.length; i += 2) {
		// bodyparams.put(updatedbody[i+1], updatedbody[i + 2]);
		// }

		// for (String s : bodyparams.keySet()) {
		// System.out.println(s + " is " + bodyparams.get(s));
		// }

	//	PostResponse = 	RestAssured.given().
	//					multiPart("username", "testvoot234@mailinator.com").
	//					contentType("multipart/form-data").
	//					body(bodyparams).
	//					when().
	//					post("https://auth.voot.com/wsv_2_0/user/authentication.json?platform=Cellular&pId=1");

	//	PostResponse.prettyPrint();

		/*
		 * 
		 * //System.out.println(body); UATResp =
		 * RestAssured.given().accept(ContentType.URLENC)
		 * .params("username","testvoot101@gmail.com","password","123456") .when().post(
		 * "https://authdev.voot.com/wsv_2_0/user/authentication.json?platform=Cellular&pId=1"
		 * ); UATResp.prettyPrint();
		 * 
		 * 
		 * String Passwordexpiry=UATResp.jsonPath().get("Email[0].Value");
		 * System.out.println(Passwordexpiry);
		 * 
		 * ProdResp =
		 * RestAssured.given().accept(ContentType.JSON).when().param("username",
		 * "testvoot101@gmail.com") .param("password", "123456") .post(
		 * "https://auth.voot.com/wsv_2_0/user/authentication.json?platform=Cellular&pId=1"
		 * ); ProdResp.prettyPrint();
		 * 
		 * softAssert.assertEquals(UATResp, ProdResp);
		 * 
		 * if (UATResp.equals(ProdResp)) { System.out.println("Test Pass"); } else
		 * System.out.println("Test Fail");
		 */
		
		
	}

	public class Message {
	    private String message;

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
	}
	
}
