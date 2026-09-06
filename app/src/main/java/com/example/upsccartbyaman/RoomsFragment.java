package com.example.upsccartbyaman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class RoomsFragment extends Fragment {

    // =========================================================
    // COLORS
    // =========================================================

    private static final int ORANGE =
            Color.rgb(239, 108, 24);

    private static final int TEXT =
            Color.rgb(35, 35, 35);

    private static final int GRAY =
            Color.rgb(105, 105, 105);

    private static final int LIGHT_GREEN =
            Color.rgb(232, 249, 239);

    private static final int BORDER =
            Color.rgb(225, 225, 225);

    // =========================================================
    // FILTER VIEWS
    // =========================================================

    private EditText searchBox;
    private EditText maxRentBox;

    private TextView sortButton;
    private TextView areaButton;
    private TextView roomTypeButton;
    private TextView genderButton;

    private TextView roomCount;

    private LinearLayout roomsContainer;

    // =========================================================
    // DATA
    // =========================================================

    private final ArrayList<RoomData> allRooms =
            new ArrayList<>();

    // =========================================================
    // LIFECYCLE
    // =========================================================

    @Nullable
    @Override
    public View onCreateView(
            @NonNull android.view.LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        Context context = requireContext();

        // =====================================================
        // ROOT
        // =====================================================

        FrameLayout root =
                new FrameLayout(context);

        root.setBackgroundColor(Color.WHITE);

        // =====================================================
        // MAIN
        // =====================================================

        LinearLayout main =
                new LinearLayout(context);

        main.setOrientation(
                LinearLayout.VERTICAL
        );

        main.setBackgroundColor(
                Color.WHITE
        );

        root.addView(
                main,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        // =====================================================
        // FIXED TOOLBAR
        // =====================================================

        LinearLayout toolbar =
                new LinearLayout(context);

        toolbar.setOrientation(
                LinearLayout.HORIZONTAL
        );

        toolbar.setGravity(
                Gravity.CENTER_VERTICAL
        );

        toolbar.setPadding(
                dp(14),
                dp(4),
                dp(10),
                dp(4)
        );

        TextView title =
                new TextView(context);

        title.setText("Rent Rooms");

        title.setTextSize(22);

        title.setTextColor(Color.BLACK);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setGravity(
                Gravity.CENTER_VERTICAL
        );

        toolbar.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        dp(58),
                        1f
                )
        );

        TextView favorite =
                createFloatingButton(
                        context,
                        "♡"
                );

        favorite.setTextSize(32);
        favorite.setTextColor(ORANGE);

        toolbar.addView(
                favorite,
                new LinearLayout.LayoutParams(
                        dp(48),
                        dp(48)
                )
        );

        main.addView(
                toolbar,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(66)
                )
        );

        // =====================================================
        // ONE MAIN SCROLL VIEW
        //
        // Search is inside this ScrollView.
        // It disappears naturally when scrolling down.
        // =====================================================

        ScrollView mainScroll =
                new ScrollView(context);

        mainScroll.setVerticalScrollBarEnabled(false);

        mainScroll.setFillViewport(true);

        LinearLayout scrollContent =
                new LinearLayout(context);

        scrollContent.setOrientation(
                LinearLayout.VERTICAL
        );

        scrollContent.setBackgroundColor(
                Color.WHITE
        );

        // =====================================================
        // FILTER CARD
        // =====================================================

        LinearLayout filterCard =
                new LinearLayout(context);

        filterCard.setOrientation(
                LinearLayout.VERTICAL
        );

        filterCard.setPadding(
                dp(14),
                dp(12),
                dp(14),
                dp(12)
        );

        filterCard.setBackground(
                roundedStroke(
                        Color.WHITE,
                        Color.rgb(238, 238, 238),
                        dp(16)
                )
        );

        // =====================================================
        // SEARCH
        // =====================================================

        searchBox =
                createSearchBox(context);

        filterCard.addView(
                searchBox,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(54)
                )
        );

        // =====================================================
        // ROW 1
        // =====================================================

        LinearLayout row1 =
                createFilterRow(context);

        sortButton =
                createFilterButton(
                        context,
                        "Newest First"
                );

        row1.addView(
                sortButton,
                createWeightParams(
                        dp(52),
                        1f,
                        dp(5)
                )
        );

        TextView leadsButton =
                createLeadsButton(context);

        row1.addView(
                leadsButton,
                createWeightParams(
                        dp(52),
                        1f,
                        0
                )
        );

        addFilterRow(
                filterCard,
                row1,
                dp(10)
        );

        // =====================================================
        // ROW 2
        // =====================================================

        LinearLayout row2 =
                createFilterRow(context);

        areaButton =
                createFilterButton(
                        context,
                        "All Areas"
                );

        row2.addView(
                areaButton,
                createWeightParams(
                        dp(52),
                        1f,
                        dp(5)
                )
        );

        roomTypeButton =
                createFilterButton(
                        context,
                        "All Room Types"
                );

        row2.addView(
                roomTypeButton,
                createWeightParams(
                        dp(52),
                        1f,
                        0
                )
        );

        addFilterRow(
                filterCard,
                row2,
                dp(8)
        );

        // =====================================================
        // ROW 3
        // =====================================================

        LinearLayout row3 =
                createFilterRow(context);

        maxRentBox =
                createMaxRentBox(context);

        row3.addView(
                maxRentBox,
                createWeightParams(
                        dp(52),
                        1f,
                        dp(5)
                )
        );

        genderButton =
                createFilterButton(
                        context,
                        "All Genders"
                );

        row3.addView(
                genderButton,
                createWeightParams(
                        dp(52),
                        1f,
                        0
                )
        );

        addFilterRow(
                filterCard,
                row3,
                dp(8)
        );

        LinearLayout.LayoutParams filterParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        filterParams.setMargins(
                dp(12),
                dp(5),
                dp(12),
                dp(5)
        );

        scrollContent.addView(
                filterCard,
                filterParams
        );

        // =====================================================
        // COUNT ROW
        // =====================================================

        LinearLayout countRow =
                new LinearLayout(context);

        countRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        countRow.setGravity(
                Gravity.CENTER_VERTICAL
        );

        countRow.setPadding(
                dp(18),
                0,
                dp(12),
                0
        );

        roomCount =
                createText(
                        context,
                        "Rooms: 0",
                        18,
                        TEXT,
                        Typeface.BOLD
                );

        countRow.addView(
                roomCount,
                new LinearLayout.LayoutParams(
                        0,
                        dp(50),
                        1f
                )
        );

        TextView refresh =
                new TextView(context);

        refresh.setText("⟳");
        refresh.setTextSize(29);
        refresh.setTextColor(ORANGE);
        refresh.setGravity(Gravity.CENTER);

        refresh.setOnClickListener(
                v -> resetFilters()
        );

        countRow.addView(
                refresh,
                new LinearLayout.LayoutParams(
                        dp(48),
                        dp(48)
                )
        );

        scrollContent.addView(
                countRow,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(52)
                )
        );

        // =====================================================
        // DIVIDER
        // =====================================================

        View divider =
                new View(context);

        divider.setBackgroundColor(
                Color.rgb(235, 235, 235)
        );

        scrollContent.addView(
                divider,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(1)
                )
        );

        // =====================================================
        // ROOM CONTAINER
        // =====================================================

        roomsContainer =
                new LinearLayout(context);

        roomsContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        roomsContainer.setPadding(
                dp(14),
                dp(10),
                dp(14),
                dp(110)
        );

        scrollContent.addView(
                roomsContainer,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        mainScroll.addView(
                scrollContent,
                new ScrollView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        main.addView(
                mainScroll,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                )
        );

        // =====================================================
        // SUPPORT BUTTON
        // =====================================================

        TextView supportButton =
                createFloatingButton(
                        context,
                        "♙"
                );

        supportButton.setTextSize(25);
        supportButton.setTextColor(Color.WHITE);
        supportButton.setElevation(dp(7));

        supportButton.setBackground(
                rounded(
                        ORANGE,
                        dp(30)
                )
        );

        FrameLayout.LayoutParams supportParams =
                new FrameLayout.LayoutParams(
                        dp(58),
                        dp(58),
                        Gravity.BOTTOM | Gravity.END
                );

        supportParams.setMargins(
                0,
                0,
                dp(18),
                dp(20)
        );

        root.addView(
                supportButton,
                supportParams
        );

        // =====================================================
        // LOAD DATA BEFORE DISPLAY
        // =====================================================

        loadRooms();

        setupFilters();

        // =====================================================
        // INITIAL DISPLAY
        //
        // IMPORTANT:
        // Do NOT call applyFilters() before default values exist.
        // =====================================================

        displayRooms(
                new ArrayList<>(allRooms)
        );

        return root;
    }

    // =========================================================
    // ADD FILTER ROW
    // =========================================================

    private void addFilterRow(
            LinearLayout parent,
            LinearLayout row,
            int topMargin) {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(52)
                );

        params.topMargin = topMargin;

        parent.addView(
                row,
                params
        );
    }

    // =========================================================
    // LOAD ROOMS
    // =========================================================

    private void loadRooms() {

        allRooms.clear();

        allRooms.add(
                new RoomData(
                        R.drawable.room1,
                        "Room for rent for girls",
                        "₹11,000",
                        "Patel Nagar",
                        "1RK",
                        "Full Rent",
                        "Girl",
                        "Room for rent for girls. South Patel Nagar near metro.",
                        new String[]{
                                "AC"
                        },
                        "05 Sep 2026, 10:02 PM",
                        true
                )
        );

        allRooms.add(
                new RoomData(
                        R.drawable.room2,
                        "Newly constructed 1BHK available for rent",
                        "₹10,000",
                        "All Areas",
                        "1BHK",
                        "Full Rent",
                        "Boy / Girl",
                        "Newly constructed 1BHK room with attached bathroom and kitchen, balcony, one room.",
                        new String[]{
                                "Attached Bathroom",
                                "Kitchen",
                                "Balcony",
                                "+6 more"
                        },
                        "04 Sep 2026, 11:30 AM",
                        true
                )
        );

        allRooms.add(
                new RoomData(
                        R.drawable.room3,
                        "Female Vacancy in Furnished 2 BHK, Karol Bagh - Prime Location",
                        "₹4,750",
                        "Karol Bagh",
                        "2BHK",
                        "Room Sharing",
                        "Girl",
                        "URGENT: 1 vacancy for Female in 2 BHK.",
                        new String[]{
                                "Fully furnished kitchen",
                                "Balcony",
                                "Cot and bed",
                                "+17 more"
                        },
                        "03 Sep 2026, 09:15 AM",
                        false
                )
        );

        allRooms.add(
                new RoomData(
                        R.drawable.room4,
                        "Beautiful furnished room available",
                        "₹8,500",
                        "Rajendra Place",
                        "1BHK",
                        "Full Rent",
                        "Boy / Girl",
                        "Clean furnished room available near metro station.",
                        new String[]{
                                "AC",
                                "WiFi",
                                "Balcony"
                        },
                        "02 Sep 2026, 06:20 PM",
                        true
                )
        );

        allRooms.add(
                new RoomData(
                        R.drawable.room1,
                        "Single room available near metro",
                        "₹7,500",
                        "Rajouri Garden",
                        "1RK",
                        "Full Rent",
                        "Boy / Girl",
                        "Spacious room available with good connectivity.",
                        new String[]{
                                "AC",
                                "WiFi",
                                "Kitchen"
                        },
                        "01 Sep 2026, 02:30 PM",
                        true
                )
        );

        allRooms.add(
                new RoomData(
                        R.drawable.room2,
                        "Fully furnished room for rent",
                        "₹9,000",
                        "Karol Bagh",
                        "1BHK",
                        "Full Rent",
                        "Boy / Girl",
                        "Fully furnished room with attached bathroom.",
                        new String[]{
                                "Attached Bathroom",
                                "Bed",
                                "Balcony"
                        },
                        "31 Aug 2026, 12:10 PM",
                        false
                )
        );

        // =====================================================
        // POSTED ROOMS (from Post tab)
        // =====================================================

        java.util.List<PostRepository.PostedRoom> postedRooms =
                PostRepository.getRooms(
                        requireContext()
                );

        // Show most recently posted rooms first.
        for (int i = postedRooms.size() - 1; i >= 0; i--) {

            PostRepository.PostedRoom posted =
                    postedRooms.get(i);

            allRooms.add(
                    0,
                    new RoomData(
                            posted.imageUri,
                            posted.title,
                            posted.price,
                            posted.location,
                            posted.roomType,
                            posted.rentType,
                            posted.gender,
                            posted.description,
                            posted.amenities,
                            formatPostedTime(
                                    posted.postedTime
                            ),
                            false,
                            posted.phone
                    )
            );
        }
    }

    // =========================================================
    // FORMAT POSTED TIME
    // =========================================================

    private String formatPostedTime(
            long millis
    ) {

        try {

            return new java.text.SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
            ).format(
                    new java.util.Date(millis)
            );

        } catch (Exception e) {

            return "";
        }
    }

    // =========================================================
    // FILTER SETUP
    // =========================================================

    private void setupFilters() {

        // =====================================================
        // SEARCH
        // =====================================================

        searchBox.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        applyFilters();
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        // =====================================================
        // MAX RENT
        // =====================================================

        maxRentBox.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        applyFilters();
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        // =====================================================
        // SORT
        // =====================================================

        sortButton.setOnClickListener(
                v -> showSortMenu()
        );

        // =====================================================
        // AREA
        // =====================================================

        areaButton.setOnClickListener(
                v -> showAreaMenu()
        );

        // =====================================================
        // ROOM TYPE
        // =====================================================

        roomTypeButton.setOnClickListener(
                v -> showRoomTypeMenu()
        );

        // =====================================================
        // GENDER
        // =====================================================

        genderButton.setOnClickListener(
                v -> showGenderMenu()
        );
    }

    // =========================================================
    // SORT MENU
    // =========================================================

    private void showSortMenu() {

        PopupMenu popup =
                new PopupMenu(
                        requireContext(),
                        sortButton
                );

        popup.getMenu().add(
                "Newest First"
        );

        popup.getMenu().add(
                "Price: Low to High"
        );

        popup.getMenu().add(
                "Price: High to Low"
        );

        popup.setOnMenuItemClickListener(
                item -> {

                    sortButton.setText(
                            item.getTitle()
                    );

                    applyFilters();

                    return true;
                }
        );

        popup.show();
    }

    // =========================================================
    // AREA MENU
    // =========================================================

    private void showAreaMenu() {

        PopupMenu popup =
                new PopupMenu(
                        requireContext(),
                        areaButton
                );

        popup.getMenu().add("All Areas");
        popup.getMenu().add("Patel Nagar");
        popup.getMenu().add("Karol Bagh");
        popup.getMenu().add("Rajendra Place");
        popup.getMenu().add("Rajouri Garden");

        popup.setOnMenuItemClickListener(
                item -> {

                    areaButton.setText(
                            item.getTitle()
                    );

                    applyFilters();

                    return true;
                }
        );

        popup.show();
    }

    // =========================================================
    // ROOM TYPE MENU
    // =========================================================

    private void showRoomTypeMenu() {

        PopupMenu popup =
                new PopupMenu(
                        requireContext(),
                        roomTypeButton
                );

        popup.getMenu().add("All Room Types");
        popup.getMenu().add("1RK");
        popup.getMenu().add("1BHK");
        popup.getMenu().add("2BHK");

        popup.setOnMenuItemClickListener(
                item -> {

                    roomTypeButton.setText(
                            item.getTitle()
                    );

                    applyFilters();

                    return true;
                }
        );

        popup.show();
    }

    // =========================================================
    // GENDER MENU
    // =========================================================

    private void showGenderMenu() {

        PopupMenu popup =
                new PopupMenu(
                        requireContext(),
                        genderButton
                );

        popup.getMenu().add("All Genders");
        popup.getMenu().add("Girl");
        popup.getMenu().add("Boy");
        popup.getMenu().add("Boy / Girl");

        popup.setOnMenuItemClickListener(
                item -> {

                    genderButton.setText(
                            item.getTitle()
                    );

                    applyFilters();

                    return true;
                }
        );

        popup.show();
    }

    // =========================================================
    // APPLY FILTERS
    // =========================================================

    private void applyFilters() {

        if (roomsContainer == null ||
                searchBox == null ||
                maxRentBox == null ||
                sortButton == null ||
                areaButton == null ||
                roomTypeButton == null ||
                genderButton == null) {

            return;
        }

        ArrayList<RoomData> filtered =
                new ArrayList<>();

        String search =
                searchBox.getText()
                        .toString()
                        .trim()
                        .toLowerCase(
                                Locale.ROOT
                        );

        String selectedArea =
                cleanFilterText(
                        areaButton.getText().toString()
                );

        String selectedRoomType =
                cleanFilterText(
                        roomTypeButton.getText().toString()
                );

        String selectedGender =
                cleanFilterText(
                        genderButton.getText().toString()
                );

        String selectedSort =
                cleanFilterText(
                        sortButton.getText().toString()
                );

        // =====================================================
        // MAX RENT
        // =====================================================

        int maxRent =
                parseMaxRent(
                        maxRentBox.getText().toString()
                );

        // =====================================================
        // FILTER ONLY
        //
        // IMPORTANT:
        // selectedSort is NOT used as a filter.
        // =====================================================

        for (RoomData room : allRooms) {

            // -------------------------------------------------
            // SEARCH
            // -------------------------------------------------

            if (!search.isEmpty()) {

                String searchable =
                        (
                                safe(room.title) + " " +
                                        safe(room.location) + " " +
                                        safe(room.roomType) + " " +
                                        safe(room.rentType) + " " +
                                        safe(room.gender) + " " +
                                        safe(room.description) + " " +
                                        joinAmenities(room.amenities)
                        ).toLowerCase(Locale.ROOT);

                if (!searchable.contains(search)) {
                    continue;
                }
            }

            // -------------------------------------------------
            // AREA
            // -------------------------------------------------

            if (!selectedArea.equalsIgnoreCase(
                    "All Areas")) {

                if (!room.location.equalsIgnoreCase(
                        selectedArea)) {

                    continue;
                }
            }

            // -------------------------------------------------
            // ROOM TYPE
            // -------------------------------------------------

            if (!selectedRoomType.equalsIgnoreCase(
                    "All Room Types")) {

                if (!room.roomType.equalsIgnoreCase(
                        selectedRoomType)) {

                    continue;
                }
            }

            // -------------------------------------------------
            // GENDER
            // -------------------------------------------------

            if (!selectedGender.equalsIgnoreCase(
                    "All Genders")) {

                if (selectedGender.equalsIgnoreCase(
                        "Boy")) {

                    if (!room.gender.equalsIgnoreCase("Boy") &&
                            !room.gender.equalsIgnoreCase("Boy / Girl")) {

                        continue;
                    }

                } else if (selectedGender.equalsIgnoreCase(
                        "Girl")) {

                    if (!room.gender.equalsIgnoreCase("Girl") &&
                            !room.gender.equalsIgnoreCase("Boy / Girl")) {

                        continue;
                    }

                } else {

                    if (!room.gender.equalsIgnoreCase(
                            selectedGender)) {

                        continue;
                    }
                }
            }

            // -------------------------------------------------
            // MAX RENT
            // -------------------------------------------------

            if (maxRent > 0) {

                int rent =
                        extractPrice(room.price);

                if (rent > maxRent) {
                    continue;
                }
            }

            filtered.add(room);
        }

        // =====================================================
        // SORT AFTER FILTERING
        // =====================================================

        if (selectedSort.equalsIgnoreCase(
                "Price: Low to High")) {

            filtered.sort(
                    Comparator.comparingInt(
                            room -> extractPrice(room.price)
                    )
            );

        } else if (selectedSort.equalsIgnoreCase(
                "Price: High to Low")) {

            filtered.sort(
                    (a, b) ->
                            Integer.compare(
                                    extractPrice(b.price),
                                    extractPrice(a.price)
                            )
            );

        } else {

            // Newest First
            filtered.sort(
                    (a, b) ->
                            b.postedTime.compareTo(
                                    a.postedTime
                            )
            );
        }

        // =====================================================
        // DISPLAY
        // =====================================================

        displayRooms(filtered);
    }

    // =========================================================
    // DISPLAY ROOMS
    // =========================================================

    private void displayRooms(
            ArrayList<RoomData> rooms) {

        if (roomsContainer == null ||
                roomCount == null) {

            return;
        }

        roomsContainer.removeAllViews();

        roomCount.setText(
                "Rooms: " + rooms.size()
        );

        if (rooms.isEmpty()) {

            TextView empty =
                    createText(
                            requireContext(),
                            "No rooms found.\nTry changing your filters.",
                            16,
                            GRAY,
                            Typeface.NORMAL
                    );

            empty.setGravity(
                    Gravity.CENTER
            );

            empty.setPadding(
                    dp(20),
                    dp(80),
                    dp(20),
                    dp(100)
            );

            roomsContainer.addView(
                    empty,
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );

            return;
        }

        for (RoomData room : rooms) {

            roomsContainer.addView(
                    createRoomCard(
                            requireContext(),
                            room
                    )
            );
        }
    }

    // =========================================================
    // RESET
    // =========================================================

    private void resetFilters() {

        // Clear text first.
        searchBox.setText("");

        maxRentBox.setText("");

        sortButton.setText(
                "Newest First"
        );

        areaButton.setText(
                "All Areas"
        );

        roomTypeButton.setText(
                "All Room Types"
        );

        genderButton.setText(
                "All Genders"
        );

        // Explicitly show everything.
        displayRooms(
                new ArrayList<>(allRooms)
        );
    }

    // =========================================================
    // ROOM CARD
    // =========================================================

    private View createRoomCard(
            Context context,
            RoomData room) {

        LinearLayout card =
                new LinearLayout(context);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setBackground(
                roundedStroke(
                        Color.WHITE,
                        Color.rgb(230, 230, 230),
                        dp(18)
                )
        );

        card.setClipToOutline(true);

        // =====================================================
        // IMAGE
        // =====================================================

        FrameLayout imageContainer =
                new FrameLayout(context);

        imageContainer.setBackgroundColor(
                Color.rgb(242, 242, 242)
        );

        ImageView image =
                new ImageView(context);

        if (room.imageUri != null &&
                !room.imageUri.isEmpty()) {

            try {

                image.setImageURI(
                        Uri.parse(
                                room.imageUri
                        )
                );

            } catch (Exception e) {

                image.setImageResource(
                        R.drawable.room1
                );
            }

        } else {

            image.setImageResource(
                    room.image
            );
        }

        image.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        imageContainer.addView(
                image,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        getRoomImageHeight()
                )
        );

        // =====================================================
        // VIDEO
        // =====================================================

        if (room.hasVideo) {

            TextView videoLabel =
                    new TextView(context);

            videoLabel.setText("Video");
            videoLabel.setTextSize(12);
            videoLabel.setTextColor(Color.WHITE);

            videoLabel.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );

            videoLabel.setGravity(
                    Gravity.CENTER
            );

            videoLabel.setBackground(
                    rounded(
                            Color.argb(
                                    190,
                                    45,
                                    45,
                                    45
                            ),
                            dp(12)
                    )
            );

            FrameLayout.LayoutParams videoParams =
                    new FrameLayout.LayoutParams(
                            dp(60),
                            dp(30),
                            Gravity.TOP | Gravity.START
                    );

            videoParams.setMargins(
                    dp(9),
                    dp(9),
                    0,
                    0
            );

            imageContainer.addView(
                    videoLabel,
                    videoParams
            );
        }

        // =====================================================
        // HEART
        // =====================================================

        TextView heart =
                createFloatingButton(
                        context,
                        "♡"
                );

        heart.setTextSize(30);

        FrameLayout.LayoutParams heartParams =
                new FrameLayout.LayoutParams(
                        dp(46),
                        dp(46),
                        Gravity.TOP | Gravity.END
                );

        heartParams.setMargins(
                0,
                dp(9),
                dp(9),
                0
        );

        imageContainer.addView(
                heart,
                heartParams
        );

        heart.setOnClickListener(
                v -> {

                    if (heart.getText()
                            .toString()
                            .equals("♡")) {

                        heart.setText("♥");

                        heart.setTextColor(
                                ORANGE
                        );

                    } else {

                        heart.setText("♡");

                        heart.setTextColor(
                                Color.rgb(
                                        120,
                                        120,
                                        120
                                )
                        );
                    }
                }
        );

        card.addView(
                imageContainer
        );

        // =====================================================
        // CONTENT
        // =====================================================

        LinearLayout content =
                new LinearLayout(context);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setPadding(
                dp(15),
                dp(13),
                dp(15),
                dp(13)
        );

        // =====================================================
        // TITLE
        // =====================================================

        TextView title =
                createText(
                        context,
                        room.title,
                        18,
                        TEXT,
                        Typeface.BOLD
                );

        title.setMaxLines(2);

        title.setEllipsize(
                TextUtils.TruncateAt.END
        );

        content.addView(title);

        // =====================================================
        // PRICE
        // =====================================================

        TextView price =
                createText(
                        context,
                        room.price + " / month",
                        17,
                        ORANGE,
                        Typeface.BOLD
                );

        price.setPadding(
                0,
                dp(5),
                0,
                dp(3)
        );

        content.addView(price);

        // =====================================================
        // LOCATION
        // =====================================================

        TextView location =
                createText(
                        context,
                        "⌖  " + room.location,
                        14,
                        GRAY,
                        Typeface.NORMAL
                );

        location.setPadding(
                0,
                dp(2),
                0,
                dp(8)
        );

        content.addView(location);

        // =====================================================
        // CHIPS
        // =====================================================

        HorizontalScrollView chipScroll =
                new HorizontalScrollView(context);

        chipScroll.setHorizontalScrollBarEnabled(
                false
        );

        LinearLayout chips =
                new LinearLayout(context);

        chips.setOrientation(
                LinearLayout.HORIZONTAL
        );

        chips.addView(
                createChip(
                        context,
                        "▱ " + room.roomType,
                        false
                )
        );

        chips.addView(
                createChip(
                        context,
                        room.rentType,
                        true
                )
        );

        chips.addView(
                createChip(
                        context,
                        "♙ " + room.gender,
                        false
                )
        );

        chipScroll.addView(chips);

        content.addView(
                chipScroll,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(38)
                )
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        TextView description =
                createText(
                        context,
                        room.description,
                        14,
                        GRAY,
                        Typeface.NORMAL
                );

        description.setMaxLines(3);

        description.setEllipsize(
                TextUtils.TruncateAt.END
        );

        description.setPadding(
                0,
                dp(10),
                0,
                dp(8)
        );

        content.addView(description);

        // =====================================================
        // AMENITIES
        // =====================================================

        HorizontalScrollView amenityScroll =
                new HorizontalScrollView(context);

        amenityScroll.setHorizontalScrollBarEnabled(
                false
        );

        LinearLayout amenityContainer =
                new LinearLayout(context);

        amenityContainer.setOrientation(
                LinearLayout.HORIZONTAL
        );

        for (String amenityText :
                room.amenities) {

            TextView amenityView =
                    new TextView(context);

            amenityView.setText(
                    amenityText
            );

            amenityView.setTextSize(12);

            amenityView.setTextColor(
                    Color.DKGRAY
            );

            amenityView.setGravity(
                    Gravity.CENTER
            );

            amenityView.setSingleLine(true);

            amenityView.setPadding(
                    dp(11),
                    0,
                    dp(11),
                    0
            );

            amenityView.setBackground(
                    roundedStroke(
                            Color.WHITE,
                            Color.rgb(
                                    215,
                                    215,
                                    215
                            ),
                            dp(20)
                    )
            );

            LinearLayout.LayoutParams amenityParams =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            dp(34)
                    );

            amenityParams.setMargins(
                    0,
                    0,
                    dp(6),
                    0
            );

            amenityContainer.addView(
                    amenityView,
                    amenityParams
            );
        }

        amenityScroll.addView(
                amenityContainer
        );

        content.addView(
                amenityScroll,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(36)
                )
        );

        // =====================================================
        // DIVIDER
        // =====================================================

        View cardDivider =
                new View(context);

        cardDivider.setBackgroundColor(
                Color.rgb(235, 235, 235)
        );

        LinearLayout.LayoutParams dividerParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(1)
                );

        dividerParams.setMargins(
                0,
                dp(9),
                0,
                dp(3)
        );

        content.addView(
                cardDivider,
                dividerParams
        );

        // =====================================================
        // BOTTOM ROW
        // =====================================================

        LinearLayout bottomRow =
                new LinearLayout(context);

        bottomRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        bottomRow.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView admin =
                createText(
                        context,
                        "♙  Admin",
                        14,
                        GRAY,
                        Typeface.BOLD
                );

        bottomRow.addView(
                admin,
                new LinearLayout.LayoutParams(
                        0,
                        dp(45),
                        1f
                )
        );

        TextView time =
                createText(
                        context,
                        "◷ " + room.postedTime,
                        11,
                        GRAY,
                        Typeface.NORMAL
                );

        time.setGravity(
                Gravity.CENTER_VERTICAL
        );

        time.setSingleLine(true);

        bottomRow.addView(
                time,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(45)
                )
        );

        TextView chat =
                createFloatingButton(
                        context,
                        "□"
                );

        chat.setTextSize(22);

        chat.setTextColor(
                Color.rgb(40, 155, 100)
        );

        chat.setBackground(
                roundedStroke(
                        LIGHT_GREEN,
                        Color.rgb(
                                185,
                                230,
                                205
                        ),
                        dp(30)
                )
        );

        LinearLayout.LayoutParams chatParams =
                new LinearLayout.LayoutParams(
                        dp(44),
                        dp(44)
                );

        chatParams.leftMargin =
                dp(8);

        bottomRow.addView(
                chat,
                chatParams
        );

        content.addView(bottomRow);

        card.addView(content);

        // =====================================================
        // CARD MARGIN
        // =====================================================

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        cardParams.bottomMargin =
                dp(14);

        card.setLayoutParams(cardParams);

        // =====================================================
        // DETAILS
        // =====================================================

        card.setOnClickListener(
                v -> openRoomDetails(room)
        );

        return card;
    }

    // =========================================================
    // RESPONSIVE IMAGE HEIGHT
    // =========================================================

    private int getRoomImageHeight() {

        int screenWidth =
                getResources()
                        .getDisplayMetrics()
                        .widthPixels;

        int height =
                (int) (screenWidth * 0.52f);

        return Math.max(
                dp(180),
                Math.min(
                        dp(240),
                        height
                )
        );
    }

    // =========================================================
    // DETAILS
    // =========================================================

    private void openRoomDetails(
            RoomData room) {

        Intent intent =
                new Intent(
                        requireContext(),
                        RoomDetailsActivity.class
                );

        intent.putExtra(
                "image",
                room.image
        );

        intent.putExtra(
                "imageUri",
                room.imageUri
        );

        intent.putExtra(
                "phone",
                room.phone
        );

        intent.putExtra(
                "title",
                room.title
        );

        intent.putExtra(
                "price",
                room.price
        );

        intent.putExtra(
                "location",
                room.location
        );

        intent.putExtra(
                "roomType",
                room.roomType
        );

        intent.putExtra(
                "rentType",
                room.rentType
        );

        intent.putExtra(
                "gender",
                room.gender
        );

        intent.putExtra(
                "postedTime",
                room.postedTime
        );

        intent.putExtra(
                "description",
                room.description
        );

        intent.putExtra(
                "amenities",
                room.amenities
        );

        startActivity(intent);
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private EditText createSearchBox(
            Context context) {

        EditText editText =
                new EditText(context);

        editText.setHint(
                "Search by location, title..."
        );

        editText.setHintTextColor(
                Color.rgb(175, 175, 175)
        );

        editText.setTextColor(TEXT);

        editText.setTextSize(15);

        editText.setSingleLine(true);

        editText.setInputType(
                InputType.TYPE_CLASS_TEXT
        );

        editText.setPadding(
                dp(15),
                0,
                dp(15),
                0
        );

        editText.setBackground(
                roundedStroke(
                        Color.rgb(250, 250, 250),
                        BORDER,
                        dp(14)
                )
        );

        return editText;
    }

    // =========================================================
    // MAX RENT
    // =========================================================

    private EditText createMaxRentBox(
            Context context) {

        EditText editText =
                new EditText(context);

        editText.setHint("Max Rent");

        editText.setHintTextColor(
                Color.rgb(175, 175, 175)
        );

        editText.setTextColor(TEXT);

        editText.setTextSize(14);

        editText.setSingleLine(true);

        editText.setInputType(
                InputType.TYPE_CLASS_NUMBER
        );

        editText.setPadding(
                dp(14),
                0,
                dp(10),
                0
        );

        editText.setBackground(
                roundedStroke(
                        Color.WHITE,
                        BORDER,
                        dp(13)
                )
        );

        return editText;
    }

    // =========================================================
    // FILTER ROW
    // =========================================================

    private LinearLayout createFilterRow(
            Context context) {

        LinearLayout row =
                new LinearLayout(context);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        return row;
    }

    // =========================================================
    // FILTER BUTTON
    // =========================================================

    private TextView createFilterButton(
            Context context,
            String text) {

        TextView button =
                new TextView(context);

        button.setText(
                text + "  ▾"
        );

        button.setTextSize(14);

        button.setTextColor(
                Color.rgb(65, 65, 65)
        );

        button.setGravity(
                Gravity.CENTER_VERTICAL
        );

        button.setSingleLine(true);

        button.setEllipsize(
                TextUtils.TruncateAt.END
        );

        button.setPadding(
                dp(13),
                0,
                dp(8),
                0
        );

        button.setBackground(
                roundedStroke(
                        Color.WHITE,
                        BORDER,
                        dp(13)
                )
        );

        return button;
    }

    // =========================================================
    // LEADS
    // =========================================================

    private TextView createLeadsButton(
            Context context) {

        TextView button =
                new TextView(context);

        button.setText(
                "Tap For Free Room Leads"
        );

        button.setTextSize(13);

        button.setTextColor(Color.WHITE);

        button.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        button.setGravity(Gravity.CENTER);

        button.setSingleLine(true);

        button.setEllipsize(
                TextUtils.TruncateAt.END
        );

        button.setPadding(
                dp(5),
                0,
                dp(5),
                0
        );

        button.setBackground(
                roundedStroke(
                        Color.rgb(87, 180, 69),
                        Color.rgb(240, 210, 70),
                        dp(13)
                )
        );

        return button;
    }

    // =========================================================
    // CHIP
    // =========================================================

    private TextView createChip(
            Context context,
            String text,
            boolean green) {

        TextView chip =
                new TextView(context);

        chip.setText(text);

        chip.setTextSize(12);

        chip.setGravity(Gravity.CENTER);

        chip.setSingleLine(true);

        chip.setPadding(
                dp(10),
                0,
                dp(10),
                0
        );

        if (green) {

            chip.setTextColor(
                    Color.rgb(50, 160, 100)
            );

            chip.setBackground(
                    roundedStroke(
                            LIGHT_GREEN,
                            Color.rgb(
                                    170,
                                    225,
                                    195
                            ),
                            dp(20)
                    )
            );

        } else {

            chip.setTextColor(
                    Color.DKGRAY
            );

            chip.setBackground(
                    roundedStroke(
                            Color.rgb(248, 248, 248),
                            BORDER,
                            dp(20)
                    )
            );
        }

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(34)
                );

        params.setMargins(
                0,
                0,
                dp(6),
                0
        );

        chip.setLayoutParams(params);

        return chip;
    }

    // =========================================================
    // FLOATING BUTTON
    // =========================================================

    private TextView createFloatingButton(
            Context context,
            String symbol) {

        TextView button =
                new TextView(context);

        button.setText(symbol);

        button.setTextSize(28);

        button.setTextColor(
                Color.rgb(110, 110, 110)
        );

        button.setGravity(Gravity.CENTER);

        button.setBackground(
                rounded(
                        Color.WHITE,
                        dp(30)
                )
        );

        button.setElevation(dp(2));

        return button;
    }

    // =========================================================
    // TEXT
    // =========================================================

    private TextView createText(
            Context context,
            String text,
            int size,
            int color,
            int typeface) {

        TextView view =
                new TextView(context);

        view.setText(text);

        view.setTextSize(size);

        view.setTextColor(color);

        view.setTypeface(
                Typeface.DEFAULT,
                typeface
        );

        return view;
    }

    // =========================================================
    // WEIGHT PARAMS
    // =========================================================

    private LinearLayout.LayoutParams createWeightParams(
            int height,
            float weight,
            int rightMargin) {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        height,
                        weight
                );

        params.rightMargin =
                rightMargin;

        return params;
    }

    // =========================================================
    // ROUNDED
    // =========================================================

    private GradientDrawable rounded(
            int color,
            int radius) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);

        drawable.setCornerRadius(radius);

        return drawable;
    }

    // =========================================================
    // ROUNDED STROKE
    // =========================================================

    private GradientDrawable roundedStroke(
            int background,
            int stroke,
            int radius) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(background);

        drawable.setCornerRadius(radius);

        drawable.setStroke(
                dp(1),
                stroke
        );

        return drawable;
    }

    // =========================================================
    // PRICE
    // =========================================================

    private int extractPrice(
            String price) {

        if (price == null ||
                price.trim().isEmpty()) {

            return Integer.MAX_VALUE;
        }

        String numbers =
                price.replaceAll(
                        "[^0-9]",
                        ""
                );

        if (numbers.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        try {

            return Integer.parseInt(numbers);

        } catch (NumberFormatException e) {

            return Integer.MAX_VALUE;
        }
    }

    // =========================================================
    // MAX RENT
    // =========================================================

    private int parseMaxRent(
            String text) {

        if (text == null ||
                text.trim().isEmpty()) {

            return 0;
        }

        String numbers =
                text.replaceAll(
                        "[^0-9]",
                        ""
                );

        if (numbers.isEmpty()) {
            return 0;
        }

        try {

            return Integer.parseInt(numbers);

        } catch (NumberFormatException e) {

            return 0;
        }
    }

    // =========================================================
    // CLEAN FILTER TEXT
    // =========================================================

    private String cleanFilterText(
            String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("▾", "")
                .trim();
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value) {

        return value == null
                ? ""
                : value;
    }

    // =========================================================
    // AMENITIES TO STRING
    // =========================================================

    private String joinAmenities(
            String[] amenities) {

        if (amenities == null) {
            return "";
        }

        StringBuilder result =
                new StringBuilder();

        for (String amenityText :
                amenities) {

            if (amenityText != null) {

                result.append(
                        amenityText
                ).append(" ");
            }
        }

        return result.toString();
    }

    // =========================================================
    // DP
    // =========================================================

    private int dp(int value) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
                        + 0.5f
        );
    }

    // =========================================================
    // ROOM MODEL
    // =========================================================

    private static class RoomData {

        int image;
        String imageUri;

        String title;
        String price;
        String location;
        String roomType;
        String rentType;
        String gender;
        String description;

        String[] amenities;

        String postedTime;

        boolean hasVideo;

        String phone;

        // Hardcoded sample rooms (drawable resource image).
        RoomData(
                int image,
                String title,
                String price,
                String location,
                String roomType,
                String rentType,
                String gender,
                String description,
                String[] amenities,
                String postedTime,
                boolean hasVideo) {

            this.image = image;
            this.imageUri = null;
            this.title = title;
            this.price = price;
            this.location = location;
            this.roomType = roomType;
            this.rentType = rentType;
            this.gender = gender;
            this.description = description;
            this.amenities = amenities;
            this.postedTime = postedTime;
            this.hasVideo = hasVideo;
            this.phone = "";
        }

        // Posted rooms (user-selected photo URI + phone number).
        RoomData(
                String imageUri,
                String title,
                String price,
                String location,
                String roomType,
                String rentType,
                String gender,
                String description,
                String[] amenities,
                String postedTime,
                boolean hasVideo,
                String phone) {

            this.image = 0;
            this.imageUri = imageUri;
            this.title = title;
            this.price = price;
            this.location = location;
            this.roomType = roomType;
            this.rentType = rentType;
            this.gender = gender;
            this.description = description;
            this.amenities = amenities;
            this.postedTime = postedTime;
            this.hasVideo = hasVideo;
            this.phone = phone == null ? "" : phone;
        }
    }
}