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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.config.EncoderConfig;
import io.restassured.path.json.JsonPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenericFunction {
	static Response UATresp1;
	static ValidatableResponse UATresp;
	static Response Prodresp1;
	static String UATResponse1;
	static String ProdResponse1;
	static Response UATresp2;
	static Response Prodresp2;
	static String UATResponse2;
	static String ProdResponse2;
	static Workbook commonwb;

	static SoftAssert softAssert = new SoftAssert();

	public static void compare(String sheetname, String path, String platform) throws EncryptedDocumentException,
			InvalidFormatException, IOException, InterruptedException, FileNotFoundException {
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		// FileInputStream fis = new FileInputStream(path);
		// Workbook wb = WorkbookFactory.create(fis);

		Workbook wb = GenericFunction.commonwb;
		Sheet sh = wb.getSheet(sheetname);

		// counting the no. of rows from sheet
		// int rowCount = sh.getLastRowNum()-sh.getFirstRowNum();
		int rowCount = sh.getPhysicalNumberOfRows();
		// started for loop
		try {
			for (int i = 1; i <= rowCount; i++) {
				Row row = sh.getRow(i);
				String UATurl = row.getCell(2).getStringCellValue();
				String Produrl = row.getCell(3).getStringCellValue();
				if (!(UATurl.isEmpty() && Produrl.isEmpty())) {
					if (platform.equals("Android") || platform.equals("iOS")) {
						// posting request for UAT
						BasicConfigurator.configure();
					//	UATresp1 = RestAssured.given().headers("OS", platform).relaxedHTTPSValidation()
					//			.contentType(ContentType.JSON).accept(ContentType.JSON).when().get(UATurl);
						UATresp=RestAssured.given().headers("OS", platform).relaxedHTTPSValidation()
						.contentType(ContentType.JSON).accept(ContentType.JSON).when().get(UATurl).then().assertThat().statusCode(200);
						UATresp1=(Response) UATresp;
						UATresp1.prettyPrint();
						UATResponse1 = UATresp1.asString();

						// posting request for PROD
						Prodresp1 = RestAssured.given().headers("OS", platform).relaxedHTTPSValidation()
								.contentType(ContentType.JSON).accept(ContentType.JSON).when().get(Produrl);
						// UATResponse1=UATresp1.prettyPrint().toString();
					}
					if (platform.equals("Web") || platform.equals("Pwa")) {
						// posting request for UAT
						BasicConfigurator.configure();
						UATresp1 = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
								.accept(ContentType.JSON).when().get(UATurl);

						// UATresp1.prettyPrint();
						// UATResponse1=UATresp1.asString();

						// posting request for PROD
						Prodresp1 = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
								.accept(ContentType.JSON).when().get(Produrl);
					}

					// Prodresp1.prettyPrint();

					UATResponse1 = UATresp1.prettyPrint().toString();

					ProdResponse1 = Prodresp1.prettyPrint().toString();

					softAssert.assertEquals(UATResponse1, ProdResponse1);
					softAssert.assertAll();

					// writing the status of json comparison and response of both UAT and PROD
					// FileInputStream fis1 = new FileInputStream(path);
					// Workbook wb1 = WorkbookFactory.create(fis1);

					Workbook wb1 = GenericFunction.commonwb;

					Sheet sh1 = wb1.getSheet(sheetname);

					Row row1 = sh1.getRow(i);
					row1.createCell(12);
					Cell cel1 = row1.getCell(12, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cel1.setCellType(CellType.STRING);
					if (UATResponse1.equals(ProdResponse1)) {
						cel1.setCellValue("");
						cel1.setCellValue("Pass");
					} else {
						cel1.setCellValue("");
						cel1.setCellValue("Fail");
					}

					Row row2 = sh1.getRow(i);
					row2.createCell(10);
					Cell cel2 = row1.getCell(10, MissingCellPolicy.CREATE_NULL_AS_BLANK);

					Row row3 = sh1.getRow(i);
					row3.createCell(11);
					Cell cel3 = row1.getCell(11, MissingCellPolicy.CREATE_NULL_AS_BLANK);

					try {

						cel2.setCellType(CellType.STRING);
						cel2.setCellValue("");
						cel2.setCellValue(UATresp1.asString());
					} catch (Exception e) {
						System.out.println(e);
						String cel2msg = e.getMessage();
						cel2.setCellValue("");
						cel2.setCellType(CellType.STRING);

						cel2.setCellValue(cel2msg);
					}

					try {
						cel3.setCellType(CellType.STRING);
						cel3.setCellValue("");
						cel3.setCellValue(Prodresp1.asString());
					} catch (Exception e) {
						System.out.println(e);
						String cel3msg = e.getMessage();
						cel3.setCellType(CellType.STRING);
						cel3.setCellValue("");
						cel3.setCellValue(cel3msg);
					}

					FileOutputStream fos = new FileOutputStream(path);
					wb1.write(fos);

					fos.close();
				} else {
					System.out.println("Break observed");
					break;
				}

			}

		} 
			catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public static Workbook readFile(String path) throws EncryptedDocumentException, IOException {
		try {
			FileInputStream fis1;
			fis1 = new FileInputStream(path);

			Workbook wb1 = WorkbookFactory.create(fis1);
			commonwb = wb1;
			return wb1;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
}
