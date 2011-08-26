package com.ps.server.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Blob;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProjectManagers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
    
    @Persistent
    private String firstName;

    @Persistent
    private String lastName;

    @Persistent
    private int numberManaged;
    
    @Persistent
    private String speciality;
    
    @Persistent
    private List<String> programNames;
    
    @Persistent
    private Blob profilePhoto;

    public ProjectManagers() {}
    
    public ProjectManagers(String firstName, String lastName, int numberManaged, String speciality, List<String> programNames, Blob profilePhoto){
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberManaged = numberManaged;
        this.speciality = speciality;
        this.programNames = programNames;
        this.profilePhoto = profilePhoto;
    }
    public ProjectManagers(String programName, String lastName, int numberManaged) {
        super();
    	this.firstName = programName;
        this.lastName = lastName;
        this.numberManaged = numberManaged;
    }
    public ProjectManagers(String programName, String lastName) {
        super();
    	this.firstName = programName;
        this.lastName = lastName;
    }

    // Accessors for the fields.  JDO doesn't use these, but your application does.
    
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    } 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } 

    public String getLasttName() {
        return lastName;
    } 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 

    public int getNumberManaged() {
        return numberManaged;
    } 
    public void setNumberManaged(int numberManaged) {
        this.numberManaged = numberManaged;
    } 
    public String getspeciality() {
        return speciality;
    } 
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
	
    public List<String> getProgramNames() {
        return programNames;
    } 
    public void setProgramNames(List<String> programNames) {
        this.programNames = programNames;
    } 
      
    public Blob getProfilePhoto() {
        return profilePhoto;
    } 
    public void setProfilePhoto(Blob profilePhoto) {
        this.profilePhoto = profilePhoto;
    } 
    
    
}
