package com.ps.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface ImageResources extends ClientBundle {
	//public static final ImageResources INSTANCE =  GWT.create(ImageResources.class);
	
	@Source("mailchimp.jpg")
	ImageResource logo();

	@Source("mailchimp.jpg")
	@ImageOptions(flipRtl = true)
	ImageResource pointer();
}
