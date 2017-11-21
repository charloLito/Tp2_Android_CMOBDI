package ca.qc.android.cstj.bibliocm.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliocm.R

import ca.qc.android.cstj.bibliocm.fragments.LivreListFragment.OnListFragmentInteractionListener

import ca.qc.android.cstj.bibliocm.models.Livre
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.livre_card.view.*

//Un adapter fait le lien entre les données provenant de notre API  et les Fragments (User Control) de notre application (Ici, les livres et LivreListFragment).
//On utilise un adapter seulement pour une Liste de données (Recycler view)
class LivresRecyclerViewAdapter(private val mValues: List<Livre>,
                                private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<LivresRecyclerViewAdapter.ViewHolder>() {

    //Adapteur gère les cartes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.livre_card, parent, false)
        return ViewHolder(view)
    }

    //Pour chaque carte, je recois le viewholder (qui est l'élément graphique (carte)) et la position dans la liste où il est rendu
    //Il est le lien entre la carte et la données (item de la liste)
     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         //Un holder est un élément de la liste
        holder.bind(mValues[position])
         //Qu'est-ce qui arrive si on clique sur un élément de la liste
        holder.mView.setOnClickListener{
            mListener!!.onListLivreFragmentInteraction(holder.livre)

        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }


    //Indique quelle données va dans quel élément graphique
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var lblTitre = mView.lblTitre
        var lblAuteur = mView.lblAuteur
        var imgLivre = mView.imgLivre

        var livre: Livre? = null



        fun bind(livre: Livre){
            this.livre=livre

            lblTitre.text=livre.titre
            lblAuteur.text=livre.auteur

            var urlImg = livre.image

            Picasso.with(imgLivre.context).load(urlImg).placeholder(R.drawable.loading).fit().centerInside().into(imgLivre)

            //Picasso.with(imgLivre.context).load(R.drawable.load).into(imgLivre)

        }


    }
}
