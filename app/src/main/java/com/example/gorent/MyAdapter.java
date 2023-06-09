package com.example.gorent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    private RecyclerViewInterface recyclerViewInterface;

    private Context context;
    private ArrayList model  , rent , photo , id,type;

    ArrayList <VehicleModel> vehicle;

    public MyAdapter(Context context, ArrayList model, ArrayList rent,ArrayList id, ArrayList photo , RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.model = model;
        this.rent=rent;
        this.id=id;
        this.photo=photo;
        this.recyclerViewInterface=recyclerViewInterface;


    }

    public MyAdapter(Context context, ArrayList model, ArrayList rent,ArrayList id,ArrayList type ,  ArrayList photo , RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.model = model;
        this.rent=rent;
        this.id=id;
        this.type=type;
        this.photo=photo;
        this.recyclerViewInterface=recyclerViewInterface;


    }

    public MyAdapter(Context context, ArrayList vehicle) {
        this.context = context;
        this.vehicle = vehicle;

    }



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.display,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.model.setText(String.valueOf(model.get(position)));
        holder.rent.setText(String.valueOf(rent.get(position)));
        byte [] convert = (byte[]) photo.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(convert,0,convert.length);
        holder.photo.setImageBitmap(bitmap);







    }

    @Override
    public int getItemCount() {

        return model.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView model , rent;
        ImageView photo;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.textModel);
            rent = itemView.findViewById(R.id.textPrice);
            photo = itemView.findViewById(R.id.Vimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onVehicleClicked(pos);
                        }
                    }
                }
            });


        }
    }
}
