package com.computersquid.razipoints.ui.views.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.computersquid.razipoints.R
import com.computersquid.razipoints.model.Action
import com.computersquid.razipoints.model.User
import com.computersquid.razipoints.ui.adapter.ActionsAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    //region Properties

    private var _actions = ArrayList<Action>()

    private lateinit var _user: User
    private lateinit var _actionsAdapter: ActionsAdapter
    private lateinit var _viewManager: RecyclerView.LayoutManager

    private var homeFragmentListener: HomeFragmentContract? = null

    //endregion


    //region LifeCycleMethods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _actions.add(Action(0, "Do thing 1", 1))
        _actions.add(Action(1, "Do thing 2", 2))
        _actions.add(Action(2, "Do thing -1", -1))
        _actions.add(Action(3, "Do thing -2", -2))

        _actionsAdapter = ActionsAdapter(context!!, R.layout.item_action, _actions)
        _viewManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = _viewManager
            adapter = _actionsAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeFragmentContract) {
            homeFragmentListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement HomeFragmentContract")
        }
    }

    override fun onDetach() {
        super.onDetach()
        homeFragmentListener = null
    }

    //endregion

    interface HomeFragmentContract {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        val TAG: String = this::class.java.simpleName

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
