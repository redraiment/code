$(function() {
    var settings = {
        callback: {
            onRightClick: function(e, id, node) {
                tree.selectNode(node, false);
            }
        },
        check: {
            enable: true,
            chkboxType: {
                Y: '',
                N: ''
            }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
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

    var tools = $('#tools');
    tools.parents('aside').click(function() {
        var nodes = tree.getSelectedNodes();
        for (var i = 0; i < nodes.length; i++) {
            tree.cancelSelectedNode(nodes[i]);
        }
    });

    var tree;
    $.getJSON('tools', function(data) {
        $.fn.zTree.init(tools, settings, data);
        tree = $.fn.zTree.getZTreeObj('tools');
    });
});
