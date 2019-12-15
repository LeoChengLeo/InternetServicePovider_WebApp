package csye6220.model;

import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;


@Entity
@Table(name="Customer")
public class Customer implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customerID", unique=true, nullable=false)
	private Long customerID;
		
	private String userName;
	
	@Column(name="passwordHash")
	private String password;
	

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
              fetch = FetchType.EAGER, optional = true)
    private CustomerServiceDetail serviceDetail;
    
    
    private String firstName;
    
    
    private String lastName;
    
    
    private String phoneNumber;
	
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
    
   
    
    
	public CustomerServiceDetail getServiceDetail() {
		return serviceDetail;
	}


	public void setServiceDetail(CustomerServiceDetail serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	
	
	public Long getCustomerID() {
		return customerID;
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




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void encryptPass()
	{
		this.password=BCrypt.hashpw(this.password,BCrypt.gensalt());
	}
	
	public boolean passwordCheck(String password)
	{
		
		return  BCrypt.checkpw(password,this.password);
	}

}
