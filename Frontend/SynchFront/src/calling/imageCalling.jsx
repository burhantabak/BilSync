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
        throw new Error('File upload failed');
      }
    })
    .then(result=>{console.log(result);return result;})
    .catch((error) => {
      throw new Error(`Error during file upload: ${error.message}`);
    });
  };
