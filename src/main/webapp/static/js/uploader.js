function upImg() {
    let allMaxSize = 1;//定义允许文件的最大值
    let imgSrc;
    let uploader = WebUploader.create({
        //上传打包
        pick: "#filePicker",
        // swf文件路径
        swf: '/static/webuploader/Uploader.swf',
        //上传的服务器
        server:'/lan/imgFile',
        //自动上传
        auto:true,
        //限制大小
        fileSingleSizeLimit:allMaxSize*1024*1024,//1M
    });
    uploader.on("fileQueued",function (file) {
        //生成缩略图
        uploader.makeThumb(file,function (error,src){
            if(error){
                $("#personages-body-img img").replaceWith("无法显示");
            }
            else{
                imgSrc = src;
                $("#personages-body-img img").attr('src',src);
            }
        });
    });
    //文件进度处理
    uploader.on("uploadProgress",function (file,Progress) {
    });
    //异常错误提示
    uploader.on("error",function (type){
        if(type == "Q_TYPE_DENIED"){
            alert("禁止上传非图片类型文件");
        }
    });
    //文件上传结束
    uploader.on("uploadFinished",function () {

    });
    //文件上传成功
    uploader.on("uploadSuccess",function (file,response) {
        //清除文件链表
        uploader.removeFile(file);
        if(response['is']){
            alert("图片上传成功");
            $("#personages-body-img img").attr('src','/img/'+response['fileName']);
            $(".personages-body-img img").attr('src','/img/'+response['fileName']);
            $("#personages-body-img img").attr('v',response['fileName']);
            $(".personages-body-img img").attr('v',response['fileName']);
        }
    });
    //文件上传失败
    uploader.on( 'uploadError', function( file ) {
        alert('图片上传失败');
    });
    //上传前做的文件处理
    uploader.on("beforeFileQueued",function (file) {
        if(file.size>20*1024*1204)
        {
            alert("上传文件不能大于1M");
        }
    });
    //上传文件前向服务器传递参数
    uploader.on('uploadBeforeSend', function (obj, data) {
        let v = $("#personages-body-img img").attr('v');
        console.log("666:"+v)
        //传入表单参数
        data = $.extend(data, {
            "v": v
        });
    });
}