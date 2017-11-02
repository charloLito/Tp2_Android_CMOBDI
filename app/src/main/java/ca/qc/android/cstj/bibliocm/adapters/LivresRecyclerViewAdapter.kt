/*package ca.qc.android.cstj.bibliocm.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.android.cstj.bibliocm.R

import ca.qc.android.cstj.bibliocm.fragments.LivreListFragment.OnListFragmentInteractionListener

import ca.qc.android.cstj.bibliocm.models.Livre
import kotlinx.android.synthetic.main.livre_card.view.*


class LivresRecyclerViewAdapter(private val mValues: List<Livre>,
                                private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<LivresRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.livre_card, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
        holder.mView.setOnClickListener{
            mListener!!.onListFragmentInteraction(holder.livre)

        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var lblTitre = mView.lblTitre
        var livre: Livre? = null
        fun bind(livre: Livre){
            this.livre=livre
            lblTitre.text=livre.titre
        }



    }
}*/
