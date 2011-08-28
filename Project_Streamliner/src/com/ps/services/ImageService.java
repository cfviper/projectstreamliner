package com.ps.services;

import java.util.List;

//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
//import com.ps.shared.Tag;
import com.ps.shared.ProjectManagers;

@RemoteServiceRelativePath("upload")
public interface ImageService extends RemoteService  {

	public String getBlobstoreUploadUrl();
	public ProjectManagers get(String key);
	public List<ProjectManagers> getRecentlyUploaded();
	public void deleteImage(String key);
	//public String tagImage(Tag tag);
	//public List<Tag> getTagsForImage(UploadedImage image);
	//public void getBlobstoreUploadUrl(AsyncCallback<String> asyncCallback);

}