package ru.danilov.gitgists.activities;

import android.content.Context;
import android.content.Intent;
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
import ru.danilov.gitgists.api.model.Gist;

/**
 * Created by Danilov Alexey on 08.03.2016.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String ID_TAG = "id_tag";
    public Gist gist;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.owner_name)
    TextView ownerName;
    @Bind(R.id.description)
    EditText description;
    @Bind(R.id.note)
    EditText note;
    @Bind(R.id.done)
    Button done;
    @Bind(R.id.to_original)
    Button toOriginal;
    public boolean isOriginal;

    public static void start(Context context, String gistId) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(ID_TAG, gistId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Realm realm = Realm.getInstance(DetailActivity.this);
        gist = realm.where(Gist.class).equalTo("id", getIntent().getStringExtra(ID_TAG)
        ).findFirst();
        setTitle(getString(R.string.detail_gist));
        isOriginal = gist.getLocaleDescription() == null || gist.getNote() == null;

        if (gist.getOwner() != null) {
            ImageLoader.getInstance().displayImage(gist.getOwner().getAvatar_url(), avatar);
            ownerName.setText(gist.getOwner().getLogin());
        } else {
            ownerName.setText(R.string.NoName);
        }
        if (gist.getLocaleDescription() == null) {
            description.setText(gist.getDescription());
        } else description.setText(gist.getLocaleDescription());
        if (gist.getNote() != null) {
            note.setText(gist.getNote());
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

        if (!description.getText().toString().equals(gist.getDescription())) {
            gist.setLocaleDescription(description.getText().toString());
        }
        if (!note.getText().toString().equals("")) {
            gist.setNote(note.getText().toString());
        } else gist.setNote(null);
        realm.commitTransaction();

        finish();
    }

    @OnClick(R.id.to_original)
    void setToOriginal() {
        description.setText(gist.getDescription());
        note.setText("");
        isOriginal = true;
    }
}
