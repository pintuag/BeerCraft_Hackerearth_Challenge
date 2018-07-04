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

public class ShoppingCartRecycleViewAdapter extends RecyclerView.Adapter<ShoppingCartRecycleViewAdapter.ViewHolder>{

   private ArrayList<Beer> cartBeerList;
    private Context context;
    private OnClickListener listener;

    public ShoppingCartRecycleViewAdapter(Context context, ArrayList<Beer> cartBeerList, OnClickListener listener) {
        this.cartBeerList = cartBeerList;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_row_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Beer beer= cartBeerList.get(position);

        holder.name.setText(beer.getName());
        holder.style.setText(beer.getStyle());
        holder.alcohal_content.setText(beer.getAbv()+"");

//        for(Beer bbeer: cartBeerList.get(position))
//        holder.itemcount.setText();

    }

    @Override
    public int getItemCount() {
        return cartBeerList.size();
    }

    public void updateList(ArrayList<Beer> temp) {
        cartBeerList =temp;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, style, alcohal_content, itemcount;


        public ViewHolder(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.beer_name);
            style=itemView.findViewById(R.id.beer_style);
            alcohal_content=itemView.findViewById(R.id.beer_alcohal_content);
            itemcount=itemView.findViewById(R.id.item_count);
        }
    }

    public interface OnClickListener {

        void  OnClickAddToCartClick(Beer beer);


    }
}
