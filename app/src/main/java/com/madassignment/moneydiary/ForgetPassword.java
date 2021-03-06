package com.madassignment.moneydiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout(width,height);
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.x = 0;
//        params.y = 0;
//        getWindow().setAttributes(params);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Button resetPwBtn = findViewById(R.id.resetPassBtn);
        resetPwBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v){
                        EditText resetEmail = findViewById(R.id.emailForReset);
                        String rsEmail = resetEmail.getText().toString();
                        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
                        final UserDao userDao = db.userDao();
                        User user = userDao.CheckUser(rsEmail);

                        if(rsEmail.equals("") || rsEmail == null) {
                            Toast.makeText(ForgetPassword.this, "Please insert an email", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            if (rsEmail.trim().matches(emailPattern)) {

                                if(user == null){
                                    Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    resetPwBtn.setEnabled(false);

                                    userDao.resetPassword(rsEmail);

                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "moneydiary_official@gmail.com" } );
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Money Diary App Reset Password");
                                    intent.putExtra(Intent.EXTRA_TEXT, "Your password had been reset to ABC123. Please reset your password after logging in. \n" +
                                            "\n Please send us an email if you have any problems.");

                                    try {
                                        startActivity(Intent.createChooser(intent, "Send mail..."));
                                    } catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(ForgetPassword.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }
}