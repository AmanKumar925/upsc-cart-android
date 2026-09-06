package com.example.upsccartbyaman;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        LinearLayout root =
                new LinearLayout(requireContext());

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(Color.WHITE);

        TextView icon =
                new TextView(requireContext());

        icon.setText("💬");
        icon.setTextSize(50);
        icon.setGravity(Gravity.CENTER);

        root.addView(icon);

        TextView title =
                new TextView(requireContext());

        title.setText("Chat");
        title.setTextSize(28);
        title.setTypeface(
                null,
                Typeface.BOLD
        );
        title.setTextColor(Color.DKGRAY);
        title.setGravity(Gravity.CENTER);

        root.addView(title);

        TextView sub =
                new TextView(requireContext());

        sub.setText(
                "Your buyer and seller conversations"
        );

        sub.setTextSize(16);
        sub.setTextColor(Color.GRAY);
        sub.setGravity(Gravity.CENTER);

        root.addView(sub);

        return root;
    }
}