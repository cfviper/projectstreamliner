package com.ps.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class ProjectDisplay {
	final static DockLayoutPanel theProject = new DockLayoutPanel(Unit.EM);
	
	public static DockLayoutPanel createProjectDisplay() {
		TabLayoutPanel tlpProject = new TabLayoutPanel(1, Unit.EM);
		
		//theProject.add(w);
		theProject.add(tlpProject);
		
		return theProject; 
	}

}
