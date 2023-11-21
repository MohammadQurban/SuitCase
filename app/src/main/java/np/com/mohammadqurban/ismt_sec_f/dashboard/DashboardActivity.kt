package np.com.mohammadqurban.ismt_sec_f.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import np.com.mohammadqurban.ismt_sec_f.AppConstants
import np.com.mohammadqurban.ismt_sec_f.HomeActivity
import np.com.mohammadqurban.ismt_sec_f.R
import np.com.mohammadqurban.ismt_sec_f.dashboard.fragments.HomeFragment
import np.com.mohammadqurban.ismt_sec_f.dashboard.fragments.ProfileFragment
import np.com.mohammadqurban.ismt_sec_f.dashboard.fragments.ShopFragment
import np.com.mohammadqurban.ismt_sec_f.databinding.ActivityDashboardBinding
import np.com.mohammadqurban.ismt_sec_f.db.User
import np.com.mohammadqurban.ismt_sec_f.login.LoginActivity

class DashboardActivity : AppCompatActivity() {
    private val tag = "DashboardActivity"
    private lateinit var dashboardViewBinding: ActivityDashboardBinding
    private val homeFragment = HomeFragment.newInstance()
    private val shopFragment = ShopFragment.newInstance()
    private val profileFragment = ProfileFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardViewBinding.root)

        val receivedIntent = intent
        val receivedLoginData = receivedIntent
            .getParcelableExtra<User>(AppConstants.KEY_LOGIN_DATA)

        Log.i(tag, "Received Email : ".plus(receivedLoginData?.email))
        Log.i(tag, "Received Password : ".plus(receivedLoginData?.password))

        setUpViews()
    }

    private fun setUpViews() {
        setupFragmentContainerView()
        setUpBottomNavigationView()
    }

    private fun setupFragmentContainerView() {
        loadFragmentInFcv(homeFragment)
    }

    private fun setUpBottomNavigationView() {
        dashboardViewBinding.bnvDashboard.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    loadFragmentInFcv(homeFragment)
                    true
                }

                R.id.menu_shop -> {
                    loadFragmentInFcv(shopFragment)
                    true
                }


                else -> {
                    loadFragmentInFcv(profileFragment)
                    true
                }
            }
        }

    }

    private fun loadFragmentInFcv(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(dashboardViewBinding.fcvDashboard.id, fragment)
            .commit()

    }
}