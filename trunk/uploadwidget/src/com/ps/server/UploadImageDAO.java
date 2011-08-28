package com.ps.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.ps.shared.ProjectManagers;

public class UploadImageDAO {

	DatastoreService datastore;
	 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public UploadImageDAO() {
		//datastore = DatastoreServiceFactory.getDatastoreService();
		blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	}

	public ProjectManagers get(String encodedKey) {
		Key key = KeyFactory.stringToKey(encodedKey);
		try {
			Entity result = datastore.get(key);
			ProjectManagers image = fromEntity(result);
			image.setKey(encodedKey);
			return image;
		} catch (EntityNotFoundException e) {
			return null;
		}

	}

	public List<ProjectManagers> getRecent() {
		Query query = new Query("UploadedImage");
		query.addSort(ProjectManagers.CREATED_AT, SortDirection.DESCENDING);
		FetchOptions options = FetchOptions.Builder.withLimit(25);

		ArrayList<ProjectManagers> results = new ArrayList<ProjectManagers>();
		for (Entity result : datastore.prepare(query).asIterable(options)) {
			ProjectManagers image = fromEntity(result);
			results.add(image);
		}
		return results;
	}

	public void delete(String encodedKey) {
		Key key = KeyFactory.stringToKey(encodedKey);
		datastore.delete(key);
	}

	private ProjectManagers fromEntity(Entity result) {
		ProjectManagers image = new ProjectManagers();
		
		image.setCreatedAt((Date) result.getProperty(ProjectManagers.CREATED_AT));
		image.setServingUrl((String) result.getProperty(ProjectManagers.SERVING_URL));
		image.setOwnerId((String) result.getProperty(ProjectManagers.OWNER_ID));

		if (image.getKey() == null) {
			String encodedKey = KeyFactory.keyToString(result.getKey());
			image.setKey(encodedKey);
		}

		return image;
	}

}
