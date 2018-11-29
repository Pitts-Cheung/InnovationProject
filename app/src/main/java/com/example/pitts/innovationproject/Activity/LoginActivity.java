package com.example.pitts.innovationproject.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitts.innovationproject.BaseActivity;
import com.example.pitts.innovationproject.R;

import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private String[] DUMMY_CREDENTIALS = new String[]{
            "0902160324:Zpc980319", "0901160629:199836hyz"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mStunoView;
    private EditText mPasswordView;
    private EditText mUsernameEdit;
    private TextInputLayout mUsernameView;
    private View mLoginProgressView;
    private View mLoginFormView;
    private SharedPreferences mSharedPreferences;
    private Button mSignInButton;
    private LinearLayout mLoginMainView;
    private int screenHeight = 0;
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mSharedPreferences = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        if(mSharedPreferences.getBoolean("ISLOADED",false)){
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("STUNO",mSharedPreferences.getString("STUNO","0000000000"));
            startActivity(i);
            this.finish();
        }

        mLoginMainView = (LinearLayout)findViewById(R.id.login_main_view);
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight/3;

        mStunoView = (EditText) findViewById(R.id.stuno);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mLoginProgressView = findViewById(R.id.login_progress);
        mUsernameEdit = (EditText)findViewById(R.id.username);
        mUsernameView = (TextInputLayout)findViewById(R.id.username_view);
    }

    @Override
    protected void onResume(){
        super.onResume();

        mLoginMainView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
                    mLoginMainView.setBackground(getResources().getDrawable(R.mipmap.login_background_softinput));
                }
                else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
                    mLoginMainView.setBackground(getResources().getDrawable(R.mipmap.login_background));
                }
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mStunoView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String stuno = mStunoView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(stuno)) {
            mStunoView.setError(getString(R.string.error_stuno_required));
            focusView = mStunoView;
            cancel = true;
        } else if (!isStunoValid(stuno)) {
            mStunoView.setError(getString(R.string.error_invalid_stuno));
            focusView = mStunoView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(stuno, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isStunoValid(String stuno) {
        return stuno.length() == 10;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignInButton.setText(show ? " " : getResources().getString(R.string.action_sign_in));
            mLoginProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignInButton.setText(show ? " " : getResources().getString(R.string.action_sign_in));
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor data){

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mStuno;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mStuno = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: 身份验证

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mStuno)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                uiHandle.sendMessage(new Message());
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

        private Handler uiHandle = new Handler(){
            @Override
            public void handleMessage(Message msg){
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("STUNO",mStuno);
                editor.putString("PASSWORD",mPassword);
                editor.putBoolean("ISLOADED",true);
                editor.commit();

                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                i.putExtra("STUNO",mStuno);
                startActivity(i);
            }
        };

        public void addUser(String user){
            String[] temp = Arrays.copyOf(DUMMY_CREDENTIALS, DUMMY_CREDENTIALS.length+1);
            temp[temp.length-1] = user;
        }
    }
}

