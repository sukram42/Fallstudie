
function getTest() {

     $.ajax({
         type: 'GET',
         url: 'http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies/0/HR/employees',
         success: (function (data) {
             alert(data);
             $('#table').bootstrapTable({
                 data: data
             })
         }),
         error: (function (a,b,c) {
             alert("Error " + b + " " +c );
         }),
         dataType: 'json'
     });

}

function imageFormatter(value, row) {
    return '<img style="border-radius: 20px;" src="'+value+'" />';
}

function addEmployee(anzahl)
{
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies/0/HR/employees/' + anzahl,
        success: (function (data) {
            getTest();
            $('table').bootstrapTable("load",data);
        }),
        error: (function (a,b,c) {
            alert("Error " + b + " " +c );
        }),
        dataType: 'json'
    });

}

