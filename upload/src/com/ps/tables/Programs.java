package com.ps.tables;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Programs implements Serializable{
    
		private static final long serialVersionUID = 1L;
		
		@PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long id;
	    
	    @Persistent
	    private String programName;
	
	    @Persistent
	    private String locationName;
	
	    @Persistent
	    private Date startDate;
	    
	    @Persistent
	    private Boolean adopted;
	
	    public Programs() {}
	    
	    public Programs(String programName, String locationName, Date startDate, Boolean adopted) {
	        this.programName = programName;
	        this.locationName = locationName;
	        this.startDate = startDate;
	        this.adopted = adopted;
	    }
	    public Programs(String programName, String locationName) {
	        super();
	    	this.programName = programName;
	        this.locationName = locationName;
	    }
	    
	
	    // Accessors for the fields.  JDO doesn't use these, but your application does.
	    
	    public Long getId() {
	        return id;
	    }
	
	    public String getProgramName() {
	        return programName;
	    } 
	    public void setProgramName(String programName) {
	        this.programName = programName;
	    } 
	
	    public String getLocationtName() {
	        return locationName;
	    } 
	    public void setLocationName(String locationName) {
	        this.locationName = locationName;
	    } 
	
	    public Date getStartDate() {
	        return startDate;
	    } 
	    public void setStartDate(Date startDate) {
	        this.startDate = startDate;
	    } 
	    public Boolean getAdopted() {
	        return adopted;
	    } 
	    public void setAdopted(Boolean adopted) {
	        this.adopted = adopted;
	    }
}
