package ru.danilov.gitgists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.danilov.gitgists.activitys.CategoryActivity;
import ru.danilov.gitgists.activitys.TabActivity;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.all_gist_button)
    Button allGistButton;
    @Bind(R.id.notes_button)
    Button notesButton;
    @Bind(R.id.all_in_button)
    Button allInButton;


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
