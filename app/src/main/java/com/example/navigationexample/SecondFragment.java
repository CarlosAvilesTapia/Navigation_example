package com.example.navigationexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.navigationexample.databinding.FragmentFirstBinding;
import com.example.navigationexample.databinding.FragmentSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private final int checkedId = -1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("Name");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSecondBinding.inflate(getLayoutInflater());

        binding.welcomeNameText.setText(getString(R.string.bienvenido) + mParam1);

        binding.answerButton.setOnClickListener(v -> {

            Bundle bundle = new Bundle();

            boolean answer = false;

            if(binding.radioGroupAnswers1.getCheckedRadioButtonId() == -1){
                Toast.makeText(getContext(), R.string.debes_escoger_una_respuesta, Toast.LENGTH_SHORT).show();
                return;
            }

            if(binding.radioGroupAnswers1.getCheckedRadioButtonId() == binding.alternative3.getId()) {
                answer = true;
                bundle.putBoolean("Answer", answer);
                bundle.putString("FinalAnswer", "Congratulations, " + mParam1 + ". You are a Kanji expert!");
                Navigation.findNavController(getView()).
                        navigate(R.id.action_secondFragment_to_thirdFragment, bundle);
            } else {
                bundle.putBoolean("Answer", answer);
                bundle.putString("FinalAnswer", "That is not correct, " + mParam1 + ". Do it again!");
                Navigation.findNavController(getView()).
                        navigate(R.id.action_secondFragment_to_thirdFragment, bundle);
            }

        });

        return binding.getRoot();
    }
}
