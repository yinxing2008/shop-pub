package cn.lblbc.shop.module.search

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.module.category.CategoryGoodsAdapter
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.part_search_top.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SearchActivity : BaseVmActivity<SearchViewModel>() {
    private lateinit var goodsAdapter: CategoryGoodsAdapter


    override fun viewModelClass() = SearchViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_search
    override fun initView() {
        goodsAdapter = CategoryGoodsAdapter(this)
        goodsGridView.adapter = goodsAdapter
        goodsGridView.setOnItemClickListener { _, _, position, _ -> onItemClick(goodsAdapter.getData(position)) }
        showSoftInputFromWindow(searchEt)
    }

    private fun showSoftInputFromWindow(editText: EditText) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun initListeners() {
        searchBackIv.setOnClickListener { finish() }
        searchEt.setOnClickListener {
            noDataLayout.visibility = GONE
            goodsGridView.visibility = GONE
            searchHisView.visibility = VISIBLE
        }
        searchEt.setOnEditorActionListener { _, keyCode, _ ->
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            true
        }
        searchTv.setOnClickListener { search() }
        searchHisView.setCallback {
            searchEt.setText(it)
            search()
        }
    }

    private fun search() {
        val keyword = searchEt.text.toString()
        mViewModel.queryGoods(keyword)
        searchHisView.addKeyword(keyword)
        hideSoftKeyboard(this)
    }

    override fun observe() {
        mViewModel.goodsList.observe(this, {
            if (it.isEmpty()) {
                noDataLayout.visibility = VISIBLE
                goodsGridView.visibility = GONE
                searchHisView.visibility = GONE
            } else {
                noDataLayout.visibility = GONE
                goodsGridView.visibility = VISIBLE
                searchHisView.visibility = GONE
                goodsAdapter.setData(it)
            }
        })
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(this, GoodsActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }
}