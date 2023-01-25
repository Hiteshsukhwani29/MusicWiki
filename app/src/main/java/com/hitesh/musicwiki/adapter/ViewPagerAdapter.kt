package com.hitesh.musicwiki.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hitesh.musicwiki.ui.albums.AlbumsFragment
import com.hitesh.musicwiki.ui.artists.ArtistsFragment
import com.hitesh.musicwiki.ui.tracks.TracksFragment

class ViewPagerAdapter(fm: FragmentManager, val tag: String) : FragmentPagerAdapter(fm) {

    private val count = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment(tag)
            1 -> ArtistsFragment(tag)
            2 -> TracksFragment(tag)
            else -> AlbumsFragment(tag)
        }
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Albums"
            1 -> "Artists"
            2 -> "Tracks"
            else -> "Albums"
        }
    }
}
