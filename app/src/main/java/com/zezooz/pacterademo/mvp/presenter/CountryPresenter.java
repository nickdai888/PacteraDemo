package com.zezooz.pacterademo.mvp.presenter;

import android.text.TextUtils;

import com.zezooz.pacterademo.mvp.model.ApiCountry;
import com.zezooz.pacterademo.mvp.model.Country;
import com.zezooz.pacterademo.mvp.model.Fact;
import com.zezooz.pacterademo.mvp.view.CountryView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by nick on 16/9/3.
 */

public class CountryPresenter extends MvpBasePresenter<CountryView> {

    private String title;
    private List<Fact> facts;

    public CountryPresenter() {
        facts = new ArrayList<Fact>();
    }

    public void loadData() {
       if(!isViewAttached()){
            return;
       }
        getView().showLoading(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCountry.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Observable<Country> observable = retrofit.create(ApiCountry.class).getCountry("746330");

        observable.map(new Func1<Country, Country>() {
                    @Override
                    public Country call(Country country) {
                        /**
                         * Use Object stream of RxJava to filter null list item
                         */
                        List<Fact> list = country.getRows();
                        if (list != null) {
                            Iterator<Fact> iterator = list.iterator();
                            while (iterator.hasNext()) {
                                Fact fact = iterator.next();
                                if (TextUtils.isEmpty(fact.getTitle())) {
                                    iterator.remove();
                                }
                            }
                        }
                        return country;
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Country>() {
                    @Override
                    public void onCompleted() {
                        // finish all work
                        getView().showLoading(false);
                        getView().showFacts(facts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // has some exceptions
                        getView().showLoading(false);
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Country country) {
                        // get the parse result
                        List<Fact> list = country.getRows();
                        facts.clear(); // 先清掉之前的
                        facts.addAll(list);
                        // update action bar title
                        getView().showTitle(country.getTitle());
                    }
                });
    }


}
