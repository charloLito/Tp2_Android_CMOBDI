package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json


class Commentaire() {

    var message:String = ""
    var etoile:Int = 0
    var prenom:String = ""
    var nom:String = ""
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
        var monJson  =
                "{ \"etoile\": \"$etoile\", \"message\": \"$message\" ,\"prenom\": \"$prenom\", \"nom\": \"$nom\", \"dateCommentaire\": \"2017-02-12T12:31:00.000Z\" }"

        return monJson
    }
}