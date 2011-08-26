package com.ps.server;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.ps.shared.ProjectManagers;
//import com.google.appengine.api.blobstore.BlobInfo;
//import com.google.appengine.api.blobstore.BlobInfoFactory;
//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
//import com.google.gwt.user.client.Window;
//import com.ps.shared.Global;

@SuppressWarnings("serial")
public class ImageServlet extends AbstractUploadServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	    //private PhotoDao dao = new PhotoDao();    

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, IllegalStateException {
    	//BlobInfo info = new	BlobInfoFactory().loadBlobInfo(blobstoreService.getUploadedBlobs(req).get("LOLUPLOAD"));
    	//Window.alert(info.toString());
    	//String url = blobstoreService.createUploadUrl("/upload");
    	//BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
    	
//    	Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
//    	BlobKey blobKey = blobs.get("image");
//       
//    	 //Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
//         //BlobKey blobKey = blobs.get("myFile");
//
////         if (blobKey == null) {
////                 res.sendRedirect("/");
////         } else {
////                 res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
////         }
//    	
//        if (blobKey == null) {
//        	res.sendRedirect("/");
//        } else {
//
//        	ImagesService imagesService = ImagesServiceFactory.getImagesService();
//        	String imageUrl = imagesService.getServingUrl(blobKey);
//
//        	//UserService userService = UserServiceFactory.getUserService();
//            // TODO: Add a better check for whether the user is logged in or not
//        	// Don't even let the user upload or get here
//        	//User user = userService.getCurrentUser();
//        	
//        	Entity uploadedImage = new Entity("UploadedImage");
//        	uploadedImage.setProperty("blobKey", blobKey);
//        	//uploadedImage.setProperty(ProjectManagers.CREATED_AT, new Date());
//        	//uploadedImage.setProperty(ProjectManagers.OWNER_ID, user.getUserId());
//        	
//        	// Highly unlikely we'll ever search on this property
//        	//uploadedImage.setUnindexedProperty(ProjectManagers.SERVING_URL, imageUrl);
//        	
//        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        	datastore.put(uploadedImage);
//        	
//        	
//        	String keyString = KeyFactory.keyToString(uploadedImage.getKey());
//            res.sendRedirect("/upload?uploadedImageKey=" + blobKey.getKeyString());       //keyString     		
//        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String uploadedImageKey = req.getParameter("uploadedImageKey");
    	resp.setHeader("Content-Type", "text/html");
    	
    	// This is a bit hacky, but it'll work. We'll use this key in an Async service to fetch the image and image information
    	resp.getWriter().println(uploadedImageKey);
    }

	@Override
	protected void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleSubmit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
    	BlobKey blobKey = blobs.get("image");

        if (blobKey == null) {
        	res.sendRedirect("/");
        } else {
        	ImagesService imagesService = ImagesServiceFactory.getImagesService();
        	String imageUrl = imagesService.getServingUrl(blobKey);
        	Entity uploadedImage = new Entity("UploadedImage");
        	uploadedImage.setProperty("blobKey", blobKey);
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        	datastore.put(uploadedImage);
        	String keyString = KeyFactory.keyToString(uploadedImage.getKey());
            res.sendRedirect("/upload");// + blobKey.getKeyString());       //keyString  
        }
	}

	@Override
	protected void showRecord(long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}