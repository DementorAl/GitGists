package ru.danilov.gitgists.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import ru.danilov.gitgists.R;
import ru.danilov.gitgists.model.File;
import ru.danilov.gitgists.model.Gist;

/**
 * Created by Danilov Alexey on 08.03.2016.
 */
public class DetailActivity extends AppCompatActivity {

    public Gist gist;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.owner_name)
    TextView ownerName;
    @Bind(R.id.discription)
    EditText discription;
    @Bind(R.id.note)
    EditText note;
    @Bind(R.id.done)
    Button done;
    @Bind(R.id.to_original)
    Button toOriginal;
    @Bind(R.id.language)
    TextView language;
    public boolean isOriginal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        String gistId = (String) bundle.getSerializable("id");
        Realm realm = Realm.getInstance(DetailActivity.this);
        gist = realm.where(Gist.class).equalTo("id", gistId).findFirst();
        setTitle("Detail Gist");
        if (gist.getLocaleDiscription() == null || gist.getNote() == null) {
            isOriginal = true;

        } else {
            isOriginal = false;

        }

        if (gist.getOwner() != null) {
            ImageLoader.getInstance().displayImage(gist.getOwner().getAvatar_url(), avatar);
            ownerName.setText(gist.getOwner().getLogin());
        } else {
            ownerName.setText("NoName");

        }
        if (gist.getLocaleDiscription() == null) {
            discription.setText(gist.getDescription());
        } else discription.setText(gist.getLocaleDiscription());
        if (gist.getNote() != null) {
            note.setText(gist.getNote());
        }
        if (gist.getFiles() != null) {
            String lan = "";
            for (File file : gist.getFiles().values()) {
                if (file.getLanguage() != null)
                    if (!file.getLanguage().equals("null"))
                        lan += file.getLanguage() + " ";
            }
            language.setText(lan);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.done)
    public void onClick() {
        Realm realm = Realm.getInstance(DetailActivity.this);
        realm.beginTransaction();

        if (!discription.getText().toString().equals(gist.getDescription())) {
            gist.setLocaleDiscription(discription.getText().toString());
        }
        if (!note.getText().toString().equals("")) {
            gist.setNote(note.getText().toString());
        } else gist.setNote(null);
        realm.commitTransaction();

        finish();
    }

    @OnClick(R.id.to_original)
    void setToOriginal() {
        discription.setText(gist.getDescription());
        note.setText("");
        isOriginal = true;
    }
}
