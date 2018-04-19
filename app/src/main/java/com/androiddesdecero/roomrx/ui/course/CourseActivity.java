package com.androiddesdecero.roomrx.ui.course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.roomrx.R;
import com.androiddesdecero.roomrx.db.db.AppDb;
import com.androiddesdecero.roomrx.db.entity.Course;
import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private EditText etIdProfesor, etNombre, etDuraction;
    private Button btSalvar, btLeerCursosProfesor, btActualizarPorId, btBorrarPorId;
    private Course course;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        configView();
    }


    private void configView(){
        course = new Course();
        courses = new ArrayList<>();
        etIdProfesor = findViewById(R.id.courseActivityIdProfesor);
        etNombre = findViewById(R.id.courseActivityName);
        etDuraction = findViewById(R.id.courseActivityIdDuracion);

        btSalvar = findViewById(R.id.courseActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setDuration(etDuraction.getText().toString());
                course.setName(etNombre.getText().toString());
                course.setProfessorId(Integer.parseInt(etIdProfesor.getText().toString()));
                AppDb.getAppDb(getApplicationContext()).courseDAO().insert(course);
            }
        });

        btLeerCursosProfesor = findViewById(R.id.courseActivityBtLeerCursosPorProfesor);
        btLeerCursosProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForProfessor(Integer.parseInt(etIdProfesor.getText().toString()));
                for(Course course: courses) {
                    Log.d("TAG", "id: " + course.getId() + " Nombre: " + course.getName() + " Duration: " + course.getDuration() + " idProfesor: " + course.getProfessorId());
                }
            }
        });

        btActualizarPorId = findViewById(R.id.courseActivityBtActualizarCursosPorId);
        btActualizarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(1);
                course.setDuration("34");
                course.setName("Android Room");
                course.setProfessorId(1);
                AppDb.getAppDb(getApplicationContext()).courseDAO().updateCourseByID(course);
            }
        });


        btBorrarPorId = findViewById(R.id.courseActivityBtBorrarCursosPorId);
        btBorrarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(1);
                //course.setDuration("34");
                //course.setName("Android Room");
                //course.setProfessorId(1);
                AppDb.getAppDb(getApplicationContext()).courseDAO().deleteCourseByID(course);
            }
        });
    }
}
