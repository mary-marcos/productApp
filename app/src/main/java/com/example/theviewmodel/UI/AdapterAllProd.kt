package com.example.theviewmodel.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theviewmodel.databinding.ProductItemBinding
import com.example.theviewmodel.model.Product

class AdapterAllProd(var listen:clicklistener) : ListAdapter<Product, AdapterAllProd.ItemViewHolder>(DiffCallback())  {
    lateinit var binding: ProductItemBinding
    class ItemViewHolder(var binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
//       val tittle:TextView=itemView.findViewById<TextView>(R.id.tittle_tv)
//        val description:TextView=itemView.findViewById<TextView>(R.id.descrip_tv)
//        val card:CardView=itemView.findViewById(R.id.cardView)
//        val productImage: ImageView= itemView.findViewById(R.id.prod_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater :LayoutInflater= parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //parent.context.getSystemService(context.LAYOUT_INF)
        //LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        binding=ProductItemBinding.inflate(inflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current=getItem(position)
        holder.binding.tittleTv.text=current.title
        holder.binding.descripTv.text=current.title
        Glide.with(holder.itemView.context).load(current.thumbnail).into(holder.binding.prodImage)
        holder.binding.cardView.setOnClickListener { listen.listener(current)
        }
        // mylistener.invoke(current)

        //  tittle.text=current.title
        // holder.description.text=current.descriptiocn
        //  Glide.with(holder.itemView.context).load(current.thumbnail).into(holder.productImage)
        // holder.card.setOnClickListener { com.respond(current)
        //mylistener.invoke(current)

    }
}


class  DiffCallback : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}