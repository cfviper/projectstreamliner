package com.ps.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ProjectsServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	//void findServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
