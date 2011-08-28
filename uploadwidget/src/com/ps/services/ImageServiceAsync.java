package com.ps.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.ps.shared.Tag;
import com.ps.shared.ProjectManagers;

public interface ImageServiceAsync {

	public void getBlobstoreUploadUrl(AsyncCallback<String> callback);
	//public void getBlobstoreUploadUrl();
	
	void get(String key, AsyncCallback<ProjectManagers> callback);

	void getRecentlyUploaded(AsyncCallback<List<ProjectManagers>> callback);

	void deleteImage(String key, AsyncCallback<Void> callback);

	//void tagImage(Tag tag, AsyncCallback<String> callback);

	//void getTagsForImage(UploadedImage image, AsyncCallback<List<Tag>> callback);


}