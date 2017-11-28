package com.example.cspeir.collegeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

/**
 * Created by cspeir on 10/6/2017.
 */

public class SiblingFragment extends Fragment {

    TextView sFirstNameText;
    TextView sLastNameText;
    EditText sFirstNameEditText;
    EditText sLastNameEditText;
    Sibling mSibling;
    Button ssubmitButton;
    String email;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup v, Bundle bundle){
        super.onCreateView(inflater, v , bundle);
        int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);


        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF,null);

        View rootView = inflater.inflate(R.layout.fragment_sibling, v, false);
        ssubmitButton=(Button)rootView.findViewById(R.id.ssubmit_button);
        sFirstNameEditText = (EditText)rootView.findViewById(R.id.sfirst_name_edit);
        sLastNameEditText = (EditText)rootView.findViewById(R.id.slast_name_edit);
        sLastNameText = (TextView)rootView.findViewById(R.id.slast_name_text);
        sFirstNameText= (TextView)rootView.findViewById(R.id.sfirst_name_text);
        if (index!=-1){

            mSibling = (Sibling) Family.get().getFamily().get(index);
            String first = mSibling.getFirstName();
            String last= mSibling.getLastName();

            sFirstNameText.setText(first);
            sLastNameText.setText(last);

        }
        else{
            mSibling = new Sibling();

        }
        if (mSibling.getEmail()==null){
            mSibling.setEmail(email);
        }
        sFirstNameText.setText(mSibling.getFirstName());
        sLastNameText.setText(mSibling.getLastName());
        ssubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = "";
                String last = "";
                first = sFirstNameEditText.getText().toString().trim();
                last = sLastNameEditText.getText().toString().trim();
                mSibling.setFirstName(first);
                mSibling.setLastName(last);
                if ((first != null && !first.equals("")) && (last != null && !last.equals(""))) {
                    sFirstNameText.setText(first);
                    sLastNameText.setText(last);
                }
                Backendless.Persistence.save(mSibling, new AsyncCallback<Sibling>() {
                    @Override
                    public void handleResponse(Sibling response) {
                        Log.i("SiblingFragment", response.toString());
                        Family.get().deleteFamilyMember(mSibling);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.i("SiblingFragment", fault.getMessage());
                    }
                });
            }
        });
        return rootView;

    }
    @Override
    public void onStart(){
        super.onStart();
        int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        if (index!=-1){

            mSibling = (Sibling) Family.get().getFamily().get(index);
            String first = mSibling.getFirstName();
            String last= mSibling.getLastName();
            sFirstNameText.setText(first);
            sLastNameText.setText(last);

        }
    }
}
