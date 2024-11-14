let createGroupBar=function (groupInfo){
/*
    groupInfo={
        groupId:1,
        groupName:1,
    }
*/
    // 创建基础的li
    let barBase=document.createElement("li");

    // 创建button
    let button=document.createElement("button");
    button.className="group-label"
    barBase.appendChild(button);

    // 创建input
    let input=document.createElement("input");
    input.type="hidden";
    input.value=groupInfo.groupId;
    button.appendChild(input);

    // 创建群头像
    let groupPic=document.createElement("div");
    groupPic.className="group-picture"
    button.appendChild(groupPic);

    // 创建信息块
    let infoBlock=document.createElement("div");
    infoBlock.className="group-msg-container";
    button.appendChild(infoBlock);

    let groupName=document.createElement("div");
    groupName.className="group-name";
    groupName.innerText=groupInfo.groupName;
    infoBlock.appendChild(groupName);

    let lastMsgTime=document.createElement("div");
    lastMsgTime.className="latest-msg-time";
    groupName.appendChild(lastMsgTime);

    let lastMsg=document.createElement("div");
    lastMsg.className="latest-msg";
    infoBlock.appendChild(lastMsg);

    return barBase;
}

// 传入的参数如下:
// labelInfo={
//     userId:1,
//     username:1,
//     url:"#",
// }
let createMemberLabel=function (labelInfo){
    let liBase=document.createElement("li");

    let linkLabel=document.createElement("a");
    linkLabel.href=labelInfo.url;
    liBase.appendChild(linkLabel);

    let input=document.createElement("input");
    input.type="hidden";
    input.value=labelInfo.userId;
    linkLabel.appendChild(input);

    let headSculpture=document.createElement("div");
    headSculpture.className="member-picture";
    linkLabel.appendChild(headSculpture);

    let memberName=document.createElement("div");
    memberName.className="member-name";
    memberName.innerText=labelInfo.username;
    linkLabel.appendChild(memberName);

    return liBase;
}

/**
 *
 * @param msgInfo 包含username,content
 * @param type  字符串self/friend
 * @returns {HTMLLIElement}
 */
let createMessageLabel=function (msgInfo,type){
    let liBase=document.createElement("li");
    liBase.classList.add("msgbar");
    liBase.classList.add(type);

    let msgArea=document.createElement("div");
    msgArea.className="msgarea "+type;
    liBase.appendChild(msgArea);

    let usernameDiv=document.createElement("div");
    usernameDiv.className="msg-username";
    usernameDiv.innerText=msgInfo.username;
    msgArea.appendChild(usernameDiv);

    let msgContent=document.createElement("div");
    msgContent.className="msg-text";
    msgContent.innerText=msgInfo.content;
    msgArea.appendChild(msgContent);

    let userPic=document.createElement("div");
    userPic.className="userpicture"
    msgArea.appendChild(userPic);

    return liBase;
}