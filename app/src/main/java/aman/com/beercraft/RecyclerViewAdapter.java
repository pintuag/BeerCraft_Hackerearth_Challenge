package aman.com.beercraft;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

   private ArrayList<Beer> BeerList;
    private Context context;
    private OnClickListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<Beer> beerList, OnClickListener listener) {
        BeerList = beerList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Beer beer= BeerList.get(position);

        holder.name.setText(beer.getName());
        holder.style.setText(beer.getStyle());
        holder.alcohal_content.setText(beer.getAbv()+"");
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.OnClickAddToCartClick(beer);
            }
        });

    }

    @Override
    public int getItemCount() {
        return BeerList.size();
    }

    public void updateList(ArrayList<Beer> temp) {
        BeerList=temp;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, style, alcohal_content;
        ImageView addToCart;

        public ViewHolder(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.beer_name);
            style=itemView.findViewById(R.id.beer_style);
            alcohal_content=itemView.findViewById(R.id.beer_alcohal_content);
            addToCart=itemView.findViewById(R.id.img_addtocart);
        }
    }

    public interface OnClickListener {

        void  OnClickAddToCartClick(Beer beer);


    }
}
