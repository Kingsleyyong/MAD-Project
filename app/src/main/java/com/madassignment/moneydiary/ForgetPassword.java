package com.madassignment.moneydiary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        TextView textView = findViewById(R.id.sendCodeStatement);
        String text = "Did not received? Click here to send a code again after 60 seconds.";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //Please do something here to give performance for 'here' text.
                Toast.makeText(ForgetPassword.this, "Here Clicked", Toast.LENGTH_SHORT).show();

            }

            //This is the style settings
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setFakeBoldText(true);
            }
        };
        ss.setSpan(clickableSpan1, 24, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }


//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setData(Uri.parse("mailto:"));
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, String.valueOf(resetPWMail));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Money Diary Reset Password");
//        intent.putExtra(Intent.EXTRA_TEXT, "Your password had been reset to ABC123. Please reset your password after logging in.");
//
//        try {
//            startActivity(Intent.createChooser(intent, "Send mail..."));
//            finish();
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(ForgetPassword.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }

}