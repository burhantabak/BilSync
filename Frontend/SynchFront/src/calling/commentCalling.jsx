export default function CreateComment(commentText,postId){
    var raw = JSON.stringify({
    "isReply": false,
    "text": commentText,
    "postId": postId,
    });
    console.log(raw)
    var requestOptions = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.token}`
      },
    body: raw,
    };

    return fetch("http://localhost:8080/post/createComment", requestOptions)
    .then(response => {
        return response;
    })
    .then((result) => {
        console.log(result);
    })
    .catch(error => console.log('error state: ', error));
}
