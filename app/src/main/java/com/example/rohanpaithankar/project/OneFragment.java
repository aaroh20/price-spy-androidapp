package com.example.rohanpaithankar.project;

/**
 * Created by Aaroh on 05-Apr-17.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//import info.androidhive.materialtabs.R;


public class OneFragment extends Fragment{

    ProgressBar progressBar;
    //TextView responseView;
    Button queryButton,buy;
    EditText product;
    WebView web;
    String response,query,id;
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    String jsonStr;

    private ArrayList<HashMap<String, String>> contactList;


 
            public OneFragment() {
        // Required empty public constructor
    }

 
            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
 
            @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_one, container, false);
                progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
                //responseView=(TextView)view.findViewById(R.id.responseView);
                product=(EditText)view.findViewById(R.id.product);
                queryButton = (Button)view.findViewById(R.id.queryButton);
                buy=(Button)view.findViewById(R.id.buy);
                web=(WebView)view.findViewById(R.id.webview);

                contactList = new ArrayList<>();

                lv = (ListView)view.findViewById(R.id.list);


                queryButton.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        query=product.getText().toString();

                        new RetrieveFeedTask().execute();

                    }

                });


                return view;



    }
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            //responseView.setText("");

        }

        protected String doInBackground(Void... urls) {
            //String email = e.getText().toString();
            HttpHandler sh = new HttpHandler();

            String pro=query.replace(' ','+');
            Log.d("pro", pro);
            // Do some validation here

            try {

                // Connect to the web site
                //StringBuffer url1=new StringBuffer("http://www.pricetree.com/search.aspx?q=");
                StringBuffer url1=new StringBuffer("http://www.pricetree.com/search.aspx?q=");
                url1.append(pro);
                Log.d("url", url1.toString());
                Document document = Jsoup.connect(url1.toString()).get();
                // Get the html document title
                Elements ele = document.getElementsByClass("items-wrap");
                Element link=ele.select("a").first();
                String absHref = link.attr("abs:href");
                StringBuffer buffer = new StringBuffer(absHref);

                try {
                    int k=buffer.lastIndexOf("-");
                    id=buffer.substring(k+1);


                    Log.e("check",id);

                }
                catch (Exception e){

                }

                //URL url = new URL(API_URL + "email=" + email + "&apiKey=" + API_KEY);
                URL url = new URL("http://www.pricetree.com/dev/api.ashx?pricetreeId="+id+"&apikey=7770AD31-382F-4D32-8C36-3743C0271699");
                //URL url = new URL("https://www.flipkart.com/casedeal-back-cover-apple-iphone-6s/p/itmertf5rzbyhmqn?pid=ACCERTF5YRRGBCTM");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    jsonStr=stringBuilder.toString();
                    Log.e(TAG, "Response from url: " + jsonStr);


                    if (jsonStr != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(jsonStr);

                            // Getting JSON Array node

                            JSONArray contacts;

                            contacts= jsonObj.getJSONArray("data");



                            // looping through All Contacts
                            for (int i = 0; i < contacts.length(); i++) {
                                JSONObject c = contacts.getJSONObject(i);

                                String id = c.getString("PriceTree_Id");
                                String sname = c.getString("Seller_Name");
                                String bp = c.getString("Best_Price");
                                String stock = c.getString("In_Stock");
                                String pname = c.getString("Product_Name");
                                String uri=c.getString("Uri");

                                // Phone node is JSON Object
                                //JSONObject phone = c.getJSONObject("phone");
                                //String mobile = phone.getString("mobile");
                                //String home = phone.getString("home");
                                //String office = phone.getString("office");

                                // tmp hash map for single contact
                                HashMap<String, String> contact = new HashMap<>();

                                // adding each child node to HashMap key => value
                                contact.put("pname", pname);
                                contact.put("seller name", sname);
                                contact.put("price", bp);
                                contact.put("stock", stock);
                                contact.put("URL", uri);

                                // adding contact to contact list
                                contactList.add(contact);
                            }
                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                            /*runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(OneFragment.this,
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            });*/

                        }
                    } else {
                        Log.e(TAG, "Couldn't get json from server.");
                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(,
                                        "Couldn't get json from server. Check LogCat for possible errors!",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });*/

                    }




                    return null;
                }
                finally{
                    urlConnection.disconnect();
                }


            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response1) {
            if(response1 == null) {
                response1 = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response1);
            //responseView.setText(response1);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), contactList,
                    R.layout.activity_list_item, new String[]{"pname", "seller name",
                    "price","stock"}, new int[]{R.id.productname,
                    R.id.bestprice,R.id.seller,R.id.stock});






            lv.setAdapter(adapter);

        }
    }
 
}