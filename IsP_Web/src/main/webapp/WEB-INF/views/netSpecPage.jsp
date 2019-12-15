<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>NetSpec Page</title>
</head>
<body>

<h2>Script</h2>

ifconfig eth0 <Strong>${clientIP.client_IPv4}</Strong> netmask <Strong>${clientIP.cityNetwork.netMask}</Strong> up <br>
route add default gw <Strong>${clientIP.cityNetwork.cityGateway.internal_IP}</Strong> <br>
echo "nameserver <Strong>${clientIP.cityNetwork.DNS_Server}</Strong>" > /etc/resolv.conf <br>

<br>

<h2>--- Upstream configuration ---</h2>
 "configure interface cable-downstream 9/scq/0 type docsis cable-mac 301" <br>
 "configure interface cable-downstream 9/scq/0 cable frequency 261000000"<br>
 "configure interface cable-downstream 9/scq/0 cable primary-capable"<br>
 "configure interface cable-downstream 9/scq/0 cable no shutdown"<br>
 "configure interface cable-downstream 9/scq/0 cable min_link_speed  20 Mbps"<br>
 "configure interface cable-downstream 9/scq/0 cable max_link_speed <Strong>30</Strong> Mbps"<br>

<h2>--- Downstream configuration ---</h2>
"configure interface cable-downstream 9/scq/0 type docsis cable-mac 301"<br>
"configure interface cable-downstream 9/scq/0 cable frequency 261000000"<br>
"configure interface cable-downstream 9/scq/0 cable primary-capable"<br>
"configure interface cable-downstream 9/scq/0 cable no shutdown"<br>
"configure interface cable-downstream 9/scq/0 cable min_link_speed  20 Mbps"<br>
"configure interface cable-downstream 9/scq/0 cable max_link_speed  <Strong>100</Strong> Mbps"<br>

</body>
</html>