<?php
    $con = mysqli_connect("localhost", "root", "", "moim");
    mysqli_query($con,'SET NAMES utf8');

       // $userName["userName"] = $userName;
       // $userNick["userNick"] = $userNick;
       // $userID["userID"] = $userID;
       // $userPW["userPW"] = $userPW;
       // $userPhone["userPhone"] = $userPhone;
       // $userEmail["userEmail"] = $userEmail;
        
        $userName = $_POST['userName'];
        $userNik = $_POST['userNik'];
        $userID = $_POST['userID'];
        $userPW = $_POST['userPW'];
        $userPhone = $_POST['userPhone'];
        $userEmail= $_POST['userEmail'];
        $userGender =  $_POST['userGender'];
        $userMajor = $_POST['userMajor'];



    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssssssss", $userName, $userNik, $userID, $userPW, $userPhone, $userEmail,$userGender,$userMajor);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;


    echo json_encode($response);

?>