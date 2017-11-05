package ca.qc.android.cstj.bibliocm.fragments


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import ca.qc.android.cstj.bibliocm.R
import ca.qc.android.cstj.bibliocm.models.Livre
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_livre.*
import kotlinx.android.synthetic.main.livre_card.*


class DetailLivreFragment(private val href: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        href.httpGet().responseJson { request, response, result ->
            when (response.httpStatusCode) {
                200 -> {
                    // Quand ça marche
                    val livre = Livre(result.get())


                    var urlImg = livre.image
                    Picasso.with(imgLivreDetail.context).load(urlImg).placeholder(R.drawable.loading).fit().centerInside().into(imgLivreDetail)

                    lblPrixDetail.text = livre.prix.toString()
                    lblAuteurDetail.text = livre.auteur
                    // TODO : Nom de la catégorie

                    lblISBNDetail.text = livre.ISBN





                }

                404 -> {
                    // TODO: Marche pas
                }
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_livre, container, false)
    }

}// Required empty public constructor
