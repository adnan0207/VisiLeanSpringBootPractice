package com.visilean.cruddemo.dao;

import com.visilean.cruddemo.entity.Instructor;

public interface AppDAO {

	public void save(Instructor theInstructor);

	public Instructor findInstructorById(int theId);

	public void deleteInstructorByID(int theId);

}
