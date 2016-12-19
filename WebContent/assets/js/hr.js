/*-----API Call Mitarbeiter------*/
$(document).ready(function() {
  $('#apiuser').click(newUser());
});

function newUser() {
  var user;
  $.ajax({
    url: 'https://randomuser.me/api/',
    dataType: 'json',
    success: function(data) {
    user = data;
    }
  });
}


