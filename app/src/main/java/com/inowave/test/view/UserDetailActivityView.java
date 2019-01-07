package com.inowave.test.view;

import com.inowave.test.models.Followers;

import java.util.List;

public interface UserDetailActivityView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showImageLoadingProgress();

    void showErrorDialog(String error);

    void showNetworkErrorDialog();

    void notifyRecyclerView(List<Followers> followers);

    void followersNotFound();
}
