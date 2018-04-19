package com.androiddesdecero.roomrx.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

@Dao
public interface ProfessorDAO {
    @Insert
    void insertProfesor(Professor professor);

    @Query("SELECT * FROM professor")
    List<Professor> findAllProfessor();

    @Query("SELECT * FROM professor where name LIKE :name")
    Professor findProfessorByName(String name);

    @Query("SELECT * FROM professor where id LIKE :id")
    Professor findProfessorByID(int id);

    @Update
    void updateProfessorByID(Professor professor);

    @Query("DELETE FROM professor")
    void deleteAllProfessor();

    @Delete
    void deleteProfessorByID(Professor professor);
}
