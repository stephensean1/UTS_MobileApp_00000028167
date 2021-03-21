package umn.ac.id;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileKu extends AppCompatActivity {

    private Button btnBack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ku);

        btnBack1 = findViewById(R.id.btn_back1);
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileKu.this, HalamanAwal.class);
                startActivity(intent);

            }
        });

    }
}