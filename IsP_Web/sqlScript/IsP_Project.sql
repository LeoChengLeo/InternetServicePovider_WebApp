

--Customers Table

CREATE TABLE Customers
(
CustomerID int IDENTITY(1000,10) NOT NULL PRIMARY KEY,
UserName varchar(40) NOT NULL,
PasswordHash varchar(40) NOT NULL,
FirstName varchar(40) NOT NULL,
LastName varchar(40) Not null,
Email varchar(80) not null,
PhoneNumber varchar(80) not null,
DateOfBirth Date not null
)

CREATE FUNCTION CheckIfUsed (@PName varchar(30))
RETURNS int
AS
BEGIN
   Declare @check int 
   select  @check=count(CustomerID) from Customers where UserName=@PName
   RETURN @check
END;

--Check if user name has been used 
ALTER TABLE Customers ADD CONSTRAINT UniqueUserName CHECK (dbo.CheckIfUsed(UserName)= 0);




--ServicePackage Table

CREATE TABLE ServicePackage
(
PackageID int IDENTITY NOT NULL PRIMARY KEY,
PackageName varchar(50) Not NULL,
Introductory_Price money Not null,
Contract_Duration  int Not null,
Package_Description TEXT,
Download_Spead float not null,
UpStreamSpeed float not null,
DownStreamSpeed float not null
)


--Gateway Table

CREATE TABLE CityGateway
(
CityGatewayID int IDENTITY NOT NULL PRIMARY KEY,
DeviceName varchar(40) not null,
OSPF_Area int Not null,
Firmware varchar(50) not null,
SerialNumber varchar(80) not null,
[Year] int not null
)




--CityNetwork Table

CREATE TABLE CityNetwork
(
CityNetworkID int IDENTITY NOT NULL PRIMARY KEY,
CityGatewayID int references CityGateway(CityGatewayID),
Network varchar(50) not null,
BroadCastAddress varchar(50) not null,
Netmask varchar(50) not null, 
InternalGateway varchar(50) not null,
DNS_Server varchar(50) not null,
DHCP_Server varchar(50) not null
)



--City Table
CREATE TABLE City
(
CityID int IDENTITY NOT NULL PRIMARY KEY,
CityNetworkID INT REFERENCES CityNetwork(CityNetworkID),
CityName varchar(30)
)





--GatewayDeviceID Table

CREATE TABLE GatewayDeviceIP
(
GatewayDeviceIP varchar(30) NOT NULL PRIMARY KEY,
CityNetworkID INT REFERENCES CityNetwork(CityNetworkID)
)




-- AreaGateway Table
CREATE TABLE AreaGateway
(
AreaGatewayID int IDENTITY NOT NULL PRIMARY KEY,
DeviceIP varchar(30) references GatewayDeviceIP(GatewayDeviceIP),
DeviceName varchar(40) not null,
Firmware varchar(50) not null,
SerialNumber varchar(80) not null,
[Year] int not null
)


--AreaSubnet Table

CREATE TABLE AreaSubnet
(
AreaSubnetID int IDENTITY NOT NULL PRIMARY KEY,
AreaGateway int references AreaGateway(AreaGatewayID),
Network varchar(50) not null,
BroadCastAddress varchar(50) not null,
Netmask varchar(50) not null, 
InternalGateway varchar(50) not null,
DNS_Server varchar(50) not null,
DHCP_Server varchar(50) not null
)





--Zipcode Table 

CREATE TABLE zipcode
(
ZipCodeID int IDENTITY NOT NULL PRIMARY KEY,
AreaSubnetID int references AreaSubnet(AreaSubnetID),
CityID int references City(CityID),
ZipcodeNumber int
)


--ClientIP Table

CREATE TABLE ClientIP
(
Client_IPv4 varchar(50) not null PRIMARY KEY,
AreaSubnetID int references AreaSubnet(AreaSubnetID),
)




--StreetNumber Table

CREATE TABLE streetNumber
(
streetNumberID int IDENTITY NOT NULL PRIMARY KEY,
ZipcodeID  int references Zipcode(ZipcodeID),
HouseNumber   int not null,
StreetName  varchar(20) not null
)




--ServiceCenterAddress Table

CREATE TABLE ServiceCenterAddress
(
ServiceCenterAddressID int IDENTITY NOT NULL PRIMARY KEY,
CityID  int references City(CityID),
ZipcodeID int references Zipcode(ZipcodeID),
StreetNumberID int references StreetNumber(StreetNumberID)
)



--ServiceCenter Table

CREATE TABLE ServiceCenter
(
ServiceCenterID INT IDENTITY not null PRIMARY KEY,
ServiceCenterAddressID int references ServiceCenterAddress(ServiceCenterAddressID),
StoreName varchar(50) not null,
ServiceDescription text,
[Hours] text,
Phone_Number varchar(30) not null
)


-- CustomerServiceDetail Table

Create table CustomerServiceDetail
(
CustomerID INT REFERENCES Customers(CustomerID),
PackageID INT REFERENCES ServicePackage(PackageID),
CityID INT REFERENCES City(CityID),
ZipcodeID INT REFERENCES Zipcode(ZipcodeID),
StreetNumberID INT REFERENCES StreetNumber(StreetNumberID),
StartServiceDate date default getdate(),
EndServiceDate date,
CONSTRAINT PKCustomerServiceDetail Primary key clustered (CustomerID, PackageID)
)




--IPAddressBind Table


Create table IPaddressBind
(
ClientIP varchar(50) REFERENCES ClientIP(Client_IPv4),
StreetNumberID INT REFERENCES  StreetNumber(StreetNumberID)
CONSTRAINT PK_IPaddressBind Primary key clustered (ClientIP,StreetNumberID)
)




CREATE FUNCTION CheckStreetNumberIDIsBind (@StreetNumberID INT)
RETURNS int
AS
BEGIN
   Declare @check int 
   select  @check=count(CustomerID) from IPaddressBind where StreetNumberID=@streetNumberID
   RETURN @check
END;

--Check StreetNumberID is not bind with any IP
ALTER TABLE CustomerServiceDetail ADD CONSTRAINT StreetNumberIsNotBind CHECK (dbo.CheckStreetNumberIDIsBind(StreetNumberID)= 0);












--Insert ServicePackage Samples

--Mbps , per month,  month for duration
Insert into ServicePackage(PackageName,Introductory_Price,Contract_Duration,Download_Spead,UpStreamSpeed,DownStreamSpeed)
values('Performance Starter',29.99,12,15,2,15)

Insert into ServicePackage(PackageName,Introductory_Price,Contract_Duration,Download_Spead,UpStreamSpeed,DownStreamSpeed)
values('Performance Plus',32.99,12,60,15,60)

Insert into ServicePackage(PackageName,Introductory_Price,Contract_Duration,Download_Spead,UpStreamSpeed,DownStreamSpeed)
values('Performance Pro',35.99,12,80,25,80)

Insert into ServicePackage(PackageName,Introductory_Price,Contract_Duration,Download_Spead,UpStreamSpeed,DownStreamSpeed)
values('Gigabt',90,12,1000,25,1000)

Insert into ServicePackage(PackageName,Introductory_Price,Contract_Duration,Download_Spead,UpStreamSpeed,DownStreamSpeed)
values('Gigabt Pro',120,12,1000,35,1000)

update ServicePackage set Package_Description='Pricing per month plus taxes for length of contract, unless otherwise stated. Additional fees and terms may apply. Pricing may or may not reflect promotional, bundle and/or other offers available. Pricing varies by location and availability. All prices subject to change at any time. May or may not be available based on service address. Speeds may vary.'






--Insert CityGateway Samples

insert CityGateway 
values ('PTX1000 Packet Transport',2,'1,5.0(2)N2(1) [build 5.0(2)N2(1)]','JAF1302ABDP',2015)

insert CityGateway 
values ('PTX1000 Packet Transport',3,'1,5.0(2)N2(1) [build 5.0(2)N2(1)]','JAF130ASEV',2017)

insert CityGateway 
values ('PTX1000 Packet Transport',4,'1,5.0(2)N2(1) [build 5.0(2)N2(1)]','JDSA130SBDP',2016)

insert CityGateway 
values ('PTX1000 Packet Transport',5,'1,5.0(2)N2(1) [build 5.0(2)N2(1)]','ASF13F2ABDP',2014)

insert CityGateway 
values ('Cisco 900 Series',6,'1,4.5(2)N2(1)','JSORFF2ABDP',2014)

insert CityGateway 
values ('Cisco ISR 1000',7,'2,5.5(2)N2(1)','JSORFRTGBDP',2018)

insert CityGateway 
values ('Cisco ASR 1002',8,'3,5.5(2)N2(1)','TYORFRTGBDP',2013)

insert CityGateway 
values ('Cisco ASR 901',9,'3,5.5(2)N2(1)','LYORFRTFBVP',2014)

insert CityGateway 
values ('Cisco ASR 1001 HX',10,'3,5.5(2)N2(1)','LYORFRSFCSP',2014)

insert CityGateway 
values ('Cisco ASR 1004 X',11,'3,5.5(2)N2(1)','LYORFRSFFCP',2016)






--Insert CityNetwork Samples

insert CityNetwork
values (1,'10.1.0.0/16','10.1.255.255','255.255.0.0','10.1.0.1','10.1.0.1','10.1.0.1')

insert CityNetwork
values (2,'10.2.0.0/16','10.2.255.255','255.255.0.0','10.2.0.1','10.2.0.1','10.2.0.1')

insert CityNetwork
values (3,'10.3.0.0/16','10.3.255.255','255.255.0.0','10.3.0.1','10.3.0.1','10.3.0.1')

insert CityNetwork
values (4,'10.4.0.0/16','10.4.255.255','255.255.0.0','10.4.0.1','10.4.0.1','10.4.0.1')

insert CityNetwork
values (5,'10.5.0.0/16','10.5.255.255','255.255.0.0','10.5.0.1','10.5.0.1','10.5.0.1')

insert CityNetwork
values (6,'10.6.0.0/16','10.6.255.255','255.255.0.0','10.6.0.1','10.6.0.1','10.6.0.1')

insert CityNetwork
values (7,'10.7.0.0/16','10.7.255.255','255.255.0.0','10.7.0.1','10.7.0.1','10.7.0.1')

insert CityNetwork
values (8,'10.8.0.0/16','10.8.255.255','255.255.0.0','10.8.0.1','10.8.0.1','10.8.0.1')

insert CityNetwork
values (9,'10.9.0.0/16','10.9.255.255','255.255.0.0','10.9.0.1','10.9.0.1','10.9.0.1')

insert CityNetwork
values (10,'10.10.0.0/16','10.10.255.255','255.255.0.0','10.10.0.1','10.10.0.1','10.10.0.1')





--INSERT City Samples

insert City values(1,'Boston')
insert City values(2,'Cambridge')
insert City values(3,'Medford')
insert City values(4,'Somerville')
insert City values(5,'Brookline')
insert City values(6,'Malden')
insert City values(7,'Lowell')
insert City values(8,'Winchester')
insert City values(9,'Arlington')
insert City values(10,'Allston')




-- Insert GatewayDeviceIP Samples

DECLARE @p_IP varchar(50)
SET @p_IP='10.50.'

DECLARE @C INT
SET @C=1

WHILE @C<=10

BEGIN
  
    DECLARE @IP varchar(50)
    SET @IP=CONCAT(@p_IP,@C)

	DECLARE @COUNT INT
	SET @COUNT=2

	while @COUNT<=254
	BEGIN

	DECLARE @finalIP varchar(50)

	SET @finalIP=CONCAT(@IP,'.',@COUNT)
	PRINT @finalIP
	
	insert GatewayDeviceIP values(@finalIP,@C)

	SET @COUNT=@COUNT+1
	END
     

SET @C=@C+1

END








--Insert Networking Info Sample on each City Samples



--BOSTON   
   
   --ServiceCenter address in boston: 130 Congress St, Boston, MA 02110  
	insert ServiceCenterAddress values(1,1,4)
	insert ServiceCenter values(1,'IsP MA service center','xx','xx','800-266-2278')

	update ServiceCenter set ServiceDescription='You can subscribe to IsPMA Services including Cable, High Speed Internet.
	You can also make a payment or pick up and exchange equipment'  where ServiceCenterID=1

	update ServiceCenter set Hours='
	Monday	9AM�7PM
	Tuesday	9AM�7PM
	Wednesday	9AM�7PM
	Thursday	9AM�7PM
	Friday	9AM�7PM
	Saturday	9AM�7PM
	Sunday	11AM�4PM'   WHERE ServiceCenterID=1





	  

	--02110
	insert AreaGateway 
	values ('10.50.1.2','Cisco ASR 901','3,5.5(2)N2(1)','LYORFRUDIOS',2013)

	insert AreaSubnet
	values (1,'10.68.1.0/24','10.68.1.255','255.255.255.0','10.68.1.1','10.68.1.1','10.68.1.1')

	insert zipcode
	values(1,02110,1)

	declare @p VARCHAR(50)
	SET @p='10.68.1.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),1)
	SET @COU=@COU+1
	END

	
	--127 ~ 141 Congress St, Boston, MA 02110
	

	
	declare @c int 
	set @c=127
	while @c<=141
		begin
		insert streetNumber values(1,@c,'Congress St')
		set @c=@c+1
		end

	


	--02115
	insert AreaGateway 
	values ('10.50.1.3','Cisco ASR 1001','4,5.5(2)N2(1)','lOSPFRUDIOS',2017)

	insert AreaSubnet
	values (11,'10.68.2.0/24','10.68.2.255','255.255.255.0','10.68.2.1','10.68.2.1','10.68.2.1')

	insert zipcode
	values(11,02115,1)

	declare @p VARCHAR(50)
	SET @p='10.68.2.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),11)
	SET @COU=@COU+1
	END

	--465 Huntington Ave, Boston, MA 02115
	--400 Huntington Ave, Boston, MA 02115
	--309 Huntington Ave, Boston, MA 02115


	SELECT * FROM zipcode WHERE ZipcodeNumber=02115
	SELECT* FROM zipcode

    declare @c int 
	set @c=309
	while @c<=465
		begin
		insert streetNumber values(11,@c,'Huntington Ave')
		set @c=@c+1
		end
   
   SELECT* FROM streetNumber WHERE ZipcodeID=11



   select*from City where CityName='Cambridge'
   select * from zipcode where ZipcodeNumber='02139'
   select * from streetNumber where HouseNumber=389 and ZipcodeID=2

--Cambridge 

    -- ServiceCenter address in cambridge: 389 Binney St, Cambridge, MA 02139
	insert ServiceCenterAddress values(2,2,207)
    insert ServiceCenter values(2,'IsP MA service center','xx','xx','800-377-4478')

	update ServiceCenter set ServiceDescription='You can subscribe to IsPMA Services including Cable, High Speed Internet.
	You can also make a payment or pick up and exchange equipment'  where ServiceCenterID=2

	update ServiceCenter set Hours='
	Monday	9AM�7PM
	Tuesday	9AM�7PM
	Wednesday	9AM�7PM
	Thursday	9AM�7PM
	Friday	9AM�7PM
	Saturday	9AM�7PM
	Sunday	11AM�4PM'   WHERE ServiceCenterID=2



	--02139
	insert AreaGateway 
	values ('10.50.2.2','Cisco ASR 900','4,4.5(2)N2(1)','AYORFRUFCSS',2015)

	insert AreaSubnet
	values (2,'10.69.1.0/24','10.69.1.255','255.255.255.0','10.69.1.1','10.69.1.1','10.69.1.1')

	insert zipcode
	values(2,02139,2)


	declare @p VARCHAR(50)
	SET @p='10.69.1.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),2)
	SET @COU=@COU+1
	END

	--355 Binney St, Cambridge, MA 02139 ~ 399 Binney St, Cambridge, MA 02139

	SELECT * FROM zipcode WHERE ZipcodeNumber=02139

	declare @c int 
	set @c=355
	while @c<=399
		begin
		insert streetNumber values(2,@c,'Binney St')
		set @c=@c+1
		end

	SELECT * FROM streetNumber WHERE ZipcodeID=2



    --02138
	insert AreaGateway 
	values ('10.50.2.3','Cisco ASR 801','4,4.5(2)N2(1)','JSKORFRUFCSS',2014)

	insert AreaSubnet
	values (12,'10.69.2.0/24','10.69.2.255','255.255.255.0','10.69.2.1','10.69.2.1','10.69.2.1')

	insert zipcode
	values(12,02138,2)


	declare @p VARCHAR(50)
	SET @p='10.69.2.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),12)
	SET @COU=@COU+1
	END

	
	--60 Chilton St, Cambridge, MA 02138
	--62 Chilton St, Cambridge, MA 02138
	--70 Chilton St, Cambridge, MA 02138


	SELECT * FROM zipcode WHERE ZipcodeNumber=02138

   declare @c int 
	set @c=60
	while @c<=70
		begin
		insert streetNumber values(12,@c,'Binney St')
		set @c=@c+1
		end

	SELECT * FROM streetNumber 



--MEDFORD
	--02145
	insert AreaGateway 
	values ('10.50.3.2','Cisco ASR 920','5,3.5(2)N2(1)','AYOSERUFCSS',2014)

	insert AreaSubnet
	values (3,'10.70.1.0/24','10.70.1.255','255.255.255.0','10.70.1.1','10.70.1.1','10.70.1.1')

	insert zipcode
	values(3,02145,3)

	declare @p VARCHAR(50)
	SET @p='10.70.1.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),3)
	SET @COU=@COU+1
	END





--SOMERVILLE
	--02129
	insert AreaGateway 
	values ('10.50.4.2','Cisco ASR 1006','4,6.5(2)N2(1)','AYORFRUFCSS',2016)

	insert AreaSubnet
	values (4,'10.71.1.0/24','10.71.1.255','255.255.255.0','10.71.1.1','10.71.1.1','10.71.1.1')

	insert zipcode
	values(4,02129,4)

	declare @p VARCHAR(50)
	SET @p='10.71.1.'

	DECLARE @COU INT
	SET @COU=2

	WHILE @COU<=254
	BEGIN

	insert ClientIP values (CONCAT(@p,@COU),4)
	SET @COU=@COU+1
	END



--BOOKLINE
	--02445
	insert AreaGateway 
	values ('10.50.5.2','Cisco ASR 1006','4,6.5(2)N2(1)','AYORFRUFCSS',2016)

	insert AreaSubnet
	values (5,'10.72.1.0/24','10.72.1.255','255.255.255.0','10.72.1.1','10.72.1.1','10.72.1.1')

	insert zipcode
	values(5,02445,5)


	


--MALDEN
	--02180
	insert AreaGateway 
	values ('10.50.6.2','Cisco ASR 1004','4,6.5(2)N2(1)','AYORFERFXSS',2015)

	insert AreaSubnet
	values (6,'10.73.1.0/24','10.73.1.255','255.255.255.0','10.73.1.1','10.73.1.1','10.73.1.1')


	insert zipcode
	values(6,02180,6)


		

--LOWELL
	--02155
	insert AreaGateway 
	values ('10.50.7.2','Cisco ASR 9001','5,6.5(2)N2(1)','AYPOLKRFXSS',2017)

	insert AreaSubnet
	values (7,'10.74.1.0/24','10.74.1.255','255.255.255.0','10.74.1.1','10.74.1.1','10.74.1.1')


	insert zipcode
	values(7,02155,7)
	

					

--WINCHESTER
	--01890
	insert AreaGateway 
	values ('10.50.8.2','Cisco ASR 9001','4,6.5(2)N2(1)','AOORFERFSPO',2018)

	insert AreaSubnet
	values (8,'10.75.1.0/24','10.75.1.255','255.255.255.0','10.75.1.1','10.75.1.1','10.75.1.1')

	insert zipcode
	values(8,01890,8)



--ARLINGTON
	--02474
	insert AreaGateway 
	values ('10.50.9.2','Cisco ASR 4331','4,5.5(2)N2(1)','AYUIFERFSPO',2015)
	insert AreaSubnet
	values (9,'10.76.1.0/24','10.76.1.255','255.255.255.0','10.76.1.1','10.76.1.1','10.76.1.1')

	insert zipcode
	values(9,02474,9)


--ALLSTON
	--02134
	insert AreaGateway 
	values ('10.50.10.2','Cisco ASR 9001','4,6.5(2)N2(1)','ASFSFERFSPO',2016)

	insert AreaSubnet
	values (10,'10.77.1.0/24','10.77.1.255','255.255.255.0','10.77.1.1','10.77.1.1','10.77.1.1')

	insert zipcode
	values(10,02134,10)









--Necessary triger, when deleting a customerServiceDetail the IP need to be released from Bind table

CREATE TRIGGER UNBindIPaddress
on CustomerServiceDetail
After Delete
AS
BEGIN

delete from IPaddressBind where StreetNumberID in  (SELECT StreetNumberID from deleted)

END






--UtilFunctions


--Function1

CREATE FUNCTION getFreeClientIpByAreaSubnetID
(@SubnetID INT)
RETURNS VARCHAR(50)
as
	Begin

	DECLARE @freeIP VARCHAR(50)

	SET @freeIP=
	    (SELECT TOP(1) C.Client_IPv4 FROM ClientIP c
		LEFT join IPaddressBind b
		on c.Client_IPv4=b.ClientIP
		WHERE AreaSubnetID=@SubnetID AND b.StreetNumberID is null
		order by c.Client_IPv4)

	RETURN @freeIP
end




--Function2

CREATE FUNCTION getFreeGatewayDeviceIpByCityNetworkID
(@cityNetworkID INT)
RETURNS VARCHAR(50)
as
Begin
	DECLARE @freeIP VARCHAR(50)

	SET @freeIP=(select Top(1) gtyIP.GatewayDeviceIP from GatewayDeviceIP  gtyIP
	left join AreaGateway areaGty
	on gtyIP.GatewayDeviceIP=areaGty.DeviceIP where gtyIP.CityNetworkID=@cityNetworkID
	and areaGty.AreaGatewayID is null)
	
	RETURN @freeIP
end



print dbo.getFreeGatewayDeviceIpByCityNetworkID(1)










--UtilProcedure 



--encrypt user password to hash
create procedure dbo.addCustomer
	@UserName varchar(40),
	@pass varchar(40),
	@FirstName varchar(40),
	@LastName varchar(40),
	@email varchar(80),
	@phoneNumber varchar(80),
	@birthday Date
as
Begin 
	
	begin try
	
	insert into Customers 
	(UserName,PasswordHash,FirstName,LastName,Email,PhoneNumber,DateOfBirth)
	values(@UserName,HashBytes('SHA1',@pass),@FirstName,@LastName,@email,@phoneNumber,@birthday)

	select @@IDENTITY
	
	end try

	begin catch
	
	select ERROR_MESSAGE()

	end catch 
end

--test
EXEC dbo.addCustomer
@UserName='',
@pass='',
@FirstName='',
@LastName='',
@email='',
@phoneNumber='',
@birthday=''










--add a new customerService Detail and bind a IP to customer address

create procedure addCustomerServiceDetail
@CustomerID int,
@PackageID int,
@CityID int,
@ZipcodeID int,
@StreetNumberID int,
@StartServiceDate date
AS
BEGIN 

	BEGIN TRY
	
	DECLARE @EndServiceDate Date
	DECLARE @Duration INT

	SET @Duration=(SELECT Contract_Duration FROM ServicePackage WHERE PackageID=@PackageID)
	SET @EndServiceDate= DATEADD(month,@Duration,@StartServiceDate)

	insert into CustomerServiceDetail (CustomerID,PackageID,CityID,ZipcodeID,StreetNumberID,StartServiceDate,EndServiceDate)
    values(@CustomerID,@PackageID,@CityID,@ZipcodeID,@StreetNumberID,@StartServiceDate,DATEADD(month,12, convert( date,GETDATE())))
	

	PRINT @@IDENTITY 



	Declare @NextFreeIP varchar(50)
	set @NextFreeIP=dbo.getFreeClientIpByAreaSubnetID((SELECT AreaSubnetID FROM zipcode where ZipCodeID=@ZipcodeID))

	insert IPaddressBind values(@NextFreeIP,@StreetNumberID)

	PRINT @@IDENTITY 

	END TRY 

	BEGIN CATCH 

	SELECT ERROR_MESSAGE()

	END CATCH 

END 



--check dependency
select top(1) streetNumberID from streetNumber where ZipcodeID=@ZipcodeID
and streetNumberID  not in (select streetNumberID from IPaddressBind) 


--test
EXEC dbo.addCustomerServiceDetail
@CustomerID=1100,
@PackageID=4,
@CityID=1,
@ZipcodeID=1,
@StreetNumberID=3,
@StartServiceDate='2019-11-20'










-- View1 (quick review activated Costomer and corresponding IPv4 )

CREATE VIEW activatedCustomerReview AS
select customer.UserName, customer.CustomerID, 
CONCAT(sn.HouseNumber,' ',sn.StreetName,', ',c.CityName,', MA, ',z.ZipcodeNumber) as [Activated Address],
ipBind.ClientIP as activatedIPv4
from CustomerServiceDetail serviceDetail
join streetNumber sn on serviceDetail.StreetNumberID=sn.streetNumberID
join City c on serviceDetail.CityID=c.CityID
join zipcode z on serviceDetail.ZipcodeID=z.ZipCodeID
join Customers customer on serviceDetail.CustomerID=customer.CustomerID
join IPaddressBind ipBind on serviceDetail.StreetNumberID =ipBind.StreetNumberID;


SELECT * FROM dbo.activatedCustomerReview




-- View2 (Show all ServiceCenterInfo)

create view showServiceCentersInfo
as
WITH ServiceCenterAddressInfo as
(select sca.ServiceCenterAddressID ,CONCAT(sn.HouseNumber,' ',sn.StreetName,', ',c.CityName,', MA, ',z.ZipcodeNumber) as ServiceCenterAddress 
from ServiceCenterAddress sca
join City c on sca.CityID=c.CityID
join zipcode z on sca.ZipcodeID=z.ZipCodeID
join streetNumber sn on sca.StreetNumberID=sn.streetNumberID)
select  sc.ServiceCenterID, sc.StoreName, sc.Phone_Number, sc.ServiceDescription, sc.Hours,
scai.ServiceCenterAddress
from ServiceCenter sc join ServiceCenterAddressInfo scai
on sc.ServiceCenterAddressID=scai.ServiceCenterAddressID;


select * from dbo.showServiceCentersInfo











