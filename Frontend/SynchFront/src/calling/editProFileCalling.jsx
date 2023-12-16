export function editProfile(profileImageName, bio, user){
    const baseUrl = 'http://localhost:8080'; // Change this to your server's base URL
    const endpoint = '/user/editProfile';
    const formdata = new FormData();
    formdata.append('bio', 'NewBioValue');
    formdata.append('profileImageName', 'NewProfileImageName');

    const urlWithParams = `${baseUrl}${endpoint}`;
    return fetch(urlWithParams, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${user.token}`,
    },
    body:formdata
  })
    .then((response) => {
        console.log(response)
      if (response.ok) {
        return response.text();
      } else {
        return "not saved";
      }
    })
    .then(result=>{return result;})
    .catch((error) => {
      throw new Error(`Error during file upload: ${error.message}`);
    });
}