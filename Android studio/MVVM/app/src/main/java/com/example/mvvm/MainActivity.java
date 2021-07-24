package com.example.mvvm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_SOME_NOTE = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<Intent> open = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            String title = data.getStringExtra(EditNode.EXTRA_TITLE);
                            String dsc = data.getStringExtra(EditNode.EXTRA_DSC);
                            int prioriy = data.getIntExtra(EditNode.EXTRA_PRIORITY, 1);

                            Note note = new Note(title, dsc, prioriy);
                            noteViewModel.inser(note);

                            Toast.makeText(MainActivity.this, "NOTE SAVED", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this, "NOTE NOT SAVED", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );
        ActivityResultLauncher<Intent> edit = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            int id = data.getIntExtra("id",-1);
                            String title = data.getStringExtra(EditNode.EXTRA_TITLE);
                            String dsc = data.getStringExtra(EditNode.EXTRA_DSC);
                            int prioriy = data.getIntExtra(EditNode.EXTRA_PRIORITY, 1);

                            Note note = new Note(title, dsc, prioriy);
                            note.setId(id);
                            noteViewModel.update(note);

                            Toast.makeText(MainActivity.this, "NOTE SAVED", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this, "NOTE NOT SAVED", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );

        FloatingActionButton addNote = findViewById(R.id.addNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNode.class);
//                startActivityForResult(intent, ADD_SOME_NOTE);
                open.launch(intent);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new

                LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RVAdapter adapter = new RVAdapter();
        recyclerView.setAdapter(adapter);


        noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        noteViewModel.getAllNotes().

                observe(this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        //update RecylerView
                        adapter.submitList(notes);
                    }
                });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note DELETED", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new RVAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, EditNode.class);

                intent.putExtra("id", note.getId());
                intent.putExtra(EditNode.EXTRA_TITLE, note.getTitle());
                intent.putExtra(EditNode.EXTRA_DSC, note.getDescription());
                intent.putExtra(EditNode.EXTRA_PRIORITY, note.getPriority());
                edit.launch(intent);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteall:
                noteViewModel.deleteAllNotes();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}