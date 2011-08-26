package com.ps.client;

import java.util.List;

import com.ps.services.*;
//import com.ps.services.QueryServiceAsync;
import com.ps.tables.Employee;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BrowseProjects {
	final static DockLayoutPanel dockBrowseProjectCard = new DockLayoutPanel(Unit.EM);
	final static StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
	final static FlexTable projectGrid = new FlexTable();
	final static Button sendButton3 = new Button("Send3");
	
	final static VerticalPanel vpBrowseProjectsStack = new VerticalPanel();
	final static String width = "120px";
	final static String height = "400px";

	private final static QueryServiceAsync queryService = GWT.create(QueryService.class);// Should this be called multiple times? or made singleton

	public static DockLayoutPanel createBrowseProjectGUI() {

		// Create a Dock Panel as Card
		dockBrowseProjectCard.setStyleName("cw-DockPanel");

		// ///////////////////
		// Initialize panels
		// ///////////////////

		// Navigation panel
		DecoratorPanel stack = stackInitialize();
		stack.ensureDebugId("cwTree-staticTree");
		vpBrowseProjectsStack.add(stack);
		
		// Add deckpanel for random tiles, type, region, chapter projects 
		
		/*
		 * Flex table will hold random tiles for projects and be narrowed down
		 * by each click in the tree, except for region which will display
		 * clickable map and drop down options.
		 */
		// Create a Flex Table
		FlexCellFormatter cellFormatter = projectGrid.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(0, 0, 1);
		projectGrid.addStyleName("cw-FlexTable");
		projectGrid.setSize("20em", "20em");
		projectGrid.setCellSpacing(3);
		projectGrid.setCellPadding(3);

		// This queries all the projects. Needs to have logic to select 20
		// random projects
		queryAllProjects(projectGrid);

		// Add panels to the DockLayoutPanel
		dockBrowseProjectCard.addNorth(new HTML("header"), 4);
		dockBrowseProjectCard.addSouth(new HTML("footer"), 2);
		dockBrowseProjectCard.addWest(vpBrowseProjectsStack, 10);
		dockBrowseProjectCard.add(projectGrid);

		projectGrid.setWidget(0, 0,new Label("start"));
		
		
		return dockBrowseProjectCard;
	}

	public static DecoratorPanel stackInitialize() {
		stackPanel.setWidth(width);
		stackPanel.setHeight(height);

		// Create section
		createType(stackPanel);
		createRegion(stackPanel);
		createBrowseByChapter(stackPanel);

		// Wrap the static tree in a DecoratorPanel
		DecoratorPanel stackDecorator = new DecoratorPanel();
		stackDecorator.setWidget(stackPanel);

		// Return the stack panel
		stackPanel.ensureDebugId("cwStackPanel");
		return stackDecorator;
	}

	public static void createType(StackLayoutPanel stackPanel) {
		Tree typeTree = new Tree();
		HorizontalPanel hpProjectType = new HorizontalPanel();

		// Set properties for horizontal panel
		hpProjectType.setSpacing(3);

		TreeItem water = addSection(typeTree, "Water");
		addChildSection(water, "Distribution");

		TreeItem construction = addSection(typeTree, "Construction");
		addChildSection(construction, "Bridge");

		stackPanel.add(typeTree, "Type", true, 3);

	}

	public static void createRegion(StackLayoutPanel stackPanel) {
		HorizontalPanel region = new HorizontalPanel();
		// region.setWidth(width);
		region.setSpacing(1);
		stackPanel.add(region, "Region", 3);

		VerticalPanel regionPanel = new VerticalPanel();
		region.add(regionPanel);
		final String asia = "water";
		final Anchor asiaLink = new Anchor(asia);
		regionPanel.add(asiaLink);

	}

	public static void createBrowseByChapter(StackLayoutPanel stackPanel) {
		HorizontalPanel BrowseByChapter = new HorizontalPanel();
		// BrowseByChapter.setWidth(width);
		BrowseByChapter.setSpacing(1);
		stackPanel.add(BrowseByChapter, "Chapter Projects", 4);

		VerticalPanel BrowseByChapterPanel = new VerticalPanel();
		BrowseByChapter.add(BrowseByChapterPanel);
		final String stl = "stl";
		final Anchor stlLink = new Anchor(stl);
		BrowseByChapterPanel.add(stlLink);

	}

	// /////////////////////////////////////////////////////////////////////////////////////
	// Helper Methods to help decorate stack section or assist other GUI
	// elements
	// //////////////////////////////////////////////////////////////////////////////////

	private static void queryAllProjects(final FlexTable projectGrid) {
		queryService.queryServer("Smith", new AsyncCallback<List<Employee>>() {
			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
				errorPopup.add(new HTML("x " + caught.getLocalizedMessage()+ " x"));
			}

			public void onSuccess(List<Employee> result) {
				// TODO Auto-generated method stub
				final DecoratedPopupPanel Popup = new DecoratedPopupPanel(true);
				Popup.add(new Label("good"));
				int row = 0;
				int column = 0;
				for (Employee employee : result) {
					if (column < 5) {
						projectGrid.setWidget(row, column, projectTile(row + "." + column + "::" + employee.getFirstName()));
						column++;
					} else {
						column = 0;
						row++;
					}
				}
			}
		});
	}

	private static DecoratorPanel projectTile(String title) {
		String blurb = " this is a blurb about ...";

		VerticalPanel tile = new VerticalPanel();
		Label tileTitle = new Label();
		tileTitle.setText(title);
		DOM.setStyleAttribute(tileTitle.getElement(), "background", "#E5ECF9");
		// Image imageWater = new Image();
		Label tileBlurb = new Label(blurb);

		// style and decorate tile
		tile.add(tileTitle);
		tile.add(tileBlurb);

		// tile.setSize("100px", "100px");
		tile.setSize("10em", "10em");
		DecoratorPanel tileDecorator = new DecoratorPanel();
		tileDecorator.setWidget(tile);

		return tileDecorator;
	}

	private static TreeItem addSection(Tree typeTree, String label) {
		TreeItem section = typeTree.addItem(label);
		return section;
	}

	private static void addChildSection(TreeItem parent, String child) {
		parent.addItem(child);

		final Anchor link = new Anchor(child);
		// typePanel.add(waterLink);
	}

	// This decorates the section headers for the Stack gui element
	private String getHeaderString(String text, ImageResource image) {
		// Get the images for decoration
		// Images images = (Images) GWT.create(Images.class);

		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		headerText.setStyleName("cw-StackPanelHeader");
		hPanel.add(headerText);

		// Return the HTML string for the panel
		return hPanel.getElement().getString();
	}

}
