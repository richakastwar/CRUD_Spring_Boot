package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity  manager
    private EntityManager entityManager;


    // inject entity manager using constructor

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);

    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {


        // Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastname  ",Student.class);

        //Return query results

        return theQuery.getResultList();
    }
    @Override
    public List<Student> findByLastname(String theLastname) {
        // Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery( "From Student WHERE lastname=:theData",Student.class);

        // Set Query Parameters
        theQuery.setParameter("theData",theLastname);


        // Return Query Results

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class,id);

        // delete the student
        entityManager.remove(theStudent);


    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return  numRowsDeleted ;
    }
}
