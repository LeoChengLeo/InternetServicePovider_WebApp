package csye6220.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="CustomerServiceDetail")
public class CustomerServiceDetail {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customerServiceID", unique=true, nullable=false)
	private long customerServiceID;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerID")
	private Customer customer;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="packageID")
	private ServicePackage servicePackage;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="addressID")
	private Address address;
	
	
	private java.sql.Date startServiceDate=new java.sql.Date(System.currentTimeMillis());

	
	private java.sql.Date endServiceDate;
	
	
	public ServicePackage getServicePackage() {
		return servicePackage;
	}


	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public java.sql.Date getEndServiceDate() {
		return endServiceDate;
	}


	public void setEndServiceDate(int duration) {
		
		int year=startServiceDate.getYear();
		int month=startServiceDate.getMonth();
		int day=startServiceDate.getDate();
		
		year=year+(month+duration)/12;
		month=(month+duration)%12;
		
		this.endServiceDate=new Date(year,month,day);
	}


	public CustomerServiceDetail() {
		// TODO Auto-generated constructor stub
	}


	public long getCustomerServiceID() {
		return customerServiceID;
	}


	public void setCustomerServiceID(long customerServiceID) {
		this.customerServiceID = customerServiceID;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public java.sql.Date getStartServiceDate() {
		return startServiceDate;
	}


	public void setStartServiceDate(java.sql.Date startServiceDate) {
		this.startServiceDate = startServiceDate;
	}


	

	
	
	
	
}
