package com.androiddesdecero.roomrx.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.androiddesdecero.roomrx.db.entity.Course;
import com.androiddesdecero.roomrx.db.entity.Languages;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

@Dao
public interface LanguagesDAO {

    @Insert
    void insert(Languages languages);

    @Query("SELECT * FROM languages")
    List<Languages> findAllLanguages();

    @Update
    void updateLanguageByID(Languages languages);

    @Delete
    void deleteLanguageByID(Languages languages);
}
