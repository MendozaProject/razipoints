package com.computersquid.razipoints.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.computersquid.razipoints.R
import com.computersquid.razipoints.data.model.Task
import com.computersquid.razipoints.ui.mvvm.BaseFragment
import com.computersquid.razipoints.utils.FragmentlessViewPagerItem
import com.computersquid.razipoints.ui.adapter.FragmentlessViewPagerAdapter
import com.computersquid.razipoints.ui.viewmodel.TaskCreatorViewModel
import com.computersquid.razipoints.ui.viewmodel.TaskCreatorViewModelImpl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_task_creator.*
import javax.inject.Inject


private const val TASK_PARAM = "task_argument"

class TaskCreatorFragment : BaseFragment() {

    private lateinit var pagerAdapter: FragmentlessViewPagerAdapter
    private lateinit var viewModel: TaskCreatorViewModel

    val NAME_POSITION = 0
    val DIFFICULTY_POSITION = 1

    private var task: Task? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            task = it.getParcelable(TASK_PARAM)
        }

        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(TaskCreatorViewModelImpl::class.java)

        val viewPagerItemList = ArrayList<FragmentlessViewPagerItem>(5)

        viewPagerItemList.add(FragmentlessViewPagerItem(
                R.layout.layout_task_creator_name,
                getString(R.string.title_create_task)))

        viewPagerItemList.add(FragmentlessViewPagerItem(
                R.layout.layout_task_creator_difficulty,
                getString(R.string.title_create_task)))

        pagerAdapter = FragmentlessViewPagerAdapter(context!!, viewPagerItemList)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_creator, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = pagerAdapter
        viewPager.getChildAt(1)
    }


    companion object {
        @JvmStatic val TAG: String = this::class.java.simpleName

        @JvmStatic
        fun newInstance(task: Task) =
                TaskCreatorFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(TASK_PARAM, task)
                    }
                }
    }
}
