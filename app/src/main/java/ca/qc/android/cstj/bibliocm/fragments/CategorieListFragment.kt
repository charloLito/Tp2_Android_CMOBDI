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
import ca.qc.android.cstj.bibliocm.helpers.CATEGORIE_URL
import ca.qc.android.cstj.bibliocm.models.Categorie
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet


//Un fragment est comme un UserControl en WPF, mais ce n'est pas

class CategorieListFragment : Fragment() {

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

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categorie_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            // Récupérer les données d'une sucursale grâce à notre Service Web développé au TP1
            CATEGORIE_URL.httpGet().responseJson{ request, response, result ->
                view.adapter= ItemRecyclerViewAdapter(createCategorieList(result.get()),mListener)
            }
        }
        return view
    }


    fun onButtonPressed(categorie: Categorie) {
        if (mListener != null) {
            mListener!!.onListFragmentInteraction(categorie)
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    //Crée la liste de toutes les succursales grâce au Json reçu par notre service web
    fun createCategorieList(json: Json):List<Categorie>{
        var categories= mutableListOf<Categorie>()
        val tabJson=json.array()

        for (i in 0.. (json.array().length()-1)){
            categories.add(Categorie(Json(tabJson[i].toString())))
        }
        return categories
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentItemInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }







    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): CategorieListFragment {
            val fragment = CategorieListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
