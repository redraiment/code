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

    $.fn.zTree.init(this, $.extend(default_settings, settings), data || []);
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
});
