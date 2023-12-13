export function getAllPosts(user) {
  const sentUrl = "http://localhost:8080/post/getAllPosts";

  return fetch(sentUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.token}`
    },
  })
  .then(response => {
    console.log("finished fetch");
    console.log(response.status);

    if (response.status === 200) {
      return response.json();
    } else {
      throw new Error(`Failed to authenticate. Status: ${response.status}`);
    }
  })
  .catch(err => {
    console.log("Error:", err);
    throw err;
  });
}
