package com.example.sih;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.text.method.LinkMovementMethod;
        import android.view.View;
        import android.widget.TextView;

public class support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

        TextView userReport = (TextView) findViewById(R.id.textViewemail);
        userReport.setText(Html.fromHtml("<a href=\"aviralcool2110@gmail.com\">Email:aviralcool2110@gmail.com</a>"));
        userReport.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
