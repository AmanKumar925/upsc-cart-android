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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemDetailsActivity extends AppCompatActivity {

    private final int ORANGE = Color.rgb(242, 100, 31);
    private final int DARK = Color.BLACK;
    private final int GRAY = Color.rgb(115, 115, 115);

    private LinearLayout root;
    private LinearLayout toolbar;
    private TextView chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove default ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        /*
         * Let Android handle system-bar insets.
         * This prevents status-bar overlap on different devices.
         */
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        // ---------------------------------------------------------
        // GET DATA
        // ---------------------------------------------------------

        String name = getIntent().getStringExtra("name");
        String category = getIntent().getStringExtra("category");
        String price = getIntent().getStringExtra("price");
        String condition = getIntent().getStringExtra("condition");
        String area = getIntent().getStringExtra("area");
        String description = getIntent().getStringExtra("description");
        String phone = getIntent().getStringExtra("phone");

        int image = getIntent().getIntExtra(
                "image",
                android.R.drawable.ic_menu_gallery
        );

        if (name == null) name = "Item";
        if (category == null) category = "Appliances";
        if (price == null) price = "₹2000";
        if (condition == null) condition = "Good";
        if (area == null) area = "All Areas";
        if (description == null) description = "";
        if (phone == null) phone = "";

        // Final values for lambdas
        final String finalName = name;
        final String finalPrice = price;
        final String finalPhone = phone;

        // ---------------------------------------------------------
        // ROOT
        // ---------------------------------------------------------

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        // ---------------------------------------------------------
        // TOOLBAR
        // ---------------------------------------------------------

        toolbar = new LinearLayout(this);
        toolbar.setOrientation(LinearLayout.HORIZONTAL);
        toolbar.setGravity(Gravity.CENTER_VERTICAL);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setElevation(dp(2));

        toolbar.setPadding(
                dp(4),
                0,
                dp(8),
                0
        );

        // Back button
        TextView back = new TextView(this);
        back.setText("‹");
        back.setTextSize(40);
        back.setTextColor(Color.BLACK);
        back.setGravity(Gravity.CENTER);
        back.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

        toolbar.addView(
                back,
                new LinearLayout.LayoutParams(
                        dp(52),
                        dp(56)
                )
        );

        back.setOnClickListener(v -> finish());

        // Toolbar title
        TextView toolbarTitle = new TextView(this);
        toolbarTitle.setText(finalName);
        toolbarTitle.setTextSize(19);
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarTitle.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        toolbarTitle.setGravity(Gravity.CENTER_VERTICAL);
        toolbarTitle.setSingleLine(true);
        toolbarTitle.setEllipsize(TextUtils.TruncateAt.END);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        dp(56),
                        1
                );

        toolbar.addView(toolbarTitle, titleParams);

        // More button
        TextView more = new TextView(this);
        more.setText("⋮");
        more.setTextSize(28);
        more.setTextColor(Color.BLACK);
        more.setGravity(Gravity.CENTER);
        more.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        toolbar.addView(
                more,
                new LinearLayout.LayoutParams(
                        dp(40),
                        dp(56)
                )
        );

        root.addView(
                toolbar,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(56)
                )
        );

        // ---------------------------------------------------------
        // SCROLL CONTENT
        // ---------------------------------------------------------

        ScrollView scrollView = new ScrollView(this);

        scrollView.setFillViewport(false);
        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setClipToPadding(false);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setBackgroundColor(Color.WHITE);

        scrollView.addView(
                content,
                new ScrollView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // ---------------------------------------------------------
        // IMAGE
        // ---------------------------------------------------------

        FrameLayout imageFrame = new FrameLayout(this);
        imageFrame.setBackgroundColor(Color.rgb(238, 238, 238));

        ImageView itemImage = new ImageView(this);

        itemImage.setImageResource(image);
        itemImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        itemImage.setAdjustViewBounds(true);

        imageFrame.addView(
                itemImage,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(300)
                )
        );

        // ---------------------------------------------------------
        // FLOATING BUTTONS
        // ---------------------------------------------------------

        LinearLayout floatingButtons = new LinearLayout(this);
        floatingButtons.setOrientation(LinearLayout.VERTICAL);
        floatingButtons.setGravity(Gravity.CENTER);

        FrameLayout.LayoutParams floatingParams =
                new FrameLayout.LayoutParams(
                        dp(52),
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        floatingParams.gravity =
                Gravity.END | Gravity.TOP;

        floatingParams.topMargin = dp(10);
        floatingParams.rightMargin = dp(6);

        imageFrame.addView(
                floatingButtons,
                floatingParams
        );

        TextView heart = createFloatingButton("♡");

        floatingButtons.addView(
                heart,
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(50)
                )
        );

        TextView share = createFloatingButton("↗");

        LinearLayout.LayoutParams shareParams =
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(50)
                );

        shareParams.topMargin = dp(6);

        floatingButtons.addView(
                share,
                shareParams
        );

        TextView flag = createFloatingButton("⚑");

        LinearLayout.LayoutParams flagParams =
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(50)
                );

        flagParams.topMargin = dp(6);

        floatingButtons.addView(
                flag,
                flagParams
        );

        // Heart
        heart.setOnClickListener(v -> {

            if (heart.getText().toString().equals("♡")) {

                heart.setText("♥");
                heart.setTextColor(ORANGE);

            } else {

                heart.setText("♡");
                heart.setTextColor(
                        Color.rgb(100, 100, 100)
                );
            }
        });

        // Share
        share.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");

            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    finalName + " - " + finalPrice
            );

            startActivity(
                    Intent.createChooser(
                            intent,
                            "Share item"
                    )
            );
        });

        content.addView(
                imageFrame,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(300)
                )
        );

        // ---------------------------------------------------------
        // PRICE
        // ---------------------------------------------------------

        TextView priceView =
                createText(
                        price,
                        34,
                        ORANGE,
                        Typeface.BOLD
                );

        priceView.setPadding(
                dp(17),
                dp(18),
                dp(17),
                0
        );

        content.addView(priceView);

        // ---------------------------------------------------------
        // NAME
        // ---------------------------------------------------------

        TextView nameView =
                createText(
                        name,
                        23,
                        DARK,
                        Typeface.BOLD
                );

        nameView.setPadding(
                dp(17),
                dp(5),
                dp(17),
                dp(12)
        );

        nameView.setMaxLines(3);
        nameView.setEllipsize(TextUtils.TruncateAt.END);

        content.addView(nameView);

        // ---------------------------------------------------------
        // CHIPS
        // ---------------------------------------------------------

        LinearLayout chips =
                new LinearLayout(this);

        chips.setOrientation(
                LinearLayout.HORIZONTAL
        );

        chips.setGravity(
                Gravity.CENTER_VERTICAL
        );

        chips.setPadding(
                dp(17),
                0,
                dp(17),
                dp(17)
        );

        TextView conditionView =
                createChip(
                        condition,
                        Color.rgb(231, 246, 228),
                        Color.rgb(57, 105, 48)
                );

        chips.addView(
                conditionView,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(34)
                )
        );

        TextView categoryView =
                createChip(
                        category,
                        Color.rgb(232, 235, 249),
                        Color.rgb(63, 72, 145)
                );

        LinearLayout.LayoutParams categoryParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(34)
                );

        categoryParams.leftMargin = dp(9);

        chips.addView(
                categoryView,
                categoryParams
        );

        content.addView(chips);

        // ---------------------------------------------------------
        // AREA
        // ---------------------------------------------------------

        TextView areaView =
                createText(
                        "⌖  " + area,
                        15,
                        GRAY,
                        Typeface.NORMAL
                );

        areaView.setPadding(
                dp(17),
                0,
                dp(17),
                dp(12)
        );

        content.addView(areaView);

        // ---------------------------------------------------------
        // POSTED
        // ---------------------------------------------------------

        TextView postedView =
                createText(
                        "▣  Posted recently",
                        14,
                        GRAY,
                        Typeface.NORMAL
                );

        postedView.setPadding(
                dp(17),
                0,
                dp(17),
                dp(24)
        );

        content.addView(postedView);

        // ---------------------------------------------------------
        // DESCRIPTION
        // ---------------------------------------------------------

        TextView descriptionTitle =
                createText(
                        "Description",
                        20,
                        DARK,
                        Typeface.BOLD
                );

        descriptionTitle.setPadding(
                dp(17),
                0,
                dp(17),
                dp(12)
        );

        content.addView(descriptionTitle);

        TextView descriptionView =
                createText(
                        description,
                        15,
                        GRAY,
                        Typeface.NORMAL
                );

        descriptionView.setLineSpacing(
                dp(4),
                1.0f
        );

        descriptionView.setPadding(
                dp(17),
                0,
                dp(17),
                dp(23)
        );

        content.addView(descriptionView);

        // ---------------------------------------------------------
        // SAFETY BOX
        // ---------------------------------------------------------

        LinearLayout safetyBox =
                new LinearLayout(this);

        safetyBox.setOrientation(
                LinearLayout.VERTICAL
        );

        safetyBox.setPadding(
                dp(16),
                dp(15),
                dp(16),
                dp(15)
        );

        safetyBox.setBackground(
                rounded(
                        Color.rgb(255, 249, 220),
                        dp(14)
                )
        );

        TextView safetyTitle =
                createText(
                        "🛡  Safety Tips for Buyers",
                        15,
                        Color.rgb(75, 64, 39),
                        Typeface.BOLD
                );

        safetyBox.addView(safetyTitle);

        TextView safetyText =
                createText(
                        "• Meet in a public place for the exchange.\n\n"
                                + "• Inspect the item thoroughly before paying.\n\n"
                                + "• Prefer sellers with \"Verified\" badges.\n\n"
                                + "• Avoid advance payments via UPI or links.",
                        13,
                        Color.rgb(90, 80, 60),
                        Typeface.NORMAL
                );

        safetyText.setLineSpacing(
                dp(1),
                1.0f
        );

        LinearLayout.LayoutParams safetyTextParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        safetyTextParams.topMargin = dp(10);

        safetyBox.addView(
                safetyText,
                safetyTextParams
        );

        LinearLayout.LayoutParams safetyParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        safetyParams.setMargins(
                dp(16),
                0,
                dp(16),
                dp(25)
        );

        content.addView(
                safetyBox,
                safetyParams
        );

        // ---------------------------------------------------------
        // SELLER TITLE
        // ---------------------------------------------------------

        TextView sellerTitle =
                createText(
                        "About Seller",
                        20,
                        DARK,
                        Typeface.BOLD
                );

        sellerTitle.setPadding(
                dp(17),
                0,
                dp(17),
                dp(12)
        );

        content.addView(sellerTitle);

        // ---------------------------------------------------------
        // SELLER CARD
        // ---------------------------------------------------------

        LinearLayout sellerCard =
                new LinearLayout(this);

        sellerCard.setOrientation(
                LinearLayout.HORIZONTAL
        );

        sellerCard.setGravity(
                Gravity.CENTER_VERTICAL
        );

        sellerCard.setPadding(
                dp(15),
                dp(13),
                dp(15),
                dp(13)
        );

        GradientDrawable sellerBackground =
                rounded(
                        Color.WHITE,
                        dp(12)
                );

        sellerBackground.setStroke(
                dp(1),
                Color.rgb(235, 235, 235)
        );

        sellerCard.setBackground(
                sellerBackground
        );

        // Avatar
        TextView avatar =
                createText(
                        "👤",
                        25,
                        Color.DKGRAY,
                        Typeface.NORMAL
                );

        avatar.setGravity(Gravity.CENTER);

        avatar.setBackground(
                rounded(
                        Color.rgb(235, 224, 125),
                        dp(40)
                )
        );

        sellerCard.addView(
                avatar,
                new LinearLayout.LayoutParams(
                        dp(50),
                        dp(50)
                )
        );

        // Seller information
        LinearLayout sellerInfo =
                new LinearLayout(this);

        sellerInfo.setOrientation(
                LinearLayout.VERTICAL
        );

        LinearLayout.LayoutParams sellerInfoParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );

        sellerInfoParams.leftMargin = dp(13);

        TextView sellerName =
                createText(
                        "Admin  ✓",
                        16,
                        DARK,
                        Typeface.BOLD
                );

        sellerInfo.addView(sellerName);

        TextView member =
                createText(
                        "Member since 2026",
                        13,
                        GRAY,
                        Typeface.NORMAL
                );

        LinearLayout.LayoutParams memberParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        memberParams.topMargin = dp(4);

        sellerInfo.addView(
                member,
                memberParams
        );

        TextView active =
                createText(
                        "●  Last active 9m ago",
                        12,
                        GRAY,
                        Typeface.NORMAL
                );

        LinearLayout.LayoutParams activeParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        activeParams.topMargin = dp(3);

        sellerInfo.addView(
                active,
                activeParams
        );

        sellerCard.addView(
                sellerInfo,
                sellerInfoParams
        );

        // Arrow
        TextView sellerArrow =
                createText(
                        "›",
                        28,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        sellerArrow.setGravity(
                Gravity.CENTER
        );

        sellerCard.addView(
                sellerArrow,
                new LinearLayout.LayoutParams(
                        dp(30),
                        dp(50)
                )
        );

        LinearLayout.LayoutParams sellerCardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        sellerCardParams.setMargins(
                dp(16),
                0,
                dp(16),
                dp(30)
        );

        content.addView(
                sellerCard,
                sellerCardParams
        );

        // ---------------------------------------------------------
        // ADD SCROLL VIEW
        // ---------------------------------------------------------

        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1
                );

        root.addView(
                scrollView,
                scrollParams
        );

        // ---------------------------------------------------------
        // CHAT BUTTON
        // ---------------------------------------------------------

        chatButton =
                createText(
                        "▢  Chat with Seller",
                        17,
                        Color.WHITE,
                        Typeface.BOLD
                );

        chatButton.setGravity(
                Gravity.CENTER
        );

        chatButton.setBackground(
                rounded(
                        ORANGE,
                        dp(30)
                )
        );

        LinearLayout.LayoutParams chatParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(58)
                );

        chatParams.leftMargin = dp(16);
        chatParams.rightMargin = dp(16);
        chatParams.topMargin = dp(7);

        root.addView(
                chatButton,
                chatParams
        );

        // ---------------------------------------------------------
        // SET ROOT
        // ---------------------------------------------------------

        setContentView(root);

        // ---------------------------------------------------------
        // SYSTEM INSETS
        // ---------------------------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(
                root,
                (view, windowInsets) -> {

                    Insets insets =
                            windowInsets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    /*
                     * Toolbar receives status-bar padding.
                     * This prevents overlap without manually
                     * creating a status-bar spacer.
                     */
                    toolbar.setPadding(
                            dp(4),
                            insets.top,
                            dp(8),
                            0
                    );

                    /*
                     * Toolbar total height includes status bar.
                     */
                    ViewGroup.LayoutParams toolbarParams =
                            toolbar.getLayoutParams();

                    toolbarParams.height =
                            dp(56) + insets.top;

                    toolbar.setLayoutParams(
                            toolbarParams
                    );

                    /*
                     * Bottom chat button moves above
                     * navigation bar automatically.
                     */
                    LinearLayout.LayoutParams buttonParams =
                            (LinearLayout.LayoutParams)
                                    chatButton.getLayoutParams();

                    buttonParams.bottomMargin =
                            Math.max(
                                    dp(10),
                                    insets.bottom + dp(6)
                            );

                    chatButton.setLayoutParams(
                            buttonParams
                    );

                    /*
                     * Add bottom padding to scrolling content
                     * so the last seller content isn't hidden
                     * behind the chat button/navigation bar.
                     */
                    scrollView.setPadding(
                            0,
                            0,
                            0,
                            dp(90) + insets.bottom
                    );

                    return windowInsets;
                }
        );

        // Apply insets immediately
        ViewCompat.requestApplyInsets(root);

        // ---------------------------------------------------------
        // CHAT ACTION
        // ---------------------------------------------------------

        chatButton.setOnClickListener(v -> {

            if (!finalPhone.isEmpty()) {

                Intent intent =
                        new Intent(Intent.ACTION_DIAL);

                intent.setData(
                        Uri.parse(
                                "tel:" + finalPhone
                        )
                );

                startActivity(intent);
            }
        });
    }

    // =============================================================
    // CREATE CHIP
    // =============================================================

    private TextView createChip(
            String value,
            int backgroundColor,
            int textColor
    ) {

        TextView view =
                createText(
                        value,
                        12,
                        textColor,
                        Typeface.BOLD
                );

        view.setGravity(Gravity.CENTER);

        view.setPadding(
                dp(14),
                0,
                dp(14),
                0
        );

        view.setMaxLines(1);

        view.setEllipsize(
                TextUtils.TruncateAt.END
        );

        view.setBackground(
                rounded(
                        backgroundColor,
                        dp(20)
                )
        );

        return view;
    }

    // =============================================================
    // FLOATING BUTTON
    // =============================================================

    private TextView createFloatingButton(
            String symbol
    ) {

        TextView button =
                createText(
                        symbol,
                        27,
                        Color.rgb(100, 100, 100),
                        Typeface.NORMAL
                );

        button.setGravity(
                Gravity.CENTER
        );

        button.setBackground(
                rounded(
                        Color.WHITE,
                        dp(30)
                )
        );

        button.setElevation(
                dp(2)
        );

        return button;
    }

    // =============================================================
    // CREATE TEXT
    // =============================================================

    private TextView createText(
            String value,
            int size,
            int color,
            int style
    ) {

        TextView view =
                new TextView(this);

        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);

        view.setTypeface(
                Typeface.DEFAULT,
                style
        );

        return view;
    }

    // =============================================================
    // ROUNDED BACKGROUND
    // =============================================================

    private GradientDrawable rounded(
            int color,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);
        drawable.setCornerRadius(radius);

        return drawable;
    }

    // =============================================================
    // DP CONVERSION
    // =============================================================

    private int dp(int value) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
                        + 0.5f
        );
    }
}