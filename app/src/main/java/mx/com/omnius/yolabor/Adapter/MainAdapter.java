package mx.com.omnius.yolabor.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mx.com.omnius.yolabor.Model.CardViewImg;
import mx.com.omnius.yolabor.Model.HistoModel;
import mx.com.omnius.yolabor.Model.JobModel;
import mx.com.omnius.yolabor.R;
import mx.com.omnius.yolabor.utils.HistoActivity;

/**
 * Created by omnius on 13/03/18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Context context;
    ArrayList<JobModel> data;
    List<HistoModel> histo_list;



    public MainAdapter(HistoActivity context, List<HistoModel> histo_list) {
        this.context = context;
        this.histo_list = histo_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_history,parent,false);
        return new  MainAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nameWorker.setText(histo_list.get(position).getFullName());
        holder.service.setText(histo_list.get(position).getServicio());

    }

    @Override
    public int getItemCount() {
        return histo_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameWorker;
        public TextView service;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_his);
            nameWorker = (TextView) itemView.findViewById(R.id.name_histo);
            service = (TextView)  itemView.findViewById(R.id.service_histo);
        }
    }
}
