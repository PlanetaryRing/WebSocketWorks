function handleLogin(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let data = {
        username: formData.get("username"),
        password: formData.get("password")
    }
    if(!isLegalFormData(data)){
        console.log("数据有误!");
        return;
    }
    let xhr = new XMLHttpRequest();
    console.log("[handleLogin]:即将发送的表单数据为:" + JSON.stringify(data));
    xhr.open("POST", "/user/login", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        // console.log("状态改变")
        if (xhr.readyState === 4 && xhr.status === 200) {
            let responseData = JSON.parse(xhr.responseText);
            console.log("收到的返回信息是:" + JSON.stringify(responseData));
            if (responseData.code === 400) {
                alert("登陆失败!" + responseData.msg.toString());
            } else if(responseData.code==200){
                let data=JSON.parse(responseData.data);
                location.href=data.url;
            }
        } else if (xhr.readyState === 4 && xhr.status !== 200) {
            console.error("Error:", xhr.status);
        }
    }
    xhr.send(JSON.stringify(data));
}

function isLegalFormData(data){
    if(data.username.length===0){
        alert("账号不能为空!");
        return false;
    }
    if(data.username.length<6 || data.username.length>25){
        alert("账号长度不符!");
        return false;
    }
    if(data.password.length===0){
        alert("密码不能为空!");
        return false;
    }
    if(data.password.length<6 || data.username.length>25){
        alert("密码长度不符!");
        return false;
    }
    return true;
}

