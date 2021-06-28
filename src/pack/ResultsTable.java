package pack;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ResultsTable {

	protected Shell shlReport;
	private Table table;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ResultsTable window = new ResultsTable();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlReport.open();
		shlReport.layout();
		while (!shlReport.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlReport = new Shell();
		shlReport.setBackground(SWTResourceManager.getColor(127, 255, 212));
		shlReport.setSize(690, 472);
		shlReport.setText("Report");
		shlReport.setLayout(null);
		
		table = new Table(shlReport, SWT.BORDER);
		table.setBackground(SWTResourceManager.getColor(255, 222, 173));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(48, 41, 590, 291);
		
		TableColumn column1 = new TableColumn(table, SWT.CENTER);
		column1.setWidth(210);
		column1.setText("Scenario and Iterations");
		
		TableColumn column2 = new TableColumn(table, SWT.CENTER);
		column2.setWidth(193);
		column2.setText("Logcat Result");
		
		TableColumn column3 = new TableColumn(table, SWT.CENTER);
		column3.setWidth(183);
		column3.setText("ADB Command Result");
		
		Button btnNewButton = new Button(shlReport, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.removeAll();
							
				ExcelFunctions excelfun=new ExcelFunctions();
				int colCount = excelfun.columnCount();
				
				if(colCount==0)
				{
					//JOptionPane.showMessageDialog(null,"There is no Data in Excel Sheet!!! ");
					JOptionPane.showMessageDialog(null,"There is no Data in Excel Sheet!!! ", "Message", JOptionPane.ERROR_MESSAGE);
				}
				
				//String[][] str=excelfun.csvData();
				String[][] str=excelfun.excelData();

				 for(int j=1;j<str[0].length;j++) { 
					 TableItem item = new TableItem(table, SWT.NONE);
					  for(int i=0;i<colCount;i++) { 
						//   
						  try {
							  item.setText(i,str[i][j]);
						  System.out.println(str[i][j]);
						  }catch(Exception e) {e.printStackTrace();}
						} 
					  }
				 	
				 
				 TableItem[] tableItems = table.getItems();			 
				 for(int i=0;i<table.getItemCount();i++)
					{
						for(int j=0;j<table.getColumnCount();j++)
						{
							//System.out.println(tableItems[i].getText(j));
						    if(tableItems[i].getText(j).equalsIgnoreCase("Fail")) {
						    	tableItems[i].setBackground(j, Display.getDefault().getSystemColor(SWT.COLOR_RED));//here because of j we can color only required cell.
						    	//tableItems[i].setBackground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
						    }
						/*
						 * else { tableItems[i].setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GREEN)); }
						 */
						}
					}
				 
				 
				 
				 
				 
				}
		});
		btnNewButton.setBounds(48, 360, 95, 43);
		btnNewButton.setText("Import");
		
		Button btnOpenExcel = new Button(shlReport, SWT.NONE);
		btnOpenExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ExcelFunctions ef=new ExcelFunctions();
				ef.openExcel();
			}
		});
		btnOpenExcel.setBounds(543, 360, 95, 43);
		btnOpenExcel.setText("Open Excel");
		
		Button clearTable = new Button(shlReport, SWT.NONE);
		clearTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.removeAll();
			}
		});
		clearTable.setBounds(318, 360, 95, 43);
		clearTable.setText("Clear Table");

	}
}
