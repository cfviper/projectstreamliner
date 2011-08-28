package com.ps.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
//import com.google.gwt.user.client.ui.FileUpload;
import com.ps.tables.*;//tables.Employee;

import java.util.Date;
import java.util.List;
//import com.ps.tables.Employee;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("query1") 
public interface QueryService extends RemoteService  {
	public String findServer(String name) throws IllegalArgumentException;
	public List<Employee> queryServer(String result) throws IllegalArgumentException;
	
	//////////////////
	// Employee Class
	//////////////////
	public List<Employee> listEmployee();
	public void addEmployee(Employee employee);
	public void removeEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	
	//////////////////
	// Projects Class
	//////////////////
	//void addProject(Projects project);
	public void addProject(String programTitle, String projectTitle, Date startDate, Date returnVisitDate, String programadopted);
	public void addNewProgram(String programTitle, String projectTitle, Date startDate, String taCommunityDescription);
	public List<Projects> queryUnassignedProjects(String programAdopted)throws IllegalArgumentException; // should this be result(programadopted)?
	
	/////////////////
	// Program Class
	/////////////////
	public List<Programs> queryPrograms(String result) throws IllegalArgumentException;
	public void addProgram(String programName, String locationName, Date startDate, Boolean adopted);
	
	//////////////////////////
	// Program Manager Class
	//////////////////////////	
	public void addNewProgramManager(String firstName, String lastName, int programsManaged,String speciality, String uploadProfilePhoto);
	//public void addNewProgramManager(String firstName, String lastName);
	//public String getBlobstoreUploadUrl();
	
}

