function test(){
    console.log("hello");
}

function deleteJoined(url, id) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status === 204)
            location.reload();
        else if(res.status === 401){
            console.log("401");
        }
        else 
            alert("ERROR");
    });
}