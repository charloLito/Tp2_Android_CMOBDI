package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import org.json.JSONArray

class Livre(jsonObject: Json) : Item() {

    var titre:String=jsonObject.obj().getString("titre")
    var auteur:String=jsonObject.obj().getString("auteur")
    var image:String=jsonObject.obj().getString("image")
    var prix:Double=jsonObject.obj().getDouble("prix")
    var href:String=jsonObject.obj().getString("href")
    var ISBN:String=jsonObject.obj().getString("ISBN")
    var categorieNom:String=jsonObject.obj().getJSONObject("categorie").getString("nom")
    var commentaires= mutableListOf<Commentaire>()

    fun ConstruireListeCommentaires(tabCom:JSONArray){

        commentaires.clear()
        for (i in 0.. (tabCom.length()-1)){
            commentaires.add(Commentaire(Json(tabCom[i].toString())))
        }

    }


    override fun getAffichage() : String {
        return titre
    }
}