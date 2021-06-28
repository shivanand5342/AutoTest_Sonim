package pack;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

@SuppressWarnings("unused")
public class GUI {

	static Logger log= Logger.getLogger(GUI.class);	
	final static String dir = System.getProperty("user.dir");
	public static String dutSerialNo, refSerialNo, dutMobNo, refMobNo, FolderLogsName;
	public static int iterationsNo, durationNo, gapBwCall;
	TestFunctions tf = new TestFunctions();
	protected Shell shell;
	/*
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	*/
	//private Text dutMob;
	private Text refMob;
	private Text dutSerial;
	private Text refSerial;
	private Text iterations;
	private Text duration;
	private Text gapBwCalls;
	//private Text resultFolderName;
	private Table table;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws IOException 
	 */
	public void open() throws IOException {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws IOException 
	 */
	protected void createContents() throws IOException {
		PropertyConfigurator.configure(dir+"\\AutoDocs\\log4j.properties");
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(0, 255, 255));
		shell.setSize(889, 547);
		shell.setText("Auto_Test");
		shell.setLayout(null);
		/*
		Label lblDutMdn = new Label(shell, SWT.NONE);
		lblDutMdn.setBounds(45, 57, 82, 15);
		lblDutMdn.setText("Dut Mob No.:");
		
		dutMob = new Text(shell, SWT.BORDER);
		dutMob.setBounds(192, 54, 291, 21);
		*/
		Label lblRefMdn = new Label(shell, SWT.BORDER | SWT.SHADOW_NONE);
		lblRefMdn.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblRefMdn.setAlignment(SWT.CENTER);
		lblRefMdn.setBackground(SWTResourceManager.getColor(0, 206, 209));
		lblRefMdn.setBounds(45, 42, 146, 31);
		lblRefMdn.setText("Ref Mob No:");
		lblRefMdn.setToolTipText("Enter 10 dirgit Reference device Mobile Number.");
		
		refMob = new Text(shell, SWT.BORDER);
		refMob.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		refMob.setBackground(SWTResourceManager.getColor(255, 222, 173));
		refMob.setBounds(213, 42, 508, 33);
//----------------------------------------------------------------------------	
		Label lblDutSerialNo = new Label(shell, SWT.BORDER);
		lblDutSerialNo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDutSerialNo.setAlignment(SWT.CENTER);
		lblDutSerialNo.setBackground(SWTResourceManager.getColor(0, 206, 209));
		lblDutSerialNo.setBounds(45, 88, 146, 31);
		lblDutSerialNo.setText("Dut Serial No:");
		lblDutSerialNo.setToolTipText("Click on Updated button to update DUT serial number.");
		
		dutSerial = new Text(shell, SWT.BORDER);
		dutSerial.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		dutSerial.setBackground(SWTResourceManager.getColor(255, 222, 173));
		dutSerial.setText("     ");
		dutSerial.setBounds(213, 86, 154, 33);
		
		Label lblRefSerialNo = new Label(shell, SWT.BORDER);
		lblRefSerialNo.setAlignment(SWT.CENTER);
		lblRefSerialNo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblRefSerialNo.setBackground(SWTResourceManager.getColor(0, 206, 209));
		lblRefSerialNo.setBounds(427, 88, 159, 31);
		lblRefSerialNo.setText("Ref Serial No.:");
		lblRefSerialNo.setToolTipText("Click on Updated button to update Reference device serial number.");
		
		refSerial = new Text(shell, SWT.BORDER);
		refSerial.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		refSerial.setBackground(SWTResourceManager.getColor(255, 222, 173));
		refSerial.setBounds(606, 86, 115, 34);
//setting the Serial Numbers
		Button serialUpdateButton = new Button(shell, SWT.NONE);
		serialUpdateButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		serialUpdateButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
//Clear both the serial text boxes
					dutSerial.setText("");
					refSerial.setText("");
					
					
					String str = tf.dutID();
					String[] str1=str.split(",");
					//String[] str1= {"serial1","serial2"};
					log.info("Number of devices: "+str1.length);
					
					//check the device count and set the serial numbers
					if(str1.length == 2) {
						dutSerial.setText(str1[0]);
						refSerial.setText(str1[1]);
					}
					else {
						dutSerial.setText(str1[0]);
					}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Devices are not connected properly!!! ", "Message", JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		serialUpdateButton.setBounds(749, 78, 98, 36);
		serialUpdateButton.setText("Update");
		
				
		Button btnSwapDut = new Button(shell, SWT.NONE);
		btnSwapDut.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		btnSwapDut.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {				
				String swapedString = tf.swapString(dutSerial.getText(), refSerial.getText());
				String[] str2=swapedString.split(",");
				if(str2[0].length() !=0 & str2[1].length()!=0) {
				dutSerial.setText(str2[0]);
				refSerial.setText(str2[1]);
				JOptionPane.showMessageDialog(null,"Devices Swaped!!! ", "Message", JOptionPane.INFORMATION_MESSAGE);
				}else {JOptionPane.showMessageDialog(null,"Single device connected!!! ", "Message", JOptionPane.INFORMATION_MESSAGE);}
				}catch(Exception e) {JOptionPane.showMessageDialog(null,"Check the serial numbers!!! ", "Message", JOptionPane.WARNING_MESSAGE);}
			}
		});
		btnSwapDut.setBounds(749, 120, 98, 35);
		btnSwapDut.setText("Swap DUT");
				
//----------------------------------------------------------------------------
		Label lblNewLabel = new Label(shell, SWT.BORDER);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(0, 206, 209));
		lblNewLabel.setBounds(45, 145, 146, 31);
		lblNewLabel.setText("Iterations:");
		lblNewLabel.setToolTipText("Enter the the number of iterations of the call");
		
		iterations = new Text(shell, SWT.BORDER);
		iterations.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		iterations.setBackground(SWTResourceManager.getColor(255, 222, 173));
		iterations.setText("1");
		iterations.setBounds(213, 143, 154, 33);
		
		Label lblNewLabel_1 = new Label(shell, SWT.BORDER);
		lblNewLabel_1.setToolTipText("Duration of the call.");
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(0, 206, 209));
		lblNewLabel_1.setBounds(427, 145, 159, 31);
		lblNewLabel_1.setText("Duration in Sec:");
		
		duration = new Text(shell, SWT.BORDER);
		duration.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		duration.setBackground(SWTResourceManager.getColor(255, 222, 173));
		duration.setText("60");
		duration.setBounds(606, 143, 115, 34);
		
		Label gapBtCalls = new Label(shell, SWT.NONE);
		gapBtCalls.setToolTipText("Gap between one call to another call.");
		gapBtCalls.setAlignment(SWT.CENTER);
		gapBtCalls.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		gapBtCalls.setBackground(SWTResourceManager.getColor(0, 206, 209));
		gapBtCalls.setText("Gap bw Calls in sec:");
		gapBtCalls.setBounds(49, 205, 142, 31);
		
		gapBwCalls = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		gapBwCalls.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		gapBwCalls.setBackground(SWTResourceManager.getColor(255, 222, 173));
		gapBwCalls.setText("10");
		gapBwCalls.setBounds(213, 202, 154, 34);
		/*
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(45, 264, 141, 15);
		lblNewLabel_2.setText("Result Logs Folder Name:");
		
		 resultFolderName = new Text(shell, SWT.BORDER);
		 resultFolderName.setBounds(192, 261, 291, 21);
		*/
		Button btnAirplaneMode = new Button(shell, SWT.NONE);
		btnAirplaneMode.setText("Airplane Mode");
		btnAirplaneMode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
								
				try {
					JOptionPane.showMessageDialog(null,"AP mode, Not Yet Implemented!!! ", "Message", JOptionPane.WARNING_MESSAGE);
				    } catch (Exception e1) {e1.printStackTrace();}
			}
		});
		btnAirplaneMode.setBounds(45, 256, 146, 36);
		
		Button btnDataOn = new Button(shell, SWT.NONE);
		btnDataOn.setText("Data ON");
		btnDataOn.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TestFunctions t=new TestFunctions();
				try {
					t.dataON();
				    } catch (IOException e1) {e1.printStackTrace();}
			}
		});
		btnDataOn.setBounds(212, 256, 119, 36);
		
		Button btnDataOff = new Button(shell, SWT.NONE);
		btnDataOff.setText("Data OFF");
		btnDataOff.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TestFunctions t=new TestFunctions();
				try {
					t.dataOFF();
				    } catch (IOException e1) {e1.printStackTrace();}
			}
		});
		btnDataOff.setBounds(351, 258, 109, 33);
		
		Button btnReboot = new Button(shell, SWT.NONE);
		btnReboot.setText("Reboot");
		btnReboot.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TestFunctions t=new TestFunctions();
				try {
					t.reboot();
				    } catch (IOException e1) {e1.printStackTrace();}
			}
		});
		btnReboot.setBounds(483, 256, 115, 36);
		
		Button btnPowerOff = new Button(shell, SWT.NONE);
		btnPowerOff.setText("Power OFF");
		btnPowerOff.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TestFunctions t=new TestFunctions();
				try {
					t.powerOFF();
				} catch (IOException e1) {e1.printStackTrace();}
			}
		});
		btnPowerOff.setBounds(627, 256, 92, 36);
/*		
//Table		
		table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		table.setBackground(SWTResourceManager.getColor(255, 222, 173));
		table.setLocation(45, 298);
	    table.setSize(286, 100);
//TestCases Check box
		TableItem tc1 = new TableItem(table, SWT.NONE);
		tc1.setText("TC1_MOCall");
		
		TableItem tc2 = new TableItem(table, SWT.NONE);
		tc2.setText("DemoTC");
*/		
//---------------------------------------------------------------------------------		
		Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);
		tree.setBounds(45, 298, 676, 149);
//Test Case List		
TreeItem TCL = new TreeItem(tree, SWT.NONE, 0);
TCL.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
TCL.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
TCL.setText("Test Case List");
		TreeItem tc1 = new TreeItem(TCL, SWT.NONE, 0);
		tc1.setText("TC1_MOCall");
		TreeItem tc2 = new TreeItem(TCL, SWT.NONE, 1);
		tc2.setText("DemoTC");
		  
//Monkey Scripts
TreeItem MSL = new TreeItem(tree, SWT.NONE, 1);
MSL.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
MSL.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
MSL.setText("Monkey Scripts");
		TreeItem All_App = new TreeItem(MSL, SWT.NONE, 0);
		All_App.setText("All_App");
		
		TreeItem Calculator = new TreeItem(MSL, SWT.NONE, 1);
		Calculator.setText("Calculator");
		
		TreeItem Camera = new TreeItem(MSL, SWT.NONE, 2);
		Camera.setText("Camera");
		
		TreeItem Chrome = new TreeItem(MSL, SWT.NONE, 3);
		Chrome.setText("Chrome");
		
		TreeItem Clock = new TreeItem(MSL, SWT.NONE, 4);
		Clock.setText("Clock");	
		
		TreeItem Contacts = new TreeItem(MSL, SWT.NONE, 5);
		Contacts.setText("Contacts");
		
		TreeItem Dialer = new TreeItem(MSL, SWT.NONE, 6);
		Dialer.setText("Dialer");
		
		TreeItem FileManager = new TreeItem(MSL, SWT.NONE, 7);
		FileManager.setText("FileManager");
		
		TreeItem FMradio = new TreeItem(MSL, SWT.NONE, 8);
		FMradio.setText("FMradio");
		
		TreeItem Launcher = new TreeItem(MSL, SWT.NONE, 9);
		Launcher.setText("Launcher");
		
		TreeItem Maps = new TreeItem(MSL, SWT.NONE, 10);
		Maps.setText("Maps");
		
		TreeItem Messaging = new TreeItem(MSL, SWT.NONE, 11);
		Messaging.setText("Messaging");
		
		TreeItem Music = new TreeItem(MSL, SWT.NONE, 12);
		Music.setText("Music");
		
		TreeItem Photos = new TreeItem(MSL, SWT.NONE, 13);
		Photos.setText("Photos");
		
		TreeItem Settings = new TreeItem(MSL, SWT.NONE, 14);
		Settings.setText("Settings");
		
		TreeItem Youtube_Music = new TreeItem(MSL, SWT.NONE, 15);
		Youtube_Music.setText("Youtube_Music");
		
//Soak Test		
TreeItem soakTest = new TreeItem(tree, SWT.NONE, 2);
soakTest.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
soakTest.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
soakTest.setText("Soak Test");
		TreeItem soakTC1 = new TreeItem(soakTest, SWT.NONE, 0);
		soakTC1.setText("SoakTC1");
		TreeItem soakTC2 = new TreeItem(soakTest, SWT.NONE, 1);
		soakTC2.setText("SoakTC2");
		TreeItem soakTC3 = new TreeItem(soakTest, SWT.NONE, 2);
		soakTC3.setText("SoakTC3");	
		TreeItem soakTC4 = new TreeItem(soakTest, SWT.NONE, 3);
		soakTC4.setText("SoakTC4");	
		TreeItem soakTC5 = new TreeItem(soakTest, SWT.NONE, 4);
		soakTC5.setText("SoakTC5");	
		
		
//---------------------------------------------------------------------------------		
//Start Button		
		Button btnStartExce= new Button(shell, SWT.NONE);
		btnStartExce.setText("Start Execution");
		btnStartExce.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
			     
					//dutMobNo = dutMob.getText();
					//log.info("The DUT num is :"+dutMobNo);
//Create Output Folder					
				    createOutputFolder();
					refMobNo = refMob.getText().trim();
					log.info("The REF num is :"+refMobNo);
					
					dutSerialNo = dutSerial.getText().trim();
					log.info("The DUT Serial num is :"+dutSerialNo);
					
					refSerialNo = refSerial.getText().trim();
					log.info("The REF Serial num is :"+refSerialNo);
					
					iterationsNo=Integer.parseInt(iterations.getText().trim());
					log.info("The iteration count is :"+iterationsNo);
						
					
					durationNo=Integer.parseInt(duration.getText().trim());
					log.info("The duration time is :"+durationNo);
				 
					gapBwCall=Integer.parseInt(gapBwCalls.getText());
					log.info("The Gap between the calls : "+gapBwCall);
					
			        //FolderLogsName = resultFolderName.getText();
			        //log.info("The Result Excel sheet name is: "+FolderLogsName);
//Test Cases			        
					if(tc1.getChecked() == true) { 
						new TC1_MoCallTest().moCallTest();
		    			log.info("***************************tc1 executed***************************");
		    		}
		    		if(tc2.getChecked() == true) {
		    			log.info("***********************Demo Script executed***********************");
		    		}
		    		
//Monkey Scripts
		    		MonkeyScripts monkeyScript = new MonkeyScripts();
		    		if(All_App.getChecked() == true) {
		    			monkeyScript.allComponentsMonkeyScript();
		    			log.info("***********************All_App Script executed***********************");
		    		}
		    		
		    		if(Calculator.getChecked() == true) {
		    			monkeyScript.calculatorMonkeyScript();
		    			log.info("***********************Calculator Script executed***********************");
		    		}
		    		
		    		if(Camera.getChecked() == true) {
		    			monkeyScript.cameraMonkeyScript();
		    			log.info("***********************Camera Script executed***********************");
		    		}
		    		
		    		if(Chrome.getChecked() == true) {
		    			monkeyScript.chormeBrowserMonkeyScript();
		    			log.info("***********************Chrome Script executed***********************");
		    		}
		    		if(Clock.getChecked() == true) {
		    			monkeyScript.clockMonkeyScript();
		    			log.info("***********************Clock Script executed***********************");
		    		}
		    		if(Contacts.getChecked() == true) {
		    			monkeyScript.contactsMonkeyScript();
		    			log.info("***********************Contacts Script executed***********************");
		    		}
		    		if(Dialer.getChecked() == true) {
		    			monkeyScript.dialerMonkeyScript();
		    			log.info("***********************Dialer Script executed***********************");
		    		}
		    		if(FileManager.getChecked() == true) {
		    			monkeyScript.FileManagerMonkeyScript();
		    			log.info("***********************File Manager Script executed***********************");
		    		}
		    		if(FMradio.getChecked() == true) {
		    			monkeyScript.FMradioMonkeyScript();
		    			log.info("***********************FM radio Script executed***********************");
		    		}
		    		if(Launcher.getChecked() == true) {
		    			monkeyScript.launcherMonkeyScript();
		    			log.info("***********************Launcher Script executed***********************");
		    		}
		    		if(Maps.getChecked() == true) {
		    			monkeyScript.mapsMonkeyScript();
		    			log.info("***********************Maps Script executed***********************");
		    		}
		    		if(Messaging.getChecked() == true) {
		    			monkeyScript.messagingMonkeyScript();
		    			log.info("***********************Messaging Script executed***********************");
		    		}
		    		if(Music.getChecked() == true) {
		    			monkeyScript.musicMonkeyScript();
		    			log.info("***********************Music Script executed***********************");
		    		}
		    		if(Photos.getChecked() == true) {
		    			monkeyScript.photosMonkeyScript();
		    			log.info("***********************Photos Script executed***********************");
		    		}
		    		if(Settings.getChecked() == true) {
		    			monkeyScript.settingsMonkeyScript();
		    			log.info("***********************Settings Script executed***********************");
		    		}
		    		if(Youtube_Music.getChecked() == true) {
		    			monkeyScript.youtubeMusicMonkeyScript();
		    			log.info("***********************Youtube Music Script executed***********************");
		    		}
		 }	
		});
		btnStartExce.setBounds(45, 453, 146, 45);	
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setVisible(true);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					//shell.close();
					Runtime.getRuntime().exec("taskkill /IM javaw.exe");
				} catch (IOException e) {e.printStackTrace();}
			}
		});
		btnNewButton.setBounds(601, 453, 120, 45);

		btnNewButton.setText("Close");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ResultsTable window=new ResultsTable();
				window.open();
			}
		});
		btnNewButton_1.setBounds(351, 453, 109, 45);
		btnNewButton_1.setText("Open Results");

	}
	
//Method to creates the Output Folder
static void createOutputFolder()
  {
	File outputFolder = new File("Output");
      if (!outputFolder.exists()) {
			outputFolder.mkdir();		
			log.info("Output Directory Created");
	  }else {
			log.info("Output Directory already exists!!!");
	  }	
  }
}
