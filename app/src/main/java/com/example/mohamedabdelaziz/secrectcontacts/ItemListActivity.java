package com.example.mohamedabdelaziz.secrectcontacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    ArrayList<contact> contacts ;
    RecyclerView recyclerView;
    database db;
    AutoCompleteTextView autoCompleteTextView ;
    ArrayList<String> names ;
    static int access =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.textsearch) ;
        contacts= new ArrayList<>() ;
         db=new database(getApplicationContext());
        SQLiteDatabase sqLiteDatabase =db.getReadableDatabase();
        names=new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),add_number.class));
            }
        });
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,names));
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = getindex(""+parent.getItemAtPosition(position));
                Intent intent =new Intent(getApplicationContext(),long_press.class);
                autoCompleteTextView.setText("");
                intent.putExtra("name",contacts.get(x).getName()) ;
                intent.putExtra("phone",contacts.get(x).getNumber()) ;
                startActivity(intent);
                overridePendingTransition(R.transition.push_down,R.transition.push_up);

            }
        });
         recyclerView = (RecyclerView) findViewById(R.id.item_list);

    }
    private int getindex(String t) {
        for (int i = 0; i < names.size(); i++) {
            if(t.equalsIgnoreCase(names.get(i))) {
                return i;
            }
        }
        return -1 ;
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences =getSharedPreferences("data",MODE_PRIVATE) ;
        if(access==0) {
            if (sharedPreferences.getString("name", "null").equals("null")) {
                startActivity(new Intent(getApplicationContext(), Splash.class));
            } else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        }
        else if(access==-1)
            finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        names.clear();
        contacts=db.restore_data() ;
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    access=0;
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            names.add(contacts.get(position).getName()) ;
            holder.name.setText(contacts.get(position).getName());
            holder.number.setText(contacts.get(position).getNumber());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),long_press.class);
                    intent.putExtra("phone",contacts.get(position).getNumber());
                    intent.putExtra("name",contacts.get(position).getName());
                    startActivity(intent);
                    overridePendingTransition(R.transition.push_down,R.transition.push_up);
                }
            });
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView name, number;
            public ViewHolder(View view) {
                super(view);
                mView = view;
                name = (TextView) view.findViewById(R.id.name);
                number = (TextView) view.findViewById(R.id.number);
            }
        }
    }
}
