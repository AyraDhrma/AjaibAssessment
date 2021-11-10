package id.co.arya.ajaibassessment.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.arya.ajaibassessment.databinding.ActivityMainBinding
import id.co.arya.ajaibassessment.datasource.response.RepositoryListResponse
import id.co.arya.ajaibassessment.utils.Status
import id.co.arya.ajaibassessment.utils.hide
import id.co.arya.ajaibassessment.utils.show
import id.co.arya.ajaibassessment.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModelFactory = MainViewModelFactory("https://api.github.com/")
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        mainViewModel.getRepoFromUser("AyraDhrma").observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        viewBinding.progressCircularMain.show()
                    }
                    Status.SUCCESS -> {
                        viewBinding.progressCircularMain.hide()
                        resource.data?.let { it1 -> setToRepositoryList(it1) }
                    }
                    Status.ERROR -> {
                        viewBinding.progressCircularMain.hide()
                        showToast(this, resource.message.toString())
                    }
                }
            }
        })
    }

    private fun setToRepositoryList(data: RepositoryListResponse) {
        val adapter = RepositoryListRecyclerAdapter(data)
        viewBinding.listRepository.hasFixedSize()
        viewBinding.listRepository.layoutManager = LinearLayoutManager(this)
        viewBinding.listRepository.adapter = adapter
    }

}