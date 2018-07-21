package com.example.jacmoura.desafio.dto;

import android.content.Context;
import com.example.jacmoura.desafio.datamaneger.PeopleListContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PeopleListInteractor implements PeopleListContract.Interactor {

    public List<PeopleListResponse> getServicePeopleList(Integer jsonResource, Context context) {

        try {
            InputStream is = context.getResources().openRawResource(jsonResource);
            Writer writer = new StringWriter();
            ArrayList<PeopleListResponse> listPeople =  new ArrayList<>();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                String json = writer.toString();
                ObjectMapper mapper = new ObjectMapper();
                listPeople = mapper.readValue(json, new TypeReference<List<PeopleListResponse>>(){});

            } finally {
                is.close();
            }
            return listPeople;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
