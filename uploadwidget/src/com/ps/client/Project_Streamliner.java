package com.ps.client;



import java.util.List;
import com.ps.client.Home;
import com.ps.client.BrowseProjects;
import com.ps.services.QueryService;
import com.ps.services.QueryServiceAsync;
import com.ps.tables.Employee;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Project_Streamliner implements EntryPoint {
	
	// The message displayed to the user when the server cannot be reached or returns an error.	 
	private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. Please check your network connection and try again.";

	
	// Create a remote service proxy to talk to the server-side Greeting service. 
	//private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public final QueryServiceAsync queryService = GWT.create(QueryService.class);
	
	// Create cards
	final static DockLayoutPanel browseProjectsCard = BrowseProjects.createBrowseProjectGUI();
	final static DockLayoutPanel homePage = Home.createHomeGUI();//new Home();
	final static DockLayoutPanel adminDashboardCard = AdminDashboard.createAdmindDashboard();
	final static DockLayoutPanel chapterDashboardCard = ChapterDashboard.createChapterDashboard();
	final static DockLayoutPanel loginCard = LoginCard.createLoginPanel();
	final static DeckPanel panelMain = new DeckPanel();
	final static Button sendButton2 = new Button("Send");
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		
		//System.out.print( panelMain.getLayoutData());
		//DockLayoutPanel browseProjectsCard = browseProjects.createBrowseProjectGUI();
		
		
		panelMain.add(homePage); //0
		panelMain.add(browseProjectsCard);//1
		panelMain.add(adminDashboardCard);//2
		panelMain.add(chapterDashboardCard);//3
		panelMain.add(loginCard); //4
		
		
		panelMain.setVisible(true);	
		//baz();
		listeners();
		RootLayoutPanel.get().add(panelMain);
		panelMain.showWidget(4);
		
		
		//homeCard.setVisible(true);
		//RootPanel.setVisible(true);
	}
	void baz(){
		final TextArea textArea = new TextArea();
		final FlexTable flexCurrentTable = new FlexTable();
		textArea.setText("test");


		queryService.queryServer("Smith", new AsyncCallback<List<Employee>>(){

			@Override
			public void onFailure(Throwable caught) {
				textArea.setText("x "+caught.getLocalizedMessage()+" x");
			}

			public void onSuccess(List<Employee> result) {
				// TODO Auto-generated method stub
				int row = 0;
				for (Employee employee : result) {
					flexCurrentTable.setText(row, 0, employee.getFirstName());			        
			        textArea.setText(  employee.getFirstName());
			        row++;
			      }
				
			}
		});
		flexCurrentTable.setVisible(true);
		textArea.setVisible(true);
		
		RootPanel.get().add(flexCurrentTable);
		RootPanel.get().add(textArea);
	}

	
	
	public void listeners(){
//		/*xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//		 * 					   Listeners									   *
//		 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx*/
//			link1.addClickListener(new ClickListener() {
//				public void onClick(Widget sender) {
//					right.clear();
//					right.add(one);
//					cache.put(link1.getTargetHistoryToken(), one);
//				}
//			});
	}

}
