package csye6220.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CityGateway")
public class CityGateway {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cityGatewayID", unique=true, nullable=false)
	private int cityGatewayID;
	
	
    @OneToOne(mappedBy = "cityGateway", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
	private CityNetwork cityNetwork;
	
	
    private String internal_IP;
    
	private int OSPF_areaID;
	
	private String deviceName;
	

	private  String firmware;
	
	private int year;
	
	
	public CityGateway() {
		// TODO Auto-generated constructor stub
	}

	public int getCityGatewayID() {
		return cityGatewayID;
	}

	public void setCityGatewayID(int cityGatewayID) {
		this.cityGatewayID = cityGatewayID;
	}

	public int getOSPF_areaID() {
		return OSPF_areaID;
	}

	public void setOSPF_areaID(int oSPF_areaID) {
		OSPF_areaID = oSPF_areaID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	public CityNetwork getCityNetwork() {
		return cityNetwork;
	}

	public void setCityNetwork(CityNetwork cityNetwork) {
		this.cityNetwork = cityNetwork;
	}

	public String getInternal_IP() {
		return internal_IP;
	}

	public void setInternal_IP(String internal_IP) {
		this.internal_IP = internal_IP;
	}
	
	
}




