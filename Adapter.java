package com.example.markattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

     List<ModelClass> ModelClassList;
     Context context;
     List<ModelClass> ModelClassList1;

    View view;

    public Adapter(List<ModelClass> modelClassList, Context context, List<ModelClass> modelClassList1) {
        ModelClassList = modelClassList;
        this.context = context;
        ModelClassList1 = modelClassList1;
    }

    public View getView() {
        return view;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int position) {
        if(ModelClassList!=null && ModelClassList.size()>0)
        {

            viewholder.cbselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        ModelClassList1.add(ModelClassList.get(position));
                        Toast.makeText(context, "Added "+ModelClassList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ModelClassList1.remove(ModelClassList.get(position));
                        Toast.makeText(context, "Removed "+ModelClassList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
                    }
            });




        }
        int sno =ModelClassList.get(position).getSno();
        String name =ModelClassList.get(position).getName();
        int year =ModelClassList.get(position).getYear();
        String department =ModelClassList.get(position).getDepartment();
        viewholder.setData(sno,name,year,department);

    }

    @Override
    public int getItemCount() {
        return ModelClassList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        private TextView sno;
        private TextView name;
        private TextView year;
        private TextView department;
        private CheckBox cbselect;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            sno = (TextView) itemView.findViewById(R.id.sno);
            name= (TextView) itemView.findViewById(R.id.name);
            department= (TextView) itemView.findViewById(R.id.dept);
            year= (TextView) itemView.findViewById(R.id.yr);
            cbselect=(CheckBox) itemView.findViewById(R.id.mark);
        }

        private void setData(int snum, String sname, int yr, String dept)
        {
            sno.setText(String.valueOf(snum));
            name.setText(sname);
            year.setText(String.valueOf(yr));
            department.setText(dept);
        }
    }
}
