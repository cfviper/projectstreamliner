package com.ps.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.ps.client.Administrator.AssignProgram;
import com.ps.client.Administrator.ManageProjectManagers;
import com.ps.services.QueryService;
import com.ps.services.QueryServiceAsync;
import com.ps.shared.Global;
import com.ps.tables.Employee;
import com.ps.tables.Programs;
import com.ps.tables.Projects;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import common.PMF;


public class AdminDashboard {
	//private final static QueryServiceAsync queryService = GWT.create(QueryService.class);
	private final static QueryServiceAsync queryService = Global.getQueryService();
	final static MenuBar menuBar = new MenuBar(false);
	final static Label statsLabel = new Label();
	final static Label unassignedLabel = new Label();
	final static DeckPanel deckPanel = new DeckPanel();
	final static FormPanel formNewProgram = new FormPanel();
	static ArrayList<String> formAnswers = new ArrayList<String>();
	//static List<Date> visitList = null;
	//static ArrayList<Date> alVisitList = new ArrayList<Date>();
	static int theCount;
	static int outputCount;

	public static DockLayoutPanel createAdmindDashboard() {
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);

		MenuBar mbMenuBar = createMenuBar(menuBar);

		//statsLabel.setText(queryCountProjects() + " Active projects");
		//statsLabel.setText(queryPrograms() + " Active programs");
		statsLabel.setText("test statsLabel");
		
		// card 1
		DecoratedTabPanel decoratedTabPanel = new DecoratedTabPanel();
		decoratedTabPanel.add(newProgramQuestions(), "Overview");
		decoratedTabPanel.add(new DockLayoutPanel(Unit.EM), "522");
		decoratedTabPanel.setAnimationEnabled(true);
		decoratedTabPanel.selectTab(0);

		// card 2
		DockLayoutPanel waitingApproval = approvalMain();
		
		// card 3
		//CreateProjectManager createPM = new CreateProjectManager();
		//createPM.createNewManagerForm();

		// Main Cards
		deckPanel.add(statsLabel); // 0
		deckPanel.add(decoratedTabPanel);// 1
		deckPanel.add(waitingApproval);// 2

		// Support Cards
		deckPanel.add(AssignProgram.assignProgramCard());// card 3
		deckPanel.add(ManageProjectManagers.createNewManagerFormCard());// 4
		
		deckPanel.showWidget(2); // default card to show
		dockLayoutPanel.addNorth(mbMenuBar, 1.7);
		dockLayoutPanel.add(deckPanel);
		return dockLayoutPanel;
	}

	private static DockLayoutPanel approvalMain(){
		DockLayoutPanel waitingApprovalMain = new DockLayoutPanel(Unit.EM);
		Grid grid = new Grid(2, 3);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		//*******************************************************************/
		//****/ Create a table to layout the Review Mini Panel /****//
	    FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    layout.setHTML(0, 0, "Review Open Applications");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add some standard form options
	    layout.setHTML(1, 0, "Open projects");
	    layout.setWidget(1, 1, new TextBox());
	    layout.setHTML(2, 0, "Updates for open projects");
	    layout.setWidget(2, 1, new TextBox());
	    Label programsManaged = new Label();
	    queryPrograms("True");
	    programsManaged.setText(queryPrograms("True")+" : "+outputCount);
	    layout.setWidget(3, 0, programsManaged);
	    
	    // Wrap the content in a DecoratorPanel
	    DecoratorPanel reviewMiniPanel = new DecoratorPanel();
	    reviewMiniPanel.setWidget(layout);
	    
	    //*******************************************************************/	    
	    //****/ Create a table to layout the Assign project mini panel /****//
	    FlexTable assignMiniLayout = new FlexTable();
	    assignMiniLayout.setCellSpacing(6);
	    FlexCellFormatter assignCellFormatter = assignMiniLayout.getFlexCellFormatter();
	    
	    //Create buttons for Assign Panel
	    Button asignPM = new Button("assign PM", new ClickHandler() {	
			public void onClick(ClickEvent event) {
				deckPanel.showWidget(3);
			}
		});
	    
	    // Add a title to the form
	    assignMiniLayout.setHTML(0, 0, "Assign Application");
	    assignCellFormatter.setColSpan(0, 0, 2);
	    assignCellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add some standard form options
	    assignMiniLayout.setHTML(1, 0, "Chapter programs/projects awaiting approval from PM: ");
	    assignMiniLayout.setWidget(1, 1, unassignedLabel);
	    assignMiniLayout.setWidget(1, 2, asignPM);
	    assignMiniLayout.setHTML(2, 0, "Community programs in adoption queue");
	    assignMiniLayout.setWidget(2, 1, new TextBox());
	    assignMiniLayout.setHTML(3, 0, "Community suggested programs waiting for approval");
	    assignMiniLayout.setWidget(3, 1, new TextBox());

	    // Wrap the content in a DecoratorPanel
	    DecoratorPanel assignMiniPanel = new DecoratorPanel();
	    assignMiniPanel.setWidget(assignMiniLayout);
	  //*******************************************************************/
	    
	    unassignedLabel.setText("foo");
	    grid.setWidget(0, 0, reviewMiniPanel);
	    grid.setWidget(0, 2, assignMiniPanel);
	    //grid.setWidget(1, 2, unassignedLabel); // new label(text)
	    //int qUP = queryUnassignedProjects(); // still has issues 
	    
	    vPanel.add(grid);
	    hPanel.add(vPanel);
	    //This section need to be fixed later to center mini panels, and not rely on fixed regions sizes (N,S,E,W)
	    
	    waitingApprovalMain.addNorth(new Label(), 10);
	    waitingApprovalMain.addWest(new Label(), 10);
	    waitingApprovalMain.add(hPanel);
	    
	    //waitingApprovalMain.add(new Label(":qUP:"+qUP));
	    //waitingApprovalMain.add(new Label("            :qP:"+qP));
		return waitingApprovalMain;
	}
	
	private static MenuBar createMenuBar(MenuBar menuBar) {

		MenuBar file = new MenuBar(true);
		file.addItem("New Program", new Command() {
			public void execute() {
				deckPanel.showWidget(1);
			}
		});
		file.addItem("New Project", new Command() {
			public void execute() {
			}
		});
		file.addSeparator();
		file.addItem("Open Program", new Command() {
			public void execute() {
			}
		});
		file.addItem("Open Project", new Command() {
			public void execute() {
			}
		});
		// ****************************** //
		MenuBar attachFile = new MenuBar(true);
		// add commands here

		MenuBar testing = new MenuBar(true);
		testing.addItem("Public", new Command() {
			public void execute() {
				Project_Streamliner.panelMain.showWidget(1);
			}
		});
		testing.addItem("Chapter", new Command() {
			public void execute() {
				Project_Streamliner.panelMain.showWidget(3);
			}
		});
		testing.addItem("Admin Main", new Command() {
			public void execute() {
				Project_Streamliner.panelMain.showWidget(2);
			}
		});
		testing.addItem("Admin Manager", new Command() {
			public void execute() {
				Project_Streamliner.panelMain.showWidget(2);
			}
		});
		// ********************************** //
		MenuBar adminMenu = new MenuBar(true);
		
		adminMenu.addItem("Stats", new Command() {
			public void execute() {
				AdminDashboard.deckPanel.showWidget(0);
			}
		});
		adminMenu.addItem("Create Project Manager", new Command() {
			public void execute() {
				AdminDashboard.deckPanel.showWidget(4); 
			}
		});
		// ******************************** //
		
		menuBar.addItem("Admin Menu", adminMenu);
		menuBar.addItem("Program Entry", file);
		menuBar.addItem("Attach File", attachFile);
		menuBar.addSeparator();
		menuBar.addItem("Testing", testing);
		return menuBar;
	}

	private static FormPanel newProgramQuestions() {
		VerticalPanel formHolder = new VerticalPanel();
		
		formHolder.add(new Label("Program Name"));
		final TextBox tbProgramName = new TextBox();
		tbProgramName.setName("tbProgramName");
		formHolder.add(tbProgramName);

		formHolder.add(new Label("Initial Project"));
		final TextBox tbStarterProject = new TextBox();
		tbStarterProject.setName("tbStarterProject");
		formHolder.add(tbStarterProject);

		formHolder.add(new Label("startDate"));
		final DateBox tbStartDate = new DateBox();
		tbStartDate.setValue(new Date());
		formHolder.add(tbStartDate);

		formHolder.add(new Label("returnVisitDate"));
		final DateBox tbReturnVisitDate = new DateBox();
		tbReturnVisitDate.setValue(new Date());
		formHolder.add(tbReturnVisitDate);

		formHolder.add(new Label("programAdopted"));
		final TextBox tbProgramAdopted = new TextBox();
		tbProgramAdopted.setName("tbProgramAdopted");
		formHolder.add(tbProgramAdopted);

		
		
		Button submitButton = new Button("Submit", new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//alVisitList.add(tbReturnVisitDate.getValue());  // This should be moved to update program, and should query selected project for insert
				saveForm(tbProgramName.getText(), tbStarterProject.getText(),tbStartDate.getValue(), tbReturnVisitDate.getValue()  , tbProgramAdopted.getText() );
			}
		});
		submitButton.ensureDebugId("cwBasicButton-normal");
		formHolder.add(submitButton);

		formNewProgram.add(formHolder);

		return formNewProgram;
	}

	private static int queryCountProjects() {
		queryService.queryServer("Smith", new AsyncCallback<List<Employee>>() {
			int countCurrentProjects = 0;

			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
				errorPopup.add(new HTML("x " + caught.getLocalizedMessage() + " x"));
				statsLabel.setText("failed");
			}

			public void onSuccess(List<Employee> result) {
				countCurrentProjects = 0;
				for (Employee employee : result) {
					employee.getFirstName();
					countCurrentProjects++;
					statsLabel.setText("Active projects:: " + countCurrentProjects);
					statsLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				}
				theCount = countCurrentProjects;
			}

		});

		return theCount;
	}

	private static int queryUnassignedProjects() {
		queryService.queryUnassignedProjects("yes", new AsyncCallback<List<Projects>>() {
			int countUnassignedProjects = 1;

			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
				errorPopup.add(new HTML("x " + caught.getLocalizedMessage() + " x"));
				unassignedLabel.setText("unasigned failed");
			}

			public void onSuccess(List<Projects> result) {
				//countUnassignedProjects = 0;
				for (Projects projects : result) {
					projects.getProgramAdopted();
					countUnassignedProjects++;
					unassignedLabel.setText("un: "+countUnassignedProjects);
				}
				outputCount = countUnassignedProjects+1;
			}
		});	
		return outputCount;
	}
	
	private static int queryPrograms(String parameter) {
		//saveProgram("foo","baz",new Date(),false);
		
		queryService.queryPrograms(parameter, new AsyncCallback<List<Programs>>() {
			int countPrograms = 1;
			
			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
				errorPopup.add(new HTML("x " + caught.getLocalizedMessage() + " x"));
				unassignedLabel.setText("program unasigned failed");
			}

			public void onSuccess(List<Programs> result) {
				//countUnassignedProjects = 0;
				for (Programs programs : result) {
					programs.getAdopted();
					countPrograms++;
					unassignedLabel.setText("blah"+countPrograms);
				}
				outputCount = countPrograms+1;
				unassignedLabel.setText("("+outputCount+")");
			}
			
		});
		
		//outputCount = 1;
		return outputCount;
	}
	
	private static void saveForm(String programTitle, String projectTitle, Date startDate, Date returnVisitDate, String programadopted) throws IllegalArgumentException{
		//formAnswers.add(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
		
		queryService.addProject( programTitle, projectTitle,startDate, returnVisitDate, programadopted, new AsyncCallback<Void>(){

			
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
				simplePopup.setWidget(new HTML("x "+caught.getLocalizedMessage()+" x"));
				Window.alert("Fail");
			}

			
			public void onSuccess(Void result) {
				Window.alert("Submitted successfully");
				
			}
		});
		
	}

	private static void saveProgram(String programName, String locationName, Date startDate, Boolean adopted) throws IllegalArgumentException{
		programName = "foo";
		locationName= "baz";
		startDate = new Date();
		adopted = false;
		queryService.addProgram(programName, locationName,startDate, adopted, new AsyncCallback<Void>(){
		
			public void onFailure(Throwable caught) {
			final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
			simplePopup.setWidget(new HTML("x "+caught.getLocalizedMessage()+" x"));
			Window.alert("Fail");
			}

			public void onSuccess(Void result) {
				Window.alert("Submitted successfully");	
			}
		});
	}
	
}