package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private Noterepository noterepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull  Application application) {
        super(application);
        noterepository = new Noterepository(application);
        allNotes = noterepository.getAllNotes();
    }
    public  void inser(Note note){
        noterepository.insert(note);
    }
    public  void delete(Note note){
        noterepository.delete(note);
    }
    public  void update(Note note){
        noterepository.update(note);
    }
    public  void deleteAllNotes(){
        noterepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
