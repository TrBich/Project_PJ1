<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbName = "datanode";
$conn = new mysqli($servername, $username, $password,$dbName);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$query = 'SELECT * FROM landmonitoring order by date ASC';
$results = array();
$data  = mysqli_query($conn,$query);
while($row = mysqli_fetch_array($data))
{
   $results[] = array(
      'id' => $row['id'],
      'nhietdo' => $row['nhietdo'],
      'doam' => $row['doam'],
      'ph' => $row['ph'],
      'DA_dat' => $row['DA_dat'],
      'PIR' => $row['PIR'],
      'EC_meter' => $row['EC_meter'],
      'anhsang' => $row['anhsang'],
       'Pin' => $row['Pin'],
      'date' => $row['date']
   );
}
echo json_encode($results);

?>