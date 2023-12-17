export function createTradingPost(post,user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 6,
        "description": post.description,
        "imageName": post.imageName,
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
export function createBorrowingPost(post, user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 1,
        "description": post.description,
        "imageName": post.imageName,
        "hashtagList":post.tags,
        "isAnonymous": false,
        "endDate":post.endDate,
        "beginDate": post.startDate,
        "price": 12,
        "iban": "TR 3903409409430049"
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
export function createDonationPost(post, user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 2,
        "description": post.description,
        "imageName": post.imageName,
        "hashtagList":post.tags,
        "isAnonymous": false,
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
export function createSectionExchangePost(post,user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 5,
        "description": post.description,
        "imageName": post.imageName,
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
export function createLostnFoundPost(post,user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 3,
        "description": post.description,
        "imageName": post.imageName,
        "hashtagList":post.tags,
        "isAnonymous": false,
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
export function createNormalPost(post,user){
    var raw = JSON.stringify({
        "title": post.title,
        "postType": 4,
        "description": post.description,
        "imageName": post.imageName,
        "hashtagList":post.tags,
        "isAnonymous": post.isAnonymous,
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