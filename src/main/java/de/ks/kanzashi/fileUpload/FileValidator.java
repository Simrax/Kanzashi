package de.ks.kanzashi.fileUpload;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return FileUpload.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		FileUpload file = (FileUpload) obj;
		if(file.getFile().getSize() == 0){
			errors.rejectValue("file", "valid.file");
		}
	}

}
