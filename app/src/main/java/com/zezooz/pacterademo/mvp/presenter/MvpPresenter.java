package com.zezooz.pacterademo.mvp.presenter;

import com.zezooz.pacterademo.mvp.view.MvpView;

/**
 * Created by nick on 16/9/3.
 */

public interface MvpPresenter <V extends MvpView> {
    /**
     * Set or attach the view to this presenter
     */
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    void detachView(boolean retainInstance);
}
