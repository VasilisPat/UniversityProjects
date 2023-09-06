<?php

require_once('functions.php');

if(isset($_POST['action'])){

    if($_POST['action']=='createCookie'){
        createCookie();
    }

    if($_POST['action']=='addToCart' && isset($_POST['productID']) && isset($_POST['productQty'])){
        addToCart($_POST['productID'], $_POST['productQty']);
    }

    if($_POST['action']=='retrieveUserCart'){
        echo json_encode(retrieveUserCart());
    }

    if($_POST['action']=='changeProductQuantity' && isset($_POST['productID']) && isset($_POST['productQty'])){
        changeProductQuantity($_POST['productID'], $_POST['productQty']);
    }

    if($_POST['action']=='removeProductFromCartByID' && isset($_POST['productID']) && isset($_POST['productQty'])){
        removeProductFromCartByID($_POST['productID'], $_POST['productQty']);
    }

    if($_POST['action']=='clearUserCart'){
        clearUserCart();
    }
    
}

function createCookie(){
    if(!isset($_COOKIE['cart'])){
        $cart = array();
        setcookie('cart', json_encode($cart), time()+3600, '/');
    }
}

function addToCart($productID, $productQty){
    $cart = json_decode($_COOKIE['cart'], true);

    $found = false;

    for($i=0; $i < sizeof($cart); $i++){
        if($cart[$i]['productID']==$productID){
            $cart[$i]['productQty'] += $productQty;
            $found = true;
            break;
        }
    }

    if(!$found){
        array_push($cart, array('productID' => $productID, 'productQty' => $productQty));
    }
    
    setcookie('cart', json_encode($cart), time()+3600, '/');
}

function retrieveUserCart():array{
    return json_decode($_COOKIE['cart'], true); 
}

function changeProductQuantity($productID, $productQty){
    $cart = json_decode($_COOKIE['cart'], true);
    
    for($i=0; $i < sizeof($cart); $i++){
        if($cart[$i]['productID']==$productID){
            $cart[$i]['productQty'] = $productQty;
            break;
        }
    }

    setcookie('cart', json_encode($cart), time()+3600, '/');
}

function removeProductFromCartByID($productID, $productQty){
    $cart = json_decode($_COOKIE['cart'], true);

    $key = array_search(array('productID' => $productID, 'productQty' => $productQty), $cart);
    unset($cart[$key]);
    $cart = array_values($cart);
    
    setcookie('cart', json_encode($cart), time()+3600, '/');
}

function clearUserCart(){
    $cart = array();
    setcookie('cart', json_encode($cart), time()+3600, '/');
    unset($_COOKIE['cart']);
}