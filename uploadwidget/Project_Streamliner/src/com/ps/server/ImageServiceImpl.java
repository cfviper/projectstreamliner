package com.ps.server;



import java.util.List;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ps.services.ImageService;
import com.ps.shared.ProjectManagers;


@SuppressWarnings("serial")
public class ImageServiceImpl extends RemoteServiceServlet implements ImageService {

	@Override
	public String getBlobstoreUploadUrl() {
		//getBlobstoreService()
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		return blobstoreService.createUploadUrl("/upload");
	}

	@Override
	public ProjectManagers get(String key) {
		UploadImageDAO dao = new UploadImageDAO();
		ProjectManagers image = dao.get(key);
		return image;
	}

	@Override
	public List<ProjectManagers> getRecentlyUploaded() {
		UploadImageDAO dao = new UploadImageDAO();
		List<ProjectManagers> images = dao.getRecent(); 
		return images;
	}

	@Override
	public void deleteImage(String key) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UploadImageDAO dao = new UploadImageDAO();
		ProjectManagers image = dao.get(key);
		if(image.getOwnerId().equals(user.getUserId())) {
			dao.delete(key);
		}
	}

//	public String tagImage(Tag tag) {
//		UserService userService = UserServiceFactory.getUserService();
//		User user = userService.getCurrentUser();
//		TagDao dao = new TagDao();
//
//		// TODO: Do validation here of x, y, ImageId
//
//		tag.setTaggerId(user.getUserId());
//		tag.setCreatedAt(new Date());
//
//		String key = dao.put(tag);
//		return key;
//	}
//
//	@Override
//	public List<Tag> getTagsForImage(UploadedImage image) {
//		TagDao dao = new TagDao();
//		List<Tag> tags = dao.getForImage(image);
//		return tags;
//	}

}