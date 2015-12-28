function validateEmptyInputs() {
    for (var i = 0; i < arguments.length; i++) {
        var input = arguments[i];
        if ($.trim(input.val()).length === 0) {
            console.log("empty input validation NOT passed")
            return false;
        }
    }

    console.log("empty input validation passed");
    return true;
}

function validatePassword(pass) {
    return containsLowerCase(pass) &&
        containsNumber(pass) &&
        containsUpperCase(pass);
}

function containsLowerCase(string) {
    for (var i = 0; i < string.length; i++) {
        var char = string[i];
        if (!$.isNumeric(char) && char.toLowerCase() === char) {
            console.log("pass lowercase validation passed");
            return true;
        }
    }
    console.log("pass lowercase validation NOT passed")
    return false;
}

function containsUpperCase(string) {
    for (var i = 0; i < string.length; i++) {
        var char = string[i];
        if (!$.isNumeric(char) && char.toUpperCase() === char) {
            console.log("pass upper validation passed");
            return true;
        }
    }
    console.log("pass uppercase validation NOT passed")
    return false;
}

function containsNumber(string) {
    for (var i = 0; i < string.length; i++) {
        var char = string[i];
        if ($.isNumeric(char)) {
            console.log("pass number validation passed")
            return true;
        }
    }

    console.log("pass lowercase validation NOT passed")
    return false;
}

function validateInputs() {
    return validateEmptyInputs($("#name"), $("#password"), $("#email")) &&
        validatePassword($("#password").val());
}