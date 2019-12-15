package csye6220.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="ServicePackage")
public class ServicePackage {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="servicePackageID", unique=true, nullable=false)
	private int servicePackageID;
	
	private String packageName;
	
	private Double indroductoryPrice;
	
	private int contractDuration;
	
	@Column(length = 65535, columnDefinition = "text")
	@Type(type="text")
	private String packageDescription;
	
	private double downloadSpeed;
	
	private double downStreamSpeed;
	
	private double upStreamSpeed;
	
	public ServicePackage() {
		// TODO Auto-generated constructor stub
	}
	

	public int getServicePackageID() {
		return servicePackageID;
	}

	public void setServicePackageID(int servicePackageID) {
		this.servicePackageID = servicePackageID;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Double getIndroductoryPrice() {
		return indroductoryPrice;
	}

	public void setIndroductoryPrice(Double indroductoryPrice) {
		this.indroductoryPrice = indroductoryPrice;
	}

	public int getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(int contractDuration) {
		this.contractDuration = contractDuration;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public double getDownloadSpeed() {
		return downloadSpeed;
	}

	public void setDownloadSpeed(double downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public double getDownStreamSpeed() {
		return downStreamSpeed;
	}

	public void setDownStreamSpeed(double downStreamSpeed) {
		this.downStreamSpeed = downStreamSpeed;
	}

	public double getUpStreamSpeed() {
		return upStreamSpeed;
	}

	public void setUpStreamSpeed(double upStreamSpeed) {
		this.upStreamSpeed = upStreamSpeed;
	}
	
	
	
	

}
