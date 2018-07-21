package com.example.jacmoura.desafio.datamaneger;

import android.content.Context;

import com.example.jacmoura.desafio.dto.PeopleListResponse;

import java.util.List;

/**
 * Created by thiagosilva on 30/05/18.
 */

public interface PeopleListContract {

    interface View {

        void getPeopleList();
        void onFailed(String message);
        void setPeopleList(List<PeopleListResponse> list);
    }

    interface Presenter {
        void getListPeople(Integer jsonResource, Context context);
        void setInteractor(PeopleListContract.Interactor interactor);
        void setView(PeopleListContract.View view);
    }

    interface Interactor {
        List<PeopleListResponse> getServicePeopleList(Integer jsonResource, Context context);
    }
}
