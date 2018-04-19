package com.androiddesdecero.roomrx.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androiddesdecero.roomrx.db.entity.Languages;
import com.androiddesdecero.roomrx.db.entity.ProfesorLenguajeJoin;
import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

@Dao
public interface ProfesorLenguajeJoinDAO {

    @Insert
    void insert(ProfesorLenguajeJoin profesorLenguajeJoin);

    @Query("SELECT * FROM professor INNER JOIN innerjoinprofesorlenguaje ON professor.id=innerjoinprofesorlenguaje.profesorId WHERE innerjoinprofesorlenguaje.lenguajeId=:lenguajeId")
    List<Professor> getProfessorForRepository(int lenguajeId);

    @Query("SELECT * FROM languages INNER JOIN innerjoinprofesorlenguaje ON languages.id=innerjoinprofesorlenguaje.lenguajeId WHERE innerjoinprofesorlenguaje.profesorId=:profesorId")
    List<Languages> getLanguagesForRepository(int profesorId);

}
