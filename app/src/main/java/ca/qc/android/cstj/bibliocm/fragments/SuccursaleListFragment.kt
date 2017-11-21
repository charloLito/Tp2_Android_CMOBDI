package ca.qc.android.cstj.bibliocm.fragments

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ca.qc.android.cstj.bibliocm.R
import ca.qc.android.cstj.bibliocm.adapters.OnListFragmentItemInteractionListener
import ca.qc.android.cstj.bibliocm.adapters.ItemRecyclerViewAdapter
import ca.qc.android.cstj.bibliocm.helpers.SUCCURSALE_URL

import ca.qc.android.cstj.bibliocm.models.Succursale
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet

//Un fragment est comme un UserControl en WPF, mais ce n'est pas

class SuccursaleListFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentItemInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.succursale_list_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            //Récupérer les données d'une sucursale grâce à notre Service Web développé au TP1
            SUCCURSALE_URL.httpGet().responseJson{request,response,result ->
                view.adapter= ItemRecyclerViewAdapter(createSuccursaleList(result.get()),mListener)
            }
        }
        return view
    }

    //Crée la liste de toutes les succursales grâce au Json reçu par notre service web
    fun createSuccursaleList(json: Json):List<Succursale>{
        var succursales= mutableListOf<Succursale>()
        val tabJson=json.array()

        for (i in 0.. (json.array().length()-1)){
            succursales.add(Succursale(Json(tabJson[i].toString())))
        }
        return succursales
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentItemInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }




    companion object {

        private val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int): SuccursaleListFragment {
            val fragment = SuccursaleListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
