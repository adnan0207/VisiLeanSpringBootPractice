package com.visilean.cruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visilean.cruddemo.entity.Course;
import com.visilean.cruddemo.entity.Instructor;
import com.visilean.cruddemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	// define field for entity manager
	private EntityManager entityManager;

	// inject entity manager using constructor injection
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		entityManager.persist(theInstructor);
	}

	@Override
	public Instructor findInstructorById(int theId) {
		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorByID(int theId) {
		// retrieve the instructor
		Instructor tempInstructor = entityManager.find(Instructor.class, theId);
		
		// get the course
		List<Course> courses = tempInstructor.getCourses();
		
		// break the association with instructor
		for(Course tempCourse : courses) {
			tempCourse.setInstructor(null);
		}
		
		// delete the instructor
		entityManager.remove(tempInstructor);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int theId) {
		return entityManager.find(InstructorDetail.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailByID(int theId) {
		// retrieve the instructor detail
		InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
		
		// remove the associated object reference
		// break the bi-directional link
		tempInstructorDetail.getInstructor().setInstructorDetail(null);
		
		// delete the instructor detail
		entityManager.remove(tempInstructorDetail);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		// create the query
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", theId);
		
		// execute the query
		List<Course> courses = query.getResultList();
		return courses;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int theId) {
		// create query
		TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " +
																	"JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);
		query.setParameter("data", theId);
		
		// execute the query
		Instructor instructor = query.getSingleResult();
		return instructor;
	}

	@Override
	@Transactional
	public void update(Instructor instructor) {
		entityManager.merge(instructor);
	}

	@Override
	@Transactional
	public void update(Course course) {
		entityManager.merge(course);
	}

	@Override
	public Course findCourseById(int theId) {
		return entityManager.find(Course.class, theId);
	}

	@Override
	@Transactional
	public void deleteCourseById(int theId) {
		// retrieve the course
		Course tempCourse = entityManager.find(Course.class, theId);
		
		// delete the course
		entityManager.remove(tempCourse);
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		entityManager.persist(theCourse);
	}

}
