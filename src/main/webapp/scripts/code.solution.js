$(function() {
    $('#theme').chosen({width: '10em'}).change(function(e, o) {
        var href = $('#code-theme').attr('href');
        $('#code-theme').attr('href', href.replace(/[^./]+(?=\.css$)/, o.selected));
    });

    $('#syntax').chosen({width: '95%'});
    $('#tags').chosen({
        width: '95%',
        no_results_text: '没找到工具：',
        placeholder_text_multiple: '添加相关工具',
        display_disabled_options: false,
        display_selected_options: false
    });

    $('.highlighttable').hide().filter(':first-of-type').show();
    $('.version').change(function() {
        $(this).parents('section').children('.highlighttable').hide().eq(this.selectedIndex).show();
    });
});
