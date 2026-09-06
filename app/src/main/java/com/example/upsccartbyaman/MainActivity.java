package com.example.upsccartbyaman;

import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends FragmentActivity {

    private LinearLayout root;

    private final int ORANGE = Color.rgb(242, 100, 31);
    private final int DARK = Color.rgb(20, 20, 20);

    private final String[] pageTitles = {
            "Buy & Sell Fast",
            "Search & Filter",
            "Chat Safely"
    };

    private final String[] pageSubtitles = {
            "Post your items in minutes and find deals nearby.",
            "Use search, category, and area filters to save time.",
            "Talk to buyers/sellers and share details in chat."
    };

    private final String[] pageTips = {
            "Tap any listing to see details and contact\nthe seller.",
            "Use filters to quickly find exactly what\nyou need.",
            "Long press your message to delete."
    };

    private final String[] pageIcons = {
            "🛍",
            "⌕",
            "💬"
    };

    private final int[][] pageGradients = {
            {Color.rgb(247, 94, 20), Color.rgb(45, 15, 5)},
            {Color.rgb(0, 180, 95), Color.rgb(0, 55, 32)},
            {Color.rgb(20, 105, 220), Color.rgb(3, 20, 50)}
    };

    private ViewPager2 pager;
    private LinearLayout dotsContainer;
    private TextView nextButton;

    private int statusBarHeight = 0;
    private int navigationBarHeight = 0;

    private FrameLayout fragmentContainer;
    private LinearLayout bottomNav;
    private int selectedTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(
                getWindow(),
                false
        );

        setSystemBarIcons(true, true);

        ViewCompat.setOnApplyWindowInsetsListener(
                getWindow().getDecorView(),
                (view, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    statusBarHeight = bars.top;
                    navigationBarHeight = bars.bottom;

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(
                getWindow().getDecorView()
        );

        showLogin();
    }

    private void setSystemBarIcons(
            boolean lightStatusBar,
            boolean lightNavigationBar
    ) {

        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(
                        getWindow(),
                        getWindow().getDecorView()
                );

        if (controller != null) {

            controller.setAppearanceLightStatusBars(
                    lightStatusBar
            );

            controller.setAppearanceLightNavigationBars(
                    lightNavigationBar
            );
        }
    }

    private void showLogin() {

        setSystemBarIcons(true, true);

        ScrollView scroll = new ScrollView(this);

        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.WHITE);

        LinearLayout content = new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        content.setPadding(
                dp(25),
                dp(20),
                dp(25),
                dp(30)
        );

        scroll.addView(
                content,
                new ScrollView.LayoutParams(
                        -1,
                        -1
                )
        );

        // Large edge-to-edge logo card
        ImageView logo = new ImageView(this);

        logo.setImageResource(
                R.drawable.logo
        );

        logo.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        logo.setClipToOutline(true);

        logo.setOutlineProvider(
                new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(
                                0,
                                0,
                                view.getWidth(),
                                view.getHeight(),
                                dp(28)
                        );
                    }
                }
        );

        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(320)
                );

        logoParams.topMargin =
                dp(10);

        content.addView(
                logo,
                logoParams
        );

        // Title: "Welcome Back!" in orange bold
        TextView title =
                createText(
                        "Welcome Back!",
                        28,
                        ORANGE,
                        Typeface.BOLD
                );

        title.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        titleParams.topMargin =
                dp(24);

        content.addView(
                title,
                titleParams
        );

        // Subtitle: "Login to continue to UPSC Cart"
        TextView subtitle =
                createText(
                        "Login to continue to UPSC Cart",
                        16,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        subtitle.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        subtitleParams.topMargin =
                dp(10);

        content.addView(
                subtitle,
                subtitleParams
        );

        // "Sign in with Google" button — white pill, gray border, icon + black text
        LinearLayout googleButton = new LinearLayout(this);

        googleButton.setOrientation(
                LinearLayout.HORIZONTAL
        );

        googleButton.setGravity(
                Gravity.CENTER
        );

        googleButton.setBackground(
                roundedBorderedBackground(
                        Color.WHITE,
                        Color.rgb(225, 225, 225),
                        dp(1),
                        dp(14)
                )
        );

        TextView googleIcon =
                createText(
                        "→",
                        20,
                        Color.rgb(30, 136, 229),
                        Typeface.BOLD
                );

        LinearLayout.LayoutParams googleIconParams =
                new LinearLayout.LayoutParams(
                        -2,
                        -2
                );

        googleIconParams.rightMargin =
                dp(10);

        googleButton.addView(
                googleIcon,
                googleIconParams
        );

        TextView googleText =
                createText(
                        "Sign in with Google",
                        16,
                        Color.BLACK,
                        Typeface.BOLD
                );

        googleButton.addView(
                googleText,
                new LinearLayout.LayoutParams(
                        -2,
                        -2
                )
        );

        LinearLayout.LayoutParams googleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(55)
                );

        googleParams.topMargin =
                dp(45);

        content.addView(
                googleButton,
                googleParams
        );

        googleButton.setOnClickListener(
                v -> showAccounts()
        );

        // Terms text, with a larger gap below the button
        TextView terms =
                createText(
                        "By continuing, you agree to our Terms of Service\nand Privacy Policy.",
                        12,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        terms.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams termsParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        termsParams.topMargin =
                dp(90);

        content.addView(
                terms,
                termsParams
        );

        setContentView(scroll);

        ViewCompat.setOnApplyWindowInsetsListener(
                scroll,
                (view, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    scroll.setPadding(
                            dp(25),
                            bars.top + dp(20),
                            dp(25),
                            bars.bottom + dp(30)
                    );

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(scroll);
    }

    private void showAccounts() {

        setSystemBarIcons(true, true);

        ScrollView scroll = new ScrollView(this);

        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.WHITE);

        LinearLayout content = new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setPadding(
                dp(25),
                dp(40),
                dp(25),
                dp(30)
        );

        scroll.addView(
                content,
                new ScrollView.LayoutParams(
                        -1,
                        -1
                )
        );

        TextView title =
                createText(
                        "Choose an account",
                        28,
                        Color.BLACK,
                        Typeface.BOLD
                );

        content.addView(
                title,
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                )
        );

        TextView subtitle =
                createText(
                        "Select an account to continue",
                        15,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        subtitleParams.topMargin =
                dp(8);

        content.addView(
                subtitle,
                subtitleParams
        );

        addAccount(
                content,
                "A",
                "Aman Kumar",
                "aman@example.com"
        );

        addAccount(
                content,
                "U",
                "UPSC Cart",
                "upsc@example.com"
        );

        addAccount(
                content,
                "G",
                "Guest Account",
                "guest@example.com"
        );

        setContentView(scroll);

        ViewCompat.setOnApplyWindowInsetsListener(
                scroll,
                (view, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    scroll.setPadding(
                            dp(25),
                            bars.top + dp(40),
                            dp(25),
                            bars.bottom + dp(30)
                    );

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(scroll);
    }

    private void addAccount(
            LinearLayout parent,
            String letter,
            String name,
            String email
    ) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        row.setPadding(
                dp(15),
                dp(12),
                dp(15),
                dp(12)
        );

        row.setBackground(
                roundedBackground(
                        Color.rgb(248, 248, 248),
                        dp(14)
                )
        );

        TextView avatar =
                createText(
                        letter,
                        20,
                        Color.WHITE,
                        Typeface.BOLD
                );

        avatar.setGravity(
                Gravity.CENTER
        );

        avatar.setBackground(
                roundedBackground(
                        ORANGE,
                        dp(40)
                )
        );

        row.addView(
                avatar,
                new LinearLayout.LayoutParams(
                        dp(45),
                        dp(45)
                )
        );

        LinearLayout textContainer =
                new LinearLayout(this);

        textContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView nameView =
                createText(
                        name,
                        16,
                        Color.BLACK,
                        Typeface.BOLD
                );

        TextView emailView =
                createText(
                        email,
                        13,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        textContainer.addView(
                nameView
        );

        textContainer.addView(
                emailView
        );

        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                );

        textParams.leftMargin =
                dp(15);

        row.addView(
                textContainer,
                textParams
        );

        TextView arrow =
                createText(
                        "›",
                        28,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        row.addView(
                arrow,
                new LinearLayout.LayoutParams(
                        dp(30),
                        dp(45)
                )
        );

        LinearLayout.LayoutParams rowParams =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(75)
                );

        rowParams.topMargin =
                dp(15);

        parent.addView(
                row,
                rowParams
        );

        row.setOnClickListener(
                v -> showOnboarding()
        );
    }

    private void showOnboarding() {

        setSystemBarIcons(false, false);

        FrameLayout screen =
                new FrameLayout(this);

        screen.setBackgroundColor(
                Color.BLACK
        );

        pager =
                new ViewPager2(this);

        pager.setAdapter(
                new OnboardingAdapter()
        );

        screen.addView(
                pager,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        TextView skip =
                createText(
                        "Skip",
                        15,
                        Color.WHITE,
                        Typeface.BOLD
                );

        skip.setGravity(
                Gravity.CENTER
        );

        skip.setPadding(
                dp(15),
                dp(10),
                dp(15),
                dp(10)
        );

        FrameLayout.LayoutParams skipParams =
                new FrameLayout.LayoutParams(
                        dp(80),
                        dp(50)
                );

        skipParams.gravity =
                Gravity.TOP | Gravity.END;

        screen.addView(
                skip,
                skipParams
        );

        skip.setOnClickListener(
                v -> showHome()
        );

        LinearLayout bottom =
                new LinearLayout(this);

        bottom.setOrientation(
                LinearLayout.HORIZONTAL
        );

        bottom.setGravity(
                Gravity.CENTER_VERTICAL
        );

        bottom.setPadding(
                dp(20),
                dp(10),
                dp(20),
                dp(10)
        );

        dotsContainer =
                new LinearLayout(this);

        dotsContainer.setOrientation(
                LinearLayout.HORIZONTAL
        );

        bottom.addView(
                dotsContainer,
                new LinearLayout.LayoutParams(
                        0,
                        dp(40),
                        1
                )
        );

        nextButton =
                createText(
                        "NEXT",
                        15,
                        Color.WHITE,
                        Typeface.BOLD
                );

        nextButton.setGravity(
                Gravity.CENTER
        );

        nextButton.setBackground(
                roundedBackground(
                        ORANGE,
                        dp(25)
                )
        );

        bottom.addView(
                nextButton,
                new LinearLayout.LayoutParams(
                        dp(110),
                        dp(50)
                )
        );

        FrameLayout.LayoutParams bottomParams =
                new FrameLayout.LayoutParams(
                        -1,
                        dp(70)
                );

        bottomParams.gravity =
                Gravity.BOTTOM;

        screen.addView(
                bottom,
                bottomParams
        );

        setContentView(screen);

        pager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position
                    ) {

                        updateOverlay(position);
                    }
                }
        );

        nextButton.setOnClickListener(
                v -> {

                    int position =
                            pager.getCurrentItem();

                    if (position <
                            pageTitles.length - 1) {

                        pager.setCurrentItem(
                                position + 1,
                                true
                        );

                    } else {

                        showHome();
                    }
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(
                screen,
                (view, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    FrameLayout.LayoutParams skipLp =
                            (FrameLayout.LayoutParams)
                                    skip.getLayoutParams();

                    skipLp.topMargin =
                            bars.top + dp(5);

                    skip.setLayoutParams(
                            skipLp
                    );

                    FrameLayout.LayoutParams bottomLp =
                            (FrameLayout.LayoutParams)
                                    bottom.getLayoutParams();

                    bottomLp.bottomMargin =
                            bars.bottom;

                    bottom.setLayoutParams(
                            bottomLp
                    );

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(screen);

        updateOverlay(0);
    }

    private void updateOverlay(int position) {

        dotsContainer.removeAllViews();

        for (int i = 0; i < pageTitles.length; i++) {

            View dot = new View(this);

            GradientDrawable background =
                    new GradientDrawable();

            background.setShape(
                    GradientDrawable.OVAL
            );

            background.setColor(
                    i == position
                            ? Color.WHITE
                            : Color.GRAY
            );

            dot.setBackground(
                    background
            );

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            dp(i == position ? 22 : 8),
                            dp(8)
                    );

            params.leftMargin =
                    dp(4);

            params.rightMargin =
                    dp(4);

            dotsContainer.addView(
                    dot,
                    params
            );
        }

        if (position ==
                pageTitles.length - 1) {

            nextButton.setText(
                    "START"
            );

        } else {

            nextButton.setText(
                    "NEXT"
            );
        }
    }

    private View buildOnboardingPage(
            int position
    ) {

        FrameLayout root =
                new FrameLayout(this);

        GradientDrawable gradient =
                new GradientDrawable(
                        GradientDrawable.Orientation.TL_BR,
                        pageGradients[position]
                );

        root.setBackground(
                gradient
        );

        LinearLayout content =
                new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setGravity(
                Gravity.CENTER
        );

        content.setPadding(
                dp(30),
                dp(50),
                dp(30),
                dp(100)
        );

        TextView icon =
                createText(
                        pageIcons[position],
                        80,
                        Color.WHITE,
                        Typeface.NORMAL
                );

        icon.setGravity(
                Gravity.CENTER
        );

        content.addView(
                icon,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(120)
                )
        );

        TextView title =
                createText(
                        pageTitles[position],
                        30,
                        Color.WHITE,
                        Typeface.BOLD
                );

        title.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        titleParams.topMargin =
                dp(20);

        content.addView(
                title,
                titleParams
        );

        TextView subtitle =
                createText(
                        pageSubtitles[position],
                        16,
                        Color.WHITE,
                        Typeface.NORMAL
                );

        subtitle.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        subtitleParams.topMargin =
                dp(15);

        content.addView(
                subtitle,
                subtitleParams
        );

        TextView tip =
                createText(
                        pageTips[position],
                        14,
                        Color.DKGRAY,
                        Typeface.NORMAL
                );

        tip.setGravity(
                Gravity.CENTER
        );

        tip.setPadding(
                dp(20),
                dp(15),
                dp(20),
                dp(15)
        );

        tip.setBackground(
                roundedBackground(
                        Color.WHITE,
                        dp(18)
                )
        );

        LinearLayout.LayoutParams tipParams =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        tipParams.topMargin =
                dp(35);

        content.addView(
                tip,
                tipParams
        );

        root.addView(
                content,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        return root;
    }

    private class OnboardingAdapter
            extends RecyclerView.Adapter<OnboardingAdapter.PageHolder> {

        @NonNull
        @Override
        public PageHolder onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {

            FrameLayout frame =
                    new FrameLayout(
                            parent.getContext()
                    );

            frame.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            -1,
                            -1
                    )
            );

            return new PageHolder(frame);
        }

        @Override
        public void onBindViewHolder(
                @NonNull PageHolder holder,
                int position
        ) {

            holder.frame.removeAllViews();

            holder.frame.addView(
                    buildOnboardingPage(position),
                    new FrameLayout.LayoutParams(
                            -1,
                            -1
                    )
            );
        }

        @Override
        public int getItemCount() {

            return pageTitles.length;
        }

        class PageHolder
                extends RecyclerView.ViewHolder {

            FrameLayout frame;

            PageHolder(View itemView) {

                super(itemView);

                frame =
                        (FrameLayout) itemView;
            }
        }
    }

    private void showHome() {

        setSystemBarIcons(true, true);

        FrameLayout screen =
                new FrameLayout(this);

        screen.setBackgroundColor(
                Color.WHITE
        );

        setContentView(screen);

        fragmentContainer =
                new FrameLayout(this);

        fragmentContainer.setId(
                View.generateViewId()
        );

        FrameLayout.LayoutParams fragmentParams =
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                );

        screen.addView(
                fragmentContainer,
                fragmentParams
        );

        bottomNav =
                new LinearLayout(this);

        bottomNav.setOrientation(
                LinearLayout.HORIZONTAL
        );

        bottomNav.setGravity(
                Gravity.CENTER_VERTICAL
        );

        bottomNav.setBackgroundColor(
                Color.WHITE
        );

        bottomNav.setElevation(
                dp(10)
        );

        addBottomItem(
                "🏠",
                "Home",
                0
        );

        addBottomItem(
                "🚪",
                "Rooms",
                1
        );

        addPostItem();

        addBottomItem(
                "💬",
                "Chat",
                3
        );

        addBottomItem(
                "📡",
                "Podcasts",
                4
        );

        FrameLayout navWrapper =
                new FrameLayout(this);

        navWrapper.setClipChildren(false);
        navWrapper.setClipToPadding(false);

        navWrapper.addView(
                bottomNav,
                new FrameLayout.LayoutParams(
                        -1,
                        dp(70)
                )
        );

        FrameLayout.LayoutParams navParams =
                new FrameLayout.LayoutParams(
                        -1,
                        dp(70)
                );

        navParams.gravity =
                Gravity.BOTTOM;

        screen.addView(
                navWrapper,
                navParams
        );

        ViewCompat.setOnApplyWindowInsetsListener(
                screen,
                (view, insets) -> {

                    Insets bars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    statusBarHeight =
                            bars.top;

                    navigationBarHeight =
                            bars.bottom;

                    FrameLayout.LayoutParams params =
                            (FrameLayout.LayoutParams)
                                    navWrapper.getLayoutParams();

                    params.bottomMargin =
                            bars.bottom;

                    navWrapper.setLayoutParams(
                            params
                    );

                    fragmentContainer.setPadding(
                            0,
                            bars.top,
                            0,
                            dp(70) + bars.bottom
                    );

                    return insets;
                }
        );

        ViewCompat.requestApplyInsets(
                screen
        );

        openFragment(
                new HomeFragment(),
                0
        );
    }

    private void addBottomItem(
            String icon,
            String label,
            int position
    ) {

        LinearLayout item =
                buildNavItem(
                        icon,
                        label
                );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        dp(70),
                        1
                );

        bottomNav.addView(
                item,
                params
        );

        item.setOnClickListener(
                v -> openFragment(
                        getFragment(position),
                        position
                )
        );
    }

    private Fragment getFragment(
            int position
    ) {

        switch (position) {

            case 1:
                return new RoomsFragment();

            case 3:
                return new ChatFragment();

            case 4:
                return new PodcastsFragment();

            default:
                return new HomeFragment();
        }
    }

    private void addPostItem() {

        LinearLayout item =
                buildNavPostItem();

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        dp(70),
                        1
                );

        bottomNav.addView(
                item,
                params
        );

        item.setOnClickListener(
                v -> openFragment(
                        new PostFragment(),
                        2
                )
        );
    }

    private void openFragment(
            Fragment fragment,
            int position
    ) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        fragmentContainer.getId(),
                        fragment
                )
                .commit();

        updateBottomNavigation(
                position
        );
    }

    private void updateBottomNavigation(
            int position
    ) {

        selectedTab = position;

        if (bottomNav == null) {
            return;
        }

        for (int i = 0;
             i < bottomNav.getChildCount();
             i++) {

            View child =
                    bottomNav.getChildAt(i);

            if (!(child instanceof LinearLayout)) {
                continue;
            }

            if (i == 2) {
                continue;
            }

            LinearLayout item =
                    (LinearLayout) child;

            for (int j = 0;
                 j < item.getChildCount();
                 j++) {

                View view =
                        item.getChildAt(j);

                if (view instanceof TextView) {

                    TextView text =
                            (TextView) view;

                    text.setTextColor(
                            i == position
                                    ? ORANGE
                                    : Color.GRAY
                    );
                }
            }
        }
    }

    private LinearLayout buildNavItem(
            String icon,
            String label
    ) {

        LinearLayout item =
                new LinearLayout(this);

        item.setOrientation(
                LinearLayout.VERTICAL
        );

        item.setGravity(
                Gravity.CENTER
        );

        TextView iconView =
                createText(
                        icon,
                        22,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        iconView.setGravity(
                Gravity.CENTER
        );

        TextView labelView =
                createText(
                        label,
                        11,
                        Color.GRAY,
                        Typeface.BOLD
                );

        labelView.setGravity(
                Gravity.CENTER
        );

        item.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(35)
                )
        );

        item.addView(
                labelView,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(25)
                )
        );

        return item;
    }

    private LinearLayout buildNavPostItem() {

        LinearLayout item =
                new LinearLayout(this);

        item.setOrientation(
                LinearLayout.VERTICAL
        );

        item.setGravity(
                Gravity.CENTER
        );

        TextView plus =
                createText(
                        "+",
                        30,
                        Color.WHITE,
                        Typeface.BOLD
                );

        plus.setGravity(
                Gravity.CENTER
        );

        plus.setBackground(
                roundedBackground(
                        ORANGE,
                        dp(30)
                )
        );

        item.addView(
                plus,
                new LinearLayout.LayoutParams(
                        dp(52),
                        dp(52)
                )
        );

        return item;
    }

    private TextView createText(
            String text,
            int size,
            int color,
            int style
    ) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setTypeface(
                Typeface.DEFAULT,
                style
        );

        return view;
    }

    private GradientDrawable roundedBackground(
            int color,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);

        drawable.setCornerRadius(
                radius
        );

        return drawable;
    }

    private GradientDrawable roundedBorderedBackground(
            int fillColor,
            int borderColor,
            int borderWidth,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(fillColor);

        drawable.setStroke(
                borderWidth,
                borderColor
        );

        drawable.setCornerRadius(
                radius
        );

        return drawable;
    }

    private int dp(int value) {

        return (int)
                (value *
                        getResources()
                                .getDisplayMetrics()
                                .density +
                        0.5f);
    }

    private LinearLayout buildQuickCard(
            String icon,
            String title,
            String subtitle,
            int accentColor
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.HORIZONTAL
        );

        card.setGravity(
                Gravity.CENTER_VERTICAL
        );

        card.setPadding(
                dp(15),
                dp(12),
                dp(15),
                dp(12)
        );

        card.setBackground(
                roundedBackground(
                        Color.WHITE,
                        dp(15)
                )
        );

        TextView iconView =
                createText(
                        icon,
                        25,
                        accentColor,
                        Typeface.NORMAL
                );

        iconView.setTextColor(
                accentColor
        );

        card.addView(
                iconView,
                new LinearLayout.LayoutParams(
                        dp(45),
                        dp(45)
                )
        );

        LinearLayout text =
                new LinearLayout(this);

        text.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView titleView =
                createText(
                        title,
                        15,
                        Color.BLACK,
                        Typeface.BOLD
                );

        TextView subtitleView =
                createText(
                        subtitle,
                        12,
                        Color.GRAY,
                        Typeface.NORMAL
                );

        text.addView(
                titleView
        );

        text.addView(
                subtitleView
        );

        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                );

        textParams.leftMargin =
                dp(12);

        card.addView(
                text,
                textParams
        );

        return card;
    }
}