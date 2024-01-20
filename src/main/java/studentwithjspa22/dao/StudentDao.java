package studentwithjspa22.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import studentwithjspa22.dto.Student;

public class StudentDao {
	
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vinod").createEntityManager();
	}

	public List<Student> getAllStudents() {
		EntityManager entityManager=getEntityManager();
		Query query=entityManager.createQuery("Select s from Student s");
		return query.getResultList();
	}

	public void saveStudent(Student student) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
	    entityTransaction.begin();
	    entityManager.persist(student);
	    entityTransaction.commit();
	}

	public void deleteStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student student=entityManager.find(Student.class, id);
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(student);
		entityTransaction.commit();
	}

	public Student getStudentById(int id) {
		EntityManager entityManager=getEntityManager();
		Student student=entityManager.find(Student.class, id);
		return student;
	}

	public void UpdateStudent(Student student) {
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
	    entityTransaction.begin();
	    entityManager.merge(student);
	    entityTransaction.commit();
	}

}
