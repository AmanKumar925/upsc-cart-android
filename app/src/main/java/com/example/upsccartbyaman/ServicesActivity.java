package com.example.upsccartbyaman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ServicesActivity extends AppCompatActivity {

    // =========================================================
    // COLORS
    // =========================================================

    private static final int ORANGE = Color.rgb(242, 100, 31);
    private static final int DARK = Color.rgb(25, 25, 25);
    private static final int ICON_BG = Color.rgb(255, 243, 229);
    private static final int SUBTITLE = Color.rgb(125, 125, 125);
    private static final int BORDER = Color.rgb(232, 232, 232);

    // =========================================================
    // SERVICES
    // =========================================================

    private final String[] services = {
            "Electrician",
            "RO Water",
            "Plumber",
            "Maid / Cook",
            "Cleaning",
            "Security\nGuard",
            "Watchman",
            "PG / Hostel",
            "Wifi",
            "Book Stall",
            "Bike Rent",
            "Car Rent"
    };

    // =========================================================
    // VIEWS
    // =========================================================

    private LinearLayout root;
    private LinearLayout toolbar;
    private ScrollView scrollView;
    private LinearLayout bottomContainer;

    // =========================================================
    // ON CREATE
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // =====================================================
        // EDGE-TO-EDGE
        // SAME APPROACH AS ItemDetailsActivity
        // =====================================================

        WindowCompat.setDecorFitsSystemWindows(
                getWindow(),
                false
        );

        getWindow().setStatusBarColor(
                Color.TRANSPARENT
        );

        getWindow().setNavigationBarColor(
                Color.TRANSPARENT
        );

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        createServicesScreen();
    }

    // =========================================================
    // CREATE SCREEN
    // =========================================================

    private void createServicesScreen() {

        // =====================================================
        // ROOT
        // =====================================================

        root = new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setBackgroundColor(
                Color.WHITE
        );

        setContentView(root);

        // =====================================================
        // TOOLBAR
        // =====================================================

        /*
         * IMPORTANT:
         *
         * Use LinearLayout here, exactly like ItemDetailsActivity.
         * This makes the status-bar inset move the toolbar content
         * below the status bar correctly.
         */

        toolbar = new LinearLayout(this);

        toolbar.setOrientation(
                LinearLayout.HORIZONTAL
        );

        toolbar.setGravity(
                Gravity.CENTER_VERTICAL
        );

        toolbar.setBackgroundColor(
                Color.WHITE
        );

        toolbar.setElevation(
                dp(2)
        );

        toolbar.setPadding(
                dp(4),
                0,
                dp(8),
                0
        );

        root.addView(
                toolbar,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(56)
                )
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        TextView backButton =
                new TextView(this);

        backButton.setText("‹");

        backButton.setTextSize(
                40
        );

        backButton.setTextColor(
                Color.BLACK
        );

        backButton.setGravity(
                Gravity.CENTER
        );

        backButton.setIncludeFontPadding(
                false
        );

        backButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.NORMAL
        );

        toolbar.addView(
                backButton,
                new LinearLayout.LayoutParams(
                        dp(52),
                        dp(56)
                )
        );

        backButton.setOnClickListener(
                v -> finish()
        );

        // =====================================================
        // TITLE
        // =====================================================

        TextView title =
                new TextView(this);

        title.setText(
                "Services"
        );

        title.setTextSize(
                19
        );

        title.setTextColor(
                Color.BLACK
        );

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setGravity(
                Gravity.CENTER
        );

        title.setSingleLine(
                true
        );

        title.setIncludeFontPadding(
                false
        );

        toolbar.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        dp(56),
                        1
                )
        );

        // =====================================================
        // RIGHT BALANCE
        // =====================================================

        View rightBalance =
                new View(this);

        toolbar.addView(
                rightBalance,
                new LinearLayout.LayoutParams(
                        dp(52),
                        dp(56)
                )
        );

        // =====================================================
        // SCROLL VIEW
        // =====================================================

        scrollView =
                new ScrollView(this);

        scrollView.setFillViewport(
                true
        );

        scrollView.setBackgroundColor(
                Color.WHITE
        );

        scrollView.setVerticalScrollBarEnabled(
                false
        );

        scrollView.setClipToPadding(
                false
        );

        root.addView(
                scrollView,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1
                )
        );

        // =====================================================
        // CONTENT
        // =====================================================

        LinearLayout content =
                new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setBackgroundColor(
                Color.WHITE
        );

        content.setPadding(
                dp(14),
                dp(16),
                dp(14),
                dp(16)
        );

        scrollView.addView(
                content,
                new ScrollView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // =====================================================
        // RESPONSIVE SERVICE GRID
        // =====================================================

        GridLayout grid =
                new GridLayout(this);

        int columns =
                getResponsiveColumnCount();

        grid.setColumnCount(
                columns
        );

        grid.setRowCount(
                (services.length + columns - 1) / columns
        );

        grid.setAlignmentMode(
                GridLayout.ALIGN_BOUNDS
        );

        grid.setUseDefaultMargins(
                false
        );

        content.addView(
                grid,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // =====================================================
        // ADD SERVICE CARDS
        // =====================================================

        for (int i = 0; i < services.length; i++) {

            ServiceCard card =
                    new ServiceCard(
                            this,
                            services[i],
                            i
                    );

            GridLayout.LayoutParams cardParams =
                    new GridLayout.LayoutParams();

            /*
             * Weight = 1 makes every column equal width.
             */

            cardParams.width = 0;

            cardParams.height =
                    dp(122);

            cardParams.columnSpec =
                    GridLayout.spec(
                            i % columns,
                            1,
                            1f
                    );

            cardParams.rowSpec =
                    GridLayout.spec(
                            i / columns
                    );

            // Horizontal gap
            cardParams.leftMargin =
                    i % columns == 0
                            ? 0
                            : dp(5);

            cardParams.rightMargin =
                    i % columns == columns - 1
                            ? 0
                            : dp(5);

            // Vertical gap
            cardParams.topMargin =
                    i / columns == 0
                            ? 0
                            : dp(6);

            cardParams.bottomMargin =
                    dp(6);

            grid.addView(
                    card,
                    cardParams
            );
        }

        // =====================================================
        // LIST YOUR SERVICE
        // =====================================================

        LinearLayout listCard =
                new LinearLayout(this);

        listCard.setOrientation(
                LinearLayout.HORIZONTAL
        );

        listCard.setGravity(
                Gravity.CENTER_VERTICAL
        );

        listCard.setPadding(
                dp(14),
                dp(10),
                dp(14),
                dp(10)
        );

        GradientDrawable listBackground =
                new GradientDrawable();

        listBackground.setColor(
                Color.WHITE
        );

        listBackground.setCornerRadius(
                dp(14)
        );

        listBackground.setStroke(
                dp(1),
                BORDER
        );

        listCard.setBackground(
                listBackground
        );

        LinearLayout.LayoutParams listParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(92)
                );

        listParams.topMargin =
                dp(12);

        content.addView(
                listCard,
                listParams
        );

        // =====================================================
        // LIST ICON
        // =====================================================

        ServiceIcon listIcon =
                new ServiceIcon(
                        this,
                        12
                );

        listCard.addView(
                listIcon,
                new LinearLayout.LayoutParams(
                        dp(54),
                        dp(54)
                )
        );

        // =====================================================
        // LIST TEXT
        // =====================================================

        LinearLayout listText =
                new LinearLayout(this);

        listText.setOrientation(
                LinearLayout.VERTICAL
        );

        listText.setGravity(
                Gravity.CENTER_VERTICAL
        );

        LinearLayout.LayoutParams listTextParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1
                );

        listTextParams.leftMargin =
                dp(12);

        listCard.addView(
                listText,
                listTextParams
        );

        // Title

        TextView listTitle =
                new TextView(this);

        listTitle.setText(
                "List Your Service"
        );

        listTitle.setTextSize(
                16
        );

        listTitle.setTextColor(
                DARK
        );

        listTitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        listTitle.setSingleLine(
                true
        );

        listText.addView(
                listTitle
        );

        // Subtitle

        TextView listSubtitle =
                new TextView(this);

        listSubtitle.setText(
                "Offer your service to UPSC students"
        );

        listSubtitle.setTextSize(
                12
        );

        listSubtitle.setTextColor(
                SUBTITLE
        );

        listSubtitle.setMaxLines(
                2
        );

        listSubtitle.setEllipsize(
                TextUtils.TruncateAt.END
        );

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        subtitleParams.topMargin =
                dp(3);

        listText.addView(
                listSubtitle,
                subtitleParams
        );

        // =====================================================
        // BOTTOM CONTACT ADMIN
        // =====================================================

        bottomContainer =
                new LinearLayout(this);

        bottomContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        bottomContainer.setGravity(
                Gravity.CENTER
        );

        bottomContainer.setBackgroundColor(
                Color.WHITE
        );

        bottomContainer.setElevation(
                dp(8)
        );

        /*
         * WRAP_CONTENT is important.
         *
         * The navigation-bar inset will be added as padding,
         * so the button can never collide with the system bar.
         */

        bottomContainer.setPadding(
                dp(16),
                dp(10),
                dp(16),
                dp(10)
        );

        root.addView(
                bottomContainer,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        // =====================================================
        // CONTACT ADMIN BUTTON
        // =====================================================

        TextView contactAdmin =
                new TextView(this);

        contactAdmin.setText(
                "Contact Admin"
        );

        contactAdmin.setTextSize(
                18
        );

        contactAdmin.setTextColor(
                Color.WHITE
        );

        contactAdmin.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        contactAdmin.setGravity(
                Gravity.CENTER
        );

        contactAdmin.setSingleLine(
                true
        );

        GradientDrawable contactBackground =
                new GradientDrawable();

        contactBackground.setColor(
                ORANGE
        );

        contactBackground.setCornerRadius(
                dp(12)
        );

        contactAdmin.setBackground(
                contactBackground
        );

        bottomContainer.addView(
                contactAdmin,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(48)
                )
        );

        contactAdmin.setOnClickListener(
                v -> {
                    // Contact Admin action
                }
        );

        // =====================================================
        // SYSTEM INSETS
        // =====================================================

        ViewCompat.setOnApplyWindowInsetsListener(
                root,
                (view, windowInsets) -> {

                    Insets insets =
                            windowInsets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    // =================================================
                    // TOOLBAR
                    // =================================================

                    /*
                     * EXACTLY LIKE ItemDetailsActivity:
                     *
                     * top padding = status bar
                     * toolbar height = status bar + 56dp
                     */

                    toolbar.setPadding(
                            dp(4),
                            insets.top,
                            dp(8),
                            0
                    );

                    ViewGroup.LayoutParams toolbarParams =
                            toolbar.getLayoutParams();

                    toolbarParams.height =
                            dp(56) + insets.top;

                    toolbar.setLayoutParams(
                            toolbarParams
                    );

                    // =================================================
                    // BOTTOM NAVIGATION BAR
                    // =================================================

                    /*
                     * Keep the button above the navigation bar.
                     */

                    bottomContainer.setPadding(
                            dp(16),
                            dp(10),
                            dp(16),
                            dp(10) + insets.bottom
                    );

                    // =================================================
                    // SCROLL VIEW
                    // =================================================

                    /*
                     * Extra bottom space makes sure the last card /
                     * List Your Service never gets hidden behind
                     * the Contact Admin area.
                     */

                    scrollView.setPadding(
                            0,
                            0,
                            0,
                            dp(18)
                    );

                    return windowInsets;
                }
        );

        ViewCompat.requestApplyInsets(
                root
        );
    }

    // =========================================================
    // RESPONSIVE COLUMN COUNT
    // =========================================================

    private int getResponsiveColumnCount() {

        return 3;
    }

    // =========================================================
    // SERVICE CARD
    // =========================================================

    private class ServiceCard
            extends LinearLayout {

        ServiceCard(
                Context context,
                String serviceName,
                int iconIndex
        ) {

            super(context);

            setOrientation(
                    VERTICAL
            );

            setGravity(
                    Gravity.CENTER
            );

            setPadding(
                    dp(5),
                    dp(7),
                    dp(5),
                    dp(7)
            );

            GradientDrawable background =
                    new GradientDrawable();

            background.setColor(
                    Color.WHITE
            );

            background.setCornerRadius(
                    dp(14)
            );

            background.setStroke(
                    dp(1),
                    BORDER
            );

            setBackground(
                    background
            );

            // =====================================================
            // ICON
            // =====================================================

            ServiceIcon icon =
                    new ServiceIcon(
                            context,
                            iconIndex
                    );

            addView(
                    icon,
                    new LinearLayout.LayoutParams(
                            dp(54),
                            dp(54)
                    )
            );

            // =====================================================
            // SERVICE NAME
            // =====================================================

            TextView name =
                    new TextView(context);

            name.setText(
                    serviceName
            );

            name.setTextColor(
                    DARK
            );

            name.setTextSize(
                    13
            );

            name.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );

            name.setGravity(
                    Gravity.CENTER
            );

            name.setMaxLines(
                    2
            );

            name.setEllipsize(
                    TextUtils.TruncateAt.END
            );

            name.setIncludeFontPadding(
                    false
            );

            LinearLayout.LayoutParams nameParams =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

            nameParams.topMargin =
                    dp(6);

            addView(
                    name,
                    nameParams
            );

            setOnClickListener(
                    v -> {
                        // Service click action
                    }
            );
        }
    }

    // =========================================================
    // SERVICE ICON
    // =========================================================

    private class ServiceIcon
            extends View {

        private final Paint paint =
                new Paint(Paint.ANTI_ALIAS_FLAG);

        private final Paint strokePaint =
                new Paint(Paint.ANTI_ALIAS_FLAG);

        private final int iconIndex;

        ServiceIcon(
                Context context,
                int iconIndex
        ) {

            super(context);

            this.iconIndex =
                    iconIndex;

            paint.setAntiAlias(
                    true
            );

            strokePaint.setAntiAlias(
                    true
            );

            strokePaint.setStyle(
                    Paint.Style.STROKE
            );

            strokePaint.setStrokeCap(
                    Paint.Cap.ROUND
            );

            strokePaint.setStrokeJoin(
                    Paint.Join.ROUND
            );

            strokePaint.setStrokeWidth(
                    dp(3)
            );

            setLayerType(
                    View.LAYER_TYPE_SOFTWARE,
                    null
            );
        }

        @Override
        protected void onDraw(
                Canvas canvas
        ) {

            super.onDraw(canvas);

            float w =
                    getWidth();

            float h =
                    getHeight();

            float cx =
                    w / 2f;

            float cy =
                    h / 2f;

            // =================================================
            // ICON BACKGROUND
            // =================================================

            paint.setStyle(
                    Paint.Style.FILL
            );

            paint.setColor(
                    ICON_BG
            );

            canvas.drawRoundRect(
                    new RectF(
                            0,
                            0,
                            w,
                            h
                    ),
                    dp(14),
                    dp(14),
                    paint
            );

            paint.setColor(
                    ORANGE
            );

            strokePaint.setColor(
                    ORANGE
            );

            // =================================================
            // ELECTRICIAN
            // =================================================

            if (iconIndex == 0) {

                Path plug =
                        new Path();

                plug.moveTo(
                        cx - dp(10),
                        cy - dp(12)
                );

                plug.lineTo(
                        cx + dp(10),
                        cy - dp(12)
                );

                plug.lineTo(
                        cx + dp(10),
                        cy + dp(2)
                );

                plug.cubicTo(
                        cx + dp(10),
                        cy + dp(11),
                        cx + dp(4),
                        cy + dp(15),
                        cx,
                        cy + dp(15)
                );

                plug.cubicTo(
                        cx - dp(4),
                        cy + dp(15),
                        cx - dp(10),
                        cy + dp(11),
                        cx - dp(10),
                        cy + dp(2)
                );

                plug.close();

                canvas.drawPath(
                        plug,
                        paint
                );

                paint.setColor(
                        Color.WHITE
                );

                canvas.drawRect(
                        cx - dp(7),
                        cy - dp(18),
                        cx - dp(3),
                        cy - dp(8),
                        paint
                );

                canvas.drawRect(
                        cx + dp(3),
                        cy - dp(18),
                        cx + dp(7),
                        cy - dp(8),
                        paint
                );

                return;
            }

            // =================================================
            // RO WATER
            // =================================================

            if (iconIndex == 1) {

                Path drop =
                        new Path();

                drop.moveTo(
                        cx,
                        cy - dp(20)
                );

                drop.cubicTo(
                        cx - dp(4),
                        cy - dp(13),
                        cx - dp(13),
                        cy - dp(5),
                        cx - dp(13),
                        cy + dp(5)
                );

                drop.cubicTo(
                        cx - dp(13),
                        cy + dp(14),
                        cx - dp(7),
                        cy + dp(20),
                        cx,
                        cy + dp(20)
                );

                drop.cubicTo(
                        cx + dp(7),
                        cy + dp(20),
                        cx + dp(13),
                        cy + dp(14),
                        cx + dp(13),
                        cy + dp(5)
                );

                drop.cubicTo(
                        cx + dp(13),
                        cy - dp(5),
                        cx + dp(4),
                        cy - dp(13),
                        cx,
                        cy - dp(20)
                );

                drop.close();

                canvas.drawPath(
                        drop,
                        paint
                );

                strokePaint.setColor(
                        Color.WHITE
                );

                strokePaint.setStrokeWidth(
                        dp(2)
                );

                canvas.drawArc(
                        new RectF(
                                cx - dp(7),
                                cy - dp(1),
                                cx + dp(7),
                                cy + dp(10)
                        ),
                        15,
                        150,
                        false,
                        strokePaint
                );

                return;
            }

            // =================================================
            // PLUMBER
            // =================================================

            if (iconIndex == 2) {

                canvas.save();

                canvas.rotate(
                        -45,
                        cx,
                        cy
                );

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(7)
                );

                canvas.drawLine(
                        cx - dp(17),
                        cy,
                        cx + dp(12),
                        cy,
                        strokePaint
                );

                canvas.drawCircle(
                        cx - dp(17),
                        cy,
                        dp(8),
                        strokePaint
                );

                canvas.drawLine(
                        cx + dp(12),
                        cy - dp(8),
                        cx + dp(12),
                        cy + dp(8),
                        strokePaint
                );

                canvas.restore();

                return;
            }

            // =================================================
            // MAID / COOK
            // =================================================

            if (iconIndex == 3) {

                paint.setColor(
                        ORANGE
                );

                canvas.drawRoundRect(
                        new RectF(
                                cx - dp(16),
                                cy - dp(15),
                                cx + dp(16),
                                cy + dp(15)
                        ),
                        dp(5),
                        dp(5),
                        paint
                );

                paint.setColor(
                        Color.WHITE
                );

                canvas.drawCircle(
                        cx,
                        cy - dp(6),
                        dp(5),
                        paint
                );

                canvas.drawRect(
                        cx - dp(8),
                        cy + dp(1),
                        cx + dp(8),
                        cy + dp(10),
                        paint
                );

                return;
            }

            // =================================================
            // CLEANING
            // =================================================

            if (iconIndex == 4) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(5)
                );

                canvas.drawLine(
                        cx - dp(11),
                        cy + dp(16),
                        cx + dp(9),
                        cy - dp(14),
                        strokePaint
                );

                strokePaint.setStrokeWidth(
                        dp(3)
                );

                canvas.drawLine(
                        cx - dp(17),
                        cy + dp(12),
                        cx - dp(5),
                        cy + dp(20),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(13),
                        cy + dp(8),
                        cx - dp(1),
                        cy + dp(16),
                        strokePaint
                );

                return;
            }

            // =================================================
            // SECURITY GUARD
            // =================================================

            if (iconIndex == 5) {

                Path shield =
                        new Path();

                shield.moveTo(
                        cx,
                        cy - dp(20)
                );

                shield.lineTo(
                        cx + dp(16),
                        cy - dp(13)
                );

                shield.lineTo(
                        cx + dp(13),
                        cy + dp(8)
                );

                shield.cubicTo(
                        cx + dp(10),
                        cy + dp(16),
                        cx + dp(4),
                        cy + dp(20),
                        cx,
                        cy + dp(22)
                );

                shield.cubicTo(
                        cx - dp(4),
                        cy + dp(20),
                        cx - dp(10),
                        cy + dp(16),
                        cx - dp(13),
                        cy + dp(8)
                );

                shield.lineTo(
                        cx - dp(16),
                        cy - dp(13)
                );

                shield.close();

                paint.setColor(
                        ORANGE
                );

                canvas.drawPath(
                        shield,
                        paint
                );

                paint.setColor(
                        Color.WHITE
                );

                canvas.drawCircle(
                        cx,
                        cy - dp(4),
                        dp(5),
                        paint
                );

                canvas.drawRoundRect(
                        new RectF(
                                cx - dp(8),
                                cy + dp(3),
                                cx + dp(8),
                                cy + dp(12)
                        ),
                        dp(4),
                        dp(4),
                        paint
                );

                return;
            }

            // =================================================
            // WATCHMAN
            // =================================================

            if (iconIndex == 6) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(3)
                );

                Path eye =
                        new Path();

                eye.moveTo(
                        cx - dp(19),
                        cy
                );

                eye.quadTo(
                        cx,
                        cy - dp(16),
                        cx + dp(19),
                        cy
                );

                eye.quadTo(
                        cx,
                        cy + dp(16),
                        cx - dp(19),
                        cy
                );

                canvas.drawPath(
                        eye,
                        strokePaint
                );

                paint.setColor(
                        ORANGE
                );

                canvas.drawCircle(
                        cx,
                        cy,
                        dp(6),
                        paint
                );

                return;
            }

            // =================================================
            // PG / HOSTEL
            // =================================================

            if (iconIndex == 7) {

                paint.setColor(
                        ORANGE
                );

                canvas.drawRect(
                        cx - dp(16),
                        cy - dp(17),
                        cx + dp(16),
                        cy + dp(18),
                        paint
                );

                paint.setColor(
                        Color.WHITE
                );

                for (int r = 0; r < 2; r++) {

                    for (int c = 0; c < 2; c++) {

                        float x =
                                cx - dp(10)
                                        + c * dp(13);

                        float y =
                                cy - dp(10)
                                        + r * dp(13);

                        canvas.drawRect(
                                x,
                                y,
                                x + dp(7),
                                y + dp(7),
                                paint
                        );
                    }
                }

                return;
            }

            // =================================================
            // WIFI
            // =================================================

            if (iconIndex == 8) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(3)
                );

                canvas.drawArc(
                        new RectF(
                                cx - dp(20),
                                cy - dp(15),
                                cx + dp(20),
                                cy + dp(17)
                        ),
                        220,
                        100,
                        false,
                        strokePaint
                );

                canvas.drawArc(
                        new RectF(
                                cx - dp(13),
                                cy - dp(8),
                                cx + dp(13),
                                cy + dp(13)
                        ),
                        220,
                        100,
                        false,
                        strokePaint
                );

                canvas.drawArc(
                        new RectF(
                                cx - dp(6),
                                cy - dp(1),
                                cx + dp(6),
                                cy + dp(10)
                        ),
                        220,
                        100,
                        false,
                        strokePaint
                );

                paint.setColor(
                        ORANGE
                );

                canvas.drawCircle(
                        cx,
                        cy + dp(14),
                        dp(3),
                        paint
                );

                return;
            }

            // =================================================
            // BOOK STALL
            // =================================================

            if (iconIndex == 9) {

                paint.setColor(
                        ORANGE
                );

                Path bookLeft =
                        new Path();

                bookLeft.moveTo(
                        cx,
                        cy - dp(15)
                );

                bookLeft.cubicTo(
                        cx - dp(7),
                        cy - dp(18),
                        cx - dp(13),
                        cy - dp(18),
                        cx - dp(17),
                        cy - dp(13)
                );

                bookLeft.lineTo(
                        cx - dp(17),
                        cy + dp(14)
                );

                bookLeft.cubicTo(
                        cx - dp(11),
                        cy + dp(10),
                        cx - dp(6),
                        cy + dp(11),
                        cx,
                        cy + dp(15)
                );

                bookLeft.close();

                canvas.drawPath(
                        bookLeft,
                        paint
                );

                Path bookRight =
                        new Path();

                bookRight.moveTo(
                        cx,
                        cy - dp(15)
                );

                bookRight.cubicTo(
                        cx + dp(7),
                        cy - dp(18),
                        cx + dp(13),
                        cy - dp(18),
                        cx + dp(17),
                        cy - dp(13)
                );

                bookRight.lineTo(
                        cx + dp(17),
                        cy + dp(14)
                );

                bookRight.cubicTo(
                        cx + dp(11),
                        cy + dp(10),
                        cx + dp(6),
                        cy + dp(11),
                        cx,
                        cy + dp(15)
                );

                bookRight.close();

                canvas.drawPath(
                        bookRight,
                        paint
                );

                return;
            }

            // =================================================
            // BIKE RENT
            // =================================================

            if (iconIndex == 10) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(3)
                );

                canvas.drawCircle(
                        cx - dp(13),
                        cy + dp(9),
                        dp(8),
                        strokePaint
                );

                canvas.drawCircle(
                        cx + dp(14),
                        cy + dp(9),
                        dp(8),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(13),
                        cy + dp(9),
                        cx - dp(3),
                        cy - dp(5),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(3),
                        cy - dp(5),
                        cx + dp(5),
                        cy + dp(9),
                        strokePaint
                );

                canvas.drawLine(
                        cx + dp(5),
                        cy + dp(9),
                        cx - dp(13),
                        cy + dp(9),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(3),
                        cy - dp(5),
                        cx + dp(6),
                        cy - dp(5),
                        strokePaint
                );

                canvas.drawLine(
                        cx + dp(6),
                        cy - dp(5),
                        cx + dp(14),
                        cy + dp(9),
                        strokePaint
                );

                return;
            }

            // =================================================
            // CAR RENT
            // =================================================

            if (iconIndex == 11) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(3)
                );

                Path car =
                        new Path();

                car.moveTo(
                        cx - dp(19),
                        cy + dp(8)
                );

                car.lineTo(
                        cx - dp(14),
                        cy - dp(7)
                );

                car.quadTo(
                        cx - dp(12),
                        cy - dp(12),
                        cx - dp(7),
                        cy - dp(12)
                );

                car.lineTo(
                        cx + dp(8),
                        cy - dp(12)
                );

                car.quadTo(
                        cx + dp(13),
                        cy - dp(11),
                        cx + dp(15),
                        cy - dp(6)
                );

                car.lineTo(
                        cx + dp(20),
                        cy + dp(8)
                );

                car.lineTo(
                        cx + dp(20),
                        cy + dp(13)
                );

                car.lineTo(
                        cx - dp(19),
                        cy + dp(13)
                );

                car.close();

                canvas.drawPath(
                        car,
                        strokePaint
                );

                paint.setColor(
                        ORANGE
                );

                canvas.drawCircle(
                        cx - dp(11),
                        cy + dp(13),
                        dp(5),
                        paint
                );

                canvas.drawCircle(
                        cx + dp(12),
                        cy + dp(13),
                        dp(5),
                        paint
                );

                return;
            }

            // =================================================
            // LIST YOUR SERVICE
            // =================================================

            if (iconIndex == 12) {

                strokePaint.setColor(
                        ORANGE
                );

                strokePaint.setStrokeWidth(
                        dp(2.5f)
                );

                canvas.drawRoundRect(
                        new RectF(
                                cx - dp(14),
                                cy - dp(18),
                                cx + dp(14),
                                cy + dp(18)
                        ),
                        dp(3),
                        dp(3),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(7),
                        cy - dp(8),
                        cx + dp(8),
                        cy - dp(8),
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(7),
                        cy,
                        cx + dp(8),
                        cy,
                        strokePaint
                );

                canvas.drawLine(
                        cx - dp(7),
                        cy + dp(8),
                        cx + dp(8),
                        cy + dp(8),
                        strokePaint
                );
            }
        }
    }

    // =========================================================
    // DP
    // =========================================================

    private int dp(float value) {

        return (int) (
                value
                        * getResources()
                        .getDisplayMetrics()
                        .density
                        + 0.5f
        );
    }
}