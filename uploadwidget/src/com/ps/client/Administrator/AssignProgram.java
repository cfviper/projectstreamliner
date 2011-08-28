package com.ps.client.Administrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.ps.images.ImageResources;
import com.ps.services.QueryServiceAsync;
import com.ps.shared.Global;
import com.ps.tables.Programs;

public class AssignProgram {// extends AsyncDataProvider<String>{
	private final static ImageResources resources = GWT.create(ImageResources.class);
	private final static QueryServiceAsync queryService = Global.getQueryService();
	final static DeckPanel deckAssignProgram = new DeckPanel();
	static VerticalPanel vpAssignProgramTest = new VerticalPanel();
	
	static List<String> bazList;
	static List<Programs> programList;
	final static ListDataProvider<String> provider = new ListDataProvider<String>();
	static Label test = new Label("test");
	static Label theList = new Label("list here");
	final static CellList<String> cellListGlobal = new CellList<String>(new TextCell());

	public static DockLayoutPanel assignProgramCard() {
		DockLayoutPanel dlpAssignProgram = new DockLayoutPanel(Unit.EM);

		CheckBox cb = new CheckBox("Foo");
		cb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("It is " + ("checked ?  : not") + "checked");
			}
		});


		HorizontalPanel hpAssignProgram = new HorizontalPanel();
		hpAssignProgram.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hpAssignProgram.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);	
		hpAssignProgram.setSpacing(10);
		
		DecoratorPanel decorLeftPanel = new DecoratorPanel();
		decorLeftPanel.add(vpProgramCategories());
		hpAssignProgram.add(decorLeftPanel);
		
		//hpAssignProgram.add(wPMListTest());
		DecoratorPanel decorMiddlePanel = new DecoratorPanel();
		decorMiddlePanel.add(vpProgramManagers());
		hpAssignProgram.add(decorMiddlePanel);
		
		DecoratorPanel decorRightPanel = new DecoratorPanel();
		decorRightPanel.add(vpProgramList());
		hpAssignProgram.add(decorRightPanel);

		
		
		hpAssignProgram.setCellHorizontalAlignment(decorLeftPanel, HasHorizontalAlignment.ALIGN_LEFT);
		hpAssignProgram.setCellHorizontalAlignment(decorMiddlePanel, HasHorizontalAlignment.ALIGN_CENTER);
		hpAssignProgram.setCellHorizontalAlignment(decorRightPanel,HasHorizontalAlignment.ALIGN_RIGHT);
		//hpAssignProgram.setStylePrimaryName("center");
		//hpAssignProgram.setStyleName("center");
		//hpAssignProgram.addStyleName("center");
		
		dlpAssignProgram.add(hpAssignProgram);
		dlpAssignProgram.setStylePrimaryName("center");
		dlpAssignProgram.setStyleName("center");
		dlpAssignProgram.addStyleName("center");
		return dlpAssignProgram;
	}

	// ////////

	// interface Binder extends UiBinder<Widget, CwCellList> {
	// }

	private static PMCell cellCreatePM() {

		// Image img = new Image(resources.logo());
		// PMCell currentCell = new PMCell(null);
		PMCell currentCell = new PMCell(resources.logo());

		return currentCell;
	}

	private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
			"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

	public static VerticalPanel vpProgramCategories() {
		//DockLayoutPanel dlpPMList = new DockLayoutPanel(Unit.EM);
		
		VerticalPanel vpAssignProgram = new VerticalPanel();

		// Create a cell to render each value in the list.
		TextCell textCell = new TextCell();

		// Create a CellList that uses the cell.
		CellList<String> cellList = new CellList<String>(textCell);

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		cellList.setRowCount(DAYS.size(), true);
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellList);
		// Push the data into the widget.
		cellList.setRowData(0, DAYS);
		
		
		
		Image widget = new Image(resources.logo());

		// Add it to the root panel.
		vpAssignProgram.add(cellList);
		vpAssignProgram.add(widget);
		
		
		return vpAssignProgram;
	}

	public static VerticalPanel vpProgramList() {

		VerticalPanel vpAssignProgramTest = new VerticalPanel();
		// Create a CellList.
		// the list cellList

		// Create a data provider.
		 MyDataProvider dataProvider = new MyDataProvider();

		// Add the cellList to the dataProvider.
		//dataProvider.onRangeChanged(cellList);
		dataProvider.addDataDisplay(cellListGlobal);

		// Create paging controls.
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellListGlobal);

				
		// Add the widgets to the root panel.
		vpAssignProgramTest.add(pager);
		vpAssignProgramTest.add(cellListGlobal);
				
		
		return vpAssignProgramTest;
	}

	public static VerticalPanel vpProgramManagers() { 
		VerticalPanel vpPMTest2 = new VerticalPanel();
		// Create a CellList.
		// the list cellList

		// Create a data provider.
		 MyDataProvider dataProvider = new MyDataProvider();

		// Add the cellList to the dataProvider.
		//dataProvider.onRangeChanged(cellList);
		dataProvider.addDataDisplay(cellListGlobal);

		// Create paging controls.
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellListGlobal);

				
		// Add the widgets to the root panel.
		vpPMTest2.add(pager);
		vpPMTest2.add(cellListGlobal);
				
		
		return vpPMTest2;
	}

	// ///////////// Inner classes //////////////
	static class PMCell extends AbstractCell<String> {// implements
														// Cell<String>{

		/**
		 * The html of the image used for contacts.
		 */
		private final String imageHtml;

		public PMCell(ImageResource image) {
			this.imageHtml = AbstractImagePrototype.create(image).getHTML();
		}

		public void render(Context context, String value, SafeHtmlBuilder sb) {
			// Value can be null, so do a null check..
			if (value == null) {
				return;
			}

			sb.appendHtmlConstant("<table>");

			// Add the contact image.
			sb.appendHtmlConstant("<tr><td rowspan='3'>");
			sb.appendHtmlConstant(imageHtml);
			sb.appendHtmlConstant("</td>");

			// Add the name and address.
			sb.appendHtmlConstant("<td style='font-size:95%;'>");
			// sb.appendEscaped(value.getAdopted().toString());
			sb.appendHtmlConstant("</td></tr><tr><td>");
			// sb.appendEscaped(value.getFirstName());
			sb.appendHtmlConstant("</td></tr></table>");
		}

	}

	private static class MyDataProvider extends AsyncDataProvider<String> {
		/**
		 * {@link #onRangeChanged(HasData)} is called when the table requests a
		 * new range of data. You can push data back to the displays using
		 * {@link #updateRowData(int, List)}.
		 */
		
		@Override
		protected void onRangeChanged(HasData<String> display) {
			// Get the new range.
			//final Range range = display.getVisibleRange();
		
			/*
			 * Query the data asynchronously. If you are using a database, you
			 * can make an RPC call here. We'll use a Timer to simulate a delay.
			 */
			queryCountPrograms("True", bazList);
				
		}
		
		public List<String> queryCountPrograms(String parameter, final List<String> bazList ) {
			final List<String> list =  Arrays.asList("Sunday", "Monday",	"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
			
			queryService.queryPrograms(parameter, new AsyncCallback<List<Programs>>() {
				
				List<String> someList = new ArrayList<String>(); 
				
				@Override
				public void onFailure(Throwable caught) {
					final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
					errorPopup.add(new HTML("x " + caught.getLocalizedMessage()	+ " x"));
					theList.setText("failed");
				}

				public void onSuccess(List<Programs> result) {
					int counter = 0;
					
					for (Programs programs : result) {
						someList.add(programs.getLocationtName());
						//someList.set(0, "something");//.add("something");
						counter++;
					    theList.setText(counter+"");
					}
					//theList.setText("after");				
					cellListGlobal.setRowData(0, someList);
				}
				
			});
			
			return bazList;
		}
		
	}
	
	
	


	//////////////--> Queries (Not listed in MyDataProvider) <--/////////////////////
	

	// @Override
	// protected void onRangeChanged(HasData<String> display) {
	// // TODO Auto-generated method stub
	// updateRowData(0, bazList);
	// }
}
