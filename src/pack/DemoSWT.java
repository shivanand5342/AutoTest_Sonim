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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.custom.TableCursor;

public class DemoSWT {

	protected Shell shlReport;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DemoSWT window = new DemoSWT();
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
		
		Tree tree = new Tree(shlReport, SWT.BORDER | SWT.CHECK);
		tree.setBounds(85, 87, 491, 193);
		  
		TreeItem TCL = new TreeItem(tree, SWT.NONE, 0);
		  TCL.setText("Test Case List");
		TreeItem item1a = new TreeItem(TCL, SWT.NONE, 0);
		  item1a.setText("Test Case 1");
		TreeItem item1b = new TreeItem(TCL, SWT.NONE, 1);
		  item1b.setText("Test Case 2");

		TreeItem MSL = new TreeItem(tree, SWT.NONE, 1);
		MSL.setText("Monkey Scripts");
        TreeItem item2a = new TreeItem(MSL, SWT.NONE, 0);
		  item2a.setText("Script 1");
		TreeItem item2a1 = new TreeItem(MSL, SWT.NONE, 1);
		  item2a1.setText("Script 2");
		  
		  Button btnStart = new Button(shlReport, SWT.NONE);
		  btnStart.addSelectionListener(new SelectionAdapter() {
		  	@Override
		  	public void widgetSelected(SelectionEvent arg0) {
		  		if(item1a.getChecked()==true) {
		  			System.out.println("test case 1 cecked");
		  		}
		  		else {
		  		System.out.println("test case 1 not checked");
		  		}
		  			
		  	}
		  });
		  btnStart.setBounds(85, 359, 75, 25);
		  btnStart.setText("Start");
		  	
		 
		 

	}
}
