package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json



class Categorie(jsonObject: Json) : Item() {

    var categorie:String=jsonObject.obj().getString("categorie")
    var href:String=jsonObject.obj().getString("href")

    override fun getAffichage() : String {
        return categorie
    }
}