package com.clemilton.instagram.login.prensentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.clemilton.instagram.R;
import com.clemilton.instagram.common.view.LoadingButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private LoadingButton buttonEnter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editTextEmail = findViewById(R.id.login_edit_text_email);
        final EditText editTextPassword = findViewById(R.id.login_edit_text_password);

        editTextEmail.addTextChangedListener(watcher);
        editTextPassword.addTextChangedListener(watcher);

        buttonEnter = (LoadingButton)findViewById(R.id.login_button_enter);
        buttonEnter.setOnClickListener( v->{
            buttonEnter.showProgress(true);
            buttonEnter.setEnabled(false);
            new Handler().postDelayed( () -> {
                buttonEnter.showProgress(false);
                buttonEnter.setEnabled(true);
                //acionando erros EditText
                TextInputLayout inputLayoutTest = findViewById(R.id.login_edit_text_email_input);
                inputLayoutTest.setError("Esse email é inválido!");
                editTextEmail.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                        R.drawable.edit_text_background_error));

                TextInputLayout inputLayoutTestP = findViewById(R.id.login_edit_text_password_input);
                inputLayoutTestP.setError("Senha incorreta!");
                editTextPassword.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                        R.drawable.edit_text_background_error));
            },4000);
        });


    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!s.toString().isEmpty()){
                buttonEnter.setEnabled(true);
            }else{
                buttonEnter.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
