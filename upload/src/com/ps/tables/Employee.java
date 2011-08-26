package com.ps.tables;


import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

	
	@PersistenceCapable(identityType = IdentityType.APPLICATION)
	public class Employee implements Serializable{
	    
		private static final long serialVersionUID = 1L;
		
		@PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long id;
	    //private Key key;
	    
	    @Persistent
	    private String firstName;

	    @Persistent
	    private String lastName;

	    @Persistent
	    private Date hireDate;

	    public Employee() {}
	    
	    public Employee(String firstName, String lastName, Date hireDate) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.hireDate = hireDate;
	    }
	    public Employee(String firstName, String lastName) {
	        super();
	    	this.firstName = firstName;
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

	    public String getLastName() {
	        return lastName;
	    } 
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    } 

	    public Date getHireDate() {
	        return hireDate;
	    } 
	    public void setHireDate(Date hireDate) {
	        this.hireDate = hireDate;
	    } 
	}