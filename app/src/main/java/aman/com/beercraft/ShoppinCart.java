package aman.com.beercraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static aman.com.beercraft.MainActivity.shoppinCartList;

public class ShoppinCart extends AppCompatActivity {

    ShoppingCartRecycleViewAdapter adapter;
    RecyclerView recyclerView;

    ImageView back;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppin_cart);



        toolbar=(Toolbar)findViewById(R.id.cart_toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.shoppincart_recyclerview);
        back=(ImageView) toolbar.findViewById(R.id.back);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter= new ShoppingCartRecycleViewAdapter(this, MainActivity.shoppinCartList, new ShoppingCartRecycleViewAdapter.OnClickListener() {
            @Override
            public void OnClickAddToCartClick(Beer beer) {
                }
        });
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
