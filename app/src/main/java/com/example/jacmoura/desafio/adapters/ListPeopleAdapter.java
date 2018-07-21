package com.example.jacmoura.desafio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jacmoura.desafio.R;
import com.example.jacmoura.desafio.model.Person;
import java.util.List;

public class ListPeopleAdapter extends RecyclerView.Adapter<ListPeopleAdapter.ViewHolder> {

    List<Person> listItems;
    private Context context;

    public ListPeopleAdapter(List<Person> listItems, Context context){
        this.listItems = listItems;
        this.context = context;
    }

    public ListPeopleAdapter(List<Person> items) {

        this.listItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = null;

        itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return new ViewHolder(itemLayoutView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        final Person person = listItems.get(position);

        holder.name.setText(person.getName());
        holder.birthday.setText(person.getBirthday());
        holder.bio.setText(person.getBio());

        String url = listItems.get(position).getImage();
        Glide.with(context).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.teamplaceholder)
                .dontAnimate()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView image;
        public TextView name;
        public TextView birthday;
        public TextView bio;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            birthday = (TextView) itemView.findViewById(R.id.birthday);
            bio = (TextView) itemView.findViewById(R.id.bio);
        }
    }

}
