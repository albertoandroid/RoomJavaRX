package com.androiddesdecero.roomrx.ui.language;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.roomrx.R;
import com.androiddesdecero.roomrx.db.db.AppDb;
import com.androiddesdecero.roomrx.db.entity.Course;
import com.androiddesdecero.roomrx.db.entity.Languages;

import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {

    private EditText etNombre;
    private Button btSalvar, btLeer, btActualizar, btBorrar;
    private Languages language;
    private List<Languages> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        configView();
    }

    private void configView(){
        languages = new ArrayList<>();
        language = new Languages();
        etNombre = findViewById(R.id.languageActivityName);
        btSalvar = findViewById(R.id.languageActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setName(etNombre.getText().toString());
                AppDb.getAppDb(getApplicationContext()).languagesDAO().insert(language);
            }
        });
        btLeer = findViewById(R.id.languageActivityBtLeerCursosPorProfesor);
        btLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();

                for(Languages languages1: languages) {
                    Log.d("TAG", "id: " + languages1.getId() + " Nombre: " + languages1.getName());
                }
            }
        });
        btActualizar = findViewById(R.id.languageActivityBtActualizarCursosPorId);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setId(1);
                language.setName("Java 8.1");
                AppDb.getAppDb(getApplicationContext()).languagesDAO().updateLanguageByID(language);
            }
        });
        btBorrar = findViewById(R.id.languageActivityBtBorrarCursosPorId);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.setId(1);
                AppDb.getAppDb(getApplicationContext()).languagesDAO().deleteLanguageByID(language);
            }
        });
    }
}
