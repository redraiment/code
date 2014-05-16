var default_settings = {
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: false,
        showRenameBtn: false,
        drag: {
            isCopy: false
        }
    },
    view: {
        selectedMulti: false,
        showIcon: false,
        dblClickExpand: false
    }
};

$.fn.tree = function(data, settings) {
    settings = settings || {};
    settings.callback = settings.callback || {};
    settings.callback.onRightClick = function(e, id, node) {
        tree.selectNode(node, false);
    };
    this.parents('aside').click(function() {
        var nodes = tree.getSelectedNodes();
        for (var i = 0; i < nodes.length; i++) {
            tree.cancelSelectedNode(nodes[i]);
        }
    })

    $.fn.zTree.init(this, $.extend(default_settings, settings), data || {});
    var tree = $.fn.zTree.getZTreeObj(this.attr('id'));

    var menu = this.prev('menu');
    menu.children('.all').click(function() {
        tree.checkAllNodes(true);
    });
    menu.children('.inverse').click(function() {
        var nodes = tree.transformToArray(tree.getNodes());
        for (var i = 0; i < nodes.length; i++) {
            tree.checkNode(nodes[i], !nodes[i].checked, false, true);
        }
    });
    menu.children('.expand').click(function() {
        tree.expandAll(true);
    });
    menu.children('.collapse').click(function() {
        tree.expandAll(false);
    });
    menu.children('.add').click(function() {
        var parent = tree.getSelectedNodes().length == 0? null: tree.getSelectedNodes()[0];
        var node = tree.addNodes(parent, {
            id: tree.getNodes().length + 1,
            pId: parent? parent.id: 0,
            name: '新节点',
            checked: true
        });
        tree.editName(node);
    });
    menu.children('.delete').click(function() {
        var nodes = tree.getSelectedNodes();
		    if (nodes && nodes.length > 0 && confirm('确认删除？')) {
					  tree.removeNode(nodes[0]);
		    }
    });
    menu.children('.edit').click(function() {
        var nodes = tree.getSelectedNodes();
        if (nodes && nodes.length > 0) {
            tree.editName(nodes[0]);
        }
    });
};

$(function() {
    $('#issues').tree([
        {id: 1, pId: 0, name: '基础数学', open: true, url: 'http://www.google.com'},
        {id: 4, pId: 0, name: '数据结构', open: true, url: '#2'},
        {id: 2, pId: 1, name: '鸡兔同笼', open: true, url: '#3'},
        {id: 3, pId: 1, name: '斐波那契数列', open: true, url: '#4'},
        {id: 5, pId: 4, name: '线性表', open: true, url: '#5'},
        {id: 8, pId: 4, name: '树', open: true, url: '#6'},
        {id: 12, pId: 4, name: '图', open: true, url: '#7'},
        {id: 6, pId: 5, name: '数组', open: true, url: '#8'},
        {id: 7, pId: 5, name: '单链表', open: true, url: '#9'},
        {id: 9, pId: 8, name: '二叉树', open: true, url: '#10'},
        {id: 10, pId: 8, name: '平衡二叉树', open: true, url: '#11'},
        {id: 11, pId: 8, name: '红黑树', open: true}
    ], {
        data: {simpleData: {enable: true}},
        callback: {
            onClick: function(e, id, node) {
                location.href = node.url;
            }
        }
    });
    $('#tools').tree([{
        name: 'Java',
        open: true,
        children: [{
            name: 'Spring'
        }, {
            name: 'Hibernate'
        }]
    }, {
        name: 'Ruby',
        open: true,
        children: [{
            name: 'Rails'
        }, {
            name: 'Sinatra'
        }]
    }, {
        name: 'Python'
    }], {
        check: {
            enable: true,
            chkboxType: {Y: '', N: ''}
        }
    });

    $('#theme').chosen({width: '10em'}).change(function(e, o) {
        $('#code-theme').attr('href', 'styles/highlight/' + o.selected + '.css');
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
