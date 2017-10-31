package ca.qc.android.cstj.bibliocm.models

import com.github.kittinunf.fuel.android.core.Json
import org.json.JSONObject
/**
 * Created by Administrateur on 2017-10-31.
 */
class Livres(jsonObject: Json){
    var titre:String=jsonObject.obj().getString("titre")
    var prix:Double=jsonObject.obj().getDouble("prix")


}