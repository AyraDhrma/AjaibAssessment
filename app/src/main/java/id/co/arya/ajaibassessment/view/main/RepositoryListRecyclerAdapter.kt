package id.co.arya.ajaibassessment.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import id.co.arya.ajaibassessment.databinding.ItemsRvRepositoryBinding
import id.co.arya.ajaibassessment.datasource.response.RepositoryListResponse

class RepositoryListRecyclerAdapter(val listResponse: RepositoryListResponse) :
    RecyclerView.Adapter<RepositoryListRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemsRvRepositoryBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsRvRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listResponse[position]){
                binding.titleRepository.text = name
            }
        }
    }

    override fun getItemCount(): Int {
        return listResponse.size
    }
}