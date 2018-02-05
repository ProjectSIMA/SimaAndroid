package it.sima.android;

/**
 * Created by theelix on 05/02/18.
 */

interface TaskCallback<T> {
    void onTaskCompleted(T result);
}
