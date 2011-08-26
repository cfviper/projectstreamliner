package com.ps.services;

import java.util.Date;
import java.util.List;
//import com.google.appengine.api.datastore.Text;
import com.ps.tables.Employee;
import com.ps.tables.Projects;
import com.ps.tables.Programs;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;

/**
 * The async counterpart of <code>QueryService</code>.
 */

public interface QueryServiceAsync {
	public void findServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	public void queryServer(String input, AsyncCallback<List<Employee>> asyncCallback);
	
	public void listEmployee(AsyncCallback<List<Employee>> callback);
	public void addEmployee(Employee employee, AsyncCallback<Void> callback);
	public void removeEmployee(Employee employee, AsyncCallback<Void> callback);
	public void updateEmployee(Employee employee, AsyncCallback<Void> callback);
	
	// Projects Class
	//void addProject(Projects project, AsyncCallback<Void> callback);
	//public void queryServer(String input, AsyncCallback<Void> asyncCallback);
	public void addProject(String programTitle, String projectTitle, Date startDate, Date returnVisitDate, String programadopted, AsyncCallback<Void> asyncCallback);
	public void queryUnassignedProjects(String input, AsyncCallback<List<Projects>> asyncCallback);//
	
	///////////////////
	// Programs Class
	//////////////////
	public void queryPrograms(String input, AsyncCallback<List<Programs>> asyncCallback);
	public void addProgram(String programName, String locationName, Date startDate, Boolean adopted, AsyncCallback<Void> asyncCallback);
	public void addNewProgram(String programTitle, String projectTitle, Date startDate, String taCommunityDescription, AsyncCallback<Void> asyncCallback);
	
	//////////////////////////
	// Programs Manager Class
	//////////////////////////
	public void addNewProgramManager(String firstName, String lastName, int programsManaged, String speciality, String uploadProfilePhoto, AsyncCallback<Void> asyncCallback);
	//public void addNewProgramManager2(String firstName, String lastName,  AsyncCallback<Void> asyncCallback);
	public void getBlobstoreUploadUrl(AsyncCallback<String> asyncCallback);
	
}
