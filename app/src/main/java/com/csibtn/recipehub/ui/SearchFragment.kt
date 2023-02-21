package com.csibtn.recipehub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.csibtn.recipehub.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private var _searchFragmentBinding: FragmentSearchBinding? = null
    private val searchFragmentBinding: FragmentSearchBinding
        get() = checkNotNull(_searchFragmentBinding) {
            "Something went wrong with the search binding"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _searchFragmentBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchFragmentBinding.root
    }


}