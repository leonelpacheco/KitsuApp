package com.leonel.kitsuapp.ui.animes


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.leonel.kitsuapp.R
import com.leonel.kitsuapp.adapter.AnimeAdapter
import com.leonel.kitsuapp.databinding.FragmentAnimesBinding
import com.leonel.kitsuapp.utils.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimesFragment : Fragment(), AnimeAdapter.OnAnimeClickListener {

    private var _binding: FragmentAnimesBinding? = null

    private val binding get() = _binding!!
    private lateinit var connectionLiveData: ConnectionLiveData
private lateinit var adapter: AnimeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val animesViewModel =
            ViewModelProvider(this).get(AnimesViewModel::class.java)
        _binding = FragmentAnimesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchAnime.setText("")
        binding.rvAnimes.layoutManager= GridLayoutManager (activity,2)
        binding.loading.isVisible=false


        //*******Revisar conexion a internet********
        connectionLiveData = activity?.let { ConnectionLiveData(it) }!!
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
               if(it) {
                   binding.txtMessage.visibility= View.GONE
                   animesViewModel.getAnimes()
               }
            }
        }

        animesViewModel.animesLiveData.observe(viewLifecycleOwner) { animes->
             adapter = AnimeAdapter(animes,this)
            binding.rvAnimes.adapter = adapter

           binding.searchAnime.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    val animesfiltrados =  animes.filter {
                        if(!it.attributes.titles.en.isNullOrBlank())
                            it.attributes.titles.en.lowercase().contains(s.toString().lowercase()) || it.attributes.startDate.lowercase().contains(s.toString().lowercase())
                        else
                        it.attributes.titles.jaJp.lowercase().contains(s.toString().lowercase()) || it.attributes.startDate.lowercase().contains(s.toString().lowercase())
                    }
                    adapter.updatefilteranimes(animesfiltrados)
                }
            })

        }

        animesViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
            binding.searchAnime.setText("")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(idAnime: String) {
        val bundle = Bundle()
        bundle.putString("idAnime", idAnime)
        findNavController().navigate(R.id.action_animesFragment_to_detailAnimeFragment, bundle)

    }

}