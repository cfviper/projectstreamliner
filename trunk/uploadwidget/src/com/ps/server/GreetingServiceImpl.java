package com.ps.server;

import javax.jdo.PersistenceManager;
import com.ps.services.GreetingService;
import com.ps.shared.FieldVerifier;
import com.ps.tables.Employee;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import common.PMF;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	
	
	public String greetServer(String input) throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		Employee e = new Employee(input, "Smith");

        try {
            pm.makePersistent(e);
        } finally {
            pm.close();
        }
		
        return "Hello, " + "e" + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	

	
	public String findServer(String input)throws IllegalArgumentException{
		input = "worked";
		return input;
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
 	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
