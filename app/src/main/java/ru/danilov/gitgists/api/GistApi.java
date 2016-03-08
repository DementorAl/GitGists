package ru.danilov.gitgists.api;

import retrofit.http.GET;
import ru.danilov.gitgists.api.model.Gist;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */
public interface GistApi  {

    @GET("/public")
    Gist.SuperList getAllGists();
}
