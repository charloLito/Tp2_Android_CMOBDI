package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json


/**
 * Created by Administrateur on 2017-10-31.
 */
class Succursale(jsonObject: Json) : Item() {
    var appelatif:String=jsonObject.obj().getString("appelatif")
    var adresse:String=jsonObject.obj().getString("adresse")
    var ville:String=jsonObject.obj().getString("ville")
    var codePostal:String=jsonObject.obj().getString("codePostal")
    var province:String=jsonObject.obj().getString("province")
    var telephone:String=jsonObject.obj().getString("telephone")
    var telecopieur:String = jsonObject.obj().getString("telecopieur")
    var information:String=jsonObject.obj().getString("information")
    var href: String=jsonObject.obj().getString("href")

    override fun getAffichage() : String {
        var appelatifCoupe: String



        var index: Int

        index = appelatif.indexOf(" ", 0)

        if(index > 0) {
            appelatifCoupe = appelatif.substring(0, index)
            return appelatifCoupe
        }
        return appelatif

    }

}