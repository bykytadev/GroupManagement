package com.vti.dto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class ChangePublicProfileDTO {

	public static boolean isBase64(String input) {
        try {
            // Attempt to decode the input
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            
            // If decoding is successful, it's a valid Base64 string
            return true;
        } catch (IllegalArgumentException e) {
            // If an exception is thrown, it's not a valid Base64 string
            return false;
        }
    }

	public static void writeToFile(byte[] data, String outputPath) {
        try {
            Path filePath = Paths.get(outputPath);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, data, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	// TODO validate
	private String avatarUrl;

	private String biography;

	public ChangePublicProfileDTO() {
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		if (isBase64(avatarUrl)) {
			Boolean isSuccess = false;
			String outputPath = "src/main/java/com/vti/images/output.jpg";
			try {
				byte[] decodedBytes = Base64.getDecoder().decode(avatarUrl);
            	writeToFile(decodedBytes, outputPath);
				isSuccess = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isSuccess) {
				this.avatarUrl = "http://localhost:8080/api/v1/files/output.jpg";
			}
		} else {
			this.avatarUrl = avatarUrl;
		}
		
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

}
