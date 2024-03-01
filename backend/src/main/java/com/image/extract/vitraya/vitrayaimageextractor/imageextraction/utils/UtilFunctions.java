package com.image.extract.vitraya.vitrayaimageextractor.imageextraction.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.ocr.AsposeOCR;
import com.aspose.ocr.Language;
import com.aspose.ocr.RecognitionResult;
import com.aspose.ocr.RecognitionSettings;
import com.asprise.ocr.*;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



@Component
public class UtilFunctions {

	@Value("${image.tesseract.inst.loc}")
	private String tesseractInstalledLoc;

	/**
	 * @throws Exception
	 * @implNote Method for extracting image text using 'Tesseract' library.
	 */
	public String extractTextByTesseract(MultipartFile mediaFile)
			throws IllegalStateException, IOException, TesseractException {
		ITesseract tesseract = new Tesseract();

		// The path of tess-data folder inside the extracted file
		tesseract.setDatapath(tesseractInstalledLoc);

		// Converting multi-part into File and then getting the text in image.
		File file = multipartToFile(mediaFile);
		String text = tesseract.doOCR(file);

		return text;
	}

	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @implNote Method for extracting image Text using 'Asprise' library.
	 */
	public void extractTextByAsprise(MultipartFile mediaFile) throws IllegalStateException, IOException {
		File file = multipartToFile(mediaFile);

		Ocr.setUp();
		Ocr ocr = new Ocr();

		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English

		String s = ocr.recognize(new File[] { file }, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println("Result: " + s);
	}
	
	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @implNote Method for extracting image Text using 'Aspose' library.
	 */
	public String extractTextByAspose(MultipartFile mediaFile) throws Exception {
		// Create instance of OCR API
		AsposeOCR api = new AsposeOCR();
		// Activate font upscaling 
		RecognitionSettings recognitionSettings = new RecognitionSettings();
		recognitionSettings.setLanguage(Language.Eng);		
		// Extract text from image
		RecognitionResult result = api.RecognizePage("C:\\Users\\suraj.gaur\\Downloads\\Assignement.png", recognitionSettings);
		System.out.println("Recognition result:\n" + result.recognitionText);
		
		return "";
	}

	/**
	 * @implNote method for converting MultiPartFile to Base64 String data to store
	 *           it into database table.
	 * @param multipart
	 * @return String
	 * @throws Exception
	 */
	public String encodeImageToBase64(MultipartFile multipart) throws Exception {
		File file = multipartToFile(multipart);

		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bytesArray = new byte[(int) file.length()];
		fileInputStream.read(bytesArray);
		fileInputStream.close();

		return Base64.encodeBase64String(bytesArray);
	}

	/**
	 * @implNote this method is created for converting MultiPartFile to a java i/o
	 *           File
	 * @param multipart
	 * @return File
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);

		fos.write(multipart.getBytes());
		fos.close();

		return convFile;
	}

	/**
	 * @implNote this utility is created for getting back the base64 string to
	 *           MultiPartFile to view it on the HTML page
	 * @param base64String
	 * @param fileName
	 * @param contentType
	 * @return MultipartFile
	 * @throws IOException
	 */
	public MultipartFile createMultipartFile(String base64String, String fileName, String contentType)
			throws IOException {
		// Convert Base64 string to byte array
		byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64String);

		// Creating MutipartFile using our CustomMultipartFile class.
		return new CustomeMultipartFile(fileName, fileName, contentType, decodedBytes);
	}

}
