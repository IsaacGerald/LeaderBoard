package com.example.volley;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.models.Entries;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class ProjectSubmission extends AppCompatActivity {
    private static final String TAG = "ProjectSubmission";

    private Entries mEntries;
    private Dialog mDialog;
    private EditText mName, mLastName, mEmail, mProjectLink;
    private MaterialButton mSubmitBtn;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private RequestQueue mQueue;
    private String mNameStr;
    private String mLastNameStr;
    private String mEmailStr;
    private String mMLinkStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        mSubmitBtn = findViewById(R.id.subButton);
        mName = findViewById(R.id.firstName);
        mEmail = findViewById(R.id.email);
        mLastName = findViewById(R.id.lastName);
        mProjectLink = findViewById(R.id.githubLink);
        mProgressBar = findViewById(R.id.progressbar);
        mToolbar = findViewById(R.id.submission_toolbar);

        mProgressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, R.color.color_Secondary),
                        PorterDuff.Mode.SRC_IN);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mQueue = Volley.newRequestQueue(this);


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                if (!isEmpty(mName.getText().toString())) {
                    mNameStr = mName.getText().toString();
                 if (!isEmpty(mLastName.getText().toString())) {
                        mLastNameStr = mLastName.getText().toString();
                        if (!isEmpty(mEmail.getText().toString())) {
                            mEmailStr = mEmail.getText().toString();
                            if (!isEmpty(mProjectLink.getText().toString())) {
                                mMLinkStr = mProjectLink.getText().toString();
                                confirmDialog();
                            } else {
                                mProjectLink.setError("Enter your projectLink!");
                            }
                        } else {
                            mEmail.setError("Enter your Email!");
                        }
                    } else {
                        mLastName.setError("Enter your last name!");
                    }
                } else {
                    mName.setError("Enter your name!");
                }
            }
        });
        
    }


    private void confirmDialog() {


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_dialog);
        ImageButton cancel = dialog.findViewById(R.id.cancel);
        Button confirm = dialog.findViewById(R.id.btnConfirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mProgressBar.setVisibility(View.GONE);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              postData(mNameStr, mLastNameStr, mEmailStr, mMLinkStr);
              mProgressBar.setVisibility(View.VISIBLE);
              dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void successDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.success_dialog);
        mProgressBar.setVisibility(View.GONE);
        dialog.show();
        if (dialog.isShowing()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 5000);
        }



    }
    private  void failDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fail_dialog);
        mProgressBar.setVisibility(View.GONE);
        dialog.show();
        if (dialog.isShowing()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 5000);
        }


    }
    private boolean isEmpty(String text){
        return text.length() == 0;
    }

    private void postData(String name, String lastName, String emailLink, String githubLink) {
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ProjectSubmission.this, "Posting Data...", Toast.LENGTH_SHORT).show();
                          if (response.length() > 0){
                              successDialog();

                              mName.setText(null);
                              mLastName.setText(null);
                              mEmail.setText(null);
                              mProjectLink.setText(null);
                          }else {
                              Toast.makeText(ProjectSubmission.this, "Try Again", Toast.LENGTH_SHORT).show();
                          }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                failDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put(Constants.name, name );
                params.put(Constants.email, emailLink);
                params.put(Constants.projectLink, githubLink);
                params.put(Constants.lastName, lastName);
                return params;
            }
        };

        mQueue.add(request);
    }

}