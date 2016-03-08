package ru.danilov.gitgists.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import ru.danilov.gitgists.R;
import ru.danilov.gitgists.activities.DetailActivity;
import ru.danilov.gitgists.api.model.Gist;

/**
 * Created by Danilov Alexey on 07.03.2016.
 */
public abstract class BaseGistsFragment extends SpiceFragment implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    private GistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeContainer.setOnRefreshListener(this);
        adapter = new GistAdapter(getActivity(), loadData(), true);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailActivity.start(getActivity(),adapter.getRealmResult().get(position).getId());
            }
        });
        loadData();

    }

    public abstract RealmResults<Gist> loadData();

    @Override
    public void onRefresh() {

    }

    public class GistAdapter extends RealmBaseAdapter<Gist> {


        public GistAdapter(Context context, RealmResults<Gist> realmResults, boolean automaticUpdate) {
            super(context, realmResults, automaticUpdate);
        }

        public RealmResults<Gist> getRealmResult() {
            return realmResults;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gist_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final Gist gist = realmResults.get(position);
            if (gist.getOwner() != null) {
                ImageLoader.getInstance().displayImage(gist.getOwner().getAvatar_url(), holder.avatar);
                holder.ownerName.setText(gist.getOwner().getLogin());
            } else {
                ImageLoader.getInstance().displayImage("", holder.avatar);
                holder.ownerName.setText("NoName");
            }

            if (gist.getDescription() != null) {
                holder.description.setText(gist.getDescription());
            } else holder.description.setText("");


            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.avatar)
            ImageView avatar;
            @Bind(R.id.owner_name)
            TextView ownerName;
            @Bind(R.id.owner_layout)
            LinearLayout ownerLayout;
            @Bind(R.id.description)
            TextView description;
            @Bind(R.id.container)
            LinearLayout container;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
