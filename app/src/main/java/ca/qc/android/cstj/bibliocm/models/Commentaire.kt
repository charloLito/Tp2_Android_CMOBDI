package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose


class Commentaire() {

    @Expose var message:String = ""
    @Expose var etoile:Int = 0
    @Expose var prenom:String = ""
    @Expose var nom:String = ""
    var dateCommentaire:String = ""
    var href:String = ""

    constructor(jsonObject: Json) : this()
    {
        message=jsonObject.obj().getString("message")
        etoile=jsonObject.obj().getInt("etoile")
        prenom=jsonObject.obj().getString("prenom")
        nom=jsonObject.obj().getString("nom")
        dateCommentaire=jsonObject.obj().getString("dateCommentaire")
        href=jsonObject.obj().getString("href")

    }

    fun toJson() : String {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this)
    }
}