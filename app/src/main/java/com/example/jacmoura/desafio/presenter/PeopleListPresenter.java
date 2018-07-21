package com.example.jacmoura.desafio.presenter;

import android.content.Context;
import com.example.jacmoura.desafio.datamaneger.PeopleListContract;
import com.example.jacmoura.desafio.dto.PeopleListResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagosilva on 30/05/18.
 */

public class PeopleListPresenter implements PeopleListContract.Presenter {

    List<PeopleListResponse> listResponse = new ArrayList<>();
    PeopleListContract.Interactor interactor = null;
    PeopleListContract.View view = null;

    public void setInteractor(PeopleListContract.Interactor interactor) {
        this.interactor = interactor;
    }

    public void setView(PeopleListContract.View view) {
        this.view = view;
    }

    public void getListPeople(Integer jsonResource, Context context) {
        listResponse = interactor.getServicePeopleList(jsonResource, context);
        if(listResponse != null){
            view.setPeopleList(listResponse);
        } else {
            view.onFailed("Não foi possível carregar os dados.");
        }
    }

}
