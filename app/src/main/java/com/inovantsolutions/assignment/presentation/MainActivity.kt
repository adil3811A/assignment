package com.inovantsolutions.assignment.presentation

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.inovantsolutions.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.inovantsolutions.assignment.R
import kotlinx.coroutines.launch
import androidx.core.view.isVisible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val myViewModel : MyViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private var quantity = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.white)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {


            repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModel.uiSate.collect { it->
                    when(it){
                        is UiSate.Error ->{
                            Toast.makeText(applicationContext, "erros is ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                        UiSate.Idea -> {
                            binding.progresbar.visibility = View.VISIBLE
                        }
                        UiSate.Loading ->{
                            binding.progresbar.visibility = View.VISIBLE
                        }
                        is UiSate.Sucsess -> {
                            binding.progresbar.visibility = View.GONE
                            binding.scrollView.visibility = View.VISIBLE
                            binding.bottomLayout.visibility = View.VISIBLE
                            val adepter = BannerImageViewPagerAdepter(applicationContext , it.response.data.images)
                            binding.viewpager2.adapter = adepter
                            binding.titleTxt.text = it.response.data.name
                            binding.indecator.attachTo(binding.viewpager2)
                            binding.colorRecyclerView.adapter = ColoRecyclerViewAdepter(applicationContext , it.response.data.images)
                            binding.productName.text = it.response.data.name
                            binding.brandName.text = it.response.data.brand_name
                            binding.skuTxt.text = "SKU : "+it.response.data.sku
                            binding.quantityTxt.text = quantity.toString()
                            binding.decreaseQuantity.setOnClickListener {
                                if(quantity>=0){
                                    quantity = quantity-1
                                    binding.quantityTxt.text = quantity.toString()
                                }
                            }
                            binding.increasQuantity.setOnClickListener {
                                quantity = quantity+1
                                binding.quantityTxt.text = quantity.toString()
                            }
                            binding.expandBtn.setOnClickListener {
                                if(binding.informationTxt.isVisible){
                                    binding.dropDownBtn.rotation =-90f
                                    binding.informationTxt.visibility = View.GONE
                                }else{
                                    binding.dropDownBtn.rotation = 90f
                                    binding.informationTxt.visibility = View.VISIBLE
                                }
                            }
                            val htmlResponce = it.response.data.description
                            val information = HtmlCompat.fromHtml(htmlResponce , Html.FROM_HTML_MODE_LEGACY)
                            binding.informationTxt.text = information
                            val price = it.response.data.final_price.toDoubleOrNull()?.let {
                                String.format("%.2f", it).toDouble()
                            } ?: 0.0
                            binding.priceTxt.text = "$price KWD"
                        }
                    }
                }
            }
        }
    }
}