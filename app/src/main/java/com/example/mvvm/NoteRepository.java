package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(final Note note, final NoteAdapter adapter) {
        Single<Note> single =Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                noteDao.insert(note);
                if (!emitter.isDisposed())
                    emitter.onSuccess(note);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Note note) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void update(final Note note, final NoteAdapter adapter) {
        Single<Note> single =Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                noteDao.update(note);
                if (!emitter.isDisposed())
                    emitter.onSuccess(note);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Note note) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void delete(final Note note, final NoteAdapter adapter) {
        Single<Note> single = Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                noteDao.delete(note);
                if (!emitter.isDisposed())
                    emitter.onSuccess(note);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Note note) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteAllNotes(final NoteAdapter adapter) {
        Single single = Single.create(new SingleOnSubscribe() {
            @Override
            public void subscribe(SingleEmitter emitter) throws Exception {
                noteDao.deleteAllNotes();
                if (!emitter.isDisposed())
                    emitter.onSuccess(null);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}