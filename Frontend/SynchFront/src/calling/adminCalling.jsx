export function banUser(userId, user){
    const apiUrl = `http://localhost:8080/admin/users/${userId}/ban`;
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "text/plain");
    myHeaders.append("Authorization", `Bearer ${user.token}`);
    var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    redirect: 'follow'
    };

    return fetch(apiUrl, requestOptions)
    .then(response => {console.log(response);response.text()})
    .catch(error => console.log('error', error));
}
export function unBanUser(userId,user){
    const apiUrl = `http://localhost:8080/admin/users/${userId}/unban`;
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "text/plain");
    myHeaders.append("Authorization", `Bearer ${user.token}`);
    var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    redirect: 'follow'
    };

    fetch(apiUrl, requestOptions)
    .then(response => {console.log(response);response.text()})
    .catch(error => console.log('error', error));
}