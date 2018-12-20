$(function () {
    $('input[name=username]').on('focus',function () {
        $('.login-err-msg').empty();
    });
});