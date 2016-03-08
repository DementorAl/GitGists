package ru.danilov.gitgists.api.requests;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.retry.RetryPolicy;

import retrofit.RetrofitError;
import ru.danilov.gitgists.api.GistApi;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */
public abstract class BaseApiRequest<Result> extends RetrofitSpiceRequest<Result, GistApi> {

    public BaseApiRequest(Class<Result> clazz) {
        super(clazz, GistApi.class);
        setRetryPolicy(new RetryPolicy() {
            int retryCount = 3;

            @Override
            public int getRetryCount() {
                retryCount--;
                return retryCount;
            }

            @Override
            public void retry(SpiceException e) {
                if (!((RetrofitError) e.getCause()).isNetworkError()) {
                    retryCount = 0;
                }

            }

            @Override
            public long getDelayBeforeRetry() {
                return 0;
            }
        });
    }
}
