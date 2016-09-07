var oEditors = [];

function se2_init(id) {
    var offset=location.href.indexOf(location.host)+location.host.length;
    var contextPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
   
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: id,
        sSkinURI: contextPath + "/smarteditor/se2/SmartEditor2Skin.html",
        htParams: {
            bUseToolbar: true,
            fOnBeforeUnload: function () { },
            fOnAppLoad: function () { },
            fCreator: "createSEditor2"
        }
    });
}

function se2_syncData() {
    $("textarea.smarteditor2").each(function () {  
        var textareaId = $(this).attr("id");
        oEditors.getById[textareaId].exec("UPDATE_CONTENTS_FIELD", []);
    });
}
