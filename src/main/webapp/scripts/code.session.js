$(function() {
    var session = new RESTful(location.href);
    $('button').click(function() {
        session.delete('', function() {
          location.reload();
        });
    });
});
