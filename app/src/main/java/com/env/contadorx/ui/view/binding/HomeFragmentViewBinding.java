package com.env.contadorx.ui.view.binding;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.env.contadorx.R;
import com.env.contadorx.base.BaseViewBinding;

public class HomeFragmentViewBinding extends BaseViewBinding {
    public LinearLayout linearTimer;
    public TextView textScore, textTimer;
    public ImageView imageMinus, imageReset, imagePlus;

    public HomeFragmentViewBinding(View viewRoot) {
        super(viewRoot);
    }

    @Override
    protected void initViews() {
      linearTimer = find(R.id.linearTimer);
        textScore = find(R.id.textScore);
        textTimer = find(R.id.textTimer);
        imageMinus = find(R.id.imageMinus);
        imageReset = find(R.id.imageReset);
        imagePlus = find(R.id.imagePlus);
    }
}
