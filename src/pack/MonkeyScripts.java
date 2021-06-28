package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MonkeyScripts {
	final static String dir = System.getProperty("user.dir");
	static TestFunctions tf=new TestFunctions();
	public static void main(String[] args) {
		
   MonkeyScripts ms=new MonkeyScripts();
   ms.allComponentsMonkeyScript();
	}

//Monkey Script for all the modules	
	void allComponentsMonkeyScript() {
		String line;
		try {
			System.out.println("All Component Monkey Script is Running...");
			Process p = Runtime.getRuntime().exec("cmd /c All_App_Monkey.bat", null, new File(dir+"//MonkeyScripts//"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) 
			      { 
				    System.out.println(line); 
				  } 
		
	      }catch(Exception e) {e.printStackTrace();}

	}
	
//Monkey Script for Calculator
void calculatorMonkeyScript() {
				String line;
				try {
					System.out.println("Calculator Script is Running...");
					Process p = Runtime.getRuntime().exec("cmd /c Calculator_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
					BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((line = bf.readLine()) != null) 
					      { 
						    System.out.println(line); 
						  } 
				
			      }catch(Exception e) {e.printStackTrace();}

}	
	
//Monkey Script for Camera	
	void cameraMonkeyScript() {
		String line;
		try {
			System.out.println("Camera Script is Running...");
			Process p = Runtime.getRuntime().exec("cmd /c Camera_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) 
			      { 
				    System.out.println(line); 
				  } 
		
	      }catch(Exception e) {e.printStackTrace();}
}	

//Monkey Script for Chrome Browser
	void chormeBrowserMonkeyScript() {
			String line;
			try {
				System.out.println("Chrome Browser Monkey Script is Running...");
				Process p = Runtime.getRuntime().exec("cmd /c Chrome_Monkey.bat", null, new File(dir+"//MonkeyScripts//"));
				BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = bf.readLine()) != null) 
				      { 
					    System.out.println(line); 
					  } 
			
		      }catch(Exception e) {e.printStackTrace();}

	}
	
//Monkey Script for Clock		
	void clockMonkeyScript() {
			String line;
			try {
				System.out.println("Clock Script is Running...");
				Process p = Runtime.getRuntime().exec("cmd /c Clock_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
				BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = bf.readLine()) != null) 
				      { 
					    System.out.println(line); 
					  } 
			
		      }catch(Exception e) {e.printStackTrace();}

	 }		

//Monkey Script for Contacts		
	void contactsMonkeyScript() {
				String line;
				try {
					System.out.println("Contacts Script is Running...");
					Process p = Runtime.getRuntime().exec("cmd /c Contacts_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
					BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((line = bf.readLine()) != null) 
					      { 
						    System.out.println(line); 
						  } 
				
			      }catch(Exception e) {e.printStackTrace();}

	}		
	
//Monkey Script for Contacts		
	void dialerMonkeyScript() {
				String line;
				try {
					System.out.println("Dialer Script is Running...");
					Process p = Runtime.getRuntime().exec("cmd /c Dialer_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
					BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((line = bf.readLine()) != null) 
					      { 
						    System.out.println(line); 
						  } 
				
			      }catch(Exception e) {e.printStackTrace();}

  }		

//Monkey Script for File Manager		
	void FileManagerMonkeyScript() {
				String line;
				try {
					System.out.println("File Manager Script is Running...");
					Process p = Runtime.getRuntime().exec("cmd /c FileManager_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
					BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((line = bf.readLine()) != null) 
					      { 
						    System.out.println(line); 
						  } 
				
			      }catch(Exception e) {e.printStackTrace();}

	}	

//Monkey Script for FM radio		
	void FMradioMonkeyScript() {
		String line;
		try {
			System.out.println("FM radio Script is Running...");
			Process p = Runtime.getRuntime().exec("cmd /c FMradio_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) 
			      { 
				    System.out.println(line); 
				  } 
		
	      }catch(Exception e) {e.printStackTrace();}

  }		
	
//Monkey Script for FM radio		
	void launcherMonkeyScript() {
			String line;
			try {
				System.out.println("Launcher Script is Running...");
				Process p = Runtime.getRuntime().exec("cmd /c Launcher_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
				BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = bf.readLine()) != null) 
				      { 
					    System.out.println(line); 
					  } 
			
		      }catch(Exception e) {e.printStackTrace();}

	 }		
	
//Monkey Script for Maps	
	void mapsMonkeyScript() {
		String line;
		try {
			System.out.println("Maps Script is Running...");
			Process p = Runtime.getRuntime().exec("cmd /c Maps_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) 
			      { 
				    System.out.println(line); 
				  } 
		
	      }catch(Exception e) {e.printStackTrace();}

  }

//Monkey Script for Messaging	
	void messagingMonkeyScript() {
		String line;
		try {
			System.out.println("Messaging Script is Running...");
			Process p = Runtime.getRuntime().exec("cmd /c Messaging_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
			BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = bf.readLine()) != null) 
			      { 
				    System.out.println(line); 
				  } 
		
	      }catch(Exception e) {e.printStackTrace();}

	}

//Monkey Script for Music	
  void musicMonkeyScript() {
			String line;
			try {
				System.out.println("Music Script is Running...");
				Process p = Runtime.getRuntime().exec("cmd /c Music_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
				BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = bf.readLine()) != null) 
				      { 
					    System.out.println(line); 
					  } 
			
		      }catch(Exception e) {e.printStackTrace();}

 }

//Monkey Script for Photos	
  void photosMonkeyScript() {
	String line;
	try {
		System.out.println("Photos Script is Running...");
		Process p = Runtime.getRuntime().exec("cmd /c Photos_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
		BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = bf.readLine()) != null) 
		      { 
			    System.out.println(line); 
			  } 
	
    }catch(Exception e) {e.printStackTrace();}

  }	

//Monkey Script for Settings	
  void settingsMonkeyScript() {
	String line;
	try {
		System.out.println("Settings Script is Running...");
		Process p = Runtime.getRuntime().exec("cmd /c Settings_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
		BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = bf.readLine()) != null) 
		      { 
			    System.out.println(line); 
			  } 
	
	}catch(Exception e) {e.printStackTrace();}

  }

//Monkey Script for Youtube Music	
  void youtubeMusicMonkeyScript() {
	String line;
	try {
		System.out.println("Youtube music Script is Running...");
		Process p = Runtime.getRuntime().exec("cmd /c Youtube_music_Monkey.bat", null, new File(dir+"//MonkeyScripts"));
		BufferedReader bf=new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = bf.readLine()) != null) 
		      { 
			    System.out.println(line); 
			  } 
	
	}catch(Exception e) {e.printStackTrace();}

  }  
  
}
