package com.example.upsccartbyaman;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PodcastsFragment extends Fragment {

    // =========================================================
    // COLORS
    // =========================================================

    private static final int BLACK = Color.rgb(25, 25, 25);
    private static final int GRAY = Color.rgb(145, 145, 145);
    private static final int LIGHT_GRAY = Color.rgb(190, 190, 190);

    private static final int ORANGE = Color.rgb(239, 108, 24);

    private static final int PEACH = Color.rgb(255, 222, 208);
    private static final int WHITE = Color.WHITE;

    // =========================================================
    // DATA
    // =========================================================

    private final List<Podcast> podcasts = new ArrayList<>();

    private LinearLayout listContainer;
    private TextView noResults;

    // =========================================================
    // MODEL
    // =========================================================

    private static class Podcast {

        String title;
        String topics;
        String chapters;

        Podcast(
                String title,
                String topics,
                String chapters
        ) {
            this.title = title;
            this.topics = topics;
            this.chapters = chapters;
        }
    }

    // =========================================================
    // CREATE VIEW
    // =========================================================

    @Nullable
    @Override
    public View onCreateView(
            @NonNull android.view.LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        Context context = requireContext();

        loadData();

        // =====================================================
        // ROOT
        // =====================================================

        LinearLayout root = new LinearLayout(context);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setBackgroundColor(
                Color.WHITE
        );

        // =====================================================
        // HEADER
        // =====================================================

        root.addView(
                createHeader(context)
        );

        // =====================================================
        // SEARCH
        // =====================================================

        root.addView(
                createSearchBar(context)
        );

        // =====================================================
        // SCROLL CONTENT
        // =====================================================

        ScrollView scrollView =
                new ScrollView(context);

        scrollView.setFillViewport(true);

        scrollView.setVerticalScrollBarEnabled(false);

        listContainer =
                new LinearLayout(context);

        listContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        listContainer.setPadding(
                dp(0),
                dp(4),
                dp(0),
                dp(30)
        );

        scrollView.addView(
                listContainer
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
        // NO RESULT
        // =====================================================

        noResults =
                new TextView(context);

        noResults.setText(
                "No podcasts found"
        );

        noResults.setTextColor(
                GRAY
        );

        noResults.setTextSize(
                16
        );

        noResults.setGravity(
                Gravity.CENTER
        );

        noResults.setVisibility(
                View.GONE
        );

        root.addView(
                noResults,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(70)
                )
        );

        // =====================================================
        // SHOW DATA
        // =====================================================

        showPodcasts(
                podcasts
        );

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private View createHeader(
            Context context) {

        LinearLayout header =
                new LinearLayout(context);

        header.setOrientation(
                LinearLayout.HORIZONTAL
        );

        header.setGravity(
                Gravity.CENTER_VERTICAL
        );

        header.setPadding(
                responsiveSidePadding(),
                dp(5),
                responsiveSidePadding(),
                dp(3)
        );

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        TextView title =
                new TextView(context);

        title.setText(
                "Podcasts"
        );

        title.setTextColor(
                BLACK
        );

        title.setTextSize(
                getScreenWidth() < 360 ? 26 : 29
        );

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setGravity(
                Gravity.CENTER_VERTICAL
        );

        header.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        dp(60),
                        1
                )
        );

        // -----------------------------------------------------
        // NOTIFICATION
        // -----------------------------------------------------

        TextView bell =
                new TextView(context);

        bell.setText(
                "♧"
        );

        bell.setTextSize(
                27
        );

        bell.setTextColor(
                Color.rgb(160, 160, 160)
        );

        bell.setGravity(
                Gravity.CENTER
        );

        header.addView(
                bell,
                new LinearLayout.LayoutParams(
                        dp(42),
                        dp(55)
                )
        );

        return header;
    }

    // =========================================================
    // SEARCH BAR
    // =========================================================

    private View createSearchBar(
            Context context) {

        LinearLayout wrapper =
                new LinearLayout(context);

        wrapper.setOrientation(
                LinearLayout.HORIZONTAL
        );

        wrapper.setGravity(
                Gravity.CENTER_VERTICAL
        );

        wrapper.setPadding(
                responsiveSidePadding(),
                dp(4),
                responsiveSidePadding(),
                dp(10)
        );

        // -----------------------------------------------------
        // SEARCH BACKGROUND
        // -----------------------------------------------------

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(
                Color.rgb(247, 247, 247)
        );

        background.setCornerRadius(
                dp(25)
        );

        background.setStroke(
                dp(1),
                Color.rgb(238, 238, 238)
        );

        // -----------------------------------------------------
        // ICON
        // -----------------------------------------------------

        TextView icon =
                new TextView(context);

        icon.setText(
                "⌕"
        );

        icon.setTextSize(
                27
        );

        icon.setTextColor(
                Color.rgb(125, 125, 125)
        );

        icon.setGravity(
                Gravity.CENTER
        );

        wrapper.addView(
                icon,
                new LinearLayout.LayoutParams(
                        dp(43),
                        dp(48)
                )
        );

        // -----------------------------------------------------
        // EDIT TEXT
        // -----------------------------------------------------

        EditText search =
                new EditText(context);

        search.setHint(
                "Search podcasts"
        );

        search.setHintTextColor(
                Color.rgb(160, 160, 160)
        );

        search.setTextColor(
                BLACK
        );

        search.setTextSize(
                16
        );

        search.setSingleLine(
                true
        );

        search.setMaxLines(
                1
        );

        search.setEllipsize(
                TextUtils.TruncateAt.END
        );

        search.setPadding(
                0,
                0,
                dp(12),
                0
        );

        search.setBackground(
                null
        );

        wrapper.addView(
                search,
                new LinearLayout.LayoutParams(
                        0,
                        dp(48),
                        1
                )
        );

        wrapper.setBackground(
                background
        );

        // -----------------------------------------------------
        // SEARCH LISTENER
        // -----------------------------------------------------

        search.addTextChangedListener(
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

                        searchPodcasts(
                                s.toString()
                        );
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        return wrapper;
    }

    // =========================================================
    // PODCAST CARD
    // =========================================================

    private View createPodcastCard(
            Context context,
            Podcast podcast) {

        LinearLayout card =
                new LinearLayout(context);

        card.setOrientation(
                LinearLayout.HORIZONTAL
        );

        card.setGravity(
                Gravity.CENTER_VERTICAL
        );

        card.setPadding(
                responsiveCardPadding(),
                dp(12),
                dp(10),
                dp(12)
        );

        // -----------------------------------------------------
        // CARD BACKGROUND
        // -----------------------------------------------------

        GradientDrawable cardBackground =
                new GradientDrawable();

        cardBackground.setColor(
                WHITE
        );

        cardBackground.setCornerRadius(
                dp(22)
        );

        cardBackground.setStroke(
                dp(1),
                Color.rgb(248, 248, 248)
        );

        card.setBackground(
                cardBackground
        );

        // -----------------------------------------------------
        // FOLDER
        // -----------------------------------------------------

        int iconSize =
                getScreenWidth() < 360
                        ? dp(72)
                        : dp(88);

        FrameLayout folder =
                createFolderIcon(
                        context,
                        iconSize
                );

        LinearLayout.LayoutParams folderParams =
                new LinearLayout.LayoutParams(
                        iconSize,
                        iconSize
                );

        folderParams.setMargins(
                0,
                0,
                responsiveIconSpacing(),
                0
        );

        card.addView(
                folder,
                folderParams
        );

        // -----------------------------------------------------
        // CONTENT
        // -----------------------------------------------------

        LinearLayout content =
                new LinearLayout(context);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setGravity(
                Gravity.CENTER_VERTICAL
        );

        LinearLayout.LayoutParams contentParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );

        card.addView(
                content,
                contentParams
        );

        // =====================================================
        // TITLE ROW
        // =====================================================

        LinearLayout titleRow =
                new LinearLayout(context);

        titleRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        titleRow.setGravity(
                Gravity.CENTER_VERTICAL
        );

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        TextView title =
                new TextView(context);

        title.setText(
                podcast.title
        );

        title.setTextColor(
                BLACK
        );

        title.setTextSize(
                getScreenWidth() < 360
                        ? 17
                        : 20
        );

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setMaxLines(
                2
        );

        title.setEllipsize(
                TextUtils.TruncateAt.END
        );

        title.setIncludeFontPadding(
                false
        );

        title.setLineSpacing(
                0,
                1.08f
        );

        titleRow.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        // -----------------------------------------------------
        // ARROW
        // -----------------------------------------------------

        TextView arrow =
                new TextView(context);

        arrow.setText(
                "›"
        );

        arrow.setTextColor(
                Color.rgb(220, 220, 220)
        );

        arrow.setTextSize(
                34
        );

        arrow.setGravity(
                Gravity.CENTER
        );

        arrow.setIncludeFontPadding(
                false
        );

        titleRow.addView(
                arrow,
                new LinearLayout.LayoutParams(
                        dp(25),
                        dp(40)
                )
        );

        content.addView(
                titleRow
        );

        // =====================================================
        // META INFORMATION
        // =====================================================

        HorizontalScrollView metaScroll =
                new HorizontalScrollView(context);

        metaScroll.setHorizontalScrollBarEnabled(
                false
        );

        metaScroll.setFillViewport(
                false
        );

        LinearLayout meta =
                new LinearLayout(context);

        meta.setOrientation(
                LinearLayout.HORIZONTAL
        );

        meta.setGravity(
                Gravity.CENTER_VERTICAL
        );

        // -----------------------------------------------------
        // TOPICS
        // -----------------------------------------------------

        TextView topics =
                createMetaText(
                        context,
                        "↳  " + podcast.topics
                );

        meta.addView(
                topics,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(28)
                )
        );

        // -----------------------------------------------------
        // GAP
        // -----------------------------------------------------

        View gap =
                new View(context);

        meta.addView(
                gap,
                new LinearLayout.LayoutParams(
                        dp(14),
                        1
                )
        );

        // -----------------------------------------------------
        // CHAPTERS
        // -----------------------------------------------------

        TextView chapters =
                createMetaText(
                        context,
                        "▤  " + podcast.chapters
                );

        meta.addView(
                chapters,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(28)
                )
        );

        metaScroll.addView(
                meta,
                new HorizontalScrollView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        dp(30)
                )
        );

        content.addView(
                metaScroll
        );

        // -----------------------------------------------------
        // CARD CLICK
        // -----------------------------------------------------

        card.setOnClickListener(
                v -> {

                    // Open podcast details here

                }
        );

        // =====================================================
        // CARD MARGINS
        // =====================================================

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        cardParams.setMargins(
                0,
                dp(5),
                0,
                dp(10)
        );

        card.setLayoutParams(
                cardParams
        );

        return card;
    }

    // =========================================================
    // FOLDER ICON
    // =========================================================

    private FrameLayout createFolderIcon(
            Context context,
            int size) {

        FrameLayout frame =
                new FrameLayout(context);

        GradientDrawable background =
                new GradientDrawable();

        background.setColor(
                PEACH
        );

        background.setCornerRadius(
                dp(17)
        );

        frame.setBackground(
                background
        );

        // -----------------------------------------------------
        // WHITE FOLDER
        // -----------------------------------------------------

        int folderWidth =
                (int) (size * 0.48f);

        int folderHeight =
                (int) (size * 0.32f);

        View folder =
                new View(context);

        GradientDrawable folderBg =
                new GradientDrawable();

        folderBg.setColor(
                Color.WHITE
        );

        folderBg.setCornerRadius(
                dp(4)
        );

        folder.setBackground(
                folderBg
        );

        FrameLayout.LayoutParams folderParams =
                new FrameLayout.LayoutParams(
                        folderWidth,
                        folderHeight,
                        Gravity.CENTER
                );

        frame.addView(
                folder,
                folderParams
        );

        return frame;
    }

    // =========================================================
    // META TEXT
    // =========================================================

    private TextView createMetaText(
            Context context,
            String text) {

        TextView view =
                new TextView(context);

        view.setText(
                text
        );

        view.setTextColor(
                LIGHT_GRAY
        );

        view.setTextSize(
                getScreenWidth() < 360
                        ? 12
                        : 14
        );

        view.setGravity(
                Gravity.CENTER_VERTICAL
        );

        view.setSingleLine(
                true
        );

        view.setIncludeFontPadding(
                false
        );

        return view;
    }

    // =========================================================
    // DATA
    // =========================================================

    private void loadData() {

        podcasts.clear();

        podcasts.add(
                new Podcast(
                        "Social Justice (comming soon)",
                        "30 sub-topics",
                        "59 chapters"
                )
        );

        podcasts.add(
                new Podcast(
                        "International Relations & Economy (comming soon)",
                        "28 sub-topics",
                        "34 chapters"
                )
        );

        podcasts.add(
                new Podcast(
                        "Science & Technology (comming soon)",
                        "92 sub-topics",
                        "59 chapters"
                )
        );

        podcasts.add(
                new Podcast(
                        "Environment & Security (comming soon)",
                        "52 sub-topics",
                        "75 chapters"
                )
        );

        podcasts.add(
                new Podcast(
                        "Ethics & Disaster Management (comming soon)",
                        "24 sub-topics",
                        "48 chapters"
                )
        );
    }

    // =========================================================
    // SHOW PODCASTS
    // =========================================================

    private void showPodcasts(
            List<Podcast> data) {

        if (listContainer == null) {
            return;
        }

        listContainer.removeAllViews();

        if (data.isEmpty()) {

            noResults.setVisibility(
                    View.VISIBLE
            );

            return;
        }

        noResults.setVisibility(
                View.GONE
        );

        for (Podcast podcast : data) {

            listContainer.addView(
                    createPodcastCard(
                            requireContext(),
                            podcast
                    )
            );
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchPodcasts(
            String query) {

        String search =
                query.trim().toLowerCase(
                        Locale.getDefault()
                );

        if (search.isEmpty()) {

            showPodcasts(
                    podcasts
            );

            return;
        }

        List<Podcast> filtered =
                new ArrayList<>();

        for (Podcast podcast : podcasts) {

            String title =
                    podcast.title.toLowerCase(
                            Locale.getDefault()
                    );

            if (title.contains(search)) {

                filtered.add(
                        podcast
                );
            }
        }

        showPodcasts(
                filtered
        );
    }

    // =========================================================
    // RESPONSIVE VALUES
    // =========================================================

    private int getScreenWidth() {

        return getResources()
                .getDisplayMetrics()
                .widthPixels;
    }

    private int responsiveSidePadding() {

        int width = getScreenWidth();

        if (width < dp(360)) {
            return dp(14);
        }

        if (width < dp(600)) {
            return dp(20);
        }

        return dp(28);
    }

    private int responsiveCardPadding() {

        int width = getScreenWidth();

        if (width < dp(360)) {
            return dp(14);
        }

        if (width < dp(600)) {
            return dp(20);
        }

        return dp(28);
    }

    private int responsiveIconSpacing() {

        int width = getScreenWidth();

        if (width < dp(360)) {
            return dp(12);
        }

        if (width < dp(600)) {
            return dp(18);
        }

        return dp(24);
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
        );
    }
}