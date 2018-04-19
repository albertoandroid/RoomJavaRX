package com.androiddesdecero.roomrx.ui.innerjoinprofesorlenguaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.roomrx.R;
import com.androiddesdecero.roomrx.db.db.AppDb;
import com.androiddesdecero.roomrx.db.entity.Languages;
import com.androiddesdecero.roomrx.db.entity.ProfesorLenguajeJoin;
import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public class InnerJoinProfesorLenguajeActivity extends AppCompatActivity {

    private EditText etIdProfesor, etIdLenguaje;
    private Button btSalvar, btGetProfesor, btGetLenguaje;
    private ProfesorLenguajeJoin profesorLenguajeJoin;
    private List<Professor> professors;
    private List<Languages> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_join_profesor_lenguaje);

        configView();
    }

    private void configView(){
        professors = new ArrayList<>();
        languages = new ArrayList<>();
        profesorLenguajeJoin = new ProfesorLenguajeJoin();
        etIdProfesor = findViewById(R.id.innerJoinActivityEtProfesorId);
        etIdLenguaje = findViewById(R.id.innerJoinActivityEtLenguajeId);

        btSalvar = findViewById(R.id.innerJoinActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profesorLenguajeJoin.setLenguajeId(Integer.parseInt(etIdLenguaje.getText().toString()));
                profesorLenguajeJoin.setProfesorId(Integer.parseInt(etIdProfesor.getText().toString()));
                AppDb.getAppDb(getApplicationContext()).profesorLenguajeJoinDAO().insert(profesorLenguajeJoin);
            }
        });
        btGetProfesor = findViewById(R.id.innerJoinActivityBtLeerProfesor);
        btGetProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //profesorLenguajeJoin.setLenguajeId(1);
                //profesorLenguajeJoin.setProfesorId(1);
                professors = AppDb.getAppDb(getApplicationContext()).profesorLenguajeJoinDAO().getProfessorForRepository(1);
                for(Professor professor: professors) {
                    Log.d("TAG", professor.getName());
                }
            }
        });

        btGetLenguaje = findViewById(R.id.innerJoinActivityBtLeerLenguaje);
        btGetLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages = AppDb.getAppDb(getApplicationContext()).profesorLenguajeJoinDAO().getLanguagesForRepository(1);
                for(Languages languages: languages){
                    Log.d("TAG", languages.getName());
                }
            }
        });
    }
}
