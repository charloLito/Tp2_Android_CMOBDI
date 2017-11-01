package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json

class Livre(jsonObject: Json) {

    var titre:String=jsonObject.obj().getString("titre")
    var auteur:String=jsonObject.obj().getString("auteur")
    var href:String=jsonObject.obj().getString("href")
}