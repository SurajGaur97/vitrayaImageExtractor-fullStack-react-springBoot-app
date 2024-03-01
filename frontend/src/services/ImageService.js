import axios from 'axios'
import { API_BASE_URL, API_ENDPOINTS } from '../util/ApiConfig';

class ImageService {

    // uploadImage(formData) {
    //     const getImageDataUrl = `${API_BASE_URL}${API_ENDPOINTS.GetImageData}`;
    //     const resData = axios.post('your-upload-endpoint', formData);
        
    //     if (resData.ok) {
    //         // File uploaded successfully
    //     } else {
    //         // File upload failed
    //     }
    // }

    getExtractedImage(id) {
        debugger
        const getImageData = `${API_BASE_URL}${API_ENDPOINTS.GetImageData}` + id;
        axios.post(getImageData).then((response) => {
            console.log(response);
            return response;
        });
    }

    login(loginCred) {        
        debugger
        const loginUser = `${API_BASE_URL}${API_ENDPOINTS.loginUser}`

        //Post method calling
        let login = axios.post(loginUser, loginCred);
        
        login.then(response => {
            window.sessionStorage.setItem('token', response.data.token);
            this.storeData('userData', response.data);

        }).catch(error => {
            console.error(error);
        });
    }

}

// export default new ImageService();
export default ImageService
