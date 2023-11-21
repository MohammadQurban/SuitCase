package np.com.mohammadqurban.ismt_sec_f.dashboard.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import np.com.mohammadqurban.ismt_sec_f.AppConstants
import np.com.mohammadqurban.ismt_sec_f.HomeActivity
import np.com.mohammadqurban.ismt_sec_f.R
import np.com.mohammadqurban.ismt_sec_f.db.User
import np.com.mohammadqurban.ismt_sec_f.login.LoginActivity


class ProfileFragment : Fragment() {
        private val tag = "ProfileFragment"




        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_profile, container, false)

        }


        companion object {
            @JvmStatic
            fun newInstance() = ProfileFragment()
        }

    }

