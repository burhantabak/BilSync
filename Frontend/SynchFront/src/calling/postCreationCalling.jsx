export function createTradingPost(post,user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 6,
        "description": post.description,
        "imagePath": "",
        "hashtagList":post.tags,
        "isAnonymous": false,
        "price": post.price,
        "iban": post.iban,
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
    
        return fetch("http://localhost:8080/post/createPost", requestOptions)
        .then(response => {
            console.log(response)
            if(response.status===200){
                return response.status;
            }
            else if(response.status >=400){
                return response.text();
            }
            return "UnIdentified error occured:(";
        })
        .then((result) => {
            console.log(result);
            return result;
        })
        .catch(error => console.log('error state: ', error));
}