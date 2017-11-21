package ca.qc.android.cstj.bibliocm.adapters
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliocm.R
import ca.qc.android.cstj.bibliocm.models.Commentaire
import kotlinx.android.synthetic.main.commentaire_card.view.*

//Un adapter fait le lien entre les données provenant de notre API  et les Fragments (User Control) de notre application
//On utilise un adapter seulement pour une Liste de données (Recycler view)
class CommentaireRecyclerViewAdapter(private val mValues: List<Commentaire>): RecyclerView.Adapter<CommentaireRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.commentaire_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(mValues[position])
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var lblMessage = mView.lblMessage
        var commentaire: Commentaire? = null
        var lblNom = mView.lblNom
        var lblPrenom = mView.lblPrenom
        var rtbEtoiles=mView.rtbEtoiles

        fun bind(commentaire: Commentaire) {
            this.commentaire = commentaire


            lblMessage.text = commentaire.message
            lblNom.text = commentaire.nom
            lblPrenom.text = commentaire.prenom
            rtbEtoiles.rating=commentaire.etoile.toFloat()
        }
    }
}
















