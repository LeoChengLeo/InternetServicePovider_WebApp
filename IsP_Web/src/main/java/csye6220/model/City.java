package csye6220.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="City")
public class City {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cityID", unique=true, nullable=false)
	private int cityID;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cityNetworkID")
	private CityNetwork cityNetwork;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="city", cascade = CascadeType.ALL)
	private List<Address> addressList;
	
	
	private String cityName;
	



	public City() {
		// TODO Auto-generated constructor stub
	}

	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public CityNetwork getCityNetwork() {
		return cityNetwork;
	}

	public void setCityNetwork(CityNetwork cityNetwork) {
		this.cityNetwork = cityNetwork;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	
	
	
}
