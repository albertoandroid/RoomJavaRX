package com.androiddesdecero.roomrx.ui.professor;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.androiddesdecero.roomrx.db.db.AppDb;
import com.androiddesdecero.roomrx.db.entity.Professor;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by albertopalomarrobledo on 17/4/18.
 */

public class ProfessorModel implements ProfessorI.Model {

    CompositeDisposable disposables;
    private ProfessorI.Presenter presenter;
    private Context context;

    public ProfessorModel(ProfessorI.Presenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
        disposables = new CompositeDisposable();
    }

    @Override
    public void crearProfesor(final Professor professor) {

        Observable<Void> observable = Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                AppDb.getAppDb(context).professorDAO().insertProfesor(professor);
                Log.e("TAG", "Observable: "  + Thread.currentThread().getName());
                e.onComplete();
            }

        });
        Observer<Void> observer = new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubcribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Void void1) {
                Log.d("TAG", "onNext: " + Thread.currentThread().getName());
                //presenter.showProfessors(profesors);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: " + Thread.currentThread().getName());
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void leerTodosProfesores() {
        Observable<List<Professor>> observable = Observable.create(new ObservableOnSubscribe<List<Professor>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Professor>> e) throws Exception {
                e.onNext(AppDb.getAppDb(context).professorDAO().findAllProfessor());
                Log.e("TAG", "Observable: "  + Thread.currentThread().getName());
                e.onComplete();
            }
        });
        Observer<List<Professor>> observer = new Observer<List<Professor>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG", "onSubcribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(List<Professor> profesors) {
                Log.d("TAG", "onNext: " + Thread.currentThread().getName());
                presenter.showProfessors(profesors);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete: " + Thread.currentThread().getName());
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void leerPorNombreProfesor(String nombreProfesor) {
        // adding an Observable to the disposable
        disposables.add(observableGetProfessorPorNombre(nombreProfesor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Professor>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Professor professor) {
                        presenter.showProfessor(professor);
                    }
                }));

    }


    Observable<Professor> observableGetProfessorPorNombre(final String nombre) {
        return Observable.defer(new Callable<ObservableSource<? extends Professor>>() {
            @Override
            public ObservableSource<? extends Professor> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                return Observable.just(AppDb.getAppDb(context).professorDAO().findProfessorByName(nombre));
            }
        });
    }

    @Override
    public void leerPorIdProfesor(int id) {
        // adding an Observable to the disposable
        disposables.add(observableGetProfessorPorId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Professor>() {

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Professor professor) {
                        presenter.showProfessor(professor);
                    }
                }));

    }


    Observable<Professor> observableGetProfessorPorId(final int id) {
        return Observable.defer(new Callable<ObservableSource<? extends Professor>>() {
            @Override
            public ObservableSource<? extends Professor> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                return Observable.just(AppDb.getAppDb(context).professorDAO().findProfessorByID(id));
            }
        });
    }

    @Override
    public void actulizarPorId(Professor professor) {
// adding an Observable to the disposable
        disposables.add(observableUpdateProfessorPorId(professor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Void>() {

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void void1) {
                    //    presenter.showProfessor(professor);
                    }
                }));
    }


    Observable<Void> observableUpdateProfessorPorId(final Professor professor) {
        return Observable.defer(new Callable<ObservableSource<? extends Void>>() {
            @Override
            public ObservableSource<? extends Void> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                AppDb.getAppDb(context).professorDAO().updateProfessorByID(professor);
                return null;
            }
        });
    }

    @Override
    public void borrar() {
// adding an Observable to the disposable
        disposables.add(observableBorrarProfessorPorId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Void>() {

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void void1) {
                        //    presenter.showProfessor(professor);
                    }
                }));
    }


    Observable<Void> observableBorrarProfessorPorId() {
        return Observable.defer(new Callable<ObservableSource<? extends Void>>() {
            @Override
            public ObservableSource<? extends Void> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                AppDb.getAppDb(context).professorDAO().deleteAllProfessor();
                return null;
            }
        });
    }

    @Override
    public void borrarPorId(Professor professor) {
// adding an Observable to the disposable
        disposables.add(observableDeleteProfessorPorId(professor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Void>() {

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void void1) {
                        //    presenter.showProfessor(professor);
                    }
                }));
    }

    Observable<Void> observableDeleteProfessorPorId(final Professor professor) {
        return Observable.defer(new Callable<ObservableSource<? extends Void>>() {
            @Override
            public ObservableSource<? extends Void> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                AppDb.getAppDb(context).professorDAO().deleteProfessorByID(professor);
                return null;
            }
        });
    }


    public void leerTodosProfesores2222(String nombreProfesor) {

        // adding an Observable to the disposable
        disposables.add(observableGetAllProfessor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Professor>>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Professor> professors) {
                        presenter.showProfessors(professors);
                    }
                }));

    }

    Observable<List<Professor>> observableGetAllProfessor() {
        return Observable.defer(new Callable<ObservableSource<? extends List<Professor>>>() {
            @Override
            public ObservableSource<? extends List<Professor>> call() throws Exception {
                // Do some long running operation
                Log.e("TAG", "Observable: " + Thread.currentThread().getName());
                return Observable.just(AppDb.getAppDb(context).professorDAO().findAllProfessor());
            }
        });
    }

    @Override
    public void onDestroy() {
        // Using clear will clear all, but can accept new disposable
        disposables.clear();
        // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
        disposables.dispose();
    }

}
