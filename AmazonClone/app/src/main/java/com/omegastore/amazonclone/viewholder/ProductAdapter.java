package com.omegastore.amazonclone.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.omegastore.amazonclone.R;
import com.omegastore.amazonclone.constant.Constant;
import com.omegastore.amazonclone.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView productName , productPrice;

        ImageView productImage;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_product_adapter, parent, false);
            productName = (TextView) view.findViewById(R.id.ProductName);
            productPrice = (TextView) view.findViewById(R.id.ProductPrice);
            productImage = (ImageView) view.findViewById(R.id.ProductImage);
            view.setTag(new ViewHolder(productName, productPrice, productImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            productName = viewHolder.tvProductName;
            productPrice = viewHolder.tvProductPrice;
            productImage = viewHolder.ivProductImage;
        }

        final Product product = getItem(position);
        productName.setText(product.getpName());
        productPrice.setText(Constant.CURRENCY+String.valueOf(product.getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
        Log.d(TAG, "Context package name: " + context.getPackageName());
        productImage.setImageResource(context.getResources().getIdentifier(
                product.getpImageName(), "drawable", context.getPackageName()));
        return view;
    }


    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvProductPrice;
        public final ImageView ivProductImage;

        public ViewHolder(TextView tvProductName, TextView tvProductPrice, ImageView ivProductImage) {
            this.tvProductName = tvProductName;
            this.tvProductPrice = tvProductPrice;
            this.ivProductImage = ivProductImage;
        }
    }
}