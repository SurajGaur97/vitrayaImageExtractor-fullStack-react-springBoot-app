
const API_BASE_URL = 'http://localhost:8080/api';

const API_ENDPOINTS = {
    GetImageData: '/image/extractor/v1/getImageData?id=',
    UploadImage: '/image/extractor/v1/uploadImage',
    loginUser: '/auth/v1/authenticateUser',
    logoutUser: '/auth/v1/invalidateUser',
    isSessionActive: '/auth/v1/isSessionActive',
    imageList: '/image/extractor/v1/getImagesList'
};

export { API_BASE_URL, API_ENDPOINTS };
