package pack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@SuppressWarnings("unused")


public class TC1_MoCallTest extends Constants{

	final static String dir = System.getProperty("user.dir");
	static Logger log= Logger.getLogger(TC1_MoCallTest.class);
	static TestFunctions tf=new TestFunctions();
	
	public static void main(String[] args)  {
		
	}
	
//Auto call	
	
	public static void moCallTest(){
		PropertyConfigurator.configure(dir+"\\AutoDocs\\log4j.properties");
		try {
			
			
			TestFunctions tf=new TestFunctions();
					
		for(int iteration=1;iteration<=iterationsCount;iteration++) {//iterationsCount
			//clear the Qxdm logs
			Runtime.getRuntime().exec(dir+"\\AutoDocs\\QXDM_Alt_I_Press.exe");
			Thread.sleep(5000);
			tf.runExeJarAdb("adb logcat -c -b all");
			Process process = Runtime.getRuntime().exec("cmd /c start \"log_window\" /MIN cmd /c \"adb logcat -v threadtime -b all>"+dir+"\\Output\\CallTest"+iteration+".txt");
			
			tf.runExeJarAdb("adb shell input keyevent HOME");
			tf.runExeJarAdb("adb shell input keyevent CALL");
			
//sending command to establish a call
			tf.makeCall(refMobNum);
//			Wait until user input time
			Thread.sleep(5000);
			waitTill_CallDuration(callDuration);
       
//Check the call status through adb command and update in result sheet
			String adbCallStatus="Pass";
			if(new TestFunctions().callState().equals("Idle_State")) {
			   adbCallStatus="Fail";}
//End the call
			tf.runExeJarAdb("adb shell input keyevent \"KEYCODE_ENDCALL\" ");
			tf.runExeJarAdb("taskkill /IM cmd.exe");
			Thread.sleep(5000);
			
//Result excel sheet update			
			String callPassStatus = tf.searchString(dir+"\\Output\\CallTest"+iteration+".txt", "setCallState DIALING -> ACTIVE");
			ExcelFunctions ef=new ExcelFunctions();
			ef.createOutputFile();
			ef.inputResults("CallTest"+iteration, callPassStatus, adbCallStatus);
//Qxdm logs collection			
			if(iteration <= 3) {
				String path = dir+"D:\\Automaition\\CallTest"+iteration+".isf";
				String ctrl_i_exe = dir+"\\AutoDocs\\QXDM_Ctrl_I_Press.exe "+path;
				Runtime.getRuntime().exec(ctrl_i_exe);
			}
			else if(adbCallStatus.equalsIgnoreCase("Fail") | callPassStatus.equalsIgnoreCase("Fail")) {
				String path = dir+"D:\\Automaition\\CallTest"+iteration+".isf";
				String ctrl_i_exe = dir+"\\AutoDocs\\QXDM_Ctrl_I_Press.exe "+path;
				Runtime.getRuntime().exec(ctrl_i_exe);
			}
			
//Gap betweent the calls
		Thread.sleep(gapForCalls*1000);	
		System.out.println("Call count is : "+iteration);
		log.info(gapForCalls+" seconds break for the next call");
		}
		}catch(Exception e) {e.printStackTrace();}	
	}	
	
//Waits the call till given duration	
public static void waitTill_CallDuration(int callDurn) {
	int time=callDurn*1000;
	int timeDurn=0;
	int itr=time/5000;
	do {
		if(timeDurn !=0 & new TestFunctions().callState().equals("Idle_State")) {
			log.info("Call went to Idle State between "+(timeDurn-5)+" to "+timeDurn+" seconds");
			break;
		}else if(timeDurn == 0 & new TestFunctions().callState().equals("Idle_State")) {
			log.error("First Call not Established");
			break;
		}
		try {
			Thread.sleep(5000);
			} catch (Exception e) {e.printStackTrace();}
		timeDurn+=5;
	}while((timeDurn*1000)<=time);
	log.info("Call established around "+timeDurn+" seconds");
}
		
}

//:: 1 PhoNum, 2 Iteration, 3 CallDuration, 4 gapBwCalls, 5outputFolder