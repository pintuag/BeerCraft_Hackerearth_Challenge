package aman.com.beercraft;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Beer> beerList;
    static  ArrayList<Beer> shoppinCartList;
    TextView tv_cart;
    Toolbar toolbar;
    LinearLayout img_shoppingCart;
    ImageView filter;
    String stylefilter="";


    final String URL="http://starlord.hackerearth.com/beercraft";

    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(EditText)findViewById(R.id.search_bar);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        tv_cart=(TextView)toolbar.findViewById(R.id.tv_cart);
        img_shoppingCart=(LinearLayout)toolbar.findViewById(R.id.cart);
        filter=(ImageView)findViewById(R.id.filter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerList = new ArrayList<>();
        shoppinCartList= new ArrayList<>();
        adapter= new RecyclerViewAdapter(this, beerList, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void OnClickAddToCartClick(Beer beer) {
                shoppinCartList.add(beer);
                tv_cart.setText(shoppinCartList.size()+"");
                Toast.makeText(MainActivity.this, "Added to cart", Toast.LENGTH_LONG).show();
            }
        });

        img_shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShoppinCart.class);
                startActivity(intent);
                
            }
        });
        recyclerView.setAdapter(adapter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                editText.setLayoutParams(lp);
                editText.setText(stylefilter);

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setView(editText)
                        .setTitle("Filter")
                        .setMessage("Enter Style")
                        .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               stylefilter=editText.getText().toString();

                                ArrayList<Beer> temp = new ArrayList();

                                for(Beer post: beerList) {

                                    if(post.getStyle().toLowerCase().contains(stylefilter.toLowerCase())){

                                        temp.add(post);


                                    }
                                    //update recyclerview
                                    adapter.updateList(temp);

                                }

                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();



            }
        });

        loadData();

        initSearch();

    }

    private void initSearch() {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {

                String text=s.toString();

                // Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

                ArrayList<Beer> temp = new ArrayList();

                for(Beer post: beerList) {

                    if(post.getName().toLowerCase().contains(text.toLowerCase())){

                        temp.add(post);


                    }
                    //update recyclerview
                    adapter.updateList(temp);

                }

            }
        });
    }

    private void loadData() {

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loadind Data");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        StringRequest stringRequest= new StringRequest(Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray =  new JSONArray(response);


                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject= (JSONObject) jsonArray.get(i);

                                Gson gson = new Gson();

                                Beer beer = gson.fromJson(jsonObject.toString(), Beer.class);

                                Log.d("Json parsing", beer.toString());

                                beerList.add(beer);
                            }

                            adapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Loading data failed", Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }





}
