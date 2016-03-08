package ru.danilov.gitgists;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import java.util.Locale;

import io.realm.RealmObject;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by Danilov Alexey on 06.03.2016.
 */

public class SpiceService extends RetrofitGsonSpiceService {
    GsonConverter converter;


    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(GistApi.class);
        converter = (GsonConverter) createConverter();
    }

    @Override
    protected String getServerUrl() {
        return "https://api.github.com/gists";
    }


    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setRequestInterceptor(createDefaultInterceptor());
        if (getConverter() != null)
            builder.setConverter(getConverter());
        builder.setEndpoint(getServerUrl()).setConverter(getConverter());

        return builder;
    }

    @Override
    protected Converter createConverter() {
      Gson gson =  new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return new GsonConverter(gson);
    }

    private RequestInterceptor createDefaultInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String lang = Locale.getDefault().getLanguage();
                request.addHeader("Accept-Language", lang);
            }
        };
    }
}