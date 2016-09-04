package com.zezooz.pacterademo.mvp.view;

import com.zezooz.pacterademo.mvp.model.Fact;

import java.util.List;

/**
 * Created by nick on 16/9/3.
 */

public interface CountryView extends MvpView {
    /**
     * show loading status
     * @param loading  loading status should be visible or not
     */
    void showLoading(boolean loading);

    /**
     * if there is an error,show the related message about the error
     * @param errorMessage
     */
    void showError(String errorMessage);

    /**
     * show the facts of country
     * @param facts
     */
    void showFacts(List<Fact> facts);

    /**
     * show the title of the country
     * @param title
     */
    void showTitle(String title);

}
