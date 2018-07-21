package com.example.jacmoura.desafio.model;

import com.example.jacmoura.desafio.dto.PeopleListResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by thiagosilva on 30/05/18.
 */

public class Person {

    private String id;

    private String name;

    private String image;

    private String birthday;

    private String bio;

    public List<Person> ConvertObjectModel(List<PeopleListResponse> response){

        List<Person> listModel = new ArrayList<>();
        HashMap<String, Person> map = new HashMap<String, Person>();
        String[] completeDate;
        String day, month, year;
        String formatDate;

        for (int i=0; i<response.size(); i++) {

            completeDate = response.get(i).getBirthday().toString().replace("T00:00:00Z","").split("-");
            day = completeDate[2];
            month = completeDate[1];
            year = completeDate[0];
            formatDate = day + "/" + month + "/" + year;

            Person person = new Person();
            person.id = response.get(i).getId();
            person.name = response.get(i).getName();
            person.image = response.get(i).getImage().replace(" ","");
            person.birthday = formatDate.toString();
            person.bio = response.get(i).getBio();

            // Faz o agrupamento das informaçoes
            String tempName = person.getName();
            Person correspondingPerson = map.get(tempName);
            if (correspondingPerson == null) {
                correspondingPerson = new Person();
                map.put(tempName, correspondingPerson);
                listModel.add(person);
            }

        }
        return listModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
