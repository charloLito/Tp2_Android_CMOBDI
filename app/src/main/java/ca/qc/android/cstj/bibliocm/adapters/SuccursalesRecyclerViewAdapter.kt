package ca.qc.android.cstj.bibliocm.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.android.cstj.bibliocm.R

import ca.qc.android.cstj.bibliocm.fragments.SuccursaleListFragment.OnListFragmentInteractionListener
import ca.qc.android.cstj.bibliocm.models.Categorie
import ca.qc.android.cstj.bibliocm.models.Item
import ca.qc.android.cstj.bibliocm.models.Succursale
import kotlinx.android.synthetic.main.item_card.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class SuccursalesRecyclerViewAdapter(private val mValues: List<Item>,
                                     private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<SuccursalesRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
        holder.mView.setOnClickListener{
            when(holder.item){
                is Succursale -> {  val succursale = holder.item as Succursale
                                    mListener!!.onListFragmentInteraction(succursale)}
                is Categorie -> {/*TODO: Categorie*/}
            }
        }
    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var lblNom= mView.lblNom
        var item: Item? = null
        fun bind(item: Item){
            this.item=item
            lblNom.text=item.getAffichage()
        }



    }
}
