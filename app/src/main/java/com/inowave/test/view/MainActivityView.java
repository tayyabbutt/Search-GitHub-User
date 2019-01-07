package com.inowave.test.view;


import com.inowave.test.models.Users;

public interface MainActivityView {
    void showLoadingDialog();
    void hideLoadingDialog();
    void showAndUpdateUser(Users user);
    void showImageLoadingProgress();
    void showErrorDialog(String error);
    void showNetworkErrorDialog();
}
