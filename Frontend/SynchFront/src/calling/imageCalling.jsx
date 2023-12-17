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
    .then(result=>{return result;})
    .catch((error) => {
      throw new Error(`Error during file upload: ${error.message}`);
    });
  };
export function getImage(imageName, user) {
  console.log("imageımı alayım lütfen" + imageName)
    const imageUrl = `http://localhost:8080/file/${imageName}`;
    if(!imageName){
        return "";
    }
    return fetch(imageUrl, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.token}`
        },
    })
        .then(response => {
          console.log(response)
            if (response.status === 200) {
                return response.blob();
            } else {
                throw new Error(`Failed to fetch image. Status: ${response.status}`);
            }
        })
        .then(blob => {
            // Create a data URL from the blob
            return new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.onloadend = () => resolve(reader.result);
                reader.onerror = reject;
                reader.readAsDataURL(blob);
            });
        })
        .then(dataUrl => {
            return dataUrl;
        })
        .catch(error => {
            console.error('Error fetching image:', error);
            throw error;
        });
}

