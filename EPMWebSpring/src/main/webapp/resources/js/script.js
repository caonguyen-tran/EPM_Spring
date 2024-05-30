function test() {
    console.log("hello");
}

function deleteJoined(url, id) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status === 204)
            location.reload();
        else if (res.status === 401) {
            console.log("401");
        } else
            alert("ERROR");
    });
}

function createScoreStudent(url, joinId, activityId) {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
                {"joinId": joinId, "activityId": activityId}
                )
    }).then(res => {
        if (res.status === 201)
            location.reload();
        else if (res.status === 401) {
            console.log("401");
        } else
            alert("ERROR");
    });
}