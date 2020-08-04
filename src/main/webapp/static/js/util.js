//验证手机号码
function checkPhone(e){
    var phone = $(e).val().trim();
    var pattern = /^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\d{8}$/;
    if(phone == '') {
        alert("请输入手机号码");
        return true;
    }
    if(!pattern.test(phone)){
        alert("请输入正确的手机号码");
        return true;
    }
    return false;
}
//验证空值
function checkValue(e){
    var s = $(e).val().trim();
    if(s == '') {
        return true;
    }
    return false;
}
//验证密码长度
function checkValueLength(e){
    var s = $(e).val().trim();
    if(s.length<6) {
        return true;
    }
    return false;
}
//两值比较
function compareValue(e1,e2){
    var s1 = $(e1).val().trim();
    var s2 = $(e2).val().trim();
    if(s1 != s2) {
        return true;
    }
    return false;
}
//登录成功
function isLoginPage() {
    $(".personages-body").css('display','block');
    $(".personages-login").css('display','none');
}
//没有登录
function noLoginPage() {
    $(".personages-body").css('display','none');
    $(".personages-body-item-content").css('display','none');
    $(".personages-login").css('display','block');
}
function getUrlInfo() {
    // let query = window.location.search.substring(1);
    // let s = query.split("=");
    // return {s0:s[0],s1:s[1]};
    let query = window.location.search;
    if(!query){
        console.log("kkk:"+query);
        return {s0:0,s1:0};
    }
    query=query.substring(1);
    console.log("lll:"+query);
    let s = query.split("=");
    return {s0:s[0],s1:s[1]};
}
//获取输入框值
function getInputValue(e) {
    return $(e).val().trim();
}
function definitionMd5(str) {
    let s=str;
    for(let i=0;i<5;i++){
        s = md5(s);
    }
    return s;
}