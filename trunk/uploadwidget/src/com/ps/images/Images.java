package com.ps.images;


import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public class Images {

	public void images(){
		
	}
	
	public Images imagePM(String name){
		
		return null;
	}
	public interface Resources extends ClientBundle {
		  @Source("mailchimp.jpg")
		  ImageResource logo();

		  @Source("mailchimp.jpg")
		  @ImageOptions(flipRtl = true)
		  ImageResource pointer();
	}
	

	public ImageResource logo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
