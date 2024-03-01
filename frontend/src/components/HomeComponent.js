import axios from 'axios'
import React, { useState, useEffect } from 'react';
import { API_BASE_URL, API_ENDPOINTS } from '../util/ApiConfig';
import { useNavigate, Link } from 'react-router-dom';

const HomeComponent = () => {
    const [image, setImage] = useState(null);
    const [selectedImage, setSelectedImage] = useState(null);
    const [resData, setResData] = useState({result: {}});
    const [imageList, setImageList] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    /**
     * @note this helps us to load any content on page load
     */
    useEffect(() => {
        checkIfSessionIsActive();
    }, []); // The empty dependency array ensures that the effect runs only once after the initial render

    /**
     * @note When choosing the image file this eill put the image data into useState for further use.
     * @param {*} event
     */
    const handleImageChange = (event) => {
        setSelectedImage(event.target.files[0]);
        setImage(URL.createObjectURL(event.target.files[0]));
    };

    /**
     * @note this method is for uploading the image and getting extrcated content and carry the content to ExtractedImageComponent page. 
     * @param {*} event 
     */
    const uploadImage = (event) => {
        setLoading(true);
        const uploadImageAPI = `${API_BASE_URL}${API_ENDPOINTS.UploadImage}`;
        event.preventDefault();

        const formData = new FormData();
        formData.append('mediaFile', selectedImage);

        try {
            const headers = {
                'Content-Type': 'multipart/form-data',
                'Accept': '*/*',
            };
            axios.post(uploadImageAPI, formData, { headers: headers, withCredentials: true }).then((response) => {
                if(response.data.status === 202) {
                    setResData(response.data);
                    setLoading(false); // Set loading to false once data is fetched
                    alert(response.data.result.fileName + " Successfully Uploaded!");

                    //Redirecting it to the ExtractedImageComponent page with the data get from above API.
                    navigate('/extractedImageView', { state: { payload: response.data } });
                } else {
                    setLoading(false); // Set loading to false once data is fetched
                    alert("There is some issue while uploading the data!");
                }
            }).catch(error => {
                console.error('Error fetching data:', error);
                setLoading(false); // Set loading to false in case of an error
            });
        } catch (error) {
            console.log(error);
        }
    };

    /**
     * @note this is for logouting the current user.
     */
    const logoutUser = () => {
        //Api for logout user
        const logoutUserAPI = `${API_BASE_URL}${API_ENDPOINTS.logoutUser}`

        const headers = {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': '*/*',
        };
        axios.get(logoutUserAPI, { headers: headers, withCredentials: true }).then(response => {
            if(response.data.status === 200) {
                //After logut redirecting it to login page
                navigate('/');
            }
        }).catch(error => {
            console.error(error);
        });
    }

    /**
     * @note this is for checking if the user is active. if not so then it will redirect to the login page
     */
    const checkIfSessionIsActive = () => {
        //Api for checking active session
        const checkApi = `${API_BASE_URL}${API_ENDPOINTS.isSessionActive}`

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        axios.get(checkApi, { headers: headers, withCredentials: true }).then(response => {
            //redirecting to logout page if response data is false
            if(!response.data) {
                navigate('/');
            } else {
                getImageList();
            }
        }).catch(error => {
            console.error(error);
        });
    }

    const getImageList = () => {
        const getAllImagesApi = `${API_BASE_URL}${API_ENDPOINTS.imageList}`

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        axios.get(getAllImagesApi, { headers: headers, withCredentials: true })
            .then(response => {
                // Update state with the fetched data
                if(response.data.status === 202) {
                    setImageList(response.data.result);
                }
                setLoading(false); // Set loading to false once data is fetched
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                setLoading(false); // Set loading to false in case of an error
            });
    }

    const viewData = (id) => {
        const getImageDataAPI = `${API_BASE_URL}${API_ENDPOINTS.GetImageData}` + id;

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        axios.get(getImageDataAPI, { headers: headers, withCredentials: true }).then((response) => {
            if(response.data.status === 202) {
                //Redirecting it to the ExtractedImageComponent page with the data get from above API.
                navigate('/extractedImageView', { state: { payload: response.data } });
            } else {
                alert("There is some issue while getting the data!");
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            setLoading(false); // Set loading to false in case of an error
        });
        
        console.log(resData.result);
    }


    return (
        <div className="App">
            <header className="App-header">
                <h1 className="text-center">Image Service</h1>
                <div>
                    <button className='button-red' onClick={logoutUser}>Logout</button>
                </div>
                <form onSubmit={uploadImage}>
                    <br /><br /><br />
                    <input type="file" className="button-orange" onChange={handleImageChange} /><br />
                    <br/>
                    <button type="submit" className="button-purple">Upload Image</button><br />
                    {loading ? (<p>Uploading data...</p>) : <p></p>}
                </form>
                <div>
                    <br />
                    {selectedImage &&
                        <>
                            <img height="20%" width="20%" src={image} alt={selectedImage.name} /><br />
                            <p style={{ fontSize: 15 }}>{selectedImage.name}</p>
                        </>
                    }
                </div>

                <div style={{ width: '70%' }}>
                    <h1>All Images List</h1>

                    {loading ? (<p>Loading data...</p>) : (
                        <table  className='styled-table'>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Action with Router link</th>
                                    <th>Action with Nevigation</th>
                                    <th>Image</th>
                                </tr>
                            </thead>
                            <tbody>
                                {/* Dynamically create table rows based on data */}
                                {imageList.map(item => (
                                    <tr key={item.id}>
                                        <td>{item.id}</td>
                                        {/* Use React Router Link to create a link to another component */}
                                        <td><Link className='linkButton1' to={`/imageView/${item.id}`}>{item.fileName}</Link></td>

                                        {/* Here, it is used 'useNavigate' that send whole payload data to other component */}
                                        <td><button className='linkButton2' onClick={() => viewData(item.id)}>{item.fileName}</button></td>
                                        <td>
                                            {item.fileType === 'image/png' && (
                                                <img height="20%" width="20%" src={`data:image/png;base64, ${item.base64String}`} alt={item.fileName} />
                                            )}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </div>
            </header>
        </div>
    );
}

export default HomeComponent
