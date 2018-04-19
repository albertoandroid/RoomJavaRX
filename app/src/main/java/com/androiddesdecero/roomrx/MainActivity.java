package com.androiddesdecero.roomrx;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androiddesdecero.roomrx.db.entity.Course;
import com.androiddesdecero.roomrx.db.entity.Languages;
import com.androiddesdecero.roomrx.ui.course.CourseActivity;
import com.androiddesdecero.roomrx.ui.innerjoinprofesorlenguaje.InnerJoinProfesorLenguajeActivity;
import com.androiddesdecero.roomrx.ui.language.LanguageActivity;
import com.androiddesdecero.roomrx.ui.professor.ProfessorActivity;

public class MainActivity extends AppCompatActivity {

    private Button btProfessor, btCurso, btLenguajes, btInnerJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }

    private void configView(){
        btProfessor = findViewById(R.id.mainActivityBtProfessor);
        btProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfessorActivity.class));
            }
        });

        btCurso = findViewById(R.id.mainActivityBtCursos);
        btCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CourseActivity.class));
            }
        });

        btLenguajes = findViewById(R.id.mainActivityBtLanguajes);
        btLenguajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LanguageActivity.class));
            }
        });

        btInnerJoin = findViewById(R.id.mainActivityBtInnerJoin);
        btInnerJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InnerJoinProfesorLenguajeActivity.class));
            }
        });
    }
}
