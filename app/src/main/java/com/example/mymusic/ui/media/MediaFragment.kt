package com.example.mymusic.ui.media

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymusic.R
import com.example.mymusic.databinding.FragmentMediaBinding
import com.example.mymusic.models.Media
import com.example.mymusic.ui.media.adapter.MediaAdapter
import com.example.mymusic.ui.media.adapter.MediaAdapter2
import com.example.mymusic.ui.media.adapter.MediaDurationAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MediaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMediaBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
//    private val mediaAdapter= MediaAdapter()    // work perfectly with notifyItemChange(position)
//    private val mediaAdapter2= MediaAdapter2()  // work perfectly without notifyItemChange(position) (View only)
    private val mediaAdapterDurationAdapter= MediaDurationAdapter()  // for duration without notifyItemChange(position) (View only)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentMediaBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.mediaRecyclerView.adapter=mediaAdapter
        binding.mediaRecyclerView.adapter=mediaAdapterDurationAdapter

//        mediaAdapter.addData(createListOfMedia())
        mediaAdapterDurationAdapter.addData(createListOfMedia())
    }

    private fun createListOfMedia():MutableList<Media> {
        val mediaList= mutableListOf<Media>()
        mediaList.add(Media(isPlaying = false, soundUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"))
        mediaList.add(Media(isPlaying = false, soundUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
//        mediaList.add(Media(isPlaying = false, soundUrl = ""))
        mediaList.add(Media(isPlaying = false, soundUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"))

        return mediaList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MediaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}