package com.ps.client;

import java.util.Date;
import java.util.List;
import com.ps.services.QueryService;
import com.ps.services.QueryServiceAsync;
import com.ps.tables.Projects;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class ChapterDashboard {
	private final static QueryServiceAsync queryService = GWT.create(QueryService.class);
	final static DeckPanel deckChapterDashboard = new DeckPanel();
	final static Grid gChapterMenu = new Grid(2,4);
	final static DecoratorPanel deckDecoratedPanel = new DecoratorPanel();
	static String deckWidth = "85em";
	static String deckHeight = "45em";
	
	
	
	public static DockLayoutPanel createChapterDashboard() {
		DockLayoutPanel dockChapterDashboard = new DockLayoutPanel(Unit.EM);
		VerticalPanel vpDashboardLeftPanel = new VerticalPanel();
		vpDashboardLeftPanel.add(new Label("world"));
		VerticalPanel vpWeeklyNews = new VerticalPanel();
		vpWeeklyNews.add(new Label("News"));
		
		// Build DeckPanel
		deckChapterDashboard.add(createDashboardMenu()); //0
		deckChapterDashboard.add(createStartNewProgram());//1
		
		
		
		deckChapterDashboard.showWidget(0);
		Label title = new Label("title");
		
		dockChapterDashboard.addNorth(title, 1.7);
		dockChapterDashboard.addSouth(new Label("footer"), 1.7);
		dockChapterDashboard.addEast(vpWeeklyNews,2);
		dockChapterDashboard.addWest(vpDashboardLeftPanel, 10);
		dockChapterDashboard.add(deckChapterDashboard);
		
		
		return dockChapterDashboard;
	}
	
	public static DockLayoutPanel createDashboardMenu(){
		DockLayoutPanel dlpDashboardMenu = new DockLayoutPanel(Unit.EM);
		DecoratorPanel decoratedDashboardMenu = new DecoratorPanel();
		
		
		// Build Button Menu
		Button startNewProgram = new Button("Start New Program");
		Button startNewProject = new Button("Start New Project");
		Button updateProgram = new Button("Update Program");
		Button updateProject = new Button("Update Project");
		Button findMentor = new Button("Find Mentor");
		Button registerChapterMembers = new Button("View Chapter Members");
		Button viewProjectList = new Button("View Project List");
		Button uploadFiles = new Button("Upload Files");// docs/youtube/picasa  (separate buttons, new screen with 3 buttons, disclosure panel)
		gChapterMenu.setWidget(0, 0, startNewProgram);
		gChapterMenu.setWidget(0, 1,startNewProject);
		gChapterMenu.setWidget(0, 2,updateProgram);
		gChapterMenu.setWidget(0, 3,updateProject);
		gChapterMenu.setWidget(1, 0,findMentor);
		gChapterMenu.setWidget(1, 1,registerChapterMembers);
		gChapterMenu.setWidget(1, 2,viewProjectList);
		gChapterMenu.setWidget(1, 3,uploadFiles);
		startNewProgram.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckChapterDashboard.showWidget(1);
			}
		});		
		startNewProject.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckChapterDashboard.showWidget(1);
			}
		});
		
		gChapterMenu.setSize(deckWidth, deckHeight);
		decoratedDashboardMenu.setWidget(gChapterMenu);
		dlpDashboardMenu.add(decoratedDashboardMenu);
		
		return dlpDashboardMenu;
	}
	
	public static DockLayoutPanel createStartNewProgram(){
		DockLayoutPanel dlpStartNewProgram = new DockLayoutPanel(Unit.EM);
		VerticalPanel vpStartProgram = new VerticalPanel();
		DecoratorPanel decoratedStartNewProgram = new DecoratorPanel();
		vpStartProgram.setSize(deckWidth, deckHeight);
		FormPanel formNewProgram = new FormPanel();
		VerticalPanel vpFormHolder = new VerticalPanel();
		HorizontalPanel hpNewProgramTitle = new HorizontalPanel();
		
		Button buttonReturn = new Button("return to Dashboard");
		buttonReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckChapterDashboard.showWidget(0);
			}
		});		
		
		// Title section
		hpNewProgramTitle.add(new Label("program"));
		hpNewProgramTitle.add(buttonReturn);
		vpStartProgram.add(hpNewProgramTitle);
		
		// Form Questions
		vpFormHolder.add(new Label("Program Name"));
		final TextBox tbProgramName = new TextBox();
		tbProgramName.setName("tbProgramName");
		vpFormHolder.add(tbProgramName);

		vpFormHolder.add(new Label("Initial Project"));
		final TextBox tbStarterProject = new TextBox();
		tbStarterProject.setName("tbStarterProject");
		vpFormHolder.add(tbStarterProject);

		vpFormHolder.add(new Label("start Date"));
		final DateBox tbStartDate = new DateBox();
		tbStartDate.setValue(new Date());
		vpFormHolder.add(tbStartDate);

		vpFormHolder.add(new Label("Community Description"));
		final TextArea taCommunityDescription = new TextArea();
		taCommunityDescription.ensureDebugId("cwBasicText-textarea");
		taCommunityDescription.setVisibleLines(5);
		vpFormHolder.add(taCommunityDescription);
		
		
		Button submitButton = new Button("Submit", new ClickHandler() {	
			public void onClick(ClickEvent event) {
				//alVisitList.add(tbReturnVisitDate.getValue());  // This should be moved to update program, and should query selected project for insert
				saveForm(tbProgramName.getText(), tbStarterProject.getText(),tbStartDate.getValue(), taCommunityDescription.getText() );
			}
		});
		submitButton.ensureDebugId("cwBasicButton-normal");
		vpFormHolder.add(submitButton);

		formNewProgram.add(vpFormHolder);
		vpStartProgram.add(formNewProgram);
			
		decoratedStartNewProgram.setWidget(vpStartProgram);
		dlpStartNewProgram.add(decoratedStartNewProgram);
		
		return dlpStartNewProgram;
	}
	
	private static void saveForm(String programTitle, String projectTitle, Date startDate, String taCommunityDescription) throws IllegalArgumentException{
		//formAnswers.add(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
		
		queryService.addNewProgram( programTitle, projectTitle,startDate,taCommunityDescription, new AsyncCallback<Void>(){
			
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

	public static DockLayoutPanel updateProgram(){
//		vpFormHolder.add(new Label("returnVisitDate"));
//		final DateBox tbReturnVisitDate = new DateBox();
//		tbReturnVisitDate.setValue(new Date());
//		vpFormHolder.add(tbReturnVisitDate);
		
		return null;
	}
}