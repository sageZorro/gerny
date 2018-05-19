
var setting = {
		
	async: {
		enable: true,
		url: "module/list",
		autoParam: ["id", "name"]
	},
    view: {
        addHoverDom: addHoverDom,
        //removeHoverDom: removeHoverDom,
       addDiyDom:addDiyDom,
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: 'moduleid',
            pIdKey: 'parentid'
        },
        key: {
        	name: "modulename"
		}
    },
    edit: {
        enable: true
    },
    callback: {
		onRename: onRename,
		onRemove: onRemove
	}
};

var moduleTreeObj;
$(document).ready(function(){
	moduleTreeObj = $.fn.zTree.init($("#module_tree"), setting);
	 var nodes = moduleTreeObj.getNodes();
     for (var i = 0; i < nodes.length; i++) { //设置节点展开
    	 moduleTreeObj.expandNode(nodes[i], true, true, true);
     }
});


//鼠标浮过显示编辑
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var aObj = $("#" + treeNode.tId + "_a");
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("module_tree");
        zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
        return false;
    });
    
    
};

//鼠标浮出 显示内容
function removeHoverDom(treeId, treeNode) {
    $("#diyBtn_"+treeNode.tId).unbind().remove();
};


//默认显示内容
function addDiyDom(treeId, treeNode) {
	//if (treeNode.parentNode && treeNode.parentNode.id!=2) return;
	console.info(treeNode.id);
	var aObj = $("#" + treeNode.tId + "_a");
	var editStr = "   编码:  "+"<input moduleid='"+treeNode.moduleid+"' class='demoIcon' onchange='inputOnchange(this,1)'  value='"+treeNode.modulecode+"' />   访问路径：<input moduleid='"+treeNode.moduleid+"' class='demoIcon' onchange='inputOnchange(this,2)'  value='"+treeNode.moduleuri+"' />";
	aObj.after(editStr);
	var btn = $("#diyBtn_"+treeNode.id);
	if (btn) btn.bind("click", function(){alert("diy Button for " + treeNode.name);});
}



//修改名称
function onRename(event, treeId, treeNode, isCancel){
	$.ajax({
		url:"module/edit",
		data:{moduleid:treeNode.moduleid,parentid:treeNode.parentid,modulename:treeNode.modulename},
		success:function(msg){
			moduleTreeObj.reAsyncChildNodes(null, "refresh",true);
		}
	});
	
}

//删除节点
function onRemove(event, treeId, treeNode, isCancel){
	if(treeNode.moduleid!=null){
		$.ajax({
			url:"module/remove/"+treeNode.moduleid
		});
	}
	
}
//修改模块参数
function inputOnchange(obj,type){
	var moduleid=$(obj).attr("moduleid");
	var value=$(obj).val();
	console.info(moduleid);
	console.info(value);
	var params;
	if(type=="1"){ //code
		params={moduleid:moduleid,modulecode:value};
	}else if(type=="2"){ //url
		params={moduleid:moduleid,moduleuri:value};
	}
	$.ajax({
		url:"module/edit",
		data:params
	});
}


