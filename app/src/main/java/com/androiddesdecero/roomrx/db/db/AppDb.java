package com.androiddesdecero.roomrx.db.db;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androiddesdecero.roomrx.constans.Constans;
import com.androiddesdecero.roomrx.db.dao.CourseDAO;
import com.androiddesdecero.roomrx.db.dao.LanguagesDAO;
import com.androiddesdecero.roomrx.db.dao.ProfesorLenguajeJoinDAO;
import com.androiddesdecero.roomrx.db.dao.ProfessorDAO;
import com.androiddesdecero.roomrx.db.entity.Course;
import com.androiddesdecero.roomrx.db.entity.Languages;
import com.androiddesdecero.roomrx.db.entity.ProfesorLenguajeJoin;
import com.androiddesdecero.roomrx.db.entity.Professor;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

@Database(entities = {Professor.class, Course.class, Languages.class, ProfesorLenguajeJoin.class}, version = 2)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;
    public abstract ProfessorDAO professorDAO();
    public abstract CourseDAO courseDAO();
    public abstract LanguagesDAO languagesDAO();
    public abstract ProfesorLenguajeJoinDAO profesorLenguajeJoinDAO();


    public static AppDb getAppDb(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constans.NOMBREDATABASE)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
