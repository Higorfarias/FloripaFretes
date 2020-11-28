package br.sc.senai.floripafretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.sc.senai.floripafretes.services.S3Service;

@SpringBootApplication
public class FloripafretesApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(FloripafretesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		s3Service.uploadFile("C:\\Users\\higor\\Desktop\\Backup Notebook\\IMG_20200413_180223.jpg");
	}

	

}
