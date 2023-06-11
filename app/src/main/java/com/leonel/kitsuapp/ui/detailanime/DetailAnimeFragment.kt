package com.leonel.kitsuapp.ui.detailanime


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.leonel.kitsuapp.databinding.FragmentDetailAnimeBinding
import com.leonel.kitsuapp.utils.ConnectionLiveData
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAnimeFragment : Fragment() {

    private var _binding: FragmentDetailAnimeBinding? = null

    private val binding get() = _binding!!
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailanimeViewModel =
            ViewModelProvider(this).get(DetailAnimeViewModel::class.java)
        _binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.loading.isVisible=false
        val idAnime = arguments?.getString("idAnime")
        idAnime?.let {

            connectionLiveData = activity?.let { ConnectionLiveData(it) }!!
            connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
                isNetworkAvailable?.let { conect->
                    if(conect) {
                        detailanimeViewModel.getAnimeById(it)
                    }
                }
            }
        }

        detailanimeViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
        }

        detailanimeViewModel.animeByIdLiveData.observe(viewLifecycleOwner){

            Picasso.get().load(it.attributes.coverImage?.large).into(binding.imgBannerAnimeDetail)
            if(!it.attributes.titles.en.isNullOrBlank())  binding.txtTitleAnimeDetail.text = it.attributes.titles.en else binding.txtTitleAnimeDetail.text = it.attributes.titles.jaJp
            Picasso.get().load(it.attributes.posterImage.large).into(binding.includeHeader.imgMovie)
            binding.includeHeader.txtTitle.text= it.attributes.canonicalTitle
            binding.includeHeader.ratingAnimeDetail.setIsIndicator(true)

            binding.includeHeader.ratingAnimeDetail.rating= detailanimeViewModel.convertRating(it.attributes.averageRating)

            binding.includeHeader.txtPopularity.text= "Episodios: " + it.attributes.episodeLength
            binding.includeHeader.txtReleaseDate.text= it.attributes.startDate
            binding.includeHeader.txtLenguaje.text= it.attributes.showType
            binding.txtDescripcionAnimeDetail.text = it.attributes.description
            binding.txtDirigidoAnimeDetail.text = it.attributes.ageRatingGuide
            binding.txtProducidoAnimeDetail.text = it.attributes.status

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}