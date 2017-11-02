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
import ca.qc.android.cstj.bibliocm.adapters.LivresRecyclerViewAdapter
import ca.qc.android.cstj.bibliocm.helpers.CATEGORIE_URL
import ca.qc.android.cstj.bibliocm.models.Item
import ca.qc.android.cstj.bibliocm.models.Livre
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet



class LivreListFragment(item: Item): Fragment() {



    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_livre_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }

            // Faire un get sur le href de la catégorie touchée.
            // "href": "https://issatherrien-tp01-olivierissacstj.c9users.io/categories/NmVhMjk1ZDctYzk2My00NGY3LTkxOGMtY2UyOWU3YTI1Mjdk"
            // Ajouter "/livres"



            CATEGORIE_URL.httpGet().responseJson{ request, response, result ->
                view.adapter= LivresRecyclerViewAdapter(createLivreList(result.get()), mListener)
            }
        }
        return view
    }

    //Crée la liste de toutes les succursales grâce au Json reçu par notre service web
    fun createLivreList(json: Json):List<Livre>{
        var livres= mutableListOf<Livre>()
        val tabJson=json.array()

        for (i in 0.. (json.array().length()-1)){
            livres.add(Livre(Json(tabJson[i].toString())))
        }
        return livres
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(livre: Livre)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): LivreListFragment {
            val fragment = LivreListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
