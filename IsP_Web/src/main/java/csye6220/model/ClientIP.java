package csye6220.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ClientIP")
public class ClientIP {
	
	
	@Id
	@Column(name="client_IPv4", unique=true, nullable=false)
	private String client_IPv4;
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cityNetworkID")
	private CityNetwork cityNetwork;
	
	
	public ClientIP() {
		// TODO Auto-generated constructor stub
	}


	public String getClient_IPv4() {
		return client_IPv4;
	}


	public void setClient_IPv4(String client_IPv4) {
		this.client_IPv4 = client_IPv4;
	}


	public CityNetwork getCityNetwork() {
		return cityNetwork;
	}


	public void setCityNetwork(CityNetwork cityNetwork) {
		this.cityNetwork = cityNetwork;
	}
	
	
	
	
	

}
