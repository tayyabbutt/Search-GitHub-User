package com.inowave.test.presenter;

import android.content.Context;


import com.inowave.test.models.Users;
import com.inowave.test.networking.RetrofitClient;
import com.inowave.test.utils.Utility;
import com.inowave.test.view.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {
    private MainActivityView view;
    private Users user;
    private Context mContext;

    public MainActivityPresenter(MainActivityView view, Context context) {
        this.view = view;
        this.mContext = context;
    }

    public void searchUser(String email) {
        if (Utility.isNetworkAvailable(mContext)) {

            user = null;
            view.showLoadingDialog();
            Call<Users> searchUserCall = RetrofitClient.getInstance().getApiService().getUser(Utility.getUserHeader(), email);
            searchUserCall.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                        view.hideLoadingDialog();
                        user = response.body();
                        view.showAndUpdateUser(user);
                        view.showImageLoadingProgress();

                    } else {
                        view.hideLoadingDialog();
                        view.showErrorDialog("User not found");

                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    view.hideLoadingDialog();
                    view.showErrorDialog(t.toString());
                }
            });
        } else {
            view.showNetworkErrorDialog();
        }
    }

}

