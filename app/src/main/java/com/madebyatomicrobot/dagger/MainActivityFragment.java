package com.madebyatomicrobot.dagger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityFragment extends Fragment {

    public interface MainActivityFragmentHost {
        void inject(MainActivityFragment fragment);
    }

    private MainActivityFragmentHost host;

    private TextView messageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        host = (MainActivityFragmentHost) context;
    }

    @Override
    public void onDetach() {
        host = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        messageView = (TextView) view.findViewById(R.id.message);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Before this point anything marked with @Inject will be null!
        // We currently aren't injecting anything but wanted to demonstrate the
        // activity-fragment interaction.
        host.inject(this);
    }

    public void showMessage(String message) {
        messageView.setText(message);
    }
}
