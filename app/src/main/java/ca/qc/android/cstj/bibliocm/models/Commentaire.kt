package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json


class Commentaire(jsonObject: Json) {


    var message:String=jsonObject.obj().getString("message")
    var etoile:Int=jsonObject.obj().getInt("etoile")
    var prenom:String=jsonObject.obj().getString("prenom")
    var nom:String=jsonObject.obj().getString("nom")
    var dateCommentaire:String=jsonObject.obj().getString("dateCommentaire")
    var href:String=jsonObject.obj().getString("href")

}