package com.ps.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.ps.services.QueryService;
import com.ps.tables.Employee;
import com.ps.tables.Programs;
import com.ps.tables.Projects;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import common.PMF;

/**
 * The server side implementation of the RPC service.
 */
//@SuppressWarnings("serial")
public class QueryServiceImpl extends RemoteServiceServlet  implements QueryService { 
	PersistenceManager pm = PMF.get().getPersistenceManager();
	private static final long serialVersionUID = 1L;
	private PersistJDO persistDAO = new PersistJDO();
	

	public String findServer(String input)throws IllegalArgumentException{
		input = escapeHtml("worked");
		return input;
	}
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	
	//////////////////////////
	// Projects Class Methods
	/////////////////////////
	//	@Override
	//	public void addProject(Projects project) {
	//		// TODO Auto-generated method stub
	//		persistDAO.addProject(project);	
	//	}
	
	public void addProject(String programTitle, String projectTitle, Date startDate, Date returnVisitDate, String programadopted) {
		persistDAO.addProject(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
	}
	
	public void addNewProgram(String programTitle, String projectTitle, Date startDate, String taCommunityDescription) {
		persistDAO.addNewProgram(programTitle, projectTitle,startDate, taCommunityDescription );
	}
	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Projects> queryUnassignedProjects(String programAdopted) throws IllegalArgumentException{
		//String query = "select from "+ Projects.class.getName()+" where lastName =='Smith'"; 
		
		Query q = pm.newQuery(Projects.class, "programAdopted == yes");
		List<Projects> entities =   (List<Projects>) q.execute();
	    //List<Projects> entities = (List<Projects>) q.execute();
		//return entities;  can't return "entities" since it's an object
	    //java.util.ArrayList java_util_arraylist = new java.util.ArrayList(entities);
	    //return new ArrayList<Projects>( entities);//List<Projects>(entities); //ArrayList -- how can this be serialized?
	    return entities;
	    //return java_util_arraylist;
	}
	
	/////////////////////////
	// Employee Class Methods
	/////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> queryServer(String result) throws IllegalArgumentException {
		//String query = "select from "+ Employee.class.getName()+" where lastName =='Smith'"; 
		//List<Employee> entities =   (List<Employee>) pm.newQuery(query).execute();
		
		Query q = pm.newQuery(Employee.class, "lastName == Smith");
	    List<Employee> entities = (List<Employee>) q.execute();
		//return entities;  can't return "entities" since it's an object
	    return new ArrayList<Employee>( entities);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.addEmployee(employee);
	}

	@Override
	public void removeEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.removeEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.updateEmployee(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployee() {
        String query = "select from "+ Employee.class.getName()+" where lastName =='Smith'"; 
		List<Employee> listEmployee =  (List<Employee>) pm.newQuery(query).execute();
        return new ArrayList<Employee> (listEmployee);
	}

	///////////////////////
	// Program Class
	//////////////////////
	@SuppressWarnings("unchecked")
	public List<Programs> queryPrograms(String result)throws IllegalArgumentException {
        //String query = "select from "+ Programs.class+" where locationName == 'baz'";
		//string query causes null exception.  Need to track down this issue, probably syntax
		Query q = pm.newQuery(Programs.class, "adopted == "+"True");
		List<Programs> resultPrograms =  (List<Programs>) q.execute();
        return new ArrayList<Programs> (resultPrograms);
	}

	public List<Programs> queryPrograms(){
		Query q = pm.newQuery(Programs.class, "*");
		List<Programs> resultPrograms =  (List<Programs>) q.execute();
        return new ArrayList<Programs> (resultPrograms);
	}

	@Override
	public void addProgram(String programName, String locationName,	Date startDate, Boolean adopted) {
		persistDAO.addProgram(programName, locationName, startDate, adopted);
	}
	
	///////////////////////
	// Program Manager Class
	//////////////////////
	public void addNewProgramManager(String firstName, String lastName, int programsManaged,String speciality, String uploadProfilePhoto){
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        String fileURL = blobstoreService.createUploadUrl("/upload");
		//persistDAO.addNewProgramManager(firstName, lastName, programsManaged);
		persistDAO.addNewProgramManager(firstName, lastName, programsManaged, speciality, fileURL); 

	}
	public String getBlobStroeUploaderUrl(){
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        return blobstoreService.createUploadUrl("/upload");
	}

//	@Override
//	public String getBlobstoreUploadUrl() {
//		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
//        return blobstoreService.createUploadUrl("/upload");
//	}

	
}
