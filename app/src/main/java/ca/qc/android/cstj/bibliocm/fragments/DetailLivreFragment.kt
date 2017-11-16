package ca.qc.android.cstj.bibliocm.fragments

import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ca.qc.android.cstj.bibliocm.R
import ca.qc.android.cstj.bibliocm.adapters.CommentaireRecyclerViewAdapter
import ca.qc.android.cstj.bibliocm.models.Commentaire
import ca.qc.android.cstj.bibliocm.models.Livre
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.commentaire_card.*
import kotlinx.android.synthetic.main.fragment_detail_livre.*


class DetailLivreFragment(private val href: String) : Fragment() {

    private lateinit var livre:Livre

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var leHref = href + "?expand=commentaires"

        leHref.httpGet().responseJson { request, response, result ->
            when (response.statusCode) {
                200 -> {
                    // Quand ça marche
                    livre = Livre(result.get())

                    val lesCommentaires = result.get().obj().getJSONArray("commentaires")
                    livre.ConstruireListeCommentaires(lesCommentaires)

                    var urlImg = livre.image
                    Picasso.with(imgLivreDetail.context).load(urlImg).placeholder(R.drawable.loading).fit().centerInside().into(imgLivreDetail)

                    lblPrixDetail.text = livre.prix.toString()
                    lblAuteurDetail.text = livre.auteur


                    lblCategorieDetail.text = livre.categorieNom
                    lblISBNDetail.text = livre.ISBN

                    lstCommentaires.layoutManager = LinearLayoutManager(context)
                    lstCommentaires.adapter = CommentaireRecyclerViewAdapter(livre.commentaires)
                    lstCommentaires.adapter.notifyDataSetChanged()

                    btnAjouter.setOnClickListener {

                        var nom = txtNom.text.toString()
                        var prenom=txtPrenom.text.toString()


                        var commentairePost: Commentaire = Commentaire()
                        commentairePost.nom = txtNom.text.toString()
                        commentairePost.prenom=txtPrenom.text.toString()
                        commentairePost.dateCommentaire = "2017-02-12T12:31:00.000Z"
                        commentairePost.etoile = ratingBar.rating.toInt()
                        commentairePost.message = txtCommentaire.text.toString()

                        var postHref = href + "/commentaires"

                        postHref.httpPost()
                                .header("Content-Type" to "application/json")
                                .body(commentairePost.toJson()).responseJson{ _ ,response, _ ->
                            when(response.statusCode){
                                201 -> {
                                    Toast.makeText(context, "Commentaire ajouté", Toast.LENGTH_SHORT).show()
                                    updateCommentaires(leHref)
                                    txtNom.text.clear()
                                    txtPrenom.text.clear()
                                    txtCommentaire.text.clear()
                                    ratingBar.rating=Float.MIN_VALUE

                                }
                            }
                        }



                        /*Fuel.post(postHref).header("Content-Type" to "application/json").body(commentairePost.toJson()).responseJson { request, response, result ->
                            when (response.httpStatusCode) {
                                201 -> Toast.makeText(context, "Commentaire Ajouter", Toast.LENGTH_SHORT).show()
                                404 -> Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show()
                                500 -> Toast.makeText(context, "Erreur Serveur", Toast.LENGTH_SHORT).show()
                            }*/
                        }
                    }
                }

        }
        return inflater.inflate(R.layout.fragment_detail_livre, container, false)

    }

    private fun updateCommentaires(href: String)
    {
        href.httpGet().responseJson { _, response, result ->
            when(response.statusCode) {
                200 -> {

                    val lesCommentaires = result.get().obj().getJSONArray("commentaires")
                    livre.ConstruireListeCommentaires(lesCommentaires)
                    lstCommentaires.adapter.notifyDataSetChanged()
                }
            }
        }
    }






}



