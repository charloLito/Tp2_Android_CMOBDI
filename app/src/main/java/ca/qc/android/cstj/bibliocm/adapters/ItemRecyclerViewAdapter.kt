package ca.qc.android.cstj.bibliocm.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliocm.R


import ca.qc.android.cstj.bibliocm.models.Item
import kotlinx.android.synthetic.main.fragment_detail_succursale.view.*
import kotlinx.android.synthetic.main.item_card.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemRecyclerViewAdapter(private val mValues: List<Item>,
                              private val mListener: OnListFragmentItemInteractionListener?) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
        holder.mView.setOnClickListener{
            mListener!!.onListFragmentInteraction(holder.item)

        }
    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var lblNom = mView.lbltitre
        var item: Item? = null
        fun bind(item: Item){
            this.item=item
            lblNom.text=item.getAffichage()
        }



    }
}
