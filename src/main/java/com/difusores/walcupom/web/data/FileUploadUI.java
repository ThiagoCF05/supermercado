package com.difusores.walcupom.web.data;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUI {
	private MultipartFile image;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
