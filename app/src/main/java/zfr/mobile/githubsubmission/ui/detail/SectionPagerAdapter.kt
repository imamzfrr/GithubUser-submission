package zfr.mobile.githubsubmission.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import zfr.mobile.githubsubmission.R

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager, data: Bundle, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab1, R.string.tab2)

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            0 -> FragmentFollowers()
            1 -> FragmentFollowing()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
        fragment.arguments = this.fragmentBundle
        return fragment
    }

    fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }
}
