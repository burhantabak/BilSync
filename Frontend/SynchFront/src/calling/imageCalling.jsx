export async function uploadFileCall(file, user) {
    const formData = new FormData();
    formData.append('image', file);

    return fetch('http://localhost:8080/file/uploadFile', {
    method: 'POST',
    body: formData,
    headers: {
      Authorization: `Bearer ${user.token}`,
    },
  })
    .then((response) => {
      if (response.ok) {
        return response.text();
      } else {
        return "not uploaded";
      }
    })
    .then(result=>{console.log(result);return result;})
    .catch((error) => {
      throw new Error(`Error during file upload: ${error.message}`);
    });
  };
  export function getImage(imageName, user){
    const sentUrl = `http://localhost:8080/file/fileSystem/${imageName}`;

  return fetch(sentUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  })
  .then(response => {
    console.log("finished fetch");
    console.log(response);

    if (response.status === 200) {
      return response.body.arrayBuffer();
    }
     else {
      return response.status;
    }
  })
  .catch(err => {
    console.log("Error:", err);
    throw err;
  });
  }
