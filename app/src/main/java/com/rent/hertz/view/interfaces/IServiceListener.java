package com.rent.hertz.view.interfaces;

import com.rent.hertz.model.interfaces.IModel;

public interface IServiceListener {

    void onComplete(IModel model);
    void startLoading();
    void stopLoading();
    void onError(String errorMessage);

}
