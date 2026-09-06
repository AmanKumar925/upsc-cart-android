package com.example.upsccartbyaman;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PostFragment extends Fragment {

    // =========================================================
    // COLORS
    // =========================================================

    private static final int ORANGE = Color.rgb(242, 100, 31);
    private static final int DARK = Color.rgb(30, 30, 30);
    private static final int GRAY = Color.rgb(105, 105, 105);
    private static final int LIGHT_GRAY = Color.rgb(245, 245, 245);
    private static final int BORDER = Color.rgb(225, 225, 225);
    private static final int WHITE = Color.WHITE;
    private static final int GREEN = Color.rgb(40, 150, 90);

    // =========================================================
    // ROOT
    // =========================================================

    private LinearLayout root;

    // =========================================================
    // TYPE
    // =========================================================

    private boolean postingRoom = false;

    private TextView itemTab;
    private TextView roomTab;

    // =========================================================
    // COMMON FIELDS
    // =========================================================

    private EditText titleBox;
    private EditText priceBox;
    private EditText locationBox;
    private EditText phoneBox;
    private EditText descriptionBox;

    // =========================================================
    // ITEM
    // =========================================================

    private Spinner categorySpinner;
    private Spinner conditionSpinner;
    private LinearLayout itemFields;

    // =========================================================
    // ROOM
    // =========================================================

    private Spinner roomTypeSpinner;
    private Spinner rentTypeSpinner;
    private Spinner genderSpinner;
    private EditText amenitiesBox;

    private LinearLayout roomFields;

    // =========================================================
    // IMAGE
    // =========================================================

    private ImageView selectedImage;
    private Uri selectedImageUri;

    private ActivityResultLauncher<String[]> galleryLauncher;

    // =========================================================
    // POST BUTTON
    // =========================================================

    private TextView postButton;

    // =========================================================
    // CREATE
    // =========================================================

    @Nullable
    @Override
    public View onCreateView(
            @NonNull android.view.LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.OpenDocument(),
                uri -> {

                    if (uri == null) {
                        return;
                    }

                    selectedImageUri = uri;

                    try {

                        requireContext()
                                .getContentResolver()
                                .takePersistableUriPermission(
                                        uri,
                                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                                );

                    } catch (Exception ignored) {
                    }

                    selectedImage.setColorFilter(null);

                    selectedImage.setImageURI(uri);

                    selectedImage.setScaleType(
                            ImageView.ScaleType.CENTER_CROP
                    );
                }
        );

        buildUI();

        return root;
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        root = new LinearLayout(requireContext());

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setBackgroundColor(
                WHITE
        );

        // =====================================================
        // TOOLBAR
        // =====================================================

        LinearLayout toolbar =
                new LinearLayout(requireContext());

        toolbar.setOrientation(
                LinearLayout.HORIZONTAL
        );

        toolbar.setGravity(
                Gravity.CENTER_VERTICAL
        );

        toolbar.setPadding(
                dp(12),
                dp(8),
                dp(18),
                dp(8)
        );

        TextView back =
                text("‹", 36, DARK);

        back.setGravity(
                Gravity.CENTER
        );

        back.setOnClickListener(
                v -> {

                    if (requireActivity()
                            .getSupportFragmentManager()
                            .getBackStackEntryCount() > 0) {

                        requireActivity()
                                .getSupportFragmentManager()
                                .popBackStack();

                    } else {

                        requireActivity()
                                .onBackPressed();
                    }
                }
        );

        toolbar.addView(
                back,
                new LinearLayout.LayoutParams(
                        dp(45),
                        dp(50)
                )
        );

        TextView heading =
                text(
                        "Post Something",
                        21,
                        DARK
                );

        heading.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        LinearLayout.LayoutParams headingParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );

        headingParams.gravity =
                Gravity.CENTER_VERTICAL;

        toolbar.addView(
                heading,
                headingParams
        );

        root.addView(
                toolbar,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollView scrollView =
                new ScrollView(requireContext());

        scrollView.setFillViewport(true);

        LinearLayout content =
                new LinearLayout(requireContext());

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setPadding(
                dp(18),
                dp(8),
                dp(18),
                dp(35)
        );

        // =====================================================
        // TYPE
        // =====================================================

        content.addView(
                label("What do you want to post?")
        );

        LinearLayout typeContainer =
                new LinearLayout(requireContext());

        typeContainer.setOrientation(
                LinearLayout.HORIZONTAL
        );

        typeContainer.setPadding(
                dp(4),
                dp(4),
                dp(4),
                dp(4)
        );

        typeContainer.setBackground(
                rounded(
                        LIGHT_GRAY,
                        14,
                        0
                )
        );

        itemTab =
                selectorTab(
                        "Sell Item",
                        true
                );

        roomTab =
                selectorTab(
                        "Post Room",
                        false
                );

        typeContainer.addView(
                itemTab,
                new LinearLayout.LayoutParams(
                        0,
                        dp(48),
                        1
                )
        );

        typeContainer.addView(
                roomTab,
                new LinearLayout.LayoutParams(
                        0,
                        dp(48),
                        1
                )
        );

        itemTab.setOnClickListener(
                v -> {

                    postingRoom = false;

                    updatePostType();
                }
        );

        roomTab.setOnClickListener(
                v -> {

                    postingRoom = true;

                    updatePostType();
                }
        );

        content.addView(
                typeContainer,
                marginParams(
                        -1,
                        -2,
                        0,
                        0,
                        0,
                        18
                )
        );

        // =====================================================
        // IMAGE
        // =====================================================

        content.addView(
                label("Photo")
        );

        FrameLayout imageContainer =
                new FrameLayout(requireContext());

        imageContainer.setBackground(
                rounded(
                        LIGHT_GRAY,
                        16,
                        BORDER
                )
        );

        selectedImage =
                new ImageView(requireContext());

        selectedImage.setImageResource(
                android.R.drawable.ic_menu_gallery
        );

        selectedImage.setColorFilter(
                GRAY
        );

        selectedImage.setScaleType(
                ImageView.ScaleType.CENTER
        );

        imageContainer.addView(
                selectedImage,
                new FrameLayout.LayoutParams(
                        -1,
                        dp(190)
                )
        );

        TextView choosePhoto =
                text(
                        "📷  Choose Photo",
                        15,
                        DARK
                );

        choosePhoto.setGravity(
                Gravity.CENTER
        );

        choosePhoto.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        choosePhoto.setBackground(
                rounded(
                        WHITE,
                        12,
                        BORDER
                )
        );

        FrameLayout.LayoutParams photoParams =
                new FrameLayout.LayoutParams(
                        dp(155),
                        dp(46)
                );

        photoParams.gravity =
                Gravity.BOTTOM |
                        Gravity.CENTER_HORIZONTAL;

        photoParams.bottomMargin =
                dp(12);

        imageContainer.addView(
                choosePhoto,
                photoParams
        );

        choosePhoto.setOnClickListener(
                v -> openGallery()
        );

        selectedImage.setOnClickListener(
                v -> openGallery()
        );

        content.addView(
                imageContainer,
                marginParams(
                        -1,
                        dp(190),
                        0,
                        0,
                        0,
                        20
                )
        );

        // =====================================================
        // COMMON FIELDS
        // =====================================================

        content.addView(
                label("Title")
        );

        titleBox =
                input("Enter a clear title");

        content.addView(
                titleBox,
                fieldParams()
        );

        content.addView(
                label("Price")
        );

        priceBox =
                input("Enter price");

        priceBox.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        content.addView(
                priceBox,
                fieldParams()
        );

        content.addView(
                label("Location / Area")
        );

        locationBox =
                input(
                        "e.g. Mukherjee Nagar"
                );

        content.addView(
                locationBox,
                fieldParams()
        );

        content.addView(
                label("Phone Number")
        );

        phoneBox =
                input(
                        "Enter contact number"
                );

        phoneBox.setInputType(
                InputType.TYPE_CLASS_PHONE
        );

        content.addView(
                phoneBox,
                fieldParams()
        );

        content.addView(
                label("Description")
        );

        descriptionBox =
                input(
                        "Describe your item or room..."
                );

        descriptionBox.setGravity(
                Gravity.TOP
        );

        descriptionBox.setMinLines(
                5
        );

        content.addView(
                descriptionBox,
                fieldParams()
        );

        // =====================================================
        // ITEM FIELDS
        // =====================================================

        itemFields =
                new LinearLayout(
                        requireContext()
                );

        itemFields.setOrientation(
                LinearLayout.VERTICAL
        );

        itemFields.addView(
                label("Category")
        );

        categorySpinner =
                spinner(
                        new String[]{
                                "Furniture",
                                "Appliances",
                                "Electronics",
                                "Books"
                        }
                );

        itemFields.addView(
                categorySpinner,
                fieldParams()
        );

        itemFields.addView(
                label("Condition")
        );

        conditionSpinner =
                spinner(
                        new String[]{
                                "New",
                                "Like New",
                                "Good",
                                "Used"
                        }
                );

        itemFields.addView(
                conditionSpinner,
                fieldParams()
        );

        content.addView(
                itemFields
        );

        // =====================================================
        // ROOM FIELDS
        // =====================================================

        roomFields =
                new LinearLayout(
                        requireContext()
                );

        roomFields.setOrientation(
                LinearLayout.VERTICAL
        );

        roomFields.addView(
                label("Room Type")
        );

        roomTypeSpinner =
                spinner(
                        new String[]{
                                "1RK",
                                "1BHK",
                                "2BHK",
                                "3BHK",
                                "Other"
                        }
                );

        roomFields.addView(
                roomTypeSpinner,
                fieldParams()
        );

        roomFields.addView(
                label("Rent Type")
        );

        rentTypeSpinner =
                spinner(
                        new String[]{
                                "Full Rent",
                                "Room Sharing"
                        }
                );

        roomFields.addView(
                rentTypeSpinner,
                fieldParams()
        );

        roomFields.addView(
                label("Gender")
        );

        genderSpinner =
                spinner(
                        new String[]{
                                "Boy",
                                "Girl",
                                "Boy / Girl"
                        }
                );

        roomFields.addView(
                genderSpinner,
                fieldParams()
        );

        roomFields.addView(
                label("Amenities")
        );

        amenitiesBox =
                input(
                        "e.g. AC, WiFi, Attached Bathroom"
                );

        amenitiesBox.setMinLines(
                2
        );

        roomFields.addView(
                amenitiesBox,
                fieldParams()
        );

        content.addView(
                roomFields
        );

        // =====================================================
        // POST BUTTON
        // =====================================================

        postButton =
                text(
                        "POST ITEM",
                        16,
                        WHITE
                );

        postButton.setGravity(
                Gravity.CENTER
        );

        postButton.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        postButton.setBackground(
                rounded(
                        ORANGE,
                        14,
                        0
                )
        );

        postButton.setOnClickListener(
                v -> submitPost()
        );

        content.addView(
                postButton,
                marginParams(
                        -1,
                        dp(55),
                        0,
                        12,
                        0,
                        20
                )
        );

        // =====================================================
        // INFO
        // =====================================================

        TextView info =
                text(
                        "Your post will be visible to UPSC aspirants in the selected area.",
                        13,
                        GRAY
                );

        info.setGravity(
                Gravity.CENTER
        );

        content.addView(
                info
        );

        scrollView.addView(
                content,
                new ScrollView.LayoutParams(
                        -1,
                        -2
                )
        );

        root.addView(
                scrollView,
                new LinearLayout.LayoutParams(
                        -1,
                        0,
                        1
                )
        );

        updatePostType();
    }

    // =========================================================
    // TYPE SWITCH
    // =========================================================

    private void updatePostType() {

        if (postingRoom) {

            roomFields.setVisibility(
                    View.VISIBLE
            );

            itemFields.setVisibility(
                    View.GONE
            );

            styleTab(
                    roomTab,
                    true
            );

            styleTab(
                    itemTab,
                    false
            );

            postButton.setText(
                    "POST ROOM"
            );

        } else {

            roomFields.setVisibility(
                    View.GONE
            );

            itemFields.setVisibility(
                    View.VISIBLE
            );

            styleTab(
                    itemTab,
                    true
            );

            styleTab(
                    roomTab,
                    false
            );

            postButton.setText(
                    "POST ITEM"
            );
        }
    }

    // =========================================================
    // GALLERY
    // =========================================================

    private void openGallery() {

        galleryLauncher.launch(
                new String[]{
                        "image/*"
                }
        );
    }

    // =========================================================
    // SUBMIT
    // =========================================================

    private void submitPost() {

        String title =
                titleBox.getText()
                        .toString()
                        .trim();

        String price =
                priceBox.getText()
                        .toString()
                        .trim();

        String location =
                locationBox.getText()
                        .toString()
                        .trim();

        String phone =
                phoneBox.getText()
                        .toString()
                        .trim();

        String description =
                descriptionBox.getText()
                        .toString()
                        .trim();

        // =====================================================
        // IMAGE
        // =====================================================

        if (selectedImageUri == null) {

            Toast.makeText(
                    requireContext(),
                    "Please select a photo.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        // =====================================================
        // TITLE
        // =====================================================

        if (title.isEmpty()) {

            titleBox.setError(
                    "Enter title"
            );

            titleBox.requestFocus();

            return;
        }

        // =====================================================
        // PRICE
        // =====================================================

        if (price.isEmpty()) {

            priceBox.setError(
                    "Enter price"
            );

            priceBox.requestFocus();

            return;
        }

        // =====================================================
        // LOCATION
        // =====================================================

        if (location.isEmpty()) {

            locationBox.setError(
                    "Enter location"
            );

            locationBox.requestFocus();

            return;
        }

        // =====================================================
        // PHONE
        // =====================================================

        if (phone.isEmpty()) {

            phoneBox.setError(
                    "Enter phone number"
            );

            phoneBox.requestFocus();

            return;
        }

        if (phone.length() < 10) {

            phoneBox.setError(
                    "Enter a valid phone number"
            );

            phoneBox.requestFocus();

            return;
        }

        // =====================================================
        // DESCRIPTION
        // =====================================================

        if (description.isEmpty()) {

            descriptionBox.setError(
                    "Enter description"
            );

            descriptionBox.requestFocus();

            return;
        }

        // =====================================================
        // SAVE ITEM
        // =====================================================

        if (!postingRoom) {

            String category =
                    categorySpinner
                            .getSelectedItem()
                            .toString();

            String condition =
                    conditionSpinner
                            .getSelectedItem()
                            .toString();

            saveItem(
                    title,
                    price,
                    condition,
                    category,
                    location,
                    description,
                    phone,
                    selectedImageUri.toString()
            );

        }

        // =====================================================
        // SAVE ROOM
        // =====================================================

        else {

            String roomType =
                    roomTypeSpinner
                            .getSelectedItem()
                            .toString();

            String rentType =
                    rentTypeSpinner
                            .getSelectedItem()
                            .toString();

            String gender =
                    genderSpinner
                            .getSelectedItem()
                            .toString();

            String amenities =
                    amenitiesBox
                            .getText()
                            .toString()
                            .trim();

            saveRoom(
                    title,
                    price,
                    location,
                    roomType,
                    rentType,
                    gender,
                    description,
                    amenities,
                    phone,
                    selectedImageUri.toString()
            );
        }
    }

    // =========================================================
    // SAVE ITEM
    // =========================================================

    private void saveItem(
            String title,
            String price,
            String condition,
            String category,
            String location,
            String description,
            String phone,
            String imageUri
    ) {

        try {

            PostRepository.addItem(
                    requireContext(),
                    title,
                    "₹" + price,
                    condition,
                    category,
                    location,
                    description,
                    phone,
                    imageUri
            );

            Toast.makeText(
                    requireContext(),
                    "Item posted successfully!",
                    Toast.LENGTH_LONG
            ).show();

            clearForm();

        } catch (Exception e) {

            Toast.makeText(
                    requireContext(),
                    "Could not save item.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // =========================================================
    // SAVE ROOM
    // =========================================================

    private void saveRoom(
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

        try {

            PostRepository.addRoom(
                    requireContext(),
                    title,
                    "₹" + price,
                    location,
                    roomType,
                    rentType,
                    gender,
                    description,
                    amenities,
                    phone,
                    imageUri
            );

            Toast.makeText(
                    requireContext(),
                    "Room posted successfully!",
                    Toast.LENGTH_LONG
            ).show();

            clearForm();

        } catch (Exception e) {

            Toast.makeText(
                    requireContext(),
                    "Could not save room.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearForm() {

        titleBox.setText("");
        priceBox.setText("");
        locationBox.setText("");
        phoneBox.setText("");
        descriptionBox.setText("");
        amenitiesBox.setText("");

        selectedImageUri = null;

        selectedImage.setImageResource(
                android.R.drawable.ic_menu_gallery
        );

        selectedImage.setScaleType(
                ImageView.ScaleType.CENTER
        );

        selectedImage.setColorFilter(
                GRAY
        );

        categorySpinner.setSelection(0);
        conditionSpinner.setSelection(0);

        roomTypeSpinner.setSelection(0);
        rentTypeSpinner.setSelection(0);
        genderSpinner.setSelection(0);

        postingRoom = false;

        updatePostType();
    }

    // =========================================================
    // TAB
    // =========================================================

    private TextView selectorTab(
            String value,
            boolean selected
    ) {

        TextView view =
                text(
                        value,
                        14,
                        selected
                                ? WHITE
                                : DARK
                );

        view.setGravity(
                Gravity.CENTER
        );

        styleTab(
                view,
                selected
        );

        return view;
    }

    private void styleTab(
            TextView view,
            boolean selected
    ) {

        view.setTextColor(
                selected
                        ? WHITE
                        : DARK
        );

        view.setBackground(
                rounded(
                        selected
                                ? ORANGE
                                : LIGHT_GRAY,
                        11,
                        0
                )
        );
    }

    // =========================================================
    // LABEL
    // =========================================================

    private TextView label(
            String value
    ) {

        TextView view =
                text(
                        value,
                        14,
                        DARK
                );

        view.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        view.setPadding(
                dp(2),
                dp(5),
                dp(2),
                dp(7)
        );

        return view;
    }

    // =========================================================
    // INPUT
    // =========================================================

    private EditText input(
            String hint
    ) {

        EditText edit =
                new EditText(
                        requireContext()
                );

        edit.setHint(
                hint
        );

        edit.setTextSize(
                14
        );

        edit.setTextColor(
                DARK
        );

        edit.setHintTextColor(
                GRAY
        );

        edit.setSingleLine(
                true
        );

        edit.setBackground(
                rounded(
                        WHITE,
                        12,
                        BORDER
                )
        );

        edit.setPadding(
                dp(14),
                dp(12),
                dp(14),
                dp(12)
        );

        return edit;
    }

    // =========================================================
    // SPINNER
    // =========================================================

    private Spinner spinner(
            String[] values
    ) {

        Spinner spinner =
                new Spinner(
                        requireContext()
                );

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        values
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(
                adapter
        );

        spinner.setBackground(
                rounded(
                        WHITE,
                        12,
                        BORDER
                )
        );

        return spinner;
    }

    // =========================================================
    // TEXT
    // =========================================================

    private TextView text(
            String value,
            float size,
            int color
    ) {

        TextView view =
                new TextView(
                        requireContext()
                );

        view.setText(
                value
        );

        view.setTextSize(
                size
        );

        view.setTextColor(
                color
        );

        return view;
    }

    // =========================================================
    // BACKGROUND
    // =========================================================

    private GradientDrawable rounded(
            int color,
            float radius,
            int stroke
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                color
        );

        drawable.setCornerRadius(
                dp((int) radius)
        );

        if (stroke != 0) {

            drawable.setStroke(
                    dp(1),
                    stroke
            );
        }

        return drawable;
    }

    // =========================================================
    // FIELD PARAMS
    // =========================================================

    private LinearLayout.LayoutParams fieldParams() {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(52)
                );

        params.bottomMargin =
                dp(14);

        return params;
    }

    // =========================================================
    // MARGIN PARAMS
    // =========================================================

    private LinearLayout.LayoutParams marginParams(
            int width,
            int height,
            int left,
            int top,
            int right,
            int bottom
    ) {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        width,
                        height
                );

        params.leftMargin =
                dp(left);

        params.topMargin =
                dp(top);

        params.rightMargin =
                dp(right);

        params.bottomMargin =
                dp(bottom);

        return params;
    }

    // =========================================================
    // DP
    // =========================================================

    private int dp(
            int value
    ) {

        return Math.round(
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
        );
    }
}