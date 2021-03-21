package umn.ac.id;

import android.content.Intent;
import android.net.Credentials;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.PrivateKey;

public class LoginKuy extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button btnBack2;
    private Button btnLogin;
    private TextView eAttemptsInfo;
    private int counter = 5;
    private String Username = "uasmobile";
    private String Password = "uasmobilegenap";

    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_kuy);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(LoginKuy.this, "Please Enter The Details Correctly", Toast.LENGTH_SHORT).show();
                } else {

                    isValid = validate(inputName, inputPassword);

                    if (!isValid){

                        counter--;

                        Toast.makeText(LoginKuy.this, "Incorrect Credetials Entered!", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText( "No Attempts Remaining: " + counter);

                        if(counter == 0) {
                            btnLogin.setEnabled(false);
                        }

                    }else{
                        Toast.makeText(LoginKuy.this, "Login Succesful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginKuy.this, ListLagu.class);
                        startActivity(intent);
                    }
                }
            }

        });

        btnBack2 = findViewById(R.id.btn_back2);
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginKuy.this, HalamanAwal.class);
                startActivity(intent);

            }
        });
    }
    public void openDialog() {

    }
    private boolean validate(String username, String password)
    {
        if(username.equals(Username) && password.equals(Password))
        {
            return true;
        }

        return false;
    }
}
