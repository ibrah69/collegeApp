package com.example.cspeir.collegeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
 * Created by cspeir on 10/5/2017.
 */

public class GuardianFragment extends Fragment {
    TextView mFirstNameText;
    TextView mLastNameText;
    EditText mFirstNameEditText;
    EditText mLastNameEditText;
    TextView mOccupationText;
    EditText mOccupationEditText;
    Guardian mGuardian;
    Button mSubmitButton;
    String email;


    @Override
        public View onCreateView(LayoutInflater inflater , ViewGroup v, Bundle bundle){
        super.onCreateView(inflater, v , bundle);
        View rootView = inflater.inflate(R.layout.fragment_guardian, v, false);
        mSubmitButton= (Button)rootView.findViewById(R.id.gsubmit_button);
        mFirstNameEditText = (EditText)rootView.findViewById(R.id.gfirst_name_edit);
        mLastNameEditText = (EditText)rootView.findViewById(R.id.glast_name_edit);
        mLastNameText = (TextView)rootView.findViewById(R.id.glast_name_text);
        mFirstNameText= (TextView)rootView.findViewById(R.id.gfirst_name_text);
        mOccupationText= (TextView)rootView.findViewById(R.id.g_occupation_text);
        mOccupationEditText= (EditText)rootView.findViewById(R.id.goccupation_edit);

            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF,null);
        int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        if (index!=-1){

            mGuardian = (Guardian)Family.get().getFamily().get(index);
            String first = mGuardian.getFirstName();
            String last= mGuardian.getLastName();
            String occupation = mGuardian.getOccupation();
            mFirstNameText.setText(first);
            mLastNameText.setText(last);
            mOccupationText.setText(occupation);
        }
        else{
            mGuardian = new Guardian();
        }
        mFirstNameText.setText(mGuardian.getFirstName());
        mLastNameText.setText(mGuardian.getLastName());
        mOccupationText.setText(mGuardian.getOccupation());
        if (mGuardian.getEmail()==null){
            mGuardian.setEmail(email);
        }

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = "";
                String last = "";
                String occupation= "";
                first = mFirstNameEditText.getText().toString().trim();
                last= mLastNameEditText.getText().toString().trim();
                occupation = mOccupationEditText.getText().toString().trim();
                mGuardian.setFirstName(first);
                mGuardian.setLastName(last);
                mGuardian.setOccupation(occupation);
                if((first != null && !first.equals(""))&& (last!=null && !last.equals(""))&&(occupation!=null &&!occupation.equals(""))){
                    mFirstNameText.setText(first);
                    mLastNameText.setText(last);
                    mOccupationText.setText(occupation);
                }
               Backendless.Persistence.save(mGuardian, new AsyncCallback<Guardian>() {
                   @Override
                   public void handleResponse(Guardian response) {
                        Log.i("GuardianFragment", response.toString());
                        Family.get().deleteFamilyMember(mGuardian);
                   }

                   @Override
                   public void handleFault(BackendlessFault fault) {
                        Log.i("GuardianFragment", fault.getMessage());
                   }
               });




            }
        });
        return rootView;
    }
    @Override
    public void onStart(){
        super.onStart();

    }
}
