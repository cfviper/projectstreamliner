package com.ps.shared;

import com.google.gwt.core.client.GWT;
import com.ps.services.QueryService;
import com.ps.services.QueryServiceAsync;

public class Global {

	private final static QueryServiceAsync queryService = GWT.create(QueryService.class);
	
	
	public final static QueryServiceAsync getQueryService(){
		return queryService;
	}
	
	public static String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
