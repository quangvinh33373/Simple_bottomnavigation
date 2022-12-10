package com.example.simplebottomnavigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder> {

     List<UserObj> mData;
     LayoutInflater mInflater;
    Context context;
    Data dt;
    Spinner spn;
    public MyRecyclerViewAdapter2(Context context, List<UserObj> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dt=new Data(parent.getContext());
        context=parent.getContext();
        View view = mInflater.inflate(R.layout.rowlayout2, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserObj obj = mData.get(position);
        holder.name.setText(obj.getName());
        holder.date.setText(obj.getBirth());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_edit(obj.getId());
                notifyDataSetChanged();
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDelete(obj.getId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date;
        ImageView edit,del;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            edit=itemView.findViewById(R.id.edit_img);
del=itemView.findViewById(R.id.del_img);
        }

    }
    private void dialogDelete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setMessage("xóa ?"

        );
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

             dt.delete(id,"user");
                mData.clear();
         mData.addAll(dt.getUser());
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("không ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(context, "Đã hủy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void Dialog_edit(int id){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_user);
        MaterialButton add;
        DatePicker date;
        spn=dialog.findViewById(R.id.group_spinner);
        add=dialog.findViewById(R.id.add_bt);
        date=dialog.findViewById(R.id.date_value);
        spn=dialog.findViewById(R.id.group_spinner);
        ArrayAdapter adapter=new ArrayAdapter(context, android.R.layout.simple_spinner_item,dt.getGroup());
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
                dt.edit_user(a,formatedDate,id);
                Toast.makeText(context,"user edited",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


}
