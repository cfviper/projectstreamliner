package com.ps.client;

import com.ps.services.GreetingService;
import com.ps.services.GreetingServiceAsync;
import com.ps.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Home {
	// Create a remote service proxy to talk to the server-side Greeting service. 
	
	private final static com.ps.services.GreetingServiceAsync greetingService  = GWT.create(com.ps.services.GreetingService.class);
	//private final static com.ps.services.ProjectsServiceAsync projectsService  = GWT.create(com.ps.services.ProjectsService.class);
	
	// The message displayed to the user when the server cannot be reached or returns an error.	 
	private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. Please check your network connection and try again.";
	
	// Create the dock to be used as the Home card
	public static final DockLayoutPanel dockHomeCard = new DockLayoutPanel(Unit.EM);

	// Create Method Global GUI Elements
	final static TextArea textArea = new TextArea();
	final static FlexTable flexCurrentTable = new FlexTable();
	final static Hyperlink link1 = new Hyperlink("Content One", "BrowseProjects");
	final static Button sendButton = new Button("Send");
	final static TextBox nameField = new TextBox();
	final static Label errorLabel = new Label();

	// Popup box GUI
	final static DialogBox dialogBox = new DialogBox();
	final static Button closeButton = new Button("Close");
	final static Label textToServerLabel = new Label();
	final static HTML serverResponseLabel = new HTML();
	final static VerticalPanel dialogVPanel = new VerticalPanel();
	
	final static SplitLayoutPanel slp = new SplitLayoutPanel();
	final static FlowPanel fp = new FlowPanel();
	
	public static DockLayoutPanel createHomeGUI() {
		// Create a Dock Panel
		dockHomeCard.setVisible(true);
	
		dockHomeCard.setStyleName("cw-DockPanel");
		
		
		fp.add(nameField);
		nameField.setText("GWT User");
		fp.add(sendButton);

		//dockHomeCard.add(textArea);
		//dockHomeCard.add(flexCurrentTable);
		//dockHomeCard.add(link1);
		
		dockHomeCard.add(fp);
		dockHomeCard.add(errorLabel);
		
		
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add GUI Elements to the RootPanel, use RootPanel.get() to get the entire body element
		//RootPanel.get("errorLabelContainer").add(errorLabel);
		

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the text area box
		textArea.setVisibleLines(5);

		// Create the popup dialog box
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			
			//Fired when the user clicks on the sendButton. 
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			
			//Fired when the user types in the nameField. 
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}
		
			// Send the name from the nameField to the server and wait for a response. 
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {

							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox.setText("Remote Procedure Call - Failure");
								serverResponseLabel.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		return dockHomeCard;
	}

}
