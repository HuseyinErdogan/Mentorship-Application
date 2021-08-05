export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
    const accessToken = JSON.parse(localStorage.getItem('accessToken'));
  
    if (user && accessToken) {
      return { Authorization: 'Bearer ' + accessToken };
    } else {
      return {};
    }
}