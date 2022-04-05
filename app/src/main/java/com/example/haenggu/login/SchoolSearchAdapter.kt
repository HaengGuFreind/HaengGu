package com.example.haenggu.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.haenggu.R

class SchoolSearchAdapter (var schoolist: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var schoolfilterlist = ArrayList<String>()
    // exampleListFull . exampleList

    init {
        schoolfilterlist = schoolist
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, data: String, pos: Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(schoolfilterlist.get(position));
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_searchschool, parent, false))
    }

    override fun getItemCount(): Int {
        return schoolfilterlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(school: String): Unit {
            //            val schoolname = itemView.findViewById<TextView>(R.id.item_searchschool_tv)

            val rvschool = itemView.findViewById< TextView>(R.id.item_searchschool_tv)
            rvschool.text = school

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,school,pos)
                }
            }
        }


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    schoolfilterlist = schoolist as ArrayList<String>
                } else {
                    val resultList = ArrayList<String>()
                    for (row in schoolist) {
                        if (row.contains(constraint.toString())) {
                            resultList.add(row)
                        }
                    }
                    schoolfilterlist = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = schoolfilterlist
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                schoolfilterlist = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }

}
//class SchoolSearchAdapter (private var schools: ArrayList<String>) : RecyclerView.Adapter<SchoolSearchAdapter.ViewHolder>() {
//    private var listener: OnClickListener? = null
//
//    fun setListener(onClickListener: OnClickListener) {
//        this.listener = onClickListener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_searchschool, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItems(schools[position])
//        //Custom OnClickListener Event
//        holder.itemView.setOnClickListener(View.OnClickListener {
//            if (listener != null) {
//                listener!!.onClickEvent(schools[position])
//            }
//        })
//    }
//
//    override fun getItemCount(): Int {
//        return schools.size
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindItems(school: String) {
//            val schoolname = itemView.findViewById<TextView>(R.id.item_searchschool_tv)
//            schoolname.text = school
//        }
//    }
//
//    /*This method will filter the list and here we are passing the filtered data
//        and assigning it to the list with notifyDataSetChanged method
//    */
//    fun filterList(filteredNames: ArrayList<String>) {
//        this.schools = filteredNames
//        notifyDataSetChanged()
//    }
//}

//class SchoolSearchAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val schoolnames: List<String>):
//    ArrayAdapter<String>(context, layoutResource, schoolnames),
//    Filterable {
//    private var mschool: List<String> = schoolnames
//
//    override fun getCount(): Int {
//        return mschool.size
//    }
//
//    override fun getItem(p0: Int): String? {
//        return mschool.get(p0)
//    }
//
//    override fun getItemId(p0: Int): Long {
//        // Or just return p0
//        return mschool.get(p0).toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(R.layout.item_searchschool, parent, false) as TextView
//
//        val schools:TextView = view.findViewById(R.id.item_searchschool_tv)
//        schools.text =  mschool[position]
//        return view
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
//                mschool = filterResults.values as List<String>
//                notifyDataSetChanged()
//            }
//
//            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
//                val queryString = charSequence?.toString()?.toLowerCase()
//
//                val filterResults = Filter.FilterResults()
//                filterResults.values = if (queryString==null || queryString.isEmpty())
//                    schoolnames
//                else
//                    schoolnames.filter {
//                        it.contains(queryString)
//                    }
//                return filterResults
//            }
//        }
//    }
//}