package com.androiddesdecero.roomrx.ui.professor;

import android.content.Context;

import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.List;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

public class ProfessorPresenter implements ProfessorI.Presenter {

    private ProfessorI.View view;
    private ProfessorI.Model model;

    public ProfessorPresenter(ProfessorI.View view, Context context){
        this.view = view;
        model = new ProfessorModel(this, context);
    }


    @Override
    public void showProfessors(List<Professor> professors) {
        view.showProfessors(professors);
    }

    @Override
    public void showProfessor(Professor professor) {
        view.showProfessor(professor);
    }

    @Override
    public void crearProfesor(Professor professor) {
        model.crearProfesor(professor);
    }

    @Override
    public void leerTodosProfesores() {
        model.leerTodosProfesores();
    }

    @Override
    public void leerPorNombreProfesor(String nombreProfesor) {
        model.leerPorNombreProfesor(nombreProfesor);
    }

    @Override
    public void leerPorIdProfesor(int id) {
        model.leerPorIdProfesor(id);
    }

    @Override
    public void actulizarPorId(Professor professor) {
        model.actulizarPorId(professor);
    }

    @Override
    public void borrar() {
        model.borrar();
    }

    @Override
    public void borrarPorId(Professor professor) {
        model.borrarPorId(professor);
    }

    @Override
    public void onDestroy() {
        model.onDestroy();
    }
}
