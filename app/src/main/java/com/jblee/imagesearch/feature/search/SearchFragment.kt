package com.jblee.imagesearch.feature.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jblee.imagesearch.Constants
import com.jblee.imagesearch.data.model.ImageModel
import com.jblee.imagesearch.databinding.FragmentSearchBinding
import com.jblee.imagesearch.data.retrofit_client.apiService
import com.jblee.imagesearch.model.SearchItemModel
import com.jblee.imagesearch.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 사용자에게 이미지 검색 기능을 제공하는 Fragment 클래스입니다.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager

    private var resItems: ArrayList<SearchItemModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()   // 뷰 초기 설정
        setupListeners() // 리스너 설정

        return binding.root
    }

    /**
     * 화면 뷰들의 초기 설정을 하는 메서드입니다.
     */
    private fun setupViews() {
        // RecyclerView 설정
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearchResult.layoutManager = gridmanager

        adapter = SearchAdapter(mContext)
        binding.rvSearchResult.adapter = adapter
        binding.rvSearchResult.itemAnimator = null

        // 최근 검색어를 가져와 EditText에 설정
        val lastSearch = Utils.getLastSearch(requireContext())
        binding.etSearch.setText(lastSearch)
    }

    /**
     * 화면에 있는 UI 요소들의 리스너 설정을 하는 메서드입니다.
     */
    private fun setupListeners() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.tvSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(mContext, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

            // 키보드 숨기기
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        }
    }

    /**
     * 입력한 검색어로 이미지를 검색하는 메서드입니다.
     */
    private fun fetchImageResults(query: String) {
        apiService.image_search(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ImageModel?> {
                override fun onResponse(call: Call<ImageModel?>, response: Response<ImageModel?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                resItems.add(SearchItemModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = resItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ImageModel?>, t: Throwable) {
                    Log.e("#jblee", "onFailure: ${t.message}")
                }
            })
    }
}
