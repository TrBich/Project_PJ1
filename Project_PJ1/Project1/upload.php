<?php
if(isset($_POST["submit"])) {
    // $file = file_get_contents($_FILES["fileToUpload"]["tmp_name"]);
    // $xml=simplexml_load_file(file_get_contents($file));
    // echo $xml;
    $servername = "localhost";
	$username = "root";
	$password = "";
	$dbName = "datanode";
	$conn = new mysqli($servername, $username, $password,$dbName);

// Check connection
	if ($conn->connect_error) {
	    die("Connection failed: " . $conn->connect_error);
	}
     switch ($_FILES['fileToUpload']['error']) {
        case UPLOAD_ERR_OK:
        $delete = "DELETE FROM landmonitoring";
        $xml = simplexml_load_file($_FILES['fileToUpload']['tmp_name']);
			foreach($xml->children() as $child){
			 	$table = $child->Table;
			 	if(!empty($table)){
			 		$index = 0;
			 		$sql  = '';
			 		if(!empty($table->children())){
			 				$size = count($table->children());
			 				$sql  = 'INSERT INTO landmonitoring(nhietdo,doam,ph,DA_dat,PIR,EC_meter,anhsang,Pin,date) VALUES ';
			 				foreach ($table->children() as  $row) {

			 				# code...
			 				if($index >= 2){
			 					
			 					$nhietdo = $row->children()[0]->Data;
			 					$doam = $row->children()[1]->Data;
			 					$ph = $row->children()[2]->Data;
			 					$DA_dat = $row->children()[3]->Data;
			 					$PIR = $row->children()[4]->Data;
			 					$EC_meter = $row->children()[5]->Data;
			 					$anhsang = $row->children()[6]->Data;
			 					$Pin = $row->children()[7]->Data;
			 					$date = $row->children()[8]->Data;
			 					$dateFormat = new DateTime($date);
								$dateInsert =  $dateFormat->format('Y-m-d H:i:s');
								if($index < $size - 1)
									$sql = $sql . "('$nhietdo','$doam','$ph','$DA_dat','$PIR','$EC_meter','$anhsang','$Pin','$dateInsert'),";
								else{

									$sql = $sql . "('$nhietdo','$doam','$ph','$DA_dat','$PIR','$EC_meter','$anhsang','$Pin','$dateInsert');";
								}
						
			 					}

							$index++;
			 				
			 				}
			 				if(mysqli_query($conn,$delete)){
			 					if (mysqli_query($conn, $sql)) {
			   							echo "Nhập dữ liệu thành công";
								} else {
				   						echo "Có lỗi trong quá trình nhập dữ liệu";
								}

			 				}else{
			 							echo "Có lỗi trong quá trình nhập dữ liệu";
			 				}
			 				
			 			
			 			}
				 
			 	}
			}
			 	
            break;
        case UPLOAD_ERR_NO_FILE:
            throw new RuntimeException('No file sent.');
        case UPLOAD_ERR_INI_SIZE:
        case UPLOAD_ERR_FORM_SIZE:
            throw new RuntimeException('Exceeded filesize limit.');
        default:
            throw new RuntimeException('Unknown errors.');
    }

}
?>