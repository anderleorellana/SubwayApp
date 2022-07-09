package com.subway.subwayapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.subway.subwayapp.R;
import com.subway.subwayapp.entity.Producto;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Producto> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ProductAdapter(List<Producto> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_items,null);

        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Producto> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre, precio;

        ViewHolder(View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imgProducto);
            nombre = itemView.findViewById(R.id.textProducto);
            precio = itemView.findViewById(R.id.textPrecio);
        }

        void bindData(final Producto item){
            Picasso.get().load(item.getImagen()).into(imagen);
            nombre.setText(item.getNombre());
            precio.setText(String.valueOf(item.getPrecio()));
        }
    }
}
