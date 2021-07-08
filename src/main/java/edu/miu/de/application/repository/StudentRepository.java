package edu.miu.de.application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.miu.de.application.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	@Query("select s from Student s where number = ?1")
	public List<Student> getStudentByNumber(String number);

	public abstract List<Student> findAllByNumberOrFirstNameOrMiddleNameOrLastName(String number, String firstName,String middleName , String lastName);
	public abstract List<Student> findAllByEnrollmentDate(LocalDate enrollmentDate);
	public abstract List<Student> findAllByCgpa(Double cgpa);


}
