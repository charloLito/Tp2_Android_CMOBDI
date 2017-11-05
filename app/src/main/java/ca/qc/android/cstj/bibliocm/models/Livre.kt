package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json

class Livre(jsonObject: Json) : Item() {

    var titre:String=jsonObject.obj().getString("titre")
    var auteur:String=jsonObject.obj().getString("auteur")
    var image:String=jsonObject.obj().getString("image")
    var href:String=jsonObject.obj().getString("href")

    override fun getAffichage() : String {
        return titre
    }
}