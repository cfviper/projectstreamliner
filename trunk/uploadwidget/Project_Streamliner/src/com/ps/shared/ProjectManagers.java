package com.ps.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
//import com.google.appengine.api.datastore.Blob;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProjectManagers implements Serializable{

	// Class declarations //
	private static final long serialVersionUID = 1L;
	public static final String CREATED_AT = "createdAt";
	public static final String OWNER_ID = "ownerId";
	public static final String SERVING_URL = "servingUrl";
	
	Date createdAt;
	String ownerId;
	String servingUrl;
	String key;
	
	// setup fields //
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
    
//    @Persistent
//    private Blob profilePhoto;

    @Persistent
	private String fileURL;

    public ProjectManagers() {}
    
//    public ProjectManagers(String firstName, String lastName, int numberManaged, String speciality, List<String> programNames, Blob profilePhoto){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.numberManaged = numberManaged;
//        this.speciality = speciality;
//        this.programNames = programNames;
//        this.profilePhoto = profilePhoto;
//    }
    
    public ProjectManagers(String programName, String lastName, int numberManaged, String speciality, String fileURL) {
        super();
    	this.firstName = programName;
        this.lastName = lastName;
        this.numberManaged = numberManaged;
        this.speciality = speciality;
        this.fileURL = fileURL;
    }
    
//    public ProjectManagers(String programName, String lastName, int numberManaged, String speciality, Blob profilePhoto) {
//        super();
//    	this.firstName = programName;
//        this.lastName = lastName;
//        this.numberManaged = numberManaged;
//        this.speciality = speciality;
//        this.profilePhoto = profilePhoto;
//    }
//    
    
    public ProjectManagers(String programName, String lastName) {
        super();
    	this.firstName = programName;
        this.lastName = lastName;
    }

    // Accessors for the fields.  JDO doesn't use these, but your application does.//
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
      
//    public byte[] getProfilePhoto() { //Blob
//        if(profilePhoto == null){
//        	return null;
//        }
//    	return profilePhoto.getBytes();
//    } 
//    
//    public void setProfilePhoto(byte[] profilePhoto) { //Blob
//        this.profilePhoto = new Blob(profilePhoto);
//    } 
//    
    public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOwnerId() {
		return ownerId;
	}
	
	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getKey() {
		return key;
	}



}
