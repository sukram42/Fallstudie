
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

function addEmployee(anzahl)
{
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies/0/HR/employees/' + anzahl,
        success: (function (data) {
            fillTable();
            $('table').bootstrapTable("load",data);
        }),
        error: (function (a,b,c) {
            alert("Error " + b + " " +c );
        }),
        dataType: 'json'
    });
}
function createCompany()
{
    var data = JSON.stringify({"passwort":"passwort","name":"hallo GmbG","fremdkapital":100.0,"eigenkapital":1000.0,"hr":{"mitarbeiter":[]}});
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies/',
        data: data,
        success: (function (datan) {
            alert(datan);
        }),
        contentType: "application/json",
        error: (function (a,b,c) {
            alert("Error " + b + " " +c );
        }),
    });
}




