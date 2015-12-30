package chat.mobilecomputationproject.activities.chat_room.create_chat_room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import chat.mobilecomputationproject.R;

public class CreateChatRoomActivity extends AppCompatActivity{


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private CreateChatRoomTask mAuthTask = null;

    // UI references.
    private EditText mNameView;
    private EditText mDescriptionView;
    private View mProgressView;
    private View mCreateChatRoomFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_room);
        // Set up the login form.
        mNameView = (EditText) findViewById(R.id.name);
        mDescriptionView = (EditText) findViewById(R.id.description);

        Button mCreateButton = (Button) findViewById(R.id.create_button);
        mCreateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreateChatRoom();
            }
        });

        mCreateChatRoomFormView = findViewById(R.id.create_chat_room_form);
        mProgressView = findViewById(R.id.create_chat_room_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid name, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptCreateChatRoom() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mNameView.setError(null);
        mDescriptionView.setError(null);

        // Store values at the time of the creation attempt.
        String name = mNameView.getText().toString();
        String description = mDescriptionView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid description, if the user entered one.
        if (!TextUtils.isEmpty(description) && !isDescriptionValid(description)) {
            mDescriptionView.setError(getString(R.string.error_invalid_description));
            focusView = mDescriptionView;
            cancel = true;
        }

        // Check for a valid name.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!isNameValid(name)) {
            mNameView.setError(getString(R.string.error_invalid_name));
            focusView = mNameView;
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
            mAuthTask = new CreateChatRoomTask(name, description);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() > 0;
    }

    private boolean isDescriptionValid(String description) {
        //TODO: Replace this with your own logic
        return description.length() > 0;
    }

    /**
     * Shows the progress UI and hides the create_chat_room form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mCreateChatRoomFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mCreateChatRoomFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCreateChatRoomFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mCreateChatRoomFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous task used to create the chat room.
     */
    public class CreateChatRoomTask extends AsyncTask<Void, Void, Boolean> {

        private final String mName;
        private final String mDescription;

        CreateChatRoomTask(String name, String description) {
            mName = name;
            mDescription = description;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new chat room here.

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_creating_chat_room), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

