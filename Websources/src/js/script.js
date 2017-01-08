
function fillTable() {
     $.ajax({
         type: 'GET',
         url: 'http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies/0/HR/employees',
         success: (function (data) {
             console.log(data);
             console.log("angèle")
             $('#employeesTable').bootstrapTable({
                 data: data
             })
         }),
         error: (function (a,b,c) {
             alert("Error " + b + " " +c );
         }),
         dataType: 'json'
     });

}





