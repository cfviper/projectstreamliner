package com.ps.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class LoginCard {
	public static DockLayoutPanel createLoginPanel(){
		DockLayoutPanel loginPanel = new DockLayoutPanel(Unit.EM);
		FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
	    
	    
	    Button administrator = new Button("admin", new ClickHandler() {	
			public void onClick(ClickEvent event) {
				Project_Streamliner.panelMain.showWidget(2);
			}
		});
	    
	    // Add a title to the form
	    layout.setHTML(0, 0, "Review Open Applications");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add some standard form options
	    layout.setHTML(1, 0, "item 1");
	    layout.setWidget(1, 1, new TextBox());
	    layout.setHTML(2, 0, "item 2");
	    layout.setWidget(2, 1, new TextBox());
	    Label programsManaged = new Label();
	   
	   
	    layout.setWidget(3, 0, programsManaged);
	    
	    // Wrap the content in a DecoratorPanel
	    DecoratorPanel reviewMiniPanel = new DecoratorPanel();
	    reviewMiniPanel.setWidget(layout);
	    loginPanel.add(reviewMiniPanel);
	    loginPanel.add(administrator);
	    
		return loginPanel;
	}
}
