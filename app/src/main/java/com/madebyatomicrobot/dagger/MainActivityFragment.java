package com.madebyatomicrobot.dagger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityFragment extends Fragment {

    private TextView messageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        messageView = (TextView) view.findViewById(R.id.message);
        return view;
    }

    public void showMessage(String message) {
        messageView.setText(message);
    }
}
