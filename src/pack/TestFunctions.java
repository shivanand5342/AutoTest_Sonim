package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("unused")
public class TestFunctions {

	final static String dir = System.getProperty("user.dir");
	static Logger log= Logger.getLogger(TestFunctions.class);
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		PropertyConfigurator.configure(dir+"\\AutoDocs\\log4j.properties");
	

	}
	
		
	public static void reboot() throws IOException {
		//
		Process process = Runtime.getRuntime().exec("adb reboot");
		log.info("Device Rebooted");
		
	}
	
	public static void powerOFF() throws IOException {
		Process process = Runtime.getRuntime().exec("adb shell reboot -p");
		log.info("Device powered off");
		}
	
	public static void dataON() throws IOException {
		Process process = Runtime.getRuntime().exec("adb shell svc data enable");
		log.info("Device data ON");
	}
	
	public static void dataOFF() throws IOException {
		Process process = Runtime.getRuntime().exec("adb shell svc data disable");
		log.info("Device data OFF");
	}

	public static String swapString(String a, String b) {
		System.out.println("Befor swap: "+a+" "+b);
		a = a + b;  
		b = a.substring(0, a.length() - b.length());  
		a = a.substring(b.length());  
		System.out.println("After swap: "+a+" "+b);
		return a+","+b;       
	}

// to get DUT and Reference device serial numbers	
	public static String dutID() {
		String line,str = null;
		try {
			ArrayList<String> list=new ArrayList<String>();    	
			Process p = Runtime.getRuntime().exec("adb devices");
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = bf.readLine()) != null) {
				//System.out.print(line);
				if (line.contains("device") & !line.contains("devices")) {
					//str = line;
					list.add(line.substring(0,8));
					}
			   }
			if(list.size() == 2) {
			      str = list.get(list.size()-2)+","+list.get(list.size()-1);
			}
			else {
				  str= list.get(list.size()-1);
			     }
			}catch(Exception e) {}
		
		return str;
	}
	
//to collect adb logs
	public static void startADB(String adbFileName, int i) {
		String fileName = adbFileName+i;
		String line;
		try {
			Process p = Runtime.getRuntime().exec("cmd /c Start_Logcat.bat"+" "+fileName, null, new File(dir+"//"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) { System.out.println(line); } 
			}catch(Exception e) {}
	}
//to collect QXDM logs
	public void startQXDM() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("qxdm.exe");	
	    log.info("QXDM Launched");
	}
	
//to stop QXDM logs	
	public void endQXDM() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /f /im qxdm.exe");	
	    log.info("QXDM Launched");
	}

//To verify given two strings present in text file
	@SuppressWarnings("resource")
	public void findString(String filePath, String str1, String str2) {
		try {
			File file = new File(filePath);
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line...
		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		          lineNum++;
		        if(line.contains(str1) && line.contains(str2)) { 
		        	//System.out.println("i found it on line " +lineNum);
		            //System.out.println(line);
		            //System.out.println("***************************");
		        	System.out.println("pass");
		        	break;
		        } else if(scanner.hasNextLine() == false) {
		        	System.out.println("fail");
		        }
		      
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}

//to check the Call status	
	public String checkADBCallState() {
		String result = "";
		Process p;
		String line;
		String callState = null;
		try {
			p = Runtime.getRuntime().exec("adb shell \"dumpsys telephony.registry | grep mCallState\"");
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) { 
				if(line.contains("mCallState")){
					System.out.println("line: "+line);
					callState = line.trim();
					break;
				}
			} 
			System.out.println(callState);
			if(callState.equalsIgnoreCase("mCallState=2")) {
				result = "Pass";
				System.out.println("Pass");
			}else {
				result = "Fail";
				System.out.println("Fail");
			}		
		} catch (IOException e) {e.printStackTrace();}
		return result;
	}

//check successfull call state	
	@SuppressWarnings("resource")
	public String checkSuccessfulCallState(String filePath) throws FileNotFoundException {
        System.out.println("checkSuccessfulCallState");
		String res = null;
		File file = new File(filePath);
	    Scanner scanner = new Scanner(file);

	    //now read the file line by line...
	    int lineNum = 0;
	    int keyStringCount = 0;
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	          lineNum++;
	        if(line.contains("Telecom : CallsManager: setCallState DIALING -> ACTIVE")) { 
	        	//System.out.println("i found it on line " +lineNum);
	            //System.out.println(line);
	            //System.out.println("***************************");
	        	
	        	keyStringCount++;
	        	//System.out.println("Call Key String count is : " +keyStringCount);
	        	res="Pass";
	        	System.out.println("Pass");
	        	break;
	        } else if(scanner.hasNextLine() == false) {
	        	res="Fail";
	        	System.out.println("Fail");
	        }
	    }	
		return res;
	}

//Search String
@SuppressWarnings("resource")
public String searchString(String filePath, String searchString) {
	int lineNum = 0;
	String result="Fail";
	try {
		File file = new File(filePath);
		Scanner scanner = new Scanner(file);
		//now read the file line by line...
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNum++;
			if(line.contains(searchString)) { 
				System.out.println("String found in the line: " +lineNum);
				result="Pass";
			}
		}
		System.out.println("String presence satus: "+result);
	}catch(Exception e) {e.printStackTrace();}
	return result;
}

//to execute the jar/exe/batch/adb command

public void runExeJarAdb(String pathOfExe_Jar) {
	try {
		String line;
		Process p = Runtime.getRuntime().exec(pathOfExe_Jar);
		BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = bf.readLine()) != null) { 
			System.out.println(line); 
		} 
	}catch(Exception e) {e.printStackTrace();}	
}

//Establish a call to the given num
public void makeCall(String phoneNum) {
	try {
		Runtime.getRuntime().exec("adb shell am start -a android.intent.action.CALL -d tel:"+phoneNum);
	} catch (IOException e) {e.printStackTrace();}
	
}
public void terminateCall() {
	try {
		Runtime.getRuntime().exec("adb shell input keyevent \"KEYCODE_ENDCALL\"");
	} catch (IOException e) {e.printStackTrace();}
	
}

//Check Class state
	public static String callState() {
		PropertyConfigurator.configure(dir+"\\AutoDocs\\log4j.properties");
		String line;
		String str = null;
		String state="mCallState=0";
		int sum=0;
		try {
			Process p = Runtime.getRuntime().exec("adb shell \"dumpsys telephony.registry | grep mCallState\"");
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
			while ((line = bf.readLine()) != null) {
				//System.out.print(line);
				if (line.contains("mCallState")) {
					line = line.trim();
					sum = sum+Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
					}
			   }
			state = (sum == 0)? "Idle_State":"Ringing_Inprogress";
		}catch(Exception e) {log.error("To check Call Status Adb command not executed");			               }
		
		return state;
	}
}
