package com.ps.server;


import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import common.PMF;

import com.google.appengine.api.datastore.Text;
import com.ps.shared.ProjectManagers;
import com.ps.tables.Employee;
import com.ps.tables.Programs;
import com.ps.tables.Projects;




public class PersistJDO{// implements EmployeeDAO{

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return PMF.get();
	}

	// Projects Methods
	public void addProject(Projects project) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		Projects p = new Projects(project.getprogramTitle(), project.getprojectTitle(),project.getstartDate(),project.getReturnVisitDate(), project.getProgramAdopted());
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
	}

	public void addProject(String programTitle, String projectTitle, Date startDate, Date returnVisitDate, String programadopted) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		Projects p = new Projects(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		
	}
	
	
	
	public void removeProject(Projects project) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		try {
			pm.currentTransaction().begin();
			project = pm.getObjectById(Projects.class, project.getId());
			pm.deletePersistent(project);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}

	public void updateProject(Projects project) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		String projectName = project.getprojectTitle();
		Date returnVisitDate = project.getReturnVisitDate();//List<Date>
		

		try {
			pm.currentTransaction().begin();
			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			project = pm.getObjectById(Projects.class, project.getId());
			project.setprojectTitle(projectName);
			project.setReturnVisitDate(returnVisitDate);
			
			pm.makePersistent(project);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}


	
	// Employee Methods
	public void addEmployee(Employee employee) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		try {
			pm.makePersistent(employee);
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployee() {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		String query = "select from " + Employee.class.getName();
		return (List<Employee>) pm.newQuery(query).execute();
	}

	public void removeEmployee(Employee employee) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		try {
			pm.currentTransaction().begin();

			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			employee = pm.getObjectById(Employee.class, employee.getId());
			pm.deletePersistent(employee);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}

	public void updateEmployee(Employee employee) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		String nameFirst = employee.getFirstName();
		String nameLast = employee.getLastName();
		

		try {
			pm.currentTransaction().begin();
			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			employee = pm.getObjectById(Employee.class, employee.getId());
			employee.setFirstName(nameFirst);
			employee.setLastName(nameLast);
			
			pm.makePersistent(employee);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}

	//////////////////
	// Program methods
	///////////////////

	public void addProgram(String programName, String locationName, Date startDate, Boolean adopted) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		Programs p = new Programs(programName, locationName,startDate, adopted);
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		
	}
	
	public void addNewProgram(String programTitle, String projectTitle, Date startDate, String taCommunityDescription) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		Projects p = new Projects(programTitle, projectTitle, startDate, taCommunityDescription);
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		
	}
	//////////////////
	// Program methods
	///////////////////
	public void addNewProgramManager(String firstName, String lastName, int programsManaged, String speciality, String fileURL) {
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		ProjectManagers p = new ProjectManagers(firstName, lastName, programsManaged, speciality, fileURL);
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		
	}
	
}