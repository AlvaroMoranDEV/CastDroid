package com.alvaromoran.podcasts.ui.discover;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alvaromoran.podcasts.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverSearchFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private DiscoverViewModel discoverViewModel;

    public DiscoverSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DiscoverSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverSearchFragment newInstance() {
        DiscoverSearchFragment fragment = new DiscoverSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View createdView = inflater.inflate(R.layout.fragment_discover_search, container, false);
        // Button controls of the fragment
        Button button = createdView.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the content of the results view with the logic provided by the view model
                discoverViewModel.userActionSearchTriggered(button.getText().toString());

            }
        });

        return createdView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
