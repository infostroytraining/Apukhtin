$("#registerUser").click(function () {
    $.ajax({
        url: '/register',
        type: 'POST',
        data: $('#registerForm').serialize(),
        error: function (data) {
            var messages = "";
            for (var err in data) {
                $('#' + err).addClass('has-error');
                messages += data[err] + '\n';
            }
            $('p.bg-danger').removeAttr('hidden');
        }
    })
});