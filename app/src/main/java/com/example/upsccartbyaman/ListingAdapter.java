package com.example.upsccartbyaman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListingAdapter
        extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    private final Context context;

    private final List<ListingItem> listings;
    private final List<ListingItem> filteredListings;


    public ListingAdapter(
            Context context,
            List<ListingItem> listings
    ) {

        this.context = context;
        this.listings = listings;

        filteredListings =
                new ArrayList<>(listings);
    }


    private int dp(int value) {

        return (int) (
                value *
                        context.getResources()
                                .getDisplayMetrics()
                                .density
        );
    }


    // =========================================================
    // VIEW HOLDER
    // =========================================================

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price;
        TextView name;
        TextView condition;
        TextView area;


        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            image =
                    itemView.findViewWithTag("image");

            price =
                    itemView.findViewWithTag("price");

            name =
                    itemView.findViewWithTag("name");

            condition =
                    itemView.findViewWithTag("condition");

            area =
                    itemView.findViewWithTag("area");
        }
    }


    // =========================================================
    // CREATE
    // =========================================================

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        LinearLayout card =
                new LinearLayout(context);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(10)
        );


        GradientDrawable cardBg =
                new GradientDrawable();

        cardBg.setColor(Color.WHITE);

        cardBg.setCornerRadius(
                dp(16)
        );

        cardBg.setStroke(
                dp(1),
                Color.rgb(235, 235, 235)
        );

        card.setBackground(cardBg);


        // IMAGE

        ImageView image =
                new ImageView(context);

        image.setTag("image");

        image.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );


        GradientDrawable imageBg =
                new GradientDrawable();

        imageBg.setColor(
                Color.rgb(245, 245, 245)
        );

        imageBg.setCornerRadius(
                dp(12)
        );

        image.setBackground(imageBg);


        card.addView(
                image,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(130)
                )
        );


        // PRICE

        TextView price =
                new TextView(context);

        price.setTag("price");

        price.setTextSize(17);

        price.setTextColor(
                Color.rgb(242, 100, 31)
        );

        price.setTypeface(
                null,
                Typeface.BOLD
        );

        LinearLayout.LayoutParams priceParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        priceParams.setMargins(
                dp(5),
                dp(9),
                dp(5),
                0
        );

        card.addView(
                price,
                priceParams
        );


        // NAME

        TextView name =
                new TextView(context);

        name.setTag("name");

        name.setTextSize(14);

        name.setTextColor(
                Color.rgb(30, 30, 30)
        );

        name.setTypeface(
                null,
                Typeface.BOLD
        );

        name.setMaxLines(2);


        LinearLayout.LayoutParams nameParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        nameParams.setMargins(
                dp(5),
                dp(4),
                dp(5),
                0
        );

        card.addView(
                name,
                nameParams
        );


        // CONDITION

        TextView condition =
                new TextView(context);

        condition.setTag("condition");

        condition.setTextSize(12);

        condition.setTextColor(
                Color.rgb(100, 100, 100)
        );


        LinearLayout.LayoutParams conditionParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        conditionParams.setMargins(
                dp(5),
                dp(4),
                dp(5),
                0
        );

        card.addView(
                condition,
                conditionParams
        );


        // AREA

        TextView area =
                new TextView(context);

        area.setTag("area");

        area.setTextSize(12);

        area.setTextColor(
                Color.rgb(130, 130, 130)
        );


        LinearLayout.LayoutParams areaParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        areaParams.setMargins(
                dp(5),
                dp(3),
                dp(5),
                0
        );

        card.addView(
                area,
                areaParams
        );


        return new ViewHolder(card);
    }


    // =========================================================
    // BIND
    // =========================================================

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        ListingItem item =
                filteredListings.get(position);


        holder.price.setText(
                item.getPrice()
        );

        holder.name.setText(
                item.getName()
        );

        holder.condition.setText(
                item.getCondition()
        );

        holder.area.setText(
                "📍 " + item.getArea()
        );


        // =====================================================
        // IMAGE
        // =====================================================

        if (item.getImageUri() != null &&
                !item.getImageUri().isEmpty()) {

            try {

                holder.image.setImageURI(
                        Uri.parse(
                                item.getImageUri()
                        )
                );

            } catch (Exception e) {

                holder.image.setImageResource(
                        R.drawable.logo
                );
            }

        } else if (item.getImageRes() != 0) {

            holder.image.setImageResource(
                    item.getImageRes()
            );

        } else {

            holder.image.setImageResource(
                    R.drawable.logo
            );
        }


        // =====================================================
        // DETAILS
        // =====================================================

        holder.itemView.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    context,
                                    ItemDetailsActivity.class
                            );

                    intent.putExtra(
                            "name",
                            item.getName()
                    );

                    intent.putExtra(
                            "price",
                            item.getPrice()
                    );

                    intent.putExtra(
                            "condition",
                            item.getCondition()
                    );

                    intent.putExtra(
                            "category",
                            item.getCategory()
                    );

                    intent.putExtra(
                            "area",
                            item.getArea()
                    );

                    intent.putExtra(
                            "description",
                            item.getDescription()
                    );

                    intent.putExtra(
                            "phone",
                            item.getPhone()
                    );

                    intent.putExtra(
                            "image",
                            item.getImageRes()
                    );

                    intent.putExtra(
                            "imageUri",
                            item.getImageUri()
                    );

                    context.startActivity(intent);
                }
        );
    }


    @Override
    public int getItemCount() {

        return filteredListings.size();
    }


    // =========================================================
    // FILTER
    // =========================================================

    public void filter(
            String category,
            String area
    ) {

        filteredListings.clear();


        for (ListingItem item : listings) {

            boolean categoryMatches =
                    category == null
                            || category.equalsIgnoreCase("All")
                            || safe(item.getCategory())
                            .equalsIgnoreCase(category);


            boolean areaMatches =
                    area == null
                            || area.equalsIgnoreCase("All Areas")
                            || safe(item.getArea())
                            .equalsIgnoreCase(area);


            if (categoryMatches &&
                    areaMatches) {

                filteredListings.add(item);
            }
        }

        notifyDataSetChanged();
    }


    // =========================================================
    // SEARCH
    // =========================================================

    public void search(
            String searchText,
            String category,
            String area
    ) {

        filteredListings.clear();


        String query =
                searchText == null
                        ? ""
                        : searchText
                        .trim()
                        .toLowerCase(Locale.ROOT);


        for (ListingItem item : listings) {

            String name =
                    safe(item.getName())
                            .toLowerCase(Locale.ROOT);

            String description =
                    safe(item.getDescription())
                            .toLowerCase(Locale.ROOT);

            String itemCategory =
                    safe(item.getCategory())
                            .toLowerCase(Locale.ROOT);

            String itemArea =
                    safe(item.getArea())
                            .toLowerCase(Locale.ROOT);


            boolean searchMatches =
                    query.isEmpty()
                            || name.contains(query)
                            || description.contains(query)
                            || itemCategory.contains(query)
                            || itemArea.contains(query);


            boolean categoryMatches =
                    category == null
                            || category.equalsIgnoreCase("All")
                            || itemCategory.equals(
                            category.toLowerCase(Locale.ROOT)
                    );


            boolean areaMatches =
                    area == null
                            || area.equalsIgnoreCase("All Areas")
                            || itemArea.equals(
                            area.toLowerCase(Locale.ROOT)
                    );


            if (searchMatches &&
                    categoryMatches &&
                    areaMatches) {

                filteredListings.add(item);
            }
        }


        notifyDataSetChanged();
    }


    private String safe(
            String value
    ) {

        return value == null
                ? ""
                : value;
    }
}