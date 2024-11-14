function registerUser(userInfo, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/user/register", true);
    xhr.setRequestHeader("Content-Type", "application/json", true);
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
                        callback(true, responseData);
                    }
                } catch (error) {
                    console.error('JSON 解析错误:', error);
                }
            } else {
                console.error('请求失败，状态码：', xhr.status);
            }
        }
    }
    xhr.send(JSON.stringify(userInfo));
}