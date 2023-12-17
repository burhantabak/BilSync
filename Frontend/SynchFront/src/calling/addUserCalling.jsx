export default function addUser(addedUser,user){
    var raw = JSON.stringify({
        "name":addedUser.name,
        "email":addedUser.email,
        "accountType":addedUser.accountType,
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
    
        return fetch("http://localhost:8080/admin/users/add", requestOptions)
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