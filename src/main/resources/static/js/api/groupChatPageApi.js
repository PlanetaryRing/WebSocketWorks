// 查询左侧的群组列表
// 传入的参数是当前用户的ID
function getUserBelongGroup(userId,callback) {
    let xhr = new XMLHttpRequest();
    let data = {
        userId: userId
    };

    xhr.open("POST", "/GroupInfo/userBelong?userId=" + userId, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 正确地响应状态
                try {
                    // 开始解析后端传来的消息
                    let responseData = JSON.parse(xhr.responseText);
                    if (responseData.code === 301) {
                        alert("出现错误:" + responseData.msg);
                        location.href = responseData.data.url;
                    } else {
                        // 没有问题,返回左侧列表
                        callback(true,responseData);
                    }
                } catch (error) {
                    console.error('JSON 解析错误:', error);
                }
            } else {
                console.error('请求失败，状态码：', xhr.status);
            }
        }
    };

    xhr.send(JSON.stringify(data));
}

// 发送消息,传入的参数为
// {
//      userId:
//      groupId:
//      type:
//      content:
// }
function sendMsg(msg,callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("post", "/message/send?userId=" + msg.userId, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            if (data.code == 200) {
                callback(true);
            } else if (data.code == 301) {
                window.location.href = data.data.url;
            } else {
                alert("发送出现错误:" + data.msg);
                callback(false);
            }
        }
    }

    xhr.send(JSON.stringify(msg));
}


// 获取个人信息
function getSelfInfo(userId,callback){
    let xhr=new XMLHttpRequest();
    xhr.open("post","/user/getSelfInfo?userId="+userId);
    xhr.setRequestHeader("Content-Type", "application/json");
    let data={
        userId:userId,
    };
    xhr.onreadystatechange=function (){
        if (xhr.readyState === 4 && xhr.status === 200) {
            let responseData=JSON.parse(xhr.responseText);
            if(responseData.code==301){
                window.location.href=responseData.data.url;
            }else {
                callback(true,responseData);
            }
        }
    }
    xhr.send(JSON.stringify(data));
}

// 获取当前群组的成员
/**
    info{
        userId:
        groupId:
    }
 */
function getGroupMember(info,callback){
    let xhr=new XMLHttpRequest();
    xhr.open("post","/GroupInfo/groupUserList?userId="+info.userId,true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let requestData={
        userId:info.userId,
        groupId:info.groupId,
    };
    xhr.onreadystatechange=function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("[getGroupMember]",xhr.responseText);
            let data=JSON.parse(xhr.responseText);
            if(data.code===301){
                window.location.href=responseData.data.url;
            }
            else {
                callback(true,data);
            }
        }
    }
    xhr.send(JSON.stringify(requestData));
}

// 获取当前群组的20条消息
/**
 info{
 userId:
 groupId:
 }
 */
function getGroupMessage(info,callback){
    let xhr=new XMLHttpRequest();
    xhr.open("post","/message/get?userId="+info.userId,true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let requestData={
        userId:info.userId,
        groupId:info.groupId,
    }
    xhr.onreadystatechange=function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("[getGroupMessage]",xhr.responseText);
            let data=JSON.parse(xhr.responseText);
            if(data.code===301){
                window.location.href=responseData.data.url;
            }
            else {
                callback(true,data);
            }
        }
    }
    xhr.send(JSON.stringify(requestData))
}