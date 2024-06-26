package com.aneek.book.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import com.aneek.book.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override							// use to represent a file uploaded via web
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//file->path
		// File namew
				String name = file.getOriginalFilename();
				// abc.png

				// random name generate file
				String randomID = UUID.randomUUID().toString();
				String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

				// Full path
				String filePath = path + File.separator + fileName1;
										// add slash("/")

				// create folder if not created
				File f = new File(path);
				if (!f.exists()) {
					f.mkdir();
				}

				// file copy

				Files.copy(file.getInputStream(), Paths.get(filePath));

				return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		// db logic to return inpustream
		return is;
	}

}
