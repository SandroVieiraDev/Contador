package com.env.contadorx.base;

import android.view.View;

public abstract class BaseViewBinding {
    private View viewRoot;

    public BaseViewBinding(View viewRoot) {
        this.viewRoot = viewRoot;
        initViews();
    }

    public View getViewRoot(){
        return viewRoot;
    }

    protected <V extends View> V find(int id){
        return viewRoot.findViewById(id);
    }

    protected abstract void initViews();

}
