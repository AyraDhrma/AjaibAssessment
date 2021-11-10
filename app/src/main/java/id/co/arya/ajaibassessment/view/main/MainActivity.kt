package id.co.arya.ajaibassessment.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.co.arya.ajaibassessment.databinding.ActivityMainBinding
import id.co.arya.ajaibassessment.utils.Status

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
                        println("loading")
                    }
                    Status.SUCCESS -> {
                        println(resource.data as String)
                    }
                    Status.ERROR -> {
                        println("Error Connection")
                    }
                }
            }
        })
    }


}