const theUrl = 'http://localhost:8082/departments';

(function($) {
    "use strict"
    // $.getJSON(theUrl, function(data) {
    //     console.log(data);
    // });

    function addNewDepartment() {
        let department_name = $('#department_name').val();
        let department_note = $('#department_note').val();
        $.ajax({
            url: "http://localhost:8082/add_department",
            type: 'post',
            data: {
                name: department_name,
                note: department_note
            },
            success: function(result) {
                console.log(result);
                // document.getElementById('test').innerHTML = result;
            },
            error: function(error) {
                console.log(error);
            }
        })
    };

    $('#btn_add_new_department').click(function(event) {
        event.preventDefault();
        addNewDepartment();
    });
})(jQuery);