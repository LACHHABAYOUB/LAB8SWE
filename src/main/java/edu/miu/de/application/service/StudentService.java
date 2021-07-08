package edu.miu.de.application.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.de.application.domain.Student;
import edu.miu.de.application.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	public StudentRepository studentRepository;

	public List<Student> getStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student getStudentById(Integer id) {
		return studentRepository.findById(id).orElse(null);
	}

	public void deleteStudentById(Integer studentId) {
		studentRepository.deleteById(studentId);
	}

	public List<Student> getStudentByNumber(String number) {
		return studentRepository.getStudentByNumber(number);
	}


	public List<Student> searchStudens(String searchString) {
		if(containsDecimalPoint(searchString) && IsGpa(searchString)) {
			return studentRepository.findAllByCgpa(Double.parseDouble(searchString));
		} else if(isDate(searchString)) {
			return studentRepository.findAllByEnrollmentDate(LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE));
		} else {
			return studentRepository.findAllByNumberOrFirstNameOrMiddleNameOrLastName(searchString, searchString,searchString,searchString);
		}
	}

	private boolean isDate(String searchString) {
		boolean isParseableAsDate = false;
		try {
			LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
			isParseableAsDate = true;
		} catch(Exception ex) {
			if(ex instanceof DateTimeParseException) {
				isParseableAsDate = false;
			}
		}
		return isParseableAsDate;
	}

	private boolean IsGpa(String searchString) {
		boolean isParseableAsMoney = false;
		try {
			Double.parseDouble(searchString);
			isParseableAsMoney = true;
		} catch(Exception ex) {
			if(ex instanceof ParseException) {
				isParseableAsMoney = false;
			}
		}
		return isParseableAsMoney;
	}

	private boolean containsDecimalPoint(String searchString) {
		return searchString.contains(".");
	}

}