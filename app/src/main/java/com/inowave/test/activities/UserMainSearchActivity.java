package com.inowave.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inowave.test.R;
import com.inowave.test.models.Users;
import com.inowave.test.presenter.MainActivityPresenter;
import com.inowave.test.utils.LoadingDialog;
import com.inowave.test.utils.MyDialog;
import com.inowave.test.utils.Utility;
import com.inowave.test.view.MainActivityView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;


public class UserMainSearchActivity extends AppCompatActivity implements MainActivityView {

    private EditText searchEditText;
    private Button search_without_auth_btn;
    private ImageView userImage;
    private TextView userName;
    private CardView userProfileLayout;
    private Users user;
    private LoadingDialog dialog;
    private ProgressBar progressBar;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_search);
        getSupportActionBar().setTitle("Search Users");

        presenter = new MainActivityPresenter(this, UserMainSearchActivity.this);
        dialog = new LoadingDialog(UserMainSearchActivity.this);
        searchEditText = findViewById(R.id.email_edit_text);
        search_without_auth_btn = findViewById(R.id.search_with_out_auth);
        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_Name);
        userProfileLayout = findViewById(R.id.user_layout);
        progressBar = findViewById(R.id.prgressBar);
        progressBar.setVisibility(View.GONE);


        userProfileLayout.setVisibility(View.GONE);

        search_without_auth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isEmpty(searchEditText.getText())) {
                    presenter.searchUser(searchEditText.getText().toString());
                    searchEditText.setText("");
                } else {
                    MyDialog.showDialog(UserMainSearchActivity.this, "Invalid Email", "Please provide user name", null);
                    searchEditText.setText("");
                }
            }
        });
        userProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utility.OBJECT_EXTRAS, (Serializable) user);
                    Utility.launchActivity(UserMainSearchActivity.this, UserMainDetailActivity.class, bundle);
                }
            }
        });

    }

    @Override
    public void showLoadingDialog() {
        dialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        dialog.hide();
    }

    @Override
    public void showAndUpdateUser(Users user) {
        this.user = user;
        userProfileLayout.setVisibility(View.VISIBLE);
        userImage.setVisibility(View.VISIBLE);
        userName.setText(user.getName());
        Picasso.get().load(user.getAvatarUrl()).into(userImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showImageLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorDialog(String error) {
        MyDialog.showDialog(this, "Error", error, null);
    }

    @Override
    public void showNetworkErrorDialog() {
        MyDialog.showDialog(this, "Network Error", "Connect to other network to get start", null);
    }

}
