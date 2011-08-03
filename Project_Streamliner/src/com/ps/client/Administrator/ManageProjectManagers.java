package com.ps.client.Administrator;
/*
 * -New is for PMs that come along down the road
 * -Current is for PMs already managing, and need programs managed to be entered at time of creation
 * -Update is to fix some data with managers, major updates will be done via appengine
 */
import java.lang.ref.PhantomReference;

import org.apache.commons.lang.math.Fraction;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ps.services.QueryServiceAsync;
import com.ps.shared.Global;

public class ManageProjectManagers{
	private  static QueryServiceAsync queryService = Global.getQueryService();
	static DockLayoutPanel dlpCreateProjectManager = new DockLayoutPanel(Unit.EM); 
	static DockLayoutPanel dlpUpdateProjectManager = new DockLayoutPanel(Unit.EM);
	
	public static DockLayoutPanel createNewManagerFormCard(){
		//VerticalPanel vpManagerForm = new VerticalPanel();
		//VerticalPanel vpManagerFormText = new VerticalPanel();
		FlexTable ftForm = new FlexTable();
		Label title = new Label("Create New Project Manager");
		
		
		Button bNewManager = new Button("New Manager!", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  DialogBox dialog = createNewManager();
		    	  //DialogBox dialog = dbTest();
		    	  dialog.center();
		 	      dialog.show();
		    	  
		      }
		    });
		
		Button bUpdateManager = new Button("Submit!", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  
		      }
		    });
		
		

		ftForm.setWidget(0,0, title);
		ftForm.setWidget(1, 0, bNewManager);
		//ftForm.setWidget(1, 0, vpManagerFormText);
		//ftForm.setWidget(1, 1, vpManagerForm);
		
		dlpCreateProjectManager.add(ftForm);
		return dlpCreateProjectManager;
	}
	
	
	
	// Create the methods for new, update, and current managers
	private static DialogBox createNewManager(){
		VerticalPanel vpCreateManagerForm = new VerticalPanel();
		FlexTable ftCreateManagerForm = new FlexTable();
		DecoratorPanel decPanel = new DecoratorPanel();
		final DialogBox dbNewManager = new DialogBox();
	    dbNewManager.setGlassEnabled(true);
	    dbNewManager.setAnimationEnabled(true);
	    dbNewManager.setText("foo");

	    HTML details = new HTML("Fill out form for new PM");
	    vpCreateManagerForm.add(details);
	    
		final FormPanel form = new FormPanel();
		final TextBox firstName = new TextBox();
		final TextBox lastName = new TextBox();
		final TextBox speciality = new TextBox();
		final TextBox programNames = new TextBox();
		final FileUpload uploadProfilePhoto = new FileUpload();
		uploadProfilePhoto.ensureDebugId("cwFileUpload");
		
				
		Button b = new Button("Submit!", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  String filename = uploadProfilePhoto.getFilename();
		          if (filename.length() == 0) {
		            Window.alert("No such file.");
		          } else {
		            Window.alert("PM added successfully.");
//		            try{ 	   
//					  	//savePMForm(firstName.getText(),lastName.getText(),0, speciality.getText(),uploadProfilePhoto.getFilename()); // 0 will need to e changed by making method without number since it will be updated by calculation.
//					} catch(NumberFormatException e) { 
//						Window.alert(e.getMessage());
//					}
		        }
		      }
		    });
		
		
		ftCreateManagerForm.setWidget(0, 0, new Label("first"));
		ftCreateManagerForm.setWidget(0, 1, firstName);
		ftCreateManagerForm.setWidget(1, 0, new Label("second"));
		ftCreateManagerForm.setWidget(1, 1, lastName);		
		ftCreateManagerForm.setWidget(2, 0, new Label("speciality"));
		ftCreateManagerForm.setWidget(2, 1, speciality);	
		ftCreateManagerForm.setWidget(3, 0, new Label("Program Name"));
		ftCreateManagerForm.setWidget(3, 1, programNames);
		ftCreateManagerForm.setWidget(4, 0, new Label("Photo"));
		ftCreateManagerForm.setWidget(4, 1, uploadProfilePhoto);
		ftCreateManagerForm.setWidget(5,1,b);
		form.setWidget(ftCreateManagerForm);
		
	     form.setEncoding(FormPanel.ENCODING_MULTIPART);
	     form.setMethod(FormPanel.METHOD_POST);
	     form.addStyleName("table-center");
	     form.addStyleName("demo-FormPanel");
	     
	     ///UploadPhoto(form,b);
	     
	     vpCreateManagerForm.add(form);
	     decPanel.setWidget(vpCreateManagerForm);
	     dbNewManager.setWidget(decPanel);
	     return dbNewManager;
	     
	     //return dlpCreateProjectManager;
	}
	
	private static DockLayoutPanel updateProjectManager(){
		VerticalPanel vpUpdateManagerForm = new VerticalPanel();
		
		return dlpUpdateProjectManager;
	}
	
	private static DockLayoutPanel addCurrentProjectManager(){
		VerticalPanel vpCurrentManagerForm = new VerticalPanel();
		
		return dlpUpdateProjectManager;
	}
	
	
	// Datastore support methods
	private static void savePMForm(String firstName, String lastName, int numberManaged, String speciality, String uploadProfilePhoto) throws IllegalArgumentException{
		//formAnswers.add(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
		//String theFile = uploadProfilePhoto.getFilename();
		queryService.addNewProgramManager( firstName, lastName, numberManaged, speciality, uploadProfilePhoto, new AsyncCallback<Void>(){

			
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
				simplePopup.setWidget(new HTML("x "+caught.getLocalizedMessage()+" x"));
				Window.alert("Fail");
			}

			
			public void onSuccess(Void result) {
				Window.alert("Submitted successfully");
				
			}
		});
		
	}

	 
	public static void UploadPhoto(final FormPanel uploadForm, final Button b) {
		 ButtonBase uploadButton;
				//        initWidget(uiBinder.createAndBindUi(this));
		         // Disable the button until we get the URL to POST to
		         //uploadButton.setText("Loading...");
				 //uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		         //uploadForm.setMethod(FormPanel.METHOD_POST);
		         //uploadButton.setEnabled(false);
		         //uploadField.setName("image");
		 
		         // Now we use out GWT-RPC service and get an URL
		 
		         startNewBlobstoreSession(uploadForm,b);
		         // Once we've hit submit and it's complete, let's set the form to a new session.
		         // We could also have probably done this on the onClick handler
		         uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
		 
		             @Override
		             public void onSubmitComplete(SubmitCompleteEvent event) {
		                 uploadForm.reset();
		                startNewBlobstoreSession(uploadForm,b);
		             }
		         });
		     }
		 
	 private static void startNewBlobstoreSession(final FormPanel uploadForm, final Button uploadButton) {
		         queryService.getBlobstoreUploadUrl(new AsyncCallback<String>() {
		             public void onSuccess(String url) {
		                 uploadForm.setAction(url);
		                 uploadButton.setText("Upload");
		                 uploadButton.setEnabled(true);
		                 Window.alert("retrieved url for blob store: " + url);
		             }
		             @Override
		             public void onFailure(Throwable caught) {
		                 // We probably want to do something here
		             }
		         });
	}
		 
		     //@UiHandler("uploadButton")
//		     void onSubmit(ClickEvent e) {
//		         uploadForm.submit();
//		     }
	
	
	
}