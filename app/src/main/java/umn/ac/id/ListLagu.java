package umn.ac.id;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListLagu extends AppCompatActivity {
    ListView allMusicList;
    ArrayAdapter<String> musicArrayAdapter;
    String songs[];
    ArrayList<File> musics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);

        allMusicList = findViewById(R.id.listView);

        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                musics = findMusicFiles(Environment.getExternalStorageDirectory());
                songs = new String[musics.size()];
                for (int i = 0; i <musics.size(); i++) {
                    songs[i] = musics.get(i).getName();
                }

                musicArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, songs);

                allMusicList.setAdapter(musicArrayAdapter);

                allMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        startActivity(new Intent(ListLagu.this, MulaiLagu.class)
                                .putExtra("songsList", musics)
                                .putExtra("position", position));
                    }
                });
            }
            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            }
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
        Login();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_profile: {
                Intent intent = new Intent(ListLagu.this, ProfileOption.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_menu_logout: {
                Intent logoutIntent = new Intent(ListLagu.this,
                        HalamanAwal.class);
                startActivity(logoutIntent);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private ArrayList<File> findMusicFiles (File file) {
        ArrayList<File> musicfileobject = new ArrayList<>();
        File [] files = file.listFiles();
        for (File currentFiles: files) {
            if (currentFiles.isDirectory() && !currentFiles.isHidden()) {
                musicfileobject.addAll(findMusicFiles(currentFiles));
            } else {
                if (currentFiles.getName().endsWith(".mp3") || currentFiles.getName().endsWith(".mp4a") || currentFiles.getName().endsWith(".wav")) {
                    musicfileobject.add(currentFiles);

                }
            }
        }
        return musicfileobject;
    }
    public void Login() {
        PopUp popup = new PopUp();
        popup.show(getSupportFragmentManager(), "PopUp");
    }

}

