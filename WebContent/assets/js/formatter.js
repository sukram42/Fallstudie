/**
 * Created by boebel on 19.12.2016.
 */
function imageFormatter(value, row) {
    return '<img style="border-radius: 20px;" src="'+value+'" />';
}
function tablebuttonFormatter(value,row)
{
    //Inputs für formatter einfügen.
    var string = "<button type='button' align='right' class='btn btn-default' aria-label='Left Align'>" +
                        "<span class='glyphicon glyphicon-remove-circle' aria-hidden=true'>" +
                        "</span>" +
                "Kündigen</button> " +
                "<button type='button' align='right' class='btn btn-default' aria-label='Left Align'> " +
                        "<span class='glyphicon glyphicon-pencil' aria-hidden='true'>" +
                "</span>" +
                "Edit </button>";


    return string;
}
