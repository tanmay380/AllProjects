<?php
      include('connections.php');

      $qry="SELECT * FROM class_table";
      $row1=mysqli_query($conn, $qry);
      $res=mysqli_fetch_all($row1, MYSQLI_ASSOC);
    //   foreach($res as $row){
    //     echo $row["c_id"] . " ". $row["cname"]."   ".PHP_EOL;
    //   }
    echo json_encode($res);


?>