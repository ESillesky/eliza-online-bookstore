
var $ = function (id) {
    return document.getElementById(id);
}

var isInt = function (str) {
    return !isNaN(str) && Number.isInteger(parseFloat(str));
}

function hasNum(str) {
    var matches = str.match(/(\d+)/);
    if (matches) {
        return true;
    }
}

function isCardType(str) {
    var visa = "visa";
    var master = "mastercard";
    var discover = "discover";
    var amex = "amex";
    if ((str.toLowerCase() === visa.toLowerCase()) || (str.toLowerCase() === master.toLowerCase()) || (str.toLowerCase() === discover.toLowerCase())
        || (str.toLowerCase() === amex.toLowerCase())) {
        return true;
    }
}

function validateForm() {
    var fname = $("fname").value;
    var lname = $("lname").value;
    var email = $("email").value;
    var psw = $("psw").value;
    var psw_r = $("psw_r").value;
    var fnamecard = $("fnamecard").value;
    var lnamecard = $("lnamecard").value;
    var cardChoice = $("cardChoice").value;
    var cardnum = $("cardnum").value;
    var expiremonth = $("expiremonth").value;
    var expireyear = $("expireyear").value;
    var flag = true;

    if (fname == "") {
        flag = false;
        $("error1").innerHTML = "Please enter first name";
        $("fname").focus();
    } else if (hasNum(fname)) {
        flag = false;
        $("error1").innerHTML = "Name cannot contain numbers";
        $("fname").focus();
    }

    if (lname == "") {
        flag = false;
        $("error2").innerHTML = "Please enter last name";
        $("lname").focus();
    } else if (hasNum(lname)) {
        flag = false;
        $("error2").innerHTML = "Name cannot contain numbers";
        $("lname").focus();
    }
    if (email == "") {
        flag = false;
        $("error3").innerHTML = "Please enter email";
        $("fname").focus();
    }
    if (psw == "") {
        flag = false;
        $("error6").innerHTML = "Please enter a password";
        $("psw").focus();
    }

    if (psw_r == "") {
        flag = false;
        $("error7").innerHTML = "Please re-enter your password";
        $("psw_r").focus();
    }
    if (psw != psw_r) {
        flag = false;
        $("error7").innerHTML = "Passwords must match";
        $("psw_r").focus();
    }

    if (hasNum(fnamecard)) {
        flag = false;
        $("error8").innerHTML = "Name cannot contain numbers";
        $("fnamecard").focus();
    }

    if (hasNum(lnamecard)) {
        flag = false;
        $("error9").innerHTML = "Name cannot contain numbers";
        $("lnamecard").focus();
    }

    if (hasNum(cardChoice)) {
        flag = false;
        $("error10").innerHTML = "Card type cannot contain numbers";
        $("cardChoice").focus();
    } else if (!isCardType(cardChoice)) {
        flag = false;
        $("error10").innerHTML = "Please enter valid card type (visa, mastercard, discover, amex)";
    }

    if (!isInt(cardnum)) {
        flag = false;
        $("error11").innerHTML = "Card number must be numeric";
        $("cardnum").focus();
    } else if (cardnum.length != 16) {
        flag = false;
        $("error11").innerHTML = "Card number must be valid";
        $("cardnum").focus();
    }

    if (!isInt(expiremonth)) {
        flag = false;
        $("error12").innerHTML = "Month must be numeric";
        $("expiremonth").focus();
    } else if (expiremonth.length != 2) {
        flag = false;
        $("error12").innerHTML = "Month must be valid (MM)";
        $("expiremonth").focus();
    }

    if (!isInt(expireyear)) {
        flag = false;
        $("error13").innerHTML = "Year must be numeric";
        $("expireyear").focus();
    } else if (expireyear.length != 4) {
        flag = false;
        $("error13").innerHTML = "Year must be valid (YYYY)";
        $("expireyear").focus();
    }

    return flag;
}

var undo = function () {
    $("error1").innerHTML = "*";
    $("error2").innerHTML = "*";
    $("error3").innerHTML = "*";
    $("error4").innerHTML = "*";
    $("error5").innerHTML = "*";
    $("error6").innerHTML = " *";
    $("error7").innerHTML = " *";
    $("error8").innerHTML = " *";
    $("error9").innerHTML = " *";
    $("error10").innerHTML = " *";
    $("error11").innerHTML = " *";
    $("error12").innerHTML = " *";
    $("error13").innerHTML = " *";
}

window.onload = function () {
    $("button").onclick = undo;
}