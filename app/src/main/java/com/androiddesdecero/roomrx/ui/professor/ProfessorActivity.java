package com.androiddesdecero.roomrx.ui.professor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.roomrx.R;
import com.androiddesdecero.roomrx.db.db.AppDb;
import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity implements ProfessorI.View {

    private EditText etNombre, etEmail;
    private Button btSalvar, btLeerTodo, btLeerPorNombre, btLeerPorId, actualizarPorId, btBorrar, btBorrarPorId;

    private Professor professor;
    private List<Professor> listaProfessors;

    private ProfessorI.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        configView();
    }

    private void configView() {
        presenter = new ProfessorPresenter(this, getApplicationContext());
        professor = new Professor();
        listaProfessors = new ArrayList<>();
        etNombre = findViewById(R.id.professorActivityEtNombre);
        etEmail = findViewById(R.id.professorActivityEtEmail);

        btSalvar = findViewById(R.id.professorActivityBtSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor.setName(etNombre.getText().toString());
                professor.setEmail(etEmail.getText().toString());
                presenter.crearProfesor(professor);
            }
        });

        btLeerTodo = findViewById(R.id.professorActivityBtLeer);
        btLeerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.leerTodosProfesores();
            }
        });

        btLeerPorNombre = findViewById(R.id.professorActivityBtFindByName);
        btLeerPorNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.leerPorNombreProfesor(etNombre.getText().toString());
            }
        });

        btLeerPorId = findViewById(R.id.professorActivityBtFindByID);
        btLeerPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.leerPorIdProfesor(2);
            }
        });

        actualizarPorId = findViewById(R.id.professorActivityBtUpdateById);
        actualizarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(1);
                professor.setName("alberto");
                professor.setEmail("a@a.com");
                presenter.actulizarPorId(professor);
            }
        });

        btBorrar = findViewById(R.id.professorActivityBtDelete);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.borrar();
            }
        });

        btBorrarPorId = findViewById(R.id.professorActivityBtDeleteById);
        btBorrarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(4);
                presenter.borrarPorId(professor);
            }
        });
    }

    @Override
    public void showProfessors(List<Professor> professors) {
        for(Professor professor: professors) {
            Log.d("TAG", professor.getEmail());
        }
    }

    @Override
    public void showProfessor(Professor professor) {
        Log.d("TAG", professor.getEmail() + " " + professor.getName() + " " + professor.getId());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }
}
