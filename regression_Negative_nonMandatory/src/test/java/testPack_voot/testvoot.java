package testPack_voot;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.RecordInputStream.LeftoverDataException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class testvoot extends GenericFunction {

	@Test
	public static void run_Function() throws FileNotFoundException, LeftoverDataException, EncryptedDocumentException,
			InvalidFormatException, InterruptedException {

		String android_path = "C:\\Users\\iFocus\\eclipse-workspace\\restAssuredFrameWork\\Input_Files\\PWA_Negative.xlsx";
		String plat_android = "Android";

		String ios_path = "C:\\Users\\iFocus\\eclipse-workspace\\restAssuredFrameWork\\Input_Files\\PWA_Negative.xlsx";
		String plat_ios = "iOS";

		
		//String web_path = "C:\\Users\\iFocus\\eclipse-workspace\\regression_Negative_nonMandatory\\Input_Files\\News_Web1_Test.xlsx";
		//C:\Program Files (x86)\Jenkins\workspace\test_automation\regression_Negative_nonMandatory\Input_Files\News_Web1_Test.xlsx
		String web_path = "C:\\Program Files (x86)\\Jenkins\\workspace\\test_automation\\regression_Negative_nonMandatory\\Input_Files\\News_Web1_Test.xlsx";
		String plat_web = "Web";

		String pwa_path = "C:\\Users\\iFocus\\eclipse-workspace\\regression_Negative_nonMandatory\\Input_Files\\PWA_Negative.xlsx";
		String platform_pwa = "Pwa";

		String path = web_path;
		String platform = plat_web;

		try {
			// FileInputStream fis1 = new FileInputStream(path);
			// Workbook wb1 = WorkbookFactory.create(fis1);
			try
			{
			Workbook wb1 = GenericFunction.readFile(path);
			}
			catch (IOException e) {
			e.printStackTrace();
			System.out.println("Sheet is Empty or Problem Accessing Sheet");
		}
				

			ArrayList<String> Sheetname = new ArrayList<String>();

			// for each sheet in the workbook
			try
			{
			int totalSheetcount=wb1.getNumberOfSheets();
			if (totalSheetcount!=0)
			{
				for (int i = 0; i < totalSheetcount; i++) {
					Sheetname.add(wb1.getSheetName(i));
				}
				ArrayList<String> All_Sheetnames = Sheetname;

				for (String EachSheetname : All_Sheetnames) {
					GenericFunction.compare(EachSheetname, path, platform);
				}
				Sheetname.clear();
			}
			else
			{
				System.out.println("Sheet is Empty or Problem Accessing Sheet");
			}
			}
			catch (Exception e) {
			e.printStackTrace();
		}
					

		} catch (IOException e) {
			e.printStackTrace();
		}
		  catch (NullPointerException e) {
			e.printStackTrace();
			  System.out.println("Sheet is Empty or Problem Accessing Sheet");
		}

	}

}
