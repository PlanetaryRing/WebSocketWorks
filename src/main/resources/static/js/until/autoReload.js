function setAutoReload(reFreshTime=5000){
    setInterval(function(){
        location.reload();
    },reFreshTime);
}