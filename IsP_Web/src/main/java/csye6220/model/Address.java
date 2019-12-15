package csye6220.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressID", unique=true, nullable=false)
	private int addressID;
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cityID")
	private City city;
	
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy="address", cascade = CascadeType.ALL)
	private CustomerServiceDetail serviceDetail;
    
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="client_IPv4")
	private  ClientIP bind_IPv4;
	
	private String zipcode;
	
	private String street;

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public ClientIP getBind_IPv4() {
		return bind_IPv4;
	}

	public void setBind_IPv4(ClientIP bind_IPv4) {
		this.bind_IPv4 = bind_IPv4;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public CustomerServiceDetail getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(CustomerServiceDetail serviceDetail) {
		this.serviceDetail = serviceDetail;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
