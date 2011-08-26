package com.ps.client;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Hyperlink;

public class GUIBuilder {

	public Hyperlink getHyperlink(String className, String name) {
		// historyListener is depreciated

		Hyperlink link = new Hyperlink(className, name);
		// link.ensureDebugId("cwHyperlink-" + className.getName());

	

		// Now that the listener is setup, fire the initial history state.
		History.fireCurrentHistoryState();
		return link;
	}
}
