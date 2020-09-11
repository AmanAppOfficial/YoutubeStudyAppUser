package e.aman.studytutorialapp.services;

import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface MyFirebaseService
{

        void onSuccess(List snapshot , ViewGroup container);
        void onSuccess(String e);
        void onFailure(String e);
}
