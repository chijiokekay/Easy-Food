package uk.ac.tees.b1325384.easyfood.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.b1325384.easyfood.Interface.ItemClickListener;
import uk.ac.tees.b1325384.easyfood.R;

public class FoodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public TextView txtDiscount;
    public TextView txtPrice;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public FoodListViewHolder(View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        txtDiscount = (TextView)itemView.findViewById(R.id.food_discount);
        txtPrice = (TextView)itemView.findViewById(R.id.food_price);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}

