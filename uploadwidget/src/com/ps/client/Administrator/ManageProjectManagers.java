package com.ps.client.Administrator;

/*
 * -New is for PMs that come along down the road
 * -Current is for PMs already managing, and need programs managed to be entered at time of creation
 * -Update is to fix some data with managers, major updates will be done via appengine
 */
import java.lang.ref.PhantomReference;

//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.Fraction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.URL;
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
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ps.services.QueryServiceAsync;
import com.ps.shared.Global;
import com.ps.shared.ProjectManagers;
import com.ps.services.ImageService;
import com.ps.services.ImageServiceAsync;

//  Check Request Factory docs to finish setup
//final EventBus eventBus = new SimpleEventBus();
//Object requestFactory = GWT.create(ExpensesRequestFactory.class);
//requestFactory.initialize(eventBus);

public class ManageProjectManagers { // extends HttpServlet{
	private static QueryServiceAsync queryService = Global.getQueryService();
	static ImageServiceAsync ImageService = GWT.create(ImageService.class);

	static DockLayoutPanel dlpCreateProjectManager = new DockLayoutPanel(Unit.EM);
	static DockLayoutPanel dlpUpdateProjectManager = new DockLayoutPanel(Unit.EM);
	final static FormPanel form = new FormPanel();
	
	
	// private RpcServiceAsync rpc;

	public static DockLayoutPanel createNewManagerFormCard() {
		// VerticalPanel vpManagerForm = new VerticalPanel();
		// VerticalPanel vpManagerFormText = new VerticalPanel();
		FlexTable ftForm = new FlexTable();
		Label title = new Label("Create New Project Manager");

		Button bNewManager = new Button("!!New Manager!", new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBox dialog = createNewManager();
				// DialogBox dialog = dbTest();
				dialog.center();
				dialog.show();

			}
		});

		Button bUpdateManager = new Button("Submit!!", new ClickHandler() {
			public void onClick(ClickEvent event) {

			}
		});

		ftForm.setWidget(0, 0, title);
		ftForm.setWidget(1, 0, bNewManager);
		// ftForm.setWidget(1, 0, vpManagerFormText);
		// ftForm.setWidget(1, 1, vpManagerForm);

		dlpCreateProjectManager.add(ftForm);
		return dlpCreateProjectManager;
	}

	// Create the methods for new, update, and current managers
	private static DialogBox createNewManager() {
		VerticalPanel vpCreateManagerForm = new VerticalPanel();
		DecoratorPanel decPanel = new DecoratorPanel();
		final DialogBox dbNewManager = new DialogBox();
		dbNewManager.setGlassEnabled(true);
		dbNewManager.setAnimationEnabled(true);
		dbNewManager.setText("foo");

		HTML details = new HTML("Fill out form for new PM.!!");
		vpCreateManagerForm.add(details);
		FormPanel theForm = getFormPanel();
		vpCreateManagerForm.add(theForm);
		theForm.setVisible(true);
		vpCreateManagerForm.add(new Label("foo"));
		

		startNewBlobstoreSession();
		
		// form.setEncoding(FormPanel.ENCODING_MULTIPART);
		// form.setMethod(FormPanel.METHOD_POST);
		// form.setAction("/upload");

		// form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
		// @Override
		// // public void onSubmitComplete(SubmitCompleteEvent event) {
		// //form.reset();
		// startNewBlobstoreSession();
		// String key = event.getResults();
		//
		// ImageService.get(key, new AsyncCallback<ProjectManagers>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onSuccess(ProjectManagers result) {
		// Window.alert("handler reached");
		// //ImageOverlay overlay = new ImageOverlay(result, loginInfo);
		// //GalleryUpdatedEvent event = new GalleryUpdatedEvent();
		// //fireEvent(event);
		//
		// // TODO: Add something here that says,
		// // hey, upload succeeded
		//
		// final PopupPanel imagePopup = new PopupPanel(true);
		// imagePopup.setAnimationEnabled(true);
		// //imagePopup.setWidget(overlay);
		// imagePopup.setGlassEnabled(true);
		// imagePopup.setAutoHideEnabled(true);
		//
		// imagePopup.center();
		//
		// }
		// });
		//
		// }
		// });
		// UploadPhoto(form,b);
		
		decPanel.setWidget(vpCreateManagerForm);
		dbNewManager.setWidget(decPanel);
		return dbNewManager;
	}

	private static DockLayoutPanel updateProjectManager() {
		VerticalPanel vpUpdateManagerForm = new VerticalPanel();

		return dlpUpdateProjectManager;
	}

	private static DockLayoutPanel addCurrentProjectManager() {
		VerticalPanel vpCurrentManagerForm = new VerticalPanel();

		return dlpUpdateProjectManager;
	}

	// Datastore support methods
	private static void savePMForm(String firstName, String lastName,
		int numberManaged, String speciality, String uploadProfilePhoto) throws IllegalArgumentException {
		// formAnswers.add(programTitle, projectTitle,startDate,
		// returnVisitDate, programadopted);
		// String theFile = uploadProfilePhoto.getFilename();
		// Window.alert("savePMreached");
		queryService.addNewProgramManager(firstName, lastName, numberManaged, speciality, uploadProfilePhoto, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
						Window.alert("Fail");
						final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
						simplePopup.setWidget(new HTML("x "	+ caught.getLocalizedMessage() + " x"));

					}

					public void onSuccess(Void result) {
						Window.alert("Submitted successfully..."
								+ result.toString());
						// form.setAction(result);
						// startNewBlobstoreSession(form);
					}
				});

	}

	public static void UploadPhoto(final FormPanel uploadForm, final Button b) {
		ButtonBase uploadButton;
		// initWidget(uiBinder.createAndBindUi(this));
		// Disable the button until we get the URL to POST to
		// uploadButton.setText("Loading...");
		// uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		// uploadForm.setMethod(FormPanel.METHOD_POST);
		// uploadButton.setEnabled(false);
		// uploadField.setName("image");

		// Now we use out GWT-RPC service and get an URL

		// startNewBlobstoreSession(uploadForm,b);
		// // Once we've hit submit and it's complete, let's set the form to a
		// new session.
		// // We could also have probably done this on the onClick handler
		// uploadForm.addSubmitCompleteHandler(new
		// FormPanel.SubmitCompleteHandler() {
		//
		// @Override
		// public void onSubmitComplete(SubmitCompleteEvent event) {
		// uploadForm.reset();
		// startNewBlobstoreSession(uploadForm,b);
		// Window.alert("UploadPhoto reached");
		// }
		// });
	}

	private static void startNewBlobstoreSession() {
		ImageService.getBlobstoreUploadUrl(new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				form.setAction(result);
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
				form.setMethod(FormPanel.METHOD_POST);
				Window.alert("snb 1");
				// uploadButton.setText("Upload");
				// uploadButton.setEnabled(true);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

		});
	}

	private static FormPanel getFormPanel() {		
		//if (form == null) {
			final FileUpload uploadProfilePhoto = new FileUpload();
			uploadProfilePhoto.ensureDebugId("cwFileUpload");
			uploadProfilePhoto.setName("image");  // next step: create and name fileUpload in getFormPanel
			//form.setWidget(ftCreateManagerForm);
			form.setTitle("profilePhoto");
			form.addStyleName("table-center");
			form.addStyleName("demo-FormPanel");
			form.setAction("/upload");
			form.setEncoding(FormPanel.ENCODING_MULTIPART);
			form.setMethod(FormPanel.METHOD_POST);
			form.setWidget(getFormFields(uploadProfilePhoto));
			
			// add submit handler
			form.addSubmitHandler(new SubmitHandler() {
				public void onSubmit(SubmitEvent event) {
					if (uploadProfilePhoto.getFilename().length() == 0) {
						Window.alert("Did you select a file?");
						event.cancel();
					}
				}
			});

			// add submit complete handler
			form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
				public void onSubmitComplete(SubmitCompleteEvent event) {
					// button.setEnabled(false);
					String results = event.getResults();
					// pResponse.add(new HTML(results));
					getFormUrl();
				}
			});
		//}		
		return form;
	}
	
	private static VerticalPanel getFormFields(FileUpload uploadProfilePhoto){
		VerticalPanel vpFormPanel = new VerticalPanel();
		FlexTable ftCreateManagerForm = new FlexTable();
		final TextBox firstName = new TextBox();
		final TextBox lastName = new TextBox();
		final TextBox speciality = new TextBox();
		final TextBox programNames = new TextBox();
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
		// ftCreateManagerForm.setWidget(5,1,b);
		
		vpFormPanel.add(new Label("baz"));
		vpFormPanel.add(ftCreateManagerForm);
		vpFormPanel.add(getPhotoButton(uploadProfilePhoto));
		return vpFormPanel;
	}
	
	private static void getFormUrl() {
		ImageService.getBlobstoreUploadUrl(new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) { // should this be result or url?
				Window.alert(result);
				form.setAction("/upload");//result
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
				form.setMethod(FormPanel.METHOD_POST);
				Window.alert("snb 1");
				System.out.println("retrieved url for blob store: " + result);
				// uploadButton.setText("Upload");
				// uploadButton.setEnabled(true);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something went wrong with the rpc call:  "+caught.getCause()+
						caught.getLocalizedMessage()+
						caught.getMessage()+
						caught.getStackTrace());
			}
		});
	}
	
	private static Button getPhotoButton(final FileUpload uploadProfilePhoto){
		Button b = new Button("Submit!", new ClickHandler() {
			public void onClick(ClickEvent event) {

				String filename = uploadProfilePhoto.getFilename();
				if (filename.length() == 0) {
					Window.alert("No such file.");
				} else {
					form.submit();
					// form.reset();
					try {
						// savePMForm(firstName.getText(),lastName.getText(),0,
						// speciality.getText(),uploadProfilePhoto.getFilename());
						// // 0 will need to e changed by making method without
						// number since it will be updated by calculation.
					} catch (NumberFormatException e) {
						Window.alert(e.getMessage());
					}
				}
			}
		});
		return b;
	}
}