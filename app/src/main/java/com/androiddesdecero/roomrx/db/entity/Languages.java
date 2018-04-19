package com.androiddesdecero.roomrx.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.androiddesdecero.roomrx.constans.Constans;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

@Entity(tableName = Constans.TABLE_NAME_LANGUAGES)
public class Languages {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
