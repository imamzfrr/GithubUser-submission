package zfr.mobile.githubsubmission.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator
import zfr.mobile.githubsubmission.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra username"
    }

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel<Any?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UserDetailViewModel::class.java] as UserDetailViewModel<Any?>

        if (username != null) {
            viewModel.setDetailUser(username)
        }
        viewModel.getDetailUser().observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} followers"
                    tvFollowing.text = "${it.following} following"
                    Glide.with(this@UserDetailActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .circleCrop()
                        .into(ivProfile)
                }
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle, lifecycle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tab, viewPager) { tab, position ->
                tab.text = sectionPagerAdapter.getPageTitle(position)
            }.attach()
        }
    }
}
