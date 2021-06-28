package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo{
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String line;
		String callState = null;
		TestFunctions tf = new TestFunctions();
		tf.makeCall("9611270506");
		Thread.sleep(5000);
		Process p = Runtime.getRuntime().exec("adb shell \"dumpsys telephony.registry | grep mCallState\"");
		BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = bf.readLine()) != null) { 
			if(line.contains("mCallState")){
				System.out.println("line: "+line);
				callState = line.trim();
				break;
			}
			
			} 
		System.out.println("callState: "+callState);
	}
	
	public static String checkADBCallState() {
		String result = "";
		Process p;
		String line;
		String callState = null;
		try {
			p = Runtime.getRuntime().exec("adb shell \"dumpsys telephony.registry | grep mCallState\"");
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) { 
				System.out.println("line: "+line);
				callState = line.trim();
				} 
			System.out.println("callState: "+callState);
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
}

