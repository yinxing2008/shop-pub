package cn.hsp.shop.module.cart

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.module.goods_list.CartAdapter
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants

class CartFragment : BaseVmFragment<CartViewModel>() {
    private val adapter = CartAdapter()
    private lateinit var goodsListRv: RecyclerView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    override fun viewModelClass() = CartViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_cart

    override fun initView() {
        goodsListRv = findViewById(R.id.goodsListRv)
        goodsListSrl = findViewById(R.id.goodsListSrl)

        goodsListRv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        goodsListSrl.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        mViewModel.queryGoods(
            onSuccess = {
                goodsListSrl.isRefreshing = true
            },
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun initListeners() {

    }

    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}