package com.mynews.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mynews.R
import com.mynews.adapters.News_Adapter
import com.mynews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Saved_News_Fragment : Fragment(R.layout.fragment_saved__news_) {
    lateinit var adapter: News_Adapter

    val viemodel: NewsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = News_Adapter()



        view.findViewById<RecyclerView>(R.id.rv_saved_news).adapter=adapter
        view.findViewById<RecyclerView>(R.id.rv_saved_news).layoutManager= LinearLayoutManager(activity)

viemodel.getNewsItems.observe(viewLifecycleOwner,{
    adapter.submitData(lifecycle,it)

})

        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
             when(direction){
                 ItemTouchHelper.LEFT ->{

                 viemodel.delete(adapter.differ)
                     adapter.notifyDataSetChanged()
                     Toast.makeText(activity,"Article Deleted",Toast.LENGTH_LONG).show()
                 }
                 ItemTouchHelper.RIGHT ->{
                     viemodel.delete(adapter.differ)
                     adapter.notifyDataSetChanged()
                     Toast.makeText(activity,"Article Deleted",Toast.LENGTH_LONG).show()
             }
             }


            }

        }).attachToRecyclerView(view.findViewById<RecyclerView>(R.id.rv_saved_news))


        adapter.setOnItemClickListner {
            findNavController().navigate(Saved_News_FragmentDirections.actionSavedNewsFragmentToArticleNewsFragment(it))
        }
    }

}