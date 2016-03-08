package ru.danilov.gitgists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.danilov.gitgists.activities.CategoryActivity;
import ru.danilov.gitgists.activities.TabActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.all_gist_button, R.id.notes_button, R.id.all_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_gist_button:
                CategoryActivity.start(this, true);
                break;
            case R.id.notes_button:
                CategoryActivity.start(this, false);
                break;
            case R.id.all_in_button:
                TabActivity.start(this);
                break;
        }
    }
}
