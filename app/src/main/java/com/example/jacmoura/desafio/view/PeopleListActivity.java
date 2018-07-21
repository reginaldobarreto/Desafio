package com.example.jacmoura.desafio.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.jacmoura.desafio.R;
import com.example.jacmoura.desafio.adapters.ListPeopleAdapter;
import com.example.jacmoura.desafio.datamaneger.PeopleListContract;
import com.example.jacmoura.desafio.dto.PeopleListInteractor;
import com.example.jacmoura.desafio.dto.PeopleListResponse;
import com.example.jacmoura.desafio.model.Person;
import com.example.jacmoura.desafio.presenter.PeopleListPresenter;

import java.util.ArrayList;
import java.util.List;

public class PeopleListActivity extends AppCompatActivity implements PeopleListContract.View{

    PeopleListContract.Presenter presenter;
    private RecyclerView recyclerViwe;
    private ListPeopleAdapter adapter;
    private TextView message;
    private List<PeopleListResponse> listPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        presenter = new PeopleListPresenter();
        presenter.setInteractor(new PeopleListInteractor());
        presenter.setView(this);
        getPeopleList();

        Person person =  new Person();
        person.ConvertObjectModel(this.listPeople);

        recyclerViwe = (RecyclerView) findViewById(R.id.peoplelist);
        recyclerViwe.setHasFixedSize(true);
        recyclerViwe.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL,false));

        adapter = new ListPeopleAdapter(person.ConvertObjectModel(this.listPeople), getBaseContext());
        recyclerViwe.setAdapter(new ListPeopleAdapter(person.ConvertObjectModel(this.listPeople),getBaseContext()));

    }

    @Override
    public void getPeopleList() {
        presenter.getListPeople(R.raw.people_list,getBaseContext());
    }

    @Override
    public void onFailed(String messageEmptyState) {
        message = (TextView) findViewById(R.id.message);
        message.setText(messageEmptyState);
        message.setEnabled(true);
    }

    @Override
    public void setPeopleList(List<PeopleListResponse> list) {
        this.listPeople = list;
    }


}
