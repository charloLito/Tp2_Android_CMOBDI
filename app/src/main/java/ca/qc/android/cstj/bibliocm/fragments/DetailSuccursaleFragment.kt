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

        val url = "$SUCCURSALE_URL/$href"

        url.httpGet().responseJson {request,response,result ->
            when(response.httpStatusCode){
                200 -> {
                    // Quand ça marche
                    val succursale = Succursale(result.get())
                    Toast.makeText(activity.applicationContext, "No:${succursale.appelatif}", Toast.LENGTH_SHORT).show()
                    lblNomSuccursaleDetail.text = succursale.appelatif
                }

                404 -> {
                    // TODO: Marche pas
                }
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_succursale, container, false)
    }

    /*companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailSuccursaleFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): DetailSuccursaleFragment {
            val fragment = DetailSuccursaleFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }*/

}// Required empty public constructor
