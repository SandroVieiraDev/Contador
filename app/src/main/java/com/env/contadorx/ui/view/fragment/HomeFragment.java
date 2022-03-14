package com.env.contadorx.ui.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.env.contadorx.R;
import com.env.contadorx.base.BaseFragment;
import com.env.contadorx.ui.view.binding.HomeFragmentViewBinding;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
    private HomeFragmentViewBinding binding;
    private boolean countingTimer;
    private Timer timer = new Timer();
    private TimerTask timerTask;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        binding = new HomeFragmentViewBinding(fragmentView);
        init();

        return fragmentView;
    }

    @Override
    protected void init() {
        binding.linearTimer.setOnClickListener(this);
        binding.imageMinus.setOnClickListener(this);
        binding.imageReset.setOnClickListener(this);
        binding.imagePlus.setOnClickListener(this);

        binding.linearTimer.setOnLongClickListener(this);

        binding.getViewRoot().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearTimer:
                stopStartTimer();
                break;
            case R.id.imageMinus:
                minusScore();
                break;
            case R.id.imageReset:
                resetScore();
                break;
            case R.id.imagePlus:
                plusScore();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        resetTimer();
        return true;
    }

    public void resetTimer(){
        if(countingTimer){
            countingTimer = false;
            timerTask.cancel();
        }
        binding.textTimer.setText("00:00");
    }

    public void stopStartTimer(){
        if(countingTimer){
            countingTimer = false;
            timerTask.cancel();
        } else {
            countingTimer = true;
            runTimer();
        }
    }


    public void minusScore(){
        String score = binding.textScore.getText().toString();
        if(!score.equals("0")){
            int c = Integer.parseInt(score); c--;
            binding.textScore.setText(String.valueOf(c));
        }
    }

    public void plusScore(){
        String score = binding.textScore.getText().toString();
        int c = Integer.parseInt(score); c++;
        binding.textScore.setText(String.valueOf(c));

    }

    public void resetScore(){
        binding.textScore.setText("0");
    }

    public void runTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread((Runnable) () -> timeCount());
                }
            };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }

    public void timeCount(){
        int sec = Integer.parseInt(binding.textTimer.getText().toString().substring(3, 5));
        sec++;
        String min = binding.textTimer.getText().toString().substring(0,2);
        if(sec==60){
            int m = Integer.parseInt(min);
            m++;
            min = new DecimalFormat("00").format(m);
            sec = 0;
        }
        binding.textTimer.setText(min + ":" + new DecimalFormat("00").format(sec));
    }
}