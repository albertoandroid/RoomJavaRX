package com.androiddesdecero.roomrx.ui.professor;

import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

public interface ProfessorI {
    interface View{
        void showProfessors(List<Professor> professors);
        void showProfessor(Professor professor);
    }
    interface Presenter{
        void showProfessors(List<Professor> professors);
        void showProfessor(Professor professor);

        void crearProfesor(Professor professor);
        void leerTodosProfesores();
        void leerPorNombreProfesor(String nombreProfesor);
        void leerPorIdProfesor(int id);
        void actulizarPorId(Professor professor);
        void borrar();
        void borrarPorId(Professor professor);
        void onDestroy();
    }
    interface Model{
        void crearProfesor(Professor professor);
        void leerTodosProfesores();
        void leerPorNombreProfesor(String nombreProfesor);
        void leerPorIdProfesor(int id);
        void actulizarPorId(Professor professor);
        void borrar();
        void borrarPorId(Professor professor);
        void onDestroy();
    }
}
