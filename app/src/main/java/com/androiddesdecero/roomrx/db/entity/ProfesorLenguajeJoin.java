package com.androiddesdecero.roomrx.db.entity;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.androiddesdecero.roomrx.constans.Constans;

@Entity(tableName = Constans.TABLE_NAME_INNER_JOIN,
        primaryKeys = {"profesorId", "lenguajeId"},
        foreignKeys = {
                @ForeignKey(entity = Professor.class,
                            parentColumns = "id",
                            childColumns = "profesorId"),
                @ForeignKey(entity = Languages.class,
                            parentColumns = "id",
                            childColumns = "lenguajeId")
        })
public class ProfesorLenguajeJoin {
    @ColumnInfo(name = "profesorId")
    public int profesorId;
    @ColumnInfo(name = "lenguajeId")
    public int lenguajeId;

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public int getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(int lenguajeId) {
        this.lenguajeId = lenguajeId;
    }
}
