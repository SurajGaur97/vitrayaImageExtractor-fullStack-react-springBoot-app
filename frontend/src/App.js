import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import ExtractedImageComponent from './components/ExtractedImageComponent';
import HomeComponent from './components/HomeComponent';
import LoginComponent from './components/LoginComponent';
import ViewImageData from './components/ViewImageData';


function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" Component={LoginComponent} />
          <Route path="/home" Component={HomeComponent} />
          <Route path="/extractedImageView" Component={ExtractedImageComponent} />
          <Route path="/imageView/:id" Component={ViewImageData} />
          {/* Nevigating non familiar url to login page */}
          <Route path="*" element={<Navigate to='/' replace />} /> 
        </Routes>
      </Router>
    </>
  );
}

export default App;
