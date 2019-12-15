package csye6220.model;

import java.util.ArrayList;
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
@Table(name="CityNetwork")
public class CityNetwork {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cityNetworkID", unique=true, nullable=false)
	private int cityNetworkID;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cityGatewayID")
	private CityGateway cityGateway;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="cityNetwork", cascade = CascadeType.ALL)
	private List<ClientIP> client_IPv4;
	

	private String network;

	private String broadCastAddress;
	
	private String netMask;
	
	private String DNS_Server;
	
	private String DHCP_Server;

	public int getCityNetworkID() {
		return cityNetworkID;
	}

	public void setCityNetworkID(int cityNetworkID) {
		this.cityNetworkID = cityNetworkID;
	}

	public CityGateway getCityGateway() {
		return cityGateway;
	}

	public void setCityGateway(CityGateway cityGateway) {
		this.cityGateway = cityGateway;
	}

	public String getBroadCastAddress() {
		return broadCastAddress;
	}

	public void setBroadCastAddress(String broadCastAddress) {
		this.broadCastAddress = broadCastAddress;
	}

	public String getNetMask() {
		return netMask;
	}

	public void setNetMask(String netMask) {
		this.netMask = netMask;
	}

	public String getDNS_Server() {
		return DNS_Server;
	}

	public void setDNS_Server(String dNS_Server) {
		DNS_Server = dNS_Server;
	}

	public String getDHCP_Server() {
		return DHCP_Server;
	}

	public void setDHCP_Server(String dHCP_Server) {
		DHCP_Server = dHCP_Server;
	}
	
	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
	
	public List<ClientIP> getClient_IPv4() {
		return client_IPv4;
	}

	public void setClient_IPv4(List<ClientIP> client_IPv4) {
		this.client_IPv4 = client_IPv4;
	}

	
	
	
	
	

}
