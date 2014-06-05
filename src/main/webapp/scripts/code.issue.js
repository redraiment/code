$(function() {
    var settings = {
        callback: {
            onRightClick: function(e, id, node) {
                tree.selectNode(node, false);
            },
            onClick: function(e, id, node) {
                location.href = node.id;
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

    var issues = $('#issues');
    issues.parents('aside').click(function() {
        var nodes = tree.getSelectedNodes();
        for (var i = 0; i < nodes.length; i++) {
            tree.cancelSelectedNode(nodes[i]);
        }
    });

    var tree;
    $.getJSON('issues', function(data) {
        $.fn.zTree.init(issues, settings, data);
        tree = $.fn.zTree.getZTreeObj('issues');
    });
});
