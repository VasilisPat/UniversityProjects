$(document).ready(function() {

    /*---Execute on Page Ready---*/

    $.ajax({
        type: 'POST',
        url: 'inc/functions.php',
        data: {'action': 'checkLoggedIn'},
        success: function(response) {
            if(response=="false"){
                $("#logoutButton").hide();
                $.ajax({
                    type: 'POST',
                    url: 'inc/functions.php',
                    data: {'action': 'guestLogin'},
                });
            }else{
                $("#loginButton").hide();
            }
        }
    });

    $.ajax({
        type: 'POST',
        url: 'inc/functions.php',
        data: {'action': 'getSessionUserDetails'},
        success: function(response) {
            var userSessionDetails = JSON.parse(response);
            $("#userFullName").append(userSessionDetails[0]);
            $("#userEmail").append(userSessionDetails[1]);
            $("#userType").append(userSessionDetails[2]);
        }
    });

    $.ajax({
        type: 'POST',
        url: 'inc/cookieHandler.php',
        data: {'action': 'createCookie'},
    });

    $.ajax({
        type: 'POST',
        url: 'inc/dbHandler.php',
        data: {'action': 'retrieveProducts', 'productCategory': 'All'},
        success: function(response) {
            addProductContainers(JSON.parse(response));
        }
    });

    /*---User Related Buttons---*/

    $("#loginButton").click(function() {
        window.location.replace("login.php");
    });

    $("#myCartButton").click(function() {
        window.location.replace("cart.html");
    });

    $("#logoutButton").click(function() {
        $.ajax({
            type: 'POST',
            url: 'inc/functions.php',
            data: {'action': 'logout'},
            success: function() {
                window.location.replace("home.html");
            }
        });
    });

    /*---Product Related Buttons---*/

    $("#allProductsButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'inc/dbHandler.php',
            data: {'action': 'retrieveProducts', 'productCategory': 'All'},
            success: function(response) {
                $("div.product-container").remove();
                addProductContainers(JSON.parse(response));
            }
        });
    });
    
    $("#accessoriesButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'inc/dbHandler.php',
            data: {'action': 'retrieveProducts', 'productCategory': 'Accessories'},
            success: function(response) {
                $("div.product-container").remove();
                addProductContainers(JSON.parse(response));
            }
        });
    });
    
    $("#consumablesButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'inc/dbHandler.php',
            data: {'action': 'retrieveProducts', 'productCategory': 'Consumables'},
            success: function(response) {
                $("div.product-container").remove();
                addProductContainers(JSON.parse(response));
            }
        });
    });
    
    $("#electronicsButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'inc/dbHandler.php',
            data: {'action': 'retrieveProducts', 'productCategory': 'Electronics'},
            success: function(response) {
                $("div.product-container").remove();
                addProductContainers(JSON.parse(response));
            }
        });
    });
    
    $("#peripheralsButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'inc/dbHandler.php',
            data: {'action': 'retrieveProducts', 'productCategory': 'Peripherals'},
            success: function(response) {
                $("div.product-container").remove();
                addProductContainers(JSON.parse(response));
            }
        });
    });

    $(document).on("click", ".addToCartButton", function() {
        var id = $(this).parent().attr('id');
        var split = id.split("-");

        var productID = split[2];
        var productName = $(this).closest("div").find("h1").text();
        var productQty = $(this).closest("div").find("input").val();

        $.ajax({
            type: 'POST',
            url: 'inc/cookieHandler.php',
            data: {'action': 'addToCart', 'productID': productID, 'productQty': productQty},
            success: function() {
                if(productQty==1){
                    alert(productQty + " Item of Type " + productName + " Added to Cart");
                }else{
                    alert(productQty + " Items of Type " + productName + " Added to Cart");
                }
            }
        });
    });

});


function addProductContainers(products){

    if(products.length>0 && products!=null){

        $("#no-products").hide();

        for(var i=0; i < products.length; i++){

            var div = document.createElement('div');
            div.id = "product-container-" + products[i]['ProductID'];
            div.className = "product-container";
    
            var h1 = document.createElement("h1");
            h1.insertAdjacentHTML('beforeend', products[i]['ProductName']);
            div.appendChild(h1);
    
            var p1 = document.createElement("p");
            p1.className = "category";
            p1.insertAdjacentHTML('beforeend', "<i>" + products[i]['ProductCategory'] + "</i>");
            div.appendChild(p1);
    
            var p2 = document.createElement("p");
            p2.insertAdjacentHTML('beforeend', "#SKU: " + products[i]['ProductID']);
            div.appendChild(p2);
    
            var p3 = document.createElement("p");
            p3.className = "price";
            p3.insertAdjacentHTML('beforeend', parseFloat(products[i]['ProductPrice']).toFixed(2) + "€");
            div.appendChild(p3);
    
            var inp = document.createElement("input");
            inp.type = 'number';
            inp.value = 1;
            inp.min = 1;
            inp.max = 15;
            div.appendChild(inp);
    
            var btn = document.createElement("button");
            btn.className = 'addToCartButton';
            btn.insertAdjacentHTML('beforeend', "Add To Cart");
            div.appendChild(btn);
    
            document.body.appendChild(div);
        }
    }else{
        $("#no-products").show();
    }
    
}