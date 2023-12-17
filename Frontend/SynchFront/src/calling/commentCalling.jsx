export default function CreateComment(commentText,postId, user, handlePostCreation){
    console.log(postId);
    var raw = JSON.stringify({
    "isReply": false,
    "text": commentText,
    "primaryPostID": postId,
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
        handlePostCreation(result); // Update the commentList in your component state

    })
    .catch(error => console.log('error state: ', error));
}
