$(document).ready(function() {

    $("#clear-btn").click(function() {

        $('#form').find('input:text, input:password, select,input:password,radio')
            .each(function() {
                $(this).val('');
            });
    })

})