package com.example.simplebottomnavigation.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebottomnavigation.Data;
import com.example.simplebottomnavigation.GroupObj;
import com.example.simplebottomnavigation.MyRecyclerViewAdapter;
import com.example.simplebottomnavigation.MyRecyclerViewAdapter2;
import com.example.simplebottomnavigation.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {
Spinner spn;
Data dt;
FloatingActionButton fab;
List<GroupObj> list;
MyRecyclerViewAdapter2 adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dt=new Data(getContext());
        fab=view.findViewById(R.id.fab);
        adapter=new MyRecyclerViewAdapter2(getContext(),dt.getUser());
        RecyclerView rv=view.findViewById(R.id.user_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Dialog_add();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    private void Dialog_add(){

        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_user);
        MaterialButton add;
        DatePicker date;
        spn=dialog.findViewById(R.id.group_spinner);
        add=dialog.findViewById(R.id.add_bt);
        date=dialog.findViewById(R.id.date_value);spn=dialog.findViewById(R.id.group_spinner);
        list=new ArrayList<>();
        list.addAll(dt.getGroup());
        ArrayAdapter adapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,list);
        spn.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = date.getYear();
                int day = date.getDayOfMonth();
                int month = date.getMonth();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatedDate = sdf.format(calendar.getTime());
                String a=spn.getSelectedItem().toString();
         dt.create_user(spn.getSelectedItem().toString(),formatedDate);
         Toast.makeText(getContext(),"user created",Toast.LENGTH_SHORT).show();
         dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}