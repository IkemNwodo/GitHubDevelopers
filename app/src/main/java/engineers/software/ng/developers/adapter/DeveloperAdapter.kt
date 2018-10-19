package engineers.software.ng.developers.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import engineers.software.ng.developers.R
import engineers.software.ng.developers.model.Developers


class DeveloperAdapter constructor(private val context: Context, developerList: ArrayList<Developers>,
                                   listener: ItemClickListener): RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>() {

    private var developerList: List<Developers> = arrayListOf()
    private var mInflater:LayoutInflater
    private var mItemClickListener: ItemClickListener

    init {
        this.developerList = developerList
        this.mInflater = LayoutInflater.from(context)
        mItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val itemView = mInflater
                .inflate(R.layout.developerlist_item, parent, false)
        return DeveloperViewHolder(itemView)
    }

    override fun getItemCount() = developerList.size

    override fun onBindViewHolder(holder: DeveloperAdapter.DeveloperViewHolder, position: Int) {
        val developers: Developers = developerList[position]

        holder.username.text = developers.username
        holder.developerState.text = developers.state

        val builder: Picasso.Builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(developers.avatar_url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.profileImage)

        holder.itemView.setOnClickListener{
            v -> mItemClickListener.onItemClick(developers) }







    }

    inner class DeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var profileImage: CircleImageView
        var username: TextView
        var developerState: TextView

        init {
            profileImage = itemView.findViewById(R.id.profile_image)
            username = itemView.findViewById(R.id.developer_name)
            developerState = itemView.findViewById(R.id.developer_state)
        }
    }

    interface ItemClickListener{
        fun onItemClick(developers: Developers)
    }
}
