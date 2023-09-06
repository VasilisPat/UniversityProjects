<?php

require_once('functions.php');

if(isset($_POST['action'])){

    if($_POST['action']=='retrieveProducts' && isset($_POST['productCategory'])){
        echo json_encode(retrieveProducts($_POST['productCategory']));
    }

    if($_POST['action']=='retrieveProductByID' && isset($_POST['productID'])){
        echo json_encode(retrieveProductByID($_POST['productID']));
    }
    
}

function connectDB():PDO {

    $dbHost="localhost";
    $dbPort = 3306;
    $dbName="eshop";
    $dbUser="eshop";
    $dbPass = 12345;

    try{
        $db = new PDO("mysql:host=$dbHost:$dbPort;dbname=$dbName;charset=utf8", $dbUser, $dbPass);
        $db->setAttribute(PDO::MYSQL_ATTR_INIT_COMMAND, "SET NAMES 'utf8' COLLATE 'utf8_general_ci' ");
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $db;
    }
    catch(PDOException $ex) {
        echo $ex->getMessage();
        die();
    }

}

function retrieveProducts($productCategory):array{
    $db=connectDB();

    $stm = $db->prepare("SELECT * FROM products WHERE ProductCategory=:productCategory OR 'All'=:productCategory");
    $params = [':productCategory' => $productCategory];

    try{
        $stm->execute($params);
        $results = $stm->fetchAll(\PDO::FETCH_ASSOC);
        return $results;
    }catch(PDOException $ex){
        echo $ex->getMessage();
        die();
    }
}

function retrieveProductByID($productID):array{
    $db=connectDB();
    
    $stm = $db->prepare("SELECT * FROM products WHERE ProductID=:productID");
    $params = [':productID' => $productID];
    
    try{
        $stm->execute($params);
        $results = $stm->fetch(\PDO::FETCH_ASSOC);
        return $results;
    }catch(PDOException $ex){
        echo $ex->getMessage();
        die();
    }
}