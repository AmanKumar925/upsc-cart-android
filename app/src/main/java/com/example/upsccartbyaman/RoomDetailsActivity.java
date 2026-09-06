package com.example.upsccartbyaman;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class RoomDetailsActivity extends AppCompatActivity {

    private final int ORANGE =
            Color.rgb(242, 100, 31);

    private final int DARK =
            Color.rgb(30, 30, 30);

    private final int GRAY =
            Color.rgb(110, 110, 110);

    private final int LIGHT_ORANGE =
            Color.rgb(255, 244, 229);

    private final int BORDER =
            Color.rgb(232, 232, 232);

    private LinearLayout toolbar;
    private ScrollView scrollView;
    private LinearLayout contentContainer;
    private TextView messageButton;

    // ------------------------------------------------------------
    // ROOM DATA
    // ------------------------------------------------------------

    private int image;
    private String imageUri;

    private String title;
    private String price;
    private String location;
    private String roomType;
    private String rentType;
    private String gender;
    private String postedTime;
    private String description;
    private String phone;

    private boolean hasVideo;

    private String[] amenities;

    private ImageView roomImage;

    // ============================================================
    // ON CREATE
    // ============================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        WindowCompat.setDecorFitsSystemWindows(
                getWindow(),
                false
        );

        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(
                        getWindow(),
                        getWindow().getDecorView()
                );

        controller.setAppearanceLightStatusBars(true);
        controller.setAppearanceLightNavigationBars(true);

        readIntentData();

        buildScreen();

        setupWindowInsets();
    }

    // ============================================================
    // READ DATA
    // ============================================================

    private void readIntentData() {

        Intent intent = getIntent();

        // IMPORTANT:
        // RoomsFragment sends the drawable resource ID as INTEGER.
        image = intent.getIntExtra("image", 0);
        imageUri = intent.getStringExtra("imageUri");

        // Fallback image
        if (image == 0) {
            image = R.drawable.room1;
        }

        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        location = intent.getStringExtra("location");
        roomType = intent.getStringExtra("roomType");
        rentType = intent.getStringExtra("rentType");
        gender = intent.getStringExtra("gender");
        postedTime = intent.getStringExtra("postedTime");
        description = intent.getStringExtra("description");
        phone = intent.getStringExtra("phone");

        hasVideo =
                intent.getBooleanExtra(
                        "hasVideo",
                        false
                );

        amenities =
                intent.getStringArrayExtra("amenities");

        if (TextUtils.isEmpty(title)) {
            title = "Room Details";
        }

        if (TextUtils.isEmpty(price)) {
            price = "Price not available";
        }

        if (TextUtils.isEmpty(location)) {
            location = "Location not available";
        }

        if (TextUtils.isEmpty(roomType)) {
            roomType = "Room";
        }

        if (TextUtils.isEmpty(rentType)) {
            rentType = "Rent";
        }

        if (TextUtils.isEmpty(gender)) {
            gender = "Any";
        }

        if (TextUtils.isEmpty(description)) {
            description =
                    "No description available.";
        }

        if (amenities == null) {
            amenities = new String[0];
        }
    }

    // ============================================================
    // BUILD SCREEN
    // ============================================================

    private void buildScreen() {

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setBackgroundColor(Color.WHITE);

        setContentView(root);

        // --------------------------------------------------------
        // TOOLBAR
        // --------------------------------------------------------

        toolbar =
                new LinearLayout(this);

        toolbar.setOrientation(
                LinearLayout.HORIZONTAL
        );

        toolbar.setGravity(Gravity.CENTER_VERTICAL);

        toolbar.setPadding(
                dp(8),
                0,
                dp(8),
                0
        );

        toolbar.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams toolbarParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(56)
                );

        root.addView(
                toolbar,
                toolbarParams
        );

        // Back button
        ImageButton backButton =
                createIconButton("‹");

        toolbar.addView(
                backButton,
                new LinearLayout.LayoutParams(
                        dp(46),
                        dp(46)
                )
        );

        backButton.setOnClickListener(
                v -> finish()
        );

        // Toolbar title
        TextView toolbarTitle =
                new TextView(this);

        toolbarTitle.setText("Room Details");
        toolbarTitle.setTextSize(18);
        toolbarTitle.setTextColor(DARK);
        toolbarTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        toolbarTitle.setGravity(
                Gravity.CENTER_VERTICAL
        );

        LinearLayout.LayoutParams toolbarTitleParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1f
                );

        toolbar.addView(
                toolbarTitle,
                toolbarTitleParams
        );

        // More button
        ImageButton moreButton =
                createIconButton("⋮");

        toolbar.addView(
                moreButton,
                new LinearLayout.LayoutParams(
                        dp(46),
                        dp(46)
                )
        );

        // --------------------------------------------------------
        // SCROLL VIEW
        // --------------------------------------------------------

        scrollView =
                new ScrollView(this);

        scrollView.setFillViewport(true);

        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                );

        root.addView(
                scrollView,
                scrollParams
        );

        contentContainer =
                new LinearLayout(this);

        contentContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        contentContainer.setPadding(
                dp(14),
                dp(8),
                dp(14),
                dp(14)
        );

        scrollView.addView(
                contentContainer,
                new ScrollView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // --------------------------------------------------------
        // MEDIA
        // --------------------------------------------------------

        createMediaSection();

        // --------------------------------------------------------
        // TITLE
        // --------------------------------------------------------

        TextView titleView =
                new TextView(this);

        titleView.setText(title);
        titleView.setTextSize(22);
        titleView.setTextColor(DARK);
        titleView.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        titleView.setLineSpacing(
                0,
                1.05f
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        titleParams.topMargin = dp(16);

        contentContainer.addView(
                titleView,
                titleParams
        );

        // --------------------------------------------------------
        // PRICE
        // --------------------------------------------------------

        TextView priceView =
                new TextView(this);

        priceView.setText(price + " / month");
        priceView.setTextSize(20);
        priceView.setTextColor(ORANGE);
        priceView.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        LinearLayout.LayoutParams priceParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        priceParams.topMargin = dp(6);

        contentContainer.addView(
                priceView,
                priceParams
        );

        // --------------------------------------------------------
        // LOCATION
        // --------------------------------------------------------

        TextView locationView =
                new TextView(this);

        locationView.setText(
                "📍 " + location
        );

        locationView.setTextSize(14);
        locationView.setTextColor(GRAY);

        LinearLayout.LayoutParams locationParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        locationParams.topMargin = dp(5);

        contentContainer.addView(
                locationView,
                locationParams
        );

        // --------------------------------------------------------
        // DETAILS CHIPS
        // --------------------------------------------------------

        HorizontalScrollView detailsScroll =
                new HorizontalScrollView(this);

        detailsScroll.setHorizontalScrollBarEnabled(false);

        LinearLayout detailsRow =
                new LinearLayout(this);

        detailsRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        detailsRow.setPadding(
                0,
                dp(14),
                0,
                dp(4)
        );

        addDetailChip(
                detailsRow,
                roomType
        );

        addDetailChip(
                detailsRow,
                rentType
        );

        addDetailChip(
                detailsRow,
                gender
        );

        if (!TextUtils.isEmpty(postedTime)) {
            addDetailChip(
                    detailsRow,
                    postedTime
            );
        }

        detailsScroll.addView(detailsRow);

        contentContainer.addView(
                detailsScroll,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(60)
                )
        );

        // --------------------------------------------------------
        // ADMIN CARD
        // --------------------------------------------------------

        createAdminCard();

        // --------------------------------------------------------
        // AMENITIES
        // --------------------------------------------------------

        createAmenitiesSection();

        // --------------------------------------------------------
        // DESCRIPTION
        // --------------------------------------------------------

        TextView descriptionTitle =
                createSectionTitle("Description");

        LinearLayout.LayoutParams descriptionTitleParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        descriptionTitleParams.topMargin = dp(20);

        contentContainer.addView(
                descriptionTitle,
                descriptionTitleParams
        );

        TextView descriptionView =
                new TextView(this);

        descriptionView.setText(description);
        descriptionView.setTextSize(15.5f);
        descriptionView.setTextColor(GRAY);
        descriptionView.setLineSpacing(
                dp(3),
                1.0f
        );

        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        descriptionParams.topMargin = dp(8);

        contentContainer.addView(
                descriptionView,
                descriptionParams
        );

        // --------------------------------------------------------
        // BOTTOM CONTACT BUTTON
        // --------------------------------------------------------

        messageButton =
                new TextView(this);

        messageButton.setText(
                "☎ Contact Admin"
        );

        messageButton.setTextSize(16);
        messageButton.setTextColor(Color.WHITE);
        messageButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        messageButton.setGravity(
                Gravity.CENTER
        );

        GradientDrawable messageBg =
                new GradientDrawable();

        messageBg.setColor(ORANGE);
        messageBg.setCornerRadius(dp(14));

        messageButton.setBackground(
                messageBg
        );

        messageButton.setElevation(dp(5));

        LinearLayout.LayoutParams messageButtonParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(54)
                );

        messageButtonParams.leftMargin = dp(14);
        messageButtonParams.rightMargin = dp(14);
        messageButtonParams.topMargin = dp(8);
        messageButtonParams.bottomMargin = dp(8);

        root.addView(
                messageButton,
                messageButtonParams
        );

        messageButton.setOnClickListener(
                v -> contactAdmin()
        );
    }

    // ============================================================
    // MEDIA SECTION
    // ============================================================

    private void createMediaSection() {

        FrameLayout mediaFrame =
                new FrameLayout(this);

        int width =
                getResources()
                        .getDisplayMetrics()
                        .widthPixels;

        int targetHeight =
                Math.round(width * 0.62f);

        targetHeight =
                Math.max(
                        dp(220),
                        Math.min(
                                dp(320),
                                targetHeight
                        )
                );

        LinearLayout.LayoutParams mediaParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        targetHeight
                );

        contentContainer.addView(
                mediaFrame,
                mediaParams
        );

        // --------------------------------------------------------
        // IMAGE
        // --------------------------------------------------------

        roomImage =
                new ImageView(this);

        // Show the user's uploaded photo when this room was posted
        // from the Post tab, otherwise fall back to the sample image.
        if (imageUri != null && !imageUri.isEmpty()) {

            try {

                roomImage.setImageURI(Uri.parse(imageUri));

            } catch (Exception e) {

                roomImage.setImageResource(image);
            }

        } else {

            roomImage.setImageResource(image);
        }

        roomImage.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        GradientDrawable imageBg =
                new GradientDrawable();

        imageBg.setColor(Color.LTGRAY);
        imageBg.setCornerRadius(dp(16));

        roomImage.setBackground(
                imageBg
        );

        mediaFrame.addView(
                roomImage,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        // --------------------------------------------------------
        // PLAY BUTTON
        // --------------------------------------------------------

        if (hasVideo) {

            TextView playButton =
                    new TextView(this);

            playButton.setText("▶");
            playButton.setTextSize(27);
            playButton.setTextColor(Color.WHITE);
            playButton.setGravity(Gravity.CENTER);

            GradientDrawable playBg =
                    new GradientDrawable();

            playBg.setColor(
                    Color.argb(
                            190,
                            0,
                            0,
                            0
                    )
            );

            playBg.setShape(
                    GradientDrawable.OVAL
            );

            playButton.setBackground(playBg);

            FrameLayout.LayoutParams playParams =
                    new FrameLayout.LayoutParams(
                            dp(72),
                            dp(72),
                            Gravity.CENTER
                    );

            mediaFrame.addView(
                    playButton,
                    playParams
            );
        }

        // --------------------------------------------------------
        // HEART
        // --------------------------------------------------------

        TextView heart =
                createFloatingButton("♡");

        FrameLayout.LayoutParams heartParams =
                new FrameLayout.LayoutParams(
                        dp(44),
                        dp(44),
                        Gravity.TOP | Gravity.END
                );

        heartParams.topMargin = dp(12);
        heartParams.rightMargin = dp(12);

        mediaFrame.addView(
                heart,
                heartParams
        );

        heart.setOnClickListener(
                v -> {

                    if (heart.getText().toString().equals("♡")) {
                        heart.setText("♥");
                    } else {
                        heart.setText("♡");
                    }
                }
        );

        // --------------------------------------------------------
        // SHARE
        // --------------------------------------------------------

        TextView share =
                createFloatingButton("↗");

        FrameLayout.LayoutParams shareParams =
                new FrameLayout.LayoutParams(
                        dp(44),
                        dp(44),
                        Gravity.TOP | Gravity.END
                );

        shareParams.topMargin = dp(64);
        shareParams.rightMargin = dp(12);

        mediaFrame.addView(
                share,
                shareParams
        );

        share.setOnClickListener(
                v -> shareRoom()
        );

        // --------------------------------------------------------
        // REPORT
        // --------------------------------------------------------

        TextView report =
                createFloatingButton("!");

        FrameLayout.LayoutParams reportParams =
                new FrameLayout.LayoutParams(
                        dp(44),
                        dp(44),
                        Gravity.TOP | Gravity.END
                );

        reportParams.topMargin = dp(116);
        reportParams.rightMargin = dp(12);

        mediaFrame.addView(
                report,
                reportParams
        );

        report.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "Report option selected",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }

    // ============================================================
    // ADMIN CARD
    // ============================================================

    private void createAdminCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.HORIZONTAL
        );

        card.setGravity(
                Gravity.CENTER_VERTICAL
        );

        card.setPadding(
                dp(12),
                dp(12),
                dp(12),
                dp(12)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(LIGHT_ORANGE);
        bg.setCornerRadius(dp(14));
        bg.setStroke(dp(1), BORDER);

        card.setBackground(bg);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(80)
                );

        cardParams.topMargin = dp(12);

        contentContainer.addView(
                card,
                cardParams
        );

        // Avatar
        TextView avatar =
                new TextView(this);

        avatar.setText("A");
        avatar.setTextSize(20);
        avatar.setTextColor(Color.WHITE);
        avatar.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        avatar.setGravity(Gravity.CENTER);

        GradientDrawable avatarBg =
                new GradientDrawable();

        avatarBg.setColor(ORANGE);
        avatarBg.setShape(
                GradientDrawable.OVAL
        );

        avatar.setBackground(avatarBg);

        card.addView(
                avatar,
                new LinearLayout.LayoutParams(
                        dp(52),
                        dp(52)
                )
        );

        LinearLayout adminText =
                new LinearLayout(this);

        adminText.setOrientation(
                LinearLayout.VERTICAL
        );

        adminText.setPadding(
                dp(12),
                0,
                0,
                0
        );

        LinearLayout.LayoutParams adminTextParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1f
                );

        card.addView(
                adminText,
                adminTextParams
        );

        TextView adminTitle =
                new TextView(this);

        adminTitle.setText("Room Admin");
        adminTitle.setTextSize(15);
        adminTitle.setTextColor(DARK);
        adminTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        adminText.addView(adminTitle);

        TextView adminSubtitle =
                new TextView(this);

        adminSubtitle.setText(
                "Verified room listing"
        );

        adminSubtitle.setTextSize(12);
        adminSubtitle.setTextColor(GRAY);

        adminText.addView(
                adminSubtitle
        );

        TextView call =
                new TextView(this);

        call.setText("Call");
        call.setTextSize(13);
        call.setTextColor(ORANGE);
        call.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        call.setGravity(Gravity.CENTER);

        GradientDrawable callBg =
                new GradientDrawable();

        callBg.setColor(Color.WHITE);
        callBg.setCornerRadius(dp(10));
        callBg.setStroke(
                dp(1),
                ORANGE
        );

        call.setBackground(callBg);

        card.addView(
                call,
                new LinearLayout.LayoutParams(
                        dp(64),
                        dp(40)
                )
        );

        call.setOnClickListener(
                v -> contactAdmin()
        );
    }

    // ============================================================
    // AMENITIES
    // ============================================================

    private void createAmenitiesSection() {

        TextView title =
                createSectionTitle("Amenities");

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        titleParams.topMargin = dp(20);

        contentContainer.addView(
                title,
                titleParams
        );

        HorizontalScrollView horizontalScroll =
                new HorizontalScrollView(this);

        horizontalScroll.setHorizontalScrollBarEnabled(false);

        LinearLayout amenitiesRow =
                new LinearLayout(this);

        amenitiesRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        amenitiesRow.setPadding(
                0,
                dp(8),
                0,
                0
        );

        for (String amenity : amenities) {

            if (!TextUtils.isEmpty(amenity)) {

                addAmenityChip(
                        amenitiesRow,
                        amenity
                );
            }
        }

        horizontalScroll.addView(
                amenitiesRow
        );

        contentContainer.addView(
                horizontalScroll,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(55)
                )
        );
    }

    // ============================================================
    // WINDOW INSETS
    // ============================================================

    private void setupWindowInsets() {

        View root =
                findViewById(
                        android.R.id.content
                );

        ViewCompat.setOnApplyWindowInsetsListener(
                root,
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    // Toolbar top inset
                    ViewGroup.LayoutParams toolbarParams =
                            toolbar.getLayoutParams();

                    toolbarParams.height =
                            dp(56) + systemBars.top;

                    toolbar.setLayoutParams(
                            toolbarParams
                    );

                    toolbar.setPadding(
                            dp(8),
                            systemBars.top,
                            dp(8),
                            0
                    );

                    // Bottom navigation inset
                    ViewGroup.MarginLayoutParams messageParams =
                            (ViewGroup.MarginLayoutParams)
                                    messageButton.getLayoutParams();

                    messageParams.bottomMargin =
                            dp(8) + systemBars.bottom;

                    messageButton.setLayoutParams(
                            messageParams
                    );

                    contentContainer.setPadding(
                            dp(14),
                            dp(8),
                            dp(14),
                            dp(12)
                    );

                    return insets;
                }
        );
    }

    // ============================================================
    // SHARE
    // ============================================================

    private void shareRoom() {

        String shareText =
                title
                        + "\n\n"
                        + price
                        + " / month"
                        + "\n"
                        + location
                        + "\n"
                        + roomType;

        Intent shareIntent =
                new Intent(
                        Intent.ACTION_SEND
                );

        shareIntent.setType(
                "text/plain"
        );

        shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                shareText
        );

        startActivity(
                Intent.createChooser(
                        shareIntent,
                        "Share Room"
                )
        );
    }

    // ============================================================
    // CONTACT
    // ============================================================

    private void contactAdmin() {

        if (TextUtils.isEmpty(phone)) {

            Toast.makeText(
                    this,
                    "Phone number not available",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            Intent intent =
                    new Intent(
                            Intent.ACTION_DIAL
                    );

            intent.setData(
                    Uri.parse(
                            "tel:" + phone
                    )
            );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Unable to open dialer",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // ============================================================
    // UI HELPERS
    // ============================================================

    private TextView createSectionTitle(
            String text
    ) {

        TextView title =
                new TextView(this);

        title.setText(text);
        title.setTextSize(18);
        title.setTextColor(DARK);
        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        return title;
    }

    private ImageButton createIconButton(
            String symbol
    ) {

        ImageButton button =
                new ImageButton(this);

        button.setImageDrawable(
                new android.graphics.drawable.ColorDrawable(
                        Color.TRANSPARENT
                )
        );

        button.setContentDescription(symbol);
        button.setBackgroundColor(
                Color.TRANSPARENT
        );

        button.setPadding(
                dp(8),
                dp(8),
                dp(8),
                dp(8)
        );

        // Using text-like symbol through content is not possible
        // directly on ImageButton, so use a TextView-style button.
        return button;
    }

    private TextView createFloatingButton(
            String text
    ) {

        TextView button =
                new TextView(this);

        button.setText(text);
        button.setTextSize(21);
        button.setTextColor(DARK);
        button.setGravity(
                Gravity.CENTER
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(Color.WHITE);
        bg.setShape(
                GradientDrawable.OVAL
        );

        bg.setStroke(
                dp(1),
                BORDER
        );

        button.setBackground(bg);
        button.setElevation(dp(3));

        return button;
    }

    private void addDetailChip(
            LinearLayout parent,
            String text
    ) {

        if (TextUtils.isEmpty(text)) {
            return;
        }

        TextView chip =
                new TextView(this);

        chip.setText(text);
        chip.setTextSize(12);
        chip.setTextColor(ORANGE);
        chip.setGravity(
                Gravity.CENTER
        );

        chip.setPadding(
                dp(12),
                0,
                dp(12),
                0
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(LIGHT_ORANGE);
        bg.setCornerRadius(dp(20));
        bg.setStroke(
                dp(1),
                Color.rgb(255, 220, 190)
        );

        chip.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(38)
                );

        params.rightMargin = dp(8);

        parent.addView(
                chip,
                params
        );
    }

    private void addAmenityChip(
            LinearLayout parent,
            String text
    ) {

        TextView chip =
                new TextView(this);

        chip.setText("✓ " + text);
        chip.setTextSize(13);
        chip.setTextColor(DARK);
        chip.setGravity(
                Gravity.CENTER
        );

        chip.setPadding(
                dp(13),
                0,
                dp(13),
                0
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(Color.WHITE);
        bg.setCornerRadius(dp(20));
        bg.setStroke(
                dp(1),
                BORDER
        );

        chip.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(40)
                );

        params.rightMargin = dp(8);

        parent.addView(
                chip,
                params
        );
    }

    private int dp(float value) {

        return Math.round(
                value
                        * getResources()
                        .getDisplayMetrics()
                        .density
        );
    }
}