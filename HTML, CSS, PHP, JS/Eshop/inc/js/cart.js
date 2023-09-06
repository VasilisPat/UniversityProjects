$(document).ready(function() {

    /*---Execute on Page Ready---*/

    $.ajax({
        type: 'POST',
        url: 'inc/functions.php',
        data: {'action': 'checkLoggedIn'},
        success: function(response) {
            if(response=="false"){
                $("#logoutButton").hide();
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
        url: 'inc/cookieHandler.php',
        data: {'action': 'retrieveUserCart'},
        success: function(response) {
            if(response.length>10){
                $("#empty-cart").hide();
                showUserCart(JSON.parse(response));
            }
        }
    });

    /*---User Related Buttons---*/

    $("#loginButton").click(function() {
        window.location.replace("login.php");
    });

    $("#homeButton").click(function() {
        window.location.replace("home.html");
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

    /*---Cart Related Buttons---*/
    $(document).on("change", "td > input", function(){
        var id = $(this).parent().parent().attr('id');
        var split = id.split("-");
        
        var productID = split[2];
        var productQty = $(this).val();

        if(productQty==0){
            $.ajax({
                type: 'POST',
                url: 'inc/cookieHandler.php',
                data: {'action': 'removeProductFromCartByID', 'productID': productID, 'productQty': 1},
                success: function() {
                    window.location.reload();
                }
            });
        }else{
            $.ajax({
                type: 'POST',
                url: 'inc/cookieHandler.php',
                data: {'action': 'changeProductQuantity', 'productID': productID, 'productQty': productQty},
                success: function() {
                    window.location.reload();
                }
            });
        }
    })

    $(document).on("click", "td > button", function() {
        var id = $(this).parent().parent().attr('id');
        var split = id.split("-");

        var productID = split[2];
        var productQty = $(this).closest("tr").find("input").val();

        $.ajax({
            type: 'POST',
            url: 'inc/cookieHandler.php',
            data: {'action': 'removeProductFromCartByID', 'productID': productID, 'productQty': productQty},
            success: function() {
                window.location.reload();
            }
        });
    });

    $("#clearCartButton").click(function() {
        $.ajax({
            type: 'POST',
            url: 'inc/cookieHandler.php',
            data: {'action': 'clearUserCart'},
            success: function() {
                $("[id^=cart-entry]").remove();
                window.location.reload();
            }
        });
    });

    $("#checkoutButton").click(function() {
        alert("Checkout not available yet!");
    });

});

function retrieveProductByID(productID){

    return $.ajax({
        type: 'POST',
        url: 'inc/dbHandler.php',
        data: {'action': 'retrieveProductByID', 'productID': productID},
    }).then(response => JSON.parse(response));

}

async function showUserCart(cart){
    var totalPrice = 0;
    var totalQuantity = 0;

    if(cart.length>0){
        for(var i=0; i < cart.length; i++){

            var product = await retrieveProductByID(cart[i]['productID']);
            var totalUnitPrice = parseFloat(cart[i]['productQty'] * product['ProductPrice']).toFixed(2);

            totalPrice = (parseFloat(totalPrice) + parseFloat(totalUnitPrice)).toFixed(2);
            totalQuantity += parseInt(cart[i]['productQty']);

            var tr1 = document.createElement('tr');
            tr1.id = "cart-entry-" + cart[i]['productID'];

            var td1 = document.createElement('td');
            td1.insertAdjacentHTML('beforeend', product['ProductName']);
            tr1.appendChild(td1);

            var td2 = document.createElement('td');
            td2.insertAdjacentHTML('beforeend', product['ProductID']);
            tr1.appendChild(td2);
            
            var td3 = document.createElement('td');
            td3.style = 'text-align:right';
            tr1.appendChild(td3);

            var inp = document.createElement("input");
            inp.type = 'number';
            inp.min = 0;
            inp.max = 15;
            inp.value = cart[i]['productQty'];
            td3.appendChild(inp);

            var td4 = document.createElement('td');
            td4.style = 'text-align:right';
            td4.insertAdjacentHTML('beforeend', parseFloat(product['ProductPrice']).toFixed(2) + "€");
            tr1.appendChild(td4);

            var td5 = document.createElement('td');
            td5.style = 'text-align:right';
            td5.insertAdjacentHTML('beforeend', totalUnitPrice + "€");
            tr1.appendChild(td5);

            var td6 = document.createElement('td');
            td6.style = 'text-align:center';
            tr1.appendChild(td6);

            var btn = document.createElement('button');
            td6.appendChild(btn);

            var img = document.createElement('img');
            img.src = './assets/icon-delete.png';
            img.alt = 'Remove Item';
            img.width = 20;
            btn.appendChild(img);

            document.getElementsByTagName('tbody')[0].appendChild(tr1);
        }
            
    }

    var tr2 = document.createElement('tr');

    var td10 = document.createElement('td');
    td10.colSpan = 2;
    td10.setAttribute('align', 'right');
    td10.insertAdjacentHTML('beforeend', "<strong>Total:</strong>");
    tr2.appendChild(td10);

    var td11 = document.createElement('td');
    td11.setAttribute('align', 'right');
    td11.insertAdjacentHTML('beforeend', "<strong>" + parseInt(totalQuantity, 10) + "</strong>");
    tr2.appendChild(td11);

    var td12 = document.createElement('td');
    td12.colSpan = 2;
    td12.setAttribute('align', 'right');
    td12.insertAdjacentHTML('beforeend', "<strong>" + parseFloat(totalPrice).toFixed(2) + "€</strong>");
    tr2.appendChild(td12);

    var td13 = document.createElement('td');
    tr2.appendChild(td13);

    document.getElementsByTagName('tbody')[0].appendChild(tr2);
}