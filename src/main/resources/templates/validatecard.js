var $ = function (id) {
    return document.getElementById(id);
}

var isInt = function (str) {
    return !isNaN(str) && Number.isInteger(parseInt(str));
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
    var string = str;
    if ((str.toLowerCase() === visa.toLowerCase()) || (str.toLowerCase() === master.toLowerCase()) || (str.toLowerCase() === discover.toLowerCase())
        || (str.toLowerCase() === amex.toLowerCase())) {
        return true;
    }
}

function validateForm() {
    var fnamecard1 = $("fnamecard1").value;
    var cardChoice1 = $("cardChoice1").value;
    var cardnum1 = $("cardnum1").value;
    var expiremonth1 = $("expiremonth1").value;
    var expireyear1 = $("expireyear1").value;
    var fnamecard2 = $("fnamecard2").value;
    var cardChoice2 = $("cardChoice2").value;
    var cardnum2 = $("cardnum2").value;
    var expiremonth2 = $("expiremonth2").value;
    var expireyear2 = $("expireyear2").value;
    var fnamecard3 = $("fnamecard3").value;
    var cardChoice3 = $("cardChoice3").value;
    var cardnum3 = $("cardnum3").value;
    var expiremonth3 = $("expiremonth3").value;
    var expireyear3 = $("expireyear3").value;
    var flag = true;

    // payment 1
    if (fnamecard1 == "") {
        flag = false;
        $("error1").innerHTML = "Please enter first name";
        $("fnamecard1").focus();
    } else if (hasNum(fnamecard1)) {
        flag = false;
        $("error1").innerHTML = "Name cannot contain numbers";
        $("fnamecard1").focus();
    }

    if (cardChoice1 == "") {
        flag = false;
        $("error2").innerHTML = "Please enter card type";
        $("cardChoice1").focus();
    } else if (hasNum(cardChoice1)) {
        flag = false;
        $("error2").innerHTML = "Card type cannot contain numbers";
        $("cardChoice1").focus();
    } else if (!isCardType(cardChoice1)) {
        flag = false;
        $("error2").innerHTML = "Please enter valid card type (visa, mastercard, discover, amex)";
    }

    if (cardnum1 == "") {
        flag = false;
        $("error3").innerHTML = "Please enter card number";
        $("cardnum1").focus();
    } else if (!isInt(cardnum1)) {
        flag = false;
        $("error3").innerHTML = "Card number must be numeric";
        $("cardnum1").focus();
    } else if (cardnum1.length < 0 || cardnum1.length != 16) {
        flag = false;
        $("error3").innerHTML = "Card number must be valid";
        $("cardnum1").focus();
    }

    if (!isInt(expiremonth1)) {
        flag = false;
        $("error4").innerHTML = "Month must be numeric";
        $("expiremonth1").focus();
    } else if (expiremonth1.length != 2) {
        flag = false;
        $("error4").innerHTML = "Month must be valid (MM)";
        $("expiremonth1").focus();
    }

    if (!isInt(expireyear1)) {
        flag = false;
        $("error5").innerHTML = "Year must be numeric";
        $("expireyear1").focus();
    } else if (expireyear1.length != 4) {
        flag = false;
        $("error5").innerHTML = "Year must be valid (YYYY)";
        $("expireyear1").focus();
    }

    // payment 2
    if (fnamecard2 == "") {
        flag = false;
        $("error6").innerHTML = "Please enter first name";
        $("fnamecard2").focus();
    } else if (hasNum(fnamecard2)) {
        flag = false;
        $("error6").innerHTML = "Name cannot contain numbers";
        $("fnamecard2").focus();
    }

    if (cardChoice2 == "") {
        flag = false;
        $("error7").innerHTML = "Please enter card type";
        $("cardChoice2").focus();
    } else if (hasNum(cardChoice2)) {
        flag = false;
        $("error7").innerHTML = "Card type cannot contain numbers";
        $("cardChoice2").focus();
    } else if (!isCardType(cardChoice2)) {
        flag = false;
        $("error7").innerHTML = "Please enter valid card type (visa, mastercard, discover, amex)";
    }

    if (cardnum2 == "") {
        flag = false;
        $("error8").innerHTML = "Please enter card number";
        $("cardnum2").focus();
    } else if (!isInt(cardnum2)) {
        flag = false;
        $("error8").innerHTML = "Card number must be numeric";
        $("cardnum2").focus();
    } else if (cardnum2.length < 0 || cardnum2.length != 16) {
        flag = false;
        $("error8").innerHTML = "Card number must be valid";
        $("cardnum2").focus();
    }

    if (!isInt(expiremonth2)) {
        flag = false;
        $("error9").innerHTML = "Month must be numeric";
        $("expiremonth2").focus();
    } else if (expiremonth2.length != 2) {
        flag = false;
        $("error9").innerHTML = "Month must be valid (MM)";
        $("expiremonth2").focus();
    }

    if (!isInt(expireyear2)) {
        flag = false;
        $("error10").innerHTML = "Year must be numeric";
        $("expireyear2").focus();
    } else if (expireyear2.length != 4) {
        flag = false;
        $("error10").innerHTML = "Year must be valid (YYYY)";
        $("expireyear2").focus();
    }

    // payment 3
    if (fnamecard3 == "") {
        flag = false;
        $("error11").innerHTML = "Please enter first name";
        $("fnamecard3").focus();
    } else if (hasNum(fnamecard3)) {
        flag = false;
        $("error11").innerHTML = "Name cannot contain numbers";
        $("fnamecard3").focus();
    }

    if (cardChoice3 == "") {
        flag = false;
        $("error12").innerHTML = "Please enter card type";
        $("cardChoice3").focus();
    } else if (hasNum(cardChoice3)) {
        flag = false;
        $("error3").innerHTML = "Card type cannot contain numbers";
        $("cardChoice3").focus();
    } else if (!isCardType(cardChoice3)) {
        flag = false;
        $("error12").innerHTML = "Please enter valid card type (visa, mastercard, discover, amex)";
    }

    if (cardnum3 == "") {
        flag = false;
        $("error13").innerHTML = "Please enter card number";
        $("cardnum3").focus();
    } else if (!isInt(cardnum3)) {
        flag = false;
        $("error13").innerHTML = "Card number must be numeric";
        $("cardnum3").focus();
    } else if (cardnum3.length < 0 || cardnum3.length != 16) {
        flag = false;
        $("error13").innerHTML = "Card number must be valid";
        $("cardnum3").focus();
    }

    if (!isInt(expiremonth3)) {
        flag = false;
        $("error14").innerHTML = "Month must be numeric";
        $("expiremonth3").focus();
    } else if (expiremonth3.length != 2) {
        flag = false;
        $("error14").innerHTML = "Month must be valid (MM)";
        $("expiremonth3").focus();
    }

    if (!isInt(expireyear3)) {
        flag = false;
        $("error15").innerHTML = "Year must be numeric";
        $("expireyear3").focus();
    } else if (expireyear3.length != 4) {
        flag = false;
        $("error15").innerHTML = "Year must be valid (YYYY)";
        $("expireyear3").focus();
    }
    return flag;
}


var undo = function () {
    $("error1").innerHTML = "*";
    $("error2").innerHTML = "*";
    $("error3").innerHTML = "*";
    $("error4").innerHTML = "*";
    $("error5").innerHTML = "*";
    $("error6").innerHTML = "*";
    $("error7").innerHTML = "*";
    $("error8").innerHTML = "*";
    $("error9").innerHTML = "*";
    $("error10").innerHTML = "*";
    $("error11").innerHTML = "*";
    $("error12").innerHTML = "*";
    $("error13").innerHTML = "*";
    $("error14").innerHTML = "*";
    $("error15").innerHTML = "*";
}

window.onload = function () {
    $("button").onclick = undo;
}