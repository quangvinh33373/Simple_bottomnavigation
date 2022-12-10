package com.example.simplebottomnavigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

     List<GroupObj> mData;
     LayoutInflater mInflater;
    Context context;
    Data dt;
    public MyRecyclerViewAdapter(Context context, List<GroupObj> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dt=new Data(parent.getContext());
        context=parent.getContext();
        View view = mInflater.inflate(R.layout.rowlayout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GroupObj obj = mData.get(position);
        holder.name.setText(obj.getName());
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
        TextView name;
        ImageView del,edit;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
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

             dt.delete(id,"usergroup");
                mData.clear();
         mData.addAll(dt.getGroup());
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
        dialog.setContentView(R.layout.dialog_add_group);
        TextInputLayout input=dialog.findViewById(R.id.group_input);
        MaterialButton add;
        add=dialog.findViewById(R.id.add_bt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=input.getEditText().getText().toString().trim();
                if(a.isEmpty()){
                    Toast.makeText(context, "input must not empty", Toast.LENGTH_SHORT).show();
                }else {

                    dt.edit_group(a,id);
                    Toast.makeText(context, "new group edited", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
