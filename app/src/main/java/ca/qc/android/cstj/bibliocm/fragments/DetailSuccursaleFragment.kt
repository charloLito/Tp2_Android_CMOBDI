package ca.qc.android.cstj.bibliocm.fragments


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import ca.qc.android.cstj.bibliocm.R
import ca.qc.android.cstj.bibliocm.helpers.SUCCURSALE_URL
import ca.qc.android.cstj.bibliocm.models.Succursale
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.fragment_detail_succursale.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailSuccursaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailSuccursaleFragment(private val href: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //val url = "$SUCCURSALE_URL/$href"

        href.httpGet().responseJson { request, response, result ->
            when (response.httpStatusCode) {
                200 -> {
                    // Quand ça marche
                    val succursale = Succursale(result.get())
                    Toast.makeText(activity.applicationContext, "No:${succursale.appelatif}", Toast.LENGTH_SHORT).show()
                    lblNomSuccursaleDetail.text = succursale.appelatif
                    lblAdresseSuccursaleDetail.text = succursale.adresse
                    lblVilleSuccursaleDetail.text = succursale.ville
                    lblCodePostalSuccursaleDetail.text = succursale.codePostal
                    lblProvinceSuccursaleDetail.text = succursale.province
                    lblTelephoneSuccursaleDetail.text = succursale.telephone
                    lblTeleCopieurSuccursaleDetail.text = succursale.telecopieur
                    lblInformationSuccursaleDetail.text = succursale.information
                }

                404 -> {
                    // TODO: Marche pas
                }
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_succursale, container, false)
    }

}// Required empty public constructor
