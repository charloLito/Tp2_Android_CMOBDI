package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json

class Livre(jsonObject: Json) : Item() {

    var titre:String=jsonObject.obj().getString("titre")
    var auteur:String=jsonObject.obj().getString("auteur")
    var image:String=jsonObject.obj().getString("image")
    // TODO: Ajouter la catégorie
    var prix:Double=jsonObject.obj().getDouble("prix")
    var href:String=jsonObject.obj().getString("href")
    var ISBN:String=jsonObject.obj().getString("ISBN")

    override fun getAffichage() : String {
        return titre
    }
}