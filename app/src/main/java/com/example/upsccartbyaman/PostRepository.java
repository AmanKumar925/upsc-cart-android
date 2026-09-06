package com.example.upsccartbyaman;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class PostRepository {

    private static final String PREFS = "upsc_cart_posts";
    private static final String ITEMS = "items";
    private static final String ROOMS = "rooms";

    private PostRepository() {
    }

    // =========================================================
    // ITEM
    // =========================================================

    public static void addItem(
            Context context,
            String name,
            String price,
            String condition,
            String category,
            String area,
            String description,
            String phone,
            String imageUri
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE
                );

        JSONArray array =
                getArray(prefs, ITEMS);

        try {

            JSONObject object = new JSONObject();

            object.put("name", name);
            object.put("price", price);
            object.put("condition", condition);
            object.put("category", category);
            object.put("area", area);
            object.put("description", description);
            object.put("phone", phone);
            object.put("imageUri", imageUri);

            array.put(object);

            prefs.edit()
                    .putString(
                            ITEMS,
                            array.toString()
                    )
                    .apply();

        } catch (Exception ignored) {
        }
    }


    public static List<ListingItem> getItems(
            Context context
    ) {

        ArrayList<ListingItem> result =
                new ArrayList<>();

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE
                );

        JSONArray array =
                getArray(prefs, ITEMS);

        for (int i = 0; i < array.length(); i++) {

            try {

                JSONObject object =
                        array.getJSONObject(i);

                result.add(
                        new ListingItem(
                                object.optString("name"),
                                object.optString("price"),
                                object.optString("condition"),
                                object.optString("category"),
                                object.optString("area"),
                                object.optString("description"),
                                object.optString("phone"),
                                object.optString("imageUri")
                        )
                );

            } catch (Exception ignored) {
            }
        }

        return result;
    }


    // =========================================================
    // ROOM
    // =========================================================

    public static void addRoom(
            Context context,
            String title,
            String price,
            String location,
            String roomType,
            String rentType,
            String gender,
            String description,
            String amenities,
            String phone,
            String imageUri
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE
                );

        JSONArray array =
                getArray(prefs, ROOMS);

        try {

            JSONObject object = new JSONObject();

            object.put("title", title);
            object.put("price", price);
            object.put("location", location);
            object.put("roomType", roomType);
            object.put("rentType", rentType);
            object.put("gender", gender);
            object.put("description", description);
            object.put("amenities", amenities);
            object.put("phone", phone);
            object.put("imageUri", imageUri);

            object.put(
                    "postedTime",
                    System.currentTimeMillis()
            );

            array.put(object);

            prefs.edit()
                    .putString(
                            ROOMS,
                            array.toString()
                    )
                    .apply();

        } catch (Exception ignored) {
        }
    }


    public static List<PostedRoom> getRooms(
            Context context
    ) {

        ArrayList<PostedRoom> result =
                new ArrayList<>();

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE
                );

        JSONArray array =
                getArray(prefs, ROOMS);

        for (int i = 0; i < array.length(); i++) {

            try {

                JSONObject object =
                        array.getJSONObject(i);

                String amenitiesText =
                        object.optString(
                                "amenities",
                                ""
                        );

                String[] amenities;

                if (amenitiesText.trim().isEmpty()) {

                    amenities =
                            new String[]{"No amenities"};

                } else {

                    String[] raw =
                            amenitiesText.split(",");

                    ArrayList<String> clean =
                            new ArrayList<>();

                    for (String item : raw) {

                        String value =
                                item.trim();

                        if (!value.isEmpty()) {
                            clean.add(value);
                        }
                    }

                    amenities =
                            clean.toArray(
                                    new String[0]
                            );
                }


                result.add(
                        new PostedRoom(
                                object.optString("title"),
                                object.optString("price"),
                                object.optString("location"),
                                object.optString("roomType"),
                                object.optString("rentType"),
                                object.optString("gender"),
                                object.optString("description"),
                                amenities,
                                object.optLong(
                                        "postedTime",
                                        System.currentTimeMillis()
                                ),
                                object.optString("phone"),
                                object.optString("imageUri")
                        )
                );

            } catch (Exception ignored) {
            }
        }

        return result;
    }


    // =========================================================
    // JSON
    // =========================================================

    private static JSONArray getArray(
            SharedPreferences prefs,
            String key
    ) {

        String value =
                prefs.getString(
                        key,
                        "[]"
                );

        try {

            return new JSONArray(value);

        } catch (Exception e) {

            return new JSONArray();
        }
    }


    // =========================================================
    // POSTED ROOM MODEL
    // =========================================================

    public static class PostedRoom {

        public final String title;
        public final String price;
        public final String location;
        public final String roomType;
        public final String rentType;
        public final String gender;
        public final String description;
        public final String[] amenities;
        public final long postedTime;
        public final String phone;
        public final String imageUri;


        public PostedRoom(
                String title,
                String price,
                String location,
                String roomType,
                String rentType,
                String gender,
                String description,
                String[] amenities,
                long postedTime,
                String phone,
                String imageUri
        ) {

            this.title = title;
            this.price = price;
            this.location = location;
            this.roomType = roomType;
            this.rentType = rentType;
            this.gender = gender;
            this.description = description;
            this.amenities = amenities;
            this.postedTime = postedTime;
            this.phone = phone;
            this.imageUri = imageUri;
        }
    }
}