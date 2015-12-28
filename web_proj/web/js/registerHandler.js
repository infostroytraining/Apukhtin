$("#registerUser").click(function () {
    removeWarnings();

    if (validateInputs()) {
        $.ajax({
            url: '/register',
            type: 'POST',
            data: $('#registerForm').serialize(),
            error: function (data) {
                var messages = "";
                for (var error in data.responseJSON) {
                    var errorElId = '#' + error;
                    $(errorElId).select();
                    $(errorElId).parent().addClass('has-error');
                    messages += data.responseJSON[error] + '\n';
                }
                showError(messages);
            },
            success: function (data) {
                window.location.href = "/users.jsp"
            }
        })
    } else {
        showError("Check inputs: all of them must not be empty\n" +
            "Check pass: it must contain at least one small, one big letter and any number");
    }
});

function showError(msg) {
    msg = msg || "Error happened";

    $("#errDiv").text(msg);
    $("#errDiv").removeAttr("hidden");
}

function removeWarnings() {
    var formElements = $("#registerUser").siblings();
    for (var i = 0; i < formElements.length; i++) {
        var formElem = formElements[i];
        formElem.removeAttribute("has-error");
    }
    $("errDiv").attr("hidden", "hidden");
}