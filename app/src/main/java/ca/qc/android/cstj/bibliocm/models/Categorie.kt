package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json



class Categorie(jsonObject: Json){

    var categorie:String=jsonObject.obj().getString("categorie")

}