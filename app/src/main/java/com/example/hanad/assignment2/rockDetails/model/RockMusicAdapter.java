package com.example.hanad.assignment2.rockDetails.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.List;

import com.example.hanad.assignment2.ItemClickListener;
import com.example.hanad.assignment2.MainActivity;
import com.example.hanad.assignment2.R;
import com.squareup.picasso.Picasso;

import io.reactivex.functions.Consumer;


/**
 * Created by hanad on 09/02/2018.
 */

public class RockMusicAdapter extends RecyclerView.Adapter<RockMusicAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Result> results;
    private RockMusicFragment rockMusicFragment;
    private RockMusicList rockMusicList;


    public RockMusicAdapter(Context applicationContext, List<Result> results, RockMusicFragment rockMusicFragment, RockMusicList rockMusicList, int row) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.results = results;
        this.rockMusicFragment = rockMusicFragment;
        this.rockMusicList = rockMusicList;
        // RockMusicFragment.this.getActivity().getApplicationContext(),rockMusicLists.getResults(),RockMusicFragment.this,R.layout.row));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.artistName.setText(results.get(position).getArtistName());
        holder.trackprice.setText(results.get(position).getTrackPrice().toString());
        holder.collection.setText(results.get(position).getCollectionName());
        Picasso.with(applicationContext)
                .load(results.get(position).getArtworkUrl60())
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageView);
        // Context context = holder.mPhoto.getContext();
        // Picasso.with(context)
        //   .load("http://api.themoviedb.org/3/movie/top_rated/" + results.get(position).getPosterPath()) + results.get(position).getPosterPath())
        //   .resize(50, 50)
        //   .centerCrop()
        //   .into(holder.mPhoto);

        holder.callItemClick(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                // Toast.makeText(context, results.get(position).getId(), Toast.LENGTH_SHORT).show();
                //  Toast.makeText(context, "Potato", Toast.LENGTH_SHORT).show();
                // This is where you could out the music playing
                //MainActivity.showDetailsMovie(results.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView artistName;
        private ImageView imageView;
        private TextView trackprice;
        private TextView collection;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artistName);
            imageView = itemView.findViewById(R.id.artworkUrl60);
            trackprice = itemView.findViewById(R.id.trackPrice);
            collection = itemView.findViewById(R.id.collectionName);

        }

        public void callItemClick(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        //@Override
        public void onClick(View view) {
            this.itemClickListener.OnClick(view, getPosition());
        }
    }
}