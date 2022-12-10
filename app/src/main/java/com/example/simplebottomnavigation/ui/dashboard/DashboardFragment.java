package com.example.simplebottomnavigation.ui.dashboard;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.example.simplebottomnavigation.R;
import com.example.simplebottomnavigation.databinding.FragmentDashboardBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
RecyclerView rv;
FloatingActionButton fab;
Data dt;
List<GroupObj> list;
MyRecyclerViewAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
   View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dt=new Data(getContext());
        rv=view.findViewById(R.id.group_rv);
        list=new ArrayList<>();
        list.addAll(dt.getGroup());
adapter=new MyRecyclerViewAdapter(getContext(),list);
rv.setLayoutManager(new LinearLayoutManager(getContext()));
rv.setAdapter(adapter);
        fab=view.findViewById(R.id.fab);
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
        dialog.setContentView(R.layout.dialog_add_group);
        TextInputLayout input=dialog.findViewById(R.id.group_input);
        MaterialButton add;
        add=dialog.findViewById(R.id.add_bt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=input.getEditText().getText().toString().trim();
if(a.isEmpty()){
    Toast.makeText(getContext(), "input must not empty", Toast.LENGTH_SHORT).show();
}else {

    dt.create_usergroup(a);
    Toast.makeText(getContext(), "new group created", Toast.LENGTH_SHORT).show();
    dialog.dismiss();
}

            }
        });
        dialog.show();
        Window window = dialog.getWindow();
     window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}