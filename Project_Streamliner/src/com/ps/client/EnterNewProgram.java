package com.ps.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.DecoratedTabBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class EnterNewProgram extends Composite {

	public EnterNewProgram() {
		
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		initWidget(dockLayoutPanel);
		
		MenuBar menuBar = new MenuBar(false);
		dockLayoutPanel.addNorth(menuBar, 1.7);
		MenuBar menuBar_1 = new MenuBar(true);
		
		MenuItem menuItem_1 = new MenuItem("New menu", false, menuBar_1);
		menuItem_1.setHTML("Program menu");
		
		MenuItem menuItem = new MenuItem("New item", false, new Command() {
			public void execute() {
			}
		});
		menuBar_1.addItem(menuItem);
		menuItem.setHTML("Add Program");
		
		MenuItem menuItem_2 = new MenuItem("New item", false, (Command) null);
		menuItem_2.setHTML("Add Project");
		menuBar_1.addItem(menuItem_2);
		menuBar.addItem(menuItem_1);
		
		DeckPanel deckPanel = new DeckPanel();
		dockLayoutPanel.add(deckPanel);
		
		DecoratedTabPanel decoratedTabPanel = new DecoratedTabPanel();
		decoratedTabPanel.setAnimationEnabled(true);
		decoratedTabPanel.setSize("449px", "250px");
		deckPanel.add(decoratedTabPanel);
		
		TextArea textArea = new TextArea();
		decoratedTabPanel.add(textArea, "522", false);
		textArea.setSize("5cm", "3cm");
		
		
	}

}
