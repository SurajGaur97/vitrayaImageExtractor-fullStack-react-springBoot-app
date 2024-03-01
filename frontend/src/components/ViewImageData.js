import axios from 'axios'
import React, { useState, useEffect } from 'react';
import { API_BASE_URL, API_ENDPOINTS } from '../util/ApiConfig';
import { useParams, useNavigate } from 'react-router-dom';

const ViewImageData = () => {
    const navigate = useNavigate();
    const { id } = useParams(); //this is used for getting dynamic id from link
    const [resData, setResData] = useState({result: {}});

    const itemId = parseInt(id, 10);

    /**
     * @note this helps us to load any content on page load
     */
    useEffect(() => { 
        //if session data is null then push back to login page
        checkIfSessionIsActive();

        if(itemId !== undefined && itemId !== null) {
            viewData(itemId);
        } else {
            logoutUser();
        }
    }, []); // The empty dependency array ensures that the effect runs only once after the initial render

    /**
     * @note method for nevigating the to home page
     */
    const nevigateToHome = () => {
        navigate('/home');
    }

    /**
     * @note function for getting image data by id
     * @param {*} id 
     */
    const viewData = (id) => {
        const getImageDataAPI = `${API_BASE_URL}${API_ENDPOINTS.GetImageData}` + id;

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        axios.get(getImageDataAPI, { headers: headers, withCredentials: true }).then((response) => {
            if(response.data.status === 202) {
                setResData(response.data);
            } else {
                alert("There is some issue while geting the data!");
            }
        }).catch(error => {
            console.error('Error fetching data:', error);
        });
    }

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
        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        //Api for checking active session
        const checkApi = `${API_BASE_URL}${API_ENDPOINTS.isSessionActive}`

        axios.get(checkApi, { headers: headers, withCredentials: true }).then(response => {
            //redirecting to logout page if response data is false
            if(!response.data) {
                navigate('/');
            }        
        }).catch(error => {
            console.error(error);
        });
    }



    return (
        <div className="App">
            <header className="App-header">
                <h1 className="text-center">Extracted Content</h1>
                <div>
                    <button className="button-red" onClick={logoutUser}>Logout</button> &nbsp;&nbsp;&nbsp;&nbsp;
                    <button className="button-orange" onClick={nevigateToHome}>Home</button>
                </div>
                <br />
                {/* checking if the payloadData present then only it went to DOMS elements for view. */}
                {resData.result &&
                    <>
                    <table> 
                        <tbody>
                            <tr>
                                <td style={{width: '50%'}}>
                                    <img height="40%" width="40%" alt={resData.result.name} src={"data:" + resData.result.fileType + ";base64," + resData.result.base64String}></img>
                                </td>
                                <td style={{width: '50%', textAlign: 'left'}}>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    Extracted Text:&nbsp;&nbsp;&nbsp;
                                                </td>
                                                <td>
                                                    <textarea className="text-box" style={{height: 150, width: 400}} value={resData.result.extractedText} readOnly /><br />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style={{textAlign: 'right'}}>
                                                    Bold Text:&nbsp;&nbsp;&nbsp; 
                                                </td>
                                                <td>
                                                    <textarea className="text-box" style={{height: 150, width: 400}} value={resData.result.boldText} readOnly />
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    </>
                }
            </header>
        </div>
    )
}

export default ViewImageData;