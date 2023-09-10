package com.example.list_view_tp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts List");
        ArrayList<ItemModel> arrayList=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(readJson());
            JSONArray array=object.getJSONArray("contacts");
            for(int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                String id=jsonObject.getString("id");
                String first_name=jsonObject.getString("first_name");
                String last_name=jsonObject.getString("last_name");
                String job=jsonObject.getString("job");
                String email=jsonObject.getString("email");
                String phone=jsonObject.getString("phone");

                ItemModel item=new ItemModel();
                item.setId(id);
                item.setName(first_name+" "+last_name);
                item.setJob(job);
                item.setEmail(email);
                item.setPhone(phone);
                arrayList.add(item);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapter adapter=new CustomAdapter(this,arrayList);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Hello my friend", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*recuperer les donnes du fichier data.json
    et les convertir en chaine de caractere*/
    public  String readJson()  {
        String json=null;
        try {
            InputStream inputStream=getAssets().open("data.json");
            int size=inputStream.available();
            byte[] buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json=new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}