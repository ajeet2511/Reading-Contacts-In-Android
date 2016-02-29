package in.digitalcurry.readcontactsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {


    @Bind(R.id.name_value) TextView nameValue_tv;
    @Bind(R.id.id_value) TextView idValue_tv;
    @Bind(R.id.lookup_value) TextView lookupValue_tv;

    @OnClick(R.id.back_btn)
    public void onBackBtnClicked(View view) {

        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {

            nameValue_tv.setText(intent.getStringExtra("name"));
            idValue_tv.setText(intent.getStringExtra("id"));
            lookupValue_tv.setText(intent.getStringExtra("lookup_key"));

        }

    }
}
