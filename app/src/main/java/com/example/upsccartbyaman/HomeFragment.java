package com.example.upsccartbyaman;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends androidx.fragment.app.Fragment {

    // =========================================================
    // COLORS
    // =========================================================

    private final int ORANGE = Color.rgb(242, 100, 31);
    private final int DARK = Color.rgb(20, 20, 20);

    // =========================================================
    // VARIABLES
    // =========================================================

    private final ArrayList<ListingItem> listings =
            new ArrayList<>();

    private ListingAdapter listingAdapter;

    private TextView locationText;
    private TextView resultText;

    private EditText search;

    private String selectedCategory = "All";
    private String selectedArea = "All Areas";

    // =========================================================
    // RESPONSIVE DP
    // =========================================================

    private int dp(int value) {

        if (getContext() == null) {
            return value;
        }

        return (int) (
                value *
                        requireContext()
                                .getResources()
                                .getDisplayMetrics()
                                .density
        );
    }

    // =========================================================
    // SCREEN WIDTH
    // =========================================================

    private int screenWidth() {

        if (getContext() == null) {
            return 1080;
        }

        return requireContext()
                .getResources()
                .getDisplayMetrics()
                .widthPixels;
    }

    // =========================================================
    // RESPONSIVE SIDE PADDING
    // =========================================================

    private int sidePadding() {

        int width = screenWidth();

        if (width < dp(360)) {
            return dp(12);
        }

        if (width < dp(600)) {
            return dp(16);
        }

        if (width < dp(900)) {
            return dp(28);
        }

        return dp(40);
    }

    // =========================================================
    // ON CREATE VIEW
    // =========================================================

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        // =====================================================
        // MAIN SCROLL
        // =====================================================

        NestedScrollView scroll =
                new NestedScrollView(requireContext());

        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setBackgroundColor(Color.WHITE);

        LinearLayout content =
                new LinearLayout(requireContext());

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        int horizontalPadding =
                sidePadding();

        content.setPadding(
                horizontalPadding,
                dp(15),
                horizontalPadding,
                dp(120)
        );

        scroll.addView(
                content,
                new ViewGroup.LayoutParams(
                        -1,
                        -2
                )
        );

        // =========================================================
        // TOP BAR
        // =========================================================

        LinearLayout topBar =
                new LinearLayout(requireContext());

        topBar.setOrientation(
                LinearLayout.HORIZONTAL
        );

        topBar.setGravity(
                Gravity.CENTER_VERTICAL
        );

        // ---------------------------------------------------------
        // LOGO
        // ---------------------------------------------------------

        ImageView logo =
                new ImageView(requireContext());

        logo.setImageResource(
                R.drawable.logo
        );

        logo.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        final int logoRadius =
                dp(16);

        logo.setOutlineProvider(
                new ViewOutlineProvider() {

                    @Override
                    public void getOutline(
                            View view,
                            Outline outline
                    ) {

                        outline.setRoundRect(
                                0,
                                0,
                                view.getWidth(),
                                view.getHeight(),
                                logoRadius
                        );
                    }
                }
        );



        logo.setClipToOutline(true);

        int logoSize =
                screenWidth() < dp(360)
                        ? dp(44)
                        : dp(50);

        topBar.addView(
                logo,
                new LinearLayout.LayoutParams(
                        logoSize,
                        logoSize
                )
        );

        // ---------------------------------------------------------
        // LOCATION
        // ---------------------------------------------------------

        TextView location =
                new TextView(requireContext());

        location.setText(
                "📍  All Areas"
        );

        location.setTextSize(
                screenWidth() < dp(360)
                        ? 13
                        : 15
        );

        location.setTextColor(
                Color.rgb(60, 60, 60)
        );

        location.setGravity(
                Gravity.CENTER
        );

        location.setSingleLine(true);

        location.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        location.setPadding(
                dp(10),
                dp(8),
                dp(10),
                dp(8)
        );

        GradientDrawable locationBg =
                new GradientDrawable();

        locationBg.setColor(
                Color.rgb(253, 236, 217)
        );

        locationBg.setCornerRadius(
                dp(30)
        );

        location.setBackground(
                locationBg
        );

        LinearLayout.LayoutParams locationParams =
                new LinearLayout.LayoutParams(
                        0,
                        dp(46),
                        1
                );

        locationParams.setMargins(
                dp(8),
                0,
                dp(6),
                0
        );

        topBar.addView(
                location,
                locationParams
        );

        locationText = location;

        location.setOnClickListener(
                v -> showAreaMenu()
        );

        // ---------------------------------------------------------
        // HEART
        // ---------------------------------------------------------

        TextView heart =
                new TextView(requireContext());

        heart.setText(
                "♡"
        );

        heart.setTextSize(
                screenWidth() < dp(360)
                        ? 22
                        : 25
        );

        heart.setTextColor(
                ORANGE
        );

        heart.setGravity(
                Gravity.CENTER
        );

        topBar.addView(
                heart,
                new LinearLayout.LayoutParams(
                        dp(38),
                        dp(48)
                )
        );

        // ---------------------------------------------------------
        // AVATAR
        // ---------------------------------------------------------

        ImageView avatar =
                new ImageView(requireContext());

        GradientDrawable avatarBg =
                new GradientDrawable();

        avatarBg.setShape(
                GradientDrawable.OVAL
        );

        avatarBg.setColor(
                Color.rgb(200, 200, 200)
        );

        avatar.setBackground(
                avatarBg
        );

        int avatarSize =
                screenWidth() < dp(360)
                        ? dp(40)
                        : dp(44);

        topBar.addView(
                avatar,
                new LinearLayout.LayoutParams(
                        avatarSize,
                        avatarSize
                )
        );

        content.addView(
                topBar,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(55)
                )
        );

        // =========================================================
        // VERIFY BOX
        // =========================================================

        LinearLayout verify =
                new LinearLayout(requireContext());

        verify.setOrientation(
                LinearLayout.HORIZONTAL
        );

        verify.setGravity(
                Gravity.CENTER_VERTICAL
        );

        verify.setPadding(
                dp(12),
                dp(12),
                dp(12),
                dp(12)
        );

        GradientDrawable verifyBg =
                new GradientDrawable();

        verifyBg.setColor(
                Color.rgb(255, 249, 230)
        );

        verifyBg.setCornerRadius(
                dp(18)
        );

        verifyBg.setStroke(
                dp(1),
                Color.rgb(245, 220, 150)
        );

        verify.setBackground(
                verifyBg
        );

        // ---------------------------------------------------------
        // SHIELD
        // ---------------------------------------------------------

        TextView shield =
                new TextView(requireContext());

        shield.setText(
                "🛡"
        );

        shield.setTextSize(
                screenWidth() < dp(360)
                        ? 22
                        : 26
        );

        shield.setGravity(
                Gravity.CENTER
        );

        verify.addView(
                shield,
                new LinearLayout.LayoutParams(
                        dp(40),
                        -2
                )
        );

        // ---------------------------------------------------------
        // VERIFY TEXT
        // ---------------------------------------------------------

        LinearLayout verifyText =
                new LinearLayout(requireContext());

        verifyText.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView verifyTitle =
                new TextView(requireContext());

        verifyTitle.setText(
                "Verify your account"
        );

        verifyTitle.setTextSize(
                screenWidth() < dp(360)
                        ? 15
                        : 17
        );

        verifyTitle.setTypeface(
                null,
                Typeface.BOLD
        );

        verifyTitle.setTextColor(
                ORANGE
        );

        verifyTitle.setMaxLines(1);

        verifyTitle.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        verifyText.addView(
                verifyTitle
        );

        TextView verifySub =
                new TextView(requireContext());

        verifySub.setText(
                "Get verified to build trust and increase response rates."
        );

        verifySub.setTextSize(
                screenWidth() < dp(360)
                        ? 11
                        : 13
        );

        verifySub.setTextColor(
                Color.rgb(150, 110, 40)
        );

        verifySub.setMaxLines(2);

        verifySub.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        verifyText.addView(
                verifySub
        );

        LinearLayout.LayoutParams verifyTextParams =
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                );

        verifyTextParams.setMargins(
                dp(6),
                0,
                dp(6),
                0
        );

        verify.addView(
                verifyText,
                verifyTextParams
        );

        // ---------------------------------------------------------
        // VERIFY BUTTON
        // ---------------------------------------------------------

        TextView verifyButton =
                new TextView(requireContext());

        verifyButton.setText(
                "Verify"
        );

        verifyButton.setTextSize(
                screenWidth() < dp(360)
                        ? 12
                        : 14
        );

        verifyButton.setTypeface(
                null,
                Typeface.BOLD
        );

        verifyButton.setTextColor(
                ORANGE
        );

        verifyButton.setGravity(
                Gravity.CENTER
        );

        verifyButton.setPadding(
                dp(14),
                dp(10),
                dp(14),
                dp(10)
        );

        GradientDrawable verifyButtonBg =
                new GradientDrawable();

        verifyButtonBg.setColor(
                Color.rgb(255, 230, 180)
        );

        verifyButtonBg.setCornerRadius(
                dp(30)
        );

        verifyButton.setBackground(
                verifyButtonBg
        );

        verify.addView(
                verifyButton,
                new LinearLayout.LayoutParams(
                        -2,
                        -2
                )
        );

        LinearLayout.LayoutParams verifyParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        verifyParams.setMargins(
                0,
                dp(20),
                0,
                0
        );

        content.addView(
                verify,
                verifyParams
        );

        // =========================================================
        // TITLE
        // =========================================================

        TextView title =
                new TextView(requireContext());

        title.setText(
                "Buy & Sell UPSC Essentials"
        );


        title.setTextSize(
                screenWidth() < dp(360)
                        ? 23
                        : screenWidth() < dp(600)
                          ? 27
                          : 30
        );

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setTextColor(
                DARK
        );

        title.setMaxLines(2);
        title.setGravity(
                Gravity.CENTER
        );
        title.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        titleParams.setMargins(
                0,
                dp(25),
                0,
                0
        );

        content.addView(
                title,
                titleParams
        );

        // =========================================================
        // SUBTITLE
        // =========================================================

        TextView subtitle =
                new TextView(requireContext());

        subtitle.setText(
                "Books • Furniture • Electronics • Accessories\n" +
                        "Zero Commission • Direct Chat"
        );

        subtitle.setTextSize(
                screenWidth() < dp(360)
                        ? 13
                        : 15
        );

        subtitle.setTextColor(
                Color.rgb(140, 140, 140)
        );

        subtitle.setGravity(Gravity.CENTER);
        subtitle.setMaxLines(3);

        content.addView(
                subtitle,
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                )
        );

        // =========================================================
        // QUICK CARDS
        // =========================================================

        LinearLayout quickRow =
                new LinearLayout(requireContext());

        quickRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        quickRow.setGravity(
                Gravity.CENTER
        );

        LinearLayout servicesCard =
                quickCard(
                        "🛠",
                        "Services",
                        "Tap to explore",
                        Color.rgb(253, 236, 217),
                        ORANGE
                );

        servicesCard.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    requireContext(),
                                    ServicesActivity.class
                            );

                    startActivity(intent);
                }
        );

        LinearLayout.LayoutParams serviceParams =
                new LinearLayout.LayoutParams(
                        0,
                        dp(100),
                        1
                );

        serviceParams.setMargins(
                0,
                0,
                dp(6),
                0
        );

        quickRow.addView(
                servicesCard,
                serviceParams
        );

        LinearLayout aspirantsCard =
                quickCard(
                        "👥",
                        "1,600+",
                        "Aspirants",
                        Color.rgb(224, 240, 253),
                        Color.rgb(35, 120, 230)
                );

        LinearLayout.LayoutParams aspirantsParams =
                new LinearLayout.LayoutParams(
                        0,
                        dp(100),
                        1
                );

        aspirantsParams.setMargins(
                dp(6),
                0,
                0,
                0
        );

        quickRow.addView(
                aspirantsCard,
                aspirantsParams
        );

        LinearLayout.LayoutParams quickParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(100)
                );

        quickParams.setMargins(
                0,
                dp(25),
                0,
                0
        );

        content.addView(
                quickRow,
                quickParams
        );

        // =========================================================
        // SEARCH
        // =========================================================

        LinearLayout searchRow =
                new LinearLayout(requireContext());

        searchRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        // ---------------------------------------------------------
        // SEARCH BOX
        // ---------------------------------------------------------

        LinearLayout searchBox =
                new LinearLayout(requireContext());

        searchBox.setGravity(
                Gravity.CENTER_VERTICAL
        );

        searchBox.setPadding(
                dp(12),
                dp(3),
                dp(12),
                dp(3)
        );

        GradientDrawable searchBg =
                new GradientDrawable();

        searchBg.setColor(
                Color.WHITE
        );

        searchBg.setCornerRadius(
                dp(30)
        );

        searchBg.setStroke(
                dp(1),
                Color.rgb(225, 225, 225)
        );

        searchBox.setBackground(
                searchBg
        );

        TextView searchIcon =
                new TextView(requireContext());

        searchIcon.setText(
                "🔍"
        );

        searchIcon.setTextSize(
                17
        );

        searchBox.addView(
                searchIcon,
                new LinearLayout.LayoutParams(
                        dp(32),
                        -2
                )
        );

        search =
                new EditText(requireContext());

        search.setHint(
                "Search books, notes..."
        );

        search.setTextSize(
                screenWidth() < dp(360)
                        ? 13
                        : 15
        );

        search.setSingleLine(true);

        search.setBackground(null);

        search.setPadding(
                0,
                0,
                0,
                0
        );

        search.setMaxLines(1);

        searchBox.addView(
                search,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        searchRow.addView(
                searchBox,
                new LinearLayout.LayoutParams(
                        0,
                        dp(55),
                        1
                )
        );

        // ---------------------------------------------------------
        // CAMERA
        // ---------------------------------------------------------

        TextView camera =
                new TextView(requireContext());

        camera.setText(
                "📷"
        );

        camera.setTextSize(
                20
        );

        camera.setGravity(
                Gravity.CENTER
        );

        GradientDrawable cameraBg =
                new GradientDrawable();

        cameraBg.setShape(
                GradientDrawable.OVAL
        );

        cameraBg.setColor(
                Color.WHITE
        );

        cameraBg.setStroke(
                dp(1),
                Color.rgb(225, 225, 225)
        );

        camera.setBackground(
                cameraBg
        );

        LinearLayout.LayoutParams cameraParams =
                new LinearLayout.LayoutParams(
                        dp(55),
                        dp(55)
                );

        cameraParams.setMargins(
                dp(8),
                0,
                0,
                0
        );

        searchRow.addView(
                camera,
                cameraParams
        );

        LinearLayout.LayoutParams searchParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(55)
                );

        searchParams.setMargins(
                0,
                dp(20),
                0,
                0
        );

        content.addView(
                searchRow,
                searchParams
        );

        // =========================================================
        // CATEGORY CHIPS
        // =========================================================

        HorizontalScrollView categoriesScroll =
                new HorizontalScrollView(
                        requireContext()
                );

        categoriesScroll.setHorizontalScrollBarEnabled(
                false
        );

        categoriesScroll.setClipToPadding(
                false
        );

        LinearLayout categories =
                new LinearLayout(
                        requireContext()
                );

        categories.setOrientation(
                LinearLayout.HORIZONTAL
        );

        String[] categoryNames = {
                "All",
                "Furniture",
                "Appliances",
                "Electronics",
                "Books"
        };

        final List<TextView> categoryChips =
                new ArrayList<>();

        for (
                int i = 0;
                i < categoryNames.length;
                i++
        ) {

            TextView chip =
                    new TextView(
                            requireContext()
                    );

            chip.setText(
                    categoryNames[i]
            );

            chip.setTextSize(
                    screenWidth() < dp(360)
                            ? 13
                            : 15
            );

            chip.setGravity(
                    Gravity.CENTER
            );

            chip.setSingleLine(true);

            chip.setPadding(
                    dp(18),
                    dp(10),
                    dp(18),
                    dp(10)
            );

            LinearLayout.LayoutParams chipParams =
                    new LinearLayout.LayoutParams(
                            -2,
                            -2
                    );

            chipParams.setMargins(
                    0,
                    0,
                    dp(8),
                    0
            );

            categories.addView(
                    chip,
                    chipParams
            );

            categoryChips.add(
                    chip
            );

            final String category =
                    categoryNames[i];

            chip.setOnClickListener(
                    v -> {

                        selectedCategory =
                                category;

                        for (
                                TextView other :
                                categoryChips
                        ) {

                            setChipNormal(
                                    other
                            );
                        }

                        setChipSelected(
                                chip
                        );

                        if (listingAdapter != null) {

                            listingAdapter.search(
                                    search.getText()
                                            .toString(),
                                    selectedCategory,
                                    selectedArea
                            );

                            updateResultCount();
                        }
                    }
            );

            if (i == 0) {

                setChipSelected(
                        chip
                );

            } else {

                setChipNormal(
                        chip
                );
            }
        }

        categoriesScroll.addView(
                categories
        );

        LinearLayout.LayoutParams categoriesParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        categoriesParams.setMargins(
                0,
                dp(20),
                0,
                0
        );

        content.addView(
                categoriesScroll,
                categoriesParams
        );

        // =========================================================
        // RESULT COUNT
        // =========================================================

        resultText =
                new TextView(
                        requireContext()
                );

        resultText.setText(
                "6 Results Found"
        );

        resultText.setTextSize(
                screenWidth() < dp(360)
                        ? 16
                        : 18
        );

        resultText.setTypeface(
                null,
                Typeface.BOLD
        );

        resultText.setTextColor(
                DARK
        );

        LinearLayout.LayoutParams resultParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        resultParams.setMargins(
                0,
                dp(22),
                0,
                dp(12)
        );

        content.addView(
                resultText,
                resultParams
        );

        // =========================================================
        // RECYCLER VIEW
        // =========================================================

        RecyclerView recyclerView =
                new RecyclerView(
                        requireContext()
                );

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(
                        requireContext(),
                        2
                );

        recyclerView.setLayoutManager(
                gridLayoutManager
        );

        recyclerView.setNestedScrollingEnabled(
                false
        );

        recyclerView.setClipToPadding(
                false
        );

        // Responsive product spacing
        int gridSpacing =
                screenWidth() < dp(360)
                        ? dp(6)
                        : dp(8);

        recyclerView.setPadding(
                0,
                0,
                0,
                dp(10)
        );

        // =========================================================
        // LISTINGS
        // =========================================================

        List<ListingItem> listings =
                new ArrayList<>();

        listings.add(
                new ListingItem(
                        "Table Fan - Working",
                        "₹450",
                        "Good",
                        "Appliances",
                        "Old Rajinder Nagar",
                        "High speed table fan in good working condition.",
                        "9000000001",
                        R.drawable.fan
                )
        );

        listings.add(
                new ListingItem(
                        "Room Cooler",
                        "₹1,200",
                        "Good",
                        "Appliances",
                        "Mukherjee Nagar",
                        "Room cooler in good working condition.",
                        "9000000002",
                        R.drawable.cooler
                )
        );

        listings.add(
                new ListingItem(
                        "Study Table + Chair",
                        "₹1,800",
                        "Good",
                        "Furniture",
                        "Mukherjee Nagar",
                        "Study table with chair. Perfect for UPSC preparation.",
                        "9000000003",
                        R.drawable.office_chair
                )
        );

        listings.add(
                new ListingItem(
                        "UPSC Polity Book Set",
                        "₹600",
                        "New",
                        "Books",
                        "GTB Nagar",
                        "UPSC Polity books in excellent condition.",
                        "9000000004",
                        R.drawable.books
                )
        );

        listings.add(
                new ListingItem(
                        "Desk Lamp",
                        "₹250",
                        "Good",
                        "Electronics",
                        "Old Rajinder Nagar",
                        "LED desk lamp suitable for night study.",
                        "9000000005",
                        R.drawable.study_table
                )
        );

        listings.add(
                new ListingItem(
                        "Bookshelf",
                        "₹900",
                        "Good",
                        "Furniture",
                        "Wazirabad",
                        "Wooden bookshelf in good condition.",
                        "9000000006",
                        R.drawable.bookshelf
                )
        );

        // =========================================================
        // POSTED ITEMS (from Post tab)
        // =========================================================

        List<ListingItem> postedItems =
                PostRepository.getItems(
                        requireContext()
                );

        // Show most recently posted items first.
        java.util.Collections.reverse(
                postedItems
        );

        listings.addAll(
                0,
                postedItems
        );

        // =========================================================
        // ADAPTER
        // =========================================================

        listingAdapter =
                new ListingAdapter(
                        requireContext(),
                        listings
                );

        recyclerView.setAdapter(
                listingAdapter
        );

        // =========================================================
        // GRID SPACING
        // =========================================================

        recyclerView.addItemDecoration(
                new RecyclerView.ItemDecoration() {

                    @Override
                    public void getItemOffsets(
                            @NonNull android.graphics.Rect outRect,
                            @NonNull View view,
                            @NonNull RecyclerView parent,
                            @NonNull RecyclerView.State state
                    ) {

                        int position =
                                parent.getChildAdapterPosition(
                                        view
                                );

                        if (position == RecyclerView.NO_POSITION) {
                            return;
                        }

                        int column =
                                position % 2;

                        int halfSpacing =
                                gridSpacing / 2;

                        if (column == 0) {

                            outRect.left =
                                    0;

                            outRect.right =
                                    halfSpacing;

                        } else {

                            outRect.left =
                                    halfSpacing;

                            outRect.right =
                                    0;
                        }

                        outRect.bottom =
                                gridSpacing;
                    }
                }
        );

        content.addView(
                recyclerView,
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                )
        );

        // =========================================================
        // INITIAL RESULT COUNT
        // =========================================================

        updateResultCount();

        // =========================================================
        // SEARCH
        // =========================================================

        search.setOnEditorActionListener(
                (v, actionId, event) -> {

                    listingAdapter.search(
                            search.getText()
                                    .toString(),
                            selectedCategory,
                            selectedArea
                    );

                    updateResultCount();

                    return false;
                }
        );

        // =========================================================
        // LIVE SEARCH
        // =========================================================

        search.addTextChangedListener(
                new android.text.TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after
                    ) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count
                    ) {

                        if (listingAdapter != null) {

                            listingAdapter.search(
                                    s.toString(),
                                    selectedCategory,
                                    selectedArea
                            );

                            updateResultCount();
                        }
                    }

                    @Override
                    public void afterTextChanged(
                            android.text.Editable s
                    ) {
                    }
                }
        );

        // =========================================================
        // SYSTEM INSETS
        // =========================================================

        ViewCompat.setOnApplyWindowInsetsListener(
                scroll,
                (v, insets) -> {

                    Insets bars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    int horizontal =
                            sidePadding();

                    content.setPadding(
                            horizontal,
                            dp(15) + bars.top,
                            horizontal,
                            dp(120) + bars.bottom
                    );

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(
                scroll
        );

        return scroll;
    }

    // =============================================================
    // CHIP SELECTED
    // =============================================================

    private void setChipSelected(
            TextView chip
    ) {

        GradientDrawable selectedBg =
                new GradientDrawable();

        selectedBg.setColor(
                ORANGE
        );

        selectedBg.setCornerRadius(
                dp(30)
        );

        chip.setBackground(
                selectedBg
        );

        chip.setTextColor(
                Color.WHITE
        );
    }

    // =============================================================
    // CHIP NORMAL
    // =============================================================

    private void setChipNormal(
            TextView chip
    ) {

        GradientDrawable normalBg =
                new GradientDrawable();

        normalBg.setColor(
                Color.WHITE
        );

        normalBg.setCornerRadius(
                dp(30)
        );

        normalBg.setStroke(
                dp(1),
                Color.rgb(
                        220,
                        220,
                        220
                )
        );

        chip.setBackground(
                normalBg
        );

        chip.setTextColor(
                Color.rgb(
                        60,
                        60,
                        60
                )
        );
    }

    // =============================================================
    // UPDATE RESULT COUNT
    // =============================================================

    private void updateResultCount() {

        if (
                listingAdapter != null
                        && resultText != null
        ) {

            resultText.setText(
                    listingAdapter.getItemCount()
                            + " Results Found"
            );
        }
    }

    // =============================================================
    // AREA MENU
    // =============================================================

    private void showAreaMenu() {

        final Dialog dialog =
                new Dialog(
                        requireContext()
                );

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        LinearLayout root =
                new LinearLayout(
                        requireContext()
                );

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setBackground(
                roundedBackground(
                        Color.WHITE,
                        dp(25)
                )
        );

        // =========================================================
        // HEADER
        // =========================================================

        LinearLayout header =
                new LinearLayout(
                        requireContext()
                );

        header.setGravity(
                Gravity.CENTER_VERTICAL
        );

        header.setPadding(
                dp(15),
                dp(8),
                dp(8),
                dp(8)
        );

        TextView icon =
                new TextView(
                        requireContext()
                );

        icon.setText(
                "📍"
        );

        icon.setTextSize(
                24
        );

        icon.setGravity(
                Gravity.CENTER
        );

        header.addView(
                icon,
                new LinearLayout.LayoutParams(
                        dp(42),
                        dp(52)
                )
        );

        TextView title =
                new TextView(
                        requireContext()
                );

        title.setText(
                "Select Your Area"
        );

        title.setTextSize(
                screenWidth() < dp(360)
                        ? 18
                        : 21
        );

        title.setTextColor(
                DARK
        );

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setMaxLines(1);

        title.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        header.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        dp(52),
                        1
                )
        );

        TextView close =
                new TextView(
                        requireContext()
                );

        close.setText(
                "×"
        );

        close.setTextSize(
                30
        );

        close.setGravity(
                Gravity.CENTER
        );

        header.addView(
                close,
                new LinearLayout.LayoutParams(
                        dp(45),
                        dp(52)
                )
        );

        root.addView(
                header
        );

        // =========================================================
        // DIVIDER
        // =========================================================

        View divider =
                new View(
                        requireContext()
                );

        divider.setBackgroundColor(
                Color.rgb(
                        230,
                        230,
                        230
                )
        );

        root.addView(
                divider,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(1)
                )
        );

        // =========================================================
        // AREA LIST
        // =========================================================

        ScrollView areaScroll =
                new ScrollView(
                        requireContext()
                );

        areaScroll.setFillViewport(
                true
        );

        LinearLayout areaList =
                new LinearLayout(
                        requireContext()
                );

        areaList.setOrientation(
                LinearLayout.VERTICAL
        );

        String[] areas = {
                "All Areas",
                "Old Rajinder Nagar",
                "Mukherjee Nagar",
                "Wazirabad",
                "Karol Bagh",
                "GTB Nagar",
                "Patel Nagar",
                "Laxmi Nagar",
                "South Delhi"
        };

        for (String area : areas) {

            TextView areaView =
                    new TextView(
                            requireContext()
                    );

            areaView.setText(
                    area
            );

            areaView.setTextSize(
                    screenWidth() < dp(360)
                            ? 15
                            : 17
            );

            areaView.setTextColor(
                    Color.rgb(
                            40,
                            40,
                            40
                    )
            );

            areaView.setGravity(
                    Gravity.CENTER_VERTICAL
            );

            areaView.setSingleLine(
                    true
            );

            areaView.setEllipsize(
                    android.text.TextUtils.TruncateAt.END
            );

            areaView.setPadding(
                    dp(22),
                    0,
                    dp(20),
                    0
            );

            areaList.addView(
                    areaView,
                    new LinearLayout.LayoutParams(
                            -1,
                            dp(58)
                    )
            );

            areaView.setOnClickListener(
                    v -> {

                        selectedArea =
                                area;

                        locationText.setText(
                                "📍  " + area
                        );

                        listingAdapter.search(
                                search.getText()
                                        .toString(),
                                selectedCategory,
                                selectedArea
                        );

                        updateResultCount();

                        dialog.dismiss();
                    }
            );
        }

        areaScroll.addView(
                areaList
        );

        root.addView(
                areaScroll,
                new LinearLayout.LayoutParams(
                        -1,
                        0,
                        1
                )
        );

        close.setOnClickListener(
                v -> dialog.dismiss()
        );

        dialog.setContentView(
                root
        );

        dialog.show();

        // =========================================================
        // RESPONSIVE DIALOG
        // =========================================================

        Window window =
                dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawable(
                    roundedBackground(
                            Color.WHITE,
                            dp(25)
                    )
            );

            int width =
                    screenWidth();

            int dialogWidth;

            if (width < dp(400)) {

                dialogWidth =
                        width;

            } else if (width < dp(700)) {

                dialogWidth =
                        (int) (
                                width * 0.94f
                        );

            } else {

                dialogWidth =
                        Math.min(
                                width - dp(60),
                                dp(600)
                        );
            }

            int screenHeight =
                    requireContext()
                            .getResources()
                            .getDisplayMetrics()
                            .heightPixels;

            int dialogHeight =
                    (int) (
                            screenHeight * 0.72f
                    );

            window.setLayout(
                    dialogWidth,
                    dialogHeight
            );

            window.setGravity(
                    Gravity.BOTTOM
            );
        }
    }

    // =============================================================
    // QUICK CARD
    // =============================================================

    private LinearLayout quickCard(
            String icon,
            String main,
            String sub,
            int bgColor,
            int accentColor
    ) {

        LinearLayout card =
                new LinearLayout(
                        requireContext()
                );

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                Gravity.CENTER
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                bgColor
        );

        bg.setCornerRadius(
                dp(18)
        );

        card.setBackground(
                bg
        );

        // ---------------------------------------------------------
        // ICON + MAIN
        // ---------------------------------------------------------

        LinearLayout row =
                new LinearLayout(
                        requireContext()
                );

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER
        );

        TextView iconView =
                new TextView(
                        requireContext()
                );

        iconView.setText(
                icon
        );

        iconView.setTextSize(
                screenWidth() < dp(360)
                        ? 19
                        : 22
        );

        iconView.setTextColor(
                accentColor
        );

        iconView.setGravity(
                Gravity.CENTER
        );

        row.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        -2,
                        -2
                )
        );

        TextView mainView =
                new TextView(
                        requireContext()
                );

        mainView.setText(
                main
        );

        mainView.setTextSize(
                screenWidth() < dp(360)
                        ? 15
                        : 17
        );

        mainView.setTypeface(
                null,
                Typeface.BOLD
        );

        mainView.setGravity(
                Gravity.CENTER
        );

        mainView.setSingleLine(
                true
        );

        mainView.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        LinearLayout.LayoutParams mainParams =
                new LinearLayout.LayoutParams(
                        -2,
                        -2
                );

        mainParams.setMargins(
                dp(5),
                0,
                0,
                0
        );

        row.addView(
                mainView,
                mainParams
        );

        card.addView(
                row
        );

        // ---------------------------------------------------------
        // SUBTITLE
        // ---------------------------------------------------------

        TextView subView =
                new TextView(
                        requireContext()
                );

        subView.setText(
                sub
        );

        subView.setTextSize(
                screenWidth() < dp(360)
                        ? 11
                        : 13
        );

        subView.setTextColor(
                Color.rgb(
                        120,
                        120,
                        120
                )
        );

        subView.setGravity(
                Gravity.CENTER
        );

        subView.setSingleLine(
                true
        );

        subView.setEllipsize(
                android.text.TextUtils.TruncateAt.END
        );

        LinearLayout.LayoutParams subParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        subParams.setMargins(
                dp(5),
                dp(3),
                dp(5),
                0
        );

        card.addView(
                subView,
                subParams
        );

        return card;
    }

    // =============================================================
    // ROUNDED BACKGROUND
    // =============================================================

    private GradientDrawable roundedBackground(
            int color,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                color
        );

        drawable.setCornerRadius(
                radius
        );

        return drawable;
    }
}