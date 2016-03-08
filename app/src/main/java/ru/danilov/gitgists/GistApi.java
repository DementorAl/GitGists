package ru.danilov.gitgists;

import retrofit.http.GET;
import ru.danilov.gitgists.model.Gist;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */
public interface GistApi  {

    @GET("/public")
    Gist.SuperList getAllGists();
}
