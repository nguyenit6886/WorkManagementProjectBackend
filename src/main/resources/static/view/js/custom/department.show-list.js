const theUrl = 'http://localhost:8082/departments';
const deleteDepartmentUrl = 'http://localhost:8082/delete_department';

(function($) {
    "use strict"
    $.getJSON(theUrl, function(data) {
        let stringListDepartment = "";
        for(let i = 0; i < data.length; i++) {
            let note = data[i].note == null ? "" : data[i].note;
            let date1 = moment(data[i].createDate).format('DD-MM-YYYY H:m:ss')
            let updateDate = data[i].updateDate == null ? date1 : data[i].updateDate;

            // date1 = [(date.getMonth()+1).padLeft(),
            //     date.getDate().padLeft(),
            //     date.getFullYear()].join('/') +' ' +
            //    [date.getHours().padLeft(),
            //     date.getMinutes().padLeft(),
            //     date.getSeconds().padLeft()].join(':');
            //date =  date[1] + date[2] + date[3] + date[4] + date[5]+ date[6];

            if(data[i].name != null && data[i].name.trim().length > 0) {
                stringListDepartment += '<div class="col-xl-12 col-xxl-12 col-lg-12 col-sm-12">' +
                                            '<a href="manager-employeeOfDepartment.html">' +
                                                '<div class="card text-white bg-info">' +
                                                    '<div class="card-header d-sm-flex justify-content-between">' +
                                                    '<h5 class="card-title text-white">'+ data[i].name +'</h5>' +
                                                        '<div class="card-footer-link mb-4 mb-sm-0">' +
                                                            '<p class="card-text text-white d-inline">Created at '+ date1 +'</p>' +
                                                        '</div>' +
                                                    '</div>' +
                                                    '<div class="card-body mb-0 py-2">' +
                                                        '<p class="card-text text-white">'+ note +'</p>' +
                                                    '</div>' +
                                                    '<div class="card-footer px-4 bg-transparent border-0 text-white d-flex justify-content-between">' +
                                                        '<p class="text-white">Last updated '+ updateDate +'</p>' +
                                                        '<div>' +
                                                            '<a href="" class="btn btn-primary">Sửa</a>' +
                                                            '<a href="#" data-id="'+ data[i].id + '" id="test" class="btn btn-danger btn-delete">Xóa</a>' +
                                                        '</div>' +
                                                    '</div>' +
                                                '</div>' +
                                            '</a>' +
                                        '</div>';
            }
        }

        $('#show-department').html(stringListDepartment);
    });

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

    function deleteDepartment(id) {
        $.ajax({
            url: deleteDepartmentUrl,
            type: 'post',
            data: {departmentId: id},
            success: function(result) {
                console.log(result);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    // let btnDelete = $(".btn-delete");
    console.log($('#test'));
    // btnDelete.click(function(event){
    //     event.preventDefault();
    //     console.log(btnDelete)
    //     deleteDepartment(idDepartment);
    // });
    // for(let i = 0; i < btnDelete.length; i++) {
        
    //     idDepartment = btnDelete[i].data('id');
    //     btnDelete[i].click(function(event){
    //         event.preventDefault();
    //         console.log(btnDelete[i])
    //         deleteDepartment(idDepartment);
    //     });
    // }
})(jQuery);