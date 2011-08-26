package com.ps.shared;

import com.google.gwt.core.client.GWT;
import com.ps.services.QueryService;
import com.ps.services.QueryServiceAsync;

public class Global {

	private final static QueryServiceAsync queryService = GWT.create(QueryService.class);
	//private final static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	
	public final static QueryServiceAsync getQueryService(){
		return queryService;
	}
	
//	public final static BlobstoreService getBlobstoreService(){
//		return blobstoreService;
//	}
	
	public static String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}